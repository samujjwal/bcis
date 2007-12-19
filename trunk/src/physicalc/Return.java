package physicalc;

/**
 * @author Ici Li, il2117@columbia.edu
 */
public class Return extends Stmt {

	private Expr returnVal;

    public Return(Expr rv) {
        returnVal = rv;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
        throw new ReturnSignal(returnVal.eval(globals,locals));
    }
}

