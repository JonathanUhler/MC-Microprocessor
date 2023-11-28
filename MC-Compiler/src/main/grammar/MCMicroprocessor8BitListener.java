// Generated from ./src/grammar/MCMicroprocessor8Bit.g4 by ANTLR 4.13.1
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MCMicroprocessor8BitParser}.
 */
public interface MCMicroprocessor8BitListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MCMicroprocessor8BitParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MCMicroprocessor8BitParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MCMicroprocessor8BitParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MCMicroprocessor8BitParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#id_name}.
	 * @param ctx the parse tree
	 */
	void enterId_name(MCMicroprocessor8BitParser.Id_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#id_name}.
	 * @param ctx the parse tree
	 */
	void exitId_name(MCMicroprocessor8BitParser.Id_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#expr_var_def_uint8}.
	 * @param ctx the parse tree
	 */
	void enterExpr_var_def_uint8(MCMicroprocessor8BitParser.Expr_var_def_uint8Context ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#expr_var_def_uint8}.
	 * @param ctx the parse tree
	 */
	void exitExpr_var_def_uint8(MCMicroprocessor8BitParser.Expr_var_def_uint8Context ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#expr_var_def_bool}.
	 * @param ctx the parse tree
	 */
	void enterExpr_var_def_bool(MCMicroprocessor8BitParser.Expr_var_def_boolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#expr_var_def_bool}.
	 * @param ctx the parse tree
	 */
	void exitExpr_var_def_bool(MCMicroprocessor8BitParser.Expr_var_def_boolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#expr_var_def}.
	 * @param ctx the parse tree
	 */
	void enterExpr_var_def(MCMicroprocessor8BitParser.Expr_var_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#expr_var_def}.
	 * @param ctx the parse tree
	 */
	void exitExpr_var_def(MCMicroprocessor8BitParser.Expr_var_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#expr_assign}.
	 * @param ctx the parse tree
	 */
	void enterExpr_assign(MCMicroprocessor8BitParser.Expr_assignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#expr_assign}.
	 * @param ctx the parse tree
	 */
	void exitExpr_assign(MCMicroprocessor8BitParser.Expr_assignContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(MCMicroprocessor8BitParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(MCMicroprocessor8BitParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#func_def}.
	 * @param ctx the parse tree
	 */
	void enterFunc_def(MCMicroprocessor8BitParser.Func_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#func_def}.
	 * @param ctx the parse tree
	 */
	void exitFunc_def(MCMicroprocessor8BitParser.Func_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(MCMicroprocessor8BitParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(MCMicroprocessor8BitParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#block_if}.
	 * @param ctx the parse tree
	 */
	void enterBlock_if(MCMicroprocessor8BitParser.Block_ifContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#block_if}.
	 * @param ctx the parse tree
	 */
	void exitBlock_if(MCMicroprocessor8BitParser.Block_ifContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#block_elif}.
	 * @param ctx the parse tree
	 */
	void enterBlock_elif(MCMicroprocessor8BitParser.Block_elifContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#block_elif}.
	 * @param ctx the parse tree
	 */
	void exitBlock_elif(MCMicroprocessor8BitParser.Block_elifContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#block_else}.
	 * @param ctx the parse tree
	 */
	void enterBlock_else(MCMicroprocessor8BitParser.Block_elseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#block_else}.
	 * @param ctx the parse tree
	 */
	void exitBlock_else(MCMicroprocessor8BitParser.Block_elseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#block_cond}.
	 * @param ctx the parse tree
	 */
	void enterBlock_cond(MCMicroprocessor8BitParser.Block_condContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#block_cond}.
	 * @param ctx the parse tree
	 */
	void exitBlock_cond(MCMicroprocessor8BitParser.Block_condContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#block_for}.
	 * @param ctx the parse tree
	 */
	void enterBlock_for(MCMicroprocessor8BitParser.Block_forContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#block_for}.
	 * @param ctx the parse tree
	 */
	void exitBlock_for(MCMicroprocessor8BitParser.Block_forContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#block_while}.
	 * @param ctx the parse tree
	 */
	void enterBlock_while(MCMicroprocessor8BitParser.Block_whileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#block_while}.
	 * @param ctx the parse tree
	 */
	void exitBlock_while(MCMicroprocessor8BitParser.Block_whileContext ctx);
}