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
    $(CDIR)/PhysiLexer.class \
    $(CDIR)/PhysiLexerTokenTypes.class \
    $(CDIR)/PhysiParser.class \
    $(CDIR)/PhysiWalker.class \
    $(CLASS)/TryDatum.class \
    $(CLASS)/TryLexer.class \
    $(CLASS)/TryParser.class \
    $(CLASS)/ParseFile.class \
    $(CDIR)/Access.class \
    $(CDIR)/And.class \
    $(CDIR)/Arith.class \
    $(CDIR)/Block.class \
    $(CDIR)/BoundsError.class \
    $(CDIR)/Break.class \
    $(CDIR)/BreakSignal.class \
    $(CDIR)/ControlSignal.class \
    $(CDIR)/Datum.class \
    $(CDIR)/Def.class \
    $(CDIR)/Expr.class \
    $(CDIR)/ExprList.class \
    $(CDIR)/For.class \
    $(CDIR)/FunCall.class \
    $(CDIR)/Function.class \
    $(CDIR)/GetNumberFunction.class \
    $(CDIR)/GetUnitFunction.class \
    $(CDIR)/Id.class \
    $(CDIR)/If.class \
    $(CDIR)/In.class \
    $(CDIR)/InterpreterError.class \
    $(CDIR)/Interpreter.class \
    $(CDIR)/Literal.class \
    $(CDIR)/Load.class \
    $(CDIR)/Logical.class \
    $(CDIR)/LValue.class \
    $(CDIR)/Main.class \
    $(CDIR)/Next.class \
    $(CDIR)/NextSignal.class \
    $(CDIR)/Node.class \
    $(CDIR)/Not.class \
    $(CDIR)/Op.class \
    $(CDIR)/Or.class \
    $(CDIR)/ParamList.class \
    $(CDIR)/PrintFunction.class \
    $(CDIR)/PBoolean.class \
    $(CDIR)/PList.class \
    $(CDIR)/PNumber.class \
    $(CDIR)/Program.class \
    $(CDIR)/PString.class \
    $(CDIR)/PUnit.class \
    $(CDIR)/PUnitPair.class \
    $(CDIR)/Rel.class \
    $(CDIR)/Return.class \
    $(CDIR)/ReturnSignal.class \
    $(CDIR)/RuntimeObject.class \
    $(CDIR)/Set.class \
    $(CDIR)/Stmt.class \
    $(CDIR)/SymbolTable.class \
    $(CDIR)/ToIntFunction.class \
    $(CDIR)/TypeError.class \
    $(CDIR)/Unary.class \
    $(CDIR)/UndefinedError.class \
    $(CDIR)/Variable.class \
    $(CDIR)/While.class \
    $(CDIR)/Constant.class \
    $(CDIR)/ConstantDef.class \
    $(CDIR)/Unit.class \
    $(CDIR)/UnitDef.class \
    $(CDIR)/FunctionDef.class \
    $(CDIR)/AliasDef.class

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



$(CDIR)/PhysiLexer.class: $(SDIR)/PhysiLexer.java
	$(JC) $(SDIR)/PhysiLexer.java

$(CDIR)/PhysiLexerTokenTypes.class: $(SDIR)/PhysiLexerTokenTypes.java
	$(JC) $(SDIR)/PhysiLexerTokenTypes.java

$(CDIR)/PhysiParser.class: $(SDIR)/PhysiParser.java
	$(JC) $(SDIR)/PhysiParser.java

$(CDIR)/PhysiWalker.class: $(SDIR)/PhysiWalker.java
	$(JC) $(SDIR)/PhysiWalker.java



$(CDIR)/Access.class: $(SDIR)/Access.java
	$(JC) $(SDIR)/Access.java

$(CDIR)/And.class: $(SDIR)/And.java
	$(JC) $(SDIR)/And.java

$(CDIR)/Arith.class: $(SDIR)/Arith.java
	$(JC) $(SDIR)/Arith.java

$(CDIR)/Block.class: $(SDIR)/Block.java
	$(JC) $(SDIR)/Block.java

$(CDIR)/BoundsError.class: $(SDIR)/BoundsError.java
	$(JC) $(SDIR)/BoundsError.java

$(CDIR)/Break.class: $(SDIR)/Break.java
	$(JC) $(SDIR)/Break.java

$(CDIR)/BreakSignal.class: $(SDIR)/BreakSignal.java
	$(JC) $(SDIR)/BreakSignal.java

$(CDIR)/ControlSignal.class: $(SDIR)/ControlSignal.java
	$(JC) $(SDIR)/ControlSignal.java

$(CDIR)/Datum.class: $(SDIR)/Datum.java
	$(JC) $(SDIR)/Datum.java

$(CDIR)/Def.class: $(SDIR)/Def.java
	$(JC) $(SDIR)/Def.java

$(CDIR)/Expr.class: $(SDIR)/Expr.java
	$(JC) $(SDIR)/Expr.java

$(CDIR)/ExprList.class: $(SDIR)/ExprList.java
	$(JC) $(SDIR)/ExprList.java

$(CDIR)/For.class: $(SDIR)/For.java
	$(JC) $(SDIR)/For.java

$(CDIR)/FunCall.class: $(SDIR)/FunCall.java
	$(JC) $(SDIR)/FunCall.java

$(CDIR)/Function.class: $(SDIR)/Function.java
	$(JC) $(SDIR)/Function.java
	
$(CDIR)/GetNumberFunction.class: $(SDIR)/GetNumberFunction.java
	$(JC) $(SDIR)/GetNumberFunction.java
	
$(CDIR)/GetUnitFunction.class: $(SDIR)/GetUnitFunction.java
	$(JC) $(SDIR)/GetUnitFunction.java

