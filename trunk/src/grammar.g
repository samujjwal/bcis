header {
    package physicalc;
}

/* ********************************************************************
 * LEXER *
 * ******************************************************************** */
class PhysiLexer extends Lexer;

options {
    charVocabulary = '\11'..'\177';  // Plain 7-bit ASCII
    testLiterals = false;
    k = 2;  // for >= or <= operators
}

protected DIGIT : '0'..'9';
protected LETTER : 'a'..'z' | 'A'..'Z';

/** Identifiers must begin with a letter or underscore, which may be
 * followed by any combination of letters, digits, and underscores. */
ID : ( LETTER | '_' ) ( LETTER | DIGIT | '_' )*;

/** Whitespace is ignored. */
WHITESPACE : (' ' | '\t' | '\f')+ { $setType(Token.SKIP); };

/** Line breaks are significant as statement separators, but are not
 * tokens on their own. */
protected NEWLINE : ('\n' | ('\r' '\n') => '\r' '\n' | '\r') 
    { newline(); } ;

/** Comments begin with '#' and go to the end of the line.  Since line
 * breaks are used as statement separators, the comment text does NOT
 * include the newline. */
COMMENT : '#' ( ~( '\n' | '\r' ) )*
    { $setType(Token.SKIP); } ;

/** Statements are separated by newlines or semicolons. */
SEPARATOR : (NEWLINE | ';');


/** There is no syntactic distinction among integers, decimal numbers,
 * and numbers with exponents.  They're all just numbers. */
NUMBER : ( (DIGIT)+ ( '.' (DIGIT)* (EXPONENT)? | EXPONENT )?
         | '.' (DIGIT)+ (EXPONENT)?
         ) ;

protected EXPONENT : 'e' ( '+' | '-' )? (DIGIT)+ ;



PLUS   : '+';
MINUS  : '-';
TIMES  : '*';
DIVIDE : '/';
CARET  : '^';
LPAREN : '(';
RPAREN : ')';




/* ********************************************************************
 * PARSER *
 * ******************************************************************** */
class PhysiParser extends Parser;

options {
    buildAST = true;
}

expr : NUMBER PLUS^ NUMBER ;
