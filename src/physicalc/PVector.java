package physicalc;

import java.lang.*;
import java.lang.Math;

/** PVector is the vector (2D x,y component pairs) data class
	which is constructed using two PNumbers */
public class PVector extends Datum  {

	protected PNumber xValue;
	protected PNumber yValue;

	public PVector () {
		xValue = new PNumber(0);
		yValue = new PNumber(0);
	}

	public PVector (PNumber x, PNumber y) {
		xValue = x;
		yValue = y;
	}

    /** Returns the result of this + that.  Does not modify this. */
    public PVector add(PVector that) throws TypeError {
		return new PVector( xValue.add(that.xValue) , yValue.add(that.yValue) );
    }

    /** Returns the result of this - that.  Does not modify this. */
    public PVector sub(PVector that) throws TypeError {
		return new PVector( xValue.sub(that.xValue) , yValue.sub(that.yValue) );
    }

    /** Returns the result of this * that.  Does not modify this. */
    public PVector mul(PVector that) throws TypeError {
		return new PVector( xValue.mul(that.xValue) , yValue.mul(that.yValue) );
    }

    /** Returns the result of this / that.  Does not modify this. */
    public PVector div(PVector that) throws TypeError {
		return new PVector( xValue.div(that.xValue) , yValue.div(that.yValue) );
    }

    /** Returns the result of this ^ that.  Does not modify this. */
    public PVector pow(PVector that) throws TypeError {
		return new PVector( xValue.pow(that.xValue) , yValue.pow(that.yValue) );
    }

    /** Returns the result of the unary minus operator, (- this).
     * Does not modify this. */
    public PVector neg() throws TypeError {
		return new PVector( xValue.neg() , yValue.neg() );
    }

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
		if (that instanceof PVector) {
			return equals((PVector) that);
		}
	    return false;
    }

    /** Returns true if "that" has the same value as this. */
	private boolean equals(PVector that) {
		return xValue.equals(that.xValue) && yValue.equals(that.yValue);
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		if ( xValue.isTrue() && yValue.isTrue() ) { return true; }
		return false;
    }

    public PBoolean lessThan(PVector that) throws TypeError {
		return (xValue.add(yValue)).lessThan(that.xValue.add(that.yValue));
    }

    public PBoolean lessEqual(PVector that) throws TypeError {
		return (xValue.add(yValue)).lessEqual(that.xValue.add(that.yValue));
    }

    public PBoolean greaterThan(PVector that) throws TypeError {
		return (xValue.add(yValue)).greaterThan(that.xValue.add(that.yValue));
    }

    public PBoolean greaterEqual(PVector that) throws TypeError {
		return (xValue.add(yValue)).greaterEqual(that.xValue.add(that.yValue));
    }

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		return "("+xValue.toString()+","+yValue.toString()+")";
    }

}