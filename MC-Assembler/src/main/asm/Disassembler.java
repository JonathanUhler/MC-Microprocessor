package asm;


import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.io.File;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import commands.Instruction;
import commands.Imm;
import commands.Loc;
import commands.Label;
import commands.components.Register;


/**
 * Command line interface entry point for the disassembler. The disassembler is responsible for
 * inferring the origin assembly code (a {@code .asm} file) from a file containing machine
 * code.
 * <p>
 * Ordinary instructions can be recreated accurately, with a few exceptions that require special
 * conditions or directives to fully recreate:
 * <ul>
 * <li> Labels must be stored in the machine code file using the {@code .label} directive
 * <li> Changes in the numeral base of immediate values must be included in the machine code
 * <li> Some instruction alises (e.g. {@code nop == or r0 0x00 0x00} are inferred
 * </ul>
 *
 * The output of the disassembler guarantees that the same machine code can be recreated from
 * running the assembler again. However, because of the instruction aliases (like {@code nop}),
 * the disassembler can never be 100% confident that its output 100% matches the original
 * assembly code.
 *
 * @author Jonathan Uhler
 */
@Command(name = "dis")
public class Disassembler implements Runnable {

	/** A logger object for this class. */
	private static final Logger LOGGER = Logger.getLogger(Disassembler.class.getName());

	/** Path to the input {@code .bin} file. */
	@Parameters(paramLabel = "IN_FILE", description = "Input file with assembled instructions.")
	private String inPath;

	/** Whether to print help information. Help is handled automatically by picocli. */
	@Option(names = {"-h", "--help"}, usageHelp = true,
			description = "Display this message and exit.")
	private boolean help;

	/** Whether to overwrite the contents of the output {@code .asm} file. */
	@Option(names = {"-f", "--force"},
			description = "Overwrites existing content in the output file, if -o is used. " +
			"If not specified, appends.")
	private boolean force;

	/** The optional path to an output {@code .asm} file to save assembly code. */
	@Option(names = {"-o", "--outfile"}, paramLabel = "<OUT_FILE>",
			description = "Specify the location of an output file. " +
			"If not specified, output is printed to stdout.")
	private String outPath;


	/** The program counter value tracker throughout the assembly process to place instructions. */
	private int pc;
	/** The immediate value type (e.g. hex, dec, or default). */
	private Imm.Type immType;


	/**
	 * Defacto constructor, called by the picocli command-line interface by MCAsssembler::main. The
	 * execution of this constructor by the CLI also instantiates any annotated instance variables.
	 * Other instance variables and routines are set up by {@code run}.
	 *
	 * This constructor is private to prevent regular construction. This class should only be
	 * constructed through the CLI (otherwise instance variables may have undefined values,
	 * resulting in undefined function behavior).
	 */
	private Disassembler() { }


	/**
	 * The entry point used by the CLI during the construction of this object. This method is
	 * responsible for initializing the {@code pc} and {@code immType} instance variables. This
	 * method also serves as the main routine for this class (loads lines, disassembles lines, and
	 * handles output).
	 */
	@Override
	public void run() {
		// Set up the logger
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new Tracer.LogFormatter());
		Disassembler.LOGGER.setUseParentHandlers(false);
		Disassembler.LOGGER.addHandler(handler);

		// Set instance variables
		this.pc = -1;
		this.immType = Imm.Type.DEFAULT;

		// Load and disassemble lines
		List<String> assembled = DataManager.loadLines(new File(this.inPath));
		List<String> assembly = this.disassembleLines(assembled);

		// Check for errors
		if (DataManager.TRACER.getNumErrors() > 0) {
			DataManager.TRACER.dumpErrors();
			System.exit(1);
		}

