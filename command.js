// commands.js
//
// A simple command-line command interface for assembler.js

const commandsVersion = "0.0.0"
// Revision History
//
//  version    date                     Change
// -------  ----------  -------------------------------------------------------
// 

// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
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
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

// ================================================================================================
// terminal commands
//
// help {--all|--run|--directory|--clear|--debug|--timeout}:    prints info about the commands
//
// run:							runs the program
//
// directory {-i|-o} {path}:	change the directory for the input or output file
//
// clear {-i|-o}:				clears one of the files specified by the "i/o" argument
//
// debug {--on|--off}:				toggles the debug messages
//
// timeout {value}:				updates the timeout value (how long the program can run
//								before automatically teminating)
//
// quit:                        terminate the program
//
// end: terminal commands
// ================================================================================================


// Version information
this.Version = commandsVersion
CommandsMessage("Commands v" + this.Version)

// Import data from assembler.js
var importedData = require('./assembler.js')


const validCommandsTable = {
    "help"      :   {numArgs: 1, helpString: "help {--all|--run|--directory|--clear|--debug|--timeout}"},
    "run"       :   {numArgs: 0, helpString: "run"},
    "directory" :   {numArgs: 2, helpString: "directory {-i|-o} {path}"},
    "clear"     :   {numArgs: 1, helpString: "clear {-i|-o}"},
    "debug"     :   {numArgs: 1, helpString: "debug {--on|--off}"},
    "timeout"   :   {numArgs: 1, helpString: "timeout {value}"},
    "quit"      :   {numArgs: 0, helpString: "quit"}
}

var exitCode = 0


// ================================================================================================
// fuction CommandsMessage
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

    // Minecraft-CommandsMessage
    var message = msg
    if (args.length > 0) {
      	message += " " + args.join(", ")
    }

	if (CommandsMessageEnable) {
		console.log(message)
	}

} // end: function CommandsMessage


// Prompt the user with "MCA %" (MinecraftAssembler % ) and get a command from them

// Exit codes:
//
// 0:       success
// 126:     invoked command cannot execute
// 127:     invoked command does not exist
// 128:     invoked command had invalid arguments
// 130:     script terminated with "quit" or ctrl + d

const readline = require('readline');
const rl = readline.createInterface({ // Setup the input and output streams and the user prompt
  input: process.stdin,
  output: process.stdout,
  prompt: 'MCA % '
});

// Prompt the user for input
rl.prompt();

// Process input when a new line is given to console
rl.on('line', (cmd) => {

    exitCode = executeCommand(cmd)
    CommandsMessage(`exit code: ${exitCode}`)
    if (exitCode != 0 && exitCode != 130) {
        printHelp()
    }

    if (exitCode == 130) {
        process.exit(130)
    }

    // Prompt the user again
    rl.prompt();
}).on('close', () => {
    console.log('Have a great day!');
    process.exit(0);
})


// ================================================================================================
// function executeCommand
//
// A function that parses and executes commands given by the user
//
// Arguments--
//
// command:     the user-inputted command
//
// Returns--
//
// None
//
function executeCommand(command) {

    // Split the individual components of the command
    var components = []
    components = command.split(" ")

    var cmd = components[0] // Get the command type out of the components list
    var args = []
    for (var i = 1; i < components.length; i++) {
        args.push(components[i]) // Get all the arguments for the command out of the components list
    } // end: for

    // Make sure the command is valid
    if (validCommandsTable[cmd] === undefined) { // Check to make sure the command exists
        return(127)
    } // end: if

    if (args.length != validCommandsTable[cmd].numArgs) { // Check for the correct number of arguments
        return(128)
    } // end: if

    // Execute the command if it is valid
    switch (cmd) {

        case "help":
            return(0)
        
        case "directory": // update the input or output file path

            if (args[0].toString() === "-i") {
                var updateDirI = importedData.setterGetter("input", args[1].toString())
            }
            else if (args[0].toString() === "-o") {
                var updateDirO = importedData.setterGetter("output", args[1].toString())
            }
            else {
                return(128)
            }

            return(0)
            
        case "clear":
            return(0)
    
        case "debug":
            if (args[0].toString() === "--on") {
                var updateDebug = importedData.setterGetter("debug", true)
            }
            else if (args[0].toString() === "--off") {
                var updateDebug = importedData.setterGetter("debug", false)
            }
            else {
                return(128)
            }

            return(0)

        case "timeout": // update the max number of lines that can be executed

            var updateTimeout = importedData.setterGetter("timeout", parseInt(args[0]))
            return(0)

        case "run": // run the assembler
            var runAssembler = importedData.init()
            return(0)

        case "quit": // quit the process
            return(130)
    }

} // end: function executeCommand


function printHelp() {

    CommandsMessage("Valid commands are:")

    // Print all the valid commands
    for (var i in validCommandsTable) {
        
        CommandsMessage("    " + validCommandsTable[i].helpString)

    } // end: for

}