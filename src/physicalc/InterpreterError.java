package physicalc;

import java.lang.String;

/** General parent class for all errors generated by user code. */
class InterpreterError extends RuntimeException {

    private String message;

    public InterpreterError() {; }

    public InterpreterError(String errorMessage) {
	message = errorMessage;
    }

    public String toString() {
	return message;
    }
}