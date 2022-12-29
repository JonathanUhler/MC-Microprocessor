// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// pack.java
// NBT-Editor
//
// Created by Jonathan Uhler on 10/24/21
// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=


package main;


import javacli.annotations.Argument;
import javacli.annotations.Option;


// +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
// public class pack
//
// pack sub-command
//
public class pack {

    // Define options and arguments for the sub-command
    @Option(name = "outfile", abbreviation = 'o', help = "specifies a file to output packed data to", nargs = 1) public static String outfile;
    @Argument(name = "infile") public static String infile;

}
// end: public class pack