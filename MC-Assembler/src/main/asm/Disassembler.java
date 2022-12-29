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


@Command(name = "dis")
public class Disassembler implements Runnable {

	// CLI options
	@Parameters(paramLabel = "IN_FILE", description = "Input file with assembled instructions.")
	private String inPath;

	@Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this message and exit.")
	private boolean help;

	@Option(names = {"-f", "--force"},
			description = "Overwrites existing content in the output file, if -o is used. If not specified, appends.")
	private boolean force;

	@Option(names = {"-o", "--outfile"}, paramLabel = "<OUT_FILE>",
			description = "Specify the location of an output file. If not specified, output is printed to stdout.")
	private String outPath;


	// Disassembler information
	private int pc; // Program counter value
	private Imm.Type immType; // Current immediate type (hex, dec, or default)


	// ----------------------------------------------------------------------------------------------------
	// public void run
	//
	// Defacto constructor, called by the picocli command-line interface by MCAsm::main. The execution of
	// this method by the CLI also instantiates any annotated instance varaibles. Other instance variables
	// and routines are set up like a normal constructor.
	//
	private Disassembler() { }
	
	@Override
	public void run() {
		this.pc = -1;
		this.immType = Imm.Type.DEFAULT;

		// Load and disassemble lines
		List<String> assembled = Log.loadLines(this.inPath);
		List<String> assembly = this.disassembleLines(assembled);

		// Check for errors
		if (Log.getNumErrors() > 0) {
			Log.dumpErrors();
			System.exit(Log.ERROR);
		}

		// Write to output file or print to stdout
		if (this.outPath != null)
			Log.saveLines(assembly, this.outPath, this.force);
		else {
			for (String line : assembly)
				Log.stdout(Log.INFO, "Disassembler", line);
		}
	}
	// end: public void run


	private String cleanLine(String line) {
		line = line.replaceAll(Lang.COMMENT_REGEX, ""); // Remove comments
		line = line.trim(); // Remove leading/trailing whitespace
	    line = line.replaceAll(Lang.ARG_DELIMITER + "\\s*", Lang.ARG_DELIMITER + Lang.STD_DELIMITER); // Format args
		line = line.replaceAll("\\s+", Lang.STD_DELIMITER); // Replace any whitespace with 1 space

		return line;
	}


	private List<String> disassembleLines(List<String> assembled) {
		List<String> assembly = new ArrayList<>();
		assembly.add("// Disassembled by MC-Assembler version " + MCAsm.VERSION);

		// Start pc at -1 to allow for pc=0x00 to pass the chronology check in disassembleInstruction
		this.pc = -1;
		// Parse lines
		for (int i = 0; i < assembled.size(); i++) {
			// Preprocess line
			MCAsm.setCurrentLineNum(i + 1);
			String line = this.cleanLine(assembled.get(i));
			if (line.length() == 0)
				continue;
			MCAsm.setCurrentLine(line);

			// Parse line
			if (line.matches(Lang.DIRECTIVE_REGEX))
				// Act upon directives (chaning pc, imm type, etc) but don't add them (labels are added later)
				this.disassembleDirective(line);
			else {
				String instruction = this.disassembleInstruction(line);
				if (instruction != null) {
					// Split instruction lines by newlines, which may be included if directives (e.g. loc) are added
					for (String subInstruction : instruction.split("\n"))
						assembly.add("\t" + subInstruction);
				}
			}

			// Check if any labels need to be inserted at this pc value
			String labelName = Label.getAliasName(this.pc);
			if (labelName != null)
				assembly.add(assembly.size() - 1, labelName + Lang.LABEL_DELIMITER);
		}

		// Return lines
		return assembly;
	}


	private String disassembleInstruction(String line) {
		// Setup the line
		line = line.replaceAll(Lang.PC_DELIMITER, ""); // Remove colon to allow splitting by space
		String[] split = line.split(Lang.STD_DELIMITER);
		if (split.length != 2) {
			MCAsm.incrementCurrentToken();
			Log.format(Log.ERROR, "Disassembler", "illegal expression, expected two colon-separated components");
			return null;
		}

		// Process string components
		String pcStr = split[0];
		String instructionStr = split[1];
		if (!pcStr.startsWith(Lang.HEX_IDENTIFIER) || !instructionStr.startsWith(Lang.HEX_IDENTIFIER)) {
			if (!instructionStr.startsWith(Lang.HEX_IDENTIFIER))
				MCAsm.incrementCurrentToken();
			Log.format(Log.ERROR, "Disassembler", "illegal start of expression, value should be hex");
			return null;
		}

		// Parse string components as integers
		int pcInt;
		int instructionInt;
		try {
			pcInt = Integer.parseInt(pcStr.substring(2), 16);
			instructionInt = Integer.parseInt(instructionStr.substring(2), 16);

			// Check for pc chronology
			if (pcInt <= this.pc) {
				Log.format(Log.ERROR, "Disassembler", "illegal start of expression, pc values must be chronological;" +
						   " expected >" + this.pc + ", found " + pcInt);
				return null;
			}
		}
		catch (NumberFormatException e) {
			Log.format(Log.ERROR, "Disassembler", "unexpected type, value should be hex");
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
		int opcode = instructionInt >> Lang.OPCODE_LOW;
		int argsMask = ((1 << (Lang.OPCODE_LOW - Lang.IMM_LOW)) - 1) << Lang.IMM_LOW;
		int args = (instructionInt & argsMask) >> Lang.IMM_LOW;
		Instruction instruction = Instruction.getInstance(opcode);
		if (Log.hasNewErrors())
			return null;
		instruction.setRegisterValues(args);
		if (Log.hasNewErrors())
			return null;

		assembly += instruction.toAssemblyString(this.immType);
		if (Log.hasNewErrors())
			return null;
		return assembly;
	}


	private String disassembleDirective(String line) {
		// Setup the line
		String[] split = line.split(Lang.STD_DELIMITER);

		String type = split[0];
		String subtype = null;
		if (split.length >= 2)
			subtype = split[1];

		// Process each directive type and subtype
		switch (type) {
		case Lang.DIRECTIVE_IDENTIFIER + "label":
			MCAsm.incrementCurrentToken();
			
			if (split.length != 3) {
				MCAsm.incrementCurrentToken();
				Log.format(Log.ERROR, "Diassembler", "illegal expression, label directives must have a name and pc");
				return null;
			}
			
			String name = split[1];
			String pcStr = split[2];
			int pcInt;
			try {
				pcInt = Integer.parseInt(pcStr.substring(2), 16);
			}
			catch (NumberFormatException e) {
				Log.format(Log.ERROR, "Disassembler", "unexpected label pc value: " + e);
				return null;
			}

			Label label = new Label(name, pcInt);
			if (Log.hasNewErrors())
				return null;
			return label.toAssemblyString();
			
		case Lang.DIRECTIVE_IDENTIFIER + "pragma":
			MCAsm.incrementCurrentToken();

			switch (subtype) {
			case "imm":
				Imm imm = Imm.getInstance((split.length == 3) ? split[2] : null);
				if (Log.hasNewErrors())
					return null;
				this.immType = imm.getType();
				return imm.toAssemblyString();
			default:
				Log.format(Log.ERROR, "Disassembler", "unexpected pragma subtype: " + subtype);
				return null;
			}

		default:
			Log.format(Log.ERROR, "Disassembler", "unexpected directive type: " + type);
			return null;
		}
	}

}
