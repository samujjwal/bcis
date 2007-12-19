package physicalc;

import java.lang.*;
import java.util.*;

/** A Function object stores a user-defined function.  Sub-classes may
 * implement built-in functions.
 *
 * @see SymbolTable
 * @see FunCall
 * @author Brian Foo, bwf2101@columia.edu
 */
public class Function implements RuntimeObject {

	private ParamList parameterList;
	private Block bodyStatements;

    /** Protected default constructor; only built-in function may be
     * created without a parameter list or block. */
    protected Function() { ; }

    public Function(ParamList pl, Block bs) {
		parameterList = pl;
		bodyStatements = bs;
    }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {


	/* Check that the arguments list is the same length as the
	 * parameter list; throw error if it's not. */
	 if ( arguments.getContents().size() != parameterList.getContents().size() ) {
		throw new InterpreterError("Function called with improper number of arguments");
	 }

	 /* Create a new SymbolTable for local variables.  */
	 SymbolTable function_locals = new SymbolTable();

	/* For each name in the parameter list, create a new Variable
	 * and add it to the local SymbolTable you just created */
	 for (Iterator it = parameterList.getContents().iterator(); it.hasNext(); ) {
		RuntimeObject r = new Variable();
		function_locals.put( (String) it.next(), r );
	 }

	/* Evaluate each argument in the ExprList and assign its value
	 * to one of the local Variables you just created. */
	 Iterator argIt = arguments.getContents().iterator();
	 for (Iterator it2 = parameterList.getContents().iterator(); it2.hasNext(); ) {
		((Variable)function_locals.get((String) it2.next())).setValue(((Expr)argIt.next()).eval(globals,locals));
	 }

	/* Call "eval" on the Block of body statements, passing in the
	 * global symbol table and the local symbol table you created.
	 * Return the value of "eval". */
	 try {
	 	return bodyStatements.eval(globals,function_locals);
	 } catch (ReturnSignal rs) {
		return rs.getValue();
	 }

    }
}
