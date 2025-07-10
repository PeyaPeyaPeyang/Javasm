package tokyo.peya.javasm.intellij.langjal.parser;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.langjal.compiler.JALLexer;

public interface JALTokens
{
    TokenSet COMMENTS = PSIElementTypeFactory.createTokenSet(
            JALLanguage.INSTANCE,
            JALLexer.BLOCK_COMMENT,
            JALLexer.LINE_COMMENT
    );

    TokenSet WHITESPACE = PSIElementTypeFactory.createTokenSet(
            JALLanguage.INSTANCE,
            JALLexer.SPACE
    );

    TokenSet STRING = PSIElementTypeFactory.createTokenSet(
            JALLanguage.INSTANCE,
            JALLexer.STRING
    );

    TokenSet NUMBER = PSIElementTypeFactory.createTokenSet(
            JALLanguage.INSTANCE,
            JALLexer.NUMBER
    );

    TokenSet LBRACE = PSIElementTypeFactory.createTokenSet(
            JALLanguage.INSTANCE,
            JALLexer.LBR
    );
    TokenSet RBRACE = PSIElementTypeFactory.createTokenSet(
            JALLanguage.INSTANCE,
            JALLexer.RBR
    );
}
