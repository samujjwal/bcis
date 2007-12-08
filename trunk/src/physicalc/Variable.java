package physicalc;


/** A Variable stores a value locally.  Variable are created and
 * modified with the "set" statement.  The name of the Variable is
 * stored in the SymbolTable.  The value of a Variable can be changed.
 *
 * @see SymbolTable
 * @see Set
 */
public class Variable implements RuntimeObject {

    Datum var;

    public Variable() {
	var = null;
    }

    public Variable(Datum initialValue) {
	var = initialValue;
    }

    public Datum getValue() {
	return var;
    }

    public Datum setValue(Datum newValue) {
	var = newValue;
	return var;
    }
}
