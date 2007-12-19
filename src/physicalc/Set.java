package physicalc;

import java.lang.String;

/**
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class Set extends Stmt {

    LValue lvalue;
    Expr valueExpr;

    public Set(LValue place, Expr valueExpression) {
        lvalue = place;
	valueExpr = valueExpression;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	lvalue.setValue(globals, locals, valueExpr.eval(globals,locals));
	return null;
    }
}

