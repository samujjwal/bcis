package physicalc;


/** A Function object stores a user-defined function.  Sub-classes may
 * implement built-in functions.
 *
 * @see SymbolTable
 * @see FunCall
 */
public class Function implements RuntimeObject {

    public Function(ParamList parameterList, Block bodyStatements) {
	;
    }

    public Datum call(SymbolTable globals, ExprList arguments) {
	return null; // remove
	
	/* Check that the arguments list is the same length as the
	 * parameter list; throw error if it's not. */

	/* Create a new SymbolTable for local variables.  */

	/* For each name in the parameter list, create a new Variable
	 * and add it to the local SymbolTable you just created. */

	/* Evaluate each argument in the ExprList and assign its value
	 * to one of the local Variables you just created. */

	/* Call "eval" on the Block of body statements, passing in the
	 * global symbol table and the local symbol table you created.
	 * Return the value of "eval". */
    }
}
