// assembler.js
//
// A simple assembler for the Minecraft Computer. Takes a text file written in
// the assembly language and outputs an array of 0's and 1's representing where
// redstone torches must go in the program memory

"use strict";
const AssemblerVersion = "3.2.0";

// Revision History
//
//  version    date                     Change
// -------  ----------  -------------------------------------------------------
// 1.0.0	10/12/2020	Changes in this version:
//							-AssemblerMessage function to organize and handle
//							console outputs
//							-replaceStringInData function that takes a file,
//							target string, and a value to replace the target
//							with and replaces all instances of the target with
//							the correct value (as well as removing blank space
//							before or after the final string)
//
// 2.0.0	10/13/2020	Changes in this version:
//							-Added the AssemblerMessageEnable constant to allow
//							console messages to be enabled/disabled
//							-Added a for-loop and the executeLine function to
//							take the return value from replaceStringInData,
//							slip up each line, and eventually execute the valid
//							assembly code
//							-Replaced the use of fs.fileRead in replaceStringInData
//							with fs.fileReadSync to read the file synchronously
//								-Removed the err argument to fs.fileReadSync
//								-Moved the value of fs.fileReadSync to the data
//								variable which is used later in the 
//								replaceStringInData
// 3.0.0	10/21/2020	Changes in this version:
//							-Added constants for the input and output directories
//							at the top of the program for easy editing
//							-Added instructionFields and instructionValidationTable
//							hashes for building the intructions
//							-Added function writeFileToData, called from within
//							replaceStringInData which writes the hexadecimal
//							version of the instructions to an output file
//							-Added function buildInstruction to take the instruction
//							in assembly language, check for a valid opcode and
//							arguments, and convernt it to hex for output
//
// 3.0.1	10/21/2020	Changes in this version:
//							-Fixed a bug with hex output
//
// 3.1.0	10/21/2020	Changes in this version:
//							-Automatically set the intructions to all lowercase
//							when parsing
//							-Added in the rest of the opcode map
//
// 3.2.0	10/22/2020	Changes in this version:
//							-Fixed bugs with the opcode hash
//							-Reordered the opcode hash to make more sense
//							-Other minor functional changes

// Version information
this.Version = AssemblerVersion
AssemblerMessage("Assembler v" + this.Version);

// Assembly Language Documentation
// ----------------------------------------------------------------------------
//
//


// ============================================================================
// AssemblerMessage
//
// Function to emit a message, with optional arguments, which are separated
// by ", "
//
// Arguments--
//
// msg:        Message
//
// args:       Optional list of arguments to output
//
// Returns--
//
// None
//
function AssemblerMessage(msg, ...args) {

	const AssemblerMessageEnable = true;

    let message = "A-MSG:    " + msg;
    if (args.length > 0) {
      	message += " " + args.join(", ");
    }

	if (AssemblerMessageEnable) {
		console.log(message);
	}

} // end: function AssemblerMessage


// ============================================================================
// GLOBAL VARIABLES
//
// The current real line number of the program counter (not included commented
// lines or blank-space lines)
let pc = 0;
// An object used to index the value of the pc using a label
let labelHash = {};
// The locations of the input and output text files (edit from here for easy
// access)
const inputDirectory = '/Users/jonathan/Documents/VS Code/Assembler/input.asm';
const outputDirectory = '/Users/jonathan/Documents/VS Code/Assembler/output.bin';
//
// end: GLOBAL VARIABLES


// ============================================================================
// Instruction field descriptors
//
// Each field is indexed by the name of the field and is a hash that describes
// the type of field and the allowable values. Keys to that hash are:
//
// isOpcode    True if the field is an opcode
//
// isLabel     True if the field is a branch label
//
// startsAt    Right-most bit of the field. This is also the amount to left
//             shift a right-justified value to position it to the field.
//
// mask        The mask with which to AND a right-justified value to ensure
//             that it fits in the field
//
// pattern     The RegEx pattern to use to match a valid value in that field
//
// minVal      The minimum allowable value for the field
//
// maxVal      The maximum allowable value for the field
//
const instructionFields = {
	// Opcode: minVal and maxVal aren't used.
	"opcode"  : {isOpcode: true,  isLabel: false, isImm: false, startsAt: 20,  mask: 0x1f, pattern : /^(\w+)$/,            minVal: 0, maxVal: 0},
	// The four register fields. Valid register numbers are 0 to 15
	"rw"      : {isOpcode: false, isLabel: false, isImm: false, startsAt: 16,  mask: 0xf,  pattern : /^r(\d+)$/,           minVal: 0, maxVal: 15},
	"ra"      : {isOpcode: false, isLabel: false, isImm: false, startsAt: 12,  mask: 0xf,  pattern : /^r(\d+)$/,           minVal: 0, maxVal: 15},
	"rb"      : {isOpcode: false, isLabel: false, isImm: false, startsAt:  8,  mask: 0xf,  pattern : /^r(\d+)$/,           minVal: 0, maxVal: 15},
	// The immediate field. It's an 8-bit field, so the valid numbers are 0 to 255, but the
	// value can be specified as hex, e.g., 0x... or decimal
	"imm"     : {isOpcode: false, isLabel: false, isImm: true,  startsAt:  0,  mask: 0xff, pattern: /^(0x[0-9a-f]+|\d+)$/, minVal: 0, maxVal: 255},
	// Lable for branch instructions. minVal and maxVal aren't used.
	"label"   : {isOpcode: false, isLabel: true, isImm: false,  startsAt:  0,  mask: 0xff, pattern: /^(\w+)$/,             minVal: 0, maxVal: 0}
} // end: hash intructionFields

