package parser;


import java.util.List;
import lexer.Token;


public class Parser {

    private List<Token> tokens;
    private Token current;


    public Parser(List<Token> tokens) {
        if (tokens == null) {
            throw new NullPointerException("tokens cannot be null");
        }

        this.tokens = tokens;
        this.nextToken();
    }


    public boolean isDone() {
        return this.tokens.size() == 0;
    }


    private void nextToken() {
        if (this.tokens.size() == 0) {
            this.current = new Token(Token.Type.EOF, "<EOF>");
            return;
        }
        this.current = this.tokens.remove(0);
    }


    private ASTNode<String> accept(Token.Type type) {
        if (this.current.getType() == type) {
            ASTNode<String> terminal = new ASTNode<>(this.current.toString());
            this.nextToken();
            return terminal;
        }
        return null;
    }


    private ASTNode<String> expect(Token.Type type) {
        ASTNode<String> node;
        if ((node = this.accept(type)) != null) {
            return node;
        }
        throw new IllegalStateException("expect: expected " + type + ", found " + this.current);
    }


    public ASTNode<String> expression() {
        ASTNode<String> node = new ASTNode<>("[expression]");
        if (node.addChild(this.primaryExpression()) != null) {
            node.addChild(this.expressionTail());
        }
        else if (node.addChild(this.accept(Token.Type.PLUS)) != null) {
            node.addChild(this.expression());
            node.addChild(this.expressionTail());
        }
        else if (node.addChild(this.accept(Token.Type.MINUS)) != null) {
            node.addChild(this.expression());
            node.addChild(this.expressionTail());
        }
        else if (node.addChild(this.accept(Token.Type.BITWISE_NOT)) != null) {
            node.addChild(this.expression());
            node.addChild(this.expressionTail());
        }
        else {
            return null;
        }
        return node;
    }


    private ASTNode<String> expressionTail() {
        ASTNode<String> node = new ASTNode<>("[expression_tail]");
        if (node.addChild(this.accept(Token.Type.TIMES)) != null ||
            node.addChild(this.accept(Token.Type.DIVIDE)) != null ||
            node.addChild(this.accept(Token.Type.PLUS)) != null ||
            node.addChild(this.accept(Token.Type.MINUS)) != null ||
            node.addChild(this.accept(Token.Type.LESS_THAN)) != null ||
            node.addChild(this.accept(Token.Type.GREATER_THAN)) != null ||
            node.addChild(this.accept(Token.Type.LESS_THAN_EQUAL)) != null ||
            node.addChild(this.accept(Token.Type.GREATER_THAN_EQUAL)) != null ||
            node.addChild(this.accept(Token.Type.EQUAL_TO)) != null ||
            node.addChild(this.accept(Token.Type.NOT_EQUAL_TO)) != null ||
            node.addChild(this.accept(Token.Type.BITWISE_AND)) != null ||
            node.addChild(this.accept(Token.Type.BITWISE_XOR)) != null ||
            node.addChild(this.accept(Token.Type.BITWISE_OR)) != null)
        {
            node.addChild(this.expression());
            node.addChild(this.expressionTail());
        }
        return node;  // Epsilon transition
    }


