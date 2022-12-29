package commands.components;


public enum Opcode {

    OR(0b00000),
    AND(0b00001),
    NOT(0b00011),
    CMP(0b00100),
    ADD(0b00110),
    SUB(0b00111),
    ORI(0b01000),
    ANDI(0b01001),
    NOTI(0b01011),
    CMPI(0b01100),
    ADDI(0b01110),
    SUBI(0b01111),
    BRZ(0b10000),
    BRO(0b10001),
    MF(0b10010),
    LD(0b11000),
    ST(0b11001),
    HALT(0b11111);


	private int opcodeInt;
	private String opcodeStr;


	private Opcode(int opcodeInt) {
		this.opcodeInt = opcodeInt;
		this.opcodeStr = this.name().toLowerCase();
	}


	public int toInteger() {
		return this.opcodeInt;
	}


	public String toString() {
		return this.opcodeStr;
	}
	
}
