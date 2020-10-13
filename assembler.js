// assembler.js
//
// A simple assembler for the Minecraft Computer. Takes a text file written in
// the assembly language and outputs an array of 0's and 1's representing where
// redstone torches must go in the program memory

"use strict";
const AssemblerVersion = "2.0.0";

// Revision History
//
//  version    date                     Change
//  ------- ----------  -------------------------------------------------------
//	1.0.0	10/12/2020	Changes in this version:
//							-AssemblerMessage function to organize and handle
//							console outputs
//							-replaceStringInData function that takes a file,
//							target string, and a value to replace the target
//							with and replaces all instances of the target with
//							the correct value (as well as removing blank space
//							before or after the final string)
//
//	2.0.0	10/13/2020	Changes in this version:
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

    let message = "Assembler Message: " + msg;
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
//
// end: GLOBAL VARIABLES


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

		// Save each of the appended lines to a new array
		returnData.push(fileLine);

		// Print the line to console
		AssemblerMessage("Data: \n" + fileLine);
	}

	// Return the edited array of lines
	AssemblerMessage("Return value:", returnData);
	return returnData;
	
} // end: function replaceStringInData


// Take the edited lines from replaceStringInData and execute them
let lines = replaceStringInData('/Users/jonathan/Documents/VS Code/Assembler/input.txt', /\/\/.*$/, "")

for (let i = 0; i < lines.length; i++) {

	// Display and use the contents and length of each line
	// Message should look like this 'Line: x has length: y, and states: "z"'
	AssemblerMessage("Line: " + i, "has length: " + lines[i].length, "and states: \"" + lines[i] + "\"");
	executeLine(lines[i]);

}


// ============================================================================
// executeLine
//
// Takes in the contents of a single line of assembly code from the data parsed
// in replaceStringInData, checks for its validity, and executes it.
//
// Arguments--
//
// lineContents:		The contents of a single line of assembly code
//
// Returns--
//
// None
//
function executeLine(lineContents) {
	if (lineContents.length === 0) { // Skip empty lines
		return;
	}
	else { // Parse and execute the assembly code on valid lines

	}


	// // Here if there is a real line to process. A valid line looks like this:
	// //
	// // [label:] opcode arg1 [,arg2] [,arg3]
	// let label = arg1 = arg2 = arg3 = "";

	// // Is there a label?
	// if (line matches /^([A-Za-z0-1]+)\:\s*(.+)$/) {
	// // That’s
	// //	start of line -> ^
	// //	A label made up of one or more sequences of letters or numbers - and save this -> ([A-Za-z0-1]+)
	// //	A literal colon -> \:
	// //	Zero or more whitespace characters -> \s*
	// //	One or more other characters - and save that -> (.+)
	// //	The end of line -> $
	// 	label = match[1];
			
	// 	if (labelHash[label] already exists, it’s an error) {
	// 		labelHash[label] = pc; // Remember that the label refers to the current pc
	// 		line = match[2]	// Remove the label from the line and remember it
	// 	}
	// }

	// // Separate the remaining fields into opcode and arguments
	// (opcode, arg1, arg2, arg3) = split(line, "\s")

	// // Assume not a directive
	// let isDirective = false;
	// // Break out into the different opcodes or directives
	// switch (opcode) {
	// 	case ".loc": // .loc <value> sets the pc to this value
	// 		pc = arg1;
	// 		isDirective = true;
	// 		break;
	// 	case true: // REPLACE THIS LATER: true put as a placeholder to fix syntax errors, replace with: <the rest of the opcodes> 
	// 		// Process as a function of the opcode. Probably want to call a function to handle the different patterns and letting it check to see
	// 		// if the opcode has the right type and number of arguments. Then call another function from those to generate the binary for the
	// 		// instruction and put it into an array indexed by pc. Each of these probably wants to return a tuple with a pretty-printed string, the
	// 		// hex value of the instruction word and any possible branch target name. Note that this isn’t complete because we can’t resolve the
	// 		// branches at this point because they may be branching forward and we haven’t yet seen the label. Each of these cases also needs to
	// 		// increment pc by 1
	// 		break;
	// 	default: Error
	// } // end: switch (opcode)
} // end: function executeLine




// // Here when done processing the file. Have to clean up the branch targets for any branch instructions
// function searchTextForInstruction() {

// 	if (instruction.isBranch) {
// 	    let branchTarget = instruction.branchTarget;
// 	    if (labelHash[branchTarget] doesn’t exist) {
// 			AssemblerMessage("ERROR:", labelHash[branchTarget], "doesn't exist!")
// 		}
// 	    targetPC = labelHash[branchTarget];
// 	    // fix up the pretty-print version and the instruction binary to include the branch target PC
// 	}
	
// } // end: foreach instruction

// // Dump out the instruction hex with the pretty-printed comment for each instructionxs