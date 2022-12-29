// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// PrimitiveBytes.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package structures;


import helper.NBTHelper;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class PrimitiveBytes
//
// Data structure for the packed bytes of a primitive type tag
//
public class PrimitiveBytes {

    private final byte identifier; // Type of the primitive tag
    private final short nameSize; // Size of the tag's name
    private final String name; // Name of the tag
    private final short valueSize; // Size of the value
    private final String value; // Value of the tag
    private int numBytes;


    public PrimitiveBytes(byte identifier, byte[] lineBytes) {
        this.identifier = identifier;
        this.nameSize = (short) ((lineBytes[1] << 8) | lineBytes[2]);
        this.numBytes += 3;

        byte[] nameBytes = Arrays.copyOfRange(lineBytes, 3, this.nameSize + 3);
        this.name = new String(nameBytes, StandardCharsets.UTF_8);
        this.numBytes += nameBytes.length;

        this.valueSize = (short) ((this.identifier == Constants.PrimitiveBytes.ID_String) ?
                (lineBytes[this.nameSize + 3] << 8) | lineBytes[this.nameSize + 4] :
                Constants.PrimitiveBytes.primitiveIdentifierSizes.get(this.identifier));
        this.numBytes += (this.identifier == Constants.PrimitiveBytes.ID_String) ? 2 : 0;

        int valueStart = (this.identifier == Constants.PrimitiveBytes.ID_String) ? 3 + this.nameSize + 2 : 3 + this.nameSize;
        byte[] valueBytes = Arrays.copyOfRange(lineBytes, valueStart, this.valueSize + valueStart);

        this.value = switch (this.identifier) {
            case Constants.PrimitiveBytes.ID_Byte -> String.valueOf(valueBytes[0]);
            case Constants.PrimitiveBytes.ID_Short -> String.valueOf(ByteBuffer.wrap(valueBytes).getShort());
            case Constants.PrimitiveBytes.ID_Int -> String.valueOf(ByteBuffer.wrap(valueBytes).getInt());
            case Constants.PrimitiveBytes.ID_Long -> String.valueOf(ByteBuffer.wrap(valueBytes).getLong());
            case Constants.PrimitiveBytes.ID_Float -> String.valueOf(ByteBuffer.wrap(valueBytes).getFloat());
            case Constants.PrimitiveBytes.ID_Double -> String.valueOf(ByteBuffer.wrap(valueBytes).getDouble());
            case Constants.PrimitiveBytes.ID_String -> new String(valueBytes, StandardCharsets.UTF_8);
            default -> "";
        };

        this.numBytes += valueBytes.length;
    }


    // ----------------------------------------------------------------------------------------------------
    // public PrimitiveBytes
    //
    // Constructor for PrimitiveBytes class
    //
    // Arguments--
    //
    // identifier:  type of the primitive tag
    //
    // nameSize:    size of the tag's name
    //
    // name:        name of the tag
    //
    // valueSize:   size of the value
    //
    // value:       value of the tag
    //
    public PrimitiveBytes(byte identifier, short nameSize, String name, short valueSize, String value) {
        // Set instance variables
        this.identifier = identifier;
        this.nameSize = nameSize;
        this.name = name;
        this.valueSize = valueSize;
        this.value = value;
    }
    // end: public PrimitiveBytes


    // ====================================================================================================
    // GET methods
    public int getNumBytes() {
        return numBytes;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    // end: GET methods


    // ====================================================================================================
    // public String getByteStreamForList
    //
    // Get the list of bytes for a primitive tag that is part of a list
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // List of the bytes in order
    //
    public String getByteStreamForList() {
        return ((this.identifier == Constants.PrimitiveTag.primitiveTagIdentifiers.get(Constants.PrimitiveTag.TAG_String)) ? // Check if the tag is a string
                NBTHelper.padString(4, Integer.toHexString(this.valueSize)) : // If it is a string, then add the length of the string
                "") +
                NBTHelper.padString(this.valueSize * 2, this.value); // Value of the tag
    }
    // end: public String getByteStreamForList


    // ====================================================================================================
    // public String getByteStream
    //
    // Get the list of bytes in order for the primitive tag
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // List of the bytes in order
    //
    public String getByteStream() {
        return NBTHelper.padString(2, Integer.toHexString(this.identifier)) + // Identifier
                NBTHelper.padString(4, Integer.toHexString(this.nameSize)) + // Size of the name
                NBTHelper.padString(((this.name.length() + 1) / 2) * 2, this.name) + // Name
                ((this.identifier == Constants.PrimitiveTag.primitiveTagIdentifiers.get(Constants.PrimitiveTag.TAG_String)) ? // Check if the tag is a string
                        NBTHelper.padString(4, Integer.toHexString(this.valueSize)) : // If it is a string, then add the length of the string
                        "") +
                NBTHelper.padString(this.valueSize * 2, this.value); // Value of the tag
    }
    // end: public String getByteStream

}
// end: public class PrimitiveBytes