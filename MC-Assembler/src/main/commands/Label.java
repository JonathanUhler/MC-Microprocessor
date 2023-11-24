package commands;


import java.util.Map;
import java.util.HashMap;
import asm.Language;
import asm.DataManager;
import commands.components.Register;


/**
 * Manages a label directive.
 * <p>
 * The label directive can be used to set branch points necessary to create loops and functiona
 * routines in the assembly language.
 * <p>
 * Labels are defined by programmer-defined names followed by a colon (":"). The basic syntax is:
 * {@code <name>:}
 * <p>
 * During definition, labels may include up to a single instruction on the same line following
 * the colon:
 * {@code <name>: <instruction>}
 * <p>
 * Once defined, labels can be referenced with their name in the branch instructions:
 * {@code br <reg> <name>}
 *
 * @author Jonathan Uhler
 */
public class Label {

	/** A mapping of the label name (the "alias") to the line number marked by the label. */
	private static Map<String, Integer> aliases = new HashMap<>();
	
	/** The symbolic name (alias) of this label. */
	private String name;
	/** The program counter/line number marked by this label. */
	private int line;


	/**
	 * Constructs a new {@code Label} object.
	 *
	 * @param name  the symbolic name/alias of this label.
	 * @param line  the program counter/line number marked by this label.
	 */
	public Label(String name, int line) {
		if (name == null || name.equals(""))
			DataManager.TRACER.addError("illegal label name: " + name);
		if (line < 0)
			DataManager.TRACER.addError("illegal label program counter value: " + line);
		
		this.name = name;
		this.line = line;

		Label.addAlias(this.name, this.line);
	}


	/**
	 * Determines whether the specified label has been declared and corresponds to a PC number.
	 *
	 * @param alias  the symbolic name of the label.
	 *
	 * @return whether the specified label has been declared.
	 */
	public static boolean hasAlias(String alias) {
		if (alias == null) {
			DataManager.TRACER.addError("unexpected label alias: " + alias);
			return false;
		}
		
		return Label.aliases.containsKey(alias);
	}


	/**
	 * Returns the program counter/line number marked by the specified label symbolic name.
	 *
	 * @param alias  the symbolic name of the label.
	 *
	 * @return the program counter/line number marked by the specified label. If the label alias
	 *         is invalid, {@code 0} is returned.
	 */
	public static int getAliasValue(String alias) {
		if (alias == null || !Label.hasAlias(alias)) {
			DataManager.TRACER.addError("unexpected label alias, cannot get PC value: " + alias);
			return 0;
		}

		return Label.aliases.get(alias);
	}


	/**
	 * Returns the symbolic name of a label that marks the specified program counter value.
	 *
	 * @param value  the PC value to get a label name for.
	 *
	 * @return the name of the label that marks the specified PC value, if such a label exists.
	 *         If no label exists at {@code pc = value}, {@code null} is returned.
	 */
	public static String getAliasName(int value) {
		for (String alias : Label.aliases.keySet()) {
			int aliasValue = Label.aliases.get(alias);

			if (aliasValue == value)
				return alias;
		}
		return null;
	}


	/**
	 * Adds a label/program counter pair to the list of known labels.
	 *
	 * @param alias  the symbolic name of the label to add.
	 * @param value  the program counter value marked by the label.
	 */
	public static void addAlias(String alias, int value) {
		if (alias == null || Label.hasAlias(alias)) {
			DataManager.TRACER.addError("unexpected label alias, exists or is null: " + alias);
			return;
		}
		
		Label.aliases.put(alias, value);
	}


	/**
	 * Returns this label directive as an assembled string.
	 *
	 * @return this label directive as an assembled string.
	 */
	public String toAssembledString() {
		return Language.DIRECTIVE_IDENTIFIER + "label" + Language.STD_DELIMITER +
			this.name + Language.STD_DELIMITER +
			Language.HEX_IDENTIFIER + Integer.toHexString(this.line);
	}


	/**
	 * Returns this label directive as a line of assembly code.
	 *
	 * @return this label directive as a line of asssembly code.
	 */
	public String toAssemblyString() {
	    return this.name + Language.LABEL_DELIMITER;
	}

}
