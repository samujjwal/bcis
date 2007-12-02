package physicalc;

import java.lang.*;
import java.util.*;

/** Access implements list access with [] subscripts.
 *
 * @see Node
 * @see PList
 * @see Set
 */
public class Access extends Expr implements LValue {

    private String id;
    private ExprList subscripts;

    public Access(String identifier, ExprList subExprs) {
	System.err.println("Constructing an Access");
	id = identifier;
	subscripts = subExprs;
    }

    public Datum eval(SymbolTable globals, SymbolTable locals) {
	System.err.println("Calling eval() in Access");

	/* Look up id in the symbol tables -- global first, then local
	 * -- or throw UndefinedError it it's not in either. */
	RuntimeObject r;
	r = globals.get(id);
	if (r == null) {
	    r = locals.get(id);
	    if (r == null) {
		throw new UndefinedError(id);
	    }
	}
	

	/* Get the value stored in the symbol table, check that it is
	 * an instanceof Variable.  If not, throw an
	 * InterpreterError. */
	Datum value;
	if (r instanceof Variable) {
	    /* Cast the object from the symbol table to a Variable. */
	    Variable var = (Variable)r;
	    value = var.getValue();
	} else if (r instanceof Constant) {
	    Constant constant = (Constant)r;
	    value = constant.getValue();
	} else {
	    throw new InterpreterError("Symbol '" + id + "' is not a variable.");
	}
	

	int index;
	for (Expr e : subscripts.getContents()) {
	    index = ((PNumber)e.eval(globals,locals)).toInt();
	    if (value instanceof PList) {
		value = ((PList)value).getIndex(index);
	    } else {
		throw new InterpreterError("Tried to access element in a non-list.");
	    }
	}


	return value;
    }



    public void setValue(SymbolTable globals, SymbolTable locals,
			 Datum newValue) {
	System.err.println("Calling setValue() in Access");

	/* Look up id in the local symbol table, or throw
	 * UndefinedError it it's not there. */
	RuntimeObject r;
	r = locals.get(id);
	if (r == null) {
	    throw new UndefinedError(id);
	}
	

	/* Get the value stored in the symbol table, check that it is
	 * an instanceof Variable.  If not, throw an
	 * InterpreterError. */
	Variable var;
	if (r instanceof Variable) {
	    /* Cast the object from the symbol table to a Variable. */
	    var = (Variable)r;
	} else {
	    throw new InterpreterError("Symbol '" + id + "' is not a variable.");
	}
	

	Datum value = var.getValue();
	PList list = null;
	int index = 0;
	for (Expr e : subscripts.getContents()) {
	    index = ((PNumber)e.eval(globals,locals)).toInt();
	    if (value instanceof PList) {
		list = (PList)value;
		try {
		    value = ((PList)value).getIndex(index);
		} catch (java.lang.IndexOutOfBoundsException error) {
		    value = null;
		}
	    } else {
		throw new InterpreterError("Tried to access element in a non-list.");
	    }
	}

	if (list == null) {
		throw new InterpreterError("Tried to access element in a non-list.");	    
	} else {
	    list.set(index, newValue);
	}	    
    }

}
