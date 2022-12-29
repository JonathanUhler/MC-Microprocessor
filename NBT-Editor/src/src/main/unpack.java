// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// unpack.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/27/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package main;


import javacli.annotations.Argument;
import javacli.annotations.Option;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class unpack
//
// unpack sub-command
//
public class unpack {

    // Define options and arguments for the sub-command
    @Option(name = "outfile", abbreviation = 'o', help = "specifies a file to output packed data to", nargs = 1) public static String outfile;
    @Argument(name = "infile") public static String infile;

}
// end: public class pack