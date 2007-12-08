package physicalc;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/** A ParamList is a container for a list of function parameters.  It
 * is used in function definitions.
 *
 */
public class ParamList extends Expr {

    private ArrayList<String> contents;

    public ParamList() {
	//System.out.println("Constructing a ParamList");
	contents = new ArrayList<String>();
    }

    public void insert(String i) {
	//System.out.println("Adding to a ParamList");
	contents.add(i);
    }

    public List<String> getContents() {
	return contents;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	/* This shouldn't be called. */
	throw new InterpreterError("GHASTLY ERROR: Called 'eval' method on a ParamList.");
    }
}
