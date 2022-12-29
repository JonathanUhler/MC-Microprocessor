package structures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Constants {

    public record PrimitiveBytes() {
        public static final byte ID_End = 0x00;
        public static final byte ID_Byte = 0x01;
        public static final byte ID_Short = 0x02;
        public static final byte ID_Int = 0x03;
        public static final byte ID_Long = 0x04;
        public static final byte ID_Float = 0x05;
        public static final byte ID_Double = 0x06;
        public static final byte ID_String = 0x08;

        public static final HashMap<Byte, String> primitiveByteNames = new HashMap<>() {{
            put(ID_End, PrimitiveTag.TAG_End);
            put(ID_Byte, PrimitiveTag.TAG_Byte);
            put(ID_Short, PrimitiveTag.TAG_Short);
            put(ID_Int, PrimitiveTag.TAG_Int);
            put(ID_Long, PrimitiveTag.TAG_Long);
            put(ID_Float, PrimitiveTag.TAG_Float);
            put(ID_Double, PrimitiveTag.TAG_Double);
            put(ID_String, PrimitiveTag.TAG_String);
        }};

        public static final HashMap<Byte, Integer> primitiveIdentifierSizes = new HashMap<>() {{
            put(ID_End, 0);
            put(ID_Byte, 1);
            put(ID_Short, 2);
            put(ID_Int, 4);
            put(ID_Long, 8);
            put(ID_Float, 4);
            put(ID_Double, 8);
        }};

        public static final ArrayList<Byte> primitiveByteIdentifiers = new ArrayList<>(Arrays.asList(ID_End, ID_Byte, ID_Short, ID_Int, ID_Long, ID_Float, ID_Double, ID_String));
    }


    public record PrimitiveTag() {
        // Define the names of all the primitive tags
        public static final String TAG_End = "TAG_End";
        public static final String TAG_Byte = "TAG_Byte";
        public static final String TAG_Short = "TAG_Short";
        public static final String TAG_Int = "TAG_Int";
        public static final String TAG_Long = "TAG_Long";
        public static final String TAG_Float = "TAG_Float";
        public static final String TAG_Double = "TAG_Double";
        public static final String TAG_String = "TAG_String";

        // Define the bytes that identify all the primitive tags
        public static final HashMap<String, Byte> primitiveTagIdentifiers = new HashMap<>() {{
            put(TAG_End, PrimitiveBytes.ID_End);
            put(TAG_Byte, PrimitiveBytes.ID_Byte);
            put(TAG_Short, PrimitiveBytes.ID_Short);
            put(TAG_Int, PrimitiveBytes.ID_Int);
            put(TAG_Long, PrimitiveBytes.ID_Long);
            put(TAG_Float, PrimitiveBytes.ID_Float);
            put(TAG_Double, PrimitiveBytes.ID_Double);
            put(TAG_String, PrimitiveBytes.ID_String);
        }};

        // Define the sizes of all the primitive tags
        public static final HashMap<String, Integer> primitiveTagSizes = new HashMap<>() {{
            put(TAG_End, 0);
            put(TAG_Byte, 1);
            put(TAG_Short, 2);
            put(TAG_Int, 4);
            put(TAG_Long, 8);
            put(TAG_Float, 4);
            put(TAG_Double, 8);
        }};

        // Define a list of all the primitive tag names
        public static final ArrayList<String> primitiveTagNames = new ArrayList<>(primitiveTagIdentifiers.keySet());
    }


    public record CollectionBytes() {
        public static final byte ID_ByteArray = 0x07;
        public static final byte ID_List = 0x09;
        public static final byte ID_Compound = 0x0a;
        public static final byte ID_IntArray = 0x0b;
        public static final byte ID_LongArray = 0x0c;

        public static final HashMap<Byte, String> collectionByteNames = new HashMap<>() {{
            put(ID_ByteArray, CollectionTag.TAG_ByteArray);
            put(ID_List, CollectionTag.TAG_List);
            put(ID_Compound, CollectionTag.TAG_Compound);
            put(ID_IntArray, CollectionTag.TAG_IntArray);
            put(ID_LongArray, CollectionTag.TAG_LongArray);
        }};

        public static final ArrayList<Byte> collectionByteIdentifiers = new ArrayList<>(Arrays.asList(ID_ByteArray, ID_List, ID_Compound, ID_IntArray, ID_LongArray));
    }


    public record CollectionTag() {
        // Define the names of all the unpacked tags
        public static final String TAG_ByteArray = "TAG_ByteArray";
        public static final String TAG_List = "TAG_List";
        public static final String TAG_Compound = "TAG_Compound";
        public static final String TAG_IntArray = "TAG_IntArray";
        public static final String TAG_LongArray = "TAG_LongArray";

        // Define the byte representing the tag types
        public static final HashMap<String, Integer> collectionTagIdentifiers = new HashMap<>() {{
            put(TAG_ByteArray, 0x07);
            put(TAG_List, 0x09);
            put(TAG_Compound, 0x0a);
            put(TAG_IntArray, 0x0b);
            put(TAG_LongArray, 0x0c);
        }};

        // Define the list of unpacked tag names
        public static final ArrayList<String> collectionTagNames = new ArrayList<>(collectionTagIdentifiers.keySet());
    }

}