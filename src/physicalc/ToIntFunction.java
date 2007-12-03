package physicalc;

public class ToIntFunction extends Function {

    public ToIntFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		Datum number = arguments.eval(globals,locals);

		if (number instanceof PNumber) {
			return new PNumber( ((PNumber)number).toInt() );
		} else {
			throw new InterpreterError("Cannot call ToInt function on non-number");
		}
    }

}