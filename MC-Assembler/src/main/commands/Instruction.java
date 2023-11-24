package commands;


import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import asm.Language;
import asm.DataManager;
import commands.components.Opcode;
import commands.components.Register;


/**
 * Manages an instruction.
 * <p>
 * Instructions perform some operation on data in registers. The general syntax for an instruction
 * is:
 * {@code opcode rw ra rb [imm]}
 *
 * @author Jonathan Uhler
 */
public class Instruction {	

	/** The opcode for this instruction. */
	private Opcode opcode;
	/** The write register targetted by this instruction. */
	private Register write;
	/** The read A register targetted by this instruction. */
	private Register reada;
	/** The read B register targetted by this instruction. */
	private Register readb;
	/** The auxiliary register targetted by this instruction. */
	private Register aux;
	/** The default immediate type for this instruction, if it takes an immediate value. */
	private Imm.Type immDefaultType;

	/** The list of registers used by this instruction in the order they appear */
	private Register[] registers;
	/** The number of arguments expected by this instruction. */
	private int numArgs;


	/**
	 * Constructs a new {@code Instruction} object with no immediate value/default type.
	 *
	 * @param opcode  the opcode.
	 * @param write   the write register.
	 * @param reada   the read A register.
	 * @param readb   the read B register.
	 * @param aux     the auxiliary register.
	 */
	private Instruction(Opcode opcode,
						Register write,
						Register reada,
						Register readb,
						Register aux)
	{
		this(opcode, write, reada, readb, aux, null);
	}


	/**
	 * Constructs a new {@code Instruction} object with a immediate value/default type.
	 *
	 * @param opcode          the opcode.
	 * @param write           the write register.
	 * @param reada           the read A register.
	 * @param readb           the read B register.
	 * @param aux             the auxiliary register.
	 * @param immDefaultType  the default type of the immediate value for this instruction.
	 */
	private Instruction(Opcode opcode,
						Register write,
						Register reada,
						Register readb,
						Register aux,
						Imm.Type immDefaultType)
	{
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


	/**
	 * Constructs a new instance of the {@code Instruction} class from a string opcode.
	 *
	 * @param opcode  the opcode.
	 *
	 * @return a new {@code Instruction} object. If the opcode is not recognized, {@code null}
	 *         is returned.
	 */
	public static Instruction getInstance(String opcode) {
		// Pre-define registers to make switch cases shorter
		Register none = new Register(Register.Type.NONE);
		Register write = new Register(Register.Type.WRITE);
		Register reada = new Register(Register.Type.READ_A);
		Register readb = new Register(Register.Type.READ_B);
		Register readi = new Register(Register.Type.READ_IMM);
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
			return new Instruction(Opcode.ORI, write, reada, none, readi, Imm.Type.HEX);
		case "andi":
			return new Instruction(Opcode.ANDI, write, reada, none, readi, Imm.Type.HEX);
		case "noti":
			return new Instruction(Opcode.NOTI, write, none, none, readi, Imm.Type.HEX);
		case "cmpi":
			return new Instruction(Opcode.CMPI, write, reada, none, readi, Imm.Type.HEX);
		case "addi":
			return new Instruction(Opcode.ADDI, write, reada, none, readi, Imm.Type.DEC);
		case "subi":
			return new Instruction(Opcode.SUBI, write, reada, none, readi, Imm.Type.DEC);
		case "brz":
			return new Instruction(Opcode.BRZ, none, none, readb, readlabel);
		case "bro":
			return new Instruction(Opcode.BRO, none, none, readb, readlabel);
		case "mf":
			return new Instruction(Opcode.MF, write, none, none, readi, Imm.Type.HEX);
		case "mflo":
			Instruction mflo = new Instruction(Opcode.MF, write, none, none, readi, Imm.Type.HEX);
			mflo.setRegisterValues(new String[] {"0x00", "0x00"});
			return mflo;
		case "mfhi":
			Instruction mfhi = new Instruction(Opcode.MF, write, none, none, readi, Imm.Type.HEX);
			mfhi.setRegisterValues(new String[] {"0x00", "0x01"});
			return mfhi;
		case "ld":
			return new Instruction(Opcode.LD, write, reada, none, readi, Imm.Type.HEX);
		case "st":
			return new Instruction(Opcode.ST, write, reada, none, readi, Imm.Type.HEX);
		case "halt":
			return new Instruction(Opcode.HALT, none, none, none, readi, Imm.Type.HEX);
		case "li":
			return new Instruction(Opcode.ORI, write, none, none, readi, Imm.Type.HEX);
		case "mov":
			return new Instruction(Opcode.ORI, write, reada, none, none);
		case "nop":
			return new Instruction(Opcode.OR, none, none, none, none);
		case "br":
			return new Instruction(Opcode.BRZ, none, none, none, readlabel);
		default:
			DataManager.TRACER.addError("unexpected opcode: " + opcode);
			return null;
		}
	}


