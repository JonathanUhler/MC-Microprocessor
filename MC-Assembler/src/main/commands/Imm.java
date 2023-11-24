package commands;


import asm.Language;
import asm.DataManager;


/**
 * Manages an immediate ({@code .imm}) directive-type instruction.
 * <p>
 * The immediate directive is used to set the numeral base of immediate data types for the
 * disassembler. It has no impact on the function of the assembler.
 * <p>
 * Three values exist for the immediate directive, which takes a single argument: {@code dec} for
 * decimal (base 10), {@code hex} for hexademical (base 16), and {@code default} to automatically
 * choose between hex/dec based on the instruction type.
 * <p>
 * The complete syntax for an immediate directive is:
 * {@code .pragma imm <hex|dec|default>}
 * <p>
 * Once set, the immedate type is persisted for all instructions until the next immediate
 * directive. The default (if no immediate directive exists) is equivalent to {@code .imm default}
 * being placed at the top of the machine code file.
 *
 * @author Jonathan Uhler
 */
public class Imm {

	/**
	 * A list of valid arguments to the immediate directive.
	 */
	public enum Type {
		/** Hexademical. */
		HEX,
		/** Decimal. */
		DEC,
		/** Set the immediate type (hex/dec) automatically based on the instruction type. */
		DEFAULT
	}


	/** The type represented by this immediate directive. */
	private Type type;


	/**
	 * Constructs a new {@code Imm} object with a given immediate type.
	 *
	 * @param type  the type.
	 */
	private Imm(Type type) {
		this.type = type;
	}


	/**
	 * Constructs a new instance of an {@code Imm} object with a given type.
	 *
	 * @param type  a string representing the immediate directive type. This string must be
	 *              {@code "hex"}, {@code "dec"}, or {@code "default"}.
	 *
	 * @return a new {@code Imm} object with the specified type. If the type is not recognized,
	 *         then {@code null} is returned.
	 */
	public static Imm getInstance(String type) {
		DataManager.TRACER.incrementToken();
		
		switch (type) {
		case "hex":
			return new Imm(Type.HEX);
		case "dec":
			return new Imm(Type.DEC);
		case "default":
			return new Imm(Type.DEFAULT);
		default:
			DataManager.TRACER.addError("unexpected immediate type: " + type);
			return null;
		}
	}


	/**
	 * Returns the immediate value type specified by this immediate directive.
	 *
	 * @return the immediate value type specified by this immediate directive.
	 */
	public Type getType() {
		return this.type;
	}


	/**
	 * Returns this immediate directive as an assembled string.
	 * <p>
	 * The format of this string is:
	 * {@code .pragma imm <hex|dec|default>}
	 *
	 * @return this immediate directive as an assembled string.
	 */
	public String toAssembledString() {
		return Language.DIRECTIVE_IDENTIFIER +
			"pragma" + Language.STD_DELIMITER +
			"imm" + Language.STD_DELIMITER +
			this.type.name().toLowerCase();
	}


	/**
	 * Returns this immediate directive as a line of assembly code.
	 *
	 * @return always {@code null}, because the immediate directive is only used in assembly code.
	 */
	public String toAssemblyString() {
	    return null;
	}

}
