// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// UnpackParser.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package unpack;


import helper.NBTHelper;
import structures.*;
import java.util.Arrays;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class UnpackParser
//
// Parse handler for compressed NBT data
//
public class UnpackParser {

    private final byte[] compressedInput; // Byte array of all the unpacked bytes
    private final String compressedStringInput; // String version of compressedInput as a hex string


    // ----------------------------------------------------------------------------------------------------
    // public UnpackParser
    //
    // Constructor for UnpackParser class
    //
    // compressedInput: the array of all packed bytes
    //
    public UnpackParser(byte[] compressedInput) {
        // Set instance variables
        this.compressedInput = compressedInput;
        this.compressedStringInput = NBTHelper.byteArrayToHexString(this.compressedInput);
    }
    // public UnpackParser


    // ====================================================================================================
    // public String unpack
    //
    // Unpacks a list of bytes
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // The unpacked lines as one string
    //
    public String unpack() throws Exception {
        // Check that the input data is valid hex data
        NBTHelper.nbtAssert((this.compressedStringInput.matches("-?[0-9a-fA-F]+")),
                "Compressed input string is not a valid hex string",
                "for string " + this.compressedStringInput);

        int byteCounter = 0; // Keep track of the current byte being unpacked

        StringBuilder unpackedData = new StringBuilder(); // Create a string builder to add to as lines as unpacked

        // Loop through each byte in the input
        while (byteCounter < this.compressedInput.length) {
            byte identifier = this.compressedInput[byteCounter]; // Get the byte, assuming it is an identifier and not any other data byte

            // If the byte is not an identifier, continue
            if (!Constants.PrimitiveBytes.primitiveByteIdentifiers.contains(identifier) && !Constants.CollectionBytes.collectionByteIdentifiers.contains(identifier)) {
                byteCounter++;
                continue;
            }

            // If the byte is an identifier, decide how to parse it
            switch (identifier) {
                // Parse as primitive
                case Constants.PrimitiveBytes.ID_Byte, Constants.PrimitiveBytes.ID_Short, Constants.PrimitiveBytes.ID_Int, Constants.PrimitiveBytes.ID_Long, Constants.PrimitiveBytes.ID_Float, Constants.PrimitiveBytes.ID_Double, Constants.PrimitiveBytes.ID_String:
                    PrimitiveTag unpackedPrimitive = this.unpackPrimitiveType(identifier, this.compressedInput, byteCounter); // Get the unpacked data
                    byteCounter += unpackedPrimitive.getNumBytes(); // Increment the byte counter by the number of bytes that were unpacked
                    unpackedData.append(unpackedPrimitive.getUnpackedStream()); // Add the unpacked line
                    break;
                // Parse as collection
                case Constants.CollectionBytes.ID_ByteArray, Constants.CollectionBytes.ID_List, Constants.CollectionBytes.ID_Compound, Constants.CollectionBytes.ID_IntArray, Constants.CollectionBytes.ID_LongArray:
                    CollectionTag unpackedCollection = this.unpackCollectionType(identifier, this.compressedInput, byteCounter);
                    break;
            }
        }

        // Return the unpacked data
        return unpackedData.toString();
    }
    // end: public String unpack


    // ====================================================================================================
    // private PrimitiveTag packPrimitiveType
    //
    // Unpack a primitive type line
    //
    // Arguments--
    //
    // identifier:  the byte identifier for the type
    //
    // allBytes:    the list of all input bytes
    //
    // byteCounter: the counter to keep track of
    //
    // Returns--
    //
    // A PrimitiveTag object holding all the unpacked data
    //
    private PrimitiveTag unpackPrimitiveType(byte identifier, byte[] allBytes, int byteCounter) {
        String type = Constants.PrimitiveBytes.primitiveByteNames.get(identifier); // Get the tag type

        PrimitiveBytes primitiveBytes = new PrimitiveBytes(identifier, Arrays.copyOfRange(allBytes, byteCounter, allBytes.length)); // Create a PrimitiveBytes object to keep track of the bytes

        // Get other data
        String name = primitiveBytes.getName();
        String value = primitiveBytes.getValue();
        int numBytes = primitiveBytes.getNumBytes();

        return new PrimitiveTag(type, name, value, numBytes); // Return the PrimitiveTag object
    }
    // end: private PrimitiveTag unpackPrimitiveType


    private CollectionTag unpackCollectionType(byte identifier, byte[] allBytes, int byteCounter) {
        String type = Constants.CollectionBytes.collectionByteNames.get(identifier);

        if (identifier == Constants.CollectionBytes.ID_Compound) {
            return null;
        }
        else if (identifier == Constants.CollectionBytes.ID_List) {
            return null;//this.unpackList();
        }
        else {
            return null;//this.unpackArray();
        }
    }


//    private CollectionTag unpackArray() {
//
//    }


//    private CollectionTag unpackList() {
//
//    }

}
// end: public class UnpackParser