	/**
	 * Constructs a new instance of the {@code Instruction} class from an integer opcode.
	 *
	 * @param opcode  the opcode.
	 *
	 * @return a new {@code Instruction} object. If the opcode is not recognized, {@code null}
	 *         is returned.
	 */
	public static Instruction getInstance(int opcode) {
		// Convert the integer opcode to a string, if able
		String opcodeStr = null;
		for (Opcode opcodeEnum : Opcode.values()) {
			int opcodeInt = opcodeEnum.toInteger();
			if (opcode == opcodeInt)
				opcodeStr = opcodeEnum.toString();
		}

		// Use the existing infrastructure of getInstance(String) to get the Instruction object
		DataManager.TRACER.incrementToken();
		return Instruction.getInstance(opcodeStr);
	}


	/**
	 * Returns whether this instruction is an immediate instruction.
	 *
	 * @return whether this instruction is an immediate instruction.
	 */
	public boolean isImmediate() {		
		for (Register register : this.registers) {
			if (register.getType() == Register.Type.READ_IMM)
				return true;
		}
		return false;
	}


	/**
	 * Returns the default immediate type for this instruction. If this instruction has no
	 * immediate value, {@code null} is returned.
	 *
	 * @return the default immediate type for this instruction.
	 */
	public Imm.Type getImmDefaultType() {
		return this.immDefaultType;
	}


	/**
	 * Sets the values of the registers in this instruction from a list of arguments.
	 * <p>
	 * If the length of the provided argument list does not match the number of arguments
	 * expected by this instruction, the routine will exit.
	 *
	 * @param args  the values to set.
	 */
	public void setRegisterValues(String[] args) {
		if (args == null) {
			DataManager.TRACER.addError("expected non-null argument list");
			return;
		}
		if (args.length != this.numArgs) {
			DataManager.TRACER.addError("incorrect number of arguments; required " +
										this.numArgs + ", found " + args.length);
			return;
		}

		int i = 0;
		for (Register register : this.registers) {
			if (register.getType() == Register.Type.NONE ||
				i >= this.numArgs || i >= args.length)
				continue;

			String value = args[i];
			if (value == null) {
				DataManager.TRACER.addError("invalid null argument: " + Arrays.toString(args));
				return;
			}
			
			register.setValue(value);
			if (DataManager.TRACER.hasNewErrors()) {
				DataManager.TRACER.addError("failed to set register value"); // Propogate the error
				return;
			}
			i++;
		}

		// Exception for mfhi and mflo. Once the immediate value is set, only 1 argument is excepted
		if (this.opcode == Opcode.MF)
			this.numArgs = 1;
	}


	/**
	 * Sets the values of the registers in this instruction from a line of machine code.
	 *
	 * @param args  the values to set, as an integer that can be parsed.
	 */
	public void setRegisterValues(int args) {
		int writeMask = ((1 << Language.WRITE_WIDTH) - 1) << Language.WRITE_LOW;
		int readaMask = ((1 << Language.READA_WIDTH) - 1) << Language.READA_LOW;
		int readbMask = ((1 << Language.READB_WIDTH) - 1) << Language.READB_LOW;
		int auxMask = ((1 << Language.IMM_WIDTH) - 1) << Language.IMM_LOW;
		
		int write = (args & writeMask) >> Language.WRITE_LOW;
		int reada = (args & readaMask) >> Language.READA_LOW;
		int readb = (args & readbMask) >> Language.READB_LOW;
		int aux = (args & auxMask) >> Language.IMM_LOW;

		int[] argsList = new int[] {write, reada, readb, aux};

		for (int i = 0; i < this.registers.length; i++) {
			Register register = this.registers[i];
			int arg = argsList[i];

			if (register.getType() != Register.Type.NONE)
				register.setValue(Integer.toString(arg));
		}
	}


	/**
	 * Returns this instruction as an assembled string.
	 *
	 * @return this instruction as an assembled string.
	 */
	public String toAssembledString() {
		int bits = 0b00000000000000000000;
		bits |= this.opcode.toInteger() << Language.OPCODE_LOW;

		for (Register register : this.registers) {
			String value = register.getValue();
			if (register.getType() == Register.Type.NONE)
				continue;

			DataManager.TRACER.incrementToken();

			int intValue;
			// Parse immediate value based on the immediate type
			if (value.matches(Language.DEC_ARGUMENT_REGEX))
				intValue = Integer.parseInt(value, 10);
			else if (value.matches(Language.HEX_ARGUMENT_REGEX))
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
			case WRITE           -> bits |= intValue << Language.WRITE_LOW;
			case READ_A          -> bits |= intValue << Language.READA_LOW;
			case READ_B          -> bits |= intValue << Language.READB_LOW;
			case READ_IMM, LABEL -> bits |= intValue << Language.IMM_LOW;
			}
		}

