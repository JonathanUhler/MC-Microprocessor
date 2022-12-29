package commands.components;


import util.Log;
import java.util.Map;
import java.util.HashMap;


public class Register {

	public enum Type {
		NONE,
		WRITE,
		READ_A,
		READ_B,
		READ_IMM,
		LABEL
	}


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
	

	private Type type;
	private String value;


	public Register(Type type) {
		if (type == null)
			Log.format(Log.ERROR, "Register", "unexpected register type: " + type + ". Try Register.Type.NONE instead");
		
		this.type = type;
		this.value = null;
	}


	public static boolean hasAlias(String alias) {
		if (alias == null) {
			Log.format(Log.ERROR, "Register", "unexpected register alias: " + alias);
			return false;
		}
		
		return Register.aliases.containsKey(alias);
	}


	public static int getAliasValue(String alias) {
		if (alias == null || !Register.hasAlias(alias)) {
			Log.format(Log.ERROR, "Register", "unexpected register alias, did not exist or is null: " + alias);
			return 0;
		}

		return Register.aliases.get(alias);
	}


	public static String getAliasName(int value) {
		for (String alias : Register.aliases.keySet()) {
			int aliasValue = Register.aliases.get(alias);

			if (aliasValue == value)
				return alias;
		}
		return null;
	}


	public Type getType() {
		return this.type;
	}


	public String getValue() {
		return this.value;
	}


	public void setValue(String value) {
		if (this.type == Type.NONE && value == null) {
			Log.format(Log.ERROR, "Register", "unexpected register value for non-NONE register: " + value);
			return;
		}
		
		this.value = value;
	}


	@Override
	public String toString() {
		return this.type + "=" + this.value;
	}

}
