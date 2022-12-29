package commands;


import asm.MCAsm;
import asm.Lang;
import util.Log;


public class Imm {

	public enum Type {
		HEX,
		DEC,
		DEFAULT
	}
	

	private Type type;


	private Imm(Type type) {
		this.type = type;
	}


	public static Imm getInstance(String type) {
		MCAsm.incrementCurrentToken();
		
		switch (type) {
		case "hex":
			return new Imm(Type.HEX);
		case "dec":
			return new Imm(Type.DEC);
		case "default":
			return new Imm(Type.DEFAULT);
		default:
			Log.format(Log.ERROR, "Imm", "unexpected immediate type: " + type);
			return null;
		}
	}


	public Type getType() {
		return this.type;
	}


	public String toAssembledString() {
		return Lang.DIRECTIVE_IDENTIFIER + "pragma" + Lang.STD_DELIMITER +
			"imm" + Lang.STD_DELIMITER + this.type.name().toLowerCase();
	}


	public String toAssemblyString() {
	    return null;
	}

}
