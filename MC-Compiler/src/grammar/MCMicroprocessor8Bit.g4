grammar MCMicroprocessor8Bit;


////////////////////////////////////////////////////////////////////////////////
// P A R S E R   R U L E S
////////////////////////////////////////////////////////////////////////////////

program: (statement | func_def)+;
statement
    : id_name
    | expr_var_def
    | func_call
    | block_cond
    | block_for
    | block_while
    | /* MARK */;

id_name : ID_LETTER (ID_LETTER | ID_DIGIT | '_')*;

expr_var_def_uint8 : T_UINT8 id_name P_ASSIGN ID_INTEGER;
expr_var_def_bool  : T_BOOL id_name P_ASSIGN (S_TRUE | S_FALSE);
expr_var_def       : expr_var_def_uint8 | expr_var_def_bool;

expr_bool           : /* MARK */;
expr_assign_literal : /* MARK */;
expr_assign_mixed   : /* MARK */;
expr_assign_names   : /* MARK */;
expr_assign : id_name P_ASSIGN ;

body : P_LBRACE statement* P_RBRACE;

func_def  : KW_FUNC id_name P_LPAR P_RPAR body;
func_call : id_name P_LPAR (id_name | ID_INTEGER | S_TRUE | S_FALSE) P_RPAR;

block_if   : KW_IF P_LPAR /* MARK */ P_RPAR body;
block_elif : KW_ELIF P_LPAR /* MARK */ P_RPAR body;
block_else : KW_ELSE body;
block_cond : block_if block_elif* block_else*;

block_for   : KW_FOR P_LPAR /* MARK */ P_SEMICOLON /* MARK */ P_SEMICOLON /* MARK */ body;
block_while : KW_WHILE P_LPAR /* MARK */ P_RPAR body;


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

KW_FUNC  : 'func';
KW_IF    : 'if';
KW_ELIF  : 'elif';
KW_ELSE  : 'else';
KW_FOR   : 'for';
KW_WHILE : 'while';

BI_EXIT : 'exit';
BI_FREE : 'free';

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
    | P_LE;
P_PREFIX
    : P_PLUS
    | P_MINUS
    | P_LNOT
    | P_BNOT;

ID_WS        : [ \n\t\r]+ -> skip;
ID_POS_DIGIT : [1-9];
ID_DIGIT     : '0' | ID_POS_DIGIT;
ID_LETTER    : [A-Za-z];
ID_INTEGER   : ID_DIGIT | ID_POS_DIGIT ID_DIGIT*;
