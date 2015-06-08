# Statements #

Statements are separated by **either** line breaks or semicolons.

A statement may consist of an expression by itself.  See SyntaxExpressions.

In these examples, capitalized words are place-holders.

## Assignment ##
```
set VAR = EXPRESSION
```

Evaluate EXPRESSION and assign it to a local variable named VAR.  Note that the "=" here is not an operator, it's part of the statement syntax.

If VAR is currently undefined, a new local variable is created.


## if/then/else ##

```
if expression then
    ... statements ...
elsif expression then
    ... statements ...
elsif expression then
    ... statements ...
...more elsif blocks...
else
    ... statements ...
done
```

## For Loops ##

```
for VAR from START to END step STEP_VAL do
    ... statements ...
done
```

Evaluate statements with the variable named VAR bound to successive values, from START up to (and including) END, adding STEP\_VAL each time.  START, END, and STEP\_VAL are all expressions.

## While Loops ##

```
while expression do
    ... statements ...
done
```

## Generic Loops ##

Put a loop inside "loop...done".  Use `break` anywhere in the loop to stop, `restart` to start over at the top of the loop.

```
loop
    ... statements ...
    restart
    ... statements ...
    break
    ... statements ...
done
```