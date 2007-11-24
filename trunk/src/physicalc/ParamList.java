package physicalc;

import java.util.ArrayList;
import java.util.List;

/** A ParamList is a container for a list of function parameters.  It
 * is used in function definitions.
 *
 */
public class ParamList extends Expr {

    private ArrayList<Id> contents;

    public ParamList() {
	System.err.println("Constructing a ParamList");
	contents = new ArrayList<Id>();
    }

    public void insert(Id i) {
	System.err.println("Adding to a ParamList");
	contents.add(i);
    }

    public List<Id> getContents() {
	return contents;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	/* This shouldn't be called. */
	throw new InterpreterError("GHASTLY ERROR: Called 'eval' method on a xParamList.");
    }
}
