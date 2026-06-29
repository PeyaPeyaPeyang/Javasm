package tokyo.peya.javasm.intellij.langjal.preprocessor;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.JALTokens;
import tokyo.peya.langjal.compiler.JALLexer;

import java.util.List;

public class JALPreprocessorAwareLexer extends LexerBase {
    private final Lexer delegate = new ANTLRLexerAdaptor(JALLanguage.INSTANCE, new JALLexer(null));
    private CharSequence originalBuffer = "";
    private String originalText = "";
    private List<JALPreprocessorDirectiveUtil.DefineDirective> defineDirectives = List.of();
    private int splitTokenStart = -1;
    private int splitTokenEnd = -1;
    private IElementType splitTokenType;

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.originalBuffer = buffer;
        this.originalText = buffer.toString();
        this.defineDirectives = JALPreprocessorDirectiveUtil.findDefineDirectives(this.originalText);
        this.clearSplitToken();
        this.delegate.start(maskPreprocessorDirectives(this.originalText, this.defineDirectives),
                            startOffset,
                            endOffset,
                            initialState);
        this.prepareSplitTokenIfNeeded();
    }

    @Override
    public int getState() {
        return this.delegate.getState();
    }

    @Override
    public @Nullable IElementType getTokenType() {
        if (this.splitTokenType != null)
            return this.splitTokenType;

        IElementType tokenType = this.delegate.getTokenType();
        if (!(tokenType instanceof TokenIElementType antlrToken) || antlrToken.getANTLRTokenType() != JALLexer.ID)
            return tokenType;

        String tokenText = this.originalBuffer.subSequence(this.delegate.getTokenStart(), this.delegate.getTokenEnd()).toString();
        String macroValue = JALPreprocessorDirectiveUtil.resolveMacroValueAt(
                this.originalText,
                this.delegate.getTokenStart(),
                tokenText
        );
        if (macroValue == null)
            return tokenType;

        IElementType macroTokenType = findFirstMacroTokenType(macroValue);
        return macroTokenType == null ? tokenType : macroTokenType;
    }

    @Override
    public int getTokenStart() {
        if (this.splitTokenType != null)
            return this.splitTokenStart;

        return this.delegate.getTokenStart();
    }

    @Override
    public int getTokenEnd() {
        if (this.splitTokenType != null)
            return this.splitTokenEnd;

        return this.delegate.getTokenEnd();
    }

    @Override
    public void advance() {
        if (this.splitTokenType != null) {
            this.advanceSplitToken();
            return;
        }

        this.delegate.advance();
        this.prepareSplitTokenIfNeeded();
    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return this.originalBuffer;
    }

    @Override
    public int getBufferEnd() {
        return this.originalBuffer.length();
    }

    private static @NotNull CharSequence maskPreprocessorDirectives(
            @NotNull String text,
            @NotNull List<JALPreprocessorDirectiveUtil.DefineDirective> directives
    ) {
        StringBuilder masked = new StringBuilder(text);

        for (JALPreprocessorDirectiveUtil.DefineDirective directive : directives) {
            for (int i = directive.range().getStartOffset(); i < directive.range().getEndOffset(); i++) {
                char c = masked.charAt(i);
                if (c != '\r' && c != '\n')
                    masked.setCharAt(i, ' ');
            }
        }

        return masked;
    }

    private void prepareSplitTokenIfNeeded() {
        this.clearSplitToken();

        IElementType tokenType = this.delegate.getTokenType();
        if (!(tokenType instanceof TokenIElementType antlrToken) || antlrToken.getANTLRTokenType() != JALLexer.SPACE)
            return;

        int tokenStart = this.delegate.getTokenStart();
        int tokenEnd = this.delegate.getTokenEnd();
        for (JALPreprocessorDirectiveUtil.DefineDirective directive : this.defineDirectives) {
            if (directive.range().getEndOffset() <= tokenStart)
                continue;
            if (directive.range().getStartOffset() >= tokenEnd)
                break;

            this.splitTokenStart = tokenStart;
            this.splitTokenEnd = findSplitTokenEnd(tokenStart, tokenEnd);
            this.splitTokenType = getSplitTokenType(tokenStart);
            return;
        }
    }

    private void advanceSplitToken() {
        int delegateEnd = this.delegate.getTokenEnd();
        if (this.splitTokenEnd >= delegateEnd) {
            this.clearSplitToken();
            this.delegate.advance();
            this.prepareSplitTokenIfNeeded();
            return;
        }

        this.splitTokenStart = this.splitTokenEnd;
        this.splitTokenEnd = findSplitTokenEnd(this.splitTokenStart, delegateEnd);
        this.splitTokenType = getSplitTokenType(this.splitTokenStart);
    }

    private int findSplitTokenEnd(int start, int limit) {
        SplitCategory category = splitCategory(this.originalBuffer.charAt(start));
        int pos = start + 1;
        while (pos < limit && splitCategory(this.originalBuffer.charAt(pos)) == category)
            pos++;
        return pos;
    }

    private @NotNull IElementType getSplitTokenType(int start) {
        if (splitCategory(this.originalBuffer.charAt(start)) != SplitCategory.TEXT)
            return JALTokens.getToken(JALLexer.SPACE);
        return JALTokens.getToken(JALLexer.LINE_COMMENT);
    }

    private static @NotNull SplitCategory splitCategory(char c) {
        if (c == ' ' || c == '\t')
            return SplitCategory.HORIZONTAL_SPACE;
        if (c == '\r' || c == '\n')
            return SplitCategory.LINE_BREAK;
        return SplitCategory.TEXT;
    }

    private void clearSplitToken() {
        this.splitTokenStart = -1;
        this.splitTokenEnd = -1;
        this.splitTokenType = null;
    }

    private static @Nullable IElementType findFirstMacroTokenType(@NotNull String macroValue) {
        for (String tokenText : JALPreprocessorDirectiveUtil.tokenizeMacroValue(macroValue)) {
            JALLexer lexer = new JALLexer(CharStreams.fromString(tokenText));
            Token token = lexer.nextToken();
            if (token.getType() == Token.EOF)
                return null;
            try {
                return JALTokens.getToken(token.getType());
            } catch (IllegalArgumentException ignored) {
                return null;
            }
        }

        return null;
    }

    private enum SplitCategory {
        HORIZONTAL_SPACE,
        LINE_BREAK,
        TEXT
    }
}
