package physicalc;

/** @author Ici Li, il2117@columbia.edu */
public class ExitFunction extends Function {

    public ExitFunction() { ; }
    
    public Datum call(SymbolTable globals, SymbolTable locals, ExprList arguments) {
//      System.err.println("Calling call() in ExitFunction");
      System.exit(0);
      return null;  
  }
   
}
