import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.tags.collection.CompoundTag;


public class Generator {

	// List of block IDs to use
    public static final int NBTID_REDSTONE_DUST = 55;
    public static final int NBTID_REDSTONE_TORCH = 76;
	public static final int NBTID_REDSTONE_REPEATER = 93;
    public static final int NBTID_COLORED_WOOL = 1;
    public static final int NBTID_WHITE_WOOL = 35;
	

	private Map<Integer, Integer> assembledInstructions;
	private int width;
	private int length;
	private int height;
	

	public Generator(List<String> assembledInstructions, int width, int length) {
		this.assembledInstructions = this.cleanLines(assembledInstructions);
		this.width = width;
		this.length = length;
		this.height = 4;

		// Check size after lines are cleaned
		if (this.assembledInstructions.size() > this.length)
			throw new IllegalArgumentException("number of assembled instructions exceeds physical length; found " +
											   this.assembledInstructions.size() + ", expected <=" + this.length);
	}


	private Map<Integer, Integer> cleanLines(List<String> lines) {
		Map<Integer, Integer> cleaned = new HashMap<>();

		for (String line : lines) {
			line = line.replaceAll(Lang.COMMENT_REGEX, "");
			line = line.trim();

			if (line.startsWith(Lang.DIRECTIVE_IDENTIFIER))
				continue;

			String[] lineSplit = line.split(Lang.STD_DELIMITER);
			if (lineSplit.length != 2)
				continue;
			String pcStr = lineSplit[0];
			pcStr = pcStr.replace(Lang.HEX_IDENTIFIER, "");
			pcStr = pcStr.replace(Lang.LABEL_DELIMITER, "");
			String instructionStr = lineSplit[1];
			instructionStr = instructionStr.replace(Lang.HEX_IDENTIFIER, "");

			int pc;
			int instruction;
			try {
				pc = Integer.parseInt(pcStr, 16);
				instruction = Integer.parseInt(instructionStr, 16);
			}
			catch (NumberFormatException e) {
				Log.stdout(Log.WARN, "Generator", "could not parse pc or instruction to int; found pc=" +
						   pcStr + ", instruction=" + instructionStr);
				continue;
			}

			if (cleaned.containsKey(pc)) {
				Log.stdout(Log.WARN, "Generator", "duplicate pc: " + pc);
				continue;
			}
			cleaned.put(pc, instruction);
		}

		return cleaned;
	}


	public byte[] generate() {
		return this.generate(false);
	}
	
