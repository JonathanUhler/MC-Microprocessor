// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// MCasm.java
// MC-Assembler
//
// Created by Jonathan Uhler on 6/2/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
// USAGE: java MCasm [-Vhv] [-t <value>] <infile> <outfile>
//
// Options
//
// -v, --version:       prints the version of the program
//
// --help:              prints information about the commands
//
// -V, --verbose:       runs the assembler with verbose debug
//
// -t, --timeout value: specified how many lines will be assembled before timeout occurs
//
// Arguments
//
// <infile>:            required input/read file
//
// <outfile>:           required output/write file
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


package main;


import javacli.annotations.Option;
import javacli.annotations.Argument;
import javacli.annotations.Version;
import javacli.OptionParser;
import assembler.Assembler;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class MCasm
//
// Main class of the project
//
public class MCasm {

    public static final String MCasmVersion = "1.0.0";

    @Option(name = "verbose", abbreviation = 'V', help = "runs the assembler with verbose output", isFlag = true, type = boolean.class) public static boolean verbose;
    @Option(name = "timeout", abbreviation = 't', help = "number of lines to be assembled before timeout", type = int.class, nargs = 1, defaultValue = "500", showDefault = true) public static int timeout;
    @Argument(name = "infile") public static String infile;
    @Argument(name = "outfile") public static String outfile;
    @Version(version = MCasmVersion, abbreviation = 'v') public static String version;


    // ====================================================================================================
    public static void main(String[] args) throws Exception {

        OptionParser optionParser = new OptionParser(MCasm.class);
        optionParser.parse(args);

        Assembler a = new Assembler();

        System.out.println("verbose: " + verbose);
        System.out.println("timeout: " + timeout);
        System.out.println("infile: " + infile);
        System.out.println("outfile: " + outfile);

    }

}
// end: public class MCasm