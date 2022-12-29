# PM-Generator
A command line tool to generate program file structures from the output of MC-Assembler. Intended to be used with the MC-Asm suite of projects


# Dependencies
JDK 17 or above - https://www.oracle.com/java/technologies/downloads/ \
javacli - https://github.com/JonathanUhler/Java-CLI (bundled automatically in PMGenerator.jar) \
NBT-Editor - https://github.com/JonathanUhler/NBT-Editor (bundled automatically in PMGenerator.jar)


# Installation
*Note: PM-Generator has currently only been testing on MacOS and likely only works on Unix-based machines* \
\
The PM-Generator repository can be cloned with the command line through ```git clone https://github.com/JonathanUhler/PM-Generator.git``` \
Once cloned, the ```PMGenerator/``` folder under ```release/``` can be dragged to someplace in your ```$PATH``` and the editor can be run from the command line with ```PMGenerator --help``` to get started.


# PM-Generator Command Line Usage
To run the generator, make sure it has been installed as outlined in Installation. Once installed and added to the ```$PATH```, the program be run with ```PMGenerator``` \
The command line arguments and options are:

```
usage: NBTEditor [-vVu] [-o FILE] [-w INT] [-l INT] [--help]
```

## Options
| Option                    | Usage
| ------------------------- | ----------------------------------------------
| -v, --version             | Print the version and exit
| -V, --verbose             | Run the generator with verbose output
| -o FILE, --output FILE    | Specify a file to write out to
| -u, --unpack              | Write unpacked data instead of packed bytes
| -w INT, --width INT       | Specify the width in bits of instructions
| -l INT, --length INT      | Specify the depth in lines of the program memory
| -o OUTFILE                | Specifies a file to output data to
| --help                    | Print help message and exit



## Arguments
| Argument          | Usage
| ----------------- | ----------------------------------------------
| infile            | Specifies the file with input data to pack



# Packed File Usage
PM-Generator uses NBT-Editor to generate NBT data that can be parsed by the WorldEdit modification for the game as a schematic file. \
\
To use the schematic, move the file to minecraft/config/worldedit/schematics and load in-game with the "//schem load [schematic file]" command. \
\
*Note: The packed NBT bytes that are produced by the NBT-Editor process may need to be compressed to work in-game. To do this run* ```gzip <schematic file>```