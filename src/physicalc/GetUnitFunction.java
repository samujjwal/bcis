package physicalc;

public class GetUnitFunction extends Function {

    public GetUnitFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		//System.out.println("Calling call() in GetUnitFunction");

		if (arguments.getContents().size() != 1) {
			throw new InterpreterError("Cannot call getUnit on more than one argument");
		}

		Expr expr = arguments.getContents().get(0);

		Datum pair = expr.eval(globals,locals);

		if (pair instanceof PUnitPair) {
			PUnit u = ((PUnitPair)pair).getUnit();
			u.setUnitMode();
			return u;
		} else {
			throw new InterpreterError("Must call GetUnitFunction on a UnitPair");
		}
    }

}