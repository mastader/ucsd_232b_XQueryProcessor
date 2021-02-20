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

    private List<Node> currentnodes = new ArrayList<>();

    // Helper functions
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
            currentnodes = context.get(var);
        }
        else {
            currentnodes = new ArrayList<>();
        }

        return currentnodes;
    }

    @Override public List<Node> visitStringXQ(XQueryGrammarParser.StringXQContext ctx) {

        return currentnodes;
    }

    @Override public List<Node> visitSingleSlashXQ(XQueryGrammarParser.SingleSlashXQContext ctx) {
        visit(ctx.xq());
        visit(ctx.rp());
        currentnodes = unique(currentnodes);

        return currentnodes;
    }

    @Override public List<Node> visitApXQ(XQueryGrammarParser.ApXQContext ctx) {
        return visit(ctx.ap());
    }

    @Override public List<Node> visitSequenceXQ(XQueryGrammarParser.SequenceXQContext ctx) {
        List<Node> res = new ArrayList<>();
        List<Node> tmp = new ArrayList<>(currentnodes);
        res.addAll(visit(ctx.xq(0)));
        currentnodes = tmp;
        res.addAll(visit(ctx.xq(1)));
        currentnodes = res;

        return currentnodes;
    }

    @Override public List<Node> visitBracketXQ(XQueryGrammarParser.BracketXQContext ctx) {
        return visit(ctx.xq());
    }

    @Override public List<Node> visitFlworXQ(XQueryGrammarParser.FlworXQContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitTagXQ(XQueryGrammarParser.TagXQContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitLetClauseXQ(XQueryGrammarParser.LetClauseXQContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitDoubleSlashXQ(XQueryGrammarParser.DoubleSlashXQContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitForClause(XQueryGrammarParser.ForClauseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitLetClause(XQueryGrammarParser.LetClauseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitWhereClause(XQueryGrammarParser.WhereClauseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitReturnClause(XQueryGrammarParser.ReturnClauseContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitMultipleCond(XQueryGrammarParser.MultipleCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitOrCond(XQueryGrammarParser.OrCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitEqualCond(XQueryGrammarParser.EqualCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAndCond(XQueryGrammarParser.AndCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitNotCond(XQueryGrammarParser.NotCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitEmptyCond(XQueryGrammarParser.EmptyCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitBracketCond(XQueryGrammarParser.BracketCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitIsCond(XQueryGrammarParser.IsCondContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSingleSlashAP(XQueryGrammarParser.SingleSlashAPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitDoubleSlashAP(XQueryGrammarParser.DoubleSlashAPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAllChildrenRP(XQueryGrammarParser.AllChildrenRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitDoubleSlashRP(XQueryGrammarParser.DoubleSlashRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitFilteredRP(XQueryGrammarParser.FilteredRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitTagRP(XQueryGrammarParser.TagRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitTextRP(XQueryGrammarParser.TextRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAttributeRP(XQueryGrammarParser.AttributeRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitBracketRP(XQueryGrammarParser.BracketRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSequenceRP(XQueryGrammarParser.SequenceRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitParentRP(XQueryGrammarParser.ParentRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSelfRP(XQueryGrammarParser.SelfRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSingleSlashRP(XQueryGrammarParser.SingleSlashRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAndFilter(XQueryGrammarParser.AndFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitIsFilter(XQueryGrammarParser.IsFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitBracketFilter(XQueryGrammarParser.BracketFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitRPFilter(XQueryGrammarParser.RPFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitNotFilter(XQueryGrammarParser.NotFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitConstantFilter(XQueryGrammarParser.ConstantFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitEqualFilter(XQueryGrammarParser.EqualFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitOrFilter(XQueryGrammarParser.OrFilterContext ctx) { return visitChildren(ctx); }
}