package commands;


import asm.MCAsm;
import asm.Lang;
import util.Log;


public class Loc {

	private int line;


	public Loc(String lineStr, int currentPC) {
		MCAsm.incrementCurrentToken();
		
		if (lineStr == null) {
			Log.format(Log.ERROR, "Loc", "illegal .loc argument, must be non-null: " + lineStr);
			return;
		}
		
		try {
			if (lineStr.startsWith(Lang.HEX_IDENTIFIER))
				this.line = Integer.parseInt(lineStr.substring(2), 16);
			else
				this.line = Integer.parseInt(lineStr);

			if (this.line < 0)
				Log.format(Log.ERROR, "Loc", "illegal .loc argument, must not be negative: " + this.line);
			if (this.line <= currentPC)
				Log.format(Log.ERROR, "Loc", "illegal .loc argument, must be after pc. Required >" +
						   currentPC + ", found " + this.line);
		}
		catch (NumberFormatException e) {
			Log.format(Log.ERROR, "Loc", "unexpected .loc argument: " + lineStr + ". Reason: " + e);
		}
	}


	public int getLine() {
		return this.line;
	}


	public String toAssembledString() {
		return null;
	}


	public String toAssemblyString() {
	    return Lang.DIRECTIVE_IDENTIFIER + "loc" + Lang.STD_DELIMITER + Log.toHexString(this.line, 1);
	}

}
