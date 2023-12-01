// Generated from ./src/grammar/MCMicroprocessor8Bit.g4 by ANTLR 4.13.1
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MCMicroprocessor8BitParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T_UINT8=1, T_BOOL=2, S_TRUE=3, S_FALSE=4, KW_FUNC=5, KW_IF=6, KW_ELIF=7, 
		KW_ELSE=8, KW_FOR=9, KW_WHILE=10, KW_CONTINUE=11, KW_BREAK=12, BI_EXIT=13, 
		BI_FREE=14, P_REASSIGN=15, P_INFIX=16, P_PREFIX=17, P_PLUS=18, P_MINUS=19, 
		P_ASSIGN=20, P_LOR=21, P_BOR=22, P_LAND=23, P_BAND=24, P_LNOT=25, P_BNOT=26, 
		P_EQ=27, P_NEQ=28, P_GT=29, P_LT=30, P_GE=31, P_LE=32, P_LPAR=33, P_RPAR=34, 
		P_LBRACE=35, P_RBRACE=36, P_SEMICOLON=37, ID_NAME=38, ID_INTEGER=39, ID_DIGIT=40, 
		ID_POS_DIGIT=41, ID_LETTER=42, ID_COMMENT=43, ID_WS=44;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_exprVarDefUint8 = 2, RULE_exprVarDefBool = 3, 
		RULE_exprVarDef = 4, RULE_exprAssign = 5, RULE_body = 6, RULE_funcDef = 7, 
		RULE_funcCall = 8, RULE_blockIf = 9, RULE_blockElif = 10, RULE_blockElse = 11, 
		RULE_blockCond = 12, RULE_blockFor = 13, RULE_blockWhile = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "exprVarDefUint8", "exprVarDefBool", "exprVarDef", 
			"exprAssign", "body", "funcDef", "funcCall", "blockIf", "blockElif", 
			"blockElse", "blockCond", "blockFor", "blockWhile"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'uint8'", "'bool'", "'true'", "'false'", "'func'", "'if'", "'elif'", 
			"'else'", "'for'", "'while'", "'continue'", "'break'", "'exit'", "'free'", 
			null, null, null, "'+'", "'-'", "'='", "'||'", "'|'", "'&&'", "'&'", 
			"'!'", "'~'", "'=='", "'!='", "'>'", "'<'", "'>='", "'<='", "'('", "')'", 
			"'{'", "'}'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "T_UINT8", "T_BOOL", "S_TRUE", "S_FALSE", "KW_FUNC", "KW_IF", "KW_ELIF", 
			"KW_ELSE", "KW_FOR", "KW_WHILE", "KW_CONTINUE", "KW_BREAK", "BI_EXIT", 
			"BI_FREE", "P_REASSIGN", "P_INFIX", "P_PREFIX", "P_PLUS", "P_MINUS", 
			"P_ASSIGN", "P_LOR", "P_BOR", "P_LAND", "P_BAND", "P_LNOT", "P_BNOT", 
			"P_EQ", "P_NEQ", "P_GT", "P_LT", "P_GE", "P_LE", "P_LPAR", "P_RPAR", 
			"P_LBRACE", "P_RBRACE", "P_SEMICOLON", "ID_NAME", "ID_INTEGER", "ID_DIGIT", 
			"ID_POS_DIGIT", "ID_LETTER", "ID_COMMENT", "ID_WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MCMicroprocessor8Bit.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MCMicroprocessor8BitParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MCMicroprocessor8BitParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274877908582L) != 0)) {
				{
				setState(32);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T_UINT8:
				case T_BOOL:
				case KW_IF:
				case KW_FOR:
				case KW_WHILE:
				case ID_NAME:
					{
					setState(30);
					statement();
					}
					break;
				case KW_FUNC:
					{
					setState(31);
					funcDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public ExprVarDefContext exprVarDef() {
			return getRuleContext(ExprVarDefContext.class,0);
		}
		public FuncCallContext funcCall() {
			return getRuleContext(FuncCallContext.class,0);
		}
		public BlockCondContext blockCond() {
			return getRuleContext(BlockCondContext.class,0);
		}
		public BlockForContext blockFor() {
			return getRuleContext(BlockForContext.class,0);
		}
		public BlockWhileContext blockWhile() {
			return getRuleContext(BlockWhileContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				match(ID_NAME);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				exprVarDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				funcCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(42);
				blockCond();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(43);
				blockFor();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(44);
				blockWhile();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprVarDefUint8Context extends ParserRuleContext {
		public TerminalNode T_UINT8() { return getToken(MCMicroprocessor8BitParser.T_UINT8, 0); }
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode P_ASSIGN() { return getToken(MCMicroprocessor8BitParser.P_ASSIGN, 0); }
		public TerminalNode ID_INTEGER() { return getToken(MCMicroprocessor8BitParser.ID_INTEGER, 0); }
		public ExprVarDefUint8Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprVarDefUint8; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExprVarDefUint8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExprVarDefUint8(this);
		}
	}

	public final ExprVarDefUint8Context exprVarDefUint8() throws RecognitionException {
		ExprVarDefUint8Context _localctx = new ExprVarDefUint8Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_exprVarDefUint8);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T_UINT8);
			setState(48);
			match(ID_NAME);
			setState(49);
			match(P_ASSIGN);
			setState(50);
			match(ID_INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprVarDefBoolContext extends ParserRuleContext {
		public TerminalNode T_BOOL() { return getToken(MCMicroprocessor8BitParser.T_BOOL, 0); }
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode P_ASSIGN() { return getToken(MCMicroprocessor8BitParser.P_ASSIGN, 0); }
		public TerminalNode S_TRUE() { return getToken(MCMicroprocessor8BitParser.S_TRUE, 0); }
		public TerminalNode S_FALSE() { return getToken(MCMicroprocessor8BitParser.S_FALSE, 0); }
		public ExprVarDefBoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprVarDefBool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExprVarDefBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExprVarDefBool(this);
		}
	}

	public final ExprVarDefBoolContext exprVarDefBool() throws RecognitionException {
		ExprVarDefBoolContext _localctx = new ExprVarDefBoolContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprVarDefBool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T_BOOL);
			setState(53);
			match(ID_NAME);
			setState(54);
			match(P_ASSIGN);
			setState(55);
			_la = _input.LA(1);
			if ( !(_la==S_TRUE || _la==S_FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprVarDefContext extends ParserRuleContext {
		public ExprVarDefUint8Context exprVarDefUint8() {
			return getRuleContext(ExprVarDefUint8Context.class,0);
		}
		public ExprVarDefBoolContext exprVarDefBool() {
			return getRuleContext(ExprVarDefBoolContext.class,0);
		}
		public ExprVarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprVarDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExprVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExprVarDef(this);
		}
	}

	public final ExprVarDefContext exprVarDef() throws RecognitionException {
		ExprVarDefContext _localctx = new ExprVarDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_exprVarDef);
		try {
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_UINT8:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				exprVarDefUint8();
				}
				break;
			case T_BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				exprVarDefBool();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprAssignContext extends ParserRuleContext {
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode P_ASSIGN() { return getToken(MCMicroprocessor8BitParser.P_ASSIGN, 0); }
		public ExprAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExprAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExprAssign(this);
		}
	}

	public final ExprAssignContext exprAssign() throws RecognitionException {
		ExprAssignContext _localctx = new ExprAssignContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(ID_NAME);
			setState(62);
			match(P_ASSIGN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public TerminalNode P_LBRACE() { return getToken(MCMicroprocessor8BitParser.P_LBRACE, 0); }
		public TerminalNode P_RBRACE() { return getToken(MCMicroprocessor8BitParser.P_RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(P_LBRACE);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 274877908550L) != 0)) {
				{
				{
				setState(65);
				statement();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			match(P_RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDefContext extends ParserRuleContext {
		public TerminalNode KW_FUNC() { return getToken(MCMicroprocessor8BitParser.KW_FUNC, 0); }
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitFuncDef(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(KW_FUNC);
			setState(74);
			match(ID_NAME);
			setState(75);
			match(P_LPAR);
			setState(76);
			match(P_RPAR);
			setState(77);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncCallContext extends ParserRuleContext {
		public List<TerminalNode> ID_NAME() { return getTokens(MCMicroprocessor8BitParser.ID_NAME); }
		public TerminalNode ID_NAME(int i) {
			return getToken(MCMicroprocessor8BitParser.ID_NAME, i);
		}
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public TerminalNode ID_INTEGER() { return getToken(MCMicroprocessor8BitParser.ID_INTEGER, 0); }
		public TerminalNode S_TRUE() { return getToken(MCMicroprocessor8BitParser.S_TRUE, 0); }
		public TerminalNode S_FALSE() { return getToken(MCMicroprocessor8BitParser.S_FALSE, 0); }
		public FuncCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterFuncCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitFuncCall(this);
		}
	}

	public final FuncCallContext funcCall() throws RecognitionException {
		FuncCallContext _localctx = new FuncCallContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funcCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(ID_NAME);
			setState(80);
			match(P_LPAR);
			setState(81);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 824633720856L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(82);
			match(P_RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockIfContext extends ParserRuleContext {
		public TerminalNode KW_IF() { return getToken(MCMicroprocessor8BitParser.KW_IF, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public BlockIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlockIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlockIf(this);
		}
	}

	public final BlockIfContext blockIf() throws RecognitionException {
		BlockIfContext _localctx = new BlockIfContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_blockIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(KW_IF);
			setState(85);
			match(P_LPAR);
			setState(86);
			match(P_RPAR);
			setState(87);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockElifContext extends ParserRuleContext {
		public TerminalNode KW_ELIF() { return getToken(MCMicroprocessor8BitParser.KW_ELIF, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public BlockElifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockElif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlockElif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlockElif(this);
		}
	}

	public final BlockElifContext blockElif() throws RecognitionException {
		BlockElifContext _localctx = new BlockElifContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_blockElif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(KW_ELIF);
			setState(90);
			match(P_LPAR);
			setState(91);
			match(P_RPAR);
			setState(92);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockElseContext extends ParserRuleContext {
		public TerminalNode KW_ELSE() { return getToken(MCMicroprocessor8BitParser.KW_ELSE, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public BlockElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlockElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlockElse(this);
		}
	}

	public final BlockElseContext blockElse() throws RecognitionException {
		BlockElseContext _localctx = new BlockElseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_blockElse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(KW_ELSE);
			setState(95);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockCondContext extends ParserRuleContext {
		public BlockIfContext blockIf() {
			return getRuleContext(BlockIfContext.class,0);
		}
		public List<BlockElifContext> blockElif() {
			return getRuleContexts(BlockElifContext.class);
		}
		public BlockElifContext blockElif(int i) {
			return getRuleContext(BlockElifContext.class,i);
		}
		public BlockElseContext blockElse() {
			return getRuleContext(BlockElseContext.class,0);
		}
		public BlockCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlockCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlockCond(this);
		}
	}

	public final BlockCondContext blockCond() throws RecognitionException {
		BlockCondContext _localctx = new BlockCondContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_blockCond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			blockIf();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ELIF) {
				{
				{
				setState(98);
				blockElif();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ELSE) {
				{
				setState(104);
				blockElse();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockForContext extends ParserRuleContext {
		public TerminalNode KW_FOR() { return getToken(MCMicroprocessor8BitParser.KW_FOR, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public List<TerminalNode> P_SEMICOLON() { return getTokens(MCMicroprocessor8BitParser.P_SEMICOLON); }
		public TerminalNode P_SEMICOLON(int i) {
			return getToken(MCMicroprocessor8BitParser.P_SEMICOLON, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public BlockForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlockFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlockFor(this);
		}
	}

	public final BlockForContext blockFor() throws RecognitionException {
		BlockForContext _localctx = new BlockForContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_blockFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(KW_FOR);
			setState(108);
			match(P_LPAR);
			setState(109);
			match(P_SEMICOLON);
			setState(110);
			match(P_SEMICOLON);
			setState(111);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockWhileContext extends ParserRuleContext {
		public TerminalNode KW_WHILE() { return getToken(MCMicroprocessor8BitParser.KW_WHILE, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public BlockWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlockWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlockWhile(this);
		}
	}

	public final BlockWhileContext blockWhile() throws RecognitionException {
		BlockWhileContext _localctx = new BlockWhileContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_blockWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(KW_WHILE);
			setState(114);
			match(P_LPAR);
			setState(115);
			match(P_RPAR);
			setState(116);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001,w\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002\u0005"+
		"\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002\b\u0007"+
		"\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002\f\u0007"+
		"\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001\u0000\u0005"+
		"\u0000!\b\u0000\n\u0000\f\u0000$\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001.\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0003\u0004<\b\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0005\u0006C\b\u0006\n\u0006\f\u0006F\t"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0005\f"+
		"d\b\f\n\f\f\fg\t\f\u0001\f\u0003\fj\b\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0000\u0000\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u0000\u0002\u0001\u0000\u0003"+
		"\u0004\u0002\u0000\u0003\u0004&\'r\u0000\"\u0001\u0000\u0000\u0000\u0002"+
		"-\u0001\u0000\u0000\u0000\u0004/\u0001\u0000\u0000\u0000\u00064\u0001"+
		"\u0000\u0000\u0000\b;\u0001\u0000\u0000\u0000\n=\u0001\u0000\u0000\u0000"+
		"\f@\u0001\u0000\u0000\u0000\u000eI\u0001\u0000\u0000\u0000\u0010O\u0001"+
		"\u0000\u0000\u0000\u0012T\u0001\u0000\u0000\u0000\u0014Y\u0001\u0000\u0000"+
		"\u0000\u0016^\u0001\u0000\u0000\u0000\u0018a\u0001\u0000\u0000\u0000\u001a"+
		"k\u0001\u0000\u0000\u0000\u001cq\u0001\u0000\u0000\u0000\u001e!\u0003"+
		"\u0002\u0001\u0000\u001f!\u0003\u000e\u0007\u0000 \u001e\u0001\u0000\u0000"+
		"\u0000 \u001f\u0001\u0000\u0000\u0000!$\u0001\u0000\u0000\u0000\" \u0001"+
		"\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#%\u0001\u0000\u0000\u0000"+
		"$\"\u0001\u0000\u0000\u0000%&\u0005\u0000\u0000\u0001&\u0001\u0001\u0000"+
		"\u0000\u0000\'.\u0005&\u0000\u0000(.\u0003\b\u0004\u0000).\u0003\u0010"+
		"\b\u0000*.\u0003\u0018\f\u0000+.\u0003\u001a\r\u0000,.\u0003\u001c\u000e"+
		"\u0000-\'\u0001\u0000\u0000\u0000-(\u0001\u0000\u0000\u0000-)\u0001\u0000"+
		"\u0000\u0000-*\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-,\u0001"+
		"\u0000\u0000\u0000.\u0003\u0001\u0000\u0000\u0000/0\u0005\u0001\u0000"+
		"\u000001\u0005&\u0000\u000012\u0005\u0014\u0000\u000023\u0005\'\u0000"+
		"\u00003\u0005\u0001\u0000\u0000\u000045\u0005\u0002\u0000\u000056\u0005"+
		"&\u0000\u000067\u0005\u0014\u0000\u000078\u0007\u0000\u0000\u00008\u0007"+
		"\u0001\u0000\u0000\u00009<\u0003\u0004\u0002\u0000:<\u0003\u0006\u0003"+
		"\u0000;9\u0001\u0000\u0000\u0000;:\u0001\u0000\u0000\u0000<\t\u0001\u0000"+
		"\u0000\u0000=>\u0005&\u0000\u0000>?\u0005\u0014\u0000\u0000?\u000b\u0001"+
		"\u0000\u0000\u0000@D\u0005#\u0000\u0000AC\u0003\u0002\u0001\u0000BA\u0001"+
		"\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000"+
		"DE\u0001\u0000\u0000\u0000EG\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000"+
		"\u0000GH\u0005$\u0000\u0000H\r\u0001\u0000\u0000\u0000IJ\u0005\u0005\u0000"+
		"\u0000JK\u0005&\u0000\u0000KL\u0005!\u0000\u0000LM\u0005\"\u0000\u0000"+
		"MN\u0003\f\u0006\u0000N\u000f\u0001\u0000\u0000\u0000OP\u0005&\u0000\u0000"+
		"PQ\u0005!\u0000\u0000QR\u0007\u0001\u0000\u0000RS\u0005\"\u0000\u0000"+
		"S\u0011\u0001\u0000\u0000\u0000TU\u0005\u0006\u0000\u0000UV\u0005!\u0000"+
		"\u0000VW\u0005\"\u0000\u0000WX\u0003\f\u0006\u0000X\u0013\u0001\u0000"+
		"\u0000\u0000YZ\u0005\u0007\u0000\u0000Z[\u0005!\u0000\u0000[\\\u0005\""+
		"\u0000\u0000\\]\u0003\f\u0006\u0000]\u0015\u0001\u0000\u0000\u0000^_\u0005"+
		"\b\u0000\u0000_`\u0003\f\u0006\u0000`\u0017\u0001\u0000\u0000\u0000ae"+
		"\u0003\u0012\t\u0000bd\u0003\u0014\n\u0000cb\u0001\u0000\u0000\u0000d"+
		"g\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000fi\u0001\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000hj\u0003\u0016"+
		"\u000b\u0000ih\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000j\u0019"+
		"\u0001\u0000\u0000\u0000kl\u0005\t\u0000\u0000lm\u0005!\u0000\u0000mn"+
		"\u0005%\u0000\u0000no\u0005%\u0000\u0000op\u0003\f\u0006\u0000p\u001b"+
		"\u0001\u0000\u0000\u0000qr\u0005\n\u0000\u0000rs\u0005!\u0000\u0000st"+
		"\u0005\"\u0000\u0000tu\u0003\f\u0006\u0000u\u001d\u0001\u0000\u0000\u0000"+
		"\u0007 \"-;Dei";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}