// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// Assembler.java
// MC-Assembler
//
// Created by Jonathan Uhler on 6/5/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package assembler;


import assembler.commands.Command;
import assembler.fields.Register;
import helper.AssemblerHelper;
import main.MCasm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class Assembler
//
// Processes and assembles MC assembly code to MC machine code
//
public class Assembler {

    private ArrayList<String> linesToAssemble = new ArrayList<>(); // List of the lines to be assembled
    private int programCounter; // Program counter value


    // ----------------------------------------------------------------------------------------------------
    // public Assembler
    //
    // Constructor for Assembler class
    //
    // Arguments--
    //
    // inputFile:   the location of the file with lines to assemble
    //
    public Assembler(String inputFile) throws FileNotFoundException {
        // Create a file object from the inputFile string
        // If the file starts with "/" then it is an absolute path, otherwise append it to the user's working directory
        File input = (inputFile.startsWith("/")) ? new File(inputFile) : new File(System.getProperty("user.dir") + "/" + inputFile);

        // Read in all the lines of the input file
        Scanner readInputFile = new Scanner(input);
        while (readInputFile.hasNextLine()) {
            this.linesToAssemble.add(readInputFile.nextLine());
        }
    }
    // end: public Assembler


    // ====================================================================================================
    // private String assembleDirective
    //
    // Assemble a directive
    //
    // Arguments--
    //
    // lineToAssemble:  the line containing a directive to assemble
    //
    // Returns--
    //
    // The assembled line, if one exists
    //
    private String assembleDirective(String lineToAssemble) throws Exception {
        String directiveType = lineToAssemble.split(" ")[0].replace(".", ""); // Get the directive type

        // Switch through tht type of directive to parse it correctly
        switch (directiveType) {
            case "pragma": // If it is a pragma, just return the line without doing anything to it
                return lineToAssemble;
            case "loc": // Check if the directive is a loc
                // If the loc is decimal, then set the program counter to that value
                if (lineToAssemble.split(" ")[1].matches("[0-9]+")) this.programCounter = Integer.parseInt(lineToAssemble.split(" ")[1]);
                // Otherwise, if the loc is hex, then parse it to a decimal value and set the program counter to that
                else if (lineToAssemble.split(" ")[1].matches("0x[0-9a-fA-F]+")) this.programCounter = Integer.parseInt(lineToAssemble.split(" ")[1].substring(2), 16);
                break;
            default: // If the directive could not be identified, then throw an error
                AssemblerHelper.asmAssert(false,
                        "an unknown directive was specified",
                        "in directive " + directiveType);
                break;
        }

        return "";
    }
    // end: private String assembleDirective


    // ====================================================================================================
    // private String assembleLabel
    //
    // Assemble a label
    //
    // Arguments--
    //
    // lineToAssemble:  the line containing a label to assemble
    //
    // Returns--
    //
    // The assembled line
    //
    private String assembleLabel(String lineToAssemble) {
        String[] labelSplit = lineToAssemble.split(":"); // Split the line by the colon
        String labelName = labelSplit[0]; // Get the name of the label by indexing the value to the left of the colon
        String labelPC = Integer.toHexString(this.programCounter); // Get the hex representation of the current program counter value

        Register.addRegister(labelName, this.programCounter); // Add the label as a valid register that can be referenced

        return ".label " + labelName + " 0x" + labelPC; // Return the label as a directive to be parsed by the disassembler
    }
    // end: private String assembleLabel


    // ====================================================================================================
    // private String assembleLine
    //
    // Assemble a normal line
    //
    // Arguments--
    //
    // lineToAssemble:  the instruction to assemble
    //
    // Returns--
    //
    // The assembled line
    //
    private String assembleLine(String lineToAssemble) throws Exception {
        lineToAssemble = lineToAssemble.replaceAll(",", ""); // Remove all the separating commas from the instruction
        String[] lineSplit = lineToAssemble.split(" "); // Split the instruction by the remaining spaces to get the opcode and arguments

        String opcode = lineSplit[0]; // Get the opcode

        // Check that the opcode exists
        AssemblerHelper.asmAssert(Command.COMMANDS.containsKey(opcode),
                "an unknown opcode was specified",
                "in opcode " + opcode);

        Command command = Command.COMMANDS.get(opcode); // Get the command object that the opcode represents
        int numArgs = command.getNumArgs(); // Get the number of arguments

        // Check that the number of arguments is correct
        AssemblerHelper.asmAssert((numArgs == lineSplit.length - 1),
                "the wrong number of arguments were passed to an instruction",
                "in instruction " + opcode + ", expected " + numArgs + " args, but got " + (lineSplit.length - 1));

        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(lineSplit)); // Turn the arguments into an array list
        arguments.remove(0); // Remove the first element, which is the opcode and thus not an argument
        command.setRegisterValues(arguments); // Set the register values for the command

