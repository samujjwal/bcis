package physicalc;

import java.lang.*;

/** While Statement
 *  While expr1 do
 *        block1
 *  done
 */

public class While extends Stmt {

	private Expr expr1;
	private Block block1;

	public While() { expr1 = null; block1 =null;}

    public While(Expr testExpr, Block block) {
        expr1 = testExpr;
		block1 = block;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
        if (expr1.eval(globals,locals).isTrue())
         	return block1.eval(globals,locals);
	/* this is for test purpose */
	//	else
	//		System.err.println("Calling eval() in Block");
    return null;
    }

	
}

