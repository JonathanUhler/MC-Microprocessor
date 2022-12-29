// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// NBTHelper.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/24/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package helper;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class NBTHelper
//
// Various helper methods
//
public class NBTHelper {

    // ====================================================================================================
    // public static void nbtAssert
    //
    // Check that a condition is true and throw an error otherwise
    //
    // Arguments--
    //
    // assertion:       a boolean assertion to evaluate
    //
    // failureMessage:  a message to print describing the error if the assertion fails
    //
    // ...extraArgs:    a list of extra arguments to describe the error
    //
    // Returns--
    //
    // None
    //
    public static void nbtAssert(boolean assertion, String failureMessage, String... extraArgs) throws Exception {
        if (!assertion) { // Check if the assertion is true or false
            // Create an error message
            String err = "ERROR: NBTEditor Assertion Failed; " + failureMessage + ((extraArgs.length > 0) ? " -\n\t" + String.join("\n\t", extraArgs) : "");

            // Print the message and throw an error
            System.out.println(err);
            throw new Exception("NBTEditor Assertion Failed: " + failureMessage);
        }
    }
    // end: public static void nbtAssert


    // ====================================================================================================
    // public static String padString
    //
    // Pad a string with 0s to a given total width
    //
    // Arguments--
    //
    // padThickness:    the total width of the final string
    //
    // data:            the string to pad
    //
    // Returns--
    //
    // The padded string
    //
    public static String padString(int padThickness, String data) {
        // Format a string with spaces to a given thickness and then replace the spaces
        return String.format("%1$" + padThickness + "s", data).replace(' ', '0');
    }
    // end: public static String padString


    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }


    public static String byteArrayToHexString(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a) sb.append(String.format("%02x", b));
        return sb.toString();
    }

}
// end: public class NBTHelper