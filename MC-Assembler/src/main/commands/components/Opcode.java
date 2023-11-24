package commands.components;


/**
 * Manages an instruction opcode.
 *
 * @author Jonathan Uhler
 */
public enum Opcode {

	/** {@code Opcode} element for the OR instruction. */
	OR(0b00000),
	/** {@code Opcode} element for the AND instruction. */
	AND(0b00001),
	/** {@code Opcode} element for the NOT instruction. */
	NOT(0b00011),
	/** {@code Opcode} element for the CMP instruction. */
	CMP(0b00100),
	/** {@code Opcode} element for the ADD instruction. */
	ADD(0b00110),
	/** {@code Opcode} element for the SUB instruction. */
	SUB(0b00111),
	/** {@code Opcode} element for the ORI instruction. */
	ORI(0b01000),
	/** {@code Opcode} element for the ANDI instruction. */
	ANDI(0b01001),
	/** {@code Opcode} element for the NOTI instruction. */
	NOTI(0b01011),
	/** {@code Opcode} element for the CMPI instruction. */
	CMPI(0b01100),
	/** {@code Opcode} element for the ADDI instruction. */
	ADDI(0b01110),
	/** {@code Opcode} element for the SUBI instruction. */
	SUBI(0b01111),
	/** {@code Opcode} element for the BRZ instruction. */
	BRZ(0b10000),
	/** {@code Opcode} element for the BRO instruction. */
	BRO(0b10001),
	/** {@code Opcode} element for the MF instruction. */
	MF(0b10010),
	/** {@code Opcode} element for the LD instruction. */
	LD(0b11000),
	/** {@code Opcode} element for the ST instruction. */
	ST(0b11001),
	/** {@code Opcode} element for the HALT instruction. */
	HALT(0b11111);
	

	/** The integer value of the opcode. */
	private int opcodeInt;
	/** The symbolic name of the instruction represented by this opcode. */
	private String opcodeStr;


	/**
	 * Constructs a new {@code Opcode} element.
	 *
	 * @param opcodeInt  the integer value of the opcode.
	 */
	private Opcode(int opcodeInt) {
		this.opcodeInt = opcodeInt;
		this.opcodeStr = this.name().toLowerCase();
	}


	/**
	 * Returns the integer representation of this opcode.
	 *
	 * @return the integer representation of this opcode.
	 */
	public int toInteger() {
		return this.opcodeInt;
	}


	/**
	 * Returns the string representation of this opcode.
	 *
	 * @return the string representation of this opcode.
	 */
	public String toString() {
		return this.opcodeStr;
	}
	
}
