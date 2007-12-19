package physicalc;

import java.util.ArrayList;
import java.util.List;

/** A Block is container for a list of Nodes.  It is used in two
 * places: 1) the body of a loop, and 2) the body of a function.
 *
 * Evaluating a block evaluates all its sub-nodes in order, and
 * returns the value of the last node.
 *
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class Block extends Stmt {

    private ArrayList<Node> contents;

    public Block() {
	//System.out.println("Constructing a Block");
	contents = new ArrayList<Node>();
    }

    public void insert(Node n) {
	//System.out.println("Adding to a Block");
	contents.add(n);
    }

    public List<Node> getContents() {
	return contents;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	//System.out.println("Calling eval() in Block");
	//System.out.println("   the block has " + ((Integer)contents.size()).toString() + " nodes");
	
	Datum result = null;
	for (Node n : contents) {
	    //System.out.println("Executing a Node inside a Block");
	    result = n.eval(globals, locals);
	}
	return result;
    }
}
