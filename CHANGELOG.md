# Minecraft-Assembler Changelog

Revision History:

	version    date                     Change
	-------  ----------  -------------------------------------------------------
	1.0.0	10/12/2020	Changes in this version:
							-AssemblerMessage function to organize and handle console outputs
							-replaceStringInData function that takes a file, target string, and a value to replace the target with and replaces all instances of the target with the correct value (as well as removing blank space before or after the final string)

	2.0.0	10/13/2020	Changes in this version:
							-Added the AssemblerMessageEnable constant to allow console messages to be enabled/disabled
							-Added a for-loop and the executeLine function to take the return value from replaceStringInData, slip up each line, and eventually execute the valid assembly code
							-Replaced the use of fs.fileRead in replaceStringInData with fs.fileReadSync to read the file synchronously
								-Removed the err argument to fs.fileReadSync
								-Moved the value of fs.fileReadSync to the data variable which is used later in the replaceStringInData
	3.0.0	10/21/2020	Changes in this version:
							-Added constants for the input and output directories at the top of the program for easy editing
							-Added instructionFields and instructionValidationTable hashes for building the intructions
							-Added function writeFileToData, called from within replaceStringInData which writes the hexadecimal version of the instructions to an output file
							-Added function buildInstruction to take the instruction in assembly language, check for a valid opcode and arguments, and convernt it to hex for output

	3.0.1	10/21/2020	Changes in this version:
							-Fixed a bug with hex output

	3.1.0	10/21/2020	Changes in this version:
							-Automatically set the intructions to all lowercase when parsing
							-Added in the rest of the opcode map

	3.2.0	10/22/2020	Changes in this version:
							-Fixed bugs with the opcode hash
							-Reordered the opcode hash to make more sense
							-Other minor functional changes

	3.3.0	11/22/2020	Chanes in this version:
							-Added the printAndParseLabels function to handle labels
							-Print labels at the top of the output

	3.3.1	11/23/2020	Add license

	4.0.0	11/24/2020	Changes in this version:
							-Replace "let" with "var"
							-Create the "init" function and move some variable declaractions into it
								-This function runs the entire assembler program
							-Create the "setterGetter" function to handle variables
							-Export some important data to be used in the command.js file
							-Add commands.js file to handle user commands

	4.1.0	11/25/2020	Changes in this version:
							-Save and load basic user config data
							-Implement more commands
							-Add config.json to handle config data
							-Minor bug fixes

	4.1.1	12/12/2020	Changes in this verstion:
							-Added an option to save the maxTimeoutValue when quitting the program

	4.2.0 12/12/2020  Changes in this version:
							-Use __dirname to find the config file so that the Assembler
							 can be run in a directory different from where the .js and
							 config files live
							-Allow the input and output files to be specified on the command line
