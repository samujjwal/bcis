package physicalc;

/** this is If Class
 *  Syntax: if expression1 then
 *             statements1
 *          elsif expression2 then
 *             statements2
 *			else
 *			   statements3
 *			done
 */
public class If extends Stmt {

    private Expr expr1;
    private Block block1;
    private Block block2;
    public If(Expr condition, Block thenBlock, Block elseBlock) {
	expr1 = condition;
	block1 = thenBlock;
	block2 = elseBlock;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	System.err.println("Calling eval() in If");

        if (expr1.eval(globals, locals).isTrue()) {
	    System.err.println("'If' condition was true; executing 'then' block.");
	    
	    return block1.eval(globals,locals);
	}
	else { 
	    return block2.eval(globals,locals);
        }
		
    }
}


