package physicalc;


/** A Unit stores a value locally.  Unit are created and
 * modified with the "set" statement.  The name of the Unit is
 * stored in the SymbolTable.  The value of a Unit can be changed.
 *
 * @see SymbolTable
 * @see Set
 */
public class Unit implements RuntimeObject {

	PUnitPair unit;

    public Unit() {
		unit = new PUnitPair();
    }

    public Unit(String id) {
		//System.out.println("Creating Base Unit");
		unit = new PUnitPair(new PNumber("1"),new PUnit(id));
	}

    public Unit(Datum initialValue) {
		//System.out.println("Creating Derived Unit");
		if ( initialValue instanceof PUnit ) {
			unit = new PUnitPair(new PNumber("1"),(PUnit) initialValue);
		} else if (initialValue instanceof PUnitPair ) {
			unit = (PUnitPair) initialValue;
		} else {
			throw new InterpreterError("Unit initialized with illegal expression");
		}
    }

    public Datum getValue() {
		return unit;
    }

    public Datum setValue(Datum newValue) {
		if ( newValue instanceof PUnitPair ) {
			unit = (PUnitPair)newValue;
			return unit;
		} else if (newValue instanceof PUnit) {
			unit.setUnit((PUnit) newValue);
			unit.setNumber(new PNumber("1"));
			return unit;
		} else {
			throw new InterpreterError("Unit set with non-unit object");
		}
    }

    public Datum setValue(String id) {
		unit.setUnit(new PUnit(id));
		unit.setNumber(new PNumber("1"));
		return unit;
	}
}
