package physicalc;

/** In is a node implementing the "in" unit-conversion operator.
 *
 * @see Node
 */
public class In extends Op  {
    private Expr left;
    private Expr right;

    public In(Expr leftOperand, Expr rightOperand) {
	left = leftOperand;
	right = rightOperand;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {

		Datum leftUnit = left.eval(globals,locals);
		Datum rightUnit = left.eval(globals,locals);

		if ( (leftUnit instanceof PUnit) && (rightUnit instanceof PUnit) ) {

			String fromName = ((PUnit)leftUnit).getName();
			String toName = ((PUnit)rightUnit).getName();

			String fromBase = ((PUnit)leftUnit).getBaseUnit();
			String toBase = ((PUnit)rightUnit).getBaseUnit();

			PNumber fromConversion = ((PUnit)leftUnit).getConversion();
			PNumber toConversion = ((PUnit)rightUnit).getConversion();

			if ( fromBase.equals(toBase) ) {
				return new PUnitPair( fromConversion.div(toConversion), new PUnit(toName) );
			} else {
				throw new InterpreterError("Base units do not match with '"+fromName+" in "+toName+"'");
			}

		} else {
			throw new InterpreterError("Cannot use 'in' operator with non-unit objects");
		}

    }
}
