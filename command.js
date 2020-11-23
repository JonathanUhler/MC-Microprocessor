// commands.js
//
// A simple command-line command interface for assembler.js

const commandsVersion = "0.0.0"
// Revision History
//
//  version    date                     Change
// -------  ----------  -------------------------------------------------------
// 

// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
// MIT License
//
// Copyright (c) 2020 Jonathan Uhler and Mike Uhler
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

// ============================================================================
// terminal commands
//
// help [--verbose]:			prints info about the commands
//
// run:							runs the program
//
// directory {-i|-o} [path]:	change the directory for the input or output file
//								if the "path" argument is given or print the
//								current directory if no "path" is given
//
// clear {-i|-o}:				clears one of the files specified by the "i/o" argument
//
// debug {on|off}:				toggles the debug messages
//
// timeout {value}:				updates the timeout value (how long the program can run
//								before automatically teminating)
//
// end: terminal commands
// ============================================================================


// Version information
this.Version = commandsVersion
CommandsMessage("Commands v" + this.Version)

const commandTypeEnum = {
    help        :   "help",
    run         :   "run",
    directory   :   "directory",
    clear       :   "clear",
    debug       :   "debug",
    timeout     :   "timeout",
}

const commandDefinition = {
    pattern     :   String,
    type        :   commandTypeEnum,
    numArgs     :   Number,
    helpString  :   String,
}

const validCommands = [
    commandDefinition = {
        pattern     :   "help",
        type        :   commandTypeEnum.help,
        numArgs     :   0,
        helpString  :   "help",
    },
    commandDefinition = {
        pattern     :   "run",
        type        :   commandTypeEnum.run,
        numArgs     :   0,
        helpString  :   "run",
    },
]

CommandsMessage(validCommands)


// ============================================================================
// CommandsMessage
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
function CommandsMessage(msg, ...args) {

	const CommandsMessageEnable = true

    let message = "MC-CMSG:	" + msg
    if (args.length > 0) {
      	message += " " + args.join(", ")
    }

	if (CommandsMessageEnable) {
		console.log(message)
	}

} // end: function CommandsMessage



// Read the users input
const readline = require('readline').createInterface({
	input: process.stdin,
	output: process.stdout
})

// Prompt the user with "MCA %" and get a command from them
readline.question(`MCA % `, (cmd) => {

    

	readline.close()
})