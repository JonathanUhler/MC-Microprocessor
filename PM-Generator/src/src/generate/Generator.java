// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// Generator.java
// PM-Generator
//
// Created by Jonathan Uhler on 11/13/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package generate;


import helper.PMConstant;
import helper.PMHelper;
import pack.PackParser;
import java.util.ArrayList;
import java.util.Arrays;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class Generator
//
// Responsible for generating the packed or unpacked NBT for a program memory structure
//
public class Generator {

    private final String inputData; // Stringified version of the assembled input lines
    private final ArrayList<String> inputLines; // List version of the assembled input lines
    // Dimension of the program memory (except the height, this is not in blocks, but in lines and bits)
    private final int width;
    private final int length;
    private final int height = 4;


    // ----------------------------------------------------------------------------------------------------
    // public Generator
    //
    // Constructor 1 for Generator class
    //
    // Arguments--
    //
    // inputLines:  list of assembled lines to build into the program memory
    //
    // width:       width (in bits, not blocks) of the program memory
    //
    // length:      length (in lines, not blocks) of the program memory
    //
    public Generator(ArrayList<String> inputLines, int width, int length) {
        // Set dimensions and lines
        this.width = width;
        this.length = length;
        this.inputLines = inputLines;

        // Create and set the string version of the data
        StringBuilder inputString = new StringBuilder();
        for (String line : this.inputLines) inputString.append(line).append("\n");
        this.inputData = inputString.toString();
    }
    // end: public Generator


    // ----------------------------------------------------------------------------------------------------
    // public Generator
    //
    // Constructor 2 for Generator class
    //
    // Arguments--
    //
    // inputData:   string representing all the lines of the input data
    //
    // width:       width (in bits, not blocks) of the program memory
    //
    // length:      length (in lines, not blocks) of the program memory
    //
    public Generator(String inputData, int width, int length) {
        // Set instance variables
        this.width = width;
        this.length = length;
        this.inputData = inputData;
        this.inputLines = new ArrayList<>(Arrays.asList(this.inputData.split("\n")));
    }
    // end: public Generator


