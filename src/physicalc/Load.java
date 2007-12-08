package physicalc;

import java.lang.String;
import java.io.*;
import antlr.CommonAST;
import antlr.collections.AST;

/** Load implements the "load" statement.
 * 
 * @see Node
 */
public class Load extends Node {

    private String file;

    public Load(String filename) {
	file = filename;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	Reader input;
	try {
	    input = new FileReader(file);
	} catch (FileNotFoundException e) {
	    System.err.println("File '" + file + "' not found.");
	    System.exit(1);
	    return null; // to keep the compiler happy
	}
	
	try {
	    PhysiLexer lexer = new PhysiLexer(input);
	    PhysiParser parser = new PhysiParser(lexer);

	    parser.program();

	    CommonAST parseTree = (CommonAST)parser.getAST();

	    PhysiWalker walker = new PhysiWalker();
	    Program p = walker.program(parseTree);

	    p.eval(globals, locals);
	
	} catch(Exception e) {
	    System.err.println(e.toString());
	}

	return null;
    }
}
