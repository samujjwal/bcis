# Use Sun Java Coding Standards #

In general, follow the Java coding standards published by Sun:
http://java.sun.com/docs/codeconv/

Quick Reference:

## Spacing ##
  * Indents are 4 spaces
  * Put a space between keywords (`while`, `for`) and parentheses
  * No space between method names and parentheses
  * Put a blank line between method definitions

## Names ##
  * Interface and class name are `MixedCase` and start with a capital letter
  * Methods and variables are `mixedCase` and start with a lower-case letter
  * Constants are `UPPER_CASE_WITH_UNDERSCORES`

## Comments ##
  * Use `/* C-style block comments */` for comments longer than one line
  * Use `// single-line comments` to comment-out sections of code
  * Use Java Doc: http://java.sun.com/j2se/javadoc/writingdoccomments

## Braces ##
  * Open brace `{` goes on the same line as the declaration
  * Closing brace `}` goes on a line by itself, indented to match the start of the declaration
  * Always use braces for if/else statements

# ANTLR Grammar Files #

For ANTLR source files, use the conventions from class:

  * Token names in the Lexer are ALLCAPS
  * Nonterminal names in the Parser are lowercase
  * Separate "|" branches in productions go on separate lines