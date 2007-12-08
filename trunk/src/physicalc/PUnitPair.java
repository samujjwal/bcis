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

	public PUnitPair (Datum n,Datum u) throws TypeError {
		if ( n instanceof PNumber ) {
			number = (PNumber) n;
		} else { throw new TypeError(n, "PNumber", this); }
		if ( u instanceof PUnit ) {
			unit = (PUnit) u;
		} else { throw new TypeError(u, "PUnit", this); }
	}

    /** Returns the result of this + that.  Does not modify this. */
    public Datum add(Datum that) throws TypeError {
		if (that instanceof PUnitPair) {
			if ( (unit.add(((PUnitPair)that).unit)).isTrue() ) {
				return new PUnitPair(number.add(((PUnitPair)that).number),unit);
			}
			throw new TypeError("+", this, that);
		}
		throw new TypeError("+", this, that);
    }

    /** Returns the result of this + that.  Does not modify this. */
	public Datum sub(Datum that) throws TypeError {
		if (that instanceof PUnitPair) {
			if ( (unit.sub(((PUnitPair)that).unit)).isTrue() ) {
				return new PUnitPair(number.sub(((PUnitPair)that).number),unit);
			}
			throw new TypeError("-", this, that);
		}
		throw new TypeError("-", this, that);
    }

    /** Returns the result of this * that.  Does not modify this. */
    /** Case: unitpair*unitpair return unit pair */
    /** Case: number*unitpair return unit pair */
    public Datum mul(Datum that) throws TypeError {
		if (that instanceof PUnitPair) {
			return new PUnitPair(number.mul(((PUnitPair)that).number),unit.mul(((PUnitPair)that).unit));
		} else if (that instanceof PNumber) {
			return new PUnitPair(number.mul(((PNumber)that)),unit);
		} else {
			throw new TypeError("*", this, that);
		}
    }

    /** Returns the result of this / that.  Does not modify this. */
	/** Case: unitpair/unitpair return unit pair */
	/** Case: number/unitpair return unit pair */
	public Datum div(Datum that) throws TypeError {
		if (that instanceof PUnitPair) {
			return new PUnitPair(number.div(((PUnitPair)that).number),unit.div(((PUnitPair)that).unit));
		} else if (that instanceof PNumber) {
			return new PUnitPair(number.div(((PNumber)that)),unit);
		} else {
			throw new TypeError("/", this, that);
		}
    }

    /** Returns the result of this ^ n.  Does not modify this. */
    public Datum pow(Datum n) throws TypeError {
		if ( n instanceof PNumber ) {
			return new PUnitPair(number.pow((PNumber)n),unit.pow((PNumber)n));
		} throw new TypeError("^", this, n);
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public Datum neg() throws TypeError {
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

    public void setNumber(PNumber n) {
		number = n;
	}

	public void setUnit(PUnit u) {
		unit = u;
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