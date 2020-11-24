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
// help {-al|--run|--directory|--clear|--debug|--timeout}:    prints info about the commands
//
// run:							runs the program
//
// directory {-i|-o} {path}:	change the directory for the input or output file
//
// clear {-i|-o}:				clears one of the files specified by the "i/o" argument
//
// debug {on|off}:				toggles the debug messages
//
// timeout {value}:				updates the timeout value (how long the program can run
//								before automatically teminating)
//
// end: terminal commands
// ================================================================================================


// Version information
this.Version = commandsVersion
CommandsMessage("Commands v" + this.Version)


const validCommandsTable = {
    "help"      :   {regEx: /^$/, numArgs: 1, helpString: "help {--all|--run|--directory|--clear|--debug|--timeout}"},
    "run"       :   {regEx: , numArgs: 0, helpString: "run"},
    "directory" :   {regEx: , numArgs: 2, helpString: "directory {-i|-o} {path}"},
    "clear"     :   {regEx: , numArgs: 1, helpString: "clear {-i|-o}"},
    "debug"     :   {regEx: , numArgs: 1, helpString: "debug {--on|--off}"},
    "timeout"   :   {regEx: , numArgs: 1, helpString: "timeout {value}"},
}


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

// Prompt the user with "MCA %" (MinecraftAssembler % ) and get a command from them
readline.question(`MCA % `, (cmd) => {

    let cmdMsg = executeCommand(cmd)
    CommandsMessage(cmdMsg)

	readline.close()
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

    // // Declare the regular expression for a command
    // let cmdRegEx = /^(\w+)\s(.+)$/ // Matches any set of characters followed by a whitespace character and other sets of characters
    // let components = []
    // // Make sure the command matches the regular expression then split the command into its components
    // if (cmdRegEx.test(command)) {
    //     components = command.split(" ")
    // }
    // else {
    //     return("Fatal error: Invalid command syntax")
    // }
    
    // let cmd = components[0] // Get the command type out of the components list
    // let args = []

    // for (let i = 1; i < components.length; i++) {
    //     args.push(components[i]) // Get all the arguments for the command out of the components list
    // }

    switch (cmd) {
        case "help":
            CommandsMessage("help")
            break
        
        case "run":
            CommandsMessage("run")
            break
        
        case "directory":
            CommandsMessage("directory")
            break
            
        case "clear":
            CommandsMessage("clear")
            break
    
        case "debug":
            CommandsMessage("debug")
            break

        case "timeout":
            CommandsMessage("timeout")
            break

        default:
            CommandsMessage("Fatal error: Invalid syntax")
            break
    }

} // end: function executeCommand