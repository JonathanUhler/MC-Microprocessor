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
		T_UINT8=1, T_BOOL=2, S_BOOLEAN=3, S_TRUE=4, S_FALSE=5, KW_FUNC=6, KW_IF=7, 
		KW_ELIF=8, KW_ELSE=9, KW_FOR=10, KW_WHILE=11, KW_CONTINUE=12, KW_BREAK=13, 
		BI_EXIT=14, BI_FREE=15, P_REASSIGN=16, P_BBINARY=17, P_BUNARY=18, P_LBINARY=19, 
		P_LUNARY=20, P_PLUS=21, P_MINUS=22, P_EQ=23, P_ASSIGN=24, P_LOR=25, P_BOR=26, 
		P_LAND=27, P_BAND=28, P_NEQ=29, P_LNOT=30, P_BNOT=31, P_LPAR=32, P_RPAR=33, 
		P_LBRACE=34, P_RBRACE=35, P_SEMICOLON=36, ID_NAME=37, ID_INTEGER=38, ID_DIGIT=39, 
		ID_POS_DIGIT=40, ID_LETTER=41, ID_COMMENT=42, ID_WS=43;
	public static final int
		RULE_exprVarDefUint8 = 0, RULE_exprVarDefBool = 1, RULE_exprVarDef = 2, 
		RULE_exprBoolean = 3, RULE_exprInteger = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"exprVarDefUint8", "exprVarDefBool", "exprVarDef", "exprBoolean", "exprInteger"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'uint8'", "'bool'", null, "'true'", "'false'", "'func'", "'if'", 
			"'elif'", "'else'", "'for'", "'while'", "'continue'", "'break'", "'exit'", 
			"'free'", null, null, null, null, null, "'+'", "'-'", "'=='", "'='", 
			"'||'", "'|'", "'&&'", "'&'", "'!='", "'!'", "'~'", "'('", "')'", "'{'", 
			"'}'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "T_UINT8", "T_BOOL", "S_BOOLEAN", "S_TRUE", "S_FALSE", "KW_FUNC", 
			"KW_IF", "KW_ELIF", "KW_ELSE", "KW_FOR", "KW_WHILE", "KW_CONTINUE", "KW_BREAK", 
			"BI_EXIT", "BI_FREE", "P_REASSIGN", "P_BBINARY", "P_BUNARY", "P_LBINARY", 
			"P_LUNARY", "P_PLUS", "P_MINUS", "P_EQ", "P_ASSIGN", "P_LOR", "P_BOR", 
			"P_LAND", "P_BAND", "P_NEQ", "P_LNOT", "P_BNOT", "P_LPAR", "P_RPAR", 
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
	public static class ExprVarDefUint8Context extends ParserRuleContext {
		public TerminalNode T_UINT8() { return getToken(MCMicroprocessor8BitParser.T_UINT8, 0); }
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode P_ASSIGN() { return getToken(MCMicroprocessor8BitParser.P_ASSIGN, 0); }
		public ExprIntegerContext exprInteger() {
			return getRuleContext(ExprIntegerContext.class,0);
		}
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
		enterRule(_localctx, 0, RULE_exprVarDefUint8);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(T_UINT8);
			setState(11);
			match(ID_NAME);
			setState(12);
			match(P_ASSIGN);
			setState(13);
			exprInteger(0);
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
		public ExprBooleanContext exprBoolean() {
			return getRuleContext(ExprBooleanContext.class,0);
		}
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
		enterRule(_localctx, 2, RULE_exprVarDefBool);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			match(T_BOOL);
			setState(16);
			match(ID_NAME);
			setState(17);
			match(P_ASSIGN);
			setState(18);
			exprBoolean(0);
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
		enterRule(_localctx, 4, RULE_exprVarDef);
		try {
			setState(22);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_UINT8:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				exprVarDefUint8();
				}
				break;
			case T_BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
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
	public static class ExprBooleanContext extends ParserRuleContext {
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode S_BOOLEAN() { return getToken(MCMicroprocessor8BitParser.S_BOOLEAN, 0); }
		public List<ExprIntegerContext> exprInteger() {
			return getRuleContexts(ExprIntegerContext.class);
		}
		public ExprIntegerContext exprInteger(int i) {
			return getRuleContext(ExprIntegerContext.class,i);
		}
		public TerminalNode P_LBINARY() { return getToken(MCMicroprocessor8BitParser.P_LBINARY, 0); }
		public TerminalNode P_LUNARY() { return getToken(MCMicroprocessor8BitParser.P_LUNARY, 0); }
		public List<ExprBooleanContext> exprBoolean() {
			return getRuleContexts(ExprBooleanContext.class);
		}
		public ExprBooleanContext exprBoolean(int i) {
			return getRuleContext(ExprBooleanContext.class,i);
		}
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public ExprBooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprBoolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExprBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExprBoolean(this);
		}
	}

	public final ExprBooleanContext exprBoolean() throws RecognitionException {
		return exprBoolean(0);
	}

	private ExprBooleanContext exprBoolean(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprBooleanContext _localctx = new ExprBooleanContext(_ctx, _parentState);
		ExprBooleanContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_exprBoolean, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(25);
				match(ID_NAME);
				}
				break;
			case 2:
				{
				setState(26);
				match(S_BOOLEAN);
				}
				break;
			case 3:
				{
				setState(27);
				exprInteger(0);
				setState(28);
				match(P_LBINARY);
				setState(29);
				exprInteger(0);
				}
				break;
			case 4:
				{
				setState(31);
				match(P_LUNARY);
				setState(32);
				exprBoolean(2);
				}
				break;
			case 5:
				{
				setState(33);
				match(P_LPAR);
				setState(34);
				exprBoolean(0);
				setState(35);
				match(P_RPAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(44);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprBooleanContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exprBoolean);
					setState(39);
					if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
					setState(40);
					match(P_LBINARY);
					setState(41);
					exprBoolean(5);
					}
					} 
				}
				setState(46);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprIntegerContext extends ParserRuleContext {
		public TerminalNode ID_NAME() { return getToken(MCMicroprocessor8BitParser.ID_NAME, 0); }
		public TerminalNode ID_INTEGER() { return getToken(MCMicroprocessor8BitParser.ID_INTEGER, 0); }
		public TerminalNode P_BUNARY() { return getToken(MCMicroprocessor8BitParser.P_BUNARY, 0); }
		public List<ExprIntegerContext> exprInteger() {
			return getRuleContexts(ExprIntegerContext.class);
		}
		public ExprIntegerContext exprInteger(int i) {
			return getRuleContext(ExprIntegerContext.class,i);
		}
		public TerminalNode P_LPAR() { return getToken(MCMicroprocessor8BitParser.P_LPAR, 0); }
		public TerminalNode P_RPAR() { return getToken(MCMicroprocessor8BitParser.P_RPAR, 0); }
		public TerminalNode P_BBINARY() { return getToken(MCMicroprocessor8BitParser.P_BBINARY, 0); }
		public ExprIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).enterExprInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MCMicroprocessor8BitListener ) ((MCMicroprocessor8BitListener)listener).exitExprInteger(this);
		}
	}

	public final ExprIntegerContext exprInteger() throws RecognitionException {
		return exprInteger(0);
	}

	private ExprIntegerContext exprInteger(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprIntegerContext _localctx = new ExprIntegerContext(_ctx, _parentState);
		ExprIntegerContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_exprInteger, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID_NAME:
				{
				setState(48);
				match(ID_NAME);
				}
				break;
			case ID_INTEGER:
				{
				setState(49);
				match(ID_INTEGER);
				}
				break;
			case P_BUNARY:
				{
				setState(50);
				match(P_BUNARY);
				setState(51);
				exprInteger(2);
				}
				break;
			case P_LPAR:
				{
				setState(52);
				match(P_LPAR);
				setState(53);
				exprInteger(0);
				setState(54);
				match(P_RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ExprIntegerContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_exprInteger);
					setState(58);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(59);
					match(P_BBINARY);
					setState(60);
					exprInteger(4);
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return exprBoolean_sempred((ExprBooleanContext)_localctx, predIndex);
		case 4:
			return exprInteger_sempred((ExprIntegerContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean exprBoolean_sempred(ExprBooleanContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean exprInteger_sempred(ExprIntegerContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001+C\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002\u0002"+
		"\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002"+
		"\u0017\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003&\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003+\b\u0003\n\u0003\f\u0003.\t\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u00049\b\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0005\u0004>\b\u0004\n\u0004\f\u0004A\t\u0004\u0001\u0004"+
		"\u0000\u0002\u0006\b\u0005\u0000\u0002\u0004\u0006\b\u0000\u0000G\u0000"+
		"\n\u0001\u0000\u0000\u0000\u0002\u000f\u0001\u0000\u0000\u0000\u0004\u0016"+
		"\u0001\u0000\u0000\u0000\u0006%\u0001\u0000\u0000\u0000\b8\u0001\u0000"+
		"\u0000\u0000\n\u000b\u0005\u0001\u0000\u0000\u000b\f\u0005%\u0000\u0000"+
		"\f\r\u0005\u0018\u0000\u0000\r\u000e\u0003\b\u0004\u0000\u000e\u0001\u0001"+
		"\u0000\u0000\u0000\u000f\u0010\u0005\u0002\u0000\u0000\u0010\u0011\u0005"+
		"%\u0000\u0000\u0011\u0012\u0005\u0018\u0000\u0000\u0012\u0013\u0003\u0006"+
		"\u0003\u0000\u0013\u0003\u0001\u0000\u0000\u0000\u0014\u0017\u0003\u0000"+
		"\u0000\u0000\u0015\u0017\u0003\u0002\u0001\u0000\u0016\u0014\u0001\u0000"+
		"\u0000\u0000\u0016\u0015\u0001\u0000\u0000\u0000\u0017\u0005\u0001\u0000"+
		"\u0000\u0000\u0018\u0019\u0006\u0003\uffff\uffff\u0000\u0019&\u0005%\u0000"+
		"\u0000\u001a&\u0005\u0003\u0000\u0000\u001b\u001c\u0003\b\u0004\u0000"+
		"\u001c\u001d\u0005\u0013\u0000\u0000\u001d\u001e\u0003\b\u0004\u0000\u001e"+
		"&\u0001\u0000\u0000\u0000\u001f \u0005\u0014\u0000\u0000 &\u0003\u0006"+
		"\u0003\u0002!\"\u0005 \u0000\u0000\"#\u0003\u0006\u0003\u0000#$\u0005"+
		"!\u0000\u0000$&\u0001\u0000\u0000\u0000%\u0018\u0001\u0000\u0000\u0000"+
		"%\u001a\u0001\u0000\u0000\u0000%\u001b\u0001\u0000\u0000\u0000%\u001f"+
		"\u0001\u0000\u0000\u0000%!\u0001\u0000\u0000\u0000&,\u0001\u0000\u0000"+
		"\u0000\'(\n\u0004\u0000\u0000()\u0005\u0013\u0000\u0000)+\u0003\u0006"+
		"\u0003\u0005*\'\u0001\u0000\u0000\u0000+.\u0001\u0000\u0000\u0000,*\u0001"+
		"\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-\u0007\u0001\u0000\u0000"+
		"\u0000.,\u0001\u0000\u0000\u0000/0\u0006\u0004\uffff\uffff\u000009\u0005"+
		"%\u0000\u000019\u0005&\u0000\u000023\u0005\u0012\u0000\u000039\u0003\b"+
		"\u0004\u000245\u0005 \u0000\u000056\u0003\b\u0004\u000067\u0005!\u0000"+
		"\u000079\u0001\u0000\u0000\u00008/\u0001\u0000\u0000\u000081\u0001\u0000"+
		"\u0000\u000082\u0001\u0000\u0000\u000084\u0001\u0000\u0000\u00009?\u0001"+
		"\u0000\u0000\u0000:;\n\u0003\u0000\u0000;<\u0005\u0011\u0000\u0000<>\u0003"+
		"\b\u0004\u0004=:\u0001\u0000\u0000\u0000>A\u0001\u0000\u0000\u0000?=\u0001"+
		"\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@\t\u0001\u0000\u0000\u0000"+
		"A?\u0001\u0000\u0000\u0000\u0005\u0016%,8?";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}