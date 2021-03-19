import org.antlr.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;  // Import the IOException class to handle errors

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

            // Rewrite the query
            XQueryJoinCreater visitor = new XQueryJoinCreater();
            String rewriteOutput = visitor.visit(tree);
            writeToFile(rewriteOutput, "newQuery.txt");

            // Generate rewrite tree and visitor
            CharStream rewriteStream = CharStreams.fromString(rewriteOutput);
            XQueryGrammarLexer rewriteLexer = new XQueryGrammarLexer(rewriteStream);
            CommonTokenStream rewriteTokens = new CommonTokenStream(rewriteLexer);
            XQueryGrammarParser rewriteParser = new XQueryGrammarParser(rewriteTokens);
            rewriteParser.removeErrorListeners();
            ParseTree rewriteTree = rewriteParser.xq();

            // Visit the tree
            XQueryImplementedVisitor rewriteVisitor = new XQueryImplementedVisitor();
            List<Node> res = rewriteVisitor.visit(rewriteTree);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Node tmp = doc.createElement("result");
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

    private static void writeToFile(String s, String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(s);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
