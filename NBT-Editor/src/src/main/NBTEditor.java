// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// NBTEditor.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package main;


import helper.NBTHelper;
import javacli.OptionParser;
import javacli.annotations.Command;
import javacli.annotations.Version;
import pack.PackParser;
import unpack.UnpackParser;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class NBTEditor
//
// Main class of the program
//
public class NBTEditor {

    public static final String NBTEditorVersion = "1.0.0"; // App version

    // Define commands and sub-commands
    @Command(name = "NBTEditor", help = "main command for NBTEditor (usage: NBTEditor)") public static String NBTEditor;
    @Command(name = "pack", help = "pack NBT data to hex bytes (usage: NBTEditor pack)") public static String pack;
    @Command(name = "unpack", help = "unpack hex data to NBT (usage: NBTEditor unpack)") public static String unpack;

    // Set version command
    @Version(version = NBTEditorVersion, abbreviation = 'v') public static String version;


    // ====================================================================================================
    // private static void pack
    //
    // Handles the pack sub-command
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // None
    //
    private static void pack() throws Exception {
        String inputFile = main.pack.infile; // Get the input file for the pack command
        File input = (inputFile.startsWith("/")) ? new File(inputFile) : new File(System.getProperty("user.dir") + "/" + inputFile); // Turn the input file into a file object

        StringBuilder inputData = new StringBuilder(); // Create a string to append each line of the input file to

        // Read the data from the input file
        Scanner readInputFile = new Scanner(input);
        while (readInputFile.hasNextLine()) inputData.append(readInputFile.nextLine()).append("\n");

        // Pack the data
        PackParser packParser = new PackParser(inputData.toString());
        String packedData = packParser.pack();

        // If no output file was specified, print the data to stdout
        if (main.pack.outfile == null) {
            System.out.println(packedData);
            return;
        }

        // If an output file was specified, write the data out to that
        File outFile = new File(main.pack.outfile);
        try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
            outputStream.write(NBTHelper.hexStringToByteArray(packedData));
        }
    }
    // end: private static void pack


    private static void unpack() throws Exception {
        String inputFile = main.unpack.infile; // Get the input file for the unpack command
        File input = (inputFile.startsWith("/")) ? new File(inputFile) : new File(System.getProperty("user.dir") + "/" + inputFile); // Turn the input file into a file object

        // Read the data from the input file
        byte[] inputData = Files.readAllBytes(Paths.get(input.getPath()));

        // Unpack the data
        UnpackParser unpackParser = new UnpackParser(inputData);
        String unpackedData = unpackParser.unpack();

        System.out.println(unpackedData);
    }


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
        // Parse command line options
        OptionParser optionParser = new OptionParser(new ArrayList<>(Arrays.asList(main.NBTEditor.class, main.pack.class, main.unpack.class)));
        optionParser.parse(args);

        // Pack or unpack if specified
        if (main.pack.infile != null) pack();
        if (main.unpack.infile != null) unpack();
    }
    // end: public static void main

}
// end: public class NBTEditor