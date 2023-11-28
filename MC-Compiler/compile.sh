#!/bin/bash


# Build lexer and parser
java -jar src/lib/antlr.jar \
	 -o ./src/main/grammar \
	 -package grammar \
	 -Xexact-output-dir \
	 ./src/grammar/MCMicroprocessor8Bit.g4

# Setup
mkdir -p obj
rm -rf obj/*

# Compile
javac -Xlint:all -cp 'src/lib/*' -d obj/ $(find src/main -name '*.java')