	public byte[] generate(boolean unpacked) {
		// Define dimensions in numbers of blocks, as opposed to number of bits/instructions. The game requires
		// a 1 block gap between bus lines, thus the formulas below of "N * 2 - 1"
		int blockWidth = this.width * 2 - 1;
		int blockHeight = this.height;
		int blockLength = this.length * 2;

		byte[][][] blockData = new byte[blockWidth][blockHeight][blockLength];
		byte[][][] metaData = new byte[blockWidth][blockHeight][blockLength];

		// In the game: x = width, z = length, y = height
        // Moving across x = moving horizontally across the bits of an instruction
        // Moving across z = moving laterally down the program memory
		for (int x = 0; x < blockWidth; x++) {
			for (int y = 0; y < blockHeight; y++) {
				for (int z = 0; z < blockLength; z++) {
					// Fill different blocks based on the vertical layer
					switch (y) {
					case 0: // First layer, wool going down the length of the program memory
						if (x % 2 == 0)
							blockData[x][y][z] = Generator.NBTID_COLORED_WOOL;
						break;
					case 1: // Second layer, redstone dust on first layer
						if (x % 2 == 0) {
							// Place repeaters every 15 block gap, otherwise dust
							if (z % 16 == 1)
								blockData[x][y][z] = Generator.NBTID_REDSTONE_REPEATER;
							else
								blockData[x][y][z] = Generator.NBTID_REDSTONE_DUST;
						}
						break;
					case 2: // Third layer, wool going across the width of each instruction (with torches)
						// Place wool for the horizontal line of each instruction (alternate white/colored)
						if (z % 2 == 1) {
							if (x % 2 == 0)
								blockData[x][y][z] = Generator.NBTID_COLORED_WOOL;
							else
								blockData[x][y][z] = Generator.NBTID_WHITE_WOOL;
						}
						// Place torches based on assembled instruction
						else if (x % 2 == 0) {
							int lineNumber = (z + 1) / 2; // Conversion factor from block units to bits
							if (!this.assembledInstructions.containsKey(lineNumber))
								break;
							int bits = this.assembledInstructions.get(lineNumber);

							// Get the currently focused bit and check for torch placement
							int low = (x + 1) / 2; // Bit of interest
							int mask = ((1 << (low + 1 - low)) - 1) << low;
							int bit = (bits & mask) >> low; // Get bit with a mask
							if (bit == 1) {
								blockData[x][y][z] = Generator.NBTID_REDSTONE_TORCH;
								metaData[x][y][z] = 4;
							}
						}
						break;
					case 3:
						if (z % 2 == 1) {
							// Place repeaters every 15 block gap, otherwise dust
							if (x % 16 == 1) {
								blockData[x][y][z] = Generator.NBTID_REDSTONE_REPEATER;
								metaData[x][y][z] = 1;
							}
							else
								blockData[x][y][z] = Generator.NBTID_REDSTONE_DUST;
						}
						break;
					}
				}
			}
		}
		
		// Compress arrays
		byte[] blockData1D = this.compressArray(blockData);
		byte[] metaData1D = this.compressArray(metaData);

		// Format NBT data
		Nbt nbtParser = new Nbt();
		CompoundTag compoundTag = new CompoundTag("Schematic");
		compoundTag.putByteArray("Blocks", blockData1D);
		compoundTag.putByteArray("Data", metaData1D);
		compoundTag.putList("Entities", new ArrayList<>());
		compoundTag.putList("TileEntities", new ArrayList<>());
		compoundTag.putString("Materials", "Alpha");
		compoundTag.putShort("Width", (short) blockWidth);
		compoundTag.putShort("Height", (short) blockHeight);
		compoundTag.putShort("Length", (short) blockLength);
		compoundTag.putInt("WEOriginX", 0);
		compoundTag.putInt("WEOriginY", 0);
		compoundTag.putInt("WEOriginZ", 0);
		compoundTag.putInt("WEOffsetX", 0);
		compoundTag.putInt("WEOffsetY", -1);
		compoundTag.putInt("WEOffsetZ", 0);

		if (unpacked)
			return nbtParser.toSnbt(compoundTag).getBytes(); // SNBT string to byte array
		try {
			return nbtParser.toByteArray(compoundTag);
		}
		catch (IOException e) {
			throw new RuntimeException("unchecked nbt parse error: " + e);
		}
	}


	private byte[] compressArray(byte[][][] arr) {
		if (arr == null)
		    throw new NullPointerException("cannot compress null array");

		int arrWidth = arr.length;
		int arrHeight = arr[0].length;
		int arrLength = arr[0][0].length;
		if (arrWidth == 0 || arrHeight == 0 || arrLength == 0)
			throw new IllegalArgumentException("array must have 3 filled dimensions of length >0, found " +
											   arrWidth + "*" + arrHeight + "*" + arrLength);
		
		byte[] compressed = new byte[arrWidth * arrHeight * arrLength];

		for (int x = 0; x < arrWidth; x++) {
			for (int y = 0; y < arrHeight; y++) {
				for (int z = 0; z < arrLength; z++) {
					// Index formula from: https://minecraft.fandom.com/wiki/Schematic_file_format
					int index = (y * arrLength + z) * arrWidth + x;

					// Although the first dimensions of the array were checked above, the sub-arrays of "arr"
					// may not be consistent lengths. If so, throw illegal argument exception back
					try {
						compressed[index] = arr[x][y][z];
					}
					catch (IndexOutOfBoundsException e) {
						throw new IllegalArgumentException("array must have constant dimension at " +
														   "[" + x + ", " + y + ", " + z + "]");
					}
				}
			}
		}

		return compressed;
	}

}
