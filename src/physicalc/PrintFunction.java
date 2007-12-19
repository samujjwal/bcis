package physicalc;

/**
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class PrintFunction extends Function {

    public PrintFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {
	//System.out.println("Calling call() in PrintFunction");
	
	for ( Expr expr : arguments.getContents() ) {
	    System.out.print( expr.eval(globals, locals).toString() );
	}
	System.out.println();
	return null;
    }

}
