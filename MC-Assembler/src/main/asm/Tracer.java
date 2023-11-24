package asm;


import java.util.logging.Formatter;
import java.util.logging.LogRecord;


/**
 * Stores information about the status of file processing (e.g. line and token number). When errors
 * occur, they can be registered with an object of this class using the {@code addError} method.
 * <p>
 * Error messages will be formatted richly with the line number, line, and token number that caused
 * the error.
 * <p>
 * An example error output might look like:
 * <pre>
 * {@code
 *     17: too many arguments for instruction
 *         mul r1 r2 r3 r4
 *                      ^
 *     1 error
 * }
 * </pre>
 * <p>
 * The above error is read as: a "too many arguments" error occured at the fifth token ("r4") on
 * the 17th line of the input file.
 *
 * @author Jonathan Uhler
 */
public class Tracer {

	/**
	 * Custom log formatter.
	 *
	 * @author Jonathan Uhler
	 */
	public static class LogFormatter extends Formatter {

		@Override
		public String format(LogRecord record) {
			return record.getLevel() +
				" (" + record.getSourceClassName() + ")\t" +
				record.getMessage() + "\r\n";
		}

	}
	

	/** The maximum number of errors that will be stored at once. */
	public static final int MAX_ERRORS = 100;

	/** The current line number being processed. */
	private int lineNum;
	/** The current line being processed. */
	private String line;
	/** The current token number being processed. */
	private int tokenNum;

	/** Whether new errors are available. */
	private boolean hasErrors;
	/** The number of errors currently stored by the tracer. */
	private int numErrors;
	/** The list of error messages stored by the tracer. */
	private String[] errors;


	/**
	 * Constructs a new {@code Tracer} object.
	 */
	public Tracer() {
		this.lineNum = 0;
		this.line = "";
		this.tokenNum = 0;

		this.hasErrors = false;
		this.numErrors = 0;
		this.errors = new String[Tracer.MAX_ERRORS];
	}


	/**
	 * Updates the current line being processed by the assembler/disassembler.
	 * <p>
	 * Calling this method resets the token number to zero.
	 *
	 * @param line  the line currently being processed.
	 */
	public void setLine(String line) {
		this.line = line;
		this.tokenNum = 0;
	}


	/**
	 * Updates the current line number being processed.
	 *
	 * @param lineNum  the line number being processed.
	 */
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}


	/**
	 * Increments the token number being processed.
	 */
	public void incrementToken() {
		this.tokenNum++;
	}


	/**
	 * Returns whether this tracer object has new error messages since the last time this method
	 * was called. Upon each call, the {@code hasErrors} flag is set to false, although the
	 * number of total errors can be viewed at any time by calling {@code getNumErrors}.
	 *
	 * @return whether new errors have been added since the last call to this method.
	 *
	 * @see getNumErrors
	 */
	public boolean hasNewErrors() {
		boolean temp = this.hasErrors;
		this.hasErrors = false;
		return temp;
	}


	/**
	 * Returns the total number of errors seen by this tracer.
	 * <p>
	 * The number of errors returned by this method may exceed {@code Tracer.MAX_ERRORS} and
	 * may be larger than the number of error <i>messages</i> stored (that is, not all errors
	 * added will be kept as messages, but will still be acknowledged by an increment in
	 * {@code numErrors}).
	 *
	 * @return the total number of errors seen by this tracer.
	 */
	public int getNumErrors() {
		return this.numErrors;
	}


	/**
	 * Adds an error message to this tracer.
	 * <p>
	 * The message will only be stored if {@code getNumErrors < Tracer.MAX_ERRORS}.
	 * <p>
	 * Each message will have information about the erroring line and token appended to the
	 * provided error description. This line information is taken from the current state of the
	 * line/token instance variables of this class, which can be incremented with {@code setLine},
	 * {@code setLineNum}, and {@code incrementToken}.
	 *
	 * @param message  a message describing the error.
	 */
	public void addError(String message) {
		this.hasErrors = true;
		this.numErrors++;
		if (this.numErrors >= this.errors.length)
			return;

		// Add a "^" character to point to roughly where the error occured in the line
		String[] tokens = this.line.split(Language.STD_DELIMITER);
		String errorPointer = "";
		for (int i = 0; i < tokens.length; i++) {
			if (i == this.tokenNum) // Stop adding spaces if this is the error-causing token
				break;

			for (int j = 0; j < tokens[i].length(); j++)
				errorPointer += " "; // Add spaces for the length of the token that is fine
			errorPointer += " "; // Space between tokens
		}
		message = this.lineNum + ": " + message; // Add line number
		message += "\n\t\t" + this.line + "\n\t\t" + errorPointer + "^"; // Add "^" symbol

		// Add the error and update error status information
		this.errors[this.numErrors - 1] = message;
	}


	/**
	 * Prints all errors kept by this trace (up to {@code Tracer.MAX_ERRORS} errors). Errors
	 * are printed to stderr.
	 */
	public void dumpErrors() {
		if (this.numErrors == 0)
			return;

		int numErrorsDisplayed = Math.min(this.numErrors, Tracer.MAX_ERRORS);
		for (int i = 0; i < numErrorsDisplayed; i++) {
			String error = this.errors[i];
			if (error != null)
				System.err.println(error);
		}

		System.err.println(numErrorsDisplayed + " " +
						   (numErrorsDisplayed == 1 ? "error" : "errors"));
		if (this.numErrors > Tracer.MAX_ERRORS)
			System.err.println("Only showing the first " + Tracer.MAX_ERRORS +
							   " errors, of " + this.numErrors + " total");
	}


	/**
	 * Clears all error information from this tracer. After a call to this method,
	 * {@code getNumErrors} will return zero, {@code hasNewErrors} will return false, and
	 * {@code dumpErrors} will not print anything to stderr.
	 */
	public void clearErrors() {
		this.hasErrors = false;
		this.numErrors = 0;
		this.errors = new String[Tracer.MAX_ERRORS];
	}

}
