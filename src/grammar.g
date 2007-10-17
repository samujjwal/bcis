/* *********************************************************************************
 * grammar.g : the lexer and the parser, in ANTLR grammar for Physicalc
 *
 * ANTLR Parser Generator Version 2.7.7 (2006-11-01) 
 *
 * @author Stuart Sierra - ss2806@colmbia.edu; Changlong Jiang - cj2214@columbia.edu 
 * 
 * @version 1.0
 * *********************************************************************************/


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

/** Statements are terminated by (any number of) newlines or
 * semicolons. */
TERMINATOR : (NEWLINE | ';')+;


/** There is no syntactic distinction among integers, decimal numbers,
 * and numbers with exponents.  They're all just numbers. */
NUMBER : ( (DIGIT)+ ( '.' (DIGIT)* )? 
         | '.' (DIGIT)+
         )
         ( ('e'|'E') ('+'|'-')? (DIGIT)+ )?
         ;

/** Strings are surrounded by double quotation marks. A double
 *  quotation character may be inside a string by using two double
 *  quotation marks in a row. */
STRING  : '"'!
              (  ~('"')
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
EQ     : '=';  // we use 'set' for assignment
NEQ     : "!=";
RELOP  : '>' | '<' | ">=" | "<=" ;


/* ********************************************************************
 * PARSER *
 * ******************************************************************** */
class PhysiParser extends Parser;

options {
    k = 2;
    buildAST = true;
}

tokens { /* used in the abstract syntax tree */
    FUNCALL;
    LIST;
    SUBSCRIPT;
    UMINUS;
    VECTOR;
}

program : (def | stmt);

/** Definitions. */
def : "unit";
/* TODO: add definitions. */

/** Statements. */
stmt : 
    simple_stmt;
/* TODO: add compound statements like 'for', 'loop', and 'if'. */

/** Simple statement: A single-line statement that must end with a
 * TERMINATOR. */
simple_stmt : expr TERMINATOR!;  /* an expression by itself can be a
				 * statement */
/* TODO: add other simple statements like 'return' and 'next'. */

/** A list of expressions, separated by commas.  Used in literal lists
 * and function calls.  */
expr_list : expr (COMMA! expr)* ;   


/** Expressions */
expr: in_expr;

/* Every binary operator is can repeat infinitely with a '*' closure.
 * 
 * This parses expressions like "a < b < c" as "(< (< a b) c)", which
 * makes no sense.
 * 
 * However, changing the '*' to a '?' means that anything after the
 * first operator gets ignored, which is clearly wrong.  Better let
 * the back-end decide if "(< (< a b) c)" is reasonable. */

in_expr : or_expr ( "in"^ or_expr )*;

or_expr : and_expr ( "or"^ and_expr )*;

and_expr : not_expr ( "and"^ not_expr )*;

not_expr : ("not"^)? eq_expr;  /* 'not' expressions cannot be chained */

eq_expr : neq_expr (EQ^ neq_expr)*;

neq_expr : rel_expr (NEQ^ rel_expr)*;

rel_expr : add_expr (RELOP^ add_expr)*;

add_expr : mul_expr ( (PLUS^ | MINUS^) mul_expr )*;

mul_expr : exp_expr ( (TIMES^ | DIVIDE^) exp_expr )*;

/** Exponentiation: tail-recursion makes it right-associative.
 * See http://wincent.com/knowledge-base/ANTLR_grammar_problems */
exp_expr : uminus_expr (CARET^ exp_expr)?;  

/** Unary negation operator. Unary plus ("+") is not included because
 * it's meaningless. */
uminus_expr :
    MINUS! atom
    {#uminus_expr = #([UMINUS, "UMINUS"], uminus_expr); }
    | atom;


/** atomic expressions (highest precedence) */
atom
    : ID
    | NUMBER 
    | STRING
    | list_literal
    | vector_literal
    | subscript_expr
    | funcall_expr
    | LPAREN! expr RPAREN!
    ;

/** Literal list (in square brackets) */
list_literal : LBRACKET! expr_list RBRACKET!
    {#list_literal = #([LIST,"LIST"], list_literal); };

/** Literal vector (in curly brackets, must have exactly 2 elements. */
vector_literal : LBRACE! expr COMMA! expr RBRACE!
    {#vector_literal = #([VECTOR,"VECTOR"], vector_literal); };

/** Array/list subscripts like "a[b]".  Back-end is responsible for
 * checking that the subscript evaluates to an integer. */
subscript_expr : ID LBRACKET! expr RBRACKET!
    {#subscript_expr = #([SUBSCRIPT, "SUBSCRIPT"], subscript_expr); };

/** Function calls */
funcall_expr : ID LPAREN! expr_list RPAREN!
    {#funcall_expr = #([FUNCALL, "FUNCALL"], funcall_expr); };
