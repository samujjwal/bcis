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

		System.err.println("Calling eval() in In");

		Datum leftUnit = left.eval(globals,locals);
		Datum rightUnit = right.eval(globals,locals);

		if (rightUnit instanceof PUnit) {

			String fromName;
			String fromBase;
			PNumber fromConversion;
			PNumber fromNumber;

			String toName = ((PUnit)rightUnit).getName();
			String toBase = ((PUnit)rightUnit).getBaseUnit();
			PNumber toConversion = ((PUnit)rightUnit).getConversion();

			if (leftUnit instanceof PUnit) {
				fromName = ((PUnit)leftUnit).getName();
				fromBase = ((PUnit)leftUnit).getBaseUnit();
				fromConversion = ((PUnit)leftUnit).getConversion();
				fromNumber = new PNumber("1");
			} else if (leftUnit instanceof PUnitPair) {
				fromName = ((PUnitPair)leftUnit).getUnit().getName();
				fromBase = ((PUnitPair)leftUnit).getUnit().getBaseUnit();
				fromConversion = ((PUnitPair)leftUnit).getUnit().getConversion();
				fromNumber = ((PUnitPair)leftUnit).getNumber();
			} else {
				throw new InterpreterError("Left operand in 'in' must be a unit or number*unit");
			}

			System.err.println("toName: "+toName+", toBase: "+toBase+", toConv: "+toConversion.toString() );
			System.err.println("fromName: "+fromName+", fromBase: "+fromBase+", fromConv: "+fromConversion.toString()+", fromNumber: "+fromNumber.toString());

			if ( fromBase.equals(toBase) ) {
				return new PUnitPair( ((fromConversion.mul(fromNumber)).div(toConversion)), (PUnit)rightUnit );
			} else {
				throw new InterpreterError("Base units do not match with '"+fromName+" in "+toName+"'");
			}

		} else {
			throw new InterpreterError("Right operand in 'in' must be a unit");
		}

    }
}
