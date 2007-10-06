import java.lang.String;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import antlr.Token;
import physicalc.*;


/** TryLexer: test the PhysiCalc lexer on the command line.
 *
 * TryLexer is an executable class that takes a single command-line
 * argument, a string.  It feeds the string through PhysiCalc's
 * ANTLR-generated lexer and prints out the list of tokens recognized.
 *
 * Run it like this:
 *
 *    java TryLexer "3 + 4"
 *
 * And you get output like this:
 *
 *     ["3",<11>,line=1,col=1]
 *     ["+",<13>,line=1,col=3]
 *     ["4",<11>,line=1,col=5]
 *
 * On each line, the first field is the string matched, the second is
 * the number of the token type, which you can find in the
 * ANTLR-generated file src/physicalc/PhysiLexerTokenTypes.txt .
 *
 */
public class TryLexer {
    public static void main(String [] args) {
	List<Token> tokens = new ArrayList<Token>();
	try {
	    String test = args[0];
	    StringReader reader = new StringReader(test);
	    PhysiLexer lexer = new PhysiLexer(reader);

	    Token t;
	    while ((t = lexer.nextToken()) != null) {
		if (t.getType() == antlr.Token.EOF_TYPE) { break; }
		tokens.add(t);
	    }
	} catch (antlr.TokenStreamException err) {
	    System.out.println(err.toString());
	}

	for (Token t : tokens) {
	    System.out.println(t.toString());
	}
    }
}
