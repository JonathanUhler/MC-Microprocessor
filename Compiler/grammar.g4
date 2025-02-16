expression
  : primary_expression
  | PLUS expression
  | MINUS expression
  | BITWISE_NOT expression
  | expression TIMES expression
  | expression DIVIDE expression
  | expression PLUS expression
  | expression MINUS expression
  | expression LESS_THAN expression
  | expression GREATER_THAN expression
  | expression LESS_THAN_EQUAL expression
  | expression GREATER_THAN_EQUAL expression
  | expression EQUAL_TO expression
  | expression NOT_EQUAL_TO expression
  | expression BITWISE_AND expression
  | expression BITWISE_XOR expression
  | expression BITWISE_OR expression
  ;

DONE expression    // Without left recursion
  : primary_expression expression_tail
  | PLUS expression expression_tail
  | MINUS expression expression_tail
  | BITWISE_NOT expression expression_tail
  ;
DONE expression_tail
  : TIMES expression expression_tail
  | DIVIDE expression expression_tail
  | PLUS expression expression_tail
  | MINUS expression expression_tail
  | LESS_THAN expression expression_tail
  | GREATER_THAN expression expression_tail
  | LESS_THAN_EQUAL expression expression_tail
  | GREATER_THAN_EQUAL expression expression_tail
  | EQUAL_TO expression expression_tail
  | NOT_EQUAL_TO expression expression_tail
  | BITWISE_AND expression expression_tail
  | BITWISE_XOR expression expression_tail
  | BITWISE_OR expression expression_tail
  | ε
  ;

DONE primary_expression
  : IDENTIFIER
  | DEC_NUMBER
  | HEX_NUMBER
  | L_PAREN expression R_PAREN
  ;

DONE statement
  : compound_statement
  | expression_statement
  ;

DONE compound_statement
  : L_BRACKET R_BRACKET
  | L_BRACKET statement_list R_BRACKET
  | selection_statement
  | iteration_statement
  ;

statement_list
  : statement
  | statement_list statement
  ;

DONE statement_list  // Without left recursion
  : statement statement_list_tail
  ;
DONE statement_list_tail
  : statement statement_list_tail
  | ε
  ;

DONE expression_statement
  : SEMICOLON
  | expression SEMICOLON
  ;

DONE if_statement
  : IF L_PAREN expression R_PAREN statement
  ;

DONE if_else_statement
  : if_statement ELSE statement
  ;

DONE selection_statement
  : if_statement
  | if_else_statement
  ;

DONE for_statement
  : FOR L_PAREN expression_statement expression_statement R_PAREN statement
  | FOR L_PAREN expression_statement expression_statement expression R_PAREN statement
  ;

DONE while_statement
  : WHILE L_PAREN expression R_PAREN statement
  ;

DONE do_while_statement
  : DO statement WHILE L_PAREN expression R_PAREN SEMICOLON

DONE iteration_statement
  : while_statement
  | do_while_statement
  | for_statement
  ;

DONE subroutine
  : SUBROUTINE IDENTIFIER L_PAREN R_PAREN statement
  | SUBROUTINE IDENTIFIER L_PAREN argument_list R_PAREN statement
  ;

argument_list
  : expression
  | argument_list COMMA expression
  ;

argument_list  // Without left recursion
  : expression argument_list_tail
  ;
argument_list_tail
  : COMMA expression argument_list_tail
  | ε
  ;