// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// MCasm.java
// MC-asm
//
// Created by Jonathan Uhler on 6/2/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
// USAGE: java MCasm [-Vhv] [-t <value>] <infile> <outfile>
//
// Options
//
// -V, --version:               prints the version of the program
//
// -h, --help:                  prints information about the commands
//
// -v, --verbose:               runs the assembler with verbose debug
//
// -t, --timeout value:         specified how many lines will be assembled before timeout occurs
//
// Arguments
//
// <infile>:                    required input/read file
//
// <outfile>:                   required output/write file
//
// end: terminal commands
// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-

// FIXME:
//  - Implement the .pragma imm {dec|hex|def} directive
//  - Should clear -o be the default? That is, why does the user have to specify this?
//  - What does clear -i do? It doesn't seem like you want to delete the input file
//  - It doesn't look like .loc works
//  - It doesn't look like the PC of a branch to a label is patching the instruction with the
//    pc value. See runningsum.asm for an example of this and the .loc problem


import java.util.Arrays;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class MCasm
//
// Main class of the project
//
public class MCasm {

    // ====================================================================================================
    public static void main(String[] args) {

    }

}
// end: public class MCasm