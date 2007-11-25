package physicalc;

import java.lang.String;

/** Id is a node implementing any source-code identifier.
 *
 * @see Node
 */
public class Id extends Expr {
    private String name;

    public Id(String idName) {
	name = idName;
    }

    public String getName() {
	return name;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	
	return new PString("Value of identifier '" + name + "'"); // remove

	/* look up "name" in "locals", return value if present */

	/* look up "name" in "globals", return value if present */

	/* if "name" isn't in "locals" or "globals", throw
	 * UndefinedError */
    }
}
