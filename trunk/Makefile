# Path to the Java runtime interpreter
JAVA = java

# Path to the Java compiler
JAVAC = javac

# The absolute path where this Makefile is located.
# Default is current working directory.
PROJECT = $(PWD)

# Directory where compiled .class files will go.
CLASS = $(PROJECT)/class

# Paths and .jar files to search for Java .class files
# (colon separated)
CLASSPATH = $(CLASS):$(PROJECT)/lib/antlr.jar:$(PROJECT)lib/junit-4.4.jar

# Flags for the Java compiler (-g includes debugging info)
JAVACFLAGS = -g -cp $(CLASSPATH) -d $(CLASS)

# Flags for the Java interpreter
JFLAGS = -cp $(CLASSPATH)

# Command line to compile a java class file:
JC = $(JAVAC) $(JAVACFLAGS)

# Package directories, sources and class files.
SDIR = $(PROJECT)/src/physicalc
CDIR = $(CLASS)/physicalc

# Command line for ANTLR
ANTLR = $(JAVA) $(JFLAGS) antlr.Tool 

# List of all class files.  New classes should be added here
# and in the PER-CLASS COMPILATION RULES, below.
CLASSES = $(CDIR)/PhysiLexerTokenTypes.class \
    $(CDIR)/PhysiLexer.class \
    $(CDIR)/PhysiParser.class \
    $(CDIR)/Main.class

# Default target: compile everything
all: $(CLASSES)

# 'run' target: run the "Main" class
run: $(CLASSES)
	$(JAVA) $(JFLAGS) physicalc.Main

ANTLR_OUTPUT = $(SDIR)/PhysiLexer.java \
    $(SDIR)/PhysiParser.java \
    $(SDIR)/PhysiLexerTokenTypes.java \
    $(SDIR)/PhysiLexer.smap \
    $(SDIR)/PhysiLexerTokenTypes.txt \
    $(SDIR)/PhysiParser.smap

# Rules for generating the lexer & parser sources from the 
# ANTLR grammar.
$(ANTLR_OUTPUT): src/grammar.g
	$(ANTLR) -o $(SDIR) src/grammar.g

# 'clean' target: remove all generated files
clean:
	rm -f $(ANTLR_OUTPUT) $(CLASSES)


### PER-CLASS COMPILATION RULES

# Compilation rules for each class file.  We need one rule
# for every class file because the .java sources and the 
# compiled .class files go in different directories.

$(CDIR)/Main.class: $(SDIR)/Main.java
	$(JC) $(SDIR)/Main.java

$(CDIR)/PhysiLexerTokenTypes.class: $(SDIR)/PhysiLexerTokenTypes.java
	$(JC) $(SDIR)/PhysiLexerTokenTypes.java

$(CDIR)/PhysiLexer.class: $(SDIR)/PhysiLexer.java
	$(JC) $(SDIR)/PhysiLexer.java

$(CDIR)/PhysiParser.class: $(SDIR)/PhysiParser.java
	$(JC) $(SDIR)/PhysiParser.java

