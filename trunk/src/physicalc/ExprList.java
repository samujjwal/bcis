package physicalc;

import java.util.ArrayList;
import java.util.List;

/** An ExprList is container for a list of Expr objects.  It is used
 * in two places: 1) list literals, and 2) function calls.
 *
 * For a list literal, you call the "eval" method and ExprList returns
 * a new PList containing the results of all the expressions inside
 * it.
 *
 * For a function call, the FunCall object can call the getContents()
 * method to retrieve the Expr child nodes directly.
 */
public class ExprList extends Expr {

    private ArrayList<Expr> contents;

    public ExprList() {
	System.out.println("Constructing an ExprList");
	contents = new ArrayList<Expr>();
    }

    public void insert(Expr e) {
	System.out.println("Adding to an ExprList");
	contents.add(e);
    }

    public List<Expr> getContents() {
	return contents;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	System.out.println("Calling eval() in ExprList");
	
	PList result = new PList();
	for (Expr e : contents) {
	    result.push(e.eval(globals, locals));
	}
	return result;
    }
}
