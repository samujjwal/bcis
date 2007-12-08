package physicalc;

public class AliasDef extends Def {

    private String newSymb;
    private String oldSymb;

    public AliasDef(String newSymbol, String oldSymbol) {
        newSymb = newSymbol;
	oldSymb = oldSymbol;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
        //lookup old symbol in global symbol table
	//then, if it's not defined, throw an error
	//add new entry to global symbol table with newSymb as the symbol
	//value as value

	RuntimeObject R = globals.get(oldSymb);
	
	if(R == null) {
	    throw new UndefinedError(oldSymb);
	}
	else {
	    globals.put(newSymb, R);
	}

	return null;
    }
}

