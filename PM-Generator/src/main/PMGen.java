import java.util.List;
import java.util.ArrayList;
import java.util.zip.GZIPOutputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.Parameters;


@Command(name = "PMGen", version = "PMGen " + PMGen.VERSION)
public class PMGen implements Runnable {

	// CLI options and information
	public static final String VERSION = "2.0.1";

	@Parameters(paramLabel = "IN_FILE", description = "Input file with assembled instructions.")
	private String inPath;

	@Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this message and exit.")
	private boolean help;

	@Option(names = {"-V", "--version"}, versionHelp = true, description = "Display the version and exit.")
	private boolean version;

	@Option(names = {"-f", "--force"},
			description = "Overwrite existing content in the output file, if -o is used. If not specified, appends.")
	private boolean force;

	@Option(names = {"-z", "--zip"}, description = "Zip the output file, if specified.")
	private boolean zip;

	@Option(names = {"-o", "--outfile"}, paramLabel = "<OUT_FILE>",
			description = "Specify the location of an output file. If not specified, output is printed to stdout.")
	private String outPath;

	@Option(names = {"-u", "--unpacked"}, description = "Write unpacked NBT data instead of packed bytes.")
	private boolean unpacked;

	@Option(names = {"-w", "--width"}, paramLabel = "<WIDTH>", defaultValue = "25",
			showDefaultValue = Visibility.ALWAYS, description = "Specify width in bits of instructions.")
	private int width;

	@Option(names = {"-l", "--length"}, paramLabel = "<LENGTH>", defaultValue = "64",
			showDefaultValue = Visibility.ALWAYS, description = "Specify length (# lines) in the program memory")
	private int length;
	

	public static void main(String[] args) {
		new CommandLine(new PMGen()).execute(args);
	}


	@Override
	public void run() {
		// Load assembled instructions
		List<String> assembledInstructions = this.loadLines();

		// Generate program memory from assembled instructions
		Generator generator = new Generator(assembledInstructions, width, length);
		byte[] programMemory = generator.generate(this.unpacked);

		// Write to output file or stdout
		if (this.outPath != null)
			this.saveData(programMemory);
		else
			Log.stdout(Log.INFO, "PMGen", new String(programMemory));
	}


	private List<String> loadLines() {
		List<String> lines = new ArrayList<>();

		try {
			FileReader fr = new FileReader(this.inPath);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready())
				lines.add(br.readLine());
			br.close();
		}
		catch (IOException e) {
			Log.stdout(Log.FATAL, "PMGen", "Cannot read IN_FILE: " + e);
		}

		return lines;
	}


	public void saveData(byte[] data) {
		try {
			FileOutputStream fos = new FileOutputStream(this.outPath, !this.force);

			if (this.zip) {
				GZIPOutputStream gzos = new GZIPOutputStream(fos);
				gzos.write(data, 0, data.length);
				gzos.close();
			}
			else {
				fos.write(data);
				fos.close();
			}
		}
		catch (IOException e) {
			Log.stdout(Log.FATAL, "PMGen", "Cannot write OUT_FILE: " + e);
		}
	}

}
