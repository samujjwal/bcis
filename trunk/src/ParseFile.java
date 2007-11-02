import java.lang.String;
import java.io.Reader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;
import antlr.Token;
import physicalc.*;


/** ParseFile: test the Physicalc parser on a full file on the command
 * line.
 * 
 * ParseFile is an executable class that takes a single command-line
 * argument, a file name.  It reads the file and feeds it through the
 * PhysiCalc parser and prints out the Lisp-style abstract syntax tree
 * it generates.
 *
 * Run it like this:
 *
 *    java ParseFile filename
 *
 */
public class ParseFile {
    public static void main(String [] args) {
	Reader reader;
	try {
	    reader = new FileReader(args[0]);
	} catch (FileNotFoundException err) {
	    System.out.println("File not found.");
	    return;
	}
	
	PhysiLexer lexer = new PhysiLexer(reader);
	PhysiParser parser = new PhysiParser(lexer);
	try {
	    parser.program();
	    System.out.println(parser.getAST().toStringList());
	} catch (Exception err) {
	    System.out.println(err.toString());
	}
    }
}
