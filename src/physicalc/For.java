package physicalc;

import java.lang.String;

public class For extends Stmt {

    public For(String symbol,
	       Expr fromExpr, Expr toExpr, Expr stepExpr,
	       Block b) {
        ;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
        return null; // remove
    }
}

