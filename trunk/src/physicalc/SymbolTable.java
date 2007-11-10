package physicalc;

import java.util.HashMap;
import java.util.Map;

/** A SymbolTable associates symbols (strings) with run-time objects
 * (functions, variables, units, or constants).
 *
 * Physicalc's symbol tables do not have a parent node, because
 * Physicalc has no nested scopes.  At any time there are exactly two
 * scopes in effect: a global scope for definitions and a local scope
 * for variables and function arguments.
 *
 * @see RuntimeObject
 */
public class SymbolTable {

    private Map<String, RuntimeObject> table;

    public SymbolTable() {
	table = new HashMap<String, RuntimeObject>();
    }

    public void put(String symbol, RuntimeObject object) {
	table.put(symbol, object);
    }

    public void putAlias(String newSymbol, String oldSymbol) {
	if (table.containsKey(oldSymbol)) {
	    table.put(newSymbol, table.get(oldSymbol));
	} else {
	    throw new UndefinedError(oldSymbol);
	}
    }

    public RuntimeObject get(String symbol) {
	return table.get(symbol);
    }
}
