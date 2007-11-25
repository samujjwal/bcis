package physicalc;

import java.lang.*;

/** Datum is an abstract base class for all data objects in a
 * Physicalc program.
 *
 * The methods in Datum just raise errors.  Sub-classes must override
 * the supported operations. */
public abstract class Datum {

    /** Returns the result of this + that.  Does not modify this. */
    public Datum add(Datum that) throws TypeError {
	System.err.println("called Datum#add");
	throw new TypeError("+", this, that);
    }

    /** Returns the result of this - that.  Does not modify this. */
    public Datum sub(Datum that) throws TypeError {
	throw new TypeError("-", this, that);
    }

    /** Returns the result of this * that.  Does not modify this. */
    public Datum mul(Datum that) throws TypeError {
	throw new TypeError("*", this, that);
    }

    /** Returns the result of this / that.  Does not modify this. */
    public Datum div(Datum that) throws TypeError {
	throw new TypeError("/", this, that);
    }

    /** Returns the result of this ^ that.  Does not modify this. */
    public Datum pow(Datum that) throws TypeError {
	throw new TypeError("^", this, that);
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public Datum neg() throws TypeError {
	throw new TypeError("unary -", this, null);
    }

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
	return false;  // Default; sub-classes should override
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
	return true;
    }

    public PBoolean lessThan(Datum that) throws TypeError {
	throw new TypeError("<", this, that);
    }

    public PBoolean lessEqual(Datum that) throws TypeError {
	throw new TypeError("<=", this, that);
    }

    public PBoolean greaterThan(Datum that) throws TypeError {
	throw new TypeError(">", this, that);
    }

    public PBoolean greaterEqual(Datum that) throws TypeError {
	throw new TypeError(">=", this, that);
    }

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
	return "Datum";  // sub-classes must override
    }

    /** For lists, returns the nth item in the collection.  For
     * strings, returns the nth character. For vectors, index 0
     * returns the x component, and index 1 returns the y
     * component. */
    public Datum getIndex(int index) throws TypeError, BoundsError {
	throw new TypeError("[]", this, this);
    }

}
