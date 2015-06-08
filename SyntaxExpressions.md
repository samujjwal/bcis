# Expressions #

## Operators in order of precedence ##

Adapted from C operator precedence: http://www.difranco.net/cop2220/op-prec.htm

`( )` Parentheses

`[ ]` brackets (array subscript or array literal)

`+ -` Unary plus/minus (attached to numbers)

`^` Exponentiation (see below)

`* /` Multiplication and division

`+ -` addition and subtraction

`< <= > >=` relational

`=` relational "equals" (this is not assignment)

`not` logical NOT

`and` logical AND

`or` logical OR

`in` Unit conversion operator (see below)

~~`find ... given ...` inferred calculation~~


## Exponents and Roots ##

The exponent operator (`^`) is a binary operator that raises its left argument to the power of its right argument.  Examples: `2^64`, `10^100`, `m/s^2`

Roots can be obtained by raising a number to a fractional power, like `3^(1/2)`.  This makes it trivial to write functions for square roots, cube roots, etc.


## Unit Conversion ##

The special `in` operator is a binary operator that converts its left argument to the units of its right argument.  So `3 meters in feet` returns 9.84251969 feet.  `50 mph in kph` returns 80.4672 kph.

Of course, both arguments must include defined units, and both arguments must have the same _quantity_.


## Inferred Calculation ##

**This feature will likely be removed as too difficult.**

The special `find...given` operator has 2 arguments.  The first appears after the `find` and is an expression that must evaluate to a unit.  The second argument appears after the `given` and must evaluate to a list of numbers with different units.  Based on the "given" units and the requested "find" units, the interpreter figures out the necessary sequence of conversions and calculations to obtain the correct result.

Examples:

`find newtons given [8 m/s^2, 4 kg]` returns 32 Newtons.

`find m/s^2 given [39.2, 4 seconds]` returns 9.8 meters/second^2.