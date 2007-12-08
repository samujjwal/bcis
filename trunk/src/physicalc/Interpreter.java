package physicalc;

import java.io.*;
import antlr.CommonAST;
import antlr.collections.AST;

/** An Interpreter object is responsible for running Physicalc code.
 * It has input, output, and error streams, which default to STDIN,
 * STDOUT, and STDERR, respectively; but may be changed by the calling
 * code for testing.
 *
 * To use this class, create an instance of it and call "eval",
 * passing in a stream for the code you want to run.
 */
public class Interpreter {

    private Reader in;

    private PrintWriter out;

    private PrintWriter err;

    /** Constructor.  Creates a new interpreter instance.  Input,
     * output, and error streams default to system STDIN, STDOUT, and
     * STDERR, respectively. */
    public Interpreter() { ; }

    /** Changes the stream that this Interpreter uses as its standard
     * input. */
    public void setInputStream(InputStream inputStream) {
	System.setIn(inputStream);
    }

    /** Changes the stream that this Interpreter uses as its standard
     * output. */
    public void setOutputStream(OutputStream outputStream) {
	System.setOut(new PrintStream(outputStream));
    }

    /** Changes the stream that this Interpreter uses as its standard
     * error. */
    public void setErrorStream(OutputStream errorStream) {
	System.setErr(new PrintStream(errorStream));
    }

    /** eval() executes Physicalc source code.
     *
     * @param code A Reader containing Physicalc source code.  For
     * normal use this would be a file stream, but it could be a
     * string reader for testing.  It could even be standard input.
     */
    public void eval(Reader code) {
	try {
	    PhysiLexer lexer = new PhysiLexer(code);
	    PhysiParser parser = new PhysiParser(lexer);

	    parser.program();

	    CommonAST parseTree = (CommonAST)parser.getAST();

	    PhysiWalker walker = new PhysiWalker();
	    Program p = walker.program(parseTree);

	    SymbolTable globals = setupGlobalSymbols();
	    SymbolTable topLevel = new SymbolTable();
	    Datum result = p.eval(globals, topLevel);
	
	    // Print the result of the last expression, for testing only.
	    if (result == null) {
		System.err.println("null");
	    } else {
		System.err.println(result.toString());
	    }
	    
	} catch(Exception e) {
	    System.err.println(e.toString());
	}
    }

    private SymbolTable setupGlobalSymbols() {
	SymbolTable globals = new SymbolTable();
	globals.put("print", ((RuntimeObject)(new PrintFunction())));
	globals.put("toInt", ((RuntimeObject)(new ToIntFunction())));
	return globals;
    }
}