// Instruction Validation table
//                                     Opcode Map
//                                    Instn<22:20>
//                  000    001   010   011   100   101   110   111
//                 +-----+-----+-----+-----+-----+-----+-----+-----+
//               00| or  | and |     | not | cmp |     | add | sub |
//                 +-----+-----+-----+-----+-----+-----+-----+-----+
//               01| ori | andi|     | noti| cmpi|     | addi| subi|
// Instn<23:33>    +-----+-----+-----+-----+-----+-----+-----+-----+
//               10| brz | bro |     |     |     |     |     |     |
//                 +-----+-----+-----+-----+-----+-----+-----+-----+
//               11| ld  | st  |     |     |     |     |     | mf  |
//                 +-----+-----+-----+-----+-----+-----+-----+-----+
const instructionValidationTable = {
	// First row of opcode table
	"or"  :  {opcode:  0, argCount: 3, args:["rw","ra","rb"]},
	"and" :  {opcode:  1, argCount: 3, args:["rw","ra","rb"]},
	"not" :  {opcode:  3, argCount: 2, args:["rw","rb"]},
	"cmp" :  {opcode:  4, argCount: 3, args:["rw","ra","rb"]},
	"add" :  {opcode:  6, argCount: 3, args:["rw","ra","rb"]},
	"sub" :  {opcode:  7, argCount: 3, args:["rw","ra","rb"]},
	// Second row of opcode table
	"ori"  : {opcode:  8, argCount: 3, args:["rw","ra","imm"]},
	"andi" : {opcode:  9, argCount: 3, args:["rw","ra","imm"]},
	"noti" : {opcode: 11, argCount: 2, args:["rw","imm"]},
	"cmpi" : {opcode: 12, argCount: 3, args:["rw","ra","imm"]},
	"addi" : {opcode: 14, argCount: 3, args:["rw","ra","imm"]},
	"subi" : {opcode: 15, argCount: 3, args:["rw","ra","imm"]},
	// Third row of opcode table
	"brz" :  {opcode: 16, argCount: 2, args:["rb","label"]},
	"bro" :  {opcode: 17, argCount: 2, args:["rb","label"]},
	// Fourth row of opcode table
	"ld" :   {opcode: 24, argCount: 3, args:["rw","ra","imm"]},
	"st" :   {opcode: 25, argCount: 3, args:["rw","ra","imm"]},
	"mf" :   {opcode: 31, argCount: 3, args:["rw","ra","imm"]},
	// pseudo instructions that provide more intuitive access to certain instructions
	"nop" :  {opcode:  0, argCount: 0, args:[]},
	"li"  :  {opcode:  8, argCount: 2, args:["rw","imm"]},
	"br"  :  {opcode: 16, argCount: 1, args:["label"]},
} // end: hash instructionValidationTable


// ============================================================================
// replaceStringInData
//
// Function to search through all lines of a file for specific characters or
// phrases (primarily used to remove lines with comments "//")
//
// Arguments--
//
// directory:		The local path to the input file
//
// search:			The string to search for in the file
//
// replace:			The string to replace the "search" string with if and when
//					it is found
//
// Returns--
//
// returnData:		The full string of all the appended lines (after replacing
//					terms and trimming the lines)
//
function replaceStringInData(directory, search, replace) {

	const fs = require('fs')


	let searchContent = search;
	let replaceContent = replace;


	let data = fs.readFileSync(directory)

	let fileData = data.toString(); // Save the file data as a string
	let fileLinesArray = fileData.split("\n"); // Split the file into individual lines as an array
	AssemblerMessage("File lines:", fileLinesArray);

	let returnData = []; // Declare the returnData array to be used later

	for (let i = 0; i < fileLinesArray.length; i++) { // Repeat for every line of the file

		// Save each individual line to the temporary fileLine variable
		// then replace the desired character(s) and trim any white space
		// from the start and end of the line
		let fileLine = fileLinesArray[i].replace(searchContent, replaceContent);
		fileLine = fileLine.trim();
		fileLine = fileLine.toLowerCase();

		// Save each of the appended lines to a new array
		if (fileLine.length !== 0) {
			returnData.push(fileLine);
		}

		// Print the line to console
		AssemblerMessage("Line " + i + " Data: \n    " + fileLine);
	}

	// Return the edited array of lines
	AssemblerMessage("Return value:", returnData);
	return returnData;
	
} // end: function replaceStringInData


