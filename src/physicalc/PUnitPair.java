package physicalc;

import java.lang.*;
import java.lang.Math;

/** PUnitPair is the number data class which includes
	intergers, decimals, and exponents.
	This is similar to the abstract Number class in java,
	but will convert all types into a Double object before
	algebraic processing */
public class PUnitPair extends Datum  {

	protected PNumber number;
	protected PUnit unit;

	public PUnitPair () {
		number = new PNumber(0);
		unit = new PUnit();
	}

	public PUnitPair (PNumber n,PUnit u) {
		number = n;
		unit = u;
	}

    /** Returns the result of this + that.  Does not modify this. */
    public PUnitPair add(PUnitPair that) throws TypeError {
		if ( unit.add(that.unit) ) {
			return new PUnitPair(number.add(that.number),unit);
		}
		throw new TypeError("+", this, that);
    }

    /** Returns the result of this - that.  Does not modify this. */
    public PUnitPair sub(PUnitPair that) throws TypeError {
		if ( unit.sub(that.unit) ) {
			return new PUnitPair(number.sub(that.number),unit);
		}
		throw new TypeError("-", this, that);
    }

    /** Returns the result of this * that.  Does not modify this. */
    public PUnitPair mul(PUnitPair that) throws TypeError {
		return new PUnitPair(number.mul(that.number),unit.mul(that.unit));
    }

    /** Case: number*unitpair return unit pair */
	public PUnitPair mul(PNumber that) throws TypeError {
		return new PUnitPair(number.mul(that),unit);
	}

    /** Returns the result of this / that.  Does not modify this. */
    public PUnitPair div(PUnitPair that) throws TypeError {
		return new PUnitPair(number.div(that.number),unit.div(that.unit));
    }

    /** Case: number/unitpair return unit pair */
	public PUnitPair div(PNumber that) throws TypeError {
		return new PUnitPair(number.div(that),unit);
	}

    /** Returns the result of this ^ n.  Does not modify this. */
    public PUnitPair pow(PNumber n) throws TypeError {
		return new PUnitPair(number.pow(n),unit.pow(n));
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public PUnitPair neg() throws TypeError {
		return new PUnitPair(number.neg(),unit);
    }

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
		if (that instanceof PUnitPair) {
			return equals((PUnitPair) that);
		}
	    return false;
    }

    /** Returns true if "that" has the same value as this. */
	private boolean equals(PUnitPair that) {
		return number.equals(that.number) && unit.equals(that.unit);
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return number.isTrue() && unit.isTrue();
    }

    public PNumber getNumber() {
		return number;
	}

	public PUnit getUnit() {
		return unit;
	}

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		return number.toString()+unit.toString();
    }

}