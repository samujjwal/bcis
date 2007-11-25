package physicalc;

import java.lang.String;

/** FunCall implements a function call.
 * 
 * @see Node
 * @see Function
 */
public class FunCall extends Expr {

    public FunCall(String functionName, ExprList argumentList) {
	;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	return new PString("Result of a FunCall"); // remove

	/* Look up functionName in the global symbol table,
	 * throw UndefinedError if it's not there. */

	/* Get the object out of the symbol table, check that it's an
	 * instanceof Function, then cast it to a Function. */

	/* Call the function's "call" method, passing in the global
	 * symbol table and the argument list. */
    }
}
