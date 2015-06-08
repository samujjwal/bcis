# Definitions #

Four things can be defined: quantities, units, constants, and functions.

Definitions may only appear at the top level of a program; definitions may not be nested.


## Quantities ##

A quantity is a fundamental type of physical property, distinct from its unit of measurement.  Examples are fundamental properties such as mass and time, and derived quantities such as velocity and momentum.

Fundamental quantities are defined with a simple declaration:
```
quantity IDENTIFIER
```
where IDENTIFIER is any valid identifier.

Derived quantities are defined with algebraic expressions:
```
quantity IDENTIFIER = EXPRESSION
```
Where EXPRESSION may only contain other quantities and the basic mathematical operators +, -, /, `*`, and `^`.


## Units ##

Units are the specific forms of measurement used for different quantities.  There may be many units for a single quantity.  Examples are meters, feet, seconds, and Liters.

When a unit is the first unit defined for a given quantity, the quantity must be specified in the declaration, like so:
```
unit IDENTIFIER for QUANTITY
```
Where QUANTITY is the name of a previously-defined quantity.

Other units should be defined in terms of expressions:
```
unit IDENTIFIER = EXPRESSION
```
Where EXPRESSION may contain only previously-defined units, numbers, and the mathematical operators +, -, /, **, and ^.**

For example:
```
unit meter for length
unit centimeter = 0.01 meter
unit millimeter = 0.1 centimeter
```


## Constants ##

A constant is like a variable, but it has global scope and cannot be changed with 'set'.
```
constant IDENTIFIER = EXPRESSION
```
Where EXPRESSION is a number with (optionally) units.


## Functions ##

Functions have any number of input arguments, separated by commas.
```
function IDENTIFIER( ARG1, ARG2, ARG3 )
   ... STATEMENTS ...
   return EXPRESSION
done
```

Function arguments are not explicitly typed, but types may be checked using "assert" statements.

Control returns from a function after the last statement or on evaluating a "return" statement.