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
		RULE_program = 0, RULE_statement = 1, RULE_id_name = 2, RULE_expr_var_def_uint8 = 3, 
		RULE_expr_var_def_bool = 4, RULE_expr_var_def = 5, RULE_expr_assign = 6, 
		RULE_body = 7, RULE_func_def = 8, RULE_func_call = 9, RULE_block_if = 10, 
		RULE_block_elif = 11, RULE_block_else = 12, RULE_block_cond = 13, RULE_block_for = 14, 
		RULE_block_while = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "id_name", "expr_var_def_uint8", "expr_var_def_bool", 
			"expr_var_def", "expr_assign", "body", "func_def", "func_call", "block_if", 
			"block_elif", "block_else", "block_cond", "block_for", "block_while"
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
		public List<Func_defContext> func_def() {
			return getRuleContexts(Func_defContext.class);
		}
		public Func_defContext func_def(int i) {
			return getRuleContext(Func_defContext.class,i);
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
					func_def();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 1099511631052L) != 0) );
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
		public Id_nameContext id_name() {
			return getRuleContext(Id_nameContext.class,0);
		}
		public Expr_var_defContext expr_var_def() {
			return getRuleContext(Expr_var_defContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Block_condContext block_cond() {
			return getRuleContext(Block_condContext.class,0);
		}
		public Block_forContext block_for() {
			return getRuleContext(Block_forContext.class,0);
		}
		public Block_whileContext block_while() {
			return getRuleContext(Block_whileContext.class,0);
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
				id_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				expr_var_def();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				func_call();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				block_cond();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				block_for();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(43);
				block_while();
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
	public static class Id_nameContext extends ParserRuleContext {
		public List<TerminalNode> ID_LETTER() { return getTokens(MCMicroprocessor8BitParser.ID_LETTER); }
		public TerminalNode ID_LETTER(int i) {
			return getToken(MCMicroprocessor8BitParser.ID_LETTER, i);
		}
		public List<TerminalNode> ID_DIGIT() { return getTokens(MCMicroprocessor8BitParser.ID_DIGIT); }
		public TerminalNode ID_DIGIT(int i) {
			return getToken(MCMicroprocessor8BitParser.ID_DIGIT, i);
		}
		public Id_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterId_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitId_name(this);
		}
	}

	public final Id_nameContext id_name() throws RecognitionException {
		Id_nameContext _localctx = new Id_nameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id_name);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(ID_LETTER);
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
	public static class Expr_var_def_uint8Context extends ParserRuleContext {
		public TerminalNode T_UINT8() { return getToken(MCMicroprocessor8BitParser.T_UINT8, 0); }
		public Id_nameContext id_name() {
			return getRuleContext(Id_nameContext.class,0);
		}
		public TerminalNode P_ASSIGN() { return getToken(MCMicroprocessor8BitParser.P_ASSIGN, 0); }
		public TerminalNode ID_INTEGER() { return getToken(MCMicroprocessor8BitParser.ID_INTEGER, 0); }
		public Expr_var_def_uint8Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_var_def_uint8; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExpr_var_def_uint8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExpr_var_def_uint8(this);
		}
	}

	public final Expr_var_def_uint8Context expr_var_def_uint8() throws RecognitionException {
		Expr_var_def_uint8Context _localctx = new Expr_var_def_uint8Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_expr_var_def_uint8);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T_UINT8);
			setState(54);
			id_name();
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
	public static class Expr_var_def_boolContext extends ParserRuleContext {
		public TerminalNode T_BOOL() { return getToken(MCMicroprocessor8BitParser.T_BOOL, 0); }
		public Id_nameContext id_name() {
			return getRuleContext(Id_nameContext.class,0);
		}
		public TerminalNode P_ASSIGN() { return getToken(MCMicroprocessor8BitParser.P_ASSIGN, 0); }
		public TerminalNode S_TRUE() { return getToken(MCMicroprocessor8BitParser.S_TRUE, 0); }
		public TerminalNode S_FALSE() { return getToken(MCMicroprocessor8BitParser.S_FALSE, 0); }
		public Expr_var_def_boolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_var_def_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExpr_var_def_bool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExpr_var_def_bool(this);
		}
	}

	public final Expr_var_def_boolContext expr_var_def_bool() throws RecognitionException {
		Expr_var_def_boolContext _localctx = new Expr_var_def_boolContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expr_var_def_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T_BOOL);
			setState(59);
			id_name();
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
	public static class Expr_var_defContext extends ParserRuleContext {
		public Expr_var_def_uint8Context expr_var_def_uint8() {
			return getRuleContext(Expr_var_def_uint8Context.class,0);
		}
		public Expr_var_def_boolContext expr_var_def_bool() {
			return getRuleContext(Expr_var_def_boolContext.class,0);
		}
		public Expr_var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_var_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExpr_var_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExpr_var_def(this);
		}
	}

	public final Expr_var_defContext expr_var_def() throws RecognitionException {
		Expr_var_defContext _localctx = new Expr_var_defContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expr_var_def);
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_UINT8:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				expr_var_def_uint8();
				}
				break;
			case T_BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				expr_var_def_bool();
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
	public static class Expr_assignContext extends ParserRuleContext {
		public Id_nameContext id_name() {
			return getRuleContext(Id_nameContext.class,0);
		}
		public Expr_assignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExpr_assign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExpr_assign(this);
		}
	}

	public final Expr_assignContext expr_assign() throws RecognitionException {
		Expr_assignContext _localctx = new Expr_assignContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expr_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			id_name();
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
			setState(69);
			match(P_LBRACE);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1099511630988L) != 0)) {
				{
				{
				setState(70);
				statement();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
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
	public static class Func_defContext extends ParserRuleContext {
		public TerminalNode KW_FUNC() { return getToken(MCMicroprocessor8BitParser.KW_FUNC, 0); }
		public Id_nameContext id_name() {
			return getRuleContext(Id_nameContext.class,0);
		}
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Func_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterFunc_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitFunc_def(this);
		}
	}

	public final Func_defContext func_def() throws RecognitionException {
		Func_defContext _localctx = new Func_defContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_func_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(KW_FUNC);
			setState(79);
			id_name();
			setState(80);
			match(P_LPAR);
			setState(81);
			match(P_RPAR);
			setState(82);
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
	public static class Func_callContext extends ParserRuleContext {
		public List<Id_nameContext> id_name() {
			return getRuleContexts(Id_nameContext.class);
		}
		public Id_nameContext id_name(int i) {
			return getRuleContext(Id_nameContext.class,i);
		}
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public TerminalNode ID_INTEGER() { return getToken(MCMicroprocessor8BitParser.ID_INTEGER, 0); }
		public TerminalNode S_TRUE() { return getToken(MCMicroprocessor8BitParser.S_TRUE, 0); }
		public TerminalNode S_FALSE() { return getToken(MCMicroprocessor8BitParser.S_FALSE, 0); }
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitFunc_call(this);
		}
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_func_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			id_name();
			setState(85);
			match(P_LPAR);
			setState(90);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID_LETTER:
				{
				setState(86);
				id_name();
				}
				break;
			case ID_INTEGER:
				{
				setState(87);
				match(ID_INTEGER);
				}
				break;
			case S_TRUE:
				{
				setState(88);
				match(S_TRUE);
				}
				break;
			case S_FALSE:
				{
				setState(89);
				match(S_FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(92);
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
	public static class Block_ifContext extends ParserRuleContext {
		public TerminalNode KW_IF() { return getToken(MCMicroprocessor8BitParser.KW_IF, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Block_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlock_if(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlock_if(this);
		}
	}

	public final Block_ifContext block_if() throws RecognitionException {
		Block_ifContext _localctx = new Block_ifContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_block_if);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			match(KW_IF);
			setState(95);
			match(P_LPAR);
			setState(96);
			match(P_RPAR);
			setState(97);
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
	public static class Block_elifContext extends ParserRuleContext {
		public TerminalNode KW_ELIF() { return getToken(MCMicroprocessor8BitParser.KW_ELIF, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Block_elifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_elif; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlock_elif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlock_elif(this);
		}
	}

	public final Block_elifContext block_elif() throws RecognitionException {
		Block_elifContext _localctx = new Block_elifContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_block_elif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(KW_ELIF);
			setState(100);
			match(P_LPAR);
			setState(101);
			match(P_RPAR);
			setState(102);
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
	public static class Block_elseContext extends ParserRuleContext {
		public TerminalNode KW_ELSE() { return getToken(MCMicroprocessor8BitParser.KW_ELSE, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Block_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlock_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlock_else(this);
		}
	}

	public final Block_elseContext block_else() throws RecognitionException {
		Block_elseContext _localctx = new Block_elseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_block_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(KW_ELSE);
			setState(105);
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
	public static class Block_condContext extends ParserRuleContext {
		public Block_ifContext block_if() {
			return getRuleContext(Block_ifContext.class,0);
		}
		public List<Block_elifContext> block_elif() {
			return getRuleContexts(Block_elifContext.class);
		}
		public Block_elifContext block_elif(int i) {
			return getRuleContext(Block_elifContext.class,i);
		}
		public List<Block_elseContext> block_else() {
			return getRuleContexts(Block_elseContext.class);
		}
		public Block_elseContext block_else(int i) {
			return getRuleContext(Block_elseContext.class,i);
		}
		public Block_condContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlock_cond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlock_cond(this);
		}
	}

	public final Block_condContext block_cond() throws RecognitionException {
		Block_condContext _localctx = new Block_condContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_block_cond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			block_if();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ELIF) {
				{
				{
				setState(108);
				block_elif();
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==KW_ELSE) {
				{
				{
				setState(114);
				block_else();
				}
				}
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class Block_forContext extends ParserRuleContext {
		public TerminalNode KW_FOR() { return getToken(MCMicroprocessor8BitParser.KW_FOR, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public List<TerminalNode> P_SEMICOLON() { return getTokens(MCMicroprocessor8BitParser.P_SEMICOLON); }
		public TerminalNode P_SEMICOLON(int i) {
			return getToken(MCMicroprocessor8BitParser.P_SEMICOLON, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Block_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_for; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlock_for(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlock_for(this);
		}
	}

	public final Block_forContext block_for() throws RecognitionException {
		Block_forContext _localctx = new Block_forContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_block_for);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(KW_FOR);
			setState(121);
			match(P_LPAR);
			setState(122);
			match(P_SEMICOLON);
			setState(123);
			match(P_SEMICOLON);
			setState(124);
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
	public static class Block_whileContext extends ParserRuleContext {
		public TerminalNode KW_WHILE() { return getToken(MCMicroprocessor8BitParser.KW_WHILE, 0); }
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Block_whileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block_while; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterBlock_while(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitBlock_while(this);
		}
	}

	public final Block_whileContext block_while() throws RecognitionException {
		Block_whileContext _localctx = new Block_whileContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_block_while);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(KW_WHILE);
			setState(127);
			match(P_LPAR);
			setState(128);
			match(P_RPAR);
			setState(129);
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
		"\u0004\u0001)\u0084\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"\u0007\u0001\u0007\u0005\u0007H\b\u0007\n\u0007\f\u0007K\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t[\b\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0005\rn\b\r\n\r\f\rq\t\r\u0001\r\u0005\rt\b\r\n\r\f\rw\t\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0000\u0000"+
		"\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e\u0000\u0002\u0002\u0000\u0001\u0001\'(\u0001\u0000"+
		"\u0004\u0005\u0082\u0000\"\u0001\u0000\u0000\u0000\u0002,\u0001\u0000"+
		"\u0000\u0000\u0004.\u0001\u0000\u0000\u0000\u00065\u0001\u0000\u0000\u0000"+
		"\b:\u0001\u0000\u0000\u0000\nA\u0001\u0000\u0000\u0000\fC\u0001\u0000"+
		"\u0000\u0000\u000eE\u0001\u0000\u0000\u0000\u0010N\u0001\u0000\u0000\u0000"+
		"\u0012T\u0001\u0000\u0000\u0000\u0014^\u0001\u0000\u0000\u0000\u0016c"+
		"\u0001\u0000\u0000\u0000\u0018h\u0001\u0000\u0000\u0000\u001ak\u0001\u0000"+
		"\u0000\u0000\u001cx\u0001\u0000\u0000\u0000\u001e~\u0001\u0000\u0000\u0000"+
		" #\u0003\u0002\u0001\u0000!#\u0003\u0010\b\u0000\" \u0001\u0000\u0000"+
		"\u0000\"!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$\"\u0001\u0000"+
		"\u0000\u0000$%\u0001\u0000\u0000\u0000%\u0001\u0001\u0000\u0000\u0000"+
		"&-\u0003\u0004\u0002\u0000\'-\u0003\n\u0005\u0000(-\u0003\u0012\t\u0000"+
		")-\u0003\u001a\r\u0000*-\u0003\u001c\u000e\u0000+-\u0003\u001e\u000f\u0000"+
		",&\u0001\u0000\u0000\u0000,\'\u0001\u0000\u0000\u0000,(\u0001\u0000\u0000"+
		"\u0000,)\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000,+\u0001\u0000"+
		"\u0000\u0000-\u0003\u0001\u0000\u0000\u0000.2\u0005(\u0000\u0000/1\u0007"+
		"\u0000\u0000\u00000/\u0001\u0000\u0000\u000014\u0001\u0000\u0000\u0000"+
		"20\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u00003\u0005\u0001\u0000"+
		"\u0000\u000042\u0001\u0000\u0000\u000056\u0005\u0002\u0000\u000067\u0003"+
		"\u0004\u0002\u000078\u0005\u0010\u0000\u000089\u0005)\u0000\u00009\u0007"+
		"\u0001\u0000\u0000\u0000:;\u0005\u0003\u0000\u0000;<\u0003\u0004\u0002"+
		"\u0000<=\u0005\u0010\u0000\u0000=>\u0007\u0001\u0000\u0000>\t\u0001\u0000"+
		"\u0000\u0000?B\u0003\u0006\u0003\u0000@B\u0003\b\u0004\u0000A?\u0001\u0000"+
		"\u0000\u0000A@\u0001\u0000\u0000\u0000B\u000b\u0001\u0000\u0000\u0000"+
		"CD\u0003\u0004\u0002\u0000D\r\u0001\u0000\u0000\u0000EI\u0005\u001f\u0000"+
		"\u0000FH\u0003\u0002\u0001\u0000GF\u0001\u0000\u0000\u0000HK\u0001\u0000"+
		"\u0000\u0000IG\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0001"+
		"\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000LM\u0005 \u0000\u0000M\u000f"+
		"\u0001\u0000\u0000\u0000NO\u0005\u0006\u0000\u0000OP\u0003\u0004\u0002"+
		"\u0000PQ\u0005\u001d\u0000\u0000QR\u0005\u001e\u0000\u0000RS\u0003\u000e"+
		"\u0007\u0000S\u0011\u0001\u0000\u0000\u0000TU\u0003\u0004\u0002\u0000"+
		"UZ\u0005\u001d\u0000\u0000V[\u0003\u0004\u0002\u0000W[\u0005)\u0000\u0000"+
		"X[\u0005\u0004\u0000\u0000Y[\u0005\u0005\u0000\u0000ZV\u0001\u0000\u0000"+
		"\u0000ZW\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000ZY\u0001\u0000"+
		"\u0000\u0000[\\\u0001\u0000\u0000\u0000\\]\u0005\u001e\u0000\u0000]\u0013"+
		"\u0001\u0000\u0000\u0000^_\u0005\u0007\u0000\u0000_`\u0005\u001d\u0000"+
		"\u0000`a\u0005\u001e\u0000\u0000ab\u0003\u000e\u0007\u0000b\u0015\u0001"+
		"\u0000\u0000\u0000cd\u0005\b\u0000\u0000de\u0005\u001d\u0000\u0000ef\u0005"+
		"\u001e\u0000\u0000fg\u0003\u000e\u0007\u0000g\u0017\u0001\u0000\u0000"+
		"\u0000hi\u0005\t\u0000\u0000ij\u0003\u000e\u0007\u0000j\u0019\u0001\u0000"+
		"\u0000\u0000ko\u0003\u0014\n\u0000ln\u0003\u0016\u000b\u0000ml\u0001\u0000"+
		"\u0000\u0000nq\u0001\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001"+
		"\u0000\u0000\u0000pu\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000"+
		"rt\u0003\u0018\f\u0000sr\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000"+
		"us\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\u001b\u0001\u0000"+
		"\u0000\u0000wu\u0001\u0000\u0000\u0000xy\u0005\n\u0000\u0000yz\u0005\u001d"+
		"\u0000\u0000z{\u0005!\u0000\u0000{|\u0005!\u0000\u0000|}\u0003\u000e\u0007"+
		"\u0000}\u001d\u0001\u0000\u0000\u0000~\u007f\u0005\u000b\u0000\u0000\u007f"+
		"\u0080\u0005\u001d\u0000\u0000\u0080\u0081\u0005\u001e\u0000\u0000\u0081"+
		"\u0082\u0003\u000e\u0007\u0000\u0082\u001f\u0001\u0000\u0000\u0000\t\""+
		"$,2AIZou";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}