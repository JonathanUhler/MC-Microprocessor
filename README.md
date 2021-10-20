# MC-Assembler
A simple assembler for Minecraft microprocessors. Accompanied by MCSdis and MCSsim written in Swift, and the 8-bit "redstone" microprocessor


# Dependencies
JDK 17 or above - https://www.oracle.com/java/technologies/downloads/ \
javacli - https://github.com/JonathanUhler/Java-CLI (bundled automatically in MCasm.jar)


# Installation
*Note: MC-Assembler has currently only been testing on MacOS and likely only works on Unix-based machines* \
\
The MC-Assembler reposity can be cloned with the command line through ```git clone https://github.com/JonathanUhler/MC-Assembler.git``` \
Once cloned, the ```MCasm/``` folder under ```release/``` can be dragged to someplace in your ```$PATH``` and the assembler can be run from the command line with ```MCasm --help``` to get started.


# MCasm Command Line Usage
MC-asm is a command line app which uses the javacli library and follows standard POSIX conventions. \
To run the assembler, make sure it has been installed as outlined in Installation. Once installed and added to the ```$PATH```, the program be run with ```MCasm``` \
The command line arguments and options are:

> usage: MCasm [-Vvf] [-o FILE] [-t VALUE] [--help] INFILE 

## Options
| Option            | Usage
| ----------------- | ----------------------------------------------
| -V, --verbose     | Runs the assembler with verbose output
| -t, --timeout INT | Number of lines to be assembled before timeout
| -f, --force       | overwrites existing content in the output file
| -o --outfile TEXT | specify the location of an output file, if not specified, output is printed to the standard output
| -v, --version     | Print the version and exit
| --help            | Print help message and exit

## Arguments
| Argument | Usage
| -------- | -------------------------------------
| infile   | Input file with assembly instructions


# MC Assembly Language
## Opcodes and Commands
*See https://github.com/JonathanUhler/MC-Assembler/tree/main/documentation for more information*
\
### Instruction Format
| 24-20      | 19-16      | 15-12     | 11-8      | 7-0       |
| ---------- | ---------- |---------- |---------- |---------- |
| opcode     | rw         | ra        | rb        | aux       |

* opcode, bits[24:20] . . . operation code for the instruction
* rw, bits[19-16] . . . . . . . write register address
* ra, bits[15-21] . . . . . . . read A register address
* rb, bits[11-8] . . . . . . . . read B register address
* aux, bits[7, 0] . . . . . . . auxiliary register (for immediate values and label references)

### Opcode Table
* Columns -- bits[22:20]
* Rows -- bits[24:23]
* "I" suffix -- specifies that this instruction takes a value in for register ra and aux (an immediate value that the operation is completed with)

|     | 000  | 001  | 010  | 011  | 100  | 101  | 110  | 111
| --- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ----
| 00  | OR   | AND  |      | NOT  | CMP  |      | ADD  | SUB
| 01  | ORI  | ANDI |      | NOTI | CMPI |      | ADDI | SUBI
| 10  | BRZ  | BRO  | MF   |      |      |      |      |
| 11  | LD   | ST   |      |      |      |      |      | HALT


## Labels
### Syntax
```
<name>: <operation>
```
or
```
<name>:
<operation>
```
where
* name -- the name of the label, as defined by the programmer
* operation -- the first instruction that is called when a branch instuction references the label

## Directives
### Syntax
```
.<directive> [arguments]
```
where
* directive -- the name of the directive
* arguments -- any arguments the directive takes

### Directives Table
| Directive  | Usage        | Arguments
| ---------- | ------------ | ---------------------
| pragma     | disassembler | hex, dec, or default
| loc        | assembler    | pc value
| <label>    | disassembler | pc value

## Other Syntax Rules
### Comments
Comments are allowed by MC-Assembler. A comment is defined as a string beginning with "//" \
In parsing, if a comment is identified, it and the beginning "//" are removed and ignored

### Whitespace
All trailing and leading whitespace is ignored by the assembler. Tabs, spaces, or no whitespace can all be used to get the same result

### Multiline Statements
With the exception of labels (which can be on the same line as their first instruction), each instruction or directive **must** be on its own line

### Line Ending Tokens
Because each statement will be on its own line, there is no token used by MC-Assembler to denote the end of a line. \
If a token such as a semicolon (;) is used, it will be interpreted as part of an argument and may result in an assembly error


# Assembled Output Formatting
## Program Counter and Instructions
### Syntax
```
0x00: 0x0000000     // nop
```
where
* The first hexadecimal number (1 byte, 2 nibbles) is the program counter/line value
* The second hexadecimal number (7 nibbles) is the assembled instruction
* The comment (beginning with "//") is the instruction that is represented in the assembled machine code

## Labels
### Syntax
```
.<name>: <line>
```
where
* name -- the name of the label, as defined by the programmer
  * Notice: when assembled, labels are turned into directives that are used by the disassembler
* line -- the hexadecimal program counter value for which the start of the label routine can be found

## Directives
### Syntax
Directives that are used by the disassembler (such as pragma and labels) are syntactically the same as those written for use by the assembler (such as loc) \
See ```MC Assembly Language > Directives > Syntax``` for more information
