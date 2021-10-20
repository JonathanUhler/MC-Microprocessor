// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// MCasm.java
// MC-Assembler
//
// Created by Jonathan Uhler on 6/2/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package main;


import javacli.annotations.Option;
import javacli.annotations.Argument;
import javacli.annotations.Version;
import javacli.OptionParser;
import assembler.Assembler;
import java.io.FileWriter;
import java.util.ArrayList;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class MCasm
//
// Main class of the project
//
public class MCasm {

    public static final String MCasmVersion = "1.0.0";

    // Define arguments and options
    @Option(name = "verbose", abbreviation = 'V', help = "runs the assembler with verbose output", isFlag = true, type = boolean.class) public static boolean verbose;
    @Option(name = "timeout", abbreviation = 't', help = "number of lines to be assembled before timeout", type = int.class, nargs = 1, defaultValue = "500", showDefault = true) public static int timeout;
    @Option(name = "force", abbreviation = 'f', help = "overwrites existing contents in the output file", isFlag = true, type = boolean.class) public static boolean force;
    @Option(name = "outfile", abbreviation = 'o', help = "specify the location of an output file", nargs = 1) public static String outfile;
    @Argument(name = "infile") public static String infile;
    @Version(version = MCasmVersion, abbreviation = 'v') public static String version;


    // ====================================================================================================
    // public static void main
    //
    // Main method
    //
    // Arguments--
    //
    // args:    list of command line arguments
    //
    // Returns--
    //
    // None
    //
    public static void main(String[] args) throws Exception {
        OptionParser optionParser = new OptionParser(MCasm.class); // Create a new option parser to parse command line args
        optionParser.parse(args); // Parse the arguments

        Assembler assembler = new Assembler(infile); // Create a new assembler to parse the data
        ArrayList<String> assembledLines = assembler.assemble(); // Assemble the lines

        // If the output file is not specified, then print out each line to the console
        if (outfile == null) {
            for (String line : assembledLines) System.out.println(line);
            return;
        }

        // If the output file is specified, write out each line
        FileWriter outWriter = new FileWriter(outfile, !force);
        for (String line : assembledLines) outWriter.write(line + "\n");
        outWriter.close();
    }
    // end: public static void main

}
// end: public class MCasm