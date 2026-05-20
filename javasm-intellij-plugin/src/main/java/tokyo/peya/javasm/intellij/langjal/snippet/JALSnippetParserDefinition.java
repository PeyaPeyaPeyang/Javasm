package tokyo.peya.javasm.intellij.langjal.snippet;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.JALParserDefinition;
import tokyo.peya.javasm.intellij.langjal.parser.JALTokens;
import tokyo.peya.langjal.compiler.JALLexer;
import tokyo.peya.langjal.compiler.JALParser;

public class JALSnippetParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(JALSnippetLanguage.INSTANCE);

    public JALSnippetParserDefinition() {
        JALParserDefinition.initStatic();
    }

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new ANTLRLexerAdaptor(JALLanguage.INSTANCE, new JALLexer(null));
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        JALParser parser = new JALParser(null);
        return new ANTLRParserAdaptor(JALLanguage.INSTANCE, parser) {
            @Override
            protected ParseTree parse(Parser parser, IElementType root) {
                JALParser jalParser = (JALParser) parser;
                if (root instanceof IFileElementType) {
                    // ルートがファイルノードの場合，命令セットやメソッド定義の開始を判定して適切なルールからパースを開始する
                    if (isInstructionStart(jalParser.getInputStream().LA(1)))
                        return jalParser.instructionSet();
                    // メソッド定義の開始か？
                    if (isMethodDefinitionStart(jalParser))
                        return jalParser.methodDefinition();
                    return jalParser.root();
                }
                return jalParser.jvmInsArgScalarType();
            }
        };
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return JALTokens.WHITESPACE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return JALTokens.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return JALTokens.STRING;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return new JALParserDefinition().createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new JALSnippetFile(viewProvider);
    }

    @Override
    public @NotNull SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    private static boolean isInstructionStart(int tokenType) {
        return JALLexer.INSN_AALOAD <= tokenType && tokenType <= JALLexer.INSN_WIDE;
    }

    private static boolean isMethodDefinitionStart(JALParser parser) {
        int tokenIndex = 1;
        int tokenType = parser.getInputStream().LA(tokenIndex);
        if (tokenType == JALLexer.KWD_MNAME_INIT || tokenType == JALLexer.KWD_MNAME_CLINIT)
            return true;
        if (!isAccessModifierOrAttribute(tokenType))
            return false;

        while (isAccessModifierOrAttribute(tokenType)) {
            tokenIndex++;
            tokenType = parser.getInputStream().LA(tokenIndex);
        }

        return tokenType != JALLexer.KWD_CLASS && tokenType != JALLexer.KWD_INTERFACE;
    }

    private static boolean isAccessModifierOrAttribute(int tokenType) {
        return switch (tokenType) {
            case JALLexer.KWD_ACC_PUBLIC,
                 JALLexer.KWD_ACC_PRIVATE,
                 JALLexer.KWD_ACC_PROTECTED,
                 JALLexer.KWD_ACC_ATTR_STATIC,
                 JALLexer.KWD_ACC_ATTR_FINAL,
                 JALLexer.KWD_ACC_ATTR_SYNCHRONIZED,
                 JALLexer.KWD_ACC_ATTR_BRIDGE,
                 JALLexer.KWD_ACC_ATTR_VARARGS,
                 JALLexer.KWD_ACC_ATTR_NATIVE,
                 JALLexer.KWD_ACC_ATTR_ABSTRACT,
                 JALLexer.KWD_ACC_ATTR_STRICTFP,
                 JALLexer.KWD_ACC_ATTR_SYNTHETIC -> true;
            default -> false;
        };
    }
}
