// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// Command.java
// MC-Assembler
//
// Created by Jonathan Uhler on 10/10/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package assembler.commands;


import assembler.fields.Opcode;
import assembler.fields.Register;
import helper.AssemblerHelper;

import java.util.ArrayList;
import java.util.HashMap;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class Command
//
// Command data structure and list of all possible commands
//
public class Command {

    // List of all possible commands and their expected arguments
    public static final HashMap<String, Command> COMMANDS = new HashMap<>(){{
        put("or", new Command(new Opcode(Opcode.OR), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.READ_B), new Register(Register.NONE)));
        put("and", new Command(new Opcode(Opcode.AND), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.READ_B), new Register(Register.NONE)));
        put("not", new Command(new Opcode(Opcode.NOT), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.READ_B), new Register(Register.NONE)));
        put("cmp", new Command(new Opcode(Opcode.CMP), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.READ_B), new Register(Register.NONE)));
        put("add", new Command(new Opcode(Opcode.ADD), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.READ_B), new Register(Register.NONE)));
        put("sub", new Command(new Opcode(Opcode.SUB), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.READ_B), new Register(Register.NONE)));

        put("ori", new Command(new Opcode(Opcode.ORI), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("andi", new Command(new Opcode(Opcode.ANDI), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("noti", new Command(new Opcode(Opcode.NOTI), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("cmpi", new Command(new Opcode(Opcode.CMPI), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("addi", new Command(new Opcode(Opcode.ADDI), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("subi", new Command(new Opcode(Opcode.SUBI), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));

        put("brz", new Command(new Opcode(Opcode.BRZ), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.READ_B), new Register(Register.LABEL)));
        put("bro", new Command(new Opcode(Opcode.BRO), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.READ_B), new Register(Register.LABEL)));
        put("mflo", new Command(new Opcode(Opcode.MF), new Register(Register.WRITE), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("mfhi", new Command(new Opcode(Opcode.MF), new Register(Register.WRITE), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.READ_IMM)));

        put("ld", new Command(new Opcode(Opcode.LD), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("st", new Command(new Opcode(Opcode.ST), new Register(Register.WRITE), new Register(Register.READ_A), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("halt", new Command(new Opcode(Opcode.HALT), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.READ_IMM)));

        put("li", new Command(new Opcode(Opcode.ORI), new Register(Register.WRITE), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.READ_IMM)));
        put("nop", new Command(new Opcode(Opcode.OR), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.NONE)));
        put("br", new Command(new Opcode(Opcode.BRZ), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.NONE), new Register(Register.LABEL)));
    }};


    private final Opcode opcode; // Opcode object for the commands
    private final Register registerWrite; // Write register object for the command
    private final Register registerA; // Input register A for the command
    private final Register registerB; // Input register B for the command
    private final Register registerAux; // Auxiliary register for the command (for immediate value or label reference)

    private final HashMap<Integer, Register> registers; // List of all the registers and their register types
    private int numArgs; // Number of argument the command has


    // ----------------------------------------------------------------------------------------------------
    // public Command
    //
    // Constructor for Command class
    //
    // Arguments--
    //
    // opcode:          an object for the command opcode
    //
    // registerWrite:   an object for th write register of the command
    //
    // registerA:       an object for the A register of the command
    //
    // registerB:       an object for the B register of the command
    //
    // aux:             an object for the auxiliary register of the command
    //
    public Command(Opcode opcode, Register registerWrite, Register registerA, Register registerB, Register aux) {
        // Set the instance variables
        this.opcode = opcode;
        this.registerWrite = registerWrite;
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerAux = aux;

        this.registers = new HashMap<>(); // Initialize the hashmap of registers
        // Set all the registers to null if they are not specified or the object value if they are specified
        this.registers.put(Register.WRITE, (this.registerWrite.getRegisterType() == Register.WRITE) ? this.registerWrite : null);
        this.registers.put(Register.READ_A, (this.registerA.getRegisterType() == Register.READ_A) ? this.registerA : null);
        this.registers.put(Register.READ_B, (this.registerB.getRegisterType() == Register.READ_B) ? this.registerB : null);
        this.registers.put(Register.READ_IMM, (this.registerAux.getRegisterType() == Register.READ_IMM) ? this.registerAux : null);
        this.registers.put(Register.LABEL, (this.registerAux.getRegisterType() == Register.LABEL) ? this.registerAux : null);

        // For each of the registers that is not null, add to the number of arguments the command takes
        for (Object register : this.registers.values()) {
            if (register != null) this.numArgs++;
        }
    }


    // ====================================================================================================
    // GET methods
    public int getNumArgs() {
        return numArgs;
    }

    public HashMap<Integer, Register> getRegisters() {
        return registers;
    }

    public HashMap<Integer, String> getRegisterValues() {
        return new HashMap<>() {{
            put(Register.WRITE, (registerWrite.getRegisterType() == Register.WRITE) ? registerWrite.getRegisterValue() : null);
            put(Register.READ_A, (registerA.getRegisterType() == Register.READ_A) ? registerA.getRegisterValue() : null);
            put(Register.READ_B, (registerB.getRegisterType() == Register.READ_B) ? registerB.getRegisterValue() : null);
            put(Register.READ_IMM, (registerAux.getRegisterType() == Register.READ_IMM) ? registerAux.getRegisterValue() : null);
            put(Register.LABEL, (registerAux.getRegisterType() == Register.LABEL) ? registerAux.getRegisterValue() : null);
        }};
    }
    // end: GET methods


    // ====================================================================================================
    // public void setRegisterValues
    //
    // Sets the values for all the registers
    //
    // Arguments--
    //
    // values:  the values of the registers
    //
    // Returns--
    //
    // None
    //
    public void setRegisterValues(ArrayList<String> values) throws Exception {
        AssemblerHelper.asmAssert((values.size() == this.numArgs),
                "Command.setRegisterValues called with an invalid number of values",
                "expected values.size() to be " + this.numArgs + ", but got " + values.size());

        int valueCounter = 0; // Counter to keep track of which argument is being set to a register
        for (Register register : this.registers.values()) { // Loop through each of the registers and set their values
            if (register != null && valueCounter < values.size()) {
                register.setRegisterValue(values.get(valueCounter)); // If the reg is not null, set the value of the register
                valueCounter++;
            }
        }
    }
    // end: public void setRegisterValues


    // ====================================================================================================
    // public String packArguments
    //
    // Packs the register values into bytes and returns the bytes
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // A hex string of the register values packed into bytes
    //
    public String packArguments() throws Exception {
        int assembledBits = 0b00000000000000000000; // Initialize a variable to hold the bits for the opcode and register values

        String binaryOpcode = Integer.toBinaryString(this.opcode.getOpcode()) + "00000000000000000000"; // Get the string representation of the opcode
        assembledBits |= Integer.parseInt(binaryOpcode, 2); // Bitwise OR the opcode with the 0s to get just the opcode with a bunch of trailing 0s

        // Loop through each of the register values
        for (int i = 0; i < this.getRegisterValues().size(); i++) {
            String regValue = new ArrayList<>(this.getRegisterValues().values()).get(i); // Get the register value

            // Check that the value is not null and can be parsed
            if (regValue != null) {
                int registerType = this.getRegisters().get(i + 1).getRegisterType(); // Get the register type
                String hexRegValue; // Initialize a variable to hold the hex string of the register

                if (regValue.matches("[0-9]+")) hexRegValue = Integer.toHexString(Integer.parseInt(regValue)); // If the register is a number, parse as dec
                else if (regValue.matches("0x[0-9a-fA-F]+")) hexRegValue = Integer.toHexString(Integer.parseInt(regValue.substring(2), 16)); // If the register has "0x" parse as hex
                else hexRegValue = Integer.toHexString(Register.getRegisters().get(regValue)); // Otherwise, index the list of valid register aliases and get that value

                // Switch through to find the correct register type and determine where the bits will go in assembledBits
                switch (registerType) {
                    case Register.WRITE -> assembledBits |= Integer.parseInt("00000" + Integer.toBinaryString(Integer.parseInt(hexRegValue, 16)) + "0000000000000000", 2);
                    case Register.READ_A -> assembledBits |= Integer.parseInt("000000000" + Integer.toBinaryString(Integer.parseInt(hexRegValue, 16)) + "000000000000", 2);
                    case Register.READ_B -> assembledBits |= Integer.parseInt("0000000000000" + Integer.toBinaryString(Integer.parseInt(hexRegValue, 16)) + "00000000", 2);
                    case Register.READ_IMM, Register.LABEL -> assembledBits |= Integer.parseInt("000000000000000000000" + Integer.toBinaryString(Integer.parseInt(hexRegValue, 16)), 2);
                    default -> AssemblerHelper.asmAssert(false,
                            "an unknown register type was specified when parsing an instruction",
                            "in register object " + this.getRegisters().get(i + 1));
                }
            }
        }

        // Return the packed argument string
        return String.format("%1$" + 7 + "s", Integer.toHexString(assembledBits)).replace(' ', '0');
    }
    // end: public String packArguments

}
// end: public class Command