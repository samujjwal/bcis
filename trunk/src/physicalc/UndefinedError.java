package physicalc;

/** Error when an undefined symbol is used. 
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class UndefinedError extends InterpreterError {
    private String symbol;

    public UndefinedError(String undefinedSymbol) {
	symbol = undefinedSymbol;
    }

    public String toString() {
	return "'" + symbol + "' is not defined";
    }
}
