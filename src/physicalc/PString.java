package physicalc;

import java.lang.*;

/** PString is the string data class which is
	basically a front for java's String class */
public class PString extends Datum  {

	protected String sValue;

	public PString () {
		sValue = "";
	}

	public PString (String s) throws NumberFormatException {
		sValue = s;
	}

    /** Returns the result of this + that.  Does not modify this. */
    public PString add(PString that) throws TypeError {
		return new PString(sValue+that.sValue);
    }

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
		if (that instanceof PString) {
			return equals((PString) that);
		}
	    return false;
    }

    /** Returns true if "that" has the same value as this. */
	private boolean equals(PString that) {
		return sValue.compareTo(that.sValue) == 0;
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return (!sValue.equals(false));
    }

    public PBoolean lessThan(PString that) throws TypeError {
		return new PBoolean( sValue.compareTo(that.sValue) < 0 );
    }

    public PBoolean lessEqual(PString that) throws TypeError {
		return new PBoolean( sValue.compareTo(that.sValue) <= 0 );
    }

    public PBoolean greaterThan(PString that) throws TypeError {
		return new PBoolean( sValue.compareTo(that.sValue) > 0 );
    }

    public PBoolean greaterEqual(PString that) throws TypeError {
		return new PBoolean( sValue.compareTo(that.sValue) >= 0 );
    }

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		return sValue.toString();
    }

}