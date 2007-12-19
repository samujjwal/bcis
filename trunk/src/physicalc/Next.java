package physicalc;

/** "next" statement
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class Next extends Stmt {

    public Next() {
        ;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	throw new NextSignal();
    }
}

