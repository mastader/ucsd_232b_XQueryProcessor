import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.sql.SQLOutput;
import java.util.*;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XPathImplementedVisitor extends XPathGrammarBaseVisitor<List<Node>>{

    private List<Node> currentNodes = new ArrayList<>();

    @Override public List<Node> visitSingleSlashAP(XPathGrammarParser.SingleSlashAPContext ctx) {
        String filename = ctx.FILENAME().getText();
        currentNodes = readXML(filename);

        return visit(ctx.rp());
    }

    @Override public List<Node> visitDoubleSlashAP(XPathGrammarParser.DoubleSlashAPContext ctx) {
        String filename = ctx.FILENAME().getText();
        currentNodes = readXML(filename);
        currentNodes.addAll(getListDescendants(currentNodes));

        return visit(ctx.rp());
    }

    @Override public List<Node> visitTagRP(XPathGrammarParser.TagRPContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> childrenNodes = getAllChildren(currentNodes);
        for (Node node : childrenNodes) {
            //System.out.println("TagRP: " + node.getNodeType());
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(ctx.TAGNAME().getText())) {
                res.add(node);
            }
        }

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitAllChildrenRP(XPathGrammarParser.AllChildrenRPContext ctx) {
        List<Node> res = getAllChildren(currentNodes);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitSelfRP(XPathGrammarParser.SelfRPContext ctx) {

        return currentNodes;
    }

    @Override public List<Node> visitParentRP(XPathGrammarParser.ParentRPContext ctx) {
        List<Node> res = getAllParents(currentNodes);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitTextRP(XPathGrammarParser.TextRPContext ctx) {
        List<Node> res = new ArrayList<>();
        for (Node node : currentNodes) {
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList childrenNodes = node.getChildNodes();
                for (int i = 0; i < childrenNodes.getLength(); i++) {
                    if (childrenNodes.item(i).getNodeType() == Node.TEXT_NODE) {
                        res.add(childrenNodes.item(i));
                    }
                }
            }
        }

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitAttributeRP(XPathGrammarParser.AttributeRPContext ctx) {
        List<Node> res = new ArrayList<>();
        String attr = ctx.ATTRNAME().getText().substring(1);
        //System.out.println("Attribute Name " + attr);

        for (Node node : currentNodes) {
            NamedNodeMap nodemap = node.getAttributes();
            if (nodemap != null && nodemap.getNamedItem(attr) != null) {
                res.add(nodemap.getNamedItem(attr));
            }
        }

        currentNodes = res;
        return visitChildren(ctx);
    }

    @Override public List<Node> visitBracketRP(XPathGrammarParser.BracketRPContext ctx) {

        return visit(ctx.rp());
    }

    @Override public List<Node> visitSingleSlashRP(XPathGrammarParser.SingleSlashRPContext ctx) {
        visit(ctx.rp(0));
        visit(ctx.rp(1));

        List<Node> res = unique(new ArrayList<>(), currentNodes);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitDoubleSlashRP(XPathGrammarParser.DoubleSlashRPContext ctx) {
        visit(ctx.rp(0));
        currentNodes = getListDescendants(currentNodes);
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> res = unique(tmp, visit(ctx.rp(1)));

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitFilteredRP(XPathGrammarParser.FilteredRPContext ctx) {
        List<Node> res = new ArrayList<>();

        visit(ctx.rp());
        List<Node> tmp = new ArrayList<>(currentNodes);

        for (Node node : tmp) {
            currentNodes.clear();
            currentNodes.add(node);
            if (visit(ctx.f()).size() != 0) {
                res.add(node);
            }
        }

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitSequenceRP(XPathGrammarParser.SequenceRPContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        res.addAll(visit(ctx.rp(0)));
        currentNodes = tmp;
        res.addAll(visit(ctx.rp(1)));

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitRPFilter(XPathGrammarParser.RPFilterContext ctx) {

        return visit(ctx.rp());
    }

    @Override public List<Node> visitConstantFilter(XPathGrammarParser.ConstantFilterContext ctx) {
        List<Node> res = new ArrayList<>();
        visit(ctx.rp());
        String strconstant = ctx.STRINGCONSTANT().getText();

        strconstant = strconstant.substring(1, strconstant.length() - 1);

        for (Node node : currentNodes) {
            if (node.getNodeType() == Node.TEXT_NODE && node.getNodeValue().equals(strconstant)) {
                res.add(node);
            }
        }

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitEqualFilter(XPathGrammarParser.EqualFilterContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.rp(0));
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.rp(1));

        for (Node node1 : re1) {
            for (Node node2 : re2) {
                if (node1.isEqualNode(node2)) {
                    res.add(node1);
                }
            }
        }

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitIsFilter(XPathGrammarParser.IsFilterContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.rp(0));
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.rp(1));

        for (Node node1 : re1) {
            for (Node node2 : re2) {
                if (node1.isSameNode(node2)) {
                    res.add(node1);
                }
            }
        }

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitBracketFilter(XPathGrammarParser.BracketFilterContext ctx) {

        return visit(ctx.f());
    }

    @Override public List<Node> visitAndFilter(XPathGrammarParser.AndFilterContext ctx) {
        List<Node> res = new ArrayList<>();

        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.f(0));
        //System.out.println(re1.size());
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.f(1));
        //System.out.println(re2.size() + "\n");

        if (re1.size() != 0 && re2.size() != 0) {
            return currentNodes;
        }
        else {
            return new ArrayList<>();
        }
        /*
        for (Node node1 : re1) {
            for (Node node2 : re2) {
                if (node1.isSameNode(node2)) {
                    res.add(node1);
                }
            }
        }

        currentNodes = res;

        return res;
        */
    }

    @Override public List<Node> visitOrFilter(XPathGrammarParser.OrFilterContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.f(0));
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.f(1));
        res = unique(re1, re2);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitNotFilter(XPathGrammarParser.NotFilterContext ctx) {
        List<Node> res = new ArrayList<>(currentNodes);
        List<Node> notNode = visit(ctx.f());

        if (notNode.size() != 0) {
            return new ArrayList<>();
        }
        else {
            return res;
        }

        // currentNodes = res;

        /*
        for (Node node1 : currentNodes) {
            for (Node node2 : notNode) {
                if (node1.isSameNode(node2) == true) {
                    res.remove(node1);
                }
            }
        }

        currentNodes = res;
        return res;
        */

    }

    private List<Node> readXML(String filename) {
        List<Node> res = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            String filepath = "test/" + filename;
            Document doc = builder.parse(filepath);
            res.add(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    private List<Node> getAllChildren(List<Node> nodes) {
        List<Node> res = new ArrayList<>();

        for (Node node : nodes) {
            NodeList nodelist = node.getChildNodes();
            for (int i = 0; i < nodelist.getLength(); i++) {
                res.add(nodelist.item(i));
            }
        }

        return res;
    }

    private List<Node> getAllParents(List<Node> nodes) {
        List<Node> res = new ArrayList<>();

        for (Node node : nodes) {
            res.add(node.getParentNode());
        }

        return res;
    }

    private List<Node> unique(List<Node> nodes1, List<Node> nodes2) {
        /*
        List<Node> tmp = new ArrayList<>();
        tmp.addAll(nodes1);
        tmp.addAll(nodes2);
        Set<Node> nodeSet = new HashSet<>(tmp);
        List<Node> res = new ArrayList<>(nodeSet);
        */

        List<Node> res = new ArrayList<>();
        res.addAll(nodes1);

        for (Node node2 : nodes2) {
            Integer find = 0;
            for (Node node : res) {
                if (node.isSameNode(node2)) {
                    find = 1;
                }
            }
            if (find == 0) res.add(node2);
        }

        return res;
    }

    private List<Node> getListDescendants(List<Node> nodes) {
        List<Node> descendants = new ArrayList<>();
        int len = nodes.size();
        for(int i = 0; i < len; i++){
            descendants.addAll(getNodeDescendants(nodes.get(i)));
        }
        return descendants;
    }

    private List<Node> getNodeDescendants(Node node) {
        List<Node> descendants = new ArrayList<>();
        int len = node.getChildNodes().getLength();
        descendants.add(node);
        for(int i = 0; i < len; i++){
            descendants.addAll(getNodeDescendants(node.getChildNodes().item(i)));
        }
        return descendants;
    }
}
