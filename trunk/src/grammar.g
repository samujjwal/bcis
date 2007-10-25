/* ***********************************************************************
 * grammar.g : the lexer and the parser, in ANTLR grammar for Physicalc
 *
 * ANTLR Parser Generator Version 2.7.7 (2006-11-01) 
 *
 * @author Changlong Jiang, cj2214@columbia.edu 
 * @author Stuart Sierra, ss2806@colmbia.edu
 * 
 * @version 1.0
 * ***********************************************************************/


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
    k = 2;  // needed for subscripts, function calls, etc.
    buildAST = true;
}

tokens { /* used in the abstract syntax tree */
    BLOCK;
    FUNCALL;
    IF;
    LIST;
    PARAMS;
    SUBSCRIPT;
    UMINUS;
    VECTOR;
}

program : (load | def | stmt)+;

/** Load statement: load the file at the path given. */
load : "load"^ STRING TERMINATOR!;

/* **************************************************
 * Definitions
 * ************************************************** */

/** Definitions. */
def : (quantity_def | unit_def | constant_def | alias_def | function_def)
      TERMINATOR;

quantity_def : "quantity"^ ID (EQ! expr)?;

unit_def : "unit"^ ID ("for"! ID | EQ! expr);

constant_def : "constant"^ ID EQ! expr;

alias_def : "alias"^ ID "for"! ID;

function_def : "function"^ ID LPAREN! params RPAREN! TERMINATOR!
    block "done";

/** Parameter list for function definitions. */
params : (ID)? (COMMA! ID)*
    {#params = #([PARAMS, "PARAMS"], params); } ;

/* **************************************************
 * Statements
 * ************************************************** */

/** Statements. */
stmt : simple_stmt | compound_stmt;

/** A block of one or more statements. */
block : (stmt)+
    {#block = #([BLOCK, "BLOCK"], block); } ;

/** Simple statement: A single-line statement that must end with a
 * TERMINATOR.  An expression by itself can be a statement.*/
simple_stmt :
    ( expr 
    | "return"^ expr
    | "next"^
    | "break"^
    | "set"^ lvalue EQ! expr
    )
    TERMINATOR! ;

/** An lvalue is anything that can be assigned to with "set".
 * Variables and subscript expressions can be assigned. */
lvalue : subscript_expr | ID ;

/** Compound statement: a multi-part statement like if/then/else or
 * while.  Compound statements always end with "done". */
compound_stmt : (if_stmt | while_stmt | for_stmt) "done"! TERMINATOR!;

/** If/then/else.  These rules transfrom an if/elsif/else sequence
 * into nested IF trees.  Each IF subtree has 3 arguments: (1) a test
 * expression, (2) a "then" block, and (3) an optional "else"
 * block. */
if_stmt :
    "if"! expr "then"! TERMINATOR! block
    elsif_stmt
    {#if_stmt = #([IF,"IF"], if_stmt); } ;

elsif_stmt : "elsif"! expr "then"! TERMINATOR! block (elsif_stmt)
    {#elsif_stmt = #([IF,"IF"], elsif_stmt); }
    | else_stmt ;

else_stmt : "else"! TERMINATOR! block
    | /* nothing */ ;


/** "while" loops. */
while_stmt : "while"^ expr "do"! TERMINATOR! block;

/** "for" loops. */
for_stmt : "for"^ ID "from"! expr "to"! expr "step"! expr "do"! TERMINATOR!
           block ;


/* **************************************************
 * Expressions
 * ************************************************** */

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

/** Exponentiation: tail-recursion makes it right-associative. */
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
 * checking that the subscript evaluates to an integer.  Chained
 * subscript expressions like a[b][c] are allowed, but the first token
 * (the 'a') must be an identifier. */
subscript_expr : ID (LBRACKET! expr RBRACKET!)+
    {#subscript_expr = #([SUBSCRIPT, "SUBSCRIPT"], subscript_expr); };

/** Function calls */
funcall_expr : ID LPAREN! expr_list RPAREN!
    {#funcall_expr = #([FUNCALL, "FUNCALL"], funcall_expr); };
