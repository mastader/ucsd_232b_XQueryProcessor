import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

public class XPathEvaluator {
    public static void main(String[] args) {
        try {
            // Generate tree and visitor
            CharStream ANTLRInput = CharStreams.fromFileName("test/XPathTest.txt");
            XPathGrammarLexer lexer = new XPathGrammarLexer(ANTLRInput);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XPathGrammarParser parser = new XPathGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.ap();

            // Visit the tree
            XPathImplementedVisitor visitor = new XPathImplementedVisitor();
            visitor.visit(tree);
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
