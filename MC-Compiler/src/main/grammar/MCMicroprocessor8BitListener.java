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
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#idName}.
	 * @param ctx the parse tree
	 */
	void enterIdName(MCMicroprocessor8BitParser.IdNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#idName}.
	 * @param ctx the parse tree
	 */
	void exitIdName(MCMicroprocessor8BitParser.IdNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#exprVarDefUint8}.
	 * @param ctx the parse tree
	 */
	void enterExprVarDefUint8(MCMicroprocessor8BitParser.ExprVarDefUint8Context ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#exprVarDefUint8}.
	 * @param ctx the parse tree
	 */
	void exitExprVarDefUint8(MCMicroprocessor8BitParser.ExprVarDefUint8Context ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#exprVarDefBool}.
	 * @param ctx the parse tree
	 */
	void enterExprVarDefBool(MCMicroprocessor8BitParser.ExprVarDefBoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#exprVarDefBool}.
	 * @param ctx the parse tree
	 */
	void exitExprVarDefBool(MCMicroprocessor8BitParser.ExprVarDefBoolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#exprVarDef}.
	 * @param ctx the parse tree
	 */
	void enterExprVarDef(MCMicroprocessor8BitParser.ExprVarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#exprVarDef}.
	 * @param ctx the parse tree
	 */
	void exitExprVarDef(MCMicroprocessor8BitParser.ExprVarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#exprAssign}.
	 * @param ctx the parse tree
	 */
	void enterExprAssign(MCMicroprocessor8BitParser.ExprAssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#exprAssign}.
	 * @param ctx the parse tree
	 */
	void exitExprAssign(MCMicroprocessor8BitParser.ExprAssignContext ctx);
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
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MCMicroprocessor8BitParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MCMicroprocessor8BitParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(MCMicroprocessor8BitParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(MCMicroprocessor8BitParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#blockIf}.
	 * @param ctx the parse tree
	 */
	void enterBlockIf(MCMicroprocessor8BitParser.BlockIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#blockIf}.
	 * @param ctx the parse tree
	 */
	void exitBlockIf(MCMicroprocessor8BitParser.BlockIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#blockElif}.
	 * @param ctx the parse tree
	 */
	void enterBlockElif(MCMicroprocessor8BitParser.BlockElifContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#blockElif}.
	 * @param ctx the parse tree
	 */
	void exitBlockElif(MCMicroprocessor8BitParser.BlockElifContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#blockElse}.
	 * @param ctx the parse tree
	 */
	void enterBlockElse(MCMicroprocessor8BitParser.BlockElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#blockElse}.
	 * @param ctx the parse tree
	 */
	void exitBlockElse(MCMicroprocessor8BitParser.BlockElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#blockCond}.
	 * @param ctx the parse tree
	 */
	void enterBlockCond(MCMicroprocessor8BitParser.BlockCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#blockCond}.
	 * @param ctx the parse tree
	 */
	void exitBlockCond(MCMicroprocessor8BitParser.BlockCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#blockFor}.
	 * @param ctx the parse tree
	 */
	void enterBlockFor(MCMicroprocessor8BitParser.BlockForContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#blockFor}.
	 * @param ctx the parse tree
	 */
	void exitBlockFor(MCMicroprocessor8BitParser.BlockForContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#blockWhile}.
	 * @param ctx the parse tree
	 */
	void enterBlockWhile(MCMicroprocessor8BitParser.BlockWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#blockWhile}.
	 * @param ctx the parse tree
	 */
	void exitBlockWhile(MCMicroprocessor8BitParser.BlockWhileContext ctx);
}