		return DataManager.toHexString(bits, Language.INSTRUCTION_NIBBLES);
	}


	/**
	 * Returns a register of this instruction as a assembly code token.
	 *
	 * @param register  the register to convert to an assembly token.
	 * @param immType   the immediate type of this register to use if the register represents an
	 *                  immediate value.
	 *
	 * @return a register of this instruction as a assembly code token. If any error occurs during
	 *         conversion, {@code null} is returned.
	 */
	private String toAssemblyValue(Register register, Imm.Type immType) {
		Register.Type registerType = register.getType();
		String registerValue = register.getValue();
		
		int intValue;
		try {
			intValue = Integer.parseInt(registerValue);
		}
		catch (NumberFormatException e) {
			DataManager.TRACER.addError("unexpected type, cannot parse register: " + registerValue);
			return null;
		}
		
		if (registerType != Register.Type.READ_IMM) {
			String aliasName;
			if (registerType == Register.Type.LABEL)
				aliasName = Label.getAliasName(intValue);
			else
				aliasName = Register.getAliasName(intValue);
			if (aliasName == null) {
				DataManager.TRACER.addError("unexpected non-immediate argument: " +
											DataManager.toHexString(intValue, 1));
				return null;
			}
			return aliasName;
		}
		else {
			if (immType == Imm.Type.HEX)
				return DataManager.toHexString(intValue, 1);
			else
				return Integer.toString(intValue);
		}
	}


    /**
	 * Returns this instruction as a line of assembly code with its default immediate type.
	 *
	 * @return this instruction as a line of assembly code.
	 */
	public String toAssemblyString() {
		return this.toAssemblyString(Imm.Type.DEFAULT);
	}


	/**
	 * Returns this instruction as a line of assembly code with the specified immediate type.
	 *
	 * @param immType  the immediate numeral base to use if this instruction has an immediate
	 *                 value.
	 *
	 * @return this instruction as a line of assembly code.
	 */
	public String toAssemblyString(Imm.Type immType) {
		// Handle certain exceptions (mostly pseudo ops)
		switch (this.opcode) {
		case OR:
			// NOP -- opcode == OR, all == 0
			// Determine if this OR instruction is a NOP by checking the register value for zero
			boolean nop = true;
			for (Register register : this.registers) {
				if (register.getType() != Register.Type.NONE &&
					Integer.parseInt(register.getValue()) != 0)
				{
					nop = false;
					break;
				}
			}
			if (nop)
				return "nop";
			break;
		case ORI:
			// LI -- opcode == ORI, reada == 0
			if (Integer.parseInt(this.reada.getValue()) == 0)
				return "li" + Language.STD_DELIMITER +
					this.toAssemblyValue(this.write, immType) +
					Language.ARG_DELIMITER + Language.STD_DELIMITER +
					this.toAssemblyValue(this.aux, immType);
			// MOV -- opcode == ORI, imm = 0
			else if (Integer.parseInt(this.aux.getValue()) == 0)
				return "mov" + Language.STD_DELIMITER +
					this.toAssemblyValue(this.write, immType) +
					Language.ARG_DELIMITER + Language.STD_DELIMITER +
					this.toAssemblyValue(this.reada, immType);
			break;
		case BRZ:
			// BR -- opcode == BRZ, readb == 0
			if (Integer.parseInt(this.readb.getValue()) == 0)
				return "br" + Language.STD_DELIMITER +
					this.toAssemblyValue(this.aux, immType);
			break;
		case MF:
			// MFLO -- opcode == MF, readimm == 0 and MFHI -- opcode == MF, readimm == 1
			int mfType = Integer.parseInt(this.aux.getValue());
			switch (mfType) {
			case 0:
				return "mflo" + Language.STD_DELIMITER + this.toAssemblyValue(this.write, immType);
			case 1:
				return "mfhi" + Language.STD_DELIMITER + this.toAssemblyValue(this.write, immType);
			default:
				DataManager.TRACER.addError("unexpected type, mf instruction imm value was " +
											mfType + ", should be 0 or 1");
				return null;
			}
		}
		

		// Parse default case for most instructions, if this instruction did not fit any of the
		// exceptions above
		String str = this.opcode.toString() + Language.STD_DELIMITER;

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
				str += Language.ARG_DELIMITER + Language.STD_DELIMITER;
			a++;
		}

		return str;
	}

}
