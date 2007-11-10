package physicalc;

public class UndefinedError extends InterpreterError {
    private String symbol;

    public UndefinedError(String undefinedSymbol) {
	symbol = undefinedSymbol;
    }

    public String toString() {
	return "'" + symbol + "' is not defined";
    }
}
