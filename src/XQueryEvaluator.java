import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XQueryEvaluator {
    public static void main(String[] args) {
        try {
            // Generate tree and visitor
            CharStream ANTLRInput = CharStreams.fromFileName(args[0]);
            XQueryGrammarLexer lexer = new XQueryGrammarLexer(ANTLRInput);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            XQueryGrammarParser parser = new XQueryGrammarParser(tokens);
            parser.removeErrorListeners();
            ParseTree tree = parser.xq();

            // Visit the tree
            XQueryImplementedVisitor visitor = new XQueryImplementedVisitor();
            List<Node> res = visitor.visit(tree);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            //Node tmp = doc.createElement("result");
            for (Node node : res) {
                Node deepcopy = doc.importNode(node, true);
                doc.appendChild(deepcopy);
            }
            //doc.appendChild(tmp);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("./output.xml"));

            //transformer.transform(source, console);
            transformer.transform(source, file);

        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

    }
}
