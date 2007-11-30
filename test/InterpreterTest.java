package physicalc;

import java.lang.String;
import java.io.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

public class InterpreterTest {

    /** An interpreter instance used by each test. */
    private Interpreter interpreter;

    @Before public void setupInterpreter() {
	interpreter = new Interpreter();
    }

    @Test public void alwaysPasses() {
	assertEquals("This test always passes.", true, true);
    }

    @Test public void doNothing() {
	assertPrints("Should do nothing.", "", "");
    }

    @Test public void arithmetic() {
	assertPrints("print(2 + 3)\n", "5\n");
    }

    @Test public void helloWorld() {
	assertPrints("print(\"Hello, world!\")\n",
		     "Hello, world!\n");
    }

    

    /** The suite() method is required for compatibility with older
     * JUnit versions. */
    public static junit.framework.Test suite() {
	return new JUnit4TestAdapter(InterpreterTest.class);
    }

    /** The assertPrints() method is an assertion that runs the
     * interpreter and checks that it prints out a certain string.
     *
     * @param message A string explaining what the test does.
     * @param program A string of Physicalc source code.  Remember the
     *                terminating line break or semicolon!
     * @param expected A string of what the interpreter should print.
     */
    private void assertPrints(String message,
			      String program,
			      String expected) {
	StringReader code = new StringReader(program);
	OutputStream output = new ByteArrayOutputStream();
	interpreter.setOutputStream(output);
	interpreter.eval(code);
	assertEquals(message, expected, output.toString());
    }


    private void assertPrints(String program,
			      String expected) {
	String message = "Should execute: " + program;
	StringReader code = new StringReader(program);
	OutputStream output = new ByteArrayOutputStream();
	interpreter.setOutputStream(output);
	interpreter.eval(code);
	assertEquals(message, expected, output.toString());
    }
}