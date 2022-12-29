// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// CollectionTag.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/23/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package structures;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class CollectionTag
//
// Data structure for the unpacked representation of a collection type tag
//
public class CollectionTag {

    private final String name; // Name of the tag
    private final int identifier; // Identifier for the tag
    private final int nameSize; // Size of the name
    private ArrayList<String> values;


    // ----------------------------------------------------------------------------------------------------
    // public CollectionTag
    //
    // Constructor for CollectionTag class
    //
    // Arguments--
    //
    // type:    the type of the tag
    //
    // name:    the name of the tag
    //
    // size:    the size of the collection
    //
    public CollectionTag(String type, String name) {
        this.name = name;
        this.identifier = Constants.CollectionTag.collectionTagIdentifiers.get(type);
        this.nameSize = this.name.length();
        this.values = new ArrayList<>();
    }
    // end: public CollectionType


    // ====================================================================================================
    // GET methods
    public String getName() {
        return name;
    }

    public String getNameHex() {
        StringBuilder nameHex = new StringBuilder();
        for (byte b : this.name.getBytes(StandardCharsets.UTF_8)) nameHex.append(Integer.toHexString(b));
        return nameHex.toString();
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getNameSize() {
        return nameSize;
    }
    // end: GET methods


    // ====================================================================================================
    // SET methods
    public void addValue(String value) {
        this.values.add(value);
    }
    // end: SET methods

}
// end: public class CollectionTag