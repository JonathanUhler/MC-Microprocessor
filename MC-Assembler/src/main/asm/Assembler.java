package asm;


import util.Log;
import commands.Instruction;
import commands.Imm;
import commands.Loc;
import commands.Label;
import commands.components.Register;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.List;
import java.util.ArrayList;


@Command(name = "asm")
public class Assembler implements Runnable {

	// CLI options
	@Parameters(paramLabel = "IN_FILE", description = "Input file with assembly instructions.")
	private String inPath;

	@Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this message and exit.")
	private boolean help;

	@Option(names = {"-f", "--force"},
			description = "Overwrites existing content in the output file, if -o is used. If not specified, appends.")
	private boolean force;

	@Option(names = {"-o", "--outfile"}, paramLabel = "<OUT_FILE>",
			description = "Specify the location of an output file. If not specified, output is printed to stdout.")
	private String outPath;


	// Assembler information
	private int pc; // Program counter value
	private Imm.Type immType; // Current immediate type (hex, dec, or default)


	// ----------------------------------------------------------------------------------------------------
	// public void run
	//
	// Defacto constructor, called by the picocli command-line interface by MCAsm::main. The execution of
	// this method by the CLI also instantiates any annotated instance varaibles. Other instance variables
	// and routines are set up like a normal constructor.
	//
	private Assembler() { }
	
	@Override
	public void run() {
		this.pc = 0x00;
		this.immType = Imm.Type.DEFAULT;
		
		// Load and assemble lines
		List<String> assembly = Log.loadLines(this.inPath);
		List<String> assembled = this.assembleLines(assembly);

		// Check for errors
		if (Log.getNumErrors() > 0) {
			Log.dumpErrors();
			System.exit(Log.ERROR);
		}

		// Write to output file or print to stdout
		if (this.outPath != null)
			Log.saveLines(assembled, this.outPath, this.force);
		else {
			for (String line : assembled)
				Log.stdout(Log.INFO, "Assembler", line);
		}
	}
	// end: public void run


	private List<String> cleanLine(String line) {
		List<String> cleaned = new ArrayList<>();

		line = line.replaceAll(Lang.COMMENT_REGEX, ""); // Remove comments
		line = line.trim(); // Remove leading/trailing whitespace
		line = line.replaceAll(Lang.ARG_DELIMITER + "\\s*", Lang.ARG_DELIMITER + Lang.STD_DELIMITER); // Format args
		line = line.replaceAll("\\s+", Lang.STD_DELIMITER); // Replace any whitespace with 1 space
		
		// Parse labels that are on the same line as the first instruction
		if (line.contains(Lang.LABEL_DELIMITER)) {
			String[] labelSplit = line.split(Lang.LABEL_DELIMITER);
			// Split labels from their first instruction, if needed
			if (labelSplit.length >= 1)
				cleaned.add(labelSplit[0] + Lang.LABEL_DELIMITER);
			if (labelSplit.length == 2)
				cleaned.add(labelSplit[1].trim());
		}
		else if (!line.equals(""))
			cleaned.add(line);

		return cleaned;
	}


	private List<String> assembleLines(List<String> assembly) {
		List<String> assembled = new ArrayList<>();
		assembled.add("// Assembled by MC-Assembler version " + MCAsm.VERSION);

		// Assemble directives (including labels)
		this.pc = 0x00;
		for (int i = 0; i < assembly.size(); i++) {
			MCAsm.setCurrentLineNum(i + 1);
			List<String> lines = this.cleanLine(assembly.get(i));
			if (lines.size() == 0)
				continue;

			for (String line : lines) {
				MCAsm.setCurrentLine(line);
				
				if (line.matches(Lang.LABEL_REGEX) || line.matches(Lang.DIRECTIVE_REGEX)) {
					String directive = this.assembleDirective(line);
					// Some directives do not have any assembled output (.loc for example, which is inferred by any
					// disassembler if the pc value suddenly skips), so do not blindly add the assembled directives.
					if (directive != null)
						assembled.add(directive);
				}
				else
					this.pc++; // This would have been a regular instruction
			}
		}

		// Assemble instructions
		this.pc = 0x00;
		for (int i = 0; i < assembly.size(); i++) {
			MCAsm.setCurrentLineNum(i + 1);
			List<String> lines = this.cleanLine(assembly.get(i));
			if (lines.size() == 0)
				continue;

			for (String line : lines) {
				MCAsm.setCurrentLine(line);
			
				// Labels are added to a constant list, so they do not need to be reparsed.
				if (line.matches(Lang.LABEL_REGEX))
					continue;
				
				// Directives must be assembled again to change the assembly environment (e.g. .loc directives).
				if (line.matches(Lang.DIRECTIVE_REGEX)) {
					String directive = this.assembleDirective(line);
					if (directive != null)
						assembled.add(directive);
				}
				else {
					String instruction = this.assembleInstruction(line);
					if (instruction != null) {
						// Split instruction lines by newlines, which may be included if pragmas (e.g. imm) are added
						for (String subInstruction : instruction.split("\n"))
							assembled.add(subInstruction);
					}
					this.pc++;
				}
			}
		}

		return assembled;
	}


