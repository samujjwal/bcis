package physicalc;

public class GetNumberFunction extends Function {

    public GetNumberFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		System.err.println("Calling call() in GetNumberFunction");

		if (arguments.getContents().size() != 1) {
			throw new InterpreterError("Cannot call getNumber on more than one argument");
		}

		Expr expr = arguments.getContents().get(0);

		Datum pair = expr.eval(globals,locals);

		if (pair instanceof PUnitPair) {
			return ((PUnitPair)pair).getNumber();
		} else {
			throw new InterpreterError("Must call GetNumberFunction on a UnitPair");
		}
    }

}