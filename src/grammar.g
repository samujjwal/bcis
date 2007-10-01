header {
    package physicalc;
}

class PhysiLexer extends Lexer;

/* Lexer rules go here. */

/* Dummy rule just so ANTLR will run without errors: */
protected DIGIT : '0'..'9' ;



class PhysiParser extends Parser;

/* Dummy rule just so ANTLR will run without errors: */
stmt : "print" ;

/* Parser rules go here. */