$(CDIR)/Id.class: $(SDIR)/Id.java
	$(JC) $(SDIR)/Id.java

$(CDIR)/If.class: $(SDIR)/If.java
	$(JC) $(SDIR)/If.java

$(CDIR)/In.class: $(SDIR)/In.java
	$(JC) $(SDIR)/In.java

$(CDIR)/InterpreterError.class: $(SDIR)/InterpreterError.java
	$(JC) $(SDIR)/InterpreterError.java

$(CDIR)/Interpreter.class: $(SDIR)/Interpreter.java
	$(JC) $(SDIR)/Interpreter.java

$(CDIR)/Literal.class: $(SDIR)/Literal.java
	$(JC) $(SDIR)/Literal.java

$(CDIR)/Load.class: $(SDIR)/Load.java
	$(JC) $(SDIR)/Load.java

$(CDIR)/Logical.class: $(SDIR)/Logical.java
	$(JC) $(SDIR)/Logical.java

$(CDIR)/LValue.class: $(SDIR)/LValue.java
	$(JC) $(SDIR)/LValue.java

$(CDIR)/Main.class: $(SDIR)/Main.java
	$(JC) $(SDIR)/Main.java

$(CDIR)/Next.class: $(SDIR)/Next.java
	$(JC) $(SDIR)/Next.java

$(CDIR)/NextSignal.class: $(SDIR)/NextSignal.java
	$(JC) $(SDIR)/NextSignal.java

$(CDIR)/Node.class: $(SDIR)/Node.java
	$(JC) $(SDIR)/Node.java

$(CDIR)/Not.class: $(SDIR)/Not.java
	$(JC) $(SDIR)/Not.java

$(CDIR)/Op.class: $(SDIR)/Op.java
	$(JC) $(SDIR)/Op.java

$(CDIR)/Or.class: $(SDIR)/Or.java
	$(JC) $(SDIR)/Or.java

$(CDIR)/ParamList.class: $(SDIR)/ParamList.java
	$(JC) $(SDIR)/ParamList.java

$(CDIR)/PBoolean.class: $(SDIR)/PBoolean.java
	$(JC) $(SDIR)/PBoolean.java

$(CDIR)/PList.class: $(SDIR)/PList.java
	$(JC) $(SDIR)/PList.java

$(CDIR)/PNumber.class: $(SDIR)/PNumber.java
	$(JC) $(SDIR)/PNumber.java

$(CDIR)/PrintFunction.class: $(SDIR)/PrintFunction.java
	$(JC) $(SDIR)/PrintFunction.java

$(CDIR)/Program.class: $(SDIR)/Program.java
	$(JC) $(SDIR)/Program.java

$(CDIR)/PString.class: $(SDIR)/PString.java
	$(JC) $(SDIR)/PString.java

$(CDIR)/PUnit.class: $(SDIR)/PUnit.java
	$(JC) $(SDIR)/PUnit.java

$(CDIR)/PUnitPair.class: $(SDIR)/PUnitPair.java
	$(JC) $(SDIR)/PUnitPair.java

$(CDIR)/Rel.class: $(SDIR)/Rel.java
	$(JC) $(SDIR)/Rel.java

$(CDIR)/Return.class: $(SDIR)/Return.java
	$(JC) $(SDIR)/Return.java

$(CDIR)/ReturnSignal.class: $(SDIR)/ReturnSignal.java
	$(JC) $(SDIR)/ReturnSignal.java

$(CDIR)/RuntimeObject.class: $(SDIR)/RuntimeObject.java
	$(JC) $(SDIR)/RuntimeObject.java

$(CDIR)/Set.class: $(SDIR)/Set.java
	$(JC) $(SDIR)/Set.java

$(CDIR)/Stmt.class: $(SDIR)/Stmt.java
	$(JC) $(SDIR)/Stmt.java

$(CDIR)/SymbolTable.class: $(SDIR)/SymbolTable.java
	$(JC) $(SDIR)/SymbolTable.java
	
$(CDIR)/ToIntFunction.class: $(SDIR)/ToIntFunction.java
	$(JC) $(SDIR)/ToIntFunction.java

$(CDIR)/TypeError.class: $(SDIR)/TypeError.java
	$(JC) $(SDIR)/TypeError.java

$(CDIR)/UndefinedError.class: $(SDIR)/UndefinedError.java
	$(JC) $(SDIR)/UndefinedError.java

$(CDIR)/Unary.class: $(SDIR)/Unary.java
	$(JC) $(SDIR)/Unary.java

$(CDIR)/Variable.class: $(SDIR)/Variable.java
	$(JC) $(SDIR)/Variable.java

$(CDIR)/While.class: $(SDIR)/While.java
	$(JC) $(SDIR)/While.java

$(CDIR)/ConstantDef.class: $(SDIR)/ConstantDef.java
	$(JC) $(SDIR)/ConstantDef.java

$(CDIR)/Constant.class: $(SDIR)/Constant.java
	$(JC) $(SDIR)/Constant.java

$(CDIR)/Unit.class: $(SDIR)/Unit.java
	$(JC) $(SDIR)/Unit.java

$(CDIR)/UnitDef.class: $(SDIR)/UnitDef.java
	$(JC) $(SDIR)/UnitDef.java

$(CDIR)/FunctionDef.class: $(SDIR)/FunctionDef.java
	$(JC) $(SDIR)/FunctionDef.java

$(CDIR)/AliasDef.class: $(SDIR)/AliasDef.java
	$(JC) $(SDIR)/AliasDef.java

$(CDIR)/InterpreterTest.class: $(TEST)/InterpreterTest.java
	$(JC) $(TEST)/InterpreterTest.java

$(CDIR)/PhysicalcSuite.class: $(TEST)/PhysicalcSuite.java
	$(JC) $(TEST)/PhysicalcSuite.java
