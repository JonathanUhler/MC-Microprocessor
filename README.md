# Minecraft-Assembler

A simple assembler program with terminal commands


# Dependencies

nodejs - https://nodejs.org/en/download/


# asm.js Usage

To run the assembler, navigate to the downloaded folder of the repository and use "node asm.js" This will open the command processor from which you can type "help --all" to see command help for all commands. In order to run the assembler, there must be assembly code in the input.asm file.

The input and output files can be specified on the command line with:

node asm.js [input-file] [output-file]


# assembler.js Usage

To assemble actual code, run asm.js with node and use the "run" command. If debug is enabled, you should see basic debug messages. From there, the assembled code should be printed to output.bin or to the file specified on the command line, or via the directory -o command
