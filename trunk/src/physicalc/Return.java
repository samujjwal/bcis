package physicalc;

public class Return extends Stmt {

	private Expr returnVal;

    public Return(Expr rv) {
        returnVal = rv;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
        return returnVal.eval(globals,locals);
    }
}

