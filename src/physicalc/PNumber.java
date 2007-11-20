package physicalc;

import java.lang.*;
import java.lang.Math;
import java.math.*;

/** PNumber is the number data class which includes
	intergers, decimals, and exponents.
	This is similar to the abstract Number class in java,
	but will convert all types into a Double object before
	algebraic processing */
public class PNumber extends Datum  {

	protected BigDecimal numValue;

	public PNumber () {
		numValue = new BigDecimal(0);
	}

	public PNumber (int number) {
		Integer n = new Integer(number);
		numValue = new BigDecimal(new BigInteger(n.toString()));
	}

	public PNumber (float number) {
		Float n = new Float(number);
		numValue = new BigDecimal(n.doubleValue());
	}

	public PNumber (double number) {
		numValue = new BigDecimal(number);
	}

	public PNumber (long number) {
		Long n = new Long(number);
		numValue = new BigDecimal(n.doubleValue());
	}

	public PNumber (short number) {
		Short n = new Short(number);
		numValue = new BigDecimal(n.doubleValue());
	}

	public PNumber (Integer number) {
		numValue = new BigDecimal(number.doubleValue());
	}

	public PNumber (Float number) {
		numValue = new BigDecimal(number.doubleValue());
	}

	public PNumber (Double number) {
		numValue = new BigDecimal(number.doubleValue());
	}

	public PNumber (Long number) {
		numValue = new BigDecimal(number.doubleValue());
	}

	public PNumber (Short number) {
		numValue = new BigDecimal(number.doubleValue());
	}

	public PNumber (BigDecimal number) {
		numValue = number;
	}

	public PNumber (BigInteger number) {
		numValue = new BigDecimal(number);
	}

	public PNumber (String number) throws NumberFormatException {
		numValue = new BigDecimal(number);
	}

    /** Returns the result of this + that.  Does not modify this. */
    public PNumber add(PNumber that) throws TypeError {
		return new PNumber(numValue.add(that.numValue));
    }

    /** Returns the result of this - that.  Does not modify this. */
    public PNumber sub(PNumber that) throws TypeError {
		return new PNumber(numValue.subtract(that.numValue));
    }

    /** Returns the result of this * that.  Does not modify this. */
    public PNumber mul(PNumber that) throws TypeError {
		return new PNumber(numValue.multiply(that.numValue));
    }

    /** Case: number*unit returns unit pair */
    public PUnitPair mul(PUnit that) throws TypeError {
		return new PUnitPair(that.conversion.mul(this),that);
	}

	/** Case: number*unitpair return unit pair */
	public PUnitPair mul(PUnitPair that) throws TypeError {
		return new PUnitPair(this.mul(that.getNumber()),that.getUnit());
	}

    /** Returns the result of this / that.  Does not modify this. */
    public PNumber div(PNumber that) throws TypeError {
		return new PNumber(numValue.divide(that.numValue,java.math.BigDecimal.ROUND_UP));
    }

    /** Case: number/unitpair return unit pair */
	public PUnitPair div(PUnitPair that) throws TypeError {
		return new PUnitPair(this.div(that.getNumber()),that.getUnit());
	}

    /** Returns the result of this ^ that.  Does not modify this. */
    public PNumber pow(PNumber that) throws TypeError {
		return new PNumber(java.lang.Math.pow(numValue.doubleValue() ,that.numValue.doubleValue()));
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public PNumber neg() throws TypeError {
		return new PNumber(numValue.negate());
    }

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
		if (that instanceof PNumber) {
			return equals((PNumber) that);
		}
	    return false;
    }

    public boolean equals(PNumber that) {
		return numValue.compareTo(that.numValue) == 0;
	}

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return !numValue.equals(false);
    }

    public PBoolean lessThan(PNumber that) throws TypeError {
		return new PBoolean( numValue.compareTo(that.numValue) < 0 );
    }

    public PBoolean lessEqual(PNumber that) throws TypeError {
		return new PBoolean( numValue.compareTo(that.numValue) <= 0 );
    }

    public PBoolean greaterThan(PNumber that) throws TypeError {
		return new PBoolean( numValue.compareTo(that.numValue) > 0 );
    }

    public PBoolean greaterEqual(PNumber that) throws TypeError {
		return new PBoolean( numValue.compareTo(that.numValue) >= 0 );
    }

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		return numValue.toString();
    }

}