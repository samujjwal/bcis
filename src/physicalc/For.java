package physicalc;

import java.lang.String;
/* this is for class 
   for identifier from expressions1 to experssion2 step expression3 do
         statements
   done

*/

public class For extends Stmt {
    String idname;
    Expr expr1,expr2,expr3;
    Block block1;

    public For(String id,Expr fromExpr, Expr toExpr, Expr stepExpr,
	       Block b) {
	idname = id;
	expr1 = fromExpr;
	expr2 = toExpr;
	expr3 = stepExpr;
	block1 = b;
    }



    public Datum eval(SymbolTable globals, SymbolTable locals) {
	
        Datum from = fromExpr.eval(globals,locals);
	Datum to = toExpr.eval(globals,locals);
	Datum step = stepExpr.eval(globals,locals);

        Id id = new Id(idname);
	
	id.setValue(globals, locals, from);

	while( id.eval(globals,locals).lessEqual(to)){


	  try {
		block1.eval(globals,locals);
	  }
	  catch (BreakSignal breaksignal){
		break;
          }
	  catch (NextSignal nextsignal) {
		continue;
          }
	
	    id.setValue(globals,locals, id.eval(globals,locals).add(step));

	}
        return null; // remove
    }
}

