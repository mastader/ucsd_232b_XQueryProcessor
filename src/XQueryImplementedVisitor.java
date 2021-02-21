import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.w3c.dom.*;

import java.sql.SQLOutput;
import java.util.*;

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

public class XQueryImplementedVisitor extends XQueryGrammarBaseVisitor<List<Node>> {

    private HashMap<String, List<Node>> context = new HashMap<>();

    private List<Node> currentNodes = new ArrayList<>();

    // Helper functions
    /*
    private List<Node> unique(List<Node> nodes) {
        List<Node> res = new ArrayList<>();
        for (Node node1 : nodes) {
            Integer find = 0;
            for (Node node2 : res) {
                if (node1.isSameNode(node2)) {
                    find = 1;
                }
            }
            if (find == 0) res.add(node1);
        }

        return res;
    }
    */
    private List<Node> unique(List<Node> nodes) {
        List<Node> res = new ArrayList<>();
        for (Node node1 : nodes) {
            if (!res.contains(node1)) {
                res.add(node1);
            }
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

    private Node makeText(String s) {
        Node re = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            re = doc.createTextNode(s);
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return re;
    }

    private Node makeElem(String t, List<Node> nodes) {
        Element re = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            re = doc.createElement(t);

            for (Node n : nodes) {
                //System.out.println(t);
                Node c = n.cloneNode(true);
                doc.adoptNode(c);
                re.appendChild(c);

                /*
                if (c.getNodeType() == Node.ATTRIBUTE_NODE) {
                    // ((Element)node).setAttribute("attr_name","attr_value");
                    re.setAttribute(c.getNodeName(), c.getNodeValue());
                } else {
                    re.appendChild(c);
                }
                 */
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }

        return re;
    }

    @Override public List<Node> visitVarXQ(XQueryGrammarParser.VarXQContext ctx) {

        String var = ctx.VAR().getText();

        if (context.containsKey(var)) {
            currentNodes = new ArrayList<>(context.get(var));
        }
        else {
            currentNodes = new ArrayList<>();
        }

        return currentNodes;

        /*
        String var = ctx.VAR().getText();

        if (context.containsKey(var)) {
            List<Node> re = new ArrayList<>(context.get(var));
            currentNodes = re;
            return re;
        }

        currentNodes = new ArrayList<>();
        return new ArrayList<>();
        */
    }

    @Override public List<Node> visitStringXQ(XQueryGrammarParser.StringXQContext ctx) {
        String constant = ctx.STRINGCONSTANT().getText();
        constant = constant.substring(1, constant.length()-1);
        Node textNode = makeText(constant);
        List<Node> ret = new ArrayList<>();
        ret.add(textNode);
        currentNodes = ret;

        return currentNodes;
    }

    @Override public List<Node> visitApXQ(XQueryGrammarParser.ApXQContext ctx) {
        return visit(ctx.ap());
    }

    @Override public List<Node> visitBracketXQ(XQueryGrammarParser.BracketXQContext ctx) {
        return visit(ctx.xq());
    }

    @Override public List<Node> visitSequenceXQ(XQueryGrammarParser.SequenceXQContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        res.addAll(visit(ctx.xq(0)));
        currentNodes = tmp;
        res.addAll(visit(ctx.xq(1)));
        currentNodes = res;

        return currentNodes;
    }

    @Override public List<Node> visitSingleSlashXQ(XQueryGrammarParser.SingleSlashXQContext ctx) {
        visit(ctx.xq());
        visit(ctx.rp());
        currentNodes = unique(currentNodes);

        return currentNodes;
    }

    @Override public List<Node> visitDoubleSlashXQ(XQueryGrammarParser.DoubleSlashXQContext ctx) {

        visit(ctx.xq());
        currentNodes.addAll(getListDescendants(currentNodes));
        currentNodes = visit(ctx.rp());
        currentNodes = unique(currentNodes);

        return currentNodes;
    }

    @Override public List<Node> visitTagXQ(XQueryGrammarParser.TagXQContext ctx) {
        // System.out.println("visit tag");
        Node tagNode = makeElem(ctx.TAGNAME(0).getText(), visit(ctx.xq()));

        List<Node> ret = new ArrayList<>();
        ret.add(tagNode);
        currentNodes = ret;

        return currentNodes;
    }

    @Override public List<Node> visitFlworXQ(XQueryGrammarParser.FlworXQContext ctx) {
        List<Node> nodes = new ArrayList<>();
        HashMap<String, List<Node>> tmpContext = new HashMap<>(context);

        iterateVar(ctx, nodes, 0);

        //currentNodes = nodes;
        context = tmpContext;

        return nodes;
    }

    private void iterateVar(XQueryGrammarParser.FlworXQContext ctx, List<Node> nodes, int iter) {

        if (ctx.forClause().VAR().size() == iter) {
            //HashMap<String, List<Node>> tmpContext = new HashMap<>(context);

            if (ctx.letClause() != null) {
                visit(ctx.letClause());
            }

            if ((ctx.whereClause() == null) || (visit(ctx.whereClause()).size() != 0)) {
                //System.out.println("entering return");
                //for (var key : context.keySet()) {
                //    System.out.println("key " + key);
                //    System.out.println("size " + context.get(key).size());
                //}
                nodes.addAll(visit(ctx.returnClause()));
                //System.out.println("end return");
            }

            //context = tmpContext;
        } else {

            List<Node> res = visit(ctx.forClause().xq(iter));
            //System.out.println(res.size());
            for (int i = 0; i < res.size(); i++) {

                List<Node> n = new ArrayList<>();
                n.add(res.get(i));
                //System.out.println(n.size());
                //System.out.println(ctx.forClause().VAR(iter).getText());

                context.put(ctx.forClause().VAR(iter).getText(), n);
                iterateVar(ctx, nodes, iter + 1);
            }
        }
    }

    @Override public List<Node> visitLetClauseXQ(XQueryGrammarParser.LetClauseXQContext ctx) {
        List<Node> tmpNodes = currentNodes; //?
        HashMap<String, List<Node>> tmpContext = context;
        currentNodes = visit(ctx.letClause());
        List<Node> res = visit(ctx.xq());
        currentNodes = tmpNodes; //?
        context = tmpContext;

        return res;
    }

    @Override public List<Node> visitForClause(XQueryGrammarParser.ForClauseContext ctx) {

        return currentNodes;
    }

    @Override public List<Node> visitLetClause(XQueryGrammarParser.LetClauseContext ctx) {
        int len = ctx.VAR().size();
        for(int i = 0; i < len; i++) {
            //System.out.println("update Context");
            context.put(ctx.VAR(i).getText(), visit(ctx.xq(i)));
        }

        return currentNodes;
    }

    @Override public List<Node> visitWhereClause(XQueryGrammarParser.WhereClauseContext ctx) {

        return visit(ctx.cond());
    }

    @Override public List<Node> visitReturnClause(XQueryGrammarParser.ReturnClauseContext ctx) {

        return visit(ctx.xq());
    }

    @Override public List<Node> visitEqualCond(XQueryGrammarParser.EqualCondContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.xq(0));
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.xq(1));

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

    @Override public List<Node> visitIsCond(XQueryGrammarParser.IsCondContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.xq(0));
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.xq(1));

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

