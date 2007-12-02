package physicalc;

import java.lang.String;

/** Set implements the "set" statement.
 *
 * @see LValue
 * @see Node
 */
public class Set extends Stmt {

    public Set(LValue lvalue, Expr valueExpression) {
	// TODO
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	/* Eval the expression and save its value. */

	/* Call setValue() on the "lvalue" you got in the constructor,
	 * passing in the symbol tables and the value from above. */
	return null;
    }
}

