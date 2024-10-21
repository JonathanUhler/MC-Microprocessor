package assembler;


import java.util.List;


public class Tracer {

    public static Tracer INSTANCE = new Tracer();

    public static final int MAX_ERRORS = 100;


    private List<String> tokens;
    private int lineIndex;
    private int tokenIndex;

    private boolean hasErrors;
    private int numErrors;
    private String[] errors;


    private Tracer() {
        this.tokens = null;
        this.lineIndex = 0;
        this.tokenIndex = 0;

        this.hasErrors = false;
        this.numErrors = 0;
        this.errors = new String[Tracer.MAX_ERRORS];
    }


    public void setTokens(List<String> tokens) {
        if (tokens == null) {
            throw new NullPointerException("tokens cannot be null");
        }

        this.tokens = tokens;
        this.tokenIndex = 0;
    }


    public void setLineIndex(int lineIndex) {
        if (lineIndex < 0) {
            throw new IllegalArgumentException("lineIndex " + lineIndex + " out of bounds");
        }
        this.lineIndex = lineIndex;
    }


    public void setTokenIndex(int tokenIndex) {
        if (tokenIndex < 0) {
            throw new IllegalArgumentException("tokenIndex " + tokenIndex + " out of bounds");
        }
        this.tokenIndex = tokenIndex;
    }


    public void incrementLine() {
        this.lineIndex++;
    }


    public void incrementToken() {
        this.tokenIndex++;
    }


    public boolean hasNewErrors() {
        boolean temp = this.hasErrors;
        this.hasErrors = false;
        return temp;
    }


    public int getNumErrors() {
        return this.numErrors;
    }


    public void addError(String message) {
        if (message == null) {
            throw new NullPointerException("message cannot be null");
        }

        this.hasErrors = true;
        this.numErrors++;
        if (this.numErrors >= this.errors.length) {
            return;
        }
        if (this.tokens == null) {
            this.errors[this.numErrors - 1] = message;
            return;
        }

        StringBuilder error = new StringBuilder();
        String line = String.join(" ", this.tokens);
        int lineNum = this.lineIndex + 1;

        error.append(lineNum + ": " + message);
        error.append("\n\t\t" + line + "\n\t\t");
        for (int i = 0; i < this.tokens.size(); i++) {
            if (i == this.tokenIndex) {
                break;
            }
            error.append(" ".repeat(this.tokens.get(i).length() + 1));
        }
        error.append("^");

        this.errors[this.numErrors - 1] = error.toString();
    }


    public void dumpErrors() {
        if (this.numErrors == 0) {
            return;
        }

        int numErrorsDisplayed = Math.min(this.numErrors, Tracer.MAX_ERRORS);
        for (int i = 0; i < numErrorsDisplayed; i++) {
            System.err.println(this.errors[i]);
        }

        System.err.println(numErrorsDisplayed + " error" + (numErrorsDisplayed == 1 ? "" : "s"));
        if (this.numErrors > Tracer.MAX_ERRORS) {
            System.err.println("Only showing the first " + Tracer.MAX_ERRORS + " errors, of " +
                               this.numErrors + " total");
        }
    }


    public void clearErrors() {
        this.hasErrors = false;
        this.numErrors = 0;
        this.errors = new String[Tracer.MAX_ERRORS];
    }

}
