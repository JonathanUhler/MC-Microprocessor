// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// Register.java
// MC-Assembler
//
// Created by Jonathan Uhler on 10/10/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package assembler.fields;


import helper.AssemblerHelper;

import java.util.HashMap;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class Register
//
// Data structure for a register (rw, ra, rb, imm, and label)
//
public class Register {

    // List of all defined registers and their string aliases
    // This list is added to if labels are parsed
    private static HashMap<String, Integer> registers = new HashMap<>() {{
        put("r0", 0);
        put("r1", 1);
        put("r2", 2);
        put("r3", 3);
        put("r4", 4);
        put("r5", 5);
        put("r6", 6);
        put("r7", 7);
        put("r8", 8);
        put("r9", 9);
        put("r10", 10);
        put("r11", 11);
        put("r12", 12);
        put("r13", 13);
        put("r14", 14);
        put("r15", 15);
    }};


    public static final int NONE = 0; // Value for a null or undefined register
    public static final int WRITE = 1; // Value for a write-type register
    public static final int READ_A = 2; // Value for an A-type register
    public static final int READ_B = 3; // Value for a B-type register
    public static final int READ_IMM = 4; // Value for an immediate register
    public static final int LABEL = 5; // Value for a label reference register


    private final int registerType; // The type of the register
    private final int minValue; // Minimum legal value for the length of the value of a register
    private final int maxValue; // Maximum legal value for the length of the value of a register

    private String registerValue; // Value of the register as it is parsed


    // ----------------------------------------------------------------------------------------------------
    // public Register
    //
    // Constructor for Register class
    //
    // Arguments--
    //
    // registerType:    the type of the register
    //
    public Register(int registerType) {
        // Set instance variables
        this.registerType = registerType;
        this.minValue = 0;
        this.maxValue = (this.registerType == Register.READ_IMM || this.registerType == Register.LABEL) ? 255 : 15;
    }
    // end: public Register


    // ====================================================================================================
    // GET methods
    public int getRegisterType() {
        return registerType;
    }

    public String getRegisterValue() {
        return registerValue;
    }

    public static HashMap<String, Integer> getRegisters() {
        return registers;
    }
    // end: GET methods


    // ====================================================================================================
    // SET methods
    public void setRegisterValue(String registerValue) {
        this.registerValue = registerValue;
    }

    public static void setRegisters(HashMap<String, Integer> registers) {
        Register.registers = registers;
    }

    public static void addRegister(String key, Integer value) {
        Register.registers.put(key, value);
    }
    // end: SET methods

}
// end: public class Register