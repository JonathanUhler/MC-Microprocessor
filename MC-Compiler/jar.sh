#!/bin/bash


# Setup
mkdir -p bin
rm -rf bin/*

# Copy libraries
mkdir bin/lib
cp src/lib/* bin/lib

# Make jar
jar cmf 'manifest.mf' bin/MCCompiler.jar -C obj/main/ .
