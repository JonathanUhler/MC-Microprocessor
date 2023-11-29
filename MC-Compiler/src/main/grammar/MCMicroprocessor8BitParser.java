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
		T__0=1, T_UINT8=2, T_BOOL=3, S_TRUE=4, S_FALSE=5, KW_FUNC=6, KW_IF=7, 
		KW_ELIF=8, KW_ELSE=9, KW_FOR=10, KW_WHILE=11, BI_EXIT=12, BI_FREE=13, 
		P_PLUS=14, P_MINUS=15, P_ASSIGN=16, P_LOR=17, P_BOR=18, P_LAND=19, P_BAND=20, 
		P_LNOT=21, P_BNOT=22, P_EQ=23, P_NEQ=24, P_GT=25, P_LT=26, P_GE=27, P_LE=28, 
		P_LPAR=29, P_RPAR=30, P_LBRACE=31, P_RBRACE=32, P_SEMICOLON=33, P_REASSIGN=34, 
		P_INFIX=35, P_PREFIX=36, ID_WS=37, ID_POS_DIGIT=38, ID_DIGIT=39, ID_LETTER=40, 
		ID_INTEGER=41;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_idName = 2, RULE_exprVarDefUint8 = 3, 
		RULE_exprVarDefBool = 4, RULE_exprVarDef = 5, RULE_exprAssign = 6, RULE_body = 7, 
		RULE_funcDef = 8, RULE_funcCall = 9, RULE_blockIf = 10, RULE_blockElif = 11, 
		RULE_blockElse = 12, RULE_blockCond = 13, RULE_blockFor = 14, RULE_blockWhile = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "idName", "exprVarDefUint8", "exprVarDefBool", 
			"exprVarDef", "exprAssign", "body", "funcDef", "funcCall", "blockIf", 
			"blockElif", "blockElse", "blockCond", "blockFor", "blockWhile"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'_'", "'uint8'", "'bool'", "'true'", "'false'", "'func'", "'if'", 
			"'elif'", "'else'", "'for'", "'while'", "'exit'", "'free'", "'+'", "'-'", 
			"'='", "'||'", "'|'", "'&&'", "'&'", "'!'", "'~'", "'=='", "'!='", "'>'", 
			"'<'", "'>='", "'<='", "'('", "')'", "'{'", "'}'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "T_UINT8", "T_BOOL", "S_TRUE", "S_FALSE", "KW_FUNC", "KW_IF", 
			"KW_ELIF", "KW_ELSE", "KW_FOR", "KW_WHILE", "BI_EXIT", "BI_FREE", "P_PLUS", 
			"P_MINUS", "P_ASSIGN", "P_LOR", "P_BOR", "P_LAND", "P_BAND", "P_LNOT", 
			"P_BNOT", "P_EQ", "P_NEQ", "P_GT", "P_LT", "P_GE", "P_LE", "P_LPAR", 
			"P_RPAR", "P_LBRACE", "P_RBRACE", "P_SEMICOLON", "P_REASSIGN", "P_INFIX", 
			"P_PREFIX", "ID_WS", "ID_POS_DIGIT", "ID_DIGIT", "ID_LETTER", "ID_INTEGER"
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
			do {
				{
				setState(34);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case T_UINT8:
				case T_BOOL:
				case KW_IF:
				case KW_FOR:
				case KW_WHILE:
				case ID_LETTER:
					{
					setState(32);
					statement();
					}
					break;
				case KW_FUNC:
					{
					setState(33);
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
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1099511631054L) != 0) );
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
		public IdNameContext idName() {
			return getRuleContext(IdNameContext.class,0);
		}
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
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				idName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				exprVarDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				funcCall();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				blockCond();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				blockFor();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(43);
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
	public static class IdNameContext extends ParserRuleContext {
		public List<TerminalNode> ID_LETTER() { return getTokens(MCMicroprocessor8BitParser.ID_LETTER); }
		public TerminalNode ID_LETTER(int i) {
			return getToken(MCMicroprocessor8BitParser.ID_LETTER, i);
		}
		public List<TerminalNode> ID_DIGIT() { return getTokens(MCMicroprocessor8BitParser.ID_DIGIT); }
		public TerminalNode ID_DIGIT(int i) {
			return getToken(MCMicroprocessor8BitParser.ID_DIGIT, i);
		}
		public IdNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterIdName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitIdName(this);
		}
	}

	public final IdNameContext idName() throws RecognitionException {
		IdNameContext _localctx = new IdNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_idName);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==ID_LETTER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(50);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(47);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1649267441666L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(52);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
	public static class ExprVarDefUint8Context extends ParserRuleContext {
		public TerminalNode T_UINT8() { return getToken(MCMicroprocessor8BitParser.T_UINT8, 0); }
		public IdNameContext idName() {
			return getRuleContext(IdNameContext.class,0);
		}
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
		enterRule(_localctx, 6, RULE_exprVarDefUint8);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T_UINT8);
			setState(54);
			idName();
			setState(55);
			match(P_ASSIGN);
			setState(56);
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
		public IdNameContext idName() {
			return getRuleContext(IdNameContext.class,0);
		}
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
		enterRule(_localctx, 8, RULE_exprVarDefBool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T_BOOL);
			setState(59);
			idName();
			setState(60);
			match(P_ASSIGN);
			setState(61);
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
		enterRule(_localctx, 10, RULE_exprVarDef);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_UINT8:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				exprVarDefUint8();
				}
				break;
			case T_BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
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
		public IdNameContext idName() {
			return getRuleContext(IdNameContext.class,0);
		}
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
		enterRule(_localctx, 12, RULE_exprAssign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			idName();
			setState(68);
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
		enterRule(_localctx, 14, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(P_LBRACE);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099511630990L) != 0)) {
				{
				{
				setState(71);
				statement();
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
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
		public IdNameContext idName() {
			return getRuleContext(IdNameContext.class,0);
		}
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
		enterRule(_localctx, 16, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(KW_FUNC);
			setState(80);
			idName();
			setState(81);
			match(P_LPAR);
			setState(82);
			match(P_RPAR);
			setState(83);
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
		public List<IdNameContext> idName() {
			return getRuleContexts(IdNameContext.class);
		}
		public IdNameContext idName(int i) {
			return getRuleContext(IdNameContext.class,i);
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
		enterRule(_localctx, 18, RULE_funcCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			idName();
			setState(86);
			match(P_LPAR);
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case ID_LETTER:
				{
				setState(87);
				idName();
				}
				break;
			case ID_INTEGER:
				{
				setState(88);
				match(ID_INTEGER);
				}
				break;
			case S_TRUE:
				{
				setState(89);
				match(S_TRUE);
				}
				break;
			case S_FALSE:
				{
				setState(90);
				match(S_FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(93);
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
		enterRule(_localctx, 20, RULE_blockIf);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(KW_IF);
			setState(96);
			match(P_LPAR);
			setState(97);
			match(P_RPAR);
			setState(98);
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
		enterRule(_localctx, 22, RULE_blockElif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(KW_ELIF);
			setState(101);
			match(P_LPAR);
			setState(102);
			match(P_RPAR);
			setState(103);
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
		enterRule(_localctx, 24, RULE_blockElse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(KW_ELSE);
			setState(106);
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
		enterRule(_localctx, 26, RULE_blockCond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			blockIf();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ELIF) {
				{
				{
				setState(109);
				blockElif();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==KW_ELSE) {
				{
				setState(115);
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
		enterRule(_localctx, 28, RULE_blockFor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(KW_FOR);
			setState(119);
			match(P_LPAR);
			setState(120);
			match(P_SEMICOLON);
			setState(121);
			match(P_SEMICOLON);
			setState(122);
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
		enterRule(_localctx, 30, RULE_blockWhile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(KW_WHILE);
			setState(125);
			match(P_LPAR);
			setState(126);
			match(P_RPAR);
			setState(127);
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
		"\u0004\u0001)\u0082\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0001\u0000\u0001\u0000\u0004\u0000#\b\u0000\u000b\u0000\f\u0000$\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001-\b\u0001\u0001\u0002\u0001\u0002\u0005\u00021\b\u0002\n\u0002\f"+
		"\u00024\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0003\u0005B\b\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0005\u0007I\b\u0007\n\u0007\f\u0007L\t"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\\\b\t"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0005\ro\b\r\n\r\f\rr\t\r\u0001\r\u0003\ru\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0000\u0000"+
		"\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e\u0000\u0003\u0002\u0000\u0001\u0001((\u0002\u0000\u0001"+
		"\u0001\'(\u0001\u0000\u0004\u0005\u0080\u0000\"\u0001\u0000\u0000\u0000"+
		"\u0002,\u0001\u0000\u0000\u0000\u0004.\u0001\u0000\u0000\u0000\u00065"+
		"\u0001\u0000\u0000\u0000\b:\u0001\u0000\u0000\u0000\nA\u0001\u0000\u0000"+
		"\u0000\fC\u0001\u0000\u0000\u0000\u000eF\u0001\u0000\u0000\u0000\u0010"+
		"O\u0001\u0000\u0000\u0000\u0012U\u0001\u0000\u0000\u0000\u0014_\u0001"+
		"\u0000\u0000\u0000\u0016d\u0001\u0000\u0000\u0000\u0018i\u0001\u0000\u0000"+
		"\u0000\u001al\u0001\u0000\u0000\u0000\u001cv\u0001\u0000\u0000\u0000\u001e"+
		"|\u0001\u0000\u0000\u0000 #\u0003\u0002\u0001\u0000!#\u0003\u0010\b\u0000"+
		"\" \u0001\u0000\u0000\u0000\"!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%\u0001\u0001"+
		"\u0000\u0000\u0000&-\u0003\u0004\u0002\u0000\'-\u0003\n\u0005\u0000(-"+
		"\u0003\u0012\t\u0000)-\u0003\u001a\r\u0000*-\u0003\u001c\u000e\u0000+"+
		"-\u0003\u001e\u000f\u0000,&\u0001\u0000\u0000\u0000,\'\u0001\u0000\u0000"+
		"\u0000,(\u0001\u0000\u0000\u0000,)\u0001\u0000\u0000\u0000,*\u0001\u0000"+
		"\u0000\u0000,+\u0001\u0000\u0000\u0000-\u0003\u0001\u0000\u0000\u0000"+
		".2\u0007\u0000\u0000\u0000/1\u0007\u0001\u0000\u00000/\u0001\u0000\u0000"+
		"\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000"+
		"\u0000\u00003\u0005\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u0000"+
		"56\u0005\u0002\u0000\u000067\u0003\u0004\u0002\u000078\u0005\u0010\u0000"+
		"\u000089\u0005)\u0000\u00009\u0007\u0001\u0000\u0000\u0000:;\u0005\u0003"+
		"\u0000\u0000;<\u0003\u0004\u0002\u0000<=\u0005\u0010\u0000\u0000=>\u0007"+
		"\u0002\u0000\u0000>\t\u0001\u0000\u0000\u0000?B\u0003\u0006\u0003\u0000"+
		"@B\u0003\b\u0004\u0000A?\u0001\u0000\u0000\u0000A@\u0001\u0000\u0000\u0000"+
		"B\u000b\u0001\u0000\u0000\u0000CD\u0003\u0004\u0002\u0000DE\u0005\u0010"+
		"\u0000\u0000E\r\u0001\u0000\u0000\u0000FJ\u0005\u001f\u0000\u0000GI\u0003"+
		"\u0002\u0001\u0000HG\u0001\u0000\u0000\u0000IL\u0001\u0000\u0000\u0000"+
		"JH\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KM\u0001\u0000\u0000"+
		"\u0000LJ\u0001\u0000\u0000\u0000MN\u0005 \u0000\u0000N\u000f\u0001\u0000"+
		"\u0000\u0000OP\u0005\u0006\u0000\u0000PQ\u0003\u0004\u0002\u0000QR\u0005"+
		"\u001d\u0000\u0000RS\u0005\u001e\u0000\u0000ST\u0003\u000e\u0007\u0000"+
		"T\u0011\u0001\u0000\u0000\u0000UV\u0003\u0004\u0002\u0000V[\u0005\u001d"+
		"\u0000\u0000W\\\u0003\u0004\u0002\u0000X\\\u0005)\u0000\u0000Y\\\u0005"+
		"\u0004\u0000\u0000Z\\\u0005\u0005\u0000\u0000[W\u0001\u0000\u0000\u0000"+
		"[X\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[Z\u0001\u0000\u0000"+
		"\u0000\\]\u0001\u0000\u0000\u0000]^\u0005\u001e\u0000\u0000^\u0013\u0001"+
		"\u0000\u0000\u0000_`\u0005\u0007\u0000\u0000`a\u0005\u001d\u0000\u0000"+
		"ab\u0005\u001e\u0000\u0000bc\u0003\u000e\u0007\u0000c\u0015\u0001\u0000"+
		"\u0000\u0000de\u0005\b\u0000\u0000ef\u0005\u001d\u0000\u0000fg\u0005\u001e"+
		"\u0000\u0000gh\u0003\u000e\u0007\u0000h\u0017\u0001\u0000\u0000\u0000"+
		"ij\u0005\t\u0000\u0000jk\u0003\u000e\u0007\u0000k\u0019\u0001\u0000\u0000"+
		"\u0000lp\u0003\u0014\n\u0000mo\u0003\u0016\u000b\u0000nm\u0001\u0000\u0000"+
		"\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000"+
		"\u0000\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000su\u0003"+
		"\u0018\f\u0000ts\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u\u001b"+
		"\u0001\u0000\u0000\u0000vw\u0005\n\u0000\u0000wx\u0005\u001d\u0000\u0000"+
		"xy\u0005!\u0000\u0000yz\u0005!\u0000\u0000z{\u0003\u000e\u0007\u0000{"+
		"\u001d\u0001\u0000\u0000\u0000|}\u0005\u000b\u0000\u0000}~\u0005\u001d"+
		"\u0000\u0000~\u007f\u0005\u001e\u0000\u0000\u007f\u0080\u0003\u000e\u0007"+
		"\u0000\u0080\u001f\u0001\u0000\u0000\u0000\t\"$,2AJ[pt";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}