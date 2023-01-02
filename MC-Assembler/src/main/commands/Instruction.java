package commands;


import asm.MCAsm;
import asm.Lang;
import util.Log;
import commands.components.Opcode;
import commands.components.Register;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class Instruction {	

	private Opcode opcode;
	private Register write;
	private Register reada;
	private Register readb;
	private Register aux;
	private Imm.Type immDefaultType;

	private Register[] registers;
	private int numArgs;


	private Instruction(Opcode opcode, Register write, Register reada, Register readb, Register aux) {
		this(opcode, write, reada, readb, aux, null);
	}


	private Instruction(Opcode opcode, Register write, Register reada,
						Register readb, Register aux, Imm.Type immDefaultType) {
		// Since this constructor is only accessed through getInstance, we know all fields are valid
		this.opcode = opcode;
		this.write = write;
		this.reada = reada;
		this.readb = readb;
		this.aux = aux;
		this.immDefaultType = immDefaultType;

		// Count number of expected arguments
		this.registers = new Register[] {this.write, this.reada, this.readb, this.aux};
		this.numArgs = 0;
		for (Register register : this.registers) {
			if (register.getType() != Register.Type.NONE)
				this.numArgs++;
		}
	}


	public static Instruction getInstance(String opcode) {
		// Pre-define registers to make switch statement fewer lines
		Register none = new Register(Register.Type.NONE);
		Register write = new Register(Register.Type.WRITE);
		Register reada = new Register(Register.Type.READ_A);
		Register readb = new Register(Register.Type.READ_B);
		Register readimm = new Register(Register.Type.READ_IMM);
		Register readlabel = new Register(Register.Type.LABEL);
		
		switch (opcode) {
		case "or":
			return new Instruction(Opcode.OR, write, reada, readb, none);
		case "and":
			return new Instruction(Opcode.AND, write, reada, readb, none);
		case "not":
			return new Instruction(Opcode.NOT, write, none, readb, none);
		case "cmp":
			return new Instruction(Opcode.CMP, write, reada, readb, none);
		case "add":
			return new Instruction(Opcode.ADD, write, reada, readb, none);
		case "sub":
			return new Instruction(Opcode.SUB, write, reada, readb, none);
		case "ori":
			return new Instruction(Opcode.ORI, write, reada, none, readimm, Imm.Type.HEX);
		case "andi":
			return new Instruction(Opcode.ANDI, write, reada, none, readimm, Imm.Type.HEX);
		case "noti":
			return new Instruction(Opcode.NOTI, write, none, none, readimm, Imm.Type.HEX);
		case "cmpi":
			return new Instruction(Opcode.CMPI, write, reada, none, readimm, Imm.Type.HEX);
		case "addi":
			return new Instruction(Opcode.ADDI, write, reada, none, readimm, Imm.Type.DEC);
		case "subi":
			return new Instruction(Opcode.SUBI, write, reada, none, readimm, Imm.Type.DEC);
		case "brz":
			return new Instruction(Opcode.BRZ, none, none, readb, readlabel);
		case "bro":
			return new Instruction(Opcode.BRO, none, none, readb, readlabel);
		case "mf":
			return new Instruction(Opcode.MF, write, none, none, readimm, Imm.Type.HEX);
		case "mflo":
			Instruction mflo = new Instruction(Opcode.MF, write, none, none, readimm, Imm.Type.HEX);
			mflo.setRegisterValues(new String[] {"0x00", "0x00"});
			return mflo;
		case "mfhi":
			Instruction mfhi = new Instruction(Opcode.MF, write, none, none, readimm, Imm.Type.HEX);
			mfhi.setRegisterValues(new String[] {"0x00", "0x01"});
			return mfhi;
		case "ld":
			return new Instruction(Opcode.LD, write, reada, none, readimm, Imm.Type.HEX);
		case "st":
			return new Instruction(Opcode.ST, write, reada, none, readimm, Imm.Type.HEX);
		case "halt":
			return new Instruction(Opcode.HALT, none, none, none, readimm, Imm.Type.HEX);
		case "li":
			return new Instruction(Opcode.ORI, write, none, none, readimm, Imm.Type.HEX);
		case "mov":
			return new Instruction(Opcode.ORI, write, reada, none, none);
		case "nop":
			return new Instruction(Opcode.OR, none, none, none, none);
		case "br":
			return new Instruction(Opcode.BRZ, none, none, none, readlabel);
		default:
			Log.format(Log.ERROR, "Instruction", "unexpected opcode: " + opcode);
			return null;
		}
	}


	public static Instruction getInstance(int opcode) {
		// Convert the integer opcode to a string, if able
		String opcodeStr = null;
		for (Opcode opcodeEnum : Opcode.values()) {
			int opcodeInt = opcodeEnum.toInteger();
			if (opcode == opcodeInt)
				opcodeStr = opcodeEnum.toString();
		}

		// Use the existing infrastructure of getInstance(String) to get the Instruction object
		MCAsm.incrementCurrentToken();
		return Instruction.getInstance(opcodeStr);
	}


	public boolean isImmediate() {		
		for (Register register : this.registers) {
			if (register.getType() == Register.Type.READ_IMM)
				return true;
		}
		return false;
	}


	public Imm.Type getImmDefaultType() {
		return this.immDefaultType;
	}


	public void setRegisterValues(String[] args) {
		if (args == null) {
			Log.format(Log.ERROR, "Instruction", "expected non-null argument list");
			return;
		}
		if (args.length != this.numArgs) {
			Log.format(Log.ERROR, "Instruction", "actual and formal argument lists differ in length; required " +
					   this.numArgs + ", found " + args.length);
			return;
		}

		int i = 0;
		for (Register register : this.registers) {
			if (register.getType() != Register.Type.NONE && i < this.numArgs && i < args.length) {				
				String value = args[i];
				if (value == null) {
					Log.format(Log.ERROR, "Instruction", "expected non-null args, found: " + Arrays.toString(args));
					return;
				}
				
				register.setValue(value);
				if (Log.hasNewErrors()) {
					Log.format(Log.ERROR, "Instruction", "failed to set register value"); // Propogate the error
					return;
				}
				i++;
			}
		}

		// Exception for mfhi and mflo. Once the immediate value is set, only 1 argument is excepted
		if (this.opcode == Opcode.MF)
			this.numArgs = 1;
	}


	public void setRegisterValues(int args) {
		int writeMask = ((1 << (Lang.WRITE_HIGH - Lang.WRITE_LOW)) - 1) << Lang.WRITE_LOW;
		int readaMask = ((1 << (Lang.READA_HIGH - Lang.READA_LOW)) - 1) << Lang.READA_LOW;
		int readbMask = ((1 << (Lang.READB_HIGH - Lang.READB_LOW)) - 1) << Lang.READB_LOW;
		int auxMask = ((1 << (Lang.IMM_HIGH - Lang.IMM_LOW)) - 1) << Lang.IMM_LOW;
		
		int write = (args & writeMask) >> Lang.WRITE_LOW;
		int reada = (args & readaMask) >> Lang.READA_LOW;
		int readb = (args & readbMask) >> Lang.READB_LOW;
		int aux = (args & auxMask) >> Lang.IMM_LOW;

		int[] argsList = new int[] {write, reada, readb, aux};

		for (int i = 0; i < this.registers.length; i++) {
			Register register = this.registers[i];
			int arg = argsList[i];

			if (register.getType() != Register.Type.NONE)
				register.setValue(Integer.toString(arg));
		}
	}


	public String toAssembledString() {
		int bits = 0b00000000000000000000;
		bits |= this.opcode.toInteger() << Lang.OPCODE_LOW;

		for (Register register : this.registers) {
			String value = register.getValue();
			if (register.getType() == Register.Type.NONE)
				continue;

			MCAsm.incrementCurrentToken();

			int intValue;
			// Immediate values
			if (value.matches(Lang.DEC_ARGUMENT_REGEX))
				intValue = Integer.parseInt(value, 10);
			else if (value.matches(Lang.HEX_ARGUMENT_REGEX))
				intValue = Integer.parseInt(value.substring(2), 16);
			// Register/label alias values
			else {
				// Alias for labels
				if (register.getType() == Register.Type.LABEL) {
					if (!Label.hasAlias(value))
						return null;
					intValue = Label.getAliasValue(value);
				}
				// Alias for registers (read a/b, write)
				else {
					if (!Register.hasAlias(value))
						return null;
					intValue = Register.getAliasValue(value);
				}
			}

			switch (register.getType()) {
			case WRITE           -> bits |= intValue << Lang.WRITE_LOW;
			case READ_A          -> bits |= intValue << Lang.READA_LOW;
			case READ_B          -> bits |= intValue << Lang.READB_LOW;
			case READ_IMM, LABEL -> bits |= intValue << Lang.IMM_LOW;
			}
		}

		return Log.toHexString(bits, Lang.INSTRUCTION_NIBBLES);
	}


	private String toAssemblyValue(Register register, Imm.Type immType) {
		Register.Type registerType = register.getType();
		String registerValue = register.getValue();
		
		int intValue;
		try {
			intValue = Integer.parseInt(registerValue);
		}
		catch (NumberFormatException e) {
			Log.format(Log.ERROR, "Instruction", "unexpected type, cannot parse register value: " + registerValue);
			return null;
		}
		
		if (registerType != Register.Type.READ_IMM) {
			String aliasName;
			if (registerType == Register.Type.LABEL)
				aliasName = Label.getAliasName(intValue);
			else
				aliasName = Register.getAliasName(intValue);
			if (aliasName == null) {
				Log.format(Log.ERROR, "Instruction",
						   "unexpected non-immediate argument: " + Log.toHexString(intValue, 1));
				return null;
			}
			return aliasName;
		}
		else {
			if (immType == Imm.Type.HEX)
				return Log.toHexString(intValue, 1);
			else
				return Integer.toString(intValue);
		}
	}


	public String toAssemblyString() {
		return this.toAssemblyString(Imm.Type.DEFAULT);
	}


	public String toAssemblyString(Imm.Type immType) {
		// Handle certain exceptions (mostly pseudo ops)
		switch (this.opcode) {
		case OR:
			// NOP -- opcode == OR, all == 0
			boolean nop = true;
			for (Register register : this.registers) {
				if (register.getType() != Register.Type.NONE && Integer.parseInt(register.getValue()) != 0) {
					nop = false;
					break;
				}
			}
			if (nop)
				return "nop";
			break;
		case ORI:
			// LI -- opcode == ORI, reada == 0
			if (Integer.parseInt(this.reada.getValue()) == 0) {
				return "li" + Lang.STD_DELIMITER + this.toAssemblyValue(this.write, immType) +
					Lang.ARG_DELIMITER + Lang.STD_DELIMITER + this.toAssemblyValue(this.aux, immType);
			}
			// MOV -- opcode == ORI, imm = 0
			else if (Integer.parseInt(this.aux.getValue()) == 0) {
				return "mov" + Lang.STD_DELIMITER + this.toAssemblyValue(this.write, immType) +
					Lang.ARG_DELIMITER + Lang.STD_DELIMITER + this.toAssemblyValue(this.reada, immType);
			}
			break;
		case BRZ:
			// BR -- opcode == BRZ, readb == 0
			if (Integer.parseInt(this.readb.getValue()) == 0) {
				return "br" + Lang.STD_DELIMITER +
					this.toAssemblyValue(this.aux, immType);
			}
			break;
		case MF:
			// MFLO -- opcode == MF, readimm == 0 and MFHI -- opcode == MF, readimm == 1
			int mfType = Integer.parseInt(this.aux.getValue());
			switch (mfType) {
			case 0:
				return "mflo" + Lang.STD_DELIMITER + this.toAssemblyValue(this.write, immType);
			case 1:
				return "mfhi" + Lang.STD_DELIMITER + this.toAssemblyValue(this.write, immType);
			default:
				Log.format(Log.ERROR, "Instruction", "unexpected type, mf instruction imm value was " +
						   mfType + ", should be 0 or 1");
				return null;
			}
		// Default case will continue to the remaining sequence to process normal operations
		}
		

		// Parse default case for most instructions, if this instruction did not fit any of the exceptions above
		// Parse instruction
		String str = this.opcode.toString() + Lang.STD_DELIMITER;

		// Handle immediate instructions
		if (this.isImmediate()) {
			if (immType == Imm.Type.DEFAULT || immType == null)
				immType = this.immDefaultType;
		}

		// Process each register
		int a = 0; // Argument number, to track comma placement
		for (int i = 0; i < this.registers.length; i++) {
			Register register = this.registers[i];
			if (register.getType() == Register.Type.NONE)
				continue;
			
			str += this.toAssemblyValue(register, immType);
			if (a < this.numArgs - 1)
				str += Lang.ARG_DELIMITER + Lang.STD_DELIMITER;
			a++;
		}

		return str;
	}

}
