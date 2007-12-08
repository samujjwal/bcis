package physicalc;

public class ToStringFunction extends Function {

    public ToStringFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		Datum number = arguments.eval(globals,locals);

		if (number instanceof PNumber) {
			return new PNumber( ((PNumber)number).toString() );
		} else {
			throw new InterpreterError("Cannot call ToString function on non-number");
		}
    }

}
