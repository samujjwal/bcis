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
		//numValue.setScale(7,java.math.BigDecimal.ROUND_HALF_EVEN);
	}

    /** Returns the result of this + that.  Does not modify this. */
    public Datum add(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PNumber(numValue.add(((PNumber)that).numValue));
		} throw new TypeError("+", this, that);
    }

    /** Returns the result of this - that.  Does not modify this. */
    public Datum sub(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PNumber(numValue.subtract(((PNumber)that).numValue));
		} throw new TypeError("-", this, that);
    }

    /** Returns the result of this * that.  Does not modify this. */
    /** Case: number*number returns number */
    /** Case: number*unit returns unit pair */
    /** Case: number*unitpair return unit pair */
    public Datum mul(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PNumber(numValue.multiply(((PNumber)that).numValue));
		} else if (that instanceof PUnit) {
			return new PUnitPair(((PUnit)that).conversion.mul(this),(PUnit)that);
		} else if (that instanceof PUnitPair) {
			return new PUnitPair(this.mul(((PUnitPair)that).getNumber()),((PUnitPair)that).getUnit());
		} else {
			throw new TypeError("*", this, that);
		}
    }

    /** Returns the result of this / that.  Does not modify this. */
    /** Case: number/number return number */
    /** Case: number/unitpair return unit pair */
    public Datum div(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PNumber((numValue.divide(((PNumber)that).numValue,7,java.math.BigDecimal.ROUND_HALF_EVEN)).toString());
		} else if (that instanceof PUnitPair) {
			return new PUnitPair(this.div(((PUnitPair)that).getNumber()),((PUnitPair)that).getUnit());
		} else {
			throw new TypeError("/", this, that);
		}
    }

    /** Returns the result of this ^ that.  Does not modify this. */
    public Datum pow(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PNumber(java.lang.Math.pow(numValue.doubleValue() ,((PNumber)that).numValue.doubleValue()));
		} throw new TypeError("^", this, that);
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public Datum neg() throws TypeError {
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

    private boolean equals(PNumber that) {
		return numValue.compareTo(that.numValue) == 0;
	}

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return !numValue.equals(false);
    }

    public PBoolean lessThan(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PBoolean( numValue.compareTo(((PNumber)that).numValue) < 0 );
		} throw new TypeError("<", this, that);
    }

    public PBoolean lessEqual(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PBoolean( numValue.compareTo(((PNumber)that).numValue) <= 0 );
		} throw new TypeError("<=", this, that);
    }

    public PBoolean greaterThan(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PBoolean( numValue.compareTo(((PNumber)that).numValue) > 0 );
		} throw new TypeError(">", this, that);
    }

    public PBoolean greaterEqual(Datum that) throws TypeError {
		if (that instanceof PNumber) {
			return new PBoolean( numValue.compareTo(((PNumber)that).numValue) >= 0 );
		} throw new TypeError(">=", this, that);
    }

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		return numValue.toString();
    }

    /** Returns this number as an int. */
    public int toInt() {
	return numValue.intValue();
    }

}