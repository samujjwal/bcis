package physicalc;

import java.util.HashMap;

/** A SymbolTable associates symbols (strings) with run-time objects
 * (functions, variables, units, or constants).
 *
 * Physicalc's symbol tables do not have a parent node, because
 * Physicalc has no nested scopes.  At any time there are exactly two
 * scopes in effect: a global scope for definitions and a local scope
 * for variables and function arguments.
 *
 * SymbolTable stores any object that implements the RuntimeObject
 * interface.  
 *
 * @see RuntimeObject
 */
public class SymbolTable {

    private HashMap<String, RuntimeObject> table;

    /** Creates a new, empty symbol table. */
    public SymbolTable() {
	table = new HashMap<String, RuntimeObject>();
    }

    /** Associates an object with a symbol in the symbol table.  If
     * "symbol" is already in the table, its value will be
     * overwritten.
     */
    public void put(String symbol, RuntimeObject object) {
	table.put(symbol, object);
    }

    /** Associates "newSymbol" with the value of "oldSymbol".  If
     * "oldSymbol" is not defined, throws an UndefinedError.
     *
     * Aliases are not references.  If "oldSymbol" is redefined to
     * point to a new object, the alias continues to point to the old
     * object.
     */
    public void putAlias(String newSymbol, String oldSymbol) {
	if (table.containsKey(oldSymbol)) {
	    table.put(newSymbol, table.get(oldSymbol));
	} else {
	    throw new UndefinedError(oldSymbol);
	}
    }

    /** Looks up and returns the value of "symbol" in the table.
     * Returns null if this table does not contain "symbol".
     *
     * Returns a generic RuntimeObject reference.  Callers of this
     * method must check the type of the returned object and cast it
     * appropriately.
     */
    public RuntimeObject get(String symbol) {
	return table.get(symbol);
    }
}
