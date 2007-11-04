package physicalc;

import java.lang.String;
import java.io.StringWriter;
import java.io.StringReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/** Class to test the Number class and its arithmetic methods.  This
 * assumes a Number constructor method that takes a string (from the
 * Lexer) and returns a Number instance. */
public class NumberTest {

    /** Integers. */
    private Number one, two, three, four, five, six, eight;

    /** Decimals. */
    private Number onePointOne, twoPointThree, twoPointFiveThree,
	threePointFour, fivePointTwoNine;

    /** Numbers with exponents. */
    private Number onePoint2e24, oneE24, twoE23, twoE47, fourE46;
    

    @Before public void setValues() {
	one = new Number("1");
	two = new Number("2");
	three = new Number("3");
	four = new Number("4");
	five = new Number("5");
	six = new Number("6");
	eight = new Number("8");

	onePointOne = new Number("1.1");
	twoPointThree = new Number("2.3");
	twoPointFiveThree = new Number("2.53");
	threePointFour = new Number("3.4");
	fivePointTwoNine = new Number("5.29");

	onePoint2e24 = new Number("1.2e24");
	oneE24 = new Number("1e24");
	twoE23 = new Number("2e23");
	twoE47 = new Number("2e47");
	fourE46 = new Number("4e46");
    }
	
    /* **************************************************
     * INTEGER ARITHMETIC *
     * **************************************************/

    @Test public void addInts() {
	assertEquals("2 + 3 = 5", five, two.add(three));
    }

    @Test public void subtractInts() {
	assertEquals("4 - 3 = 1", one, four.sub(three));
    }
    
    @Test public void multiplyInts() {
	assertEquals("2 * 3 = 6", six, two.mul(three));
    }

    @Test public void divideInts() {
	assertEquals("6 / 2 = 3", three, six.div(two));
    }

    @Test public void exponentInts() {
	assertEquals("2 ^ 3 = 8", eight, two.pow(three));
    }


    /* **************************************************
     * DECIMAL ARITHMETIC *
     * **************************************************/

    @Test public void addDecimals() {
	assertEquals("1.1 + 2.3 = 3.4", threePointFour,
		     onePointOne.add(twoPointThree));
    }

    @Test public void subtractDecimals() {
	assertEquals("3.4 - 1.1 = 2.3", twoPointThree,
		     threePointFour.sub(onePointOne));
    }

    @Test public void multiplyDecimals() {
	assertEquals("1.1 * 2.3 = 2.53", twoPointFiveThree,
		     onePointOne.mul(twoPointThree));
    }

    @Test public void divideDecimals() {
	assertEquals("2.53 / 1.1 = 2.3", twoPointFiveThree,
		     twoPointFiveThree.div(onePointOne));
    }

    @Test public void exponentDecimals() {
	assertEquals("2.3 ^ 2 = 5.29", fivePointTwoNine,
		     twoPointThree.pow(two));
    }


    /* **************************************************
     * NUMBER WITH EXPONENT ARITHMETIC *
     * **************************************************/

    @Test public void addExponents() {
	assertEquals("1e24 + 2e23 = 1.2e24", onePoint2e24,
		     oneE24.add(twoE23));
    }

    @Test public void subtractExponents() {
	assertEquals("1.2e24 - 2e23 = 1e24", oneE24,
		     onePoint2e24.sub(twoE23));
    }

    @Test public void multiplyExponents() {
	assertEquals("1e24 * 2e23 = 2e47", twoE47,
		     oneE24.mul(twoE23));
    }

    @Test public void divideExponents() {
	assertEquals("2e47 / 2e23 = 1e24", oneE24,
		     twoE47.div(twoE23));
    }

    @Test public void exponentExponents() {
	assertEquals("2e23 ^ 2 = 4e46", fourE46,
		     twoE23.pow(two));
    }


    /** The suite() method is required for compatibility with older
     * JUnit versions. */
    public static junit.framework.Test suite() {
	return new JUnit4TestAdapter(NumberTest.class);
    }
}
