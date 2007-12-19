package physicalc;

import java.lang.String;

/** Rel is a node implementing any relational operator, including
 * equals and not-equals.
 *
 * @see Node
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class Rel extends Logical {
    private Expr left;
    private Expr right;
    private String op;

    public Rel(String operator, Expr leftOperand, Expr rightOperand) {
	op = operator;
	left = leftOperand;
	right = rightOperand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	Datum leftValue = left.eval(globals, locals);
	Datum rightValue = right.eval(globals, locals);

	/* Datum classes take care of type checking. */
	if (op.equals("=")) {
	    /* equals() returns a Java boolean; we must create a
	     * PBoolean to match our return type. */
	    return new PBoolean(leftValue.equals(rightValue));
	} else if (op.equals("!=")) {
	    /* Same thing, but take the logical opposite. */
	    return new PBoolean(!(leftValue.equals(rightValue)));
	} else if (op.equals("<")) {
	    return leftValue.lessThan(rightValue);
	} else if (op.equals("<=")) {
	    return leftValue.lessEqual(rightValue);
	} else if (op.equals(">")) {
	    return leftValue.greaterThan(rightValue);
	} else if (op.equals(">=")) {
	    return leftValue.greaterEqual(rightValue);
	} else {
	    /* This will only happen if the tree walker is wrong. */
	    throw new InterpreterError("GHASTLY ERROR: Rel class with invalid operator.");
	}
    }
}
