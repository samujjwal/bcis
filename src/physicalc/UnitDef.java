package physicalc;

/**
 * @author Brian Foo, bwf2101@columia.edu
 */
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
		//System.out.println("Calling eval() in UnitDef");
		if (valueExpr == null) {
			//System.out.println("Found Unit definition with null expression");
			globals.put(id,new Unit(id));
		} else {
			//System.out.println("Found Unit definition with expression");
			Datum val = valueExpr.eval(globals,locals);
			globals.put(id,new Unit(id,val));
		}

        return null; // definitions always return null
    }
}

