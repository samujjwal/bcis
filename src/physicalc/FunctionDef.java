package physicalc;

/** Function Definitions
 * @author Brian Foo, bwf2101@columia.edu
 */
public class FunctionDef extends Def {

	private String id;
	private ParamList paramList;
	private Block bodyBlock;

    public FunctionDef(String i, ParamList pl, Block bb) {
        id = i;
        paramList = pl;
        bodyBlock = bb;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {

		RuntimeObject func = new Function(paramList,bodyBlock);
        globals.put(id,func);

        return null; // definitions always return null
    }
}

