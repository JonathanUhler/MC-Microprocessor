import java.io.File;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParameterException;
import grammar.MCMicroprocessor8BitLexer;
import grammar.MCMicroprocessor8BitParser;


@Command(name = "mcc", version = "mcc " + MCCompiler.VERSION)
public class MCCompiler implements Runnable {

	/** The current verion string for the program. */
	public static final String VERSION = "1.0.0";
	

	/** Command line interface specification, used to handle errors. */
	@Spec
	private CommandSpec spec;


	@Parameters(paramLabel = "SOURCE_FILES")
	private String[] sourcePaths;

	/** Whether to display help information. Help is handled automatically by picocli. */
	@Option(names = {"-h", "--help"}, usageHelp = true,
			description = "Display this message and exit.")
	private boolean help;

	/** Whether to display version information. Version is handled automatically by picocli. */
	@Option(names = {"-V", "--version"}, versionHelp = true,
			description = "Display the version and exit.")
	private boolean version;

	@Option(names = {"-S"},
			description = "Only run preprocess and compilation steps (generates `.asm' files).")
	private boolean stopAtAsm;


	public static void main(String[] args) {
		new CommandLine(new MCCompiler()).execute(args);
	}


	private MCCompiler() { }


	@Override
	public void run() {
		if (this.sourcePaths == null)
			throw new ParameterException(this.spec.commandLine(), "no source files");

		for (String sourcePath : this.sourcePaths)
			this.compileSourceFile(sourcePath);
	}


	public void compileSourceFile(String path) {
		File inFile = new File(path);
		String fileName = FileManager.name(inFile);

		String source = null;
		try {
			source = FileManager.load(inFile);
		}
		catch (ParseCancellationException e) {
			throw new ParameterException(this.spec.commandLine(), e.getMessage());
		}

		String output = this.compile(source);
		File outFile = new File(inFile.getParent() + "/" +
								fileName + "." +
								(this.stopAtAsm ? "asm" : "bin"));
		try {
			FileManager.save(output, outFile, true);
		}
		catch (ParseCancellationException e) {
			throw new ParameterException(this.spec.commandLine(), e.getMessage());
		}
	}


	public String compile(String source) {
		CodePointCharStream stream = CharStreams.fromString(source);
		MCMicroprocessor8BitLexer lexer = new MCMicroprocessor8BitLexer(stream);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MCMicroprocessor8BitParser parser = new MCMicroprocessor8BitParser(tokens);

		//RuleContext tree = parser.program();

		String assembly = "";
		if (this.stopAtAsm)
			return assembly;
		return null;
	}

}
