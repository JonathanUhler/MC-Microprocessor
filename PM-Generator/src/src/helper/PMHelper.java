// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// PMHelper.java
// PM-Generator
//
// Created by Jonathan Uhler on 11/13/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package helper;


import java.util.ArrayList;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class PMHelper
//
// Class with helper functions
public class PMHelper {

    // ====================================================================================================
    // public static void pmAssert
    //
    // Check for the truth of a condition and throw an error if it is false
    //
    // Arguments--
    //
    // assertion:       the condition to check
    //
    // failureMessage:  the message to print if the assertion fails
    //
    // extraArgs:       any extra arguments to print
    //
    // Returns--
    //
    // None
    //
    public static void pmAssert(boolean assertion, String failureMessage, String... extraArgs) throws Exception {
        if (!assertion) { // Check if the assertion is true or false
            // Create an error message
            String err = "ERROR: PMGenerator Assertion Failed; " + failureMessage + ((extraArgs.length > 0) ? " -\n\t" + String.join("\n\t", extraArgs) : "");

            // Print the message and throw an error
            System.out.println(err);
            throw new Exception("PMGenerator Assertion Failed: " + failureMessage);
        }
    }
    // end: public static void pmAssert


    // ====================================================================================================
    // public static ArrayList<String> removeLabels
    //
    // Remove everything that is not an instruction from a list of assembled lines
    //
    // Arguments--
    //
    // assembledLines:  list of lines to clean up
    //
    // Returns--
    //
    // Cleaned up lines
    //
    public static ArrayList<String> removeLabels(ArrayList<String> assembledLines) {
        // Initialize a new list to store the cleaned instructions
        ArrayList<String> instructions = new ArrayList<>();

        // Clean each line
        for (String line : assembledLines) {
            // Remove comments and whitespace
            line = line.replaceAll("//.*", "");
            line = line.trim();

            // Labels and blank lines
            if (line.startsWith(".") || line.equals("")) continue;

            // Add the remaining lines, removing the hex identifier at the start
            instructions.add(line.replace("0x", ""));
        }

        // Return the instructions
        return instructions;
    }
    // end: public static ArrayList<String> removeLabels


    public static String padString(int padThickness, String data) {
        // Format a string with spaces to a given thickness and then replace the spaces
        return String.format("%1$" + padThickness + "s", data).replace(' ', '0');
    }

}
