package physicalc;

import java.lang.String;

/** Id is a node implementing any source-code identifier.  It also
 * implements LValue, so it can be assigned in a "Set" statement.
 *
 * @see Node
 */
public class Id extends Expr implements LValue {

    private String name;

    public Id(String idName) {

	name = idName;	
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	
	/* Look up idName in the local symbol table, or throw
	 * UndefinedError it it's not there. */

	/* Check the type of the object you got with instanceof.  If
	 * it's a Variable or Constant, get the value and return it.
	 * If it's a Unit, return the Unit.  Anything else, throw an
	 * InterpreterError. */
	
	RuntimeObject R = locals.get(name);

	if(R == null) {
	    R = globals.get(name);
	}
	if(R == null) {
	    throw new UndefinedError(name);
	}

	if(R instanceof Variable) {
	    return ((Variable)R).getValue();
	} else if (R instanceof Constant) {
	    return ((Constant)R).getValue();
	}
	else if(R instanceof Unit) {
	    return ((Unit)R).getValue();
	}
	else {
		throw new InterpreterError("Tried to get value of a non-Variable/Constant/Unit");
	}
    }

    public void setValue(SymbolTable globals, SymbolTable locals,
			 Datum newValue) {
	//System.out.println("Calling setValue() in Name");

	RuntimeObject r;
	r = locals.get(name);
	if (r == null) {
	    r = new Variable(newValue);
	    locals.put(name, r);
	} else if (r instanceof Variable) {
	    ((Variable)r).setValue(newValue);
	} else if (globals.get(name) != null) {
	    throw new InterpreterError("Tried to assign to a non-variable.");
	} else {
	    throw new UndefinedError(name);
	}
	    
    }
}
