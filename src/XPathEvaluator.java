import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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
            List<Node> res = visitor.visit(tree);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Node tmp = doc.createElement("result");
            for (Node node : res) {
                Node copy = doc.importNode(node, true);
                tmp.appendChild(copy);
            }
            doc.appendChild(tmp);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("out/create.xml"));

            //transformer.transform(source, console);
            transformer.transform(source, file);

        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }
}
