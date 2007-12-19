package physicalc;

/** ReturnSignal is used to signal to a function call that a "return"
 * statement has been executed.  It carries the value to be returned
 * from the function.
 *
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public class ReturnSignal extends ControlSignal {

    private Datum value;

    public ReturnSignal(Datum returnValue) {
        value = returnValue;
    }

    public Datum getValue() {
	return value;
    }
}

