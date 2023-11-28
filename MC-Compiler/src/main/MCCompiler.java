import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParameterException;


@Command(name = "mcc", version = "mcc " + MCCompiler.VERSION)
public class MCCompiler implements Runnable {

	/** The current verion string for the program. */
	public static final String VERSION = "1.0.0";
	

	/** Command line interface specification, used to handle errors. */
	@Spec
	private CommandSpec spec;


	@Parameters(paramLabel = "SOURCE_FILES")
	private String[] sourceFiles;

	/** Whether to display help information. Help is handled automatically by picocli. */
	@Option(names = {"-h", "--help"}, usageHelp = true,
			description = "Display this message and exit.")
	private boolean help;

	/** Whether to display version information. Version is handled automatically by picocli. */
	@Option(names = {"-V", "--version"}, versionHelp = true,
			description = "Display the version and exit.")
	private boolean version;


	public static void main(String[] args) {
		new CommandLine(new MCCompiler()).execute(args);
	}


	private MCCompiler() { }


	@Override
	public void run() {
		if (this.sourceFiles == null)
			throw new ParameterException(this.spec.commandLine(), "no source files");
		
		for (String sourceFile : this.sourceFiles)
			System.out.println(sourceFile);
	}

}



/*
import java.io.*;
import org.antlr.v4.runtime.*;
import grammar.*;

public class MCCompiler {
    public static void main(String args[]) throws Exception {
		MCMicroprocessor8BitLexer lexer = new MCMicroprocessor8BitLexer(new ANTLRInputStream(System.in));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MCMicroprocessor8BitParser parser = new MCMicroprocessor8BitParser(tokens);
		System.out.println(parser.var_def_bool());

		Token tok = lexer.nextToken();
		while (tok.getType() != Token.EOF) {
			System.out.println("Lexeme='" + tok.getText() + "', Token='" + tok.getType() + "'");
			tok = lexer.nextToken();
		}
	}
}
*/
