// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// PMConstant.java
// PM-Generator
//
// Created by Jonathan Uhler on 11/13/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package helper;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class PMConstant
//
// Class of constants to be used throughout the project
//
public class PMConstant {

    // List of block IDs to use
    public static final int REDSTONE_DUST = 55;
    public static final int REDSTONE_TORCH = 76;
    public static final int COLORED_WOOL = 1;
    public static final int WHITE_WOOL = 35;

    // List of tags to look for in the template
    public static final String BLOCKS_ARRAY_LENGTH = "$BLOCKS_ARRAY_LENGTH";
    public static final String BLOCKS_ARRAY_DATA = "$BLOCKS_ARRAY_DATA";
    public static final String DATA_ARRAY_LENGTH = "$DATA_ARRAY_LENGTH";
    public static final String DATA_ARRAY_DATA = "$DATA_ARRAY_DATA";
    public static final String HEIGHT = "$HEIGHT";
    public static final String LENGTH = "$LENGTH";
    public static final String WIDTH = "$WIDTH";

    // Template data
    public static final String PM_TEMPLATE =
            "TAG_Compound Schematic 10 = {\n" +
            "\tTAG_ByteArray Blocks $BLOCKS_ARRAY_LENGTH = [\n" +
            "\t\t$BLOCKS_ARRAY_DATA\n" +
            "\t]\n" +
            "\tTAG_ByteArray Data $DATA_ARRAY_LENGTH = [\n" +
            "\t\t$DATA_ARRAY_DATA\n" +
            "\t]\n" +
            "\tTAG_List Entities 0 = (\n" +
            "\t)\n" +
            "\tTAG_List TileEntities 0 = (\n" +
            "\t)\n" +
            "\n" +
            "\tTAG_String Materials = Alpha\n" +
            "\n" +
            "\tTAG_Short Height = $HEIGHT\n" +
            "\tTAG_Short Length = $LENGTH\n" +
            "\tTAG_Short Width = $WIDTH\n" +
            "\tTAG_Int WEOffsetX = 0\n" +
            "\tTAG_Int WEOffsetY = 0\n" +
            "\tTAG_Int WEOffsetZ = 0\n" +
            "}";

}
// end: public class PMConstant