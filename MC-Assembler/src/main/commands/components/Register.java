package commands.components;


import java.util.Map;
import java.util.HashMap;
import asm.DataManager;


/**
 * Manages a data register used by an instruction.
 *
 * @author Jonathan Uhler
 */
public class Register {

	/**
	 * The purpose of the register.
	 */
	public enum Type {
		/** Not applicable/empty register. */
		NONE,
		/** Pointer to the write register for an instruction. */
		WRITE,
		/** Pointer to the read A register for an instruction. */
		READ_A,
		/** Pointer to the read B register for an instruction. */
		READ_B,
		/** Holds an immediate/literal value. */
		READ_IMM,
		/** Pointer to a PC value defined by a label directive. */
		LABEL
	}


	/** Mapping of physical registers in the register file to their assembly symbolic names. */
	private static Map<String, Integer> aliases = new HashMap<>() {{
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
	

	/** The type/purpose of this register's contents. */
	private Type type;
	/** The value/contents of this register. */
	private String value;


	/**
	 * Constructs a new {@code Register} object.
	 *
	 * @param type  the purpose of this register.
	 */
	public Register(Type type) {
		if (type == null)
			DataManager.TRACER.addError("unexpected register type: " + type + ". " +
										"Try Register.Type.NONE instead");
		
		this.type = type;
		this.value = null;
	}


	/**
	 * Determines whether the specified register symbolic name exists.
	 *
	 * @param alias  the symbolic name of a register.
	 *
	 * @return whether a register with the specified symbolic name exists.
	 */
	public static boolean hasAlias(String alias) {
		if (alias == null) {
			DataManager.TRACER.addError("unexpected register alias: " + alias);
			return false;
		}
		
		return Register.aliases.containsKey(alias);
	}


	/**
	 * Returns the integer representation of a register from its symbolic name.
	 *
	 * @param alias  the symbolic name of a register.
	 *
	 * @return the integer representation of the register.
	 */
	public static int getAliasValue(String alias) {
		if (alias == null || !Register.hasAlias(alias)) {
			DataManager.TRACER.addError("unexpected register alias: " + alias);
			return 0;
		}

		return Register.aliases.get(alias);
	}


	/**
	 * Returns the symbolic name of a register from its integer value.
	 *
	 * @param value  the integer value of a register.
	 *
	 * @return the symbolic name of the register with the specified integer value. If no such
	 *         register exists, {@code null} is returned.
	 */
	public static String getAliasName(int value) {
		for (String alias : Register.aliases.keySet()) {
			int aliasValue = Register.aliases.get(alias);

			if (aliasValue == value)
				return alias;
		}
		return null;
	}


	/**
	 * Returns the purpose of this register.
	 *
	 * @return the purpose of this register.
	 */
	public Type getType() {
		return this.type;
	}


	/**
	 * Returns the value of this register.
	 *
	 * @return the value of this register.
	 */
	public String getValue() {
		return this.value;
	}


	/**
	 * Sets the value of this register.
	 *
	 * @param value  the value.
	 */
	public void setValue(String value) {
		if (this.type != Type.NONE && value == null) {
			DataManager.TRACER.addError("unexpected value for non-NONE register: " + value);
			return;
		}
		
		this.value = value;
	}


	/**
	 * Returns a string representation of this register.
	 *
	 * @return a string representation of this register.
	 */
	@Override
	public String toString() {
		return this.type + "=" + this.value;
	}

}
