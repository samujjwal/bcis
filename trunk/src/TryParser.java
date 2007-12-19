import java.lang.String;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import antlr.Token;
import physicalc.*;


/** TryParser: test the Physicalc parser on the command line.
 * 
 * TryParser is an executable class that takes a single command-line
 * argument, a string.  It feeds the string through the PhysiCalc
 * parser and prints out the Lisp-style abstract syntax tree it
 * generates.
 *
 * Run it like this:
 *
 *    java TryAst "Physicalc code here"
 *
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class TryParser {
    public static void main(String [] args) {
	StringReader reader = new StringReader(args[0]);
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
