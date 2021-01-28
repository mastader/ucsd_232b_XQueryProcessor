import org.w3c.dom.Node;
import java.util.List;

public class XPathImplementedVisitor extends XPathGrammarBaseVisitor<List<Node>>{
    @Override public List<Node> visitSingleSlashAP(XPathGrammarParser.SingleSlashAPContext ctx) {
        return visitChildren(ctx);
    }

    @Override public List<Node> visitDoubleSlashAP(XPathGrammarParser.DoubleSlashAPContext ctx) {
        System.out.println(ctx.FILENAME());
        return visitChildren(ctx);
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAllChildrenRP(XPathGrammarParser.AllChildrenRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitDoubleSlashRP(XPathGrammarParser.DoubleSlashRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitFilteredRP(XPathGrammarParser.FilteredRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitTagRP(XPathGrammarParser.TagRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitTextRP(XPathGrammarParser.TextRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAttributeRP(XPathGrammarParser.AttributeRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitBracketRP(XPathGrammarParser.BracketRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSequenceRP(XPathGrammarParser.SequenceRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitParentRP(XPathGrammarParser.ParentRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSelfRP(XPathGrammarParser.SelfRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitSingleSlashRP(XPathGrammarParser.SingleSlashRPContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitAndFilter(XPathGrammarParser.AndFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitIsFilter(XPathGrammarParser.IsFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitBracketFilter(XPathGrammarParser.BracketFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitRPFilter(XPathGrammarParser.RPFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitNotFilter(XPathGrammarParser.NotFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitConstantFilter(XPathGrammarParser.ConstantFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitEqualFilter(XPathGrammarParser.EqualFilterContext ctx) { return visitChildren(ctx); }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public List<Node> visitOrFilter(XPathGrammarParser.OrFilterContext ctx) { return visitChildren(ctx); }
}