// Set up timestamp information to print to the output file as a comment
let today = new Date();
let date = today.getMonth() + '-' + (today.getDate()) + '-' + today.getFullYear();
let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
let dateTime = date + ' ' + time;

writeDataToFile(outputDirectory, "// assembler.js v" + AssemblerVersion + "\n" + "// " + dateTime + "\n");
// Take the edited lines from replaceStringInData and compile them
let lines = replaceStringInData(inputDirectory, /\/\/.*$/, "");


// For each line, parse and build the line
for (let i = 0; i < lines.length; i++) {

	// Display and use the contents and length of each line
	// Message should look like this 'Line: x has length: y, and states: "z"'
	AssemblerMessage("Line: " + i, "has length: " + lines[i].length, "and states: \"" + lines[i] + "\"");
	// For each line of the assembly code, parse and print it
	let line = lines[i];
	let matchStr = lineParse(line);
	let buildResult = buildInstruction(matchStr);
  	if (!buildResult.result) {
    	AssemblerMessage(`Unable to build instruction for ${line}: ${buildResult.message}`)
	} 
	else {
		writeDataToFile(outputDirectory, `0x${buildResult.instruction.toString(16).padStart(7, "0")}` + "\n")

    	AssemblerMessage(`Assembled instruction for ${line} was 0x${buildResult.instruction.toString(16).padStart(7, "0")}, with branch target \"${buildResult.brTarget}\"; immediate was hex: ${buildResult.immWasHex}`)
  	}

}


// ============================================================================
// writeDataToFile
//
// Takes a given directory and some data and writes it to the file given.
//
// Arguments--
//
// directory:		The location of the file to write the data to on the
//					current machine
//
// dataToWrite:		The data to be written to the specified file
//
// Returns--
//
// None
//
function writeDataToFile(directory, dataToWrite) {
	var fs = require("fs");

	fs.appendFile(directory, dataToWrite, (err) => {
		if (err) {
			throw err;
		}
		else {
			AssemblerMessage("Output file at \"" + directory + "\" written")
		}
	});
}


// ============================================================================
// buildInstruction
//
// Take the results of lineParse and build an instruction from the text.
//
// Arguments--
//
// parsedValue:		The return value from parseLine
//
// Returns--
//
// A hash with the results of the attempt to build the instruction, with the
// following keys:
//
// result:        (Boolean) The result of the function. If true, the value
//                of the instruction is in the instruction key. If false, something
//                went wrong, and the error message is in the message key
//
// instruction:   (Integer) The value of the assembled instruction
//
// brTarget:      (String) If the instruction is a branch, this is the
//                symbolic branch target. "" if not a branch
//
// immWasHex:     (Boolean) True if the immediate field was a hex value
//
// message:       (String) The error message if the assembly failed
//
function buildInstruction(parsedValue) {
	var instruction = 0;
	var errorMessage = "";
	var brTarget = "";
  
	let iValEntry = instructionValidationTable[parsedValue.opcode];
	// Get the instruction validation entry based on the opcode. If this comes
	// back undefined, then it was an invalid instruction
	if (iValEntry === undefined) {
		errorMessage = "Opcode not recogignized: " + parsedValue.opcode;
		return { result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage };
	}
  
	// Start building the instruction with the opcode
	instruction |= iValEntry.opcode << instructionFields["opcode"].startsAt;
  
	// Confirm that the argument count was what was expected
	if (iValEntry.argCount !== parsedValue.argCount) {
		errorMessage = parsedValue.opcode + " had the wrong argument count: expected " + iValEntry.argCount + ", saw " + parsedValue.argCount;
		return { result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage };
	}
  
	let immWasHex = false;
	let fieldArgs = iValEntry.args;
	for (let i = 0; i < iValEntry.argCount; i++) {
  
		// argType is the argument type for this argument from instructionValidationTable[opcode].args[i]
		let argType = fieldArgs[i];
	
		// iField is the expected argument field
		let iField = instructionFields[argType];
	
		// argSupplied is the text of the argument
		let argSupplied = parsedValue.args[i];
	
		// Use the pattern for this argument type to parse the text value
		var argMatch = argSupplied.match(iField.pattern);
	
		// If the parse fails, it's an error
		if (argMatch === null || argMatch[1] === undefined) {
			errorMessage = parsedValue.opcode + " had incorrect argument " + String(i+1) + ": " + argSupplied;
			return{result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage};
		}
	
		// If this field is a branch target, we're done
		if (iField.isLabel) {
			return{result: true, instruction: instruction, immWasHex: false, brTarget: argSupplied, message: ""};
		}
	
		// For anything else, make sure that the value is in range
		if (argMatch[1] < iField.minVal || argMatch[1] > iField.maxVal) {
			errorMessage = parsedValue.opcode + " argument " + String(i+1) + " was out of range: " + argSupplied;
			return{result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage};
		}
		// At this point, argMatch[1] is the value of the argument as returned by the RegExp
		// parse. For registers, which look like "r10", argMatch[1] contains 10. For an
		// immediate value, it is the value, which may have a 0x prefix.
	
		var immValue = 0;
		// If this is an immediate, we have to separate out the hex and decimal values and
		// parse them differently. If the parse fails, it's an error
		if (iField.isImm) {
			if (argMatch[1].slice(0,2) === "0x") {
				immValue = parseInt(argMatch[1].slice(2), 16);
				immWasHex = true;
			} 
			else {
				immValue = parseInt(argMatch[1], 10);
			}
			// Did parseInt return an invalid result?
			if (isNaN(immValue)) {
				errorMessage = parsedValue.opcode + " argument " + String(i+1) + " had an invalid immediate value: " + argSupplied;
				return { result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage };
			}
			// Jam the value back into argMatch[i] and let the code below handle combining it
			argMatch[1] = immValue;
		}
	
		// Finally, argMatch[i] has the value of the field. Mask it, shift it to the
		// right position in the instruction, and OR it in.
		instruction |= (argMatch[1] & iField.mask) << iField.startsAt;
  
	} // end: for (let i = 0; i < iValEntry.argCount; i++)
  
	return { result: true, instruction: instruction, immWasHex: immWasHex, brTarget: "", message: "" };
  
  
	// Loop over the arguments and validate them
  
} // end: function buildInstruction


