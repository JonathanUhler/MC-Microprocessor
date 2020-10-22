// Instruction field descriptors
//
// Each field is indexed by the name of the field and is a hash that describes
// the type of field and the allowable values. Keys to that hash are:
//
//  isOpcode    True if the field is an opcode
//
//  isLabel     True if the field is a branch label
//
//  startsAt    Right-most bit of the field. This is also the amount to left
//              shift a right-justified value to position it to the field.
//
//  mask        The mask with which to AND a right-justified value to ensure
//              that it fits in the field
//
//  pattern     The RegEx pattern to use to match a valid value in that field
//
//  minVal      The minimum allowable value for the field
//
//  maxVal      The maximum allowable value for the field

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
}

// Instruction Validation table

const instructionValidationTable = {
  "ori" : {opcode: 0x8, argCount: 3, args:["rw","ra","imm"]},
  "brz" : {opcode: 0x8, argCount: 2, args:["rb","label"]}
}

// buildInstruction
//
// Take the results of lineParse and build an instruction from the text.
//
// Arguments:
//  parsedValue   The return value from parseLine
//
// Returns:
//  A hash with the results of the attempt to build the instruction, with the
//  following keys:
//
//  result        (Boolean) The result of the function. If true, the value
//                of the instruction is in the instruction key. If false, something
//                went wrong, and the error message is in the message key
//
//  instruction   (Integer) The value of the assembled instruction
//
//  brTarget      (String) If the instruction is a branch, this is the
//                symbolic branch target. "" if not a branch
//
//  immWasHex     (Boolean) True if the immediate field was a hex value
//
//  message       (String) The error message if the assembly failed


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
      return { result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage };
    }

    // If this field is a branch target, we're done
    if (iField.isLabel) {
      return { result: true, instruction: instruction, immWasHex: false, brTarget: argSupplied, message: "" };
    }

    // For anything else, make sure that the value is in range
    if (
      argMatch[1] < iField.minVal ||
      argMatch[1] > iField.maxVal
    ) {
      errorMessage = parsedValue.opcode + " argument " + String(i+1) + " was out of range: " + argSupplied;
      return { result: false, instruction: 0, brTarget: "", immWasHex: false, message: errorMessage };
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
      } else {
        immValue = parseInt(argMatch[1], 10)
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

  } // for (let i = 0; i < iValEntry.argCount; i++)

  return { result: true, instruction: instruction, immWasHex: immWasHex, brTarget: "", message: "" };


  // Loop over the arguments and validate them

} // function buildInstruction(parsedValue)





// lineParse
//
// Parse a line into its constituent parts. Assumes that any comments have
// been removed and that blank lines (after comment removal) are skipped
//
// Arguments:
//  line      Line to parse
//
// Return value:
//  Hash returning the information. If this value is undefined, there was a parse
//  error on the line. If it isn't undefined, it is a hash with the information.
//  Hash keys are:
//
//  label     Any label that was on the line. If this value is "", there was no
//            label
//
//  opcode    The opcode from the line (also includes .loc)
//
//  argCount  Count of arguments on the line (0..3)
//
//  args      Array (0..argCount-1) with the argument values

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
  } else {
    // If not .loc, then it must be an opcode with optional args
    const opcodeRegExp = /^(\w+)\s*(.*)$/;
    match = line.match(opcodeRegExp);
    if (match !== null) {
      opcode = match[1];
      line = match[2];
    } else {
      // If that doesn't match, then something went wrong
      console.log ("ERROR parsing opcode: " + line);
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
      console.log ("ERROR: Too many args on line: " + line);
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
}

  let line = "ori r10, r11, 0xc"
  let matchStr = lineParse(line);
  let buildResult = buildInstruction(matchStr);
  if (!buildResult.result) {
    console.log (`Unable to build instruction for ${line}: ${buildResult.message}`)
  } else {
    console.log (`Assembled instruction for ${line} was 0x${buildResult.instruction.toString(16)}, with branch target \"${buildResult.brTarget}\"; immediate was hex: ${buildResult.immWasHex}`)
  }


/*
// Print the information returned from parseLine as an example of how to use the information
function printParsedLine(line, matchStr) {
  if (matchStr === undefined) {
    console.log ("Parse error found on line " + line);
    return;
  }
  console.log (`Line is \"${line}\"`);
  console.log (`  label: \"${matchStr.label}\", opcode: \"${matchStr.opcode}\", argCount: ${matchStr.argCount}`)
  for (let i = 0; i < matchStr.argCount; i++) {
    console.log (`    Argument ${i}: \"${matchStr.args[i]}\"`)
  }
}

// Define some test lines
let lines = [];
lines.push("label: or r4, r5, r6");
lines.push("label: ori r1, r2, 100");
lines.push("label: bogusop r3, r4");
lines.push("label: nop");
lines.push("or r4, r5, r6");
lines.push("ori r1, r2, 100");
lines.push("bogusop r3, r4");
lines.push("nop");
lines.push(".loc 10");
lines.push("bogus arg1, arg2, arg3, arg4")

for (let i = 0; i < lines.length; i++) {
  let line = lines[i];
  let matchStr = lineParse(line);
  printParsedLine(line, matchStr);
}
*/
