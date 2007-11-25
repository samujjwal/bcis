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

	public PUnit (String s, Datum p) throws TypeError {
		name = s;
		if ( p instanceof PUnitPair ) {
			conversion = ((PUnitPair) p).getNumber();
			unitMap = ((PUnitPair) p).getUnit().unitMap;
			rootName = ((PUnitPair) p).getUnit().rootName;
		} else { throw new TypeError(p, "PUnitPair", this); }
	}

	/** lets you know if the two units can be added */
	public Datum add(Datum that) throws TypeError {
		if ( that instanceof PUnit ) {
			return new PBoolean(equals(((PUnit)that)));
		} throw new TypeError("+", this, that);
	}

	/** lets you know if the two units can be subtracted */
	public Datum sub(Datum that) throws TypeError {
		if ( that instanceof PUnit ) {
			return new PBoolean(equals(((PUnit)that)));
		} throw new TypeError("-", this, that);
    }

    /** Returns the result of this * that.  Does not modify this. */
    /** Case: number*unit returns unit pair */
    public Datum mul(Datum that) throws TypeError {
		if (that instanceof PUnit) {
			HashMap<String,PNumber> returnMap = new HashMap<String,PNumber>();
			Iterator it = unitMap.keySet().iterator();
			String thisKey = "";
			PNumber thisValue = new PNumber();
			PNumber thatValue = new PNumber();
			while(it.hasNext()) { // go through this unit hashmap
				thisKey = (String)it.next();
				thisValue = new PNumber( unitMap.get(thisKey).toString() );
				if (((PUnit)that).unitMap.containsKey(thisKey)) {
					thatValue = new PNumber( ((PUnit)that).unitMap.get(thisKey).toString() );
					returnMap.put( thisKey,(PNumber)thisValue.add(thatValue) );
				}
				else {
					returnMap.put( thisKey,thisValue );
				}
			}
			it = ((PUnit)that).unitMap.keySet().iterator();
			String thatKey = "";
			while(it.hasNext()) { // go through that unit hashmap
				thatKey = (String)it.next();
				thatValue = new PNumber( ((PUnit)that).unitMap.get(thatKey).toString() );
				if (!unitMap.containsKey(thatKey)) {
					returnMap.put( thatKey,thatValue );
				}
			}
			return new PUnit(returnMap,(PNumber) conversion.mul(((PUnit)that).conversion));
		} else if (that instanceof PNumber) {
			PUnit returnUnit = this;
			return new PUnitPair(conversion.mul((PNumber)that),returnUnit);
		} else {
			throw new TypeError("*", this, that);
		}
    }

    /** Returns the result of this / that.  Does not modify this. */
	public Datum div(Datum that) throws TypeError {
		if (that instanceof PUnit) {
			HashMap<String,PNumber> returnMap = new HashMap<String,PNumber>();
			Iterator it = unitMap.keySet().iterator();
			String thisKey = "";
			PNumber thisValue = new PNumber();
			PNumber thatValue = new PNumber();
			while(it.hasNext()) { // go through this unit hashmap
				thisKey = (String)it.next();
				thisValue = new PNumber( unitMap.get(thisKey).toString() );
				if (((PUnit)that).unitMap.containsKey(thisKey)) {
					thatValue = new PNumber( ((PUnit)that).unitMap.get(thisKey).toString() );
					returnMap.put( thisKey,(PNumber) thisValue.sub(thatValue) );
				}
				else {
					returnMap.put( thisKey,thisValue );
				}
			}
			it = ((PUnit)that).unitMap.keySet().iterator();
			String thatKey = "";
			while(it.hasNext()) { // go through that unit hashmap
				thatKey = (String)it.next();
				thatValue = new PNumber( ((PUnit)that).unitMap.get(thatKey).toString() );
				if (!unitMap.containsKey(thatKey)) {
					returnMap.put( thatKey,(PNumber) thatValue.neg() );
				}
			}
			return new PUnit(returnMap,(PNumber) conversion.div(((PUnit)that).conversion));
		} else {
			throw new TypeError("/", this, that);
		}
	}

	public Datum pow(Datum n) {
		if (n instanceof PNumber) {
			PUnit returnUnit = new PUnit(rootName);
			returnUnit.unitMap.put(this.rootName,((PNumber)n));
			returnUnit.conversion = (PNumber) this.conversion.pow(((PNumber)n));
			return returnUnit;
		} else {
			throw new TypeError("^", this, n);
		}
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