// ============================================================================
// lineParse
//
// Parse a line into its constituent parts. Assumes that any comments have
// been removed and that blank lines (after comment removal) are skipped
//
// Arguments--
//
// line:      	Line to parse
//
// Returns--
//
//  Hash returning the information. If this value is undefined, there was a parse
//  error on the line. If it isn't undefined, it is a hash with the information.
//  Hash keys are:
//
// label:		Any label that was on the line. If this value is "", there was no
//            	label
//
// opcode:    	The opcode from the line (also includes .loc)
//
// argCount:  	Count of arguments on the line (0..3)
//
// args:      	Array (0..argCount-1) with the argument values
//
function lineParse(line) {
	let label, opcode, match
	// Trim any leading and trailing whitespace
	line.trim();

	// Check for a label
	const labelRegExp = /^(\w+)\:\s*(.+)$/;
	label = "";
	match = line.match(labelRegExp);
	if (match !== null) {
		label = match[1];
		line = match[2];
	}

	// Check for .loc
	const locRegExp = /^\.loc\s+(\d+)$/;
	match = line.match(locRegExp);
	if (match !== null) {
		opcode = ".loc";
		line = match[1];
	} 
	else {
		// If not .loc, then it must be an opcode with optional args
		const opcodeRegExp = /^(\w+)\s*(.*)$/;
		match = line.match(opcodeRegExp);
		if (match !== null) {
				opcode = match[1];
				line = match[2];
		}
		else {
			// If that doesn't match, then something went wrong
			AssemblerMessage("ERROR parsing opcode: " + line);
			return undefined;
		}
	}

	// Check for and extract the args
	line = line.replace(/\s+/g, "");
	let argCount = 0;
	let argSplit = [];
	if (line.length != 0) {
		argSplit = line.split(",")
		argCount = argSplit.length;
		if (argCount > 3) {
			AssemblerMessage("ERROR: Too many args on line: " + line);
			return undefined;
		}
	}

	// Return the parsed information
	return {
		label: label,
		opcode: opcode,
		argCount: argCount,
		args: argSplit,
	}
} // end: function lineParse


// ============================================================================
// printParsedLine
//
// Print the information returned from parseLine as an example of how to use
// the information
//
// Arguments--
//
// line:		the literal line, as passed in to the assembler (written in
//				assembly code)
//
// matchStr:	parsed line to print (this is the hash returned by lineParse)
//
function printParsedLine(line, matchStr) {
	if (matchStr === undefined) {
	  	AssemblerMessage("Parse error found on line " + line);
	  	return;
	}
	AssemblerMessage(`Line is \"${line}\"`);
	AssemblerMessage(`  label: \"${matchStr.label}\", opcode: \"${matchStr.opcode}\", argCount: ${matchStr.argCount}`)
	for (let i = 0; i < matchStr.argCount; i++) {
	  	AssemblerMessage(`    Argument ${i}: \"${matchStr.args[i]}\"`)
	}
} // end: function printParsedLine

