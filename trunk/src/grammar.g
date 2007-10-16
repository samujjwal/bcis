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
ID  options { testLiterals = true; }
    : ( LETTER | '_' ) ( LETTER | DIGIT | '_' )*;

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

/* Copied shamelessly from Mx */
STRING  : '"'!
              (  ~('"' | '\n')
               | ('"'!'"')
              )*
          '"'!
        ;

PLUS   : '+';
MINUS  : '-';
TIMES  : '*';
DIVIDE : '/';
CARET  : '^';
LPAREN : '(';
RPAREN : ')';
LBRACKET : '[';
RBRACKET : ']';
LBRACE : '{';
RBRACE : '}';
COMMA  : ',';
EQUALS : '=';

RELOP : '>' | '<' | ">=" | "<=";


/* ********************************************************************
 * PARSER *
 * ******************************************************************** */
class PhysiParser extends Parser;

options {
    k = 2;
    buildAST = true;
}

tokens {
    EXPR_LIST;
    LIST;
    SUBSCRIPT;
    VECTOR;
}

program : expr;

expr_list : in_expr (COMMA! in_expr)* 
            {#expr_list = #([EXPR_LIST,"EXPR_LIST"], expr_list); }
        ;   

list_literal : LBRACKET! expr_list RBRACKET!
    {#list_literal = #([LIST,"LIST"], list_literal); };

vector_literal : LBRACE! NUMBER COMMA! NUMBER RBRACE!
    {#vector_literal = #([VECTOR,"VECTOR"], vector_literal); };

atom
    : ID
    | NUMBER 
    | STRING
    | list_literal
    | vector_literal
    | LPAREN! expr RPAREN!
    | subscript_expr
    ;

expr: in_expr;

in_expr : or_expr ( "in"^ or_expr )*;

or_expr : and_expr ( "or"^ and_expr )*;

and_expr : not_expr ( "and"^ not_expr )*;

not_expr : ("not"^ eq_expr)* | eq_expr;

eq_expr : rel_expr (EQUALS^ rel_expr)*;

rel_expr : add_expr (RELOP^ add_expr)*;

add_expr : mul_expr ( (PLUS^ | MINUS^) mul_expr )*;

mul_expr : exp_expr ( (TIMES^ | DIVIDE^) exp_expr )*;

/* Exponentiation: tail-recursion makes it right-associative.
 * See http://wincent.com/knowledge-base/ANTLR_grammar_problems */
exp_expr : uminus_expr (CARET^ exp_expr)?;  

/* Unary negation operator. */
uminus_expr : MINUS^ atom | atom;

/* Array/list subscripts. */
subscript_expr : ID LBRACKET! expr RBRACKET!
    {#subscript_expr = #([SUBSCRIPT, "SUBSCRIPT"], subscript_expr); };
