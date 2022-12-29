// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// PackParser.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package pack;


import structures.*;
import helper.NBTHelper;
import java.util.ArrayList;
import java.util.Arrays;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class PackParser
//
// Parse handler for uncompressed NBT data
//
public class PackParser {

    private String uncompressedInput; // String of the input contents
    private ArrayList<String> uncompressedLines; // List of the uncompressedInput split up by line


    // ----------------------------------------------------------------------------------------------------
    // public PackParser
    //
    // Constructor for PackParser class
    //
    // Arguments--
    //
    // uncompressedInput:   a string of the raw NBT data to compress into bytes
    //
    public PackParser(String uncompressedInput) {
        this.uncompressedInput = uncompressedInput; // Set the instance variable
        this.uncompressedLines = new ArrayList<>(Arrays.asList(this.uncompressedInput.split("\n"))); // Split and set the list of lines
    }
    // end: public PackParser


    // ====================================================================================================
    // private ArrayList<String> cleanInput
    //
    // Cleans up the raw input data
    //
    // Arguments--
    //
    // input:   a list of input lines to clean
    //
    // Returns--
    //
    // Cleaned input lines
    //
    private ArrayList<String> cleanInput(ArrayList<String> input) {
        ArrayList<String> cleanedInput = new ArrayList<>();

        // Loop through each of the lines
        for (String lineToClean : input) {
            lineToClean = lineToClean.replaceAll("//.*", ""); // Remove any comments and their text
            lineToClean = lineToClean.trim(); // Remove any leading or trailing whitespace

            if (lineToClean.matches(".+")) cleanedInput.add(lineToClean); // If the line still has content after the cleaning, then add it to the list of final lines
        }

        // Return the cleaned input
        return cleanedInput;
    }
    // end: private ArrayList<String> cleanInput


    // ====================================================================================================
    // public String pack
    //
    // Packs the uncompressed data into bytes
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // A hex string of the packed bytes
    //
    public String pack() throws Exception {
        this.uncompressedLines = this.cleanInput(this.uncompressedLines); // Clean up the input lines

        int compoundDepth = 0; // Keep track of the depth of compounds
        int arrayDepth = 0; // Keep track of the depth of arrays
        int listDepth = 0; // Keep track of the depth of lists
        int packAsList; // Whether to pack data as a list (0 for not, 1 for yes)
        int packAsListChange = 0; // How much to change the packAsList value by for each line
        int lineCounter = 0; // Keep track of the current lines being parsed


        StringBuilder packedBytes = new StringBuilder(); // Initialize a stringbuilder to be used to pack the data into

        // Loop through each of the lines
        while (lineCounter < this.uncompressedLines.size()) {
            String line = this.uncompressedLines.get(lineCounter); // Get the line being parsed

            if (line.contains("{")) compoundDepth++; // If the line contains the start of a compound, increment the compound depth
            else if (line.contains("}")) { // If the line is the end of a compound, decrement the compound depth and add the TAG_End 00 byte
                compoundDepth--;
                packedBytes.append("00");
            }
            else if (line.contains("[")) arrayDepth++; // If the line contains the start of an array, increment the array depth
            else if (line.contains("]")) arrayDepth--; // If the line contains the end of an array, decrement the array depth


            // Routine for determining whether to parse lines in a list format (without names) or not (with names)
            // Apply the change
            packAsList = packAsListChange;

            // Update whether content should be packed as a list or normally depending on if lists are ended or compounds started
            if ((line.contains(")") || line.contains("{")) && listDepth > 0) packAsListChange = 0;

            // Update the list depth counter
            if (line.contains("(")) listDepth++;
            else if (line.contains(")")) listDepth--;

            // If there are two lists back to back, then the counter must be immediately incremented and you cannot wait for the changer
            if (line.contains("(") && // Check that the current line starts a new list
                    lineCounter != 0 && this.uncompressedLines.get(lineCounter - 1).contains(")") && // And the previous line ended a list
                    listDepth > 1) // And all of this is in another list
                packAsList++;

            // Update whether content should be packed as a list or normally depending on if lists are started or compounds ended
            if ((line.contains("(") || line.contains("}")) && listDepth > 0) {
                packAsListChange++;
                if (listDepth > 1) packAsListChange = 1; // If in more than one list (nested lists), don't let the counter go over 1 to allow correct parsing of things inside elements of lists
            }

            if (listDepth == 0) packAsList = 0; // If not in a list anymore, then don't parse as a list
            // end: Routine for determining whether to parse as list or not


            String tag = line.split(" ")[0]; // Get the tag of the line

            // If the tag is not a tag, then increment the line counter and continue to the next line
            if (!Constants.PrimitiveTag.primitiveTagNames.contains(tag) && !Constants.CollectionTag.collectionTagNames.contains(tag)) {
                lineCounter++;
                continue;
            }

            // Switch on the tag to determine how to parse it
            switch (tag) {
                // If the tag is a primitive, then parse using the primitive method
                case Constants.PrimitiveTag.TAG_Byte, Constants.PrimitiveTag.TAG_Short, Constants.PrimitiveTag.TAG_Int, Constants.PrimitiveTag.TAG_Long, Constants.PrimitiveTag.TAG_Float, Constants.PrimitiveTag.TAG_Double, Constants.PrimitiveTag.TAG_String:
                    if (packAsList == 0) packedBytes.append(this.packPrimitiveType(line).getByteStream());
                    else packedBytes.append(this.packPrimitiveType(line).getByteStreamForList());
                    break;
                // If the tag is a collection, then parse using the collection method
                case Constants.CollectionTag.TAG_ByteArray, Constants.CollectionTag.TAG_List, Constants.CollectionTag.TAG_Compound, Constants.CollectionTag.TAG_IntArray, Constants.CollectionTag.TAG_LongArray:
                    if (packAsList == 0) packedBytes.append(this.packCollectionType(line, this.uncompressedLines).getByteStream());
                    else packedBytes.append(this.packCollectionType(line, this.uncompressedLines).getByteStreamForList());
                    break;
            }

            // Increment the line counter
            lineCounter++;
        }

        // After the loop, make sure both depth counters are 0 again, meaning there was the correct number of open and close brackets
        NBTHelper.nbtAssert((compoundDepth == 0),
                (compoundDepth > 0) ? "Missing close brace \"}\"" : "Missing open brace \"{\"",
                "Make sure all braces and brackets are on SEPARATE lines");
        NBTHelper.nbtAssert((arrayDepth == 0),
                (arrayDepth > 0) ? "Missing close bracket \"]\"" : "Missing open bracket \"[\"",
                "Make sure all braces and brackets are on SEPARATE lines");
        NBTHelper.nbtAssert((listDepth == 0),
                (listDepth > 0) ? "Missing close parenthesis \")\"" : "Missing open parenthesis \"(\"",
                "Make sure all braces and brackets are on SEPARATE lines");

        // Return the packed data
        return packedBytes.toString();
    }
    // end: public String pack