	private String assembleInstruction(String line) {
		line = line.replaceAll(Lang.ARG_DELIMITER, ""); // Remove commas to allow splitting by the remaining spaces
		String[] split = line.split(Lang.STD_DELIMITER);

		String opcode = split[0];
		String[] args = new String[split.length - 1];
		for (int i = 1; i < split.length; i++)
			args[i - 1] = split[i];

		Instruction instruction = Instruction.getInstance(opcode);
		if (Log.hasNewErrors())
			return null;
		instruction.setRegisterValues(args);
		if (Log.hasNewErrors())
			return null;

		String assembled = "";

		// Add imm directives
		if (instruction.isImmediate()) {
			String immValueStr = args[args.length - 1];
			Imm.Type valueType = immValueStr.startsWith(Lang.HEX_IDENTIFIER) ? Imm.Type.HEX : Imm.Type.DEC;
			Imm.Type defaultType = instruction.getImmDefaultType();

			// Don't switch if:
			//  - valueType == defaultType && (this.immType == valueType || this.immType == Imm.Type.DEFAULT)
			//  - valueType != defaultType && this.immType == valueType
			if (!((valueType == defaultType && (this.immType == valueType || this.immType == Imm.Type.DEFAULT)) ||
				  (valueType != defaultType && this.immType == valueType)))
			{
				this.immType = valueType;

				String immTypeStr = this.immType.name().toLowerCase();
				Imm imm = Imm.getInstance(immTypeStr);
				assembled += imm.toAssembledString() + "\n";
			}
		}
		
		assembled += Log.toHexString(this.pc, 1) + ": " + instruction.toAssembledString();
		if (Log.hasNewErrors())
			return null;
		return assembled;
	}
	

	private String assembleDirective(String line) {
		// Process labels separately
		if (line.matches(Lang.LABEL_REGEX)) {
			String[] split = line.split(Lang.LABEL_DELIMITER);
			String name = split[0];

			Label label = new Label(name, this.pc);
			if (Log.hasNewErrors())
				return null;
			return label.toAssembledString();
		}

		
		// Process other directives
		line = line.replaceAll(Lang.ARG_DELIMITER, ""); // Remove commas to split by the remaining spaces
		String[] split = line.split(Lang.STD_DELIMITER);
		
		// Allocate the primary type (.loc, .pragma, etc.) for the directive. And optionally attempt to
		// allocate a subtype (imm for .pragma, etc.)
		String type = split[0];
		String subtype = null;
		if (split.length >= 2)
			subtype = split[1];

		// Process each directive type and subtype
		switch (type) {
		case Lang.DIRECTIVE_IDENTIFIER + "loc":
			Loc loc = new Loc((split.length == 2) ? split[1] : null, this.pc);
			if (Log.hasNewErrors())
				return null;
			this.pc = loc.getLine();
			return loc.toAssembledString();
			
		case Lang.DIRECTIVE_IDENTIFIER + "pragma":
			MCAsm.incrementCurrentToken();
			
			switch (subtype) {
			case "imm":
				Imm imm = Imm.getInstance((split.length == 3) ? split[2] : null);
				if (Log.hasNewErrors())
					return null;
				return imm.toAssembledString();
			default:
			    Log.format(Log.ERROR, "Assembler", "unexpected pragma subtype: " + subtype);
			}
				
		default:
			Log.format(Log.ERROR, "Assembler", "unexpected directive type: " + type);
			return null;
		}
	}

}
