package util;


import asm.MCAsm;
import asm.Lang;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class Log
//
// Provides logging and output utilities. Can be modified further to allow for more project-specific
// features as needed.
//
// This modification of Log.java includes (list methods/features):
//
//  - Hex/dec string management
//  - File I/O (loading and saving data)
//  - Error tracking
//
public class Log {

	private static final String STDLOG_FILE = "Log.log";


	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int WARN = 2;
	public static final int ERROR = 3;
	public static final int FATAL = 4;
	public static final String[] levelToString = {"DEBUG", "INFO", "WARN", "ERROR", "FATAL"};


	public static final int MAX_ERRORS = 100;
	private static boolean hasNewErrors = false;
	private static int numErrors = 0;
	private static String[] errors = new String[Log.MAX_ERRORS];


	// ====================================================================================================
	// ERROR TRACKING
	//
	public static boolean hasNewErrors() {
		boolean temp = Log.hasNewErrors;
		Log.hasNewErrors = false;
		return temp;
	}

	
	public static int getNumErrors() {
		return Log.numErrors;
	}

	
	public static void dumpErrors() {
		if (numErrors == 0)
			return;

		int numErrorsDisplayed = Math.min(Log.numErrors, Log.MAX_ERRORS);
		for (int i = 0; i < numErrorsDisplayed; i++) {
			String error = Log.errors[i];
			if (error != null)
				System.out.println(error);
		}

		System.out.println(numErrorsDisplayed + " " + (numErrorsDisplayed == 1 ? "error" : "errors"));
		if (Log.numErrors > Log.MAX_ERRORS)
			System.out.println("Only showing the first " + Log.MAX_ERRORS + " errors, of " + Log.numErrors + " total");
	}
	// end: ERROR TRACKING


	// ====================================================================================================
	// FILE AND DATA MANAGEMENT
	//
	public static List<String> loadLines(String inPath) {
		List<String> lines = new ArrayList<>();

		try {
			FileReader fr = new FileReader(inPath);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready())
				lines.add(br.readLine());
			br.close();
		}
		catch (IOException e) {
			Log.stdout(Log.FATAL, "MCAsm", "Cannot read IN_FILE: " + e);
		}

