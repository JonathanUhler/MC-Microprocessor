#!/bin/bash


cd ../src/main
javadoc *.java asm/*.java commands/*.java commands/**/*.java -d ../../docs/javadoc -cp ../lib/*.jar
