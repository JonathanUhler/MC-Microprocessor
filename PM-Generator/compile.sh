#!/bin/bash


# Setup
mkdir -p obj
rm -rf obj/*

# Compile
javac -Xlint:unchecked -Xlint:deprecation -cp 'src/lib/*' -d obj/ $(find src/main -name '*.java')
