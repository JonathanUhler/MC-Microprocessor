import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import grammar.MCMicroprocessor8BitLexer;
import grammar.MCMicroprocessor8BitParser;


public class ConfTest {

	public static MCMicroprocessor8BitLexer lexer;
	public static MCMicroprocessor8BitParser parser;
	

	public static void setUpParser(String input) {
		CodePointCharStream stream = CharStreams.fromString(input);
		ConfTest.lexer = new MCMicroprocessor8BitLexer(stream);
		ConfTest.lexer.removeErrorListeners();
		ConfTest.lexer.addErrorListener(new ThrowingErrorListener());
		ConfTest.parser = new MCMicroprocessor8BitParser(new CommonTokenStream(lexer));
		ConfTest.parser.removeErrorListeners();
		ConfTest.parser.addErrorListener(new ThrowingErrorListener());
	}

}
