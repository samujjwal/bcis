package physicalc;

/** Not is a node implementing the "not" logical operator.
 *
 * @see Node
 *
 * @author Stuart Sierra, ss2806@columbia.edu
 * @author Changlong Jiang cj2214@columbia.edu
  */
public class Not extends Logical {
    private Expr oper;
    
    public Not(Expr Operand) {
	oper = Operand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	
	if(oper.eval(globals, locals).isTrue()) {
		return new PBoolean(false);
	}
	else {
		return new PBoolean(true);
	}
    }
}
