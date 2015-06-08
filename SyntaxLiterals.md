# Literals #

## True/False ##

Literal boolean values are represented by the tokens `true` and `false`.

## Strings ##

Strings are surrounded by double quotation marks.  A double quotation mark may be placed inside a string by using two double quotation marks in a row.  So the string
```
my "friend" Bob
```
> could be written
```
"my ""friend"" Bob"
```

Strings may contain any character, including line breaks.

C-style string escapes -- `\n`, `\t`, etc -- are not supported.

## Numbers ##

All numbers are stored as arbitrary-precision decimals.  Numbers may be written as integers or with C-style floating point syntax.  The following numbers are all identical:
```
42
42e1
42.
42.0
42.0e1
42.00000000000000000000
```

### Implicit Multiplication of Units ###

A number may be followed by a space and a then a unit identifier.  This is read as an implicit multiplication between the number and the unit.

So `3 m/s` is the same as `3*m/s`, if both "m" and "s" are defined as units.

## Lists ##

Literal lists are enclosed in square brackets, and items are separated by commas.  Lists may be heterogeneous (contain elements of different types).
```
[ 43, 12 m/s^2, "strings" ]
```

Nested lists are allowed:
```
[ 1, 2, [3, 4], 5, 6 ]
```


## Vectors ##

Vectors are enclosed in curly brackets, and components are separated by commas.  Vector components must be numbers (optionally including units), and those numbers must all have the same _quantity_ (though they may have different units).

Example vectors
```
{3, 4}
{2 meters, 7 feet}
```
Vectors may not be nested.  Matrices are not supported.



## Identifiers ##

See SyntaxIdentifiers.