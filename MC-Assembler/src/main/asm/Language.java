package asm;


/**
 * Record information about the MC Microprocessor assembly language and file syntax.
 *
 * @author Jonathan Uhler
 */
public class Language {

	/** The number of nibbles used by a single instruction. */
	public static final int INSTRUCTION_NIBBLES = 7;
	/** The width, in bits, of the opcode portion of an instruction. */
	public static final int OPCODE_WIDTH = Language.OPCODE_HIGH - Language.OPCODE_LOW;
	/** The high bit, exclusive, of the opcode portion of an instruction. */
	public static final int OPCODE_HIGH = 25;
	/** The low bit, inclusive, of the opcode portion of an instruction. */
	public static final int OPCODE_LOW = 20;
	/** The width, in bits, of the write register portion of an instruction. */
	public static final int WRITE_WIDTH = Language.WRITE_HIGH - Language.WRITE_LOW;
	/** The high bit, exclusive, of the write register portion of an instruction. */
	public static final int WRITE_HIGH = 20;
	/** The low bit, inclusive, of the write register portion of an instruction. */
	public static final int WRITE_LOW = 16;
	/** The width, in bits, of the read A register portion of an instruction. */
	public static final int READA_WIDTH = Language.READA_HIGH - Language.READA_LOW;
	/** The high bit, exclusive, of the read A register portion of an instruction. */
	public static final int READA_HIGH = 16;
	/** The low bit, inclusive, of the read A register portion of an instruction. */
	public static final int READA_LOW = 12;
	/** The width, in bits, of the read B register portion of an instruction. */
	public static final int READB_WIDTH = Language.READB_HIGH - Language.READB_LOW;
	/** The high bit, exclusive, of the read B register portion of an instruction. */
	public static final int READB_HIGH = 12;
	/** The low bit, inclusive, of the read B register portion of an instruction. */
	public static final int READB_LOW = 8;
	/** The width, in bits, of the read immediate portion of an instruction. */
	public static final int IMM_WIDTH = Language.IMM_HIGH - Language.IMM_LOW;
	/** The high bit, exclusive, of the read immediate portion of an instruction. */
	public static final int IMM_HIGH = 8;
	/** The low bit, inclusive, of the read immediate portion of an instruction. */
	public static final int IMM_LOW = 0;

	/** The standard delimiter between major tokens (e.g. instructions and argument lists). */
	public static final String STD_DELIMITER = " ";
	/** The delimiter between arguments to instructions. */
	public static final String ARG_DELIMITER = ",";
	/** The delimiter between a label name and (optionally) the label's first instruction. */
	public static final String LABEL_DELIMITER = ":";
	/** The delimiter between the PC value and machine instruction in the output file. */
	public static final String PC_DELIMITER = ":";

	/** The prefix token for a directive */
	public static final String DIRECTIVE_IDENTIFIER = ".";
	/** The prefix token for a hex value. */
	public static final String HEX_IDENTIFIER = "0x";

	/** The regular expression for a decimal argument value. */
    public static final String DEC_ARGUMENT_REGEX = "[0-9]+";
	/** The regular expression for a hex argument value. */
    public static final String HEX_ARGUMENT_REGEX = Language.HEX_IDENTIFIER + "[0-9a-fA-F]+";
	/** The regular expression for a comment. */
    public static final String COMMENT_REGEX = "\\/\\/.*";
	/** The regular expression for a label. */
	public static final String LABEL_REGEX = "\\S+" + Language.LABEL_DELIMITER;
	/** The regular expression for a directive. */
	public static final String DIRECTIVE_REGEX = "\\" + Language.DIRECTIVE_IDENTIFIER + ".+";


	/**
	 * A private constructor to prevent construction of a {@code Language} object. Class
	 * variables should be accessed statically.
	 */
	private Language() { }

}
