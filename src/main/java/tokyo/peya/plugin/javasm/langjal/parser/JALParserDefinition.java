package tokyo.peya.plugin.javasm.langjal.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.langjal.JALFile;
import tokyo.peya.plugin.javasm.langjal.JALLanguage;
import tokyo.peya.plugin.javasm.langjal.psi.JALTypes;

public final class JALParserDefinition implements ParserDefinition
{
    public static final IFileElementType FILE = new IFileElementType(JALLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project)
    {
        return new JALLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project)
    {
        return new JALParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType()
    {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens()
    {
        return JALTokenSets.COMMENT;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements()
    {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode astNode)
    {
        return JALTypes.Factory.createElement(astNode);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider fileViewProvider)
    {
        return new JALFile(fileViewProvider);
    }
}
