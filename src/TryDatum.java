import java.lang.String;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import antlr.Token;
import physicalc.*;


/** TryDatum: test the Datum classes
 *
 * TryDatum will create and test various Datum objects
 *
 * Run it like this:
 *
 *    java TryDatum
 *
 */
public class TryDatum {
    public static void main(String [] args) {

		/*****************
		PNumber & PBoolean Tests - testing basic algebra and logic of integers, decimals, and exponents
		*****************/

		PNumber integer = new PNumber(5); 					// you can use a primitive int, float, double, long, short
		PNumber integer2 = new PNumber(new Integer(5));    	//or wrapper class Integer, Float, Double, Long, Short
		PNumber integer3 = new PNumber("5");  				//or you can use a String
		PNumber decimal = new PNumber(3.14159);
		PNumber pos_exponent = new PNumber("3e4");
		PNumber neg_exponent = new PNumber("3e-4");
		PNumber result = new PNumber();
		PBoolean bool = new PBoolean();

		try {

			System.out.println("****************************************************************");
			System.out.println("PNumber & PBoolean Tests:\n");
			result = integer.add(decimal);
			System.out.println(integer.toString()+" + "+decimal.toString()+" = "+result.toString());

			result = pos_exponent.sub(integer);
			System.out.println(pos_exponent.toString()+" - "+integer.toString()+" = "+result.toString());

			result = integer.mul(neg_exponent);
			System.out.println(integer.toString()+" * "+neg_exponent.toString()+" = "+result.toString());

			result = decimal.div(pos_exponent);
			System.out.println(decimal.toString()+" / "+pos_exponent.toString()+" = "+result.toString());

			result = integer.pow(decimal);
			System.out.println(integer.toString()+" ^ "+decimal.toString()+" = "+result.toString());

			bool = new PBoolean(integer.equals(integer2));
			System.out.println(integer.toString()+" == "+integer2.toString()+" = "+bool.toString());

			bool = integer.lessThan(decimal);
			System.out.println(integer.toString()+" < "+decimal.toString()+" = "+bool.toString());
			System.out.println("****************************************************************");

		} catch (TypeError err) {
			System.out.println(err.toString());
		}

		/*****************
		PUnit Tests - testing basic algebraic symbolic computation of units
		*****************/

		HashMap<String,Double> map1 = new HashMap<String,Double>();  // w*x^3/y^2
			map1.put("w",new Double(1));
			map1.put("x",new Double(3));
			map1.put("y",new Double(-2));
		HashMap<String,Double> map2 = new HashMap<String,Double>();  // z^4/x/y^4
			map2.put("x",new Double(-1));
			map2.put("y",new Double(-4));
			map2.put("z",new Double(4));
		HashMap<String,Double> map3 = new HashMap<String,Double>();  // z^4/y^4/x
			map3.put("y",new Double(-4));
			map3.put("x",new Double(-1));
			map3.put("z",new Double(4));

		PUnit unit1 = new PUnit(map1);
		PUnit unit2 = new PUnit(map2);
		PUnit unit3 = new PUnit(map3);
		PUnit result2 = new PUnit();

		try {

			System.out.println("PUnit Tests:\n");
			bool = new PBoolean(unit2.add(unit3));
			System.out.println(unit2.toString()+" + "+unit3.toString()+" = "+bool.toString());

			bool = new PBoolean(unit1.sub(unit2));
			System.out.println(unit1.toString()+" - "+unit2.toString()+" = "+bool.toString());

			result2 = unit1.mul(unit2);
			System.out.println(unit1.toString()+" * "+unit2.toString()+" = "+result2.toString());

			result2 = unit1.div(unit2);
			System.out.println(unit1.toString()+" / "+unit2.toString()+" = "+result2.toString());

			bool = new PBoolean(unit2.equals(unit3));
			System.out.println(unit2.toString()+" == "+unit3.toString()+" = "+bool.toString());

			System.out.println("****************************************************************");

		} catch (TypeError err) {
			System.out.println(err.toString());
		}

		/*****************
		PUnitPair Tests - testing algebraic manipulation of number-unit pairs
		*****************/

		PUnitPair pair1 = new PUnitPair(integer,unit1);
		PUnitPair pair2 = new PUnitPair(decimal,unit2);
		PUnitPair pair3 = new PUnitPair(integer,unit3);
		PUnitPair result3 = new PUnitPair();

		try {

			System.out.println("PUnitPair Tests:\n");
			result3 = pair2.add(pair3);  //note that in practice, addition/subraction with unit pairs must catch exceptions (incompatible units)
			System.out.println(pair2.toString()+" + "+pair3.toString()+" = "+result3.toString());

			result3 = pair2.sub(pair3);
			System.out.println(pair2.toString()+" - "+pair3.toString()+" = "+result3.toString());

			result3 = pair1.mul(pair2);
			System.out.println(pair1.toString()+" * "+pair2.toString()+" = "+result3.toString());

			result3 = pair2.div(pair3);
			System.out.println(pair2.toString()+" / "+pair3.toString()+" = "+result3.toString());

			bool = new PBoolean(pair1.equals(pair2));
			System.out.println(pair1.toString()+" == "+pair2.toString()+" = "+bool.toString());
			System.out.println("****************************************************************");

		} catch (TypeError err) {
			System.out.println(err.toString());
		}


		/*****************
		PVector Tests - testing basic algebra and logic of vectors (2d x,y component) integers, decimals, and exponents
		*****************/

		PVector vector1 = new PVector(integer,decimal);
		PVector vector2 = new PVector(decimal,pos_exponent);
		PVector vector3 = new PVector(neg_exponent,integer);
		PVector result4 = new PVector();

		try {

			System.out.println("PVector Tests:\n");
			result4 = vector1.add(vector2);
			System.out.println(vector1.toString()+" + "+vector2.toString()+" = "+result4.toString());

			result4 = vector3.sub(vector1);
			System.out.println(vector3.toString()+" - "+vector1.toString()+" = "+result4.toString());

			result4 = vector2.mul(vector3);
			System.out.println(vector2.toString()+" * "+vector3.toString()+" = "+result4.toString());

			result4 = vector1.div(vector2);
			System.out.println(vector1.toString()+" / "+vector2.toString()+" = "+result4.toString());

			result4 = vector3.pow(vector1);
			System.out.println(vector3.toString()+" ^ "+vector1.toString()+" = "+result4.toString());

			bool = new PBoolean(vector2.equals(vector3));
			System.out.println(vector2.toString()+" == "+vector3.toString()+" = "+bool.toString());

			bool = vector1.lessThan(vector2);
			System.out.println(vector1.toString()+" < "+vector2.toString()+" = "+bool.toString());
			System.out.println("****************************************************************");

		} catch (TypeError err) {
			System.out.println(err.toString());
		}

		/*****************
		Plist Tests - testing adding and removing PNumber and PUnitPair elements from list
		*****************/

		PList list1 = new PList();
		list1.push(integer);
		list1.push(unit1);
		list1.push(pair1);
		list1.push(pair2);

		try {

			System.out.println("Plist Tests:\n");
			System.out.println(list1.toString());
			System.out.println("****************************************************************");

		} catch (TypeError err) {
			System.out.println(err.toString());
		}

		/*****************
		PString Tests - testing concatenation and lexigraphical comparisons of strings
		*****************/

		PString string1 = new PString("apple");
		PString string2 = new PString("sauce");
		PString string3 = new PString("power");
		PString result5 = new PString();

		try {

			System.out.println("PString Tests:\n");
			result5 = string1.add(string2);
			System.out.println(string1.toString()+" + "+string2.toString()+" = "+result5.toString());

			bool = new PBoolean(string2.equals(string3));
			System.out.println(string2.toString()+" == "+string3.toString()+" = "+bool.toString());

			bool = string3.lessThan(string1);
			System.out.println(string3.toString()+" < "+string1.toString()+" = "+bool.toString());
			System.out.println("****************************************************************");

		} catch (TypeError err) {
			System.out.println(err.toString());
		}
    }
}