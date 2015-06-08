# Introduction #

This page links to descriptions of each class/interface that needs to be implemented.

All classes are in the package **physicalc**.


# Program Node Classes #

These are objects created and returned by the Tree Walker.  Each node represents a single piece of the program.  Each class in this hierarchy has a constructor and an "eval" method.  The "eval" method takes two SymbolTable arguments; the first is the global symbol table, the second is the local symbol table for the current function (or the top level).

  * Node
    * Stmt
      * Set
      * Break
      * Next
      * Return
      * If
      * IfElse
      * While
      * For
      * Block (a collection of statements in the body of another statement)
    * ExprList (a collection of expressions, separated by commas)
    * Expr
      * Literal (a constant literal number, string, etc.)
      * Id
      * FunCall (function call)
      * Logical
        * And
        * Or
        * Not
        * Rel (relational operators)
      * Op
        * Access (array access, like `a[4]`)
        * Arith (arithmetic)
        * Unary (unary minus)
    * Def (definition)
      * ConstantDef
      * FunctionDef
      * UnitDef
      * AliasDef
    * Load (like an include statement in C)
    * Program (root node for the whole program)


# Data Classes #

These objects represent "data" -- anything that can be stored as a
value or used in expressions.

  * Datum
    * Number
    * Vector
    * Unit
    * PBoolean
    * PString
    * PList


# Other Classes #

  * Function
  * Variable
  * Constant (Like a variable, but can't be changed. Is this necessary?)
  * SymbolTable
  * RuntimeObject (an interface for any object that can be placed in the symbol table)
  * Interpreter (given a stream of text, runs the lexer/parser/walker and executes the program)
  * Main (contains the "public static void main" method, responsible for handling the command line and opening files)


# Exception Classes #

  * InterpreterError (any error found while running the code)
    * TypeError (attempt to use an operator on incompatible types)
    * UndefinedError (attempt to use an undefined identifier)
    * BoundsError (attempt to access beyond the end of a list/vector/string)