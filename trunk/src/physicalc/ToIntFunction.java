package physicalc;

/**
 * @author Brian Foo, bwf2101@columia.edu
 */
public class ToIntFunction extends Function {

    public ToIntFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

	if (arguments.getContents().size() != 1) {
	    throw new InterpreterError("Cannot call toInt on more than one argument");
	}
	

	Expr expr = arguments.getContents().get(0);
	
	Datum number = expr.eval(globals,locals);

	if (number instanceof PNumber) {
	    return new PNumber( ((PNumber)number).toInt() );
	} else {
	    throw new InterpreterError("Cannot call ToInt function on non-number");
	}
    }

}