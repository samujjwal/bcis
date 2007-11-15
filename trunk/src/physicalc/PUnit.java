package physicalc;

import java.lang.*;
import java.lang.Math;
import java.util.*;

/** PUnit is the symbolic data class which includes
	the algebraic manipulation of symbolic units
	stored as HashMaps */
public class PUnit extends Datum  {

	protected HashMap unitMap;

	public PUnit () {
		unitMap = new HashMap();
	}

	public PUnit (HashMap map) {
		unitMap = map;
	}

	/** lets you know if the two units can be added */
	public boolean add(PUnit that) throws TypeError {
		return equals(that);
	}

	/** lets you know if the two units can be subtracted */
	public boolean sub(PUnit that) throws TypeError {
		return equals(that);
    }

    /** Returns the result of this * that.  Does not modify this. */
    public PUnit mul(PUnit that) throws TypeError {
		HashMap<String,Double> returnMap = new HashMap<String,Double>();
		Iterator it = unitMap.keySet().iterator();
		String thisKey = "";
		Double thisValue = new Double(0);
		Double thatValue = new Double(0);
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new Double( unitMap.get(thisKey).toString() );
		    if (that.unitMap.containsKey(thisKey)) {
				thatValue = new Double( that.unitMap.get(thisKey).toString() );
				returnMap.put( thisKey,new Double(thisValue.doubleValue()+thatValue.doubleValue()) );
			}
			else {
				returnMap.put( thisKey,new Double(thisValue.doubleValue()) );
			}
		}
		it = that.unitMap.keySet().iterator();
		String thatKey = "";
		while(it.hasNext()) { // go through that unit hashmap
			thatKey = (String)it.next();
			thatValue = new Double( that.unitMap.get(thatKey).toString() );
			if (!unitMap.containsKey(thatKey)) {
				returnMap.put( thatKey,new Double(thatValue.doubleValue()) );
			}
		}
		return new PUnit(returnMap);
    }

    /** Returns the result of this / that.  Does not modify this. */
	public PUnit div(PUnit that) throws TypeError {
		HashMap<String,Double> returnMap = new HashMap<String,Double>();
		Iterator it = unitMap.keySet().iterator();
		String thisKey = "";
		Double thisValue = new Double(0);
		Double thatValue = new Double(0);
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new Double( unitMap.get(thisKey).toString() );
			if (that.unitMap.containsKey(thisKey)) {
				thatValue = new Double( that.unitMap.get(thisKey).toString() );
				returnMap.put( thisKey,new Double(thisValue.doubleValue()-thatValue.doubleValue()) );
			}
			else {
				returnMap.put( thisKey,new Double(thisValue.doubleValue()) );
			}
		}
		it = that.unitMap.keySet().iterator();
		String thatKey = "";
		while(it.hasNext()) { // go through that unit hashmap
			thatKey = (String)it.next();
			thatValue = new Double( that.unitMap.get(thatKey).toString() );
			if (!unitMap.containsKey(thatKey)) {
				returnMap.put( thatKey,new Double(thatValue.doubleValue()*-1) );
			}
		}
		return new PUnit(returnMap);
	}

	/** Returns true if "that" is the same type and has the same value
	 * as this. */
	public boolean equals(Object that) {
		if (that instanceof PUnit) {
			return equals((PUnit) that);
		}
		return false;
	}

	/** Returns true if "that" has the same value as this. */
	private boolean equals(PUnit that) {
		if ( unitMap.size() != that.unitMap.size() ) { return false; }
		Iterator it = unitMap.keySet().iterator();
		String thisKey = "";
		Double thisValue = new Double(0);
		Double thatValue = new Double(0);
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new Double( unitMap.get(thisKey).toString() );
			if (that.unitMap.containsKey(thisKey)) {
				thatValue = new Double( that.unitMap.get(thisKey).toString() );
				if ( thisValue.doubleValue() != thatValue.doubleValue() ){ return false; }
			}
			else {
				return false;
			}
		}
		return true;
	}

	/** Returns true if this object is "true" in the Physicalc sense.
	 * Anything that is not the literal boolean "false" is considered
	 * true in Physicalc. */
	public boolean isTrue() {
		return !unitMap.isEmpty();
    }

    /** Returns a string representation of this Datum suitable for
	 * display in program output. */
	public String toString() {
		Iterator it = unitMap.keySet().iterator();
		String returnString = "";
		String thisKey = "";
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			Double value = new Double( unitMap.get(thisKey).toString() );
			if (value.doubleValue() > 1.0) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "*" + thisKey + "^" + value.toString(); }
			}
			else if (value.doubleValue() == 1.0) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "*" + thisKey; }
			}
			else if (value.doubleValue() < -1.0) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "/" + thisKey + "^" + value.toString(); }
			}
			else if (value.doubleValue() == -1.0) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "/" + thisKey;}
			}
			else {
				returnString += "";
			}
		}
		return returnString;
    }

}