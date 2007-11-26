package physicalc;

public class ReturnSignal extends ControlSignal {

    private Datum value;

    public ReturnSignal(Datum returnValue) {
        value = returnValue;
    }

    public Datum getValue() {
	return value;
    }
}

