package tokyo.peya.plugin.javasm.langjal.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.langjal.parser.JALLexerAdapter;
import tokyo.peya.plugin.javasm.langjal.parser.JALTokenSets;
import tokyo.peya.plugin.javasm.langjal.psi.JALTokenType;
import tokyo.peya.plugin.javasm.langjal.psi.JALTypes;

public class JALSyntaxHighlighter extends SyntaxHighlighterBase
{
    public static final TextAttributesKey NUMBER =
            TextAttributesKey.createTextAttributesKey("JAL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey KEYWORD =
            TextAttributesKey.createTextAttributesKey("JAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING_VALUE =
            TextAttributesKey.createTextAttributesKey("JAL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT =
            TextAttributesKey.createTextAttributesKey("JAL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            TextAttributesKey.createTextAttributesKey("JAL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey OPERATOR =
            TextAttributesKey.createTextAttributesKey("JAL_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BRACES =
            TextAttributesKey.createTextAttributesKey("JAL_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey SEMICOLON =
            TextAttributesKey.createTextAttributesKey("JAL_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COMMA =
            TextAttributesKey.createTextAttributesKey("JAL_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey PARENTHESES =
            TextAttributesKey.createTextAttributesKey("JAL_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACKETS =
            TextAttributesKey.createTextAttributesKey("JAL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey LABEL =
            TextAttributesKey.createTextAttributesKey("JAL_LABEL", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey FIELD =
            TextAttributesKey.createTextAttributesKey("JAL_FIELD", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    public static final TextAttributesKey METHOD_DECLARATION =
            TextAttributesKey.createTextAttributesKey("JAL_METHOD_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey METHOD_CALL =
            TextAttributesKey.createTextAttributesKey("JAL_METHOD_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey CLASS_META =
            TextAttributesKey.createTextAttributesKey("JAL_CLASS_META", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey CLASS_NAME =
            TextAttributesKey.createTextAttributesKey("JAL_CLASS_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey CLASS_REFERENCE =
            TextAttributesKey.createTextAttributesKey("JAL_CLASS_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
    public static final TextAttributesKey BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey("JAL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);

    private static final TextAttributesKey[] NUMBER_KEYS = {NUMBER};
    private static final TextAttributesKey[] KEY_KEYS = {KEYWORD};
    private static final TextAttributesKey[] STRING_KEYS = {STRING_VALUE};

    private static final TextAttributesKey[] COMMENT_KEYS = {COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = {BLOCK_COMMENT};
    private static final TextAttributesKey[] OPERATOR_KEYS = {OPERATOR};
    private static final TextAttributesKey[] BRACES_KEYS = {BRACES};
    private static final TextAttributesKey[] SEMICOLON_KEYS = {SEMICOLON};
    private static final TextAttributesKey[] COMMA_KEYS = {COMMA};
    private static final TextAttributesKey[] PARENTHESES_KEYS = {PARENTHESES};
    private static final TextAttributesKey[] BRACKETS_KEYS = {BRACKETS};
    private static final TextAttributesKey[] LABEL_KEYS = {LABEL};
    private static final TextAttributesKey[] FIELD_KEYS = {FIELD};
    private static final TextAttributesKey[] METHOD_DECLARATION_KEYS = {METHOD_DECLARATION};
    private static final TextAttributesKey[] METHOD_CALL_KEYS = {METHOD_CALL};
    private static final TextAttributesKey[] CLASS_META_KEYS = {CLASS_META};
    private static final TextAttributesKey[] CLASS_NAME_KEYS = {CLASS_NAME};
    private static final TextAttributesKey[] CLASS_REFERENCE_KEYS = {CLASS_REFERENCE};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = {BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @Override
    public @NotNull Lexer getHighlightingLexer()
    {
        return new JALLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType iElementType)
    {
        if (iElementType == null)
            return EMPTY_KEYS;

        if (JALTokenSets.NUMBER.contains(iElementType))
            return NUMBER_KEYS;
        else if (JALTokenSets.KEYWORDS.contains(iElementType))
            return KEY_KEYS;
        else  if (JALTypes.STRING.equals(iElementType))
            return STRING_KEYS;
        else if (JALTypes.LINE_COMMENT.equals(iElementType))
            return COMMENT_KEYS;
        else if (JALTypes.BLOCK_COMMENT.equals(iElementType))
            return BLOCK_COMMENT_KEYS;
        else if (JALTokenSets.OPERATORS.contains(iElementType))
            return OPERATOR_KEYS;
        else if (JALTokenSets.BRACES.contains(iElementType))
            return BRACES_KEYS;
        else if (JALTypes.SEMI.equals(iElementType))
            return SEMICOLON_KEYS;
        else if (JALTypes.COMMA.equals(iElementType))
            return COMMA_KEYS;
        else if (JALTokenSets.PARENTHESES.contains(iElementType))
            return PARENTHESES_KEYS;
        else if (JALTokenSets.BRACKETS.contains(iElementType))
            return BRACKETS_KEYS;
        else if (JALTokenSets.LABEL.contains(iElementType))
            return LABEL_KEYS;
        else if (JALTypes.FIELD_NAME.equals(iElementType))
            return FIELD_KEYS;
     //   else if (JALTypes.NAME.equals(iElementType))
       //     return METHOD_DECLARATION_KEYS;
        else if (JALTokenSets.INSTRUCTIONS.contains(iElementType))
            return METHOD_CALL_KEYS;
        else if (JALTypes.CLASS_META.equals(iElementType))
            return CLASS_META_KEYS;
        else if (JALTokenSets.CLASS_REF.contains(iElementType))
            return CLASS_REFERENCE_KEYS;
        else if (iElementType == TokenType.BAD_CHARACTER)
            return BAD_CHAR_KEYS;

        return EMPTY_KEYS;
    }
}
