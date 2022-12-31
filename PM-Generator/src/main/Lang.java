public class Lang {

	public static final int INSTRUCTION_NIBBLES = 7;
	public static final int OPCODE_WIDTH = Lang.OPCODE_HIGH - Lang.OPCODE_LOW;
	public static final int OPCODE_HIGH = 25;
	public static final int OPCODE_LOW = 20;
	public static final int WRITE_WIDTH = Lang.WRITE_HIGH - Lang.WRITE_LOW;
	public static final int WRITE_HIGH = 20;
	public static final int WRITE_LOW = 16;
	public static final int READA_WIDTH = Lang.READA_HIGH - Lang.READA_LOW;
	public static final int READA_HIGH = 16;
	public static final int READA_LOW = 12;
	public static final int READB_WIDTH = Lang.READB_HIGH - Lang.READB_LOW;
	public static final int READB_HIGH = 12;
	public static final int READB_LOW = 8;
	public static final int IMM_WIDTH = Lang.IMM_HIGH - Lang.IMM_LOW;
	public static final int IMM_HIGH = 8;
	public static final int IMM_LOW = 0;

	public static final String STD_DELIMITER = " ";
	public static final String ARG_DELIMITER = ",";
	public static final String LABEL_DELIMITER = ":";
	public static final String PC_DELIMITER = ":";
	
	public static final String DIRECTIVE_IDENTIFIER = ".";
	public static final String HEX_IDENTIFIER = "0x";
	
    public static final String DEC_ARGUMENT_REGEX = "[0-9]+";
    public static final String HEX_ARGUMENT_REGEX = Lang.HEX_IDENTIFIER + "[0-9a-fA-F]+";
    public static final String COMMENT_REGEX = "\\/\\/.*";
	public static final String LABEL_REGEX = "\\S+" + Lang.LABEL_DELIMITER;
	public static final String DIRECTIVE_REGEX = "\\" + Lang.DIRECTIVE_IDENTIFIER + ".+";


	private Lang() { }

}
