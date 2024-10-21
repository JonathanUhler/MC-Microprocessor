package assembler;


import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;


public class Tokenizer {

    public enum LineType {
        INSTRUCTION,
        DIRECTIVE,
        LABEL
    }
    

    public static List<String> loadLines(File file) {
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }

        List<String> lines = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }
        }
        catch (IOException e) {
            return null;
        }

        return lines;
    }


    public static int saveLines(List<String> lines, File file) {
        if (lines == null) {
            throw new NullPointerException("lines cannot be null");
        }
        if (file == null) {
            throw new NullPointerException("file cannot be null");
        }

        int linesWritten = 0;

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (String line : lines) {
                writer.write(line + "\n");
                linesWritten++;
            }
            writer.close();
        }
        catch (IOException e) {
            return -1;
        }

        return linesWritten;
    }


    public static List<String> tokenize(String line) {
        if (line == null) {
            throw new NullPointerException("line cannot be null");
        }

        line = line.replaceAll("\\/\\/.*", "");  // Remove comments
        line = line.replaceAll(",?\\s+", " ");   // Standardize separators to one space to split by
        line = line.trim();

        if (line.length() == 0) {
            return null;
        }
        return Arrays.asList(line.split(" "));
    }


    public static LineType classify(List<String> tokens) {
        if (tokens == null) {
            throw new NullPointerException("tokens cannot be null");
        }
        if (tokens.size() == 0) {
            throw new IllegalArgumentException("cannot classify empty list of tokens");
        }

        String firstToken = tokens.get(0);
        if (firstToken.matches("^[_a-zA-Z][_a-zA-Z0-9]*:$")) {
            return Tokenizer.LineType.LABEL;
        }
        else if (firstToken.matches("^\\.[_a-zA-Z][_a-zA-Z0-9]*$")) {
            return Tokenizer.LineType.DIRECTIVE;
        }
        else {
            return Tokenizer.LineType.INSTRUCTION;
        }
    }

}