        return "0x" + Integer.toHexString(this.programCounter) + ": 0x" + command.packArguments(); // Return the parsed instruction
    }
    // end: private String assembleLine


    // ====================================================================================================
    // private ArrayList<String> cleanUpLines
    //
    // Cleans up a list of lines to prepare them for assembly
    //
    // Arguments--
    //
    // linesToClean:    the list of lines to clean up
    //
    // Returns--
    //
    // The list of cleaned lines
    private ArrayList<String> cleanUpLines(ArrayList<String> linesToClean) {
        ArrayList<String> cleanedLines = new ArrayList<>(); // Initialize a list to hold the cleaned lines

        for (String lineToClean : linesToClean) {
            // Match the comment starting string "//" and 0 or more of any character
            lineToClean = lineToClean.replaceAll("//.*", ""); // Remove any comments and their text
            lineToClean = lineToClean.trim(); // Remove any leading or trailing whitespace
            lineToClean = lineToClean.replaceAll(",", ", "); // Make sure there is whitespace after each comma by replacing commas with comma + space
            lineToClean = lineToClean.replaceAll(" +", " "); // Replace all whitespace with just 1 space

            // Check if the line is a label with its first instruction on the same line
            // Ex: "label: nop"
            if (lineToClean.contains(":") && lineToClean.split(":").length > 1) {
                cleanedLines.add(lineToClean.split(":")[0] + ":"); // Add the label name back with its colon
                cleanedLines.add(lineToClean.split(":")[1].trim()); // Add the instruction as a separate entry so everything is on a different line
                continue;
            }

            // Check if the line is something other than just whitespace and add it to the cleanedLines array
            if (lineToClean.matches(".+")) cleanedLines.add(lineToClean);
        }

        return cleanedLines; // Return the array of cleaned lines
    }
    // end: private ArrayList<String> cleanUpLines


    // ====================================================================================================
    // public ArrayList<String> assemble
    //
    // Assemble lines
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // The list of assembled lines
    //
    public ArrayList<String> assemble() throws Exception {
        ArrayList<String> assembledLines = new ArrayList<>(); // Initialize a list to hold the assembled lines

        assembledLines.add("// Assembled by MC-Assembler version " + MCasm.MCasmVersion); // Add a comment branding the lines as MC-Assembler with the version for debug
        long processStartMS = new Date().getTime(); // Save the millisecond value of when the assembly started to be used later on

        this.programCounter = 0x0; // Set the program counter to 0
        this.linesToAssemble = this.cleanUpLines(this.linesToAssemble); // Clean up the input lines by removing whitespace and comments

        // First, loop through all the lines once for the labels
        for (String lineToAssemble : this.linesToAssemble) {
            // If the line is a label, parse and add it
            if (lineToAssemble.matches(".+:.*")) assembledLines.add(this.assembleLabel(lineToAssemble) + "\t\t\t\t\t// " + lineToAssemble);
            // If the line is a directive, parse it, but don't add anything
            // This won't add directives for the disassembler (like pragma) but will parse .loc which effects the parsing of labels
            else if (lineToAssemble.startsWith(".")) this.assembleDirective(lineToAssemble);
            // Otherwise, just increment the program counter
            else this.programCounter++;
        }

        this.programCounter = 0x0; // Reset the program counter

        // Loop through everything again to parse instructions and other directive
        for (String lineToAssemble : this.linesToAssemble) {
            // Parse directives if they are found
            if (lineToAssemble.startsWith(".")) {
                String assembledDirective = this.assembleDirective(lineToAssemble);
                if (!assembledDirective.equals("")) assembledLines.add(assembledDirective + "\t\t\t\t\t// " + lineToAssemble);
            }
            // Parse everything other than directives and labels (eg instructions)
            else if (!lineToAssemble.matches(".+:.*")) {
                assembledLines.add(this.assembleLine(lineToAssemble) + "\t\t\t\t\t// " + lineToAssemble); // Add the assembled line
                this.programCounter++; // Increment the program counter
            }
        }

        // Specify the length of the process in a second comment as the top
        assembledLines.add(1, "// " + new Date() + ", process took " + (new Date().getTime() - processStartMS) + "ms");

        return assembledLines; // Return all the lines
    }
    // end: private ArrayList<String> assemble

}
// end: public class Assembler