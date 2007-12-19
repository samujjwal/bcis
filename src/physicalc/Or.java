package physicalc;

/** Or is a node implementing the "or" logical operator.
 *
 * @see Node
 * @author Ici Li, il2117@columbia.edu
 */
public class Or extends Expr {
    private Expr left;
    private Expr right;

    public Or(Expr leftOperand, Expr rightOperand) {
	left = leftOperand;
	right = rightOperand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	if ((left.eval(globals, locals).isTrue())) {
	    /* short-circuit if left operand is true */
	    return new PBoolean(true);
	} else if ((right.eval(globals, locals).isTrue())) {
	    return new PBoolean(true);
	} else {
            return new PBoolean(false);
	}
    }
}
