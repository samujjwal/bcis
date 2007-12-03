package physicalc;

public class GetNumberFunction extends Function {

    public GetNumberFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		Datum pair = arguments.eval(globals,locals);

		if (pair instanceof PUnitPair) {
			return ((PUnitPair)pair).getNumber();
		} else {
			throw new InterpreterError("Must call GetNumberFunction on a UnitPair");
		}
    }

}