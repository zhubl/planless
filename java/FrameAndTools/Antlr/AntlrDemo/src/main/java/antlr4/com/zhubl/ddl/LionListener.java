// Generated from /Users/mrlion/Projects/JavaProjects/planless/java/FrameAndTools/Antlr/src/main/antlr4/com/zhubl/ddl/Lion.g4 by ANTLR 4.7
package antlr4.com.zhubl.ddl;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LionParser}.
 */
public interface LionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LionParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LionParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LionParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LionParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LionParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LionParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LionParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LionParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LionParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LionParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LionParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LionParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LionParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(LionParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LionParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(LionParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LionParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(LionParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link LionParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(LionParser.AtomContext ctx);
}