    // ====================================================================================================
    // private PrimitiveBytes packPrimitiveType
    //
    // Pack a primitive type tag into a PrimitiveBytes object
    //
    // Arguments--
    //
    // line:    the line to pack
    //
    // Returns--
    //
    // A PrimitiveBytes object containing all the information for the packed line
    //
    private PrimitiveBytes packPrimitiveType(String line) {
        String declaration = line.split("=")[0].trim(); // Get the declaration (type and name) of the line

        String tag = declaration.split(" ")[0]; // Get the tag type
        String name = declaration.split(" ")[1]; // Get the tag name
        String value = line.split("=")[1].trim(); // Get the value
        PrimitiveTag primitiveTag = new PrimitiveTag(tag, name, value); // Create a new PrimitiveTag object to get more data from

        byte identifier = primitiveTag.getIdentifier(); // Get the byte identifier for the tag
        short nameSize = primitiveTag.getNameSize(); // Get the size of the name in bytes
        String nameHex = primitiveTag.getNameHex(); // Get the hex string of the name
        String valueHex = primitiveTag.getValueHex(); // Get the hex string of the value
        short valueSize = primitiveTag.getSize(); // Get the size of the value

        return new PrimitiveBytes(identifier, nameSize, nameHex, valueSize, valueHex); // Return PrimitiveBytes object
    }
    // end: private PrimitiveBytes packPrimitiveType


