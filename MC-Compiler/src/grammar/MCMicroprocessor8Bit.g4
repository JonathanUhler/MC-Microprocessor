grammar MCMicroprocessor8Bit;


////////////////////////////////////////////////////////////////////////////////
// P A R S E R   R U L E S
////////////////////////////////////////////////////////////////////////////////


/*
// Any statement that may exist anywhere in the file (creating a program)
program: (statement | exprFuncDef)* EOF;

// Any statement that may exist within a control structure
statement
    : ID_NAME
    | exprVarDef
    | exprBoolean
    | exprInteger
    | exprAssign
    | exprFuncCall
    | exprConditional
    | exprFor
    | exprWhile
    ;

// Control structure body
body : P_LBRACE statement* P_RBRACE;
*/

// Variable definition expressions
exprVarDefUint8 : T_UINT8 ID_NAME P_ASSIGN exprInteger;
exprVarDefBool  : T_BOOL ID_NAME P_ASSIGN exprBoolean;
exprVarDef      : exprVarDefUint8 | exprVarDefBool;

// Expression literals by data type
exprBoolean
    : ID_NAME
    | S_BOOLEAN
    | exprBoolean P_LBINARY exprBoolean
    | exprInteger P_LBINARY exprInteger
    | P_LUNARY exprBoolean
    | P_LPAR exprBoolean P_RPAR
    ;
exprInteger
    : ID_NAME
    | ID_INTEGER
    | exprInteger P_BBINARY exprInteger
    | P_BUNARY exprInteger
    | P_LPAR exprInteger P_RPAR
    ;

/*
exprAssign      : ;
exprFuncDef     : ;
exprFuncCall    : ;
exprIf          : ;
exprElif        : ;
exprElse        : ;
exprConditional : exprIf exprElif* exprElse?;
exprFor         : ;
exprWhile       : ;
*/


////////////////////////////////////////////////////////////////////////////////
// L E X E R   T O K E N   R U L E S
//
// Note about lexer behavior: When multiple lexer rules match a string, ANTLR
// assumes the match that is defined first in this grammar file. To avoid
// unwanted issues from this behavior, lexer rules should be defined from the
// most broad/able to capture to the least broad/able to capture (e.g. an integer
// then a digit than a non-zero digit, or a word then a letter then a character
// then a letter).
////////////////////////////////////////////////////////////////////////////////
// T Y P E   D E S C R I P T I O N S
//
// Each BNF symbol begins with a "type" that identifies its purpose in a generic
// manner. The currently defined sets of symbol "types" are as follows:
//
//  "ID": A generic, regex-style symbol identifier. These are used to match
//        arbitrary patterns (like all digits, all letters, etc).
//  "T": The name of a data type.
//  "S": The name of a singleton value (e.g. true and false).
//  "KW": The name of a keyword in the language (e.g. "if"). Keywords are generally
//        used to create functional blocks.
//  "BI": A built-in function name. These are a superset of keywords, but are all
//        usable as functions.
//  "P": A punctuator (e.g. mathematical operators, grouping symbols, etc).

// Data type names
T_UINT8 : 'uint8';
T_BOOL  : 'bool';

// Singleton values
S_BOOLEAN : S_TRUE | S_FALSE;
S_TRUE    : 'true';
S_FALSE   : 'false';

// Reserved keywords for control structures
KW_FUNC     : 'func';
KW_IF       : 'if';
KW_ELIF     : 'elif';
KW_ELSE     : 'else';
KW_FOR      : 'for';
KW_WHILE    : 'while';
KW_CONTINUE : 'continue';
KW_BREAK    : 'break';

// Built-in function references
BI_EXIT : 'exit';
BI_FREE : 'free';

// Complex punctuators
P_REASSIGN  : (P_PLUS | P_MINUS | P_BOR | P_BAND) P_ASSIGN;
P_BBINARY
    : P_PLUS
    | P_MINUS
    | P_BOR
    | P_BAND
    ;
P_BUNARY
    : P_BNOT
    ;
P_LBINARY
    : P_EQ
    | P_LOR
    | P_LAND
    | P_NEQ
    ;
P_LUNARY
    : P_LNOT
    ;

// Basic punctuators
P_PLUS      : '+';
P_MINUS     : '-';
P_EQ        : '==';
P_ASSIGN    : '=';
P_LOR       : '||';
P_BOR       : '|';
P_LAND      : '&&';
P_BAND      : '&';
P_NEQ       : '!=';
P_LNOT      : '!';
P_BNOT      : '~';
P_LPAR      : '(';
P_RPAR      : ')';
P_LBRACE    : '{';
P_RBRACE    : '}';
P_SEMICOLON : ';';

// Identifiers
ID_NAME      : (ID_LETTER | '_') (ID_LETTER | ID_DIGIT | '_')*;
ID_INTEGER   : (ID_DIGIT) | (ID_POS_DIGIT ID_DIGIT*);
ID_DIGIT     : '0' | ID_POS_DIGIT;
ID_POS_DIGIT : [1-9];
ID_LETTER    : [A-Za-z];
ID_COMMENT   : '//' ~[\r\n]* -> skip;
ID_WS        : [ \n\t\r]+ -> skip;
