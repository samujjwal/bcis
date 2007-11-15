package physicalc;

import java.lang.*;

public class PBoolean extends Datum {

    protected Boolean boolValue;

    public PBoolean() {
		boolValue = new Boolean(false);
	}

    public PBoolean(boolean value) {
		boolValue = new Boolean(value);
    }

    public boolean isTrue() {
		return boolValue.booleanValue();
    }

    public String toString() {
		return boolValue.toString();
	}
}
