package commands;


import asm.Lang;
import util.Log;
import commands.components.Register;
import java.util.Map;
import java.util.HashMap;


public class Label {

	private static Map<String, Integer> aliases = new HashMap<>();
	

	private String name;
	private int line;


	public Label(String name, int line) {
		if (name == null || name.equals(""))
			Log.format(Log.ERROR, "Label", "illegal label name: " + name);
		if (line < 0)
			Log.format(Log.ERROR, "Label", "illegal label program counter value: " + line);
		
		this.name = name;
		this.line = line;

		Label.addAlias(this.name, this.line);
	}


	public static boolean hasAlias(String alias) {
		if (alias == null) {
			Log.format(Log.ERROR, "Label", "unexpected label alias: " + alias);
			return false;
		}
		
		return Label.aliases.containsKey(alias);
	}


	public static int getAliasValue(String alias) {
		if (alias == null || !Label.hasAlias(alias)) {
			Log.format(Log.ERROR, "Label", "unexpected label alias, did not exist or is null: " + alias);
			return 0;
		}

		return Label.aliases.get(alias);
	}


	public static String getAliasName(int value) {
		for (String alias : Label.aliases.keySet()) {
			int aliasValue = Label.aliases.get(alias);

			if (aliasValue == value)
				return alias;
		}
		return null;
	}


	public static void addAlias(String alias, int value) {
		if (alias == null || Label.hasAlias(alias)) {
			Log.format(Log.ERROR, "Label", "unexpected label alias, exists or is null: " + alias);
			return;
		}
		
		Label.aliases.put(alias, value);
	}


	public String toAssembledString() {
		return Lang.DIRECTIVE_IDENTIFIER + "label" + Lang.STD_DELIMITER + this.name +
			Lang.STD_DELIMITER + Lang.HEX_IDENTIFIER + Integer.toHexString(this.line);
	}


	public String toAssemblyString() {
	    return this.name + Lang.LABEL_DELIMITER;
	}

}
