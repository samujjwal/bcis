package physicalc;


/** A Unit stores a value locally.  Unit are created and
 * modified with the "set" statement.  The name of the Unit is
 * stored in the SymbolTable.  The value of a Unit can be changed.
 *
 * @see SymbolTable
 * @see Set
 */
public class Unit implements RuntimeObject {

	PUnit unit;

    public Unit() {
		unit = new PUnit();
    }

    public Unit(Datum initialValue) {
		if ( initialValue instanceof PUnit ) {
			unit = (PUnit)initialValue;
		} else {
			throw new InterpreterError("Unit initialized with non-unit object");
		}
    }

    public Datum getValue() {
		return unit;
    }

    public Datum setValue(Datum newValue) {
		if ( newValue instanceof PUnit ) {
			unit = (PUnit)newValue;
			return unit;
		} else {
			throw new InterpreterError("Unit set with non-unit object");
		}
    }
}
