// Generated from /Users/yunyi/Documents/CSE232b/ucsd_232b_XQueryProcessor/src/XQueryGrammar.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XQueryGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, VAR=36, TAGNAME=37, ATTRNAME=38, 
		TEXT=39, FILENAME=40, STRINGCONSTANT=41, WHITESPACE=42;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "VAR", "LOWERCASE", "UPPERCASE", "DIGIT", "TAGNAME", 
			"ATTRNAME", "TEXT", "FILENAME", "STRINGCONSTANT", "WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'/'", "'//'", "'('", "')'", "','", "'<'", "'>'", "'{'", "'}'", 
			"'</'", "'join'", "'['", "']'", "'for'", "'in'", "'let'", "':='", "'where'", 
			"'return'", "'='", "'eq'", "'=='", "'is'", "'empty'", "'some'", "'satisfies'", 
			"'and'", "'or'", "'not'", "'doc'", "'document'", "'*'", "'.'", "'..'", 
			"'text()'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"VAR", "TAGNAME", "ATTRNAME", "TEXT", "FILENAME", "STRINGCONSTANT", "WHITESPACE"
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


	public XQueryGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XQueryGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2,\u0109\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3#"+
		"\3#\3#\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3"+
		"*\3+\3+\3+\3+\6+\u00f0\n+\r+\16+\u00f1\3,\3,\3,\3,\3,\3,\3-\3-\7-\u00fc"+
		"\n-\f-\16-\u00ff\13-\3-\3-\3.\6.\u0104\n.\r.\16.\u0105\3.\3.\2\2/\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\2M\2O\2Q\'S(U)W*Y+[,\3\2\b\3\2c|\3\2C\\\3\2\62;\4\2//aa\3"+
		"\2$$\5\2\13\f\17\17\"\"\2\u010b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2"+
		"\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3]\3\2\2\2\5_\3\2\2\2\7b\3\2"+
		"\2\2\td\3\2\2\2\13f\3\2\2\2\rh\3\2\2\2\17j\3\2\2\2\21l\3\2\2\2\23n\3\2"+
		"\2\2\25p\3\2\2\2\27s\3\2\2\2\31x\3\2\2\2\33z\3\2\2\2\35|\3\2\2\2\37\u0080"+
		"\3\2\2\2!\u0083\3\2\2\2#\u0087\3\2\2\2%\u008a\3\2\2\2\'\u0090\3\2\2\2"+
		")\u0097\3\2\2\2+\u0099\3\2\2\2-\u009c\3\2\2\2/\u009f\3\2\2\2\61\u00a2"+
		"\3\2\2\2\63\u00a8\3\2\2\2\65\u00ad\3\2\2\2\67\u00b7\3\2\2\29\u00bb\3\2"+
		"\2\2;\u00be\3\2\2\2=\u00c2\3\2\2\2?\u00c6\3\2\2\2A\u00cf\3\2\2\2C\u00d1"+
		"\3\2\2\2E\u00d3\3\2\2\2G\u00d6\3\2\2\2I\u00dd\3\2\2\2K\u00e0\3\2\2\2M"+
		"\u00e2\3\2\2\2O\u00e4\3\2\2\2Q\u00e6\3\2\2\2S\u00e8\3\2\2\2U\u00ef\3\2"+
		"\2\2W\u00f3\3\2\2\2Y\u00f9\3\2\2\2[\u0103\3\2\2\2]^\7\61\2\2^\4\3\2\2"+
		"\2_`\7\61\2\2`a\7\61\2\2a\6\3\2\2\2bc\7*\2\2c\b\3\2\2\2de\7+\2\2e\n\3"+
		"\2\2\2fg\7.\2\2g\f\3\2\2\2hi\7>\2\2i\16\3\2\2\2jk\7@\2\2k\20\3\2\2\2l"+
		"m\7}\2\2m\22\3\2\2\2no\7\177\2\2o\24\3\2\2\2pq\7>\2\2qr\7\61\2\2r\26\3"+
		"\2\2\2st\7l\2\2tu\7q\2\2uv\7k\2\2vw\7p\2\2w\30\3\2\2\2xy\7]\2\2y\32\3"+
		"\2\2\2z{\7_\2\2{\34\3\2\2\2|}\7h\2\2}~\7q\2\2~\177\7t\2\2\177\36\3\2\2"+
		"\2\u0080\u0081\7k\2\2\u0081\u0082\7p\2\2\u0082 \3\2\2\2\u0083\u0084\7"+
		"n\2\2\u0084\u0085\7g\2\2\u0085\u0086\7v\2\2\u0086\"\3\2\2\2\u0087\u0088"+
		"\7<\2\2\u0088\u0089\7?\2\2\u0089$\3\2\2\2\u008a\u008b\7y\2\2\u008b\u008c"+
		"\7j\2\2\u008c\u008d\7g\2\2\u008d\u008e\7t\2\2\u008e\u008f\7g\2\2\u008f"+
		"&\3\2\2\2\u0090\u0091\7t\2\2\u0091\u0092\7g\2\2\u0092\u0093\7v\2\2\u0093"+
		"\u0094\7w\2\2\u0094\u0095\7t\2\2\u0095\u0096\7p\2\2\u0096(\3\2\2\2\u0097"+
		"\u0098\7?\2\2\u0098*\3\2\2\2\u0099\u009a\7g\2\2\u009a\u009b\7s\2\2\u009b"+
		",\3\2\2\2\u009c\u009d\7?\2\2\u009d\u009e\7?\2\2\u009e.\3\2\2\2\u009f\u00a0"+
		"\7k\2\2\u00a0\u00a1\7u\2\2\u00a1\60\3\2\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4"+
		"\7o\2\2\u00a4\u00a5\7r\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7{\2\2\u00a7"+
		"\62\3\2\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7o\2\2\u00ab"+
		"\u00ac\7g\2\2\u00ac\64\3\2\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7c\2\2\u00af"+
		"\u00b0\7v\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7h\2\2"+
		"\u00b3\u00b4\7k\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7u\2\2\u00b6\66\3\2"+
		"\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7p\2\2\u00b9\u00ba\7f\2\2\u00ba8\3"+
		"\2\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd\7t\2\2\u00bd:\3\2\2\2\u00be\u00bf"+
		"\7p\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7v\2\2\u00c1<\3\2\2\2\u00c2\u00c3"+
		"\7f\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7e\2\2\u00c5>\3\2\2\2\u00c6\u00c7"+
		"\7f\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9\7e\2\2\u00c9\u00ca\7w\2\2\u00ca"+
		"\u00cb\7o\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7p\2\2\u00cd\u00ce\7v\2\2"+
		"\u00ce@\3\2\2\2\u00cf\u00d0\7,\2\2\u00d0B\3\2\2\2\u00d1\u00d2\7\60\2\2"+
		"\u00d2D\3\2\2\2\u00d3\u00d4\7\60\2\2\u00d4\u00d5\7\60\2\2\u00d5F\3\2\2"+
		"\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7z\2\2\u00d9\u00da"+
		"\7v\2\2\u00da\u00db\7*\2\2\u00db\u00dc\7+\2\2\u00dcH\3\2\2\2\u00dd\u00de"+
		"\7&\2\2\u00de\u00df\5U+\2\u00dfJ\3\2\2\2\u00e0\u00e1\t\2\2\2\u00e1L\3"+
		"\2\2\2\u00e2\u00e3\t\3\2\2\u00e3N\3\2\2\2\u00e4\u00e5\t\4\2\2\u00e5P\3"+
		"\2\2\2\u00e6\u00e7\5U+\2\u00e7R\3\2\2\2\u00e8\u00e9\7B\2\2\u00e9\u00ea"+
		"\5U+\2\u00eaT\3\2\2\2\u00eb\u00f0\5K&\2\u00ec\u00f0\5M\'\2\u00ed\u00f0"+
		"\5O(\2\u00ee\u00f0\t\5\2\2\u00ef\u00eb\3\2\2\2\u00ef\u00ec\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f1\u00f2\3\2\2\2\u00f2V\3\2\2\2\u00f3\u00f4\7$\2\2\u00f4\u00f5"+
		"\5U+\2\u00f5\u00f6\7\60\2\2\u00f6\u00f7\5U+\2\u00f7\u00f8\7$\2\2\u00f8"+
		"X\3\2\2\2\u00f9\u00fd\7$\2\2\u00fa\u00fc\n\6\2\2\u00fb\u00fa\3\2\2\2\u00fc"+
		"\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0100\3\2"+
		"\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\7$\2\2\u0101Z\3\2\2\2\u0102\u0104"+
		"\t\7\2\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0108\b.\2\2\u0108\\\3\2\2\2"+
		"\7\2\u00ef\u00f1\u00fd\u0105\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}