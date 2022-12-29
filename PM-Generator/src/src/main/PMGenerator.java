// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// PMGenerator.java
// PM-Generator
//
// Created by Jonathan Uhler on 11/13/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package main;


import javacli.annotations.Version;
import javacli.annotations.Option;
import javacli.annotations.Argument;
import javacli.OptionParser;
import helper.NBTHelper;
import helper.PMHelper;
import generate.Generator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class PMGenerator
//
// Main class, responsible for handling the generator as a command line tool
//
public class PMGenerator {

    // Define options and arguments for the command line functionality
    public static final String PMGeneratorVersion = "1.0.0";
    @Version(version = PMGeneratorVersion, abbreviation = 'v') public static String version;

    @Argument(name = "infile") public static String infile;

    @Option(name = "verbose", abbreviation = 'V', help = "generate with verbose output", type = boolean.class, isFlag = true) public static boolean verbose;
    @Option(name = "outfile", abbreviation = 'o', help = "specify a file to write out to", nargs = 1) public static String outfile;
    @Option(name = "unpack", abbreviation = 'u', help = "write unpacked data instead of packed bytes", type = boolean.class, isFlag = true) public static boolean unpack;
    @Option(name = "width", abbreviation = 'w', help = "width in bits of instructions", nargs = 1, type = int.class, defaultValue = "25", showDefault = true) public static int width;
    @Option(name = "length", abbreviation = 'l', help = "depth in lines of the program memory", nargs = 1, type = int.class, defaultValue = "63", showDefault = true) public static int length;


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
        // Parse command line arguments
        OptionParser optionParser = new OptionParser(PMGenerator.class);
        optionParser.parse(args);


        // Get input data
        String input = PMGenerator.infile;
        File inputFile = (input.startsWith("/")) ? new File(input) : new File(System.getProperty("user.dir") + "/" + input);
        ArrayList<String> inputData = new ArrayList<>();
        // Read all the input data into an arraylist
        Scanner readInputFile = new Scanner(inputFile);
        while (readInputFile.hasNextLine()) inputData.add(readInputFile.nextLine());


        // Generate program memory NBT
        Generator generator = new Generator(PMHelper.removeLabels(inputData), width, length);
        String generatedProgramMem = generator.generate(PMGenerator.unpack);


        // Print output data if no output file is specified
        if (PMGenerator.outfile == null) {
            System.out.println(generatedProgramMem);
        }
        else {
            // Write data to an output file if specified
            File outputFile = (outfile.startsWith("/")) ? new File(outfile) : new File(System.getProperty("user.dir") + "/" + outfile);
            if (unpack) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
                writer.write(generatedProgramMem);
                writer.close();
            }
            else {
                try (FileOutputStream o = new FileOutputStream(outputFile)) { o.write(NBTHelper.hexStringToByteArray(generatedProgramMem)); }
            }
        }
    }
    // end: public static void main

}
// end: public class PMGenerator