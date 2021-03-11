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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, VAR=38, TAGNAME=39, 
		ATTRNAME=40, TEXT=41, FILENAME=42, STRINGCONSTANT=43, WHITESPACE=44;
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
			"T__33", "T__34", "T__35", "T__36", "VAR", "LOWERCASE", "UPPERCASE", 
			"DIGIT", "TAGNAME", "ATTRNAME", "TEXT", "FILENAME", "STRINGCONSTANT", 
			"WHITESPACE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'/'", "'//'", "','", "'<'", "'>'", "'{'", "'}'", 
			"'</'", "'join'", "'['", "']'", "'for'", "'in'", "'let'", "':='", "'where'", 
			"'return'", "'='", "'eq'", "'=='", "'is'", "'empty'", "'some'", "'satisfies'", 
			"'and'", "'or'", "'not'", "'doc'", "'document'", "')/'", "')//'", "'*'", 
			"'.'", "'..'", "'text()'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "VAR", "TAGNAME", "ATTRNAME", "TEXT", "FILENAME", "STRINGCONSTANT", 
			"WHITESPACE"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2.\u0114\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3"+
		"\'\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3,\3-\3-\3-\3-\6-\u00fb\n-\r"+
		"-\16-\u00fc\3.\3.\3.\3.\3.\3.\3/\3/\7/\u0107\n/\f/\16/\u010a\13/\3/\3"+
		"/\3\60\6\60\u010f\n\60\r\60\16\60\u0110\3\60\3\60\2\2\61\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O\2Q\2S\2U)W*Y+[,]-_.\3\2\b\3\2c|\3\2C\\\3\2\62;\4\2//aa\3\2$"+
		"$\5\2\13\f\17\17\"\"\2\u0116\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3"+
		"\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2"+
		"\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C"+
		"\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\3a\3\2\2\2"+
		"\5c\3\2\2\2\7e\3\2\2\2\tg\3\2\2\2\13j\3\2\2\2\rl\3\2\2\2\17n\3\2\2\2\21"+
		"p\3\2\2\2\23r\3\2\2\2\25t\3\2\2\2\27w\3\2\2\2\31|\3\2\2\2\33~\3\2\2\2"+
		"\35\u0080\3\2\2\2\37\u0084\3\2\2\2!\u0087\3\2\2\2#\u008b\3\2\2\2%\u008e"+
		"\3\2\2\2\'\u0094\3\2\2\2)\u009b\3\2\2\2+\u009d\3\2\2\2-\u00a0\3\2\2\2"+
		"/\u00a3\3\2\2\2\61\u00a6\3\2\2\2\63\u00ac\3\2\2\2\65\u00b1\3\2\2\2\67"+
		"\u00bb\3\2\2\29\u00bf\3\2\2\2;\u00c2\3\2\2\2=\u00c6\3\2\2\2?\u00ca\3\2"+
		"\2\2A\u00d3\3\2\2\2C\u00d6\3\2\2\2E\u00da\3\2\2\2G\u00dc\3\2\2\2I\u00de"+
		"\3\2\2\2K\u00e1\3\2\2\2M\u00e8\3\2\2\2O\u00eb\3\2\2\2Q\u00ed\3\2\2\2S"+
		"\u00ef\3\2\2\2U\u00f1\3\2\2\2W\u00f3\3\2\2\2Y\u00fa\3\2\2\2[\u00fe\3\2"+
		"\2\2]\u0104\3\2\2\2_\u010e\3\2\2\2ab\7*\2\2b\4\3\2\2\2cd\7+\2\2d\6\3\2"+
		"\2\2ef\7\61\2\2f\b\3\2\2\2gh\7\61\2\2hi\7\61\2\2i\n\3\2\2\2jk\7.\2\2k"+
		"\f\3\2\2\2lm\7>\2\2m\16\3\2\2\2no\7@\2\2o\20\3\2\2\2pq\7}\2\2q\22\3\2"+
		"\2\2rs\7\177\2\2s\24\3\2\2\2tu\7>\2\2uv\7\61\2\2v\26\3\2\2\2wx\7l\2\2"+
		"xy\7q\2\2yz\7k\2\2z{\7p\2\2{\30\3\2\2\2|}\7]\2\2}\32\3\2\2\2~\177\7_\2"+
		"\2\177\34\3\2\2\2\u0080\u0081\7h\2\2\u0081\u0082\7q\2\2\u0082\u0083\7"+
		"t\2\2\u0083\36\3\2\2\2\u0084\u0085\7k\2\2\u0085\u0086\7p\2\2\u0086 \3"+
		"\2\2\2\u0087\u0088\7n\2\2\u0088\u0089\7g\2\2\u0089\u008a\7v\2\2\u008a"+
		"\"\3\2\2\2\u008b\u008c\7<\2\2\u008c\u008d\7?\2\2\u008d$\3\2\2\2\u008e"+
		"\u008f\7y\2\2\u008f\u0090\7j\2\2\u0090\u0091\7g\2\2\u0091\u0092\7t\2\2"+
		"\u0092\u0093\7g\2\2\u0093&\3\2\2\2\u0094\u0095\7t\2\2\u0095\u0096\7g\2"+
		"\2\u0096\u0097\7v\2\2\u0097\u0098\7w\2\2\u0098\u0099\7t\2\2\u0099\u009a"+
		"\7p\2\2\u009a(\3\2\2\2\u009b\u009c\7?\2\2\u009c*\3\2\2\2\u009d\u009e\7"+
		"g\2\2\u009e\u009f\7s\2\2\u009f,\3\2\2\2\u00a0\u00a1\7?\2\2\u00a1\u00a2"+
		"\7?\2\2\u00a2.\3\2\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7u\2\2\u00a5\60"+
		"\3\2\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7o\2\2\u00a8\u00a9\7r\2\2\u00a9"+
		"\u00aa\7v\2\2\u00aa\u00ab\7{\2\2\u00ab\62\3\2\2\2\u00ac\u00ad\7u\2\2\u00ad"+
		"\u00ae\7q\2\2\u00ae\u00af\7o\2\2\u00af\u00b0\7g\2\2\u00b0\64\3\2\2\2\u00b1"+
		"\u00b2\7u\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7k\2\2"+
		"\u00b5\u00b6\7u\2\2\u00b6\u00b7\7h\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9"+
		"\7g\2\2\u00b9\u00ba\7u\2\2\u00ba\66\3\2\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd"+
		"\7p\2\2\u00bd\u00be\7f\2\2\u00be8\3\2\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1"+
		"\7t\2\2\u00c1:\3\2\2\2\u00c2\u00c3\7p\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5"+
		"\7v\2\2\u00c5<\3\2\2\2\u00c6\u00c7\7f\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9"+
		"\7e\2\2\u00c9>\3\2\2\2\u00ca\u00cb\7f\2\2\u00cb\u00cc\7q\2\2\u00cc\u00cd"+
		"\7e\2\2\u00cd\u00ce\7w\2\2\u00ce\u00cf\7o\2\2\u00cf\u00d0\7g\2\2\u00d0"+
		"\u00d1\7p\2\2\u00d1\u00d2\7v\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7+\2\2\u00d4"+
		"\u00d5\7\61\2\2\u00d5B\3\2\2\2\u00d6\u00d7\7+\2\2\u00d7\u00d8\7\61\2\2"+
		"\u00d8\u00d9\7\61\2\2\u00d9D\3\2\2\2\u00da\u00db\7,\2\2\u00dbF\3\2\2\2"+
		"\u00dc\u00dd\7\60\2\2\u00ddH\3\2\2\2\u00de\u00df\7\60\2\2\u00df\u00e0"+
		"\7\60\2\2\u00e0J\3\2\2\2\u00e1\u00e2\7v\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4"+
		"\7z\2\2\u00e4\u00e5\7v\2\2\u00e5\u00e6\7*\2\2\u00e6\u00e7\7+\2\2\u00e7"+
		"L\3\2\2\2\u00e8\u00e9\7&\2\2\u00e9\u00ea\5Y-\2\u00eaN\3\2\2\2\u00eb\u00ec"+
		"\t\2\2\2\u00ecP\3\2\2\2\u00ed\u00ee\t\3\2\2\u00eeR\3\2\2\2\u00ef\u00f0"+
		"\t\4\2\2\u00f0T\3\2\2\2\u00f1\u00f2\5Y-\2\u00f2V\3\2\2\2\u00f3\u00f4\7"+
		"B\2\2\u00f4\u00f5\5Y-\2\u00f5X\3\2\2\2\u00f6\u00fb\5O(\2\u00f7\u00fb\5"+
		"Q)\2\u00f8\u00fb\5S*\2\u00f9\u00fb\t\5\2\2\u00fa\u00f6\3\2\2\2\u00fa\u00f7"+
		"\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fdZ\3\2\2\2\u00fe\u00ff\7$\2\2\u00ff"+
		"\u0100\5Y-\2\u0100\u0101\7\60\2\2\u0101\u0102\5Y-\2\u0102\u0103\7$\2\2"+
		"\u0103\\\3\2\2\2\u0104\u0108\7$\2\2\u0105\u0107\n\6\2\2\u0106\u0105\3"+
		"\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"\u010b\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\7$\2\2\u010c^\3\2\2\2\u010d"+
		"\u010f\t\7\2\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\b\60\2\2\u0113"+
		"`\3\2\2\2\7\2\u00fa\u00fc\u0108\u0110\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}