package asm;


import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Manages file operations and number bases for the assembler.
 *
 * @author Jonathan Uhler
 */
public class DataManager {

	/** A logger object for this class. */
	public static final Logger LOGGER = Logger.getLogger(DataManager.class.getName());
	/** A tracer object for the assembler/disassembler program. */
	public static final Tracer TRACER = new Tracer();
	

	/**
	 * Loads a list of lines from a file.
	 *
	 * @param f  the file.
	 *
	 * @return a list of lines. If any error occurs during file reading, {@code null} is returned.
	 */
	public static List<String> loadLines(File f) {
		List<String> lines = new ArrayList<>();
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine())
				lines.add(s.nextLine());
		}
		catch (IOException e) {
			DataManager.LOGGER.severe("IOException when loading lines: " + e);
			return null;
		}
		return lines;
	}


	/**
	 * Writes a list of lines to a file.
	 *
	 * @param lines  the lines.
	 * @param f      the file.
	 * @param force  whether to overwrite existing file contents.
	 *
	 * @return the number of lines written. If an error occurs during writing, {@code -1} is
	 *         returned.
	 */
	public static int saveLines(List<String> lines, File f, boolean force) {
		int written = 0;
		try {
			f.createNewFile();
			FileWriter fw = new FileWriter(f, !force);
			for (String line : lines) {
				fw.write(line + "\n");
				written++;
			}
			fw.close();
		}
		catch (IOException e) {
			DataManager.LOGGER.severe("IOException when saving lines: " + e);
			return -1;
		}
		return written;
	}


	/**
	 * Converts an integer value to a hexadecimal string padded to at least the specified number
	 * of nibbles.
	 *
	 * @param value    the integer value to convert.
	 * @param nibbles  the minimum size of the hex string, in nibbles (characters).
	 *
	 * @return the specified integer value as a hexademical string of at least the length
	 *         specified by {@code nibbles}.
	 */
	public static String toHexString(int value, int nibbles) {
		String hex = Integer.toHexString(value);
		String str = Language.HEX_IDENTIFIER + String.format("%1$" + nibbles + "s", hex);
		str = str.replace(' ', '0');
		return str;
	}


	/**
	 * Construction of this class is not allowed. Routines should be accessed statically.
	 */
	private DataManager() { }

}
