package physicalc;

import java.lang.String;

/** Load implements the "load" statement.
 * 
 * @see Node
 */
public class Load extends Node {

    public Load(String filename) {
	;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	return null; // replace
    }
}
