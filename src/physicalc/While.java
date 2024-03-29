package physicalc;

import java.lang.*;

/** While Statement
 *  While expr1 do
 *        block1
 *  done
 *
 * @author Changlong Jiang cj2214@columbia.edu
 * @author Stuart Sierra, ss2806@columbia.edu
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
      while (expr1.eval(globals,locals).isTrue()) { 
		try {
		    block1.eval(globals,locals);
		}
		catch (BreakSignal breaksignal)
		{
			break;
		}	 
		catch (NextSignal nextsignal)
		{ 
			continue;
		}

      }
    	return null;
    }
}
