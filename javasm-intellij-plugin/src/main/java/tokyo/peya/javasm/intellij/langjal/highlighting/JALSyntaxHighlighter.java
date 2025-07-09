package tokyo.peya.javasm.intellij.langjal.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.langjal.compiler.JALLexer;
import tokyo.peya.javasm.langjal.compiler.JALParser;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class JALSyntaxHighlighter extends SyntaxHighlighterBase
{
    private static final TextAttributesKey[] EMPTY = new TextAttributesKey[0];

    public static final TextAttributesKey ID =
            createTextAttributesKey("JAL_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("JAL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("JAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("JAL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("JAL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("JAL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("JAL_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey SEMICOLON =
            createTextAttributesKey("JAL_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("JAL_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey PARENTHESIS =
            createTextAttributesKey("JAL_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("JAL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey LABEL =
            createTextAttributesKey("JAL_LABEL", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey METHOD_NAME =
            createTextAttributesKey("JAL_METHOD_NAME", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey METHOD_CALL =
            createTextAttributesKey("JAL_METHOD_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey PARAMETER =
            createTextAttributesKey("JAL_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey CLASS_NAME =
            createTextAttributesKey("JAL_CLASS_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey FIELD_NAME =
            createTextAttributesKey("JAL_FIELD_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    public static final TextAttributesKey DESC_BOOLEAN =
            createTextAttributesKey("JAL_DESC_BOOLEAN", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_BYTE =
            createTextAttributesKey("JAL_DESC_BYTE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_CHAR =
            createTextAttributesKey("JAL_DESC_CHAR", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_SHORT =
            createTextAttributesKey("JAL_DESC_SHORT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_INT =
            createTextAttributesKey("JAL_DESC_INT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_LONG =
            createTextAttributesKey("JAL_DESC_LONG", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_FLOAT =
            createTextAttributesKey("JAL_DESC_FLOAT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_DOUBLE =
            createTextAttributesKey("JAL_DESC_DOUBLE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_VOID =
            createTextAttributesKey("JAL_DESC_VOID", DefaultLanguageHighlighterColors.KEYWORD);

    static
    {
        initialise();
    }

    @SuppressWarnings("deprecation")
    private static void initialise()
    {
        PSIElementTypeFactory.defineLanguageIElementTypes(JALLanguage.INSTANCE,
                                                          JALParser.tokenNames,
                                                          JALParser.ruleNames);
    }

    @Override
    public @NotNull Lexer getHighlightingLexer()
    {
        return new ANTLRLexerAdaptor(JALLanguage.INSTANCE, new JALLexer(null));
    }

    @Nullable
    private TextAttributesKey highlightsToken(TokenIElementType token)
    {
        return  switch (token.getANTLRTokenType())
            {
                case JALLexer.ID -> ID;
                case JALLexer.NUMBER -> NUMBER;
                case JALLexer.STRING -> STRING;
                case JALLexer.LINE_COMMENT -> COMMENT;
                case JALLexer.BLOCK_COMMENT -> BLOCK_COMMENT;
                case JALLexer.LBR,
                     JALLexer.RBR -> BRACES;
                case JALLexer.SEMI -> SEMICOLON;
                case JALLexer.COMMA -> COMMA;
                case JALLexer.LP,
                     JALLexer.RP -> PARENTHESIS;
                case JALLexer.LBK,
                     JALLexer.RBK -> BRACKETS;
                case JALLexer.KWD_CLASS_PROP_MINOR,
                     JALLexer.KWD_CLASS_PROP_MAJOR,
                     JALLexer.KWD_CLASS_PROP_SUPER_CLASS,
                     JALLexer.KWD_CLASS_PROP_INTERFACES -> PARAMETER;
                case JALLexer.TYPE_DESC_OBJECT -> CLASS_NAME;

                case JALLexer.TYPE_DESC_BOOLEAN -> DESC_BOOLEAN;
                case JALLexer.TYPE_DESC_BYTE -> DESC_BYTE;
                case JALLexer.TYPE_DESC_CHAR -> DESC_CHAR;
                case JALLexer.TYPE_DESC_SHORT -> DESC_SHORT;
                case JALLexer.TYPE_DESC_INT -> DESC_INT;
                case JALLexer.TYPE_DESC_LONG -> DESC_LONG;
                case JALLexer.TYPE_DESC_FLOAT -> DESC_FLOAT;
                case JALLexer.TYPE_DESC_DOUBLE -> DESC_DOUBLE;
                case JALLexer.TYPE_DESC_VOID -> DESC_VOID;

                case JALLexer.KWD_ACC_PUBLIC,
                     JALLexer.KWD_ACC_PRIVATE,
                     JALLexer.KWD_ACC_PROTECTED,
                     JALLexer.KWD_ACC_ATTR_STATIC,
                     JALLexer.KWD_ACC_ATTR_FINAL,
                     JALLexer.KWD_ACC_ATTR_SUPER,
                     JALLexer.KWD_ACC_ATTR_SYNCHRONIZED,
                     JALLexer.KWD_ACC_ATTR_BRIDGE,
                     JALLexer.KWD_ACC_ATTR_VARARGS,
                     JALLexer.KWD_ACC_ATTR_NATIVE,
                     JALLexer.KWD_ACC_ATTR_ABSTRACT,
                     JALLexer.KWD_ACC_ATTR_STRICTFP,
                     JALLexer.KWD_ACC_ATTR_VOLATILE,
                     JALLexer.KWD_ACC_ATTR_TRANSIENT,
                     JALLexer.KWD_ACC_ATTR_SYNTHETIC,
                     JALLexer.KWD_ACC_ATTR_ANNOTATION,
                     JALLexer.KWD_ACC_ATTR_ENUM,
                     JALLexer.KWD_CLASS,
                     JALLexer.KWD_INTERFACE,
                     JALLexer.KWD_METHOD_HANDLE,
                     JALLexer.KWD_METHOD_TYPE,
                     JALLexer.KWD_SWITCH_DEFAULT -> KEYWORD;
                default -> null;
            };
    }
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType elementType)
    {
        TextAttributesKey key = null;
        if (elementType instanceof TokenIElementType tokenType)
             key = highlightsToken(tokenType);

        if (key == null)
            return EMPTY;
        else
            return new TextAttributesKey[]{key};
    }
}
