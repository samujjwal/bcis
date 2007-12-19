package physicalc;

/**
 * @author Ici Li, il2117@columbia.edu
 */
public class ToStringFunction extends Function {

    public ToStringFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

	if (arguments.getContents().size() != 1) {
	    throw new InterpreterError("Cannot call toInt on more than one argument");
	}
	

	Expr expr = arguments.getContents().get(0);
	
		Datum string1 = expr.eval(globals,locals);

		return new PString(string1.toString());
    }

}
