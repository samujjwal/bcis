package physicalc;

/** Literal is a node implementing any source-code literal, such as a
 * number or a list.
 *
 * @see Node
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class Literal extends Expr {
    private Datum value;

    public Literal(Datum theValue) {
	value = theValue;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	//System.out.println("Calling eval() in Literal");
	return value;
    }
}
