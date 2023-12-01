grammar MCMicroprocessor8Bit;


////////////////////////////////////////////////////////////////////////////////
// P A R S E R   R U L E S
////////////////////////////////////////////////////////////////////////////////

program: (statement | funcDef)* EOF;
statement
    : ID_NAME
    | exprVarDef
    | funcCall
    | blockCond
    | blockFor
    | blockWhile
    ; /* MARK: add more: break/continue in loops, exprAssign */

exprVarDefUint8 : T_UINT8 ID_NAME P_ASSIGN ID_INTEGER;
exprVarDefBool  : T_BOOL ID_NAME P_ASSIGN (S_TRUE | S_FALSE);
exprVarDef      : exprVarDefUint8 | exprVarDefBool;

exprAssign : ID_NAME P_ASSIGN; /* MARK: finish this */

body : P_LBRACE statement* P_RBRACE;

funcDef  : KW_FUNC ID_NAME P_LPAR P_RPAR body; /* MARK: >1 args support */
funcCall : ID_NAME P_LPAR (ID_NAME | ID_INTEGER | S_TRUE | S_FALSE) P_RPAR; /* MARK: >1 args */

blockIf   : KW_IF P_LPAR /* MARK: add boolean expression */ P_RPAR body;
blockElif : KW_ELIF P_LPAR /* MARK: add boolean expression */ P_RPAR body;
blockElse : KW_ELSE body;
blockCond : blockIf blockElif* blockElse?;

blockFor   : KW_FOR P_LPAR /* MARK */ P_SEMICOLON /* MARK */ P_SEMICOLON /* MARK */ body;
blockWhile : KW_WHILE P_LPAR /* MARK: add boolean expression */ P_RPAR body;


////////////////////////////////////////////////////////////////////////////////
// L E X E R   T O K E N   R U L E S
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

T_UINT8 : 'uint8';
T_BOOL  : 'bool';

S_TRUE  : 'true';
S_FALSE : 'false';

KW_FUNC     : 'func';
KW_IF       : 'if';
KW_ELIF     : 'elif';
KW_ELSE     : 'else';
KW_FOR      : 'for';
KW_WHILE    : 'while';
KW_CONTINUE : 'continue';
KW_BREAK    : 'break';

BI_EXIT : 'exit';
BI_FREE : 'free';

P_REASSIGN  : (P_PLUS | P_MINUS | P_BOR | P_BAND) P_ASSIGN;
P_INFIX
    : P_PLUS
    | P_MINUS
    | P_LOR
    | P_BOR
    | P_LAND
    | P_BAND
    | P_EQ
    | P_NEQ
    | P_GT
    | P_LT
    | P_GE
    | P_LE
    ;
P_PREFIX
    : P_LNOT
    | P_BNOT
    ;

P_PLUS      : '+';
P_MINUS     : '-';
P_ASSIGN    : '=';
P_LOR       : '||';
P_BOR       : '|';
P_LAND      : '&&';
P_BAND      : '&';
P_LNOT      : '!';
P_BNOT      : '~';
P_EQ        : '==';
P_NEQ       : '!=';
P_GT        : '>';
P_LT        : '<';
P_GE        : '>=';
P_LE        : '<=';
P_LPAR      : '(';
P_RPAR      : ')';
P_LBRACE    : '{';
P_RBRACE    : '}';
P_SEMICOLON : ';';

ID_NAME : (ID_LETTER | '_') (ID_LETTER | ID_DIGIT | '_')*;
ID_INTEGER   : (ID_DIGIT) | (ID_POS_DIGIT ID_DIGIT*);
ID_DIGIT     : '0' | ID_POS_DIGIT;
ID_POS_DIGIT : [1-9];
ID_LETTER    : [A-Za-z];
ID_COMMENT   : '//' ~[\r\n]* -> skip;
ID_WS        : [ \n\t\r]+ -> skip;
