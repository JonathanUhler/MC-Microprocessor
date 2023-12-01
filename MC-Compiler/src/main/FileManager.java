import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import org.antlr.v4.runtime.misc.ParseCancellationException;


/**
 * Manages file operations for the compiler.
 *
 * @author Jonathan Uhler
 */
public class FileManager {

	public static String name(File f) {
		return f.getName().replaceFirst("[.][^.]+$", "");
	}
	

	/**
	 * Loads mc8 source from a file.
	 *
	 * @param f  the file.
	 *
	 * @return the contents of {@code f}.
	 *
	 * @throws ParseCancellationException  if any error occurs during file reading.
	 */
	public static String load(File f) {
		StringBuilder string = new StringBuilder();
		try {
			Scanner s = new Scanner(f);
			while (s.hasNextLine())
				string.append(s.nextLine() + "\n");
		}
		catch (FileNotFoundException fnfe) {
			throw new ParseCancellationException("error: file not found: " + f);
		}
		return string.toString();
	}


	/**
	 * Writes assembly or binary output to a file.
	 *
	 * @param output the output.
	 * @param f      the file.
	 * @param force  whether to overwrite existing file contents.
	 *
	 * @return whether the contents were successfully written.
	 *
	 * @throws ParseCancellationException  if any error occurs during file reading.
	 */
	public static boolean save(String output, File f, boolean force) {
		int written = 0;
		try {
			f.createNewFile();
			FileWriter fw = new FileWriter(f, !force);
			fw.write(output);
			fw.close();
		}
		catch (IOException e) {
			throw new ParseCancellationException("error: unwritable file: " + f + ", " + e);
		}
		return true;
	}


	/**
	 * Construction of this class is not allowed. Routines should be accessed statically.
	 */
	private FileManager() { }

}
