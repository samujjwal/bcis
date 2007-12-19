package physicalc;

import java.util.ArrayList;
import java.util.List;

/** A Program is a container for a collection of Nodes representing a
 * complete program.  
 *
 * Evaluating a Program evaluates all its sub-nodes in order, and
 * returns the value of the last node.
 *
 * A Program creates its own top-level symbol table for variables
 * defined outisde of any function definitions.
 *
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class Program extends Node {

    private ArrayList<Node> contents;

    public Program() {
	//System.out.println("Constructing a Program");
	contents = new ArrayList<Node>();
    }

    public void insert(Node n) {
	//System.out.println("Adding to a Program");
	contents.add(n);
    }

    public List<Node> getContents() {
	return contents;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	//System.out.println("Calling eval() in Program");
	
	locals = new SymbolTable();
	
	Datum result = null;
	for (Node n : contents) {
	    result = n.eval(globals, locals);
	}
	return result;
    }
}
