// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// Opcode.java
// MC-Assembler
//
// Created by Jonathan Uhler on 10/10/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package assembler.fields;


import helper.AssemblerHelper;

// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class Opcode
//
// Data structure for an opcode and list of all possible opcodes in a map
//
public class Opcode {

    // Row 1 of the opcode table
    public static final int OR = 0b00000;
    public static final int AND = 0b00001;
    public static final int NOT = 0b00011;
    public static final int CMP = 0b00100;
    public static final int ADD = 0b00110;
    public static final int SUB = 0b00111;
    // Row 2 of the opcode table
    public static final int ORI = 0b01000;
    public static final int ANDI = 0b01001;
    public static final int NOTI = 0b01011;
    public static final int CMPI = 0b01100;
    public static final int ADDI = 0b01110;
    public static final int SUBI = 0b01111;
    // Row 3 of the opcode table
    public static final int BRZ = 0b10000;
    public static final int BRO = 0b10001;
    public static final int MF = 0b10010;
    // Row 4 of the opcode table
    public static final int LD = 0b11000;
    public static final int ST = 0b11001;
    public static final int HALT = 0b11111;

    // All opcodes in the table
    public static final int[] opcodes = new int[]{OR, AND, -1, NOT, CMP, -1, ADD, SUB, ORI, ANDI, -1, NOTI, CMPI, -1, ADDI, SUBI, BRZ, BRO, MF, -1, -1, -1, -1, -1, LD, ST, -1, -1, -1, -1, -1, HALT};


    private final int opcode; // Opcode value, should be one of opcodes
    private final int minValue; // Minimum legal value for an opcode
    private final int maxValue; // Maximum legal value for an opcode


    // ----------------------------------------------------------------------------------------------------
    // public Opcode
    //
    // Constructor for Opcode class
    //
    // Arguments--
    //
    // opcode:  the integer opcode value for the opcode
    //
    public Opcode(int opcode) {
        // Set instance variable
        this.opcode = opcode;
        this.minValue = 0;
        this.maxValue = Opcode.opcodes.length;

        try {
            AssemblerHelper.asmAssert((this.opcode <= this.maxValue) &&
                            (this.opcode >= this.minValue),
                    "Opcode.Opcode contructed with an invalid opcode value",
                    "for opcode value " + this.opcode + ", was not between " + this.minValue + " and " + this.maxValue);
        } catch (Exception ignored) {}
    }
    // end: public Opcode


    // ====================================================================================================
    // GET methods
    public int getOpcode() {
        return opcode;
    }
    // end: GET methods

}
// end: public class Opcode