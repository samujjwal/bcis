package physicalc;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.Reader;
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
    public Interpreter() {
	in = new InputStreamReader(System.in);
	out = new PrintWriter(System.out);
	err = new PrintWriter(System.err);
    }

    /** Changes the stream that this Interpreter uses as its standard
     * input. */
    public void setInputStream(Reader inputStream) {
	in = inputStream;
    }

    /** Changes the stream that this Interpreter uses as its standard
     * output. */
    public void setOutputStream(Writer outputStream) {
	out = new PrintWriter(outputStream);
    }

    /** Changes the stream that this Interpreter uses as its standard
     * error. */
    public void setErrorStream(Writer errorStream) {
	err = new PrintWriter(errorStream);
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

	    SymbolTable globals = new SymbolTable();
	    SymbolTable topLevel = new SymbolTable();
	    Datum result = p.eval(globals, topLevel);
	
	    if (result == null) {
		System.out.println("null");
	    } else {
		System.out.println(result.toString());
	    }
	    
	} catch(Exception e) {
	    System.err.println(e.toString());
	}
    }
}