		// Write to output file or print to stdout
		if (this.outPath != null) {
			int written = DataManager.saveLines(assembly, new File(this.outPath), this.force);
			if (written == -1)
				System.exit(1);
		}
		else {
			for (String line : assembly)
				Disassembler.LOGGER.info(line);
		}
	}


	/**
	 * Cleans (normalizes the format of) a line of machine code.
	 * <p>
	 * Line cleaning involves:
	 * <ul>
	 * <li> Comments are removed
	 * <li> Leading and trailing whitespace is removed
	 * <li> All whitespace is replaced with a single space character
	 * </ul>
	 *
	 * @param line  the line to clean.
	 *
	 * @return the cleaned line. The returned line may be an empty string, but will never be
	 *         {@code null}.
	 */
	private String cleanLine(String line) {
		// Remove comments
		line = line.replaceAll(Language.COMMENT_REGEX, "");
		// Remove leading/trailing whitespace
		line = line.trim();
		// Standardize argument separators
	    line = line.replaceAll(Language.ARG_DELIMITER + "\\s*",
							   Language.ARG_DELIMITER + Language.STD_DELIMITER);
		// Standardize whitespace
		line = line.replaceAll("\\s+", Language.STD_DELIMITER);

		return line;
	}


	/**
	 * Disassembles a list of of lines.
	 *
	 * @param assembled  a list of line of disassemble. The lines in this list do not need to
	 *                   be clean.
	 *
	 * @return a list of assembly code lines.
	 */
	private List<String> disassembleLines(List<String> assembled) {
		List<String> assembly = new ArrayList<>();

		// Start pc at -1 to allow for pc=0x00 to pass the chronology check in
		// disassembleInstruction
		this.pc = -1;
		for (int i = 0; i < assembled.size(); i++) {
			// Preprocess line
			DataManager.TRACER.setLineNum(i + 1);
			String line = this.cleanLine(assembled.get(i));
			if (line.length() == 0)
				continue;
			DataManager.TRACER.setLine(line);

			// Act upon directives (changing pc, imm type, etc) but don't add them (labels
			// are added later, and other directives are not added to the assembly code)
			if (line.matches(Language.DIRECTIVE_REGEX))
				this.disassembleDirective(line);
			// Process regular instructions, which will always be added to the output
			else {
				String instruction = this.disassembleInstruction(line);
				if (instruction != null) {
					// Split instruction lines by newlines, which may be included if directives
					// (e.g. loc) are added
					for (String subInstruction : instruction.split("\n"))
						assembly.add("\t" + subInstruction);
				}
			}

			// Check if any labels need to be inserted at this pc value
			String labelName = Label.getAliasName(this.pc);
			if (labelName != null)
				assembly.add(assembly.size() - 1, labelName + Language.LABEL_DELIMITER);
		}

		// Return lines
		return assembly;
	}


	/**
	 * Disassembles an operative instruction (that is, not a directive or label).
	 *
	 * @param line  the instruction line to disassemble.
	 *
	 * @return the disassembled line. If any error occurs during disassembly, {@code null} is
	 *         returned and error information is added to the {@code Tracer} object of
	 *         {@code DataManager}.
	 */
	private String disassembleInstruction(String line) {
		String[] split = line.split(Language.PC_DELIMITER + Language.STD_DELIMITER);
		if (split.length != 2) {
			DataManager.TRACER.incrementToken();
			DataManager.TRACER.addError("illegal expression, expected `{PC}: {instruction}`");
			return null;
		}

		// Process string components into the program counter and instruction. Make sure that
		// both values are marked as hexademical
		String pcStr = split[0];
		String instructionStr = split[1];
		if (!pcStr.startsWith(Language.HEX_IDENTIFIER) ||
			!instructionStr.startsWith(Language.HEX_IDENTIFIER))
		{
			if (!instructionStr.startsWith(Language.HEX_IDENTIFIER))
				DataManager.TRACER.incrementToken();
			DataManager.TRACER.addError("illegal start of expression, value should be hex");
			return null;
		}

		// Parse string components as integers from the hex strings
		int pcInt;
		int instructionInt;
		try {
			pcInt = Integer.parseInt(pcStr.substring(2), 16);
			instructionInt = Integer.parseInt(instructionStr.substring(2), 16);

			// Check for pc chronology (that is, make sure the pc did not just backwards when
			// reading the machine code line-by-line)
			if (pcInt <= this.pc) {
				DataManager.TRACER.addError("illegal start of expression, pc must be " +
											"chronological; expected >" + this.pc +
											", found " + pcInt);
				return null;
			}
		}
		catch (NumberFormatException e) {
			DataManager.TRACER.addError("unexpected type, value should be hex");
			return null;
		}

		String assembly = "";
		// Update the pc value, adding .loc as needed
		if (pcInt > this.pc + 1) {
			Loc loc = new Loc(Integer.toString(pcInt), this.pc);
			assembly += loc.toAssemblyString() + "\n";
		}
		this.pc = pcInt;

		// Disassemble the instruction
		int opcode = instructionInt >> Language.OPCODE_LOW;
		int argsMask = ((1 << (Language.OPCODE_LOW - Language.IMM_LOW)) - 1) << Language.IMM_LOW;
		int args = (instructionInt & argsMask) >> Language.IMM_LOW;
		Instruction instruction = Instruction.getInstance(opcode);
		if (DataManager.TRACER.hasNewErrors())
			return null;
		instruction.setRegisterValues(args);
		if (DataManager.TRACER.hasNewErrors())
			return null;

		assembly += instruction.toAssemblyString(this.immType);
		if (DataManager.TRACER.hasNewErrors())
			return null;
		return assembly;
	}


	/**
	 * Disassembles a directive or label.
	 *
	 * @param line  the line to disassemble.
	 *
	 * @return the disassembled directive or label.
	 */
	private String disassembleDirective(String line) {
		String[] split = line.split(Language.STD_DELIMITER);

		String type = split[0];
		String subtype = null;
		if (split.length >= 2)
			subtype = split[1];

		// Process each directive type and subtype
		switch (type) {
		// Labels
		case Language.DIRECTIVE_IDENTIFIER + "label":
			DataManager.TRACER.incrementToken();
			
			if (split.length != 3) {
				DataManager.TRACER.incrementToken();
				DataManager.TRACER.addError("illegal expression, .label expects two arguments");
				return null;
			}
			
			String name = split[1];
			String pcStr = split[2];
			int pcInt;
			try {
				pcInt = Integer.parseInt(pcStr.substring(2), 16);
			}
			catch (NumberFormatException e) {
				DataManager.TRACER.addError("unexpected label pc value: " + e);
				return null;
			}

			Label label = new Label(name, pcInt);
			if (DataManager.TRACER.hasNewErrors())
				return null;
			return label.toAssemblyString();
	    // Pragma directives
		case Language.DIRECTIVE_IDENTIFIER + "pragma":
			DataManager.TRACER.incrementToken();

			switch (subtype) {
			case "imm":
				Imm imm = Imm.getInstance((split.length == 3) ? split[2] : null);
				if (DataManager.TRACER.hasNewErrors())
					return null;
				this.immType = imm.getType();
				return imm.toAssemblyString();
			default:
				DataManager.TRACER.addError("unexpected pragma subtype: " + subtype);
				return null;
			}
		// Unknown/error condition
		default:
			DataManager.TRACER.addError("unexpected directive type: " + type);
			return null;
		}
	}

}
