package physicalc;

/** The Node class is an abstract base class for all abstract program
 * representations in the Interpreter.
 *
 * Each Node sub-class represents a specific type of program
 * structure, such as an "if" statement or an addition expression.  A
 * tree of these Nodes is generated by the tree walker.
 *
 * Sub-classes must provide constructors with the appropriate argument
 * types to be used by the tree walker.  For example, a binary
 * operator node would have a constructor with two Expr arguments, one
 * for the left operand and one for the right.
 *
 * Each Node sub-class must provide an "eval" method.  "Eval" is
 * responsible for executing whatever logical part of the program is
 * represented by its node, recursively calling the "eval" methods of
 * its child nodes.  So, for example, an "if" node would "eval" its
 * conditional expression and use that result to decide which block to
 * "eval."
 *
 * "Eval" takes two SymbolTable arguments.  The first is the global
 * symbol table, which will remain constant throughout the program.
 * The second is the current local symbol table.  There is one local
 * symbol table for each function invocation, and one "top-level"
 * symbol table for statements executed outside any function body.
 * All nodes will pass these symbol tables unmodified to their child
 * nodes, except for function calls, which create a new local symbol
 * table.
 *
 * "Eval" returns a Datum object, which, if applicable, is the result
 * of evaluating this node's expression.  Statements and definitions
 * can simply return null.
 *
 * @see Program
 * @see Interpreter
 * @see SymbolTable
 * @author Stuart Sierra, ss2806@columbia.edu
 */
public abstract class Node {

    public abstract Datum eval(SymbolTable globals, SymbolTable locals)
	throws InterpreterError;
}
