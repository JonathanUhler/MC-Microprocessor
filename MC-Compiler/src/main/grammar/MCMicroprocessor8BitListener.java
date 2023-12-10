// Generated from ./src/grammar/MCMicroprocessor8Bit.g4 by ANTLR 4.13.1
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MCMicroprocessor8BitParser}.
 */
public interface MCMicroprocessor8BitListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#exprBoolean}.
	 * @param ctx the parse tree
	 */
	void enterExprBoolean(MCMicroprocessor8BitParser.ExprBooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#exprBoolean}.
	 * @param ctx the parse tree
	 */
	void exitExprBoolean(MCMicroprocessor8BitParser.ExprBooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCMicroprocessor8BitParser#exprInteger}.
	 * @param ctx the parse tree
	 */
	void enterExprInteger(MCMicroprocessor8BitParser.ExprIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCMicroprocessor8BitParser#exprInteger}.
	 * @param ctx the parse tree
	 */
	void exitExprInteger(MCMicroprocessor8BitParser.ExprIntegerContext ctx);
}