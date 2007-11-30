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
    import java.util.ArrayList;
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
    EXPR_LIST;
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
def : (unit_def | constant_def | alias_def | function_def)
      TERMINATOR!;

unit_def : "unit"^ ID (EQ! expr)?;

constant_def : "constant"^ ID EQ! expr;

alias_def : "alias"^ ID "for"! ID;

function_def : "function"^ ID LPAREN! params RPAREN! TERMINATOR!
    block "done"!;

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
    | /* nothing */
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

elsif_stmt
    : "elsif"! expr "then"! TERMINATOR! block (elsif_stmt)
        /* need to enclose next IF in a BLOCK for tree walker */
        { #elsif_stmt = #([BLOCK,"BLOCK"], #([IF,"IF"], elsif_stmt)); }
    | else_stmt ;

else_stmt : "else"! TERMINATOR! block
    | /* nothing, but still have to include a block for the tree walker */
        { #else_stmt = #([BLOCK,"BLOCK"], else_stmt); }
    ;


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
expr_list 
    : expr (COMMA! expr)*
        {#expr_list = #([EXPR_LIST, "EXPR_LIST"], expr_list); }
    | /* nothing, still need a node for the tree walker */
        {#expr_list = #([EXPR_LIST, "EXPR_LIST"], expr_list); }
    ;


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

and_expr : eq_expr ( "and"^ eq_expr )*;

eq_expr : neq_expr (EQ^ neq_expr)*;

neq_expr : rel_expr (NEQ^ rel_expr)*;

rel_expr : add_expr (RELOP^ add_expr)*;

add_expr : mul_expr ( (PLUS^ | MINUS^) mul_expr )*;

mul_expr : exp_expr ( (TIMES^ | DIVIDE^) exp_expr )*;

/** Exponentiation: tail-recursion makes it right-associative. */
exp_expr : not_expr (CARET^ exp_expr)?;  

not_expr : ("not"^)? uminus_expr;  /* 'not' expressions cannot be chained */

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
    | "true"
    | "false"
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



/* ********************************************************************
 * TREE WALKER *
 * ******************************************************************** */
class PhysiWalker extends TreeParser;

program returns [ Program p ]
{
    p = new Program();
    Node n;
}
    : ( n=node { p.insert(n); } )+
    ;

node returns [ Node n ] 
{
    n = null;
    Expr e;
    Load l;
    Def d;
    Stmt s;
}
    : e=expr { n = e; }
    | l=load { n = l; }
    | s=stmt { n = s; }
    | d=def  { n = d; }
    ;

expr returns [ Expr e ]
{
    Expr a, b;
    e = null;
}
    /* Logical operators */
    : #("and" a=expr b=expr) { e = new And(a, b); }
    | #("or" a=expr b=expr)  { e = new Or(a, b); }
    | #("not" a=expr) { e = new Not(a); }
    | #("in" a=expr b=expr) { e = new In(a, b); }
    
    /* Relational operators */
    | #(EQ a=expr b=expr) { e = new Rel("=", a, b); }
    | #(NEQ a=expr b=expr) { e = new Rel("!=", a, b); }
    | #(op:RELOP a=expr b=expr) { e = new Rel(op.getText(), a, b); }

    /* Arithmetic operators */
    | #(PLUS a=expr b=expr) { e = new Arith("+", a, b); }
    | #(MINUS a=expr b=expr) { e = new Arith("-", a, b); }
    | #(TIMES a=expr b=expr) { e = new Arith("*", a, b); }
    | #(DIVIDE a=expr b=expr) { e = new Arith("/", a, b); }
    | #(CARET a=expr b=expr) { e = new Arith("^", a, b); }
    // | #(UMINUS a=expr) { e = new Unary(a); }

    /* Other expressions */
    | a=funcall { e = a; }
    // | a=subscript { e = a; }
    | a=literal { e = a; }
    | a=literal_list { e = a; }
    | i:ID { e = new Id(i.getText()); }
    ;

expr_list returns [ ExprList elist ]
{
    Expr a;
    elist = null;
}
    : #(EXPR_LIST  { elist = new ExprList(); }
          (a=expr  { elist.insert(a); }
	  )*
      )
    ;

funcall returns [ FunCall f ]
{
    ExprList e;
    f = null;
}
    : #(FUNCALL i:ID e=expr_list) { f = new FunCall(i.getText(), e); }
    ;

literal returns [ Literal lit ]
{
    lit = null;
}
    : n:NUMBER { lit = new Literal(new PNumber(n.getText())); }
    | s:STRING { lit = new Literal(new PString(s.getText())); }
    | "true" { lit = new Literal(new PBoolean(true)); }
    | "false" { lit = new Literal(new PBoolean(false)); }
    ;

literal_list returns [ ExprList elist ]
{
    Expr e;
    elist = null;
}
    : #(LIST  { elist = new ExprList(); }
              #(EXPR_LIST 
                (e=expr  { elist.insert(e); }
		)*
              )
       )
    ;


load returns [ Load ld ]
{
    ld = null;
}
    : #("load" file:STRING) { ld = new Load(file.getText()); }
    ;


stmt returns [ Stmt s ]
{
    s = null;
    Expr e,a,from,to,step;
    Block b,c;
}
    : #("set" id1:ID e=expr) { s = new Set(id1.getText(), e); }
    | "break" { s = new Break(); }
    | "next" { s = new Next(); }
    | #("return" e=expr) { s = new Return(e); }
    | #(IF a=expr b=block c=block) { s = new If(a,b,c); }
    | #("while" a=expr b=block) { s = new While(a,b); }
    | #("for" id2:ID from=expr to=expr step=expr b=block)
        { s = new For(id2.getText(), from, to, step, b); }
    ;


block returns [ Block b ]
{
    b = null;
    Node n;
}
    : #(BLOCK  { b = new Block(); }
          ( n=node { b.insert(n); } )* 
       )
    ;


param_list returns [ ParamList plist ]
{
    plist = null;
}
    : #(PARAMS  { plist = new ParamList(); }
          (id:ID  { plist.insert(id.getText()); }
	  )*
      )
    ;

def returns [ Def d ]
{
    d = null;
    Block b;
    Expr e;
    ParamList p;
}
    : #("constant" id1:ID e=expr) { d = new ConstantDef(id1.getText(), e); }
    | #("unit" id2:ID e=expr) { d = new UnitDef(id2.getText(), e); }
    | #("function" id3:ID p=param_list b=block)
          { d = new FunctionDef(id3.getText(), p, b); }
    | #("alias" id4:ID id5:ID)
          { d = new AliasDef(id4.getText(), id5.getText()); }
    ;
