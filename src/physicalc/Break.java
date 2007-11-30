package physicalc;

public class Break extends Stmt {

    public Break() {
        ;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	throw new BreakSignal();
    }
}