    @Override public List<Node> visitEmptyCond(XQueryGrammarParser.EmptyCondContext ctx) {
        List<Node> tmp = currentNodes;
        List<Node> res = visit(ctx.xq());
        currentNodes = tmp;

        if (res.size() == 0) {
            return currentNodes;
        }

        return new ArrayList<>();
    }

    @Override public List<Node> visitMultipleCond(XQueryGrammarParser.MultipleCondContext ctx) {
        List<Node> tmpNode = currentNodes;
        HashMap<String, List<Node>> tmpContext = context;

        int len = ctx.VAR().size();

        for (int i = 0; i < len; i++) {
            //System.out.println("update Context");
            context.put(ctx.VAR(i).getText(), visit(ctx.xq(i)));
        }
        List<Node> res = visit(ctx.cond());

        context = tmpContext;
        currentNodes = tmpNode;

        return res;
    }

    @Override public List<Node> visitBracketCond(XQueryGrammarParser.BracketCondContext ctx) {

        return visit(ctx.cond());
    }

    @Override public List<Node> visitAndCond(XQueryGrammarParser.AndCondContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.cond(0));
        //System.out.println(re1.size());
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.cond(1));
        //System.out.println(re2.size() + "\n");

        if (re1.size() != 0 && re2.size() != 0) {
            return currentNodes;
        }

        return new ArrayList<>();
    }

    @Override public List<Node> visitOrCond(XQueryGrammarParser.OrCondContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.cond(0));
        currentNodes = tmp;
        re1.addAll(visit(ctx.cond(1)));
        res = unique(re1);
        currentNodes = res;

        return res;
    }

    @Override public List<Node> visitNotCond(XQueryGrammarParser.NotCondContext ctx) {
        List<Node> res = new ArrayList<>(currentNodes);
        List<Node> notNode = visit(ctx.cond());

        if (notNode.size() != 0) {
            return new ArrayList<>();
        }
        return res;
    }

    @Override public List<Node> visitSingleSlashAP(XQueryGrammarParser.SingleSlashAPContext ctx) {
        String filename = ctx.FILENAME().getText();
        filename = filename.substring(1, filename.length() - 1);
        currentNodes = readXML(filename);

        return visit(ctx.rp());
    }

    @Override public List<Node> visitDoubleSlashAP(XQueryGrammarParser.DoubleSlashAPContext ctx) {
        String filename = ctx.FILENAME().getText();
        filename = filename.substring(1, filename.length() - 1);
        currentNodes = readXML(filename);
        currentNodes.addAll(getListDescendants(currentNodes));

        return visit(ctx.rp());
    }

    @Override public List<Node> visitTagRP(XQueryGrammarParser.TagRPContext ctx) {
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

    @Override public List<Node> visitAllChildrenRP(XQueryGrammarParser.AllChildrenRPContext ctx) {
        List<Node> res = getAllChildren(currentNodes);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitSelfRP(XQueryGrammarParser.SelfRPContext ctx) {

        return currentNodes;
    }

    @Override public List<Node> visitParentRP(XQueryGrammarParser.ParentRPContext ctx) {
        List<Node> res = getAllParents(currentNodes);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitTextRP(XQueryGrammarParser.TextRPContext ctx) {
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

    @Override public List<Node> visitAttributeRP(XQueryGrammarParser.AttributeRPContext ctx) {
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

    @Override public List<Node> visitBracketRP(XQueryGrammarParser.BracketRPContext ctx) {

        return visit(ctx.rp());
    }

    @Override public List<Node> visitSingleSlashRP(XQueryGrammarParser.SingleSlashRPContext ctx) {
        visit(ctx.rp(0));
        visit(ctx.rp(1));

        currentNodes = unique(currentNodes);

        //currentNodes = res;
        return currentNodes;
    }

    @Override public List<Node> visitDoubleSlashRP(XQueryGrammarParser.DoubleSlashRPContext ctx) {
        /*
        visit(ctx.rp(0));
        currentNodes = getListDescendants(currentNodes);
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> res = unique2(tmp, visit(ctx.rp(1)));

        currentNodes = res;
        return res;
        */
        visit(ctx.rp(0));
        currentNodes = getListDescendants(currentNodes);
        List<Node> tmp = new ArrayList<>(currentNodes);
        visit(ctx.rp(1));
        tmp.addAll(currentNodes);
        currentNodes = unique(tmp);

        // currentNodes = res;
        return currentNodes;
    }

    @Override public List<Node> visitFilteredRP(XQueryGrammarParser.FilteredRPContext ctx) {
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

    @Override public List<Node> visitSequenceRP(XQueryGrammarParser.SequenceRPContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        res.addAll(visit(ctx.rp(0)));
        currentNodes = tmp;
        res.addAll(visit(ctx.rp(1)));

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitRPFilter(XQueryGrammarParser.RPFilterContext ctx) {

        return visit(ctx.rp());
    }

    @Override public List<Node> visitConstantFilter(XQueryGrammarParser.ConstantFilterContext ctx) {
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

    @Override public List<Node> visitEqualFilter(XQueryGrammarParser.EqualFilterContext ctx) {
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

    @Override public List<Node> visitIsFilter(XQueryGrammarParser.IsFilterContext ctx) {
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

    @Override public List<Node> visitBracketFilter(XQueryGrammarParser.BracketFilterContext ctx) {

        return visit(ctx.f());
    }

    @Override public List<Node> visitAndFilter(XQueryGrammarParser.AndFilterContext ctx) {
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

    @Override public List<Node> visitOrFilter(XQueryGrammarParser.OrFilterContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentNodes);
        List<Node> re1 = visit(ctx.f(0));
        currentNodes = tmp;
        List<Node> re2 = visit(ctx.f(1));
        res = unique2(re1, re2);

        currentNodes = res;
        return res;
    }

    @Override public List<Node> visitNotFilter(XQueryGrammarParser.NotFilterContext ctx) {
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
            String filepath = filename;
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

    private List<Node> unique2(List<Node> nodes1, List<Node> nodes2) {
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

}