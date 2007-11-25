package physicalc;

import java.lang.*;

/** TypeError is raised when an operation cannot be performed because
 * the types of its arguments are incompatible. */
public class TypeError extends InterpreterError {

    String errorMessage;

    public TypeError(String operation, Datum object1, Datum object2) {
		errorMessage = "Types are incompatible in the following operation:\n" + object1.toString() + " " + operation + " " + object2.toString() + "\n";
    }

    /* wrong types for instantiation */
    public TypeError(Datum found, String requires, Datum object) {
			errorMessage = "Cannot instantiate " + object.toString() + " with " + found.toString() + ", requires " + requires + "\n";
    }

    public String toString() {
		return errorMessage;
	}
}
