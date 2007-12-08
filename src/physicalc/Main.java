package physicalc;

import java.io.*;
import java.lang.String;

class Main {

    public static void main(String[] args) {
	// TODO: add code to check syntax of command line

	Reader input;
	try {
	    input = new FileReader(args[0]);
	} catch (FileNotFoundException e) {
	    //System.out.println("File '" + args[0] + "' not found.");
	    return;
	}
	
	Interpreter interpreter = new Interpreter();
	interpreter.eval(input);
    }
    
}
	
