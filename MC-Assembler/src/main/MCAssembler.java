import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import asm.Assembler;
import asm.Disassembler;


/**
 * Command line entry point to the assembler/disassembler program. Each of these two primary
 * functions can be accessed through the {@code asm} and {@code dis} sub-commands.
 *
 * @author Jonathan Uhler
 */
@Command(name = "mcasm", subcommands = {Assembler.class, Disassembler.class},
		 version = "mcasm " + MCAssembler.VERSION)
public class MCAssembler implements Runnable {

	/** The current verion string for the program. */
	public static final String VERSION = "2.2.0";
	

	/** Command line interface specification, used to handle errors. */
	@Spec
	private CommandSpec spec;


	/** Whether to display help information. Help is handled automatically by picocli. */
	@Option(names = {"-h", "--help"}, usageHelp = true,
			description = "Display this message and exit.")
	private boolean help;

	/** Whether to display version information. Version is handled automatically by picocli. */
	@Option(names = {"-V", "--version"}, versionHelp = true,
			description = "Display the version and exit.")
	private boolean version;
	

	/**
	 * Entry point for the CLI program. This method is responsible for acting on command line
	 * arguments.
	 *
	 * @param args  command line arguments.
	 */
	public static void main(String[] args) {
	    new CommandLine(new MCAssembler()).execute(args);
	}


	/**
	 * Runs this class as a CLI command. Because a sub-command must be specified, a call to
	 * this method will always throw a {@code ParameterException}.
	 *
	 * @throws ParameterException  always.
	 */
	@Override
	public void run() {
	    throw new ParameterException(spec.commandLine(), "Missing required subcommand");
	}

}
