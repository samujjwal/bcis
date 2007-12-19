package physicalc;


/** A Constant stores a value globally.  It cannot be changed.
 *
 * @see SymbolTable
 * @see ConstantDef
 * @author Ici Li, il2117@columbia.edu
 */
public class Constant implements RuntimeObject {
    
    Datum constant1;

    public Constant() {
	constant1 = null;
    }

    public Constant(Datum initialValue) {
	constant1 = initialValue;
    }

    public Datum getValue() {
	return constant1; // remove
    }
}
