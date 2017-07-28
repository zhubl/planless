import antlr4.com.zhubl.ddl.LionLexer;
import antlr4.com.zhubl.ddl.LionParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.InputStream;

/**
 * Created by mrlion on 2017/7/28.
 */
public class Main {

    private static void run(String expr) throws Exception {
        //构建流CodePointCharStream(4.7版本ANTLRInputStream标记为deprecated)
        CodePointCharStream in = CharStreams.fromString(expr);
        //词法分析
        LionLexer lexer = new LionLexer(in);
        //token流
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器
        LionParser parser = new LionParser(tokens);
        //验证
        parser.prog();
    }

    public static void main(String[] args) throws Exception {
        String[] expr={
                "19920728",
                "(a+b)*4",
                "a+b*4",
                "(a"
        };

        for (String e: expr) {
            System.out.println("Expr: " + e);
            run(e);
        }
    }
}
