package physicalc;

/** Unary is a node implementing the unary minus operator.  It returns
 * the numeric negative of its argument.
 *
 * @see Node
 * @author Ici Li, il2117@columbia.edu
 */
public class Unary extends Op {
    private Expr op;
    
    public Unary(Expr operand) {
	op = operand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	Datum oper = op.eval(globals, locals);
	
	return oper.neg();
    }
}
