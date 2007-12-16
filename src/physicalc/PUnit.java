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
	protected boolean unitMode;

	public PUnit () {
		name = "";
		conversion = new PNumber("1");
		unitMap = new HashMap<String,PNumber>();
		unitMode = false;
	}

	public PUnit (HashMap<String,PNumber> map, PNumber conv) {
		name = "";
		rootName = "";
		conversion = conv;
		unitMap = map;
		unitMode = false;
	}

	public PUnit (String s) {
		name = s;
		rootName = s;
		conversion = new PNumber("1");
		unitMap = new HashMap<String,PNumber>();
		unitMap.put(s,new PNumber("1"));
		unitMode = false;
	}

	public PUnit (String s, Datum p) throws TypeError {
		name = s;
		if ( p instanceof PUnitPair ) {
			conversion = ((PUnitPair) p).getNumber();
			unitMap = ((PUnitPair) p).getUnit().unitMap;
			rootName = ((PUnitPair) p).getUnit().rootName;
			unitMode = false;
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
					//System.err.println("putting in "+thisKey);
					returnMap.put( thisKey,(PNumber) thisValue.sub(thatValue) );
				}
				else {
					//System.err.println("putting in "+thisKey);
					returnMap.put( thisKey,thisValue );
				}
			}
			it = ((PUnit)that).unitMap.keySet().iterator();
			String thatKey = "";
			while(it.hasNext()) { // go through that unit hashmap
				thatKey = (String)it.next();
				thatValue = new PNumber( ((PUnit)that).unitMap.get(thatKey).toString() );
				if (!unitMap.containsKey(thatKey)) {
					//System.err.println("putting in "+thatKey);
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
			HashMap<String,PNumber> returnMap = new HashMap<String,PNumber>();
			Iterator it = unitMap.keySet().iterator();
			String thisKey = "";
			PNumber thisValue = new PNumber();
			while(it.hasNext()) { // go through this unit hashmap
				thisKey = (String)it.next();
				thisValue = new PNumber( unitMap.get(thisKey).toString() );
				returnMap.put( thisKey,(PNumber)thisValue.mul(n) );
			}
			return new PUnit(returnMap,(PNumber)conversion.pow((PNumber)n));
		} else {
			throw new TypeError("^", this, n);
		}
	}

	public Datum neg() {
		HashMap<String,PNumber> returnMap = new HashMap<String,PNumber>();
		Iterator it = unitMap.keySet().iterator();
		String thisKey = "";
		PNumber thisValue = new PNumber();
		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			thisValue = new PNumber( unitMap.get(thisKey).toString() );
			returnMap.put( thisKey,(PNumber)thisValue.neg() );
		}
		return new PUnit(returnMap,(PNumber)(new PNumber("1")).div(conversion));
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

	public String getName() {
		return name;
	}

	public String getBaseUnit() {
		return rootName;
	}
	public void setUnitMode() {
		unitMode = true;
	}
	public void unsetUnitMode() {
		unitMode = false;
	}


	public String toUnit() {
		Iterator it = unitMap.keySet().iterator();
		String returnString = "";
		String negString = "";
		String thisKey = "";
		int negCount = 0;
		boolean first = true;
		boolean firstneg = true;

		while(it.hasNext()) { // go through this unit hashmap
			thisKey = (String)it.next();
			PNumber value = unitMap.get(thisKey);

			if ( ( value.greaterThan(new PNumber("1")) ).isTrue() ) {
				if (!first) { returnString += "*"; } else { first = false; }
				if (!(thisKey.compareTo("") == 0)) {
				returnString += thisKey + "^" + value.toString(); }
			}
			else if (value.equals(new PNumber("1")) ) {
				if (!first) { returnString += "*"; } else { first = false; }
				if (!(thisKey.compareTo("") == 0)) {
				returnString += thisKey; }
			}
			else if ( ( value.lessThan(new PNumber("-1")) ).isTrue() ) {
				if (!firstneg) { negString += "*"; } else { firstneg = false; }
				if (!(thisKey.compareTo("") == 0)) {
				negString += thisKey + "^" + value.neg().toString(); }
				negCount++;
			}
			else if (value.equals(new PNumber("-1")) ) {
				if (!firstneg) { negString += "*"; } else { firstneg = false; }
				if (!(thisKey.compareTo("") == 0)) {
				negString += thisKey;}
				negCount++;
			}
			else if ( ( value.greaterThan(new PNumber("0")) ).isTrue() ) {
				if (!first) { returnString += "*"; } else { first = false; }
				if (!(thisKey.compareTo("") == 0)) {
				returnString += thisKey + "^" + value.toString(); }
			}
			else if ( ( value.lessThan(new PNumber("0")) ).isTrue() ) {
				if (!firstneg) { negString += "*"; } else { firstneg = false; }
				if (!(thisKey.compareTo("") == 0)) {
				negString += thisKey + "^" + value.neg().toString(); }
				negCount++;
			}
			else {
				returnString += "";
			}
		}

		// now put the positive and negative together
		if ( returnString.equals("") ) { returnString = "1"; }
		if ( negCount > 1 ) {
			if ( returnString.equals("") ) { returnString = "("+negString+")^-1"; }
			else { returnString += "*("+negString+")^-1"; }
		} else {
			if ( negCount > 0 ){
				if ( returnString.equals("") ) { returnString = negString+"^-1"; }
				else { returnString += "*"+negString+"^-1"; }
			}
		}

		return returnString;
	}

    /** Returns a string representation of this Datum suitable for
	 * display in program output. */
	public String toString() {
		if ( unitMode )
			return this.toUnit();
		else
			return conversion.toString()+"*"+this.toUnit();
    }

}