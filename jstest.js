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
