package physicalc;

/** @author Ici Li, il2117@columbia.edu
 */
public class ConstantDef extends Def {

    private String id1;
    private Expr valueExpr1;
 
    public ConstantDef(String id, Expr valueExpr) {
        id1 = id;
	valueExpr1 = valueExpr;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	Constant c1 = new Constant(valueExpr1.eval(globals, locals));
	
	RuntimeObject R = c1;
	
	globals.put(id1, R);

        return null;
    }
}

