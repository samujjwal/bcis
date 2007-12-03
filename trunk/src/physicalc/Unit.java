package physicalc;


/** A Unit stores a value locally.  Unit are created and
 * modified with the "set" statement.  The name of the Unit is
 * stored in the SymbolTable.  The value of a Unit can be changed.
 *
 * @see SymbolTable
 * @see Set
 */
public class Unit implements RuntimeObject {

    public Unit() {
	;
    }

    public Unit(Datum initialValue) {
	;
    }

    public Datum getValue() {
	return null; // remove
    }

    public Datum setValue(Datum newValue) {
	return null; // remove
    }
}
