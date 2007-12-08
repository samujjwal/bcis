package physicalc;

public class ExitFunction extends Function {

    public ExitFucntion() { ; }
    
    public Datum call(SymbolTable globals, SymbolTable locals, ExprList 
arguments) {
    System.err.println("Calling call() in ExitFunction");
    System.exit(0);
    }
    return null;
}