    // ====================================================================================================
    // private CollectionBytes packCollectionType
    //
    // Pack a collection type tag into a CollectionBytes object
    //
    // Arguments--
    //
    // startLine:   the first line of the collection to pack
    //
    // allLines:    the list of all the input lines
    //
    // Returns--
    //
    // A CollectionBytes object containing all the information for the packed line
    //
    private CollectionBytes packCollectionType(String startLine, ArrayList<String> allLines) throws Exception {
        String declaration = startLine.split("=")[0].trim(); // Get the name and tag from the line

        String tag = declaration.split(" ")[0]; // Get the tag
        String name = declaration.split(" ")[1]; // Get the name
        int collectionSize = Integer.parseInt(declaration.split(" ")[2]); // Get the number of elements in the collection

        CollectionTag collectionTag = new CollectionTag(tag, name); // Create a CollectionTag object to get more data from

        // Get additional data about the collection tag
        int identifier = collectionTag.getIdentifier();
        int nameSize = collectionTag.getNameSize();
        String nameHex = collectionTag.getNameHex();

        // If the tag is a compound, return only identifier, name size, and name
        if (tag.equals(Constants.CollectionTag.TAG_Compound)) {
            return new CollectionBytes((byte) identifier, (short) nameSize, nameHex);
        }
        // If the tag is a list, pack separately
        else if (tag.equals(Constants.CollectionTag.TAG_List)) {
            return this.packList(startLine, allLines, identifier, nameSize, nameHex, collectionSize);
        }
        // If the tag is an array, pack separately
        else {
            return this.packArray(startLine, allLines, identifier, nameSize, nameHex, collectionSize);
        }
    }
    // end: private CollectionBytes packCollectionType


    // ====================================================================================================
    // private CollectionBytes packList
    //
    // Pack a list type
    //
    // Arguments--
    //
    // startLine:       the first line of the list
    //
    // allLines:        all the input lines
    //
    // identifier:      the byte id for the list
    //
    // nameSize:        the size of the list name
    //
    // nameHex:         the hex bytes of the list name
    //
    // expectedSize:    the expected number of elements in the list
    //
    // Returns--
    //
    // The packed list
    //
    private CollectionBytes packList(String startLine, ArrayList<String> allLines, int identifier, int nameSize, String nameHex, int expectedSize) {
        int listElementIdentifier = 0;

        for (int i = allLines.indexOf(startLine) + 1; i < allLines.size(); i++) {
            String currentLine = allLines.get(i);
            String tag = currentLine.split(" ")[0];

            if (Constants.PrimitiveTag.primitiveTagNames.contains(tag)) { listElementIdentifier = Constants.PrimitiveTag.primitiveTagIdentifiers.get(tag); break; }
            else if (Constants.CollectionTag.collectionTagNames.contains(tag)) { listElementIdentifier = Constants.CollectionTag.collectionTagIdentifiers.get(tag); break; }
        }

        return new CollectionBytes((byte) identifier, (short) nameSize, nameHex, (byte) listElementIdentifier, expectedSize);
    }
    // end: private CollectionBytes packList


    // ====================================================================================================
    // private CollectionBytes packArray
    //
    // Pack an array type
    //
    // Arguments--
    //
    // startLine:       the first line of the array
    //
    // allLines:        all the input lines
    //
    // identifier:      the byte id for the array
    //
    // nameSize:        the size of the array name
    //
    // nameHex:         the hex bytes of the array name
    //
    // expectedSize:    the expected number of elements in the array
    //
    // Returns--
    //
    // The packed list
    //
    private CollectionBytes packArray(String startLine, ArrayList<String> allLines, int identifier, int nameSize, String nameHex, int expectedSize) throws Exception {
        StringBuilder arrayContents = new StringBuilder();
        int arraySize = 0;

        int padSize = 2;
        if (identifier == Constants.CollectionTag.collectionTagIdentifiers.get(Constants.CollectionTag.TAG_IntArray)) padSize = 8;
        else if (identifier == Constants.CollectionTag.collectionTagIdentifiers.get(Constants.CollectionTag.TAG_LongArray)) padSize = 16;

        for (int i = allLines.indexOf(startLine) + 1; i < allLines.size(); i++) {
            String currentLine = allLines.get(i);

            if (currentLine.contains("]")) break;

            currentLine = currentLine.replaceAll(",\\s*", ",");
            for (String element : currentLine.split(",")) arrayContents.append(NBTHelper.padString(padSize, Integer.toHexString(Integer.parseInt(element))));

            arraySize += currentLine.split(",").length;
        }

        NBTHelper.nbtAssert((arraySize == expectedSize),
                "Incorrect number of elements in an array",
                "in array " + startLine + ", expected " + expectedSize + ", but found " + arraySize);

        return new CollectionBytes((byte) identifier, (short) nameSize, nameHex, arraySize, arrayContents.toString());
    }
    // end: private CollectionBytes packArray

}
// end: public class PackParser