# Path to the Java runtime interpreter
JAVA = java

# Path to the Java compiler
JAVAC = javac

# Path to the JavaDoc tool
JAVADOC = javadoc

# The absolute path where this Makefile is located.
# Default is current working directory.
PROJECT = $(PWD)

# Directory where source .java file go.
SOURCE = $(PROJECT)/src

# Directory where compiled .class files will go.
CLASS = $(PROJECT)/class

# Directory for JavaDoc-generated documentation.
APIDOC = $(PROJECT)/doc/api

# Directory for .java source files for unit tests.
TEST = $(PROJECT)/test

# Paths and .jar files to search for Java .class files
# (colon separated)
CLASSPATH = $(CLASS):$(PROJECT)/lib/antlr.jar:$(PROJECT)/lib/junit-4.4.jar

# Flags for the Java compiler (-g includes debugging info)
JAVACFLAGS = -g -cp $(CLASSPATH) -sourcepath $(SOURCE) -d $(CLASS)

# Flags for the Java interpreter
JFLAGS = -cp $(CLASSPATH) 

# Command line to compile a java class file:
JC = $(JAVAC) $(JAVACFLAGS)

# Directories for sources and class files within the 'physicalc'
# package.
SDIR = $(SOURCE)/physicalc
CDIR = $(CLASS)/physicalc

# Command line for ANTLR
ANTLR = $(JAVA) $(JFLAGS) antlr.Tool 

# List of all project class files.  New classes should be added here
# and in the PER-CLASS COMPILATION RULES, below.
CLASSES = \
    $(CDIR)/PhysiLexerTokenTypes.class \
    $(CDIR)/PhysiLexer.class \
    $(CDIR)/PhysiParser.class \
    $(CDIR)/PhysiWalker.class \
    $(CDIR)/InterpreterError.class \
    $(CDIR)/TypeError.class \
    $(CDIR)/BoundsError.class \
    $(CDIR)/Main.class \
    $(CDIR)/Interpreter.class \
    $(CDIR)/Datum.class \
    $(CDIR)/PNumber.class \
    $(CDIR)/PUnit.class \
    $(CDIR)/PUnitPair.class \
    $(CDIR)/PVector.class \
    $(CDIR)/PList.class \
    $(CDIR)/PBoolean.class \
    $(CLASS)/TryParser.class \
    $(CLASS)/TryLexer.class \
    $(CLASS)/TryDatum.class \
    $(CLASS)/ParseFile.class

# List of all test class files.  New tests should be added here and in
# the PER-CLASS COMPILATION RULES, below.
TESTCLASSES = $(CDIR)/InterpreterTest.class \
    $(CDIR)/PhysicalcSuite.class

# Default target: compile all project classes (not tests)
all: $(CLASSES)

# 'run' target: run the "Main" class
run: $(CLASSES)
	$(JAVA) $(JFLAGS) physicalc.Main

# 'test' target: compile and run all unit tests
test: $(CLASSES) $(TESTCLASSES)
	$(JAVA) $(JFLAGS) org.junit.runner.JUnitCore physicalc.PhysicalcSuite

# The files generated by running ANTLR on the grammar file.
ANTLR_OUTPUT = $(SDIR)/PhysiLexer.java \
    $(SDIR)/PhysiParser.java \
    $(SDIR)/PhysiLexerTokenTypes.java \
    $(SDIR)/PhysiLexer.smap \
    $(SDIR)/PhysiLexerTokenTypes.txt \
    $(SDIR)/PhysiParser.smap \
    $(SDIR)/PhysiWalker.java \
    $(SDIR)/PhysiWalker.smap

# 'doc' target: make the Javadocs
doc: $(CLASSES) 
	mkdir -p $(APIDOC)
	$(JAVADOC) -sourcepath $(SOURCE) \
		-private -d $(APIDOC) physicalc

# Rules for generating the lexer & parser sources from the 
# ANTLR grammar.
$(ANTLR_OUTPUT): $(SOURCE)/grammar.g
	$(ANTLR) -o $(SDIR) $(SOURCE)/grammar.g

# 'clean' target: remove all generated files
clean:
	rm -f $(ANTLR_OUTPUT) $(CLASSES) $(TESTCLASSES)
	rm -rf $(APIDOC)


### PER-CLASS COMPILATION RULES

# Compilation rules for each class file.  We need one rule
# for every class file because the .java sources and the 
# compiled .class files go in different directories.

$(CLASS)/TryParser.class: $(SOURCE)/TryParser.java
	$(JC) $(SOURCE)/TryParser.java

$(CLASS)/TryLexer.class: $(SOURCE)/TryLexer.java
	$(JC) $(SOURCE)/TryLexer.java
	
$(CLASS)/TryDatum.class: $(SOURCE)/TryDatum.java
	$(JC) $(SOURCE)/TryDatum.java

$(CLASS)/ParseFile.class: $(SOURCE)/ParseFile.java
	$(JC) $(SOURCE)/ParseFile.java

$(CDIR)/InterpreterError.class: $(SDIR)/InterpreterError.java
	$(JC) $(SDIR)/InterpreterError.java

$(CDIR)/TypeError.class: $(SDIR)/TypeError.java
	$(JC) $(SDIR)/TypeError.java

$(CDIR)/PhysiLexerTokenTypes.class: $(SDIR)/PhysiLexerTokenTypes.java
	$(JC) $(SDIR)/PhysiLexerTokenTypes.java

$(CDIR)/PhysiLexer.class: $(SDIR)/PhysiLexer.java
	$(JC) $(SDIR)/PhysiLexer.java

$(CDIR)/PhysiParser.class: $(SDIR)/PhysiParser.java
	$(JC) $(SDIR)/PhysiParser.java

$(CDIR)/PhysiWalker.class: $(SDIR)/PhysiWalker.java
	$(JC) $(SDIR)/PhysiWalker.java

$(CDIR)/Interpreter.class: $(SDIR)/Interpreter.java
	$(JC) $(SDIR)/Interpreter.java

$(CDIR)/Datum.class: $(SDIR)/Datum.java
	$(JC) $(SDIR)/Datum.java

$(CDIR)/PBoolean.class: $(SDIR)/PBoolean.java
	$(JC) $(SDIR)/PBoolean.java
	
$(CDIR)/PNumber.class: $(SDIR)/PNumber.java
	$(JC) $(SDIR)/PNumber.java
	
$(CDIR)/PUnit.class: $(SDIR)/PUnit.java
	$(JC) $(SDIR)/PUnit.java
	
$(CDIR)/PUnitPair.class: $(SDIR)/PUnitPair.java
	$(JC) $(SDIR)/PUnitPair.java
	
$(CDIR)/PVector.class: $(SDIR)/PVector.java
	$(JC) $(SDIR)/PVector.java
	
$(CDIR)/PList.class: $(SDIR)/PList.java
	$(JC) -Xlint:unchecked $(SDIR)/PList.java
	
$(CDIR)/PVector.class: $(SDIR)/PVector.java
	$(JC) $(SDIR)/PString.java

$(CDIR)/Main.class: $(SDIR)/Main.java
	$(JC) $(SDIR)/Main.java

$(CDIR)/BoundsError.class: $(SDIR)/BoundsError.java
	$(JC) $(SDIR)/BoundsError.java

$(CDIR)/InterpreterTest.class: $(TEST)/InterpreterTest.java
	$(JC) $(TEST)/InterpreterTest.java

$(CDIR)/PhysicalcSuite.class: $(TEST)/PhysicalcSuite.java
	$(JC) $(TEST)/PhysicalcSuite.java