    // ====================================================================================================
    // private String generateNBT
    //
    // Generate raw NBT data based on two 3d arrays for the blocks and data tags
    //
    // Arguments--
    //
    // blockData:   a 3d array of block IDs for all the blocks in the structure
    //
    // dataData:    a 3d array of data tags for the blocks
    //
    // Returns--
    //
    // The unpacked NBT string of the data
    //
    private String generateNBT(byte[][][] blockData, byte[][][] dataData) {
        // Get the dimensions of the structure
        int width = blockData.length;
        int height = blockData[0].length;
        int length = blockData[0][0].length;

        // Initialize 1d arrays to compact the 3d data into
        byte[] blockData1D = new byte[width * length * height];
        byte[] dataData1D = new byte[width * length * height];

        // Compact the block data
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < length; z++) {
                    // Put the data into the 1d array at the correct place using the formula
                    //      1d index = (y * l + z) * w + x
                    blockData1D[(y * length + z) * width + x] = blockData[x][y][z];
                }
            }
        }

        // Compact the data tags
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < length; z++) {
                    // Put the data into the 1d array at the correct place using the formula
                    //      1d index = (y * l + z) * w + x
                    dataData1D[(y * length + z) * width + x] = dataData[x][y][z];
                }
            }
        }

        // Call the second version of this method that only takes in 1d arrays
        return this.generateNBT(width, length, height, blockData1D, dataData1D);
    }
    // end: private String generateNBT


    // ====================================================================================================
    // private String generateNBT
    //
    // Generate raw NBT data based on two 1d arrays for the blocks and data tags
    //
    // Arguments--
    //
    // width:       the width of the structure
    //
    // length:      the length of the structure
    //
    // height:      the height of the structure
    //
    // blockData:   the data array for the blocks
    //
    // dataData:    the data tag array
    //
    // Returns--
    //
    // The unpacked NBT string of the data
    //
    private String generateNBT(int width, int length, int height, byte[] blockData, byte[] dataData) {
        // Get the template for the program memory
        String template = PMConstant.PM_TEMPLATE;
        // Get a string version of the two arrays without the brackets
        String blocks = Arrays.toString(blockData).replace("[", "").replace("]", "");
        String data = Arrays.toString(dataData).replace("[", "").replace("]", "");

        // Add all the data to the correct location
        template = template.replace(PMConstant.BLOCKS_ARRAY_LENGTH, String.valueOf(blockData.length));
        template = template.replace(PMConstant.DATA_ARRAY_LENGTH, String.valueOf(dataData.length));
        template = template.replace(PMConstant.BLOCKS_ARRAY_DATA, blocks);
        template = template.replace(PMConstant.DATA_ARRAY_DATA, data);
        template = template.replace(PMConstant.WIDTH, String.valueOf(width));
        template = template.replace(PMConstant.LENGTH, String.valueOf(length));
        template = template.replace(PMConstant.HEIGHT, String.valueOf(height));

        // Return the NBT data
        return template;
    }
    // end: private String generateNBT


    // ====================================================================================================
    // public String generate
    //
    // Generate NBT data
    //
    // Arguments--
    //
    // None
    //
    // Returns--
    //
    // The packed bytes for the NBT data of the program memory
    //
    public String generate() throws Exception {
        // Return a call to the overloaded generate() method without keepUnpacked
        return this.generate(false);
    }
    // end: public String generate


    // ====================================================================================================
    // public String generate
    //
    // Generate NBT data
    //
    // Arguments--
    //
    // keepUnpacked:    whether or not to keep the NBT data unpacked
    //
    // Returns--
    //
    // The packed or unpacked bytes for the NBT data of the program memory
    //
    public String generate(boolean keepUnpacked) throws Exception {
        // Define the dimensions (in blocks) of the structure
        int w = this.width * 2 - 1;
        int h = this.height;
        int l = this.length * 2;

        // Define the byte arrays for the blocks and the data tags
        // Start with them as 3d arrays to make creation easier, then to pack convert them to the required 1d later
        byte[][][] blockData = new byte[w][h][l];
        byte[][][] dataData = new byte[w][h][l];


        // Check that the length (number of lines) is enough to fit all the assembled input lines
        PMHelper.pmAssert((this.inputLines.size() <= this.length),
                "number of instructions exceeds program memory length",
                "given " + this.inputLines.size() + " instructions", "expected <= " + this.length);


        // In the game: x = width, z = length, y = height
        // Moving across x = moving horizontally across the bits of an instruction
        // Moving across z = moving laterally down the program memory

        // Loop through all the blocks in the structure
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                for (int z = 0; z < l; z++) {
                    // Switch on y, which determines what layer is being build
                    switch (y) {
                        case 0: // First layer, wool going vertically
                            if (x % 2 == 0) blockData[x][y][z] = PMConstant.COLORED_WOOL;
                            break;
                        case 1: // Second layer, redstone dust on first layer
                            if (x % 2 == 0) blockData[x][y][z] = PMConstant.REDSTONE_DUST;
                            break;
                        case 2: // Third layer, wool going horizontally with torches
                            // Place wool (white or colored depending on which line)
                            if (z % 2 == 1) {
                                if (x % 2 == 0) blockData[x][y][z] = PMConstant.COLORED_WOOL;
                                else blockData[x][y][z] = PMConstant.WHITE_WOOL;
                            }
                            // Deal with placing torches based on assembled instructions
                            else if (((z + 1) / 2) < this.inputLines.size() && x % 2 == 0) {
                                // Get the current line and bits for the current line from the assembled instructions
                                String line = this.inputLines.get((z + 1) / 2).split(" ")[1];
                                String bits = PMHelper.padString(this.width, Integer.toBinaryString(Integer.parseInt(line, 16)));

                                // Check that the width is enough to fit all the bits
                                PMHelper.pmAssert((bits.length() <= this.width), "instruction wider than program memory", "instruction width was " + bits.length(), "expected width <= " + this.width);

                                // If the bit is a 1, place a torch at the correct location
                                if (bits.charAt((x + 1) / 2) == '1') {
                                    blockData[x][y][z] = PMConstant.REDSTONE_TORCH;
                                    dataData[x][y][z] = 4; // Set the data value to put the torch on the side of the block
                                }
                            }
                            break;
                        case 3: // Fourth layer, redstone dust on third layer
                            if (z % 2 == 1) blockData[x][y][z] = PMConstant.REDSTONE_DUST;
                            break;
                    }
                }
            }
        }

        // Generate and return the NBT data
        String programMemNBT = this.generateNBT(blockData, dataData);
        return (keepUnpacked) ? programMemNBT : new PackParser(programMemNBT).pack();
    }
    // end: public String generate

}
// end: public class Generator