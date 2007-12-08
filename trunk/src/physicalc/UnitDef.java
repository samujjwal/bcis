package physicalc;

public class UnitDef extends Def {

	String id;
	Expr valueExpr;

    public UnitDef(String i) {
		id = i;
		valueExpr = null;
    }

    public UnitDef(String i, Expr val) {
        id = i;
        valueExpr = val;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {

		if (valueExpr.equals(null)) {
			locals.put(id,new Unit(id));
		} else {
			Datum val = valueExpr.eval(globals,locals);
			locals.put(id,new Unit(val));
		}

        return null; // definitions always return null
    }
}

