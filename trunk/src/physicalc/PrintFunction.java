package physicalc;

public class PrintFunction extends Function {

    public PrintFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {
	for ( Expr expr : arguments.getContents() ) {
	    System.out.print( expr.eval(globals, locals).toString() );
	}
	System.out.println();
	return null;
    }

}
