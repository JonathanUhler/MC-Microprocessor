# MC-asm
An assembler program for Minecraft microprocessors. Accompanied by MCSdis, MCSsim, and the 8-bit redstone microprocessor


# Dependencies
javacli, https://github.com/JonathanUhler/Java-CLI


# Installation
MC-asm can be installed via

> git clone https://github.com/JonathanUhler/MC-asm.git

Then simply navigate to the working directory with the project and run it from the command line (see MCasm Usage).


# MCasm Usage
MC-asm is a command line app which uses the javacli library and follows standard POSIX conventions. \
To run the assembler, navigate to the ```src/``` directory and use ```./MCasm``` \
The command line arguments and options are:

> usage: MCasm [-Vvt] [--help] INFILE OUTFILE 

## Options
| Option            | Usage                                          |
| ----------------- | ---------------------------------------------- |
| -V, --verbose     | Runs the assembler with verbose output         | 
| -t, --timeout INT | Number of lines to be assembled before timeout |
| -v, --version     | Print the version and exit                     |
| -h, --help        | Print help message and exit                    |

## Arguments
| Argument | Usage                                 |
| -------- | ------------------------------------- |
| infile   | Input file with assembly instructions |
| outfile  | File to print out to                  |


# Input and Output File Formatting

