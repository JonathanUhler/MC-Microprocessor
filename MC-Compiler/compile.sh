#!/bin/bash


# Build lexer and parser
java -jar src/lib/antlr.jar \
	 -o ./src/main/grammar \
	 -package grammar \
	 -Xexact-output-dir \
	 ./src/grammar/MCMicroprocessor8Bit.g4

# Setup
mkdir -p obj
mkdir -p obj/main
mkdir -p obj/tests
rm -rf obj/*

# Compile
javac -Xlint:all -cp 'src/lib/*' -d obj/main/ $(find src/main -name '*.java')
javac -Xlint:all -cp '.:src/lib/*:obj/main' -d obj/tests/ $(find src/tests -name '*.java')