		return lines;
	}


	public static void saveLines(List<String> lines, String outPath) {
		Log.saveLines(lines, outPath, false);
	}

	public static void saveLines(List<String> lines, String outPath, boolean force) {
		try {
			FileWriter fw = new FileWriter(outPath, !force);
			BufferedWriter bw = new BufferedWriter(fw);

			for (String line : lines)
				bw.write(line + "\n");

			bw.close();
		}
		catch (IOException e) {
			Log.stdout(Log.FATAL, "MCAsm", "Cannot write OUT_FILE: " + e);
		}
	}
	
	
	public static String toHexString(int number, int nibbles) {
		String spaceStr = Lang.HEX_IDENTIFIER + String.format("%1$" + nibbles + "s", Integer.toHexString(number));
		return spaceStr.replace(' ', '0');
	}
	// end: FILE AND DATA MANAGEMENT


	// ====================================================================================================
	// public static void gfxmsg
	//
	// Displays a graphical message
	//
	// Arguments--
	//
	//  title:   the title of the message window
	//
	//  message: the message to display
	//
	public static void gfxmsg(String title, Object message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}
	// end: public static void gfxmsg


	// ====================================================================================================
	// public static String format
	//
	// Returns a log message with the formatting of Log.stdout. Does not print to any output buffer
	//
	// Arguments--
	//
	//  level:    the level of the message (Log.[INFO|DEBUG|WARN|ERROR|FATAL]). If the level is
	//            unknown, itwill be replaced by "Log.stdout"
	//
	//  location: the location the message originated from, such as a class or method name
	//
	//  message:  the message to add to the formatted log
	//
	public static String format(int level, String location, String message) {
		// Check if the level is known. If not, replace where the level string would be with "Log.stdout"
		String lineNumber = (level == Log.ERROR || level == Log.FATAL) ? ": " + MCAsm.getCurrentLineNum() + ":" : "";
		String formatted;
		if (level < Log.DEBUG || level > Log.FATAL)
			formatted = "Log.format (" + location + ")" + lineNumber + "  " + message;
		else
			formatted = levelToString[level] + " (" + location + ")" + lineNumber + "  " + message;

		// Handle errors by adding them to the stack to be printed later
		if (level == Log.ERROR || level == Log.FATAL) {
			// Add "^" pointer character to highlight roughly where the error is in the current line
			String currentLine = MCAsm.getCurrentLine();
			String[] currentLineTokens = currentLine.split(Lang.STD_DELIMITER);
			int currentToken = MCAsm.getCurrentToken();

			// Construct a buffer of spaces to shift a "^" character to begin at the right token in the context line,
			// similar to the "^" pointer in java errors
			String spaceBuffer = "";
			for (int i = 0; i < currentLineTokens.length; i++) {
				if (i == currentToken)
					break;
				
				for (int j = 0; j < currentLineTokens[i].length(); j++)
					spaceBuffer += " ";
				spaceBuffer += " ";
			}

			// Add line and "^"
			formatted += "\n\t\t" + currentLine + "\n\t\t" + spaceBuffer + "^";

			// Edit the error buffer as needed
			if (Log.numErrors < Log.MAX_ERRORS)
				Log.errors[Log.numErrors] = formatted;
			Log.numErrors++;
			Log.hasNewErrors = true;
		}

		// Exit on fatal error
		if (level == Log.FATAL) {
			Log.dumpErrors();
			System.out.println("\nProcess exiting gracefully upon a fatal error");
			System.exit(Log.FATAL);
		}

		// Return message
		return formatted;
	}
	// end: public static String format 
	

	// ====================================================================================================
	// public static void stdout
	//
	// Prints to the standard output
	//
	// Arguments--
	//
	//  level:    the level of the message (info, debug, warn, error, fatal). If the level is unknown, it
	//            will be replaced by "Log.stdout"
	//
	//  location: the location the message originated from, such as a class or method name
	//
	//  message:  the message to print
	//
	public static void stdout(int level, String location, String message) {
		System.out.println(Log.format(level, location, message));
	}
	// end: public static void stdout


	// ====================================================================================================
	// public static void stdlog
	//
	// Writes a message to a standard log file, and optionally prints it to the standard output
	//
	// Arguments--
	//
	//  level:    the level of the message (info, debug, warn, error, fatal). If the level is unknown, it
	//            will be replaced by "Log.stdout"
	//
	//  location: the location the message originated from, such as a class or method name
	//
	//  message:  the message to print
	//
	//  print:    an optional parameter to force the message to print (or not) to the standard output. By
	//            default, the message is only printed if it is WARN or higher
	//
	public static void stdlog(int level, String location, String message) {
		boolean print = (level == Log.WARN || level == Log.ERROR || level == Log.FATAL);
		Log.stdlog(level, location, message, print);
	}

	public static void stdlog(int level, String location, String message, boolean print) {
		// Print message to stdout if requested
		if (print)
			Log.stdout(level, location, message);

		// Write to the log file
		if (Log.STDLOG_FILE != null) {
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(Log.STDLOG_FILE),
																 StandardCharsets.UTF_8,
																 StandardOpenOption.APPEND,
																 StandardOpenOption.CREATE)) {
				writer.write(Log.format(level, location, message) + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			Log.stdout(level, location, "(STDLOG_FILE was null) " + message);
		}

		// If the message was a fatal error, assume there is no way to recover and close to program to
		// prevent further errors or damage
		if (level == Log.FATAL)
			System.exit(Log.FATAL);
	}
	// end: public static void stdlog
	
}
// end: public class Log
