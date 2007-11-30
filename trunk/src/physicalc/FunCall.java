package physicalc;

import java.lang.*;
import java.util.*;

/** FunCall implements a function call.
 *
 * @see Node
 * @see Function
 */
public class FunCall extends Expr {

	private String functionName;
	private ExprList argumentList;
	private Function func;

    public FunCall(String f, ExprList al) {
		functionName = f;
		argumentList = al;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {

	System.err.println("Calling eval() in FunCall");

	/* Look up functionName in the global symbol table,
	 * throw UndefinedError if it's not there. */
	 if ( globals.get(functionName).equals(null) ) {
		throw new UndefinedError(functionName);
	 }

	/* Get the object out of the symbol table, check that it's an
	 * instanceof Function, then cast it to a Function. */
	 if ( globals.get(functionName) instanceof Function ) {
		 func = (Function) globals.get(functionName);
	 }

	/* Call the function's "call" method, passing in the global
	 * symbol table and the argument list. */
	 return func.call(globals,locals,argumentList);

    }
}
