package physicalc;

import java.lang.*;
import java.util.*;

/** A Function object stores a user-defined function.  Sub-classes may
 * implement built-in functions.
 *
 * @see SymbolTable
 * @see FunCall
 */
public class Function implements RuntimeObject {

	private ParamList parameterList;
	private Block bodyStatements;
	private SymbolTable locals;

    public Function(ParamList pl, Block bs) {
		parameterList = pl;
		bodyStatements = bs;
		/* Create a new SymbolTable for local variables.  */
		locals = new SymbolTable();
    }

    public Datum call(SymbolTable globals, ExprList arguments) {

	/* Check that the arguments list is the same length as the
	 * parameter list; throw error if it's not. */
	 if ( arguments.getContents().size() != parameterList.getContents().size() ) {
		throw new InterpreterError("Argument list does not match parameter list");
	 }

	/* For each name in the parameter list, create a new Variable
	 * and add it to the local SymbolTable you just created */
	 for (Iterator it = parameterList.getContents().iterator(); it.hasNext(); ) {
		RuntimeObject r = new Variable();
		locals.put( (String) it.next(), r );
	 }

	/* Evaluate each argument in the ExprList and assign its value
	 * to one of the local Variables you just created. */
	 Iterator argIt = arguments.getContents().iterator();
	 for (Iterator it2 = parameterList.getContents().iterator(); it2.hasNext(); ) {
		((Variable)locals.get((String) it2.next())).setValue(((Expr)argIt.next()).eval(globals,locals));
	 }

	/* Call "eval" on the Block of body statements, passing in the
	 * global symbol table and the local symbol table you created.
	 * Return the value of "eval". */
	 return bodyStatements.eval(globals,locals);

    }
}
