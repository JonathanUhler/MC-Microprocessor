package asm;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import java.util.ArrayList;


@Command(name = "MCAsm", subcommands = {Assembler.class, Disassembler.class}, version = "MCAsm " + MCAsm.VERSION)
public class MCAsm implements Runnable {

	public static final String VERSION = "2.0.0";
	

	@Spec
	private CommandSpec spec;


	@Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this message and exit.")
	private boolean help;

	@Option(names = {"-V", "--version"}, versionHelp = true, description = "Display the version and exit.")
	private boolean version;


	// Error debug information
	private static int currentLineNum;
	private static String currentLine;
	private static int currentToken;
	

	public static void main(String[] args) {
		MCAsm.currentLineNum = 0;
		MCAsm.currentLine = "";
		MCAsm.currentToken = 0;
		
		new CommandLine(new MCAsm()).execute(args);
	}


	@Override
	public void run() {
	    throw new ParameterException(spec.commandLine(), "Missing required subcommand");
	}


	// Handlers for debugging information
	public static int getCurrentLineNum() {
		return MCAsm.currentLineNum;
	}

	
	public static String getCurrentLine() {
		return MCAsm.currentLine;
	}


	public static int getCurrentToken() {
		return MCAsm.currentToken;
	}


	public static void setCurrentLineNum(int currentLineNum) {
		MCAsm.currentLineNum = currentLineNum;
	}


	public static void setCurrentLine(String currentLine) {
		MCAsm.currentLine = currentLine;
		MCAsm.currentToken = 0;
	}


	public static void incrementCurrentToken() {
		MCAsm.currentToken++;
	}

}
