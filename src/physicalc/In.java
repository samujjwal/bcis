package physicalc;

/** In is a node implementing the "in" unit-conversion operator.
 *
 * @see Node
 */
public class In extends Op  {
    private Expr left;
    private Expr right;

    public In(Expr leftOperand, Expr rightOperand) {
	left = leftOperand;
	right = rightOperand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	return null; // TODO: replace
    }
}
