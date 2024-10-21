import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import assembler.Assembler;
/*import assembler.Disassembler;*/


/**
 * Command line entry point to the assembler/disassembler program. Each of these two primary
 * functions can be accessed through the {@code asm} and {@code dis} sub-commands.
 *
 * @author Jonathan Uhler
 */
@Command(name = "mcasm",
         subcommands = {Assembler.class/*, Disassembler.class*/},
         version = "mcasm " + MCAssembler.VERSION)
public class MCAssembler implements Runnable {

    /** The current verion string for the program. */
    public static final String VERSION = "1.0.0";


    @Spec
    private CommandSpec spec;


    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this message and exit.")
    private boolean help;
    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Show version and exit.")
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
