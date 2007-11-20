package physicalc;

import java.lang.*;
import java.lang.Math;
import java.util.*;

/** PUnit is the symbolic data class which includes
	the algebraic manipulation of symbolic units
	stored as HashMaps */
public class PUnit extends Datum  {

	protected String name;  					// ie. minute
	protected String rootName;  				// ie. second
	protected PNumber conversion;  				// ie. 60
	protected HashMap<String,PNumber> unitMap;

	public PUnit () {
		name = "";
		conversion = new PNumber("1");
		unitMap = new HashMap<String,PNumber>();
	}

	public PUnit (HashMap<String,PNumber> map, PNumber conv) {
		name = "";
		rootName = "";
		conversion = conv;
		unitMap = map;
	}

	public PUnit (String s) {
		name = s;
		rootName = s;
		conversion = new PNumber("1");
		unitMap = new HashMap<String,PNumber>();
		unitMap.put(s,new PNumber("1"));
	}

	public PUnit (String s, PUnitPair p) {
		name = s;
		conversion = p.getNumber();
		unitMap = p.getUnit().unitMap;
		rootName = p.getUnit().rootName;
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
		HashMap<String,PNumber> returnMap = new HashMap<String,PNumber>();
		Iterator it = unitMap.keySet().iterator();
		String thisKey = "";
		PNumber thisValue = new PNumber();
		PNumber thatValue = new PNumber();
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new PNumber( unitMap.get(thisKey).toString() );
		    if (that.unitMap.containsKey(thisKey)) {
				thatValue = new PNumber( that.unitMap.get(thisKey).toString() );
				returnMap.put( thisKey,thisValue.add(thatValue) );
			}
			else {
				returnMap.put( thisKey,thisValue );
			}
		}
		it = that.unitMap.keySet().iterator();
		String thatKey = "";
		while(it.hasNext()) { // go through that unit hashmap
			thatKey = (String)it.next();
			thatValue = new PNumber( that.unitMap.get(thatKey).toString() );
			if (!unitMap.containsKey(thatKey)) {
				returnMap.put( thatKey,thatValue );
			}
		}
		return new PUnit(returnMap,conversion.mul(that.conversion));
    }

    /** Case: number*unit returns unit pair */
	public PUnitPair mul(PNumber that) throws TypeError {
		PUnit returnUnit = this;
		return new PUnitPair(conversion.mul(that),returnUnit);
	}

    /** Returns the result of this / that.  Does not modify this. */
	public PUnit div(PUnit that) throws TypeError {
		HashMap<String,PNumber> returnMap = new HashMap<String,PNumber>();
		Iterator it = unitMap.keySet().iterator();
		String thisKey = "";
		PNumber thisValue = new PNumber();
		PNumber thatValue = new PNumber();
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new PNumber( unitMap.get(thisKey).toString() );
			if (that.unitMap.containsKey(thisKey)) {
				thatValue = new PNumber( that.unitMap.get(thisKey).toString() );
				returnMap.put( thisKey,thisValue.sub(thatValue) );
			}
			else {
				returnMap.put( thisKey,thisValue );
			}
		}
		it = that.unitMap.keySet().iterator();
		String thatKey = "";
		while(it.hasNext()) { // go through that unit hashmap
			thatKey = (String)it.next();
			thatValue = new PNumber( that.unitMap.get(thatKey).toString() );
			if (!unitMap.containsKey(thatKey)) {
				returnMap.put( thatKey,thatValue.neg() );
			}
		}
		return new PUnit(returnMap,conversion.div(that.conversion));
	}

	public PUnit pow(PNumber n) {
		PUnit returnUnit = new PUnit(rootName);
		returnUnit.unitMap.put(this.rootName,n);
		returnUnit.conversion = this.conversion.pow(n);

		return returnUnit;
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
		PNumber thisValue = new PNumber();
		PNumber thatValue = new PNumber();
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new PNumber( unitMap.get(thisKey).toString() );
			if (that.unitMap.containsKey(thisKey)) {
				thatValue = new PNumber( that.unitMap.get(thisKey).toString() );
				if ( !thisValue.equals(thatValue) ){ return false; }
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

    public PNumber getConversion() {
		return conversion;
	}

    /** Returns a string representation of this Datum suitable for
	 * display in program output. */
	public String toString() {
		Iterator it = unitMap.keySet().iterator();
		String returnString = "";
		String thisKey = "";
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			PNumber value = unitMap.get(thisKey);
			if ( ( value.greaterThan(new PNumber("1.0")) ).isTrue() ) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "*" + thisKey + "^" + value.toString(); }
			}
			else if (value.equals(new PNumber("1.0")) ) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "*" + thisKey; }
			}
			else if ( ( value.lessThan(new PNumber("-1.0")) ).isTrue() ) {
				if (!(thisKey.compareTo("") == 0)) {
				returnString += "/" + thisKey + "^" + value.neg().toString(); }
			}
			else if (value.equals(new PNumber("-1.0")) ) {
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