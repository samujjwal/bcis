package physicalc;

public class ToStringFunction extends Function {

    public ToStringFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {

		Datum string1 = arguments.eval(globals,locals);

		return string1.toString();
    }

}
