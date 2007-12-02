package physicalc;

/** LValue is an interface implemented by any Node that can be
 * assigned a value in a "set" statement.  Identifiers (i.e. instances
 * of Id) and list items (i.e. instances of Access) are both LValues.
 *
 * @see Id
 * @see Access
 * @see Set
 */
public interface LValue {

    public void setValue(SymbolTable globals, SymbolTable locals,
			 Datum newValue);
}
