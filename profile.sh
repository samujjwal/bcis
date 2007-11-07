#!/bin/bash

# profile.sh

# This file sets up the CLASSPATH and other needed environment
# variables to run the example programs and tests.
#
# Do not execute this file as a shell script; instead, "source" it at
# the shell command line like this:
#
#     source profile.sh
#
# This will only work if your shell is "bash".  It should work in any
# UNIX-like environment, including Linux and Cygwin.


# Java class search path: needs to include the project "class"
# directory and any .jar files.
export CLASSPATH=.:$PWD:$PWD/class:$PWD/lib/antlr.jar:$PWD/lib/junit-4.4.jar

# Java source search path:
export SOURCEPATH=.:$PWD:$PWD/src:$PWD/test

# Convenience alias for ANTLR.
alias antlr="java antlr.Tool -diagnostic"

# Convenience alias for compiling files without changing the Makefile:
alias compile="javac -g -d $PWD/class -sourcepath $SOURCEPATH"

# Convenience alias for running a single test class.  Should be
# followed by the name of a Test class, like "physicalc.NumberTest":
alias test="java org.junit.runner.JUnitCore"
