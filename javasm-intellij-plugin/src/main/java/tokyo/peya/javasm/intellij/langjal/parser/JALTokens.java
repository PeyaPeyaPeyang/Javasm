package tokyo.peya.javasm.intellij.langjal.parser;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.langjal.compiler.JALLexer;

import java.util.List;

public interface JALTokens
{
    List<TokenIElementType> TOKENS = PSIElementTypeFactory.getTokenIElementTypes(JALLanguage.INSTANCE);

    @NotNull
    static TokenIElementType getToken(int id)
    {
        for (TokenIElementType token: TOKENS)
            if (token.getANTLRTokenType() == id)
                return token;

        throw new IllegalArgumentException("No JAL token found: " + id);
    }

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
