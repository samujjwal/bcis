package physicalc;


/** A Unit stores a value locally.  Unit are created and
 * modified with the "set" statement.  The name of the Unit is
 * stored in the SymbolTable.  The value of a Unit can be changed.
 *
 * @see SymbolTable
 * @see Set
 * @author Brian Foo, bwf2101@columia.edu
 */
public class Unit implements RuntimeObject {

	PUnit unit;

    public Unit() {
		unit = new PUnit();
    }

    public Unit(String id) {
		//System.out.println("Creating Base Unit");
		unit = new PUnit(id);
	}

    public Unit(String id,Datum initialValue) {
		//System.out.println("Creating Derived Unit");
		if ( initialValue instanceof PUnit ) {
			unit = (PUnit) initialValue;
		} else if (initialValue instanceof PUnitPair ) {
			unit = new PUnit(id,(PUnitPair)initialValue);
		} else {
			throw new InterpreterError("Unit initialized with illegal expression");
		}
    }

    public Datum getValue() {
		return unit;
    }

    public Datum setValue(String id,Datum newValue) {
		if ( newValue instanceof PUnitPair ) {
			unit = new PUnit(id,(PUnitPair)newValue);
			return unit;
		} else if (newValue instanceof PUnit) {
			unit = (PUnit) newValue;
			return unit;
		} else {
			throw new InterpreterError("Unit set with non-unit object");
		}
    }

    public Datum setValue(String id) {
		unit = new PUnit(id);
		return unit;
	}
}
