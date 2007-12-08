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
		System.err.println("Calling eval() in UnitDef");
		if (valueExpr == null) {
			System.err.println("Found Unit definition with null expression");
			locals.put(id,new Unit(id));
		} else {
			System.err.println("Found Unit definition with expression");
			Datum val = valueExpr.eval(globals,locals);
			locals.put(id,new Unit(val));
		}

        return null; // definitions always return null
    }
}

