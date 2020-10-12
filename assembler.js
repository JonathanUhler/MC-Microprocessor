inComment = false
pc = 0
labelHash = {}
foreach line {

	// Look for /* */ comments
	if (line contains “/*”) {
	  if (line contains “*/" later in the same line) {
		delete all characters between /* and */, including those characters
	  } else {
		delete all characters from /* to the end of the line
		inComment = true
	 } else if (line contains “*/“) {
	  delete all characters from the beginning of the line to the //, including those characters
	  inComment = false
	}

	// Look for // comments
	if (line contains “//“) {
	  delete all characters from // to the end of line, including those characters
	}

	// Trim out leading and trailing whitespace
	delete all white space at the beginning of the line
	delete all white space at the end of the line

	// If the line is now empty, skip to the next line
	if (line.length == 0) { continue }

	// Here if there is a real line to process. A valid line looks like this:
	//
	// [label:] opcode arg1 [,arg2] [,arg3]
	label = arg1 = arg2 = arg3 = “”

	// Is there a label?
	if (line matches /^([A-Za-z0-1]+)\:\s*(.+)$/) {
	// That’s
	//	start of line -> ^
	//	A label made up of one or more sequences of letters or numbers - and save this -> ([A-Za-z0-1]+)
	//	A literal colon -> \:
	//	Zero or more whitespace characters -> \s*
	//	One or more other characters - and save that -> (.+)
	//	The end of line -> $
	  label = match[1];
	  if labelHash[label] already exists, it’s an error
	  labelHash[label] = pc; // Remember that the label refers to the current pc
	 line = match[2]	// Remove the label from the line and remember it
	}

	// Separate the remaining fields into opcode and arguments
	(opcode, arg1, arg2, arg3) = split(line, “\s”)
	
	// Assume not a directive
	isDirective = false
	// Break out into the different opcodes or directives
	switch (opcode) {
	  case “.loc”:		// .loc <value> sets the pc to this value
	    pc = arg1
	    isDirective = true;
	    break

	  case <the rest of the opcodes>:
	    // Process as a function of the opcode. Probably want to call a function to handle the different patterns and letting it check to see
	    // if the opcode has the right type and number of arguments. Then call another function from those to generate the binary for the
	    // instruction and put it into an array indexed by pc. Each of these probably wants to return a tuple with a pretty-printed string, the
	    // hex value of the instruction word and any possible branch target name. Note that this isn’t complete because we can’t resolve the
	    // branches at this point because they may be branching forward and we haven’t yet seen the label. Each of these cases also needs to
	   // increment pc by 1
	    break

	  default: Error
	}
} // foreach line

// Here when done processing the file. Have to clean up the branch targets for any branch instructions
foreach instruction {
	if instruction.isBranch {
	    let branchTarget = instruction.branchTarget
	    if labelHash[branchTarget] doesn’t exist, it’s an error
	    targetPC = labelHash[branchTarget]
	    fix up the pretty-print version and the instruction binary to include the branch target PC
	}
} // foreach instruction

// Dump out the instruction hex with the pretty-printed comment for each instruction