package physicalc;

import java.lang.String;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/** Class to test the Unit class and its arithmetic methods. */
public class UnitTest {

    private Unit second, minute;
    private Unit meter, foot;
    private Number three, four, twelve;

    @Before public void setValues() {
	second = new Unit("second");
	meter = new Unit("meter");

	three = new Number("3");
	four = new Number("4");
	twelve = new Number("12");
    }

    @Test public void deriveFromNumbers() {
	minute = new Unit("minute", second.mul(new Number("60")));
	foot = new Unit("foot", meter.mul(new Number("3.2808399")));
    }

    @Test public void combineWithNumbers() {
	assertEquals("Should combine numbers and units when multiplying.",
		     "3 * meter",
		     three.mul(meter).toString());
	assertEquals("Should combine numbers and units when multiplying.",
		     "3 * meter",
		     meter.mul(three).toString());
    }

    @Test public void exponent() {
	assertEquals("Should accept units raised to an integer power.",
		     "meter ^ 3",
		     meter.pow(three).toString());
	assertEquals("Should accept units raised to a negative power.",
		     "meter ^ -3",
		     meter.pow(three.neg()).toString());
	assertEquals("Should accept units raised to a fractional power.",
		     "meter ^ 0.75",
		     meter.pow(three.div(four)).toString());
    }

    @Test(expected=TypeError.class)
    public void unitAsPower() {
	meter.pow(second);
    }

    @Test public void multiply() {
	assertEquals("Should combine units when multiplying them.",
		     "meter * second",
		     meter.mul(second).toString());
    }

    @Test public void divide() {
	assertEquals("Should combine units when dividing them.",
		     "meter / second",
		     meter.div(second).toString());
    }

    /** The suite() method is required for compatibility with older
     * JUnit versions. */
     public static junit.framework.Test suite() {
 	return new JUnit4TestAdapter(UnitTest.class);
     }
}
