package physicalc;


/** A Variable stores a value locally.  Variable are created and
 * modified with the "set" statement.  The name of the Variable is
 * stored in the SymbolTable.  The value of a Variable can be changed.
 *
 * @see SymbolTable
 * @see Set
 */
public class Variable implements RuntimeObject {

    public Variable() {
	;
    }

    public Variable(Datum initialValue) {
	;
    }

    public Datum getValue() {
	return null; // remove
    }

    public Datum setValue(Datum newValue) {
	return null; // remove
    }
}