    public ASTNode<String> primaryExpression() {
        ASTNode<String> node = new ASTNode<>("[primary_expression]");
        if (node.addChild(this.accept(Token.Type.IDENTIFIER)) != null) {
            ;
        }
        else if (node.addChild(this.accept(Token.Type.DEC_NUMBER)) != null) {
            ;
        }
        else if (node.addChild(this.accept(Token.Type.HEX_NUMBER)) != null) {
            ;
        }
        else if (this.accept(Token.Type.L_PAREN) != null) {
            node.addChild(this.expression());
            this.expect(Token.Type.R_PAREN);
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> statement() {
        ASTNode<String> node = new ASTNode<>("[statement]");
        if (node.addChild(this.compoundStatement()) != null) {
            ;
        }
        else if (node.addChild(this.expressionStatement()) != null) {
            ;
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> compoundStatement() {
        ASTNode<String> node = new ASTNode<>("[statement]");
        if (this.accept(Token.Type.L_BRACKET) != null) {
            if (this.accept(Token.Type.R_BRACKET) != null) {
                ;
            }
            else if (node.addChild(this.statementList()) != null) {
                this.expect(Token.Type.R_BRACKET);
            }
            else {
                return null;
            }
        }
        else if (node.addChild(this.selectionStatement()) != null) {
            ;
        }
        else if (node.addChild(this.iterationStatement()) != null) {
            ;
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> statementList() {
        ASTNode<String> node = new ASTNode<>("[statement_list]");
        if (node.addChild(this.statement()) != null) {
            node.addChild(this.statementListTail());
        }
        else {
            return null;
        }
        return node;
    }


    private ASTNode<String> statementListTail() {
        ASTNode<String> node = new ASTNode<>("[statement_list_tail]");
        if (node.addChild(this.statement()) != null) {
            node.addChild(this.statementListTail());
        }
        return node;  // Epsilon transition
    }


    public ASTNode<String> expressionStatement() {
        ASTNode<String> node = new ASTNode<>("[expression_statement]");
        if (this.accept(Token.Type.SEMICOLON) != null) {
            ;
        }
        else if (node.addChild(this.expression()) != null) {
            this.expect(Token.Type.SEMICOLON);
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> selectionStatement() {
        ASTNode<String> node = new ASTNode<>("[selection_statement]");
        if (node.addChild(this.ifElseStatement()) != null) {
            ;
        }
        else if (node.addChild(this.ifStatement()) != null) {
            ;
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> ifStatement() {
        ASTNode<String> node = new ASTNode<>("[if_statement]");
        if (this.accept(Token.Type.IF) != null) {
            this.expect(Token.Type.L_PAREN);
            node.addChild(this.expression());
            this.expect(Token.Type.R_PAREN);
            node.addChild(this.statement());
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> ifElseStatement() {
        ASTNode<String> node = new ASTNode<>("[if_else_statement]");
        if (node.addChild(this.ifStatement()) != null) {
            this.expect(Token.Type.ELSE);
            node.addChild(this.statement());
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> iterationStatement() {
        ASTNode<String> node = new ASTNode<>("[iteration_statement]");
        if (node.addChild(this.whileStatement()) != null) {
            ;
        }
        else if (node.addChild(this.doWhileStatement()) != null) {
            ;
        }
        else if (node.addChild(this.forStatement()) != null) {
            ;
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> whileStatement() {
        ASTNode<String> node = new ASTNode<>("[while_statement]");
        if (this.accept(Token.Type.WHILE) != null) {
            this.expect(Token.Type.L_PAREN);
            node.addChild(this.expression());
            this.expect(Token.Type.R_PAREN);
            node.addChild(this.statement());
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> doWhileStatement() {
        ASTNode<String> node = new ASTNode<>("[do_while_statement]");
        if (this.accept(Token.Type.DO) != null) {
            node.addChild(this.statement());
            this.expect(Token.Type.WHILE);
            this.expect(Token.Type.L_PAREN);
            node.addChild(this.expression());
            this.expect(Token.Type.R_PAREN);
            this.expect(Token.Type.SEMICOLON);
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> forStatement() {
        ASTNode<String> node = new ASTNode<>("[for_statement]");
        if (this.accept(Token.Type.FOR) != null) {
            this.expect(Token.Type.L_PAREN);
            node.addChild(this.expressionStatement());
            node.addChild(this.expressionStatement());
            if (node.addChild(this.expression()) != null) {
                ;
            }
            this.expect(Token.Type.R_PAREN);
            node.addChild(this.statement());
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> subroutine() {
        ASTNode<String> node = new ASTNode<>("[subroutine]");
        if (this.accept(Token.Type.SUBROUTINE) != null) {
            node.addChild(this.expect(Token.Type.IDENTIFIER));
            this.expect(Token.Type.L_PAREN);
            if (node.addChild(this.argumentList()) != null) {
                ;
            }
            this.expect(Token.Type.R_PAREN);
            node.addChild(this.statement());
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> argumentList() {
        ASTNode<String> node = new ASTNode<>("[argument_list]");
        if (node.addChild(this.expression()) != null) {
            node.addChild(this.argumentListTail());
        }
        else {
            return null;
        }
        return node;
    }


    public ASTNode<String> argumentListTail() {
        ASTNode<String> node = new ASTNode<>("[argument_list_tail]");
        if (this.accept(Token.Type.COMMA) != null) {
            node.addChild(this.expression());
            node.addChild(this.argumentListTail());
        }
        return node;  // Epsilon transition
    }

}
