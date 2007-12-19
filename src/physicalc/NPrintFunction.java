package physicalc;

/**
 * @author Ici Li, il2117@columbia.edu
 */
public class NPrintFunction extends Function {

    public NPrintFunction() { ; }

    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {
	//System.out.println("Calling call() in NPrintFunction");
	
	for ( Expr expr : arguments.getContents() ) {
	    System.out.print( expr.eval(globals, locals).toString() );
	}

	return null;
    }

}
