package assembler;


import java.util.List;
import java.util.Arrays;
import java.util.Objects;


public class Instruction {

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

        public final int lsb = 20;
	public final int i;
	public final String s;

	private Opcode(int i) {
            this.i = i;
            this.s = this.name().toLowerCase();
	}
    }


    private Opcode opcode;
    private Register[] arguments;
    private Immediate.Type immediateDefaultType;


    public Instruction(String opcodeStr) {
        if (opcodeStr == null) {
            throw new NullPointerException("opcodeStr cannot be null");
        }

        Register w = new Register(Register.Type.WRITE);
        Register ra = new Register(Register.Type.READ_A);
        Register rb = new Register(Register.Type.READ_B);
        Register imm = new Register(Register.Type.READ_IMM);
        Register label = new Register(Register.Type.LABEL);

        switch (opcodeStr) {
        case "or":
            this.initialize(Opcode.OR, w, ra, rb, null);
            break;
        case "and":
            this.initialize(Opcode.AND, w, ra, rb, null);
            break;
        case "not":
            this.initialize(Opcode.NOT, w, null, rb, null);
            break;
        case "cmp":
            this.initialize(Opcode.CMP, w, ra, rb, null);
            break;
        case "add":
            this.initialize(Opcode.ADD, w, ra, rb, null);
            break;
        case "sub":
            this.initialize(Opcode.SUB, w, ra, rb, null);
            break;
        case "ori":
            this.initialize(Opcode.ORI, w, ra, null, imm, Immediate.Type.HEX);
            break;
        case "andi":
            this.initialize(Opcode.ANDI, w, ra, null, imm, Immediate.Type.HEX);
            break;
        case "noti":
            this.initialize(Opcode.NOTI, w, null, null, imm, Immediate.Type.HEX);
            break;
        case "cmpi":
            this.initialize(Opcode.CMPI, w, ra, null, imm, Immediate.Type.HEX);
            break;
        case "addi":
            this.initialize(Opcode.ADDI, w, ra, null, imm, Immediate.Type.DEC);
            break;
        case "subi":
            this.initialize(Opcode.SUBI, w, ra, null, imm, Immediate.Type.DEC);
            break;
        case "brz":
            this.initialize(Opcode.BRZ, null, null, rb, label);
            break;
        case "bro":
            this.initialize(Opcode.BRO, null, null, rb, label);
            break;
        case "mf":
            this.initialize(Opcode.MF, w, null, null, imm, Immediate.Type.HEX);
            break;
        case "mflo":
            this.initialize(Opcode.MF, w, null, null, imm, Immediate.Type.HEX);
            this.setArguments(Arrays.asList(new String[] {"0x00", "0x00"}));
            break;
        case "mfhi":
            this.initialize(Opcode.MF, w, null, null, imm, Immediate.Type.HEX);
            this.setArguments(Arrays.asList(new String[] {"0x00", "0x01"}));
            break;
        case "ld":
            this.initialize(Opcode.LD, w, ra, null, imm, Immediate.Type.HEX);
            break;
        case "st":
            this.initialize(Opcode.ST, w, ra, null, imm, Immediate.Type.HEX);
            break;
        case "halt":
            this.initialize(Opcode.HALT, null, null, null, imm, Immediate.Type.HEX);
            break;
        case "li":
            this.initialize(Opcode.ORI, w, null, null, imm, Immediate.Type.HEX);
            break;
        case "mov":
            this.initialize(Opcode.ORI, w, ra, null, null);
            break;
        case "nop":
            this.initialize(Opcode.OR, null, null, null, null);
            break;
        case "br":
            this.initialize(Opcode.BRZ, null, null, null, label);
            break;
        default:
            Tracer.INSTANCE.addError("unexpected opcode");
            return;
        }
    }


    private void initialize(Opcode opcode, Register w, Register ra, Register rb, Register imm) {
        this.initialize(opcode, w, ra, rb, imm, null);
    }


    private void initialize(Opcode opcode,
                            Register w,
                            Register ra,
                            Register rb,
                            Register imm,
                            Immediate.Type immediateDefaultType)
    {
        this.opcode = opcode;
        this.immediateDefaultType = immediateDefaultType;

        Register[] allArgs = {w, ra, rb, imm};
        this.arguments = Arrays.stream(allArgs).filter(Objects::nonNull).toArray(Register[]::new);
    }


    public void setArguments(List<String> tokens) {
        if (tokens == null) {
            throw new NullPointerException("tokens cannot be null");
        }
        if (tokens.size() != this.arguments.length) {
            Tracer.INSTANCE.addError("incorrect number of arguments");
            return;
        }

        int numArguments = this.arguments.length;
        for (int i = 0; i < numArguments; i++) {
            Register register = this.arguments[i];
            String value = tokens.get(i);

            register.setValue(value);
            if (Tracer.INSTANCE.hasNewErrors()) {
                return;
            }
        }
    }


    private Register getRegister(Register.Type type) {
        if (type == null) {
            throw new NullPointerException("type cannot be null");
        }

        for (Register register : this.arguments) {
            if (register.getType() == type) {
                return register;
            }
        }
        return null;
    }


    public boolean isImmediate() {
        return this.getRegister(Register.Type.READ_IMM) != null;
    }


    public Immediate.Type getImmediateDefaultType() {
        return this.immediateDefaultType;
    }


    public String getImmediateValue() {
        Register immediate = this.getRegister(Register.Type.READ_IMM);
        return immediate == null ? null : immediate.toAssemblyString();
    }


    public String toAssembledString() {
        int bits = 0;
        bits |= this.opcode.i << this.opcode.lsb;

        for (Register register : this.arguments) {
            Tracer.INSTANCE.incrementToken();
            Register.Type type = register.getType();
            int value = register.toAssembledString();

            bits |= value << type.lsb;
        }

        return "0x" + String.format("%1$07X", bits);
    }


    public String toAssemblyString() {
        throw new UnsupportedOperationException("Instruction.toAssemblyString not yet supported");
    }

}
