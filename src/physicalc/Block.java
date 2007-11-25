package physicalc;

import java.util.ArrayList;
import java.util.List;

/** A Block is container for a list of Nodes.  It is used in two
 * places: 1) the body of a loop, and 2) the body of a function.
 *
 * Evaluating a block evaluates all its sub-nodes in order, and
 * returns the value of the last node.
 */
public class Block extends Stmt {

    private ArrayList<Node> contents;

    public Block() {
	System.err.println("Constructing a Block");
	contents = new ArrayList<Node>();
    }

    public void insert(Node n) {
	System.err.println("Adding to a Block");
	contents.add(n);
    }

    public List<Node> getContents() {
	return contents;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	System.err.println("Calling eval() in Block");
	
	Datum result = null;
	for (Node n : contents) {
	    result = n.eval(globals, locals);
	}
	return result;
    }
}
