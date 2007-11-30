package physicalc;

public class Return extends Stmt {

	private Expr returnVal;

    public Return(Expr rv) {
        returnVal = rv;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
        throw new ReturnSignal(returnVal.eval(globals,locals));
    }
}

