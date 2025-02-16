package lexer;


public class Token {

    /**
     * Enumeration of all lexical tokens in the language grammar.
     *
     * Tokens are parsed in terms of precedence from top to bottom of the enumerator.
     */
    public enum Type {
        // Ignored symbols
        WHITESPACE("\\s+", true),
        SINGLE_LINE_COMMENT("\\/\\/.*$", true),
        MULTI_LINE_COMMENT("\\/\\*(.|\n)*\\*\\/", true),
        // Grouping symbols
        L_PAREN("\\("),
        R_PAREN("\\)"),
        L_BRACKET("\\{"),
        R_BRACKET("\\}"),
        COLON(":"),
        SEMICOLON(";"),
        COMMA(","),
        // Relational operators
        LESS_THAN_EQUAL("<="),
        GREATER_THAN_EQUAL(">="),
        EQUAL_TO("=="),
        NOT_EQUAL_TO("!="),
        LESS_THAN("<"),
        GREATER_THAN(">"),
        // Assignment operators
        PLUS_ASSIGN("\\+="),
        MINUS_ASSIGN("-="),
        TIMES_ASSIGN("\\*="),
        DIVIDE_ASSIGN("\\/="),
        ASSIGN("="),
        // Arithmetic operators
        PLUS("\\+"),
        MINUS("-"),
        TIMES("\\*"),
        DIVIDE("\\/"),
        // Bitwise operators
        BITWISE_OR("\\|"),
        BITWISE_XOR("\\^"),
        BITWISE_AND("&"),
        BITWISE_NOT("~"),
        // Language keywords
        IF("if"),
        ELSE("else"),
        FOR("for"),
        WHILE("while"),
        DO("do"),
        SWITCH("switch"),
        BREAK("break"),
        CONTINUE("continue"),
        SUBROUTINE("subroutine"),
        // Built-in functions
        FREE("free"),
        // Programmer symbols
        IDENTIFIER("[_a-zA-Z][_a-zA-Z0-9]*"),
        HEX_NUMBER("0x[0-9a-fA-F]+"),
        DEC_NUMBER("0|[1-9][0-9]*"),
        EOF("");

        public final String regex;
        public final boolean ignorable;

        private Type(String regex) {
            this(regex, false);
        }

        private Type(String regex, boolean ignorable) {
            this.regex = regex;
            this.ignorable = ignorable;
        }
    }


    private Type type;
    private String value;


    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }


    public Type getType() {
        return this.type;
    }


    public String getValue() {
        return this.value;
    }


    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Token)) {
            return false;
        }

        Type otherType = ((Token) other).getType();
        return this.type == otherType;
    }


    @Override
    public String toString() {
        return this.type + "('" + this.value + "')";
    }

}
