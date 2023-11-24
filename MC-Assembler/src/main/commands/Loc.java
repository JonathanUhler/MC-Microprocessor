package commands;


import asm.Language;
import asm.DataManager;


/**
 * Manages a location ({@code .loc}) directive.
 * <p>
 * The location directive is used to set the program counter to a higher value than what already
 * exists. This is useful for physically distancing routines (e.g. error/halt conditions) in the
 * program memory. Labels should be used to navigate between lines of code, while {@code .loc}
 * is used to "write" code at a high PC value.
 * <p>
 * The complete syntax for a location directive is:
 * {@code .loc <pc>}
 * <p>
 * Once set to a valid value (greater than the PC value at the time the location directive is
 * encountered), the program counter will be set to the value specified by the argument.
 *
 * @author Jonathan Uhler
 */
public class Loc {

	/** The program counter value targetted by this location directive. */
	private int line;


	/**
	 * Constructs a new location directive.
	 *
	 * @param lineStr    the argument to the location directive; must be parsable as an integer.
	 * @param currentPC  the current value of the program counter, used for error checking.
	 */
	public Loc(String lineStr, int currentPC) {
		DataManager.TRACER.incrementToken();
		
		if (lineStr == null) {
			DataManager.TRACER.addError("illegal .loc argument, must be non-null: " + lineStr);
			return;
		}
		
		try {
			if (lineStr.startsWith(Language.HEX_IDENTIFIER))
				this.line = Integer.parseInt(lineStr.substring(2), 16);
			else
				this.line = Integer.parseInt(lineStr);
		}
		catch (NumberFormatException e) {
			DataManager.TRACER.addError("unexpected .loc argument: " + lineStr + ". Reason: " + e);
		}

		if (this.line < 0)
			DataManager.TRACER.addError("illegal .loc argument, is negative: " + this.line);
		if (this.line <= currentPC)
			DataManager.TRACER.addError("illegal .loc argument, before PC: required >" +
										currentPC + ", found " + this.line);
	}


	/**
	 * Returns the program counter value targetted by this location directive.
	 *
	 * @return the program counter value targetted by this location directive.
	 */
	public int getLine() {
		return this.line;
	}


	/**
	 * Returns this location directive as an assembled string.
	 *
	 * @return always {@code null}, because the location directive is only used in machine code.
	 */
	public String toAssembledString() {
		return null;
	}


	/**
	 * Returns this location directive as a line of assembly code.
	 *
	 * @return this location directive as a line of assembly code.
	 */
	public String toAssemblyString() {
	    return Language.DIRECTIVE_IDENTIFIER + "loc" + Language.STD_DELIMITER +
			DataManager.toHexString(this.line, 1);
	}

}
