# NBT-Editor
A simple editor for Minecraft's NBT data files. Can be used as a stand-alone command line tool for basic NBT parsing or as a library in conjunction with a larger program. \
Intended for use in the MC-Assembler suite of projects


# Dependencies
JDK 17 or above - https://www.oracle.com/java/technologies/downloads/ \
javacli - https://github.com/JonathanUhler/Java-CLI (bundled automatically in NBTEditor.jar)


# Installation
*Note: NBT-Editor has currently only been testing on MacOS and likely only works on Unix-based machines* \
\
The NBT-Editor repository can be cloned with the command line through ```git clone https://github.com/JonathanUhler/NBT-Editor.git``` \
Once cloned, the ```NBTEditor/``` folder under ```release/``` can be dragged to someplace in your ```$PATH``` and the editor can be run from the command line with ```NBTEditor --help``` to get started.


# NBT-Editor Command Line Usage
NBT-Editor can be used as a command line app or an external library. As a command line app it uses the javacli library and follows standard POSIX conventions. \
To run the editor, make sure it has been installed as outlined in Installation. Once installed and added to the ```$PATH```, the program be run with ```NBTEditor``` \
The command line arguments and options are:

```
usage: NBTEditor [-v] [--help]
    pack [-o OUTFILE] [--help] INFILE
    unpack [-o OUTFILE] [--help] INFILE
```

## Options
### NBTEditor
| Option        | Usage                       |
|---------------|-----------------------------|
| -v, --version | Print the version and exit  |
| --help        | Print help message and exit |

### pack
| Option     | Usage                              |
|------------|------------------------------------|
| -o OUTFILE | Specifies a file to output data to |
| --help     | Print help message and exit        |

### unpack
| Option     | Usage                              |
|------------|------------------------------------|
| -o OUTFILE | Specifies a file to output data to |
| --help     | Print help message and exit        |

## Arguments
### pack
| Argument | Usage                                      |
|----------|--------------------------------------------|
| infile   | Specifies the file with input data to pack |

### unpack
| Argument | Usage                                      |
|----------|--------------------------------------------|
| infile   | Specifies the file with raw data to unpack |

# NBT-Editor Library Usage
NBT-Editor can be used as a command line app or an external library. As a library, methods and classes can simply be used directly to parse. \
For more detailed information of a functional example for the pack and unpack methods/classes, see ```src/main/NBTEditor.java``` for a code example.

## Adding NBT-Editor to a Project
To use NBT-Editor as a library with another program, clone the repository and extract the jarfile at ```NBT-Editor/release/NBT-Editor/NBTEditor.jar``` \
Reference this jarfile directly with the classpath option or indirectly through your IDE/package manager of choice.


## PackParser Class
### Diagram
```
+-------------------------------------------------------------------------------+
|                                   PackParser                                  |
+-------------------------------------------------------------------------------+
| -uncompressedInput: String                                                    |
| -uncompressedLines: ArrayList<String>                                         |
+-------------------------------------------------------------------------------+
| +PackParser(String)                                                           |
+-------------------------------------------------------------------------------+
| -cleanInput(ArrayList<String>): ArrayList<String>                             |
| +pack(): String                                                               |
| -packPrimitiveType(String): PrimitiveBytes                                    |
| -packCollectionType(String, ArrayList<String>): CollectionBytes               |
| -packList(String, ArrayList<String>, int, int, String, int): CollectionBytes  |
| -packArray(String, ArrayList<String>, int, int, String, int): CollectionBytes |
+-------------------------------------------------------------------------------+
```

### pack() Method
To pack data, call the ```pack()``` method of the ```PackParser``` class with no arguments. This method returns a hex string (without a leading "0x" or "\x") representing the packed data.


## UnpackParser Class
### Diagram
```
+---------------------------------------------------------+
|                      UnpackParser                       |
+---------------------------------------------------------+
| -compressedInput: byte[]                                |
| -compressedStringInput: String                          |
+---------------------------------------------------------+
| +UnpackParser(byte[])                                   |
+---------------------------------------------------------+
| +upack(): String                                        |
| -unpackPrimitiveType(byte, byte[], int): PrimitiveTag   |
| -unpackCollectionType(byte, byte[], int): CollectionTag |
+---------------------------------------------------------+
```

### unpack() Method
To unpack data, call the ```unpack()``` method of the ```UnpackParser``` class with no arguments. This method returns a single string with newline ("\n") characters that represents the unpacked NBT data.


## Example
```java
import pack.PackParser;
import unpack.UnpackParser;


public class Example {
    public static void main(String[] args) {
        
        String uncompressedInput = "TAG_Byte a = 1";
        PackParser packParser = new PackParser(uncompressedInput);
        String packedInput = packParser.pack();
        
        byte[] compressedOutput = {0x01, 0x00, 0x01, 0x61, 0x01};
        UnpackParser unpackParser = new UnpackParser(compressedOutput);
        String unpackedOutput = unpackParser.unpack();
        
    }
}
```


# Packed File Usage
While NBT-Editor can be used for many different NBT applications, its most useful application is to pack and unpack WorldEdit schematic files. \
Once a schematic file is created (the format for this type of file can be found online), pack it will NBT-Editor. \
To use the schematic, move the file to minecraft/config/worldedit/schematics and load in-game with the "// schem load" command. \
\
*Note: The packed NBT bytes that are produced by NBT-Editor may need to be compressed to work in-game. To do this run* ```gzip <schematic file>```