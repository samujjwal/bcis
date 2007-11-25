package physicalc;

import java.lang.*;
import java.util.*;

/** PList is the list data structure which
	basically a front for java's ArrayList class */
public class PList extends Datum  {

	protected ArrayList<Datum> list;

	public PList () {
		list = new ArrayList<Datum>();
	}

	public PList (int initCapacity) {
		list = new ArrayList<Datum>(initCapacity);
	}

	public Datum add(Datum that) {
		if (that instanceof PList) {
			PList returnList = this;
			for (Iterator it = that.list.iterator (); it.hasNext (); ) {
				returnList.push( (Datum) it.next() );
  			}
  			return returnList;
		} throw new TypeError("+", this, that);
	}

	public boolean push(Datum d) {
		return list.add(d);
	}

	public boolean contains(Datum d) {
		return list.contains(d);
	}

	public int indexOf(Datum d) {
		return list.indexOf(d);
	}

	public Datum getIndex(int index) throws TypeError, BoundsError {
		return list.get(index);
	}

	public Datum remove(int index) {
		return list.remove(index);
	}

	public Datum set(int index,Datum d) {
		return list.set(index,d);
	}

	public int size() {
		return list.size();
	}

    /** Returns true if "that" is the same type and has the same value
     * as this. */
    public boolean equals(Object that) {
		if (that instanceof PList) {
			return equals((PList) that);
		}
	    return false;
    }

    /** Returns true if "that" has the same value as this. */
	private boolean equals(PList that) {
		if ( list.size() != that.list.size() ) { return false; }
		Iterator thatIt = that.list.iterator();
		for (Iterator it = list.iterator (); it.hasNext (); ) {
		    Datum thisD = (Datum) it.next();
		    Datum thatD = (Datum) thatIt.next();
		    if ( !thisD.equals(thatD) ) { return false; }
  		}
  		return true;
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return (!list.equals(false));
    }

    public void clear() {
		list.clear();
	}

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		String returnString = "{";
		for (Iterator it = list.iterator (); it.hasNext (); ) {
			Datum d = (Datum) it.next();
			returnString += d.toString()+",";
  		}
  		returnString += "}";
  		return returnString;
    }

}