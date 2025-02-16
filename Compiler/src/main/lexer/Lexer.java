package lexer;


import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer {

    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();

        while (input.length() > 0) {
            boolean foundToken = false;
            for (Token.Type tokenType : Token.Type.values()) {
                Pattern pattern = Pattern.compile("^" + tokenType.regex);
                Matcher matcher = pattern.matcher(input);
                if (!matcher.find()) {
                    continue;
                }

                String match = matcher.group();
                input = input.substring(match.length());
                if (!tokenType.ignorable) {
                    tokens.add(new Token(tokenType, match));
                }
                foundToken = true;
                break;
            }

            if (!foundToken) {
                System.out.println("syntax error, input = " + input);  // MARK: do something here
                System.exit(1);
            }
        }

        return tokens;
    }

}
