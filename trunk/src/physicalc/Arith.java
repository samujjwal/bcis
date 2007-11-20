package physicalc;

import java.lang.String;

/** Arith is a node implementing "+","-","*","/", and "^" 
 * 
 * @see Node
 */
public class Arith extends Op {
    private Expr left;
    private Expr right;
    private String op;

    public Arith(String operator, Expr leftOperand, Expr rightOperand) {
	op = operator;
	left = leftOperand;
	right = rightOperand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	Datum leftValue = left.eval(globals, locals);
	Datum rightValue = right.eval(globals, locals);

	/* Datum classes take care of type checking. */
	if (op.equals("+")) { 
	    return leftValue.add(rightValue);
	} else if (op.equals("-")) {
	    return leftValue.sub(rightValue);
	} else if (op.equals("*")) {
	    return leftValue.mul(rightValue);
	} else if (op.equals("/")) {
	    return leftValue.div(rightValue);
	} else if (op.equals("^")) {
	    return leftValue.pow(rightValue);
	} else {
	    /* This will only happen if the tree walker is wrong. */
	    throw new InterpreterError("GHASTLY ERROR: Arith class with invalid operator.");
	}
    }
}
