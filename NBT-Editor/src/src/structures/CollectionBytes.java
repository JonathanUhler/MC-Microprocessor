// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// CollectionBytes.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package structures;


import helper.NBTHelper;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class CollectionBytes
//
// Data structure for the packed bytes of a collection type tag
//
public class CollectionBytes {

    private final byte identifier; // Type of the collection
    private final short nameSize; // Number of bytes that make up the name
    private final String name; // Bytes of the name
    private byte collectionIdentifier; // Type of the collection
    private final int collectionSize; // Size of the collection
    private String collectionData; // Data in the collection


    // ----------------------------------------------------------------------------------------------------
    // public CollectionBytes
    //
    // Constructor 1 for CollectionBytes class
    //
    // Arguments--
    //
    // identifier:  type of the collection
    //
    // nameSize:    number of bytes that make up the collection's name
    //
    // name:        bytes of the name
    //
    public CollectionBytes(byte identifier, short nameSize, String name) {
        this(identifier, nameSize, name, (byte) -1, -1); // Call the next constructor with no collection size
    }
    // end: public CollectionBytes


    // ----------------------------------------------------------------------------------------------------
    // public CollectionBytes
    //
    // Constructor 2 for CollectionBytes class
    //
    // Arguments--
    //
    // identifier:              type of the collection
    //
    // nameSize:                number of bytes that make up the collection's name
    //
    // name:                    bytes of the name
    //
    // collectionIdentifier:    type of the collection
    //
    // collectionSize:          size of the collection
    //
    public CollectionBytes(byte identifier, short nameSize, String name, byte collectionIdentifier, int collectionSize) {
        // Set instance variables
        this.identifier = identifier;
        this.nameSize = nameSize;
        this.name = name;
        this.collectionIdentifier = collectionIdentifier;
        this.collectionSize = collectionSize;
    }
    // end: public CollectionBytes


    // ----------------------------------------------------------------------------------------------------
    // public CollectionBytes
    //
    // Constructor 3 for CollectionBytes class
    //
    // Arguments--
    //
    // identifier:              type of the collection
    //
    // nameSize:                number of bytes that make up the collection's name
    //
    // name:                    bytes of the name
    //
    // collectionSize:          size of the collection
    //
    // collectionData:          data in the collection
    //
    public CollectionBytes(byte identifier, short nameSize, String name, int collectionSize, String collectionData) {
        // Set instance variables
        this.identifier = identifier;
        this.nameSize = nameSize;
        this.name = name;
        this.collectionSize = collectionSize;
        this.collectionData = collectionData;
    }
    // end: public CollectionBytes


    // ====================================================================================================
    // public String getByteStreamForList
    //
    // Get all the bytes in order for the collection that is part of a list
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // Hex string of all the bytes of the collection
    //
    public String getByteStreamForList() throws Exception {
        if (this.identifier == Constants.CollectionTag.collectionTagIdentifiers.get(Constants.CollectionTag.TAG_Compound)) return "";
        // Get the byte stream for a list
        else if (this.identifier == Constants.CollectionTag.collectionTagIdentifiers.get(Constants.CollectionTag.TAG_List)) {
            // Check that the collection identifier and collection size have been defined
            NBTHelper.nbtAssert((this.collectionIdentifier != -1) &&
                            (this.collectionSize != -1),
                    "CollectionBytes object was constructed with insufficient data",
                    "collection type was TAG_List but either a collection ID or collection size were not provided");

            return NBTHelper.padString(2, Integer.toHexString(this.collectionIdentifier)) + // Identifier for the elements in the list
                    NBTHelper.padString(8, Integer.toHexString(this.collectionSize)); // Size of the list
        }
        // Get the byte stream for any type of array
        else {
            return NBTHelper.padString(8, Integer.toHexString(this.collectionSize)) + // Size of the array
                    NBTHelper.padString(((this.collectionData.length() + 1) / 2) * 2, this.collectionData); // Data in the array
        }
    }
    // end: public String getByteStreamForList


    // ====================================================================================================
    // public String getByteSteam
    //
    // Get all the bytes in order for the collection
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // Hex string of all the bytes of the collection
    //
    public String getByteStream() throws Exception {
        // Get the byte stream for a compound
        if (this.identifier == Constants.CollectionTag.collectionTagIdentifiers.get(Constants.CollectionTag.TAG_Compound)) {
            return NBTHelper.padString(2, Integer.toHexString(this.identifier)) + // Identifier
                    NBTHelper.padString(4, Integer.toHexString(this.nameSize)) + // Size of the name
                    NBTHelper.padString(((this.name.length() + 1) / 2) * 2, this.name); // name
        }
        // Get the byte stream for a list
        else if (this.identifier == Constants.CollectionTag.collectionTagIdentifiers.get(Constants.CollectionTag.TAG_List)) {
            // Check that the collection identifier and collection size have been defined
            NBTHelper.nbtAssert((this.collectionIdentifier != -1) &&
                    (this.collectionSize != -1),
                    "CollectionBytes object was constructed with insufficient data",
                    "collection type was TAG_List but either a collection ID or collection size were not provided");

            return NBTHelper.padString(2, Integer.toHexString(this.identifier)) + // Identifier
                    NBTHelper.padString(4, Integer.toHexString(this.nameSize)) + // Size of the name
                    NBTHelper.padString(((this.name.length() + 1) / 2) * 2, this.name) + // Name
                    NBTHelper.padString(2, Integer.toHexString(this.collectionIdentifier)) + // Identifier for the elements in the list
                    NBTHelper.padString(8, Integer.toHexString(this.collectionSize)); // Size of the list
        }
        // Get the byte stream for any type of array
        else {
            return NBTHelper.padString(2, Integer.toHexString(this.identifier)) + // Identifier
                    NBTHelper.padString(4, Integer.toHexString(this.nameSize)) + // Size of the name
                    NBTHelper.padString(((this.name.length() + 1) / 2) * 2, this.name) + // name
                    NBTHelper.padString(8, Integer.toHexString(this.collectionSize)) + // Size of the array
                    NBTHelper.padString(((this.collectionData.length() + 1) / 2) * 2, this.collectionData); // Data in the array
        }
    }
    // end: public String getByteSteam

}
// end: public class CollectionBytes