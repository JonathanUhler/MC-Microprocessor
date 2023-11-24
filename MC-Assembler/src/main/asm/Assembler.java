package asm;


import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.ConsoleHandler;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import commands.Instruction;
import commands.Imm;
import commands.Loc;
import commands.Label;
import commands.components.Register;


/**
 * Command line interface entry point for the assembler. The assembler is responsible for
 * generating a machine code {@code .bin} file from the assembly code in a {@code .asm} file.
 *
 * See {@code mcasm asm --help} for more information about command line usage.
 *
 * @author Jonathan Uhler
 */
@Command(name = "asm")
public class Assembler implements Runnable {

	/** A logger object for this class. */
	private static final Logger LOGGER = Logger.getLogger(Assembler.class.getName());
	

	/** Path to the input {@code .asm} file. */
	@Parameters(paramLabel = "IN_FILE", description = "Input file with assembly instructions.")
	private String inPath;

	/** Whether to print help information. Help is handled automatically by picocli. */
	@Option(names = {"-h", "--help"}, usageHelp = true,
			description = "Display this message and exit.")
	private boolean help;

	/** Whether to overwrite the contents of the output file. */
	@Option(names = {"-f", "--force"},
			description = "Overwrites existing content in the output file, if -o is used. " +
			"If not specified, appends.")
	private boolean force;

	/** The optional path to an output file to save assembly results. */
	@Option(names = {"-o", "--outfile"}, paramLabel = "<OUT_FILE>",
			description = "Specify the location of an output file. " +
			"If not specified, output is printed to stdout.")
	private String outPath;


	/** The program counter value tracked throughout the assembly process to place instructions. */
	private int pc;
	/** The immediate number type (e.g. hex, dec, or default). */
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
	private Assembler() { }


	/**
	 * The entry point used by the CLI during the construction of this object. This method is
	 * responsible for initializing the {@code pc} and {@code immType} instance variables. This
	 * method also serves as the main routine for this class (loads lines, assembles lines, and
	 * handles output).
	 */
	@Override
	public void run() {
		// Set up the logger
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new Tracer.LogFormatter());
		Assembler.LOGGER.setUseParentHandlers(false);
		Assembler.LOGGER.addHandler(handler);

		// Set up instance variables
		this.pc = 0x00;
		this.immType = Imm.Type.DEFAULT;
		
		// Load and assemble lines
		List<String> assembly = DataManager.loadLines(new File(this.inPath));
		List<String> assembled = this.assembleLines(assembly);

		// Check for errors
		if (DataManager.TRACER.getNumErrors() > 0) {
			DataManager.TRACER.dumpErrors();
			System.exit(1);
		}

		// Write to output file or print to stdout
		if (this.outPath != null) {
			int written = DataManager.saveLines(assembled, new File(this.outPath), this.force);
			if (written == -1)
				System.exit(1);
		}
		else {
			for (String line : assembled)
				Assembler.LOGGER.info(line);
		}
	}


	/**
	 * Cleans (normalizes the format of) an instruction line.
	 * <p>
	 * Line cleaning involves:
	 * <ul>
	 * <li> Comments are removed
	 * <li> Leading and trailing whitespace is removed
	 * <li> Argument separators are replaced with the ", " string (a single space character)
	 * <li> All whitespace is replaced with a single space character.
	 * <li> Labels with their first instruction on the same line are split into two lines
	 * </ul>
	 *
	 * If the specified line is empty before or after the cleaning process, the returned list
	 * will contain zero elements.
	 * <p>
	 * If the specified line does not contain a compound label (a label and line combination),
	 * the returned list will contain exactly one element.
	 * <p>
	 * If the specified line contains a label with its first instruction on the same line,
	 * the returned list will contain exactly two elements: 1) the label, and 2) the instruction.
	 *
	 * @param line  the line to clean.
	 *
	 * @return the cleaned line as a list of zero, one, or two elements representing the contents
	 *         of the cleaned line.
	 */
	private List<String> cleanLine(String line) {
		List<String> cleaned = new ArrayList<>();

		// Remove comments
		line = line.replaceAll(Language.COMMENT_REGEX, "");
		// Remove leading/trailing whitespace
		line = line.trim();
		// Standardize argument separators
		line = line.replaceAll(Language.ARG_DELIMITER + "\\s*",
							   Language.ARG_DELIMITER + Language.STD_DELIMITER);
		// Standardize whitespace
		line = line.replaceAll("\\s+", Language.STD_DELIMITER);
		
		// Parse labels that are on the same line as the first instruction
		if (line.contains(Language.LABEL_DELIMITER)) {
			String[] labelSplit = line.split(Language.LABEL_DELIMITER);
			// Split labels from their first instruction, if needed
			if (labelSplit.length >= 1)
				cleaned.add(labelSplit[0] + Language.LABEL_DELIMITER);
			if (labelSplit.length == 2)
				cleaned.add(labelSplit[1].trim());
		}

		// For non-label lines, add the line if not empty
		else if (!line.equals(""))
			cleaned.add(line);

		// Return the cleaned line contents
		return cleaned;
	}


	/**
	 * Assembles a list of of lines.
	 *
	 * @param assembly  a list of line of assemble. The lines in this list do not need to be clean.
	 *
	 * @return a list of assembled lines.
	 */
	private List<String> assembleLines(List<String> assembly) {
		List<String> assembled = new ArrayList<>();

		// Assemble directives (including labels). This must be done first to establish context
		// about what labels are available for use in the instructions later on.
		this.pc = 0x00;
		for (int i = 0; i < assembly.size(); i++) {
			DataManager.TRACER.setLineNum(i + 1);
			List<String> lines = this.cleanLine(assembly.get(i));
			if (lines.size() == 0)
				continue;

			for (String line : lines) {
				DataManager.TRACER.setLine(line);
				
				if (line.matches(Language.LABEL_REGEX) || line.matches(Language.DIRECTIVE_REGEX)) {
					String directive = this.assembleDirective(line);
					// Some directives do not have any assembled output (.loc for example, which
					// is inferred by any disassembler if the pc value suddenly skips), so do not
					// blindly add the assembled directives without doing the null check.
					if (directive != null)
						assembled.add(directive);
				}
				else
					this.pc++; // This would have been a regular instruction
			}
		}

		// Assemble instructions after the contextual information from the labels and directives
		// has been established at the top of the assembled output.
		this.pc = 0x00;
		for (int i = 0; i < assembly.size(); i++) {
			DataManager.TRACER.setLineNum(i + 1);
			List<String> lines = this.cleanLine(assembly.get(i));
			if (lines.size() == 0)
				continue;

			for (String line : lines) {
				DataManager.TRACER.setLine(line);
			
				// Labels are added to a constant list, so they do not need to be reparsed.
				if (line.matches(Language.LABEL_REGEX))
					continue;
				// Directives must be assembled again to change the assembly environment
				// (e.g. .loc directives).
				else if (line.matches(Language.DIRECTIVE_REGEX)) {
					String directive = this.assembleDirective(line);
					if (directive != null)
						assembled.add(directive);
				}
				// Regular instructions can be assembled for the first time, and added to the
				// list of assembled instructions
				else {
					String instruction = this.assembleInstruction(line);
					this.pc++;

					if (instruction == null)
						continue;
					// Split instruction lines by newlines, which may be included if pragmas
					// (e.g. imm) are added
					for (String subInstruction : instruction.split("\n"))
						assembled.add(subInstruction);
				}
			}
		}

		return assembled;
	}


	/**
	 * Assembles an operative instruction (that is, not a directive or label).
	 *
	 * @param line  the instruction line to assemble.
	 *
	 * @return the assembled line. If any error occurs during assembly, {@code null} is returned
	 *         and error information is added to the {@code Tracer} object of {@code DataManager}.
	 */
	private String assembleInstruction(String line) {
		// Remove commas to allow splitting by the remaining spaces
		line = line.replaceAll(Language.ARG_DELIMITER, "");
		String[] split = line.split(Language.STD_DELIMITER);

		// Get components of the instruction
		String opcode = split[0];
		String[] args = new String[split.length - 1];
		for (int i = 1; i < split.length; i++)
			args[i - 1] = split[i];

		// Create an instruction object to manage assembly
		Instruction instruction = Instruction.getInstance(opcode);
		if (DataManager.TRACER.hasNewErrors())
			return null;
		instruction.setRegisterValues(args);
		if (DataManager.TRACER.hasNewErrors())
			return null;

		// String for the assembled instruction to be built
		String assembled = "";

		// Add .imm directive to set the immediate value type for disassembly
		if (instruction.isImmediate()) {
			String immValueStr = args[args.length - 1];
			Imm.Type valueType = immValueStr.startsWith(Language.HEX_IDENTIFIER) ?
				Imm.Type.HEX :
				Imm.Type.DEC;
			Imm.Type defaultType = instruction.getImmDefaultType();

			// The immediate type does not need to be changed only if
			// 1) The immediate type for this instruction (valueType) is the default type
			//    (defaultType) and either the current type (this.immType) is the default type or
			//    the type for this instruction.
			// 2) The immediate type for this instruction (valueType) is not the default type
			//    (defaultType) but the type for this instruction *is* the same as the current
			//    type (this.immType).
			// If neither of this conditions is true, then the immediate type should be changed.
			boolean valueIsDefault = valueType == defaultType;
			boolean currentIsDefault = this.immType == Imm.Type.DEFAULT;
			boolean currentIsValue = this.immType == valueType;
			boolean noChangeFromDefault = valueIsDefault && (currentIsValue || currentIsDefault);
			boolean noChangeFromCurrent = !valueIsDefault && currentIsValue;
			if (!noChangeFromDefault && !noChangeFromCurrent) {
				this.immType = valueType;
				String immTypeStr = this.immType.name().toLowerCase();
				Imm imm = Imm.getInstance(immTypeStr);
				assembled += imm.toAssembledString() + "\n";
			}
		}

		// Add the assembled instruction
		assembled += DataManager.toHexString(this.pc, 1) + ": " + instruction.toAssembledString();
		if (DataManager.TRACER.hasNewErrors())
			return null;
		return assembled;
	}
	

	/**
	 * Assembles a directive or label.
	 *
	 * @param line  the line to assemble.
	 *
	 * @return the assembled directive or label.
	 */
	private String assembleDirective(String line) {
		// Process labels separately from regular directive types
		if (line.matches(Language.LABEL_REGEX)) {
			String[] split = line.split(Language.LABEL_DELIMITER);
			String name = split[0];

			Label label = new Label(name, this.pc);
			if (DataManager.TRACER.hasNewErrors())
				return null;
			return label.toAssembledString();
		}

		// Process other directives
		// Remove commas to split by the remaining spaces
		line = line.replaceAll(Language.ARG_DELIMITER, "");
		String[] split = line.split(Language.STD_DELIMITER);
		
		// Allocate the primary type (.loc, .pragma, etc.) for the directive. And optionally
		// attempt to allocate a subtype (imm for .pragma, etc.)
		String type = split[0];
		String subtype = null;
		if (split.length >= 2)
			subtype = split[1];

		// Process each directive type and subtype
		switch (type) {
		// Loc directives
		case Language.DIRECTIVE_IDENTIFIER + "loc":
			Loc loc = new Loc((split.length == 2) ? split[1] : null, this.pc);
			if (DataManager.TRACER.hasNewErrors())
				return null;
			this.pc = loc.getLine();
			return loc.toAssembledString();
		// Pragma directives
		case Language.DIRECTIVE_IDENTIFIER + "pragma":
			DataManager.TRACER.incrementToken();
			switch (subtype) {
			case "imm":
				Imm imm = Imm.getInstance((split.length == 3) ? split[2] : null);
				if (DataManager.TRACER.hasNewErrors())
					return null;
				return imm.toAssembledString();
			default:
				DataManager.TRACER.addError("unexpected pragma subtype: " + subtype);
			}
		// Unknown/error condition	
		default:
			DataManager.TRACER.addError("unexpected directive type: " + type);
			return null;
		}
	}

}
