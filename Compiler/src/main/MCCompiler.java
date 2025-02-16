import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;

// MARK: temp while debugging
import java.util.List;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import parser.ASTNode;


public class MCCompiler {

    public static void main(String[] args) {
        String input = """
            (4 * 3 + 2)
            /*
        subroutine f(x, y, z, alpha) {
            if (x | y) {
                z;
            }
            else {
                alpha;
            }
        }
            */
        """;
        List<Token> tokens = Lexer.tokenize(input);

        Parser parser = new Parser(tokens);
        ASTNode<String> ast = parser.primaryExpression();
        if (!parser.isDone()) {
            System.out.println("unparsed tokens: " + tokens);
            throw new RuntimeException("parsing did not finish");
        }
        else {
            System.out.println("parser finished successfully");
        }

        System.out.println(ast);
    }

}
