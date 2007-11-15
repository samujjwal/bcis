package physicalc;

import java.lang.*;
import java.lang.Math;

/** PNumber is the number data class which includes
	intergers, decimals, and exponents.
	This is similar to the abstract Number class in java,
	but will convert all types into a Double object before
	algebraic processing */
public class PNumber extends Datum  {

	protected Double numValue;

	public PNumber () {
		numValue = new Double(0);
	}

	public PNumber (int number) {
		Integer n = new Integer(number);
		numValue = new Double(n.doubleValue());
	}

	public PNumber (float number) {
		Float n = new Float(number);
		numValue = new Double(n.doubleValue());
	}

	public PNumber (double number) {
		numValue = new Double(number);
	}

	public PNumber (long number) {
		Long n = new Long(number);
		numValue = new Double(n.doubleValue());
	}

	public PNumber (short number) {
		Short n = new Short(number);
		numValue = new Double(n.doubleValue());
	}

	public PNumber (Integer number) {
		numValue = new Double(number.doubleValue());
	}

	public PNumber (Float number) {
		numValue = new Double(number.doubleValue());
	}

	public PNumber (Double number) {
		numValue = number;
	}

	public PNumber (Long number) {
		numValue = new Double(number.doubleValue());
	}

	public PNumber (Short number) {
		numValue = new Double(number.doubleValue());
	}

	public PNumber (String number) throws NumberFormatException {
		numValue = new Double(number);
	}

    /** Returns the result of this + that.  Does not modify this. */
    public PNumber add(PNumber that) throws TypeError {
		return new PNumber(numValue.doubleValue() + that.numValue.doubleValue());
    }

    /** Returns the result of this - that.  Does not modify this. */
    public PNumber sub(PNumber that) throws TypeError {
		return new PNumber(numValue.doubleValue() - that.numValue.doubleValue());
    }

    /** Returns the result of this * that.  Does not modify this. */
    public PNumber mul(PNumber that) throws TypeError {
		return new PNumber(numValue.doubleValue() * that.numValue.doubleValue());
    }

    /** Returns the result of this / that.  Does not modify this. */
    public PNumber div(PNumber that) throws TypeError {
		return new PNumber(numValue.doubleValue() / that.numValue.doubleValue());
    }

    /** Returns the result of this ^ that.  Does not modify this. */
    public PNumber pow(PNumber that) throws TypeError {
		return new PNumber(java.lang.Math.pow(numValue.doubleValue() ,that.numValue.doubleValue()));
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public PNumber neg() throws TypeError {
		return new PNumber(numValue.doubleValue() * -1);
    }

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
		if (that instanceof PNumber) {
			return equals((PNumber) that);
		}
	    return false;
    }

    /** Returns true if "that" has the same value as this. */
	private boolean equals(PNumber that) {
		return numValue.doubleValue() == that.numValue.doubleValue();
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return !numValue.isNaN();
    }

    public PBoolean lessThan(PNumber that) throws TypeError {
		return new PBoolean( numValue < that.numValue );
    }

    public PBoolean lessEqual(PNumber that) throws TypeError {
		return new PBoolean( numValue <= that.numValue );
    }

    public PBoolean greaterThan(PNumber that) throws TypeError {
		return new PBoolean( numValue > that.numValue );
    }

    public PBoolean greaterEqual(PNumber that) throws TypeError {
		return new PBoolean( numValue >= that.numValue );
    }

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		return numValue.toString();
    }

}