package physicalc;

public class GetUnitFunction extends Function {

    public GetUnitFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		Datum pair = arguments.eval(globals,locals);

		if (pair instanceof PUnitPair) {
			return ((PUnitPair)pair).getUnit();
		} else {
			throw new InterpreterError("Must call GetUnitFunction on a UnitPair");
		}
    }

}