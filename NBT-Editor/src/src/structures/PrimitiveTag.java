// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// PrimitiveTag.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package structures;


import java.nio.charset.StandardCharsets;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class PrimitiveTag
public class PrimitiveTag {

    private final String type; // Type of the tag
    private final String name; // Name of the tag
    private final String value; // Value of the tag
    private final byte identifier; // Byte identifier to go with the type
    private short size; // Size of the value
    private final short nameSize; // Size of the name
    private int numBytes;


    // ----------------------------------------------------------------------------------------------------
    // public PrimitiveTag
    //
    // Constructor for PrimitiveTag class
    //
    // Arguments--
    //
    // type:        the type of the tag
    //
    // name:        the name of the tag
    //
    // value:       the value of the tag
    //
    // numBytes:    the number of bytes in the packed primitive tag
    //
    public PrimitiveTag(String type, String name, String value, int numBytes) {
        this(type, name, value);
        this.numBytes = numBytes;
    }
    // end: public PrimitiveTag


    // ----------------------------------------------------------------------------------------------------
    // public PrimitiveTag
    //
    // Constructor for PrimitiveTag class
    //
    // Arguments--
    //
    // type:    the type of the tag
    //
    // name:    the name of the tag
    //
    // value:   the value of the tag
    //
    public PrimitiveTag(String type, String name, String value) {
        // Set instance variables
        this.type = type;
        this.name = name;
        this.value = value;
        this.identifier = Constants.PrimitiveTag.primitiveTagIdentifiers.get(this.type);
        this.nameSize = (short) this.name.length();

        // Set the length depending on what type of tag it is
        switch (type) {
            // For everything but string, get the length from the sizes hashmap
            case Constants.PrimitiveTag.TAG_End, Constants.PrimitiveTag.TAG_Byte, Constants.PrimitiveTag.TAG_Short, Constants.PrimitiveTag.TAG_Int, Constants.PrimitiveTag.TAG_Float, Constants.PrimitiveTag.TAG_Long, Constants.PrimitiveTag.TAG_Double -> this.size = (short) (int) Constants.PrimitiveTag.primitiveTagSizes.get(type);
            // For string, get the length from the length of the string
            case Constants.PrimitiveTag.TAG_String -> this.size = (short) this.value.length();
        }

        // MARK: assert size <= max allowed size
    }
    // end: public PrimitiveTag


    // ====================================================================================================
    // GET methods
    public int getNumBytes() {
        return numBytes;
    }

    public byte getIdentifier() {
        return identifier;
    }

    public short getSize() {
        return size;
    }

    public short getNameSize() {
        return nameSize;
    }

    public String getUnpackedStream() {
        return this.type + " " + this.name + " = " + this.value + "\n";
    }

    public String getNameHex() {
        StringBuilder nameHex = new StringBuilder();
        // Create a string by adding each byte of the name to a stringbuilder, then return that hex string
        for (byte b : this.name.getBytes(StandardCharsets.UTF_8)) nameHex.append(Integer.toHexString(b));
        return nameHex.toString();
    }

    public String getValueHex() {
        // Switch through the current type to decide how to parse the value
        switch (this.type) {
            // For all cases but string, just parse the string value as the correct type and then convert that to hex
            case Constants.PrimitiveTag.TAG_Byte:
                return Integer.toHexString(Byte.parseByte(this.value));
            case Constants.PrimitiveTag.TAG_Short:
                return Integer.toHexString(Short.parseShort(this.value));
            case Constants.PrimitiveTag.TAG_Int:
                return Integer.toHexString(Integer.parseInt(this.value));
            case Constants.PrimitiveTag.TAG_Long:
                return Long.toHexString(Long.parseLong(this.value));
            case Constants.PrimitiveTag.TAG_Float:
                return Integer.toHexString(Float.floatToIntBits(Float.parseFloat(this.value)));
            case Constants.PrimitiveTag.TAG_Double:
                return Long.toHexString(Double.doubleToLongBits(Double.parseDouble(this.value)));
            // For string, add each individual byte of the string to a stringbuilder and return that
            case Constants.PrimitiveTag.TAG_String:
                StringBuilder stringHex = new StringBuilder();
                for (byte b : this.value.getBytes(StandardCharsets.UTF_8)) stringHex.append(Integer.toHexString(b));
                return stringHex.toString();
            default:
                return "";
        }
    }
    // end: GET methods

}
// end: public class PrimitiveTag