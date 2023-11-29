#!/bin/bash

args="$*"

java -cp '.:src/lib/*:obj/tests:obj/main' org.junit.runner.JUnitCore $args
