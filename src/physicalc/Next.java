package physicalc;

public class Next extends Stmt {

    public Next() {
        ;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	throw new NextSignal();
    }
}

