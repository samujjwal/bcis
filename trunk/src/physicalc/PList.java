package physicalc;

import java.lang.*;
import java.util.*;

/** PList is the list data structure which
	basically a front for java's ArrayList class */
public class PList extends Datum  {

	protected ArrayList<String> list;

	public PList () {
		list = new ArrayList<String>();
	}

	public PList (int initCapacity) {
		list = new ArrayList<String>(initCapacity);
	}

	public boolean push(Object o) {
		return list.add(o.toString());
	}

	public boolean contains(Object o) {
		return list.contains(o.toString());
	}

	public int indexOf(Object o) {
		return list.indexOf(o.toString());
	}

	public Object get(int index) {
		return list.get(index);
	}

	public Object remove(int index) {
		return list.remove(index);
	}

	public Object set(int index,Object o) {
		return list.set(index,o.toString());
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
		    Object thisO = it.next();
		    Object thatO = thatIt.next();
		    if ( !thisO.equals(thatO) ) { return false; }
  		}
  		return true;
    }

    /** Returns true if this object is "true" in the Physicalc sense.
     * Anything that is not the literal boolean "false" is considered
     * true in Physicalc. */
    public boolean isTrue() {
		return (list.size() >= 0);
    }

    public void clear() {
		list.clear();
	}

    /** Returns a string representation of this Datum suitable for
     * display in program output. */
    public String toString() {
		String returnString = "{";
		for (Iterator it = list.iterator (); it.hasNext (); ) {
			Object s = it.next();
			returnString += s+",";
  		}
  		returnString += "}";
  		return returnString;
    }

}