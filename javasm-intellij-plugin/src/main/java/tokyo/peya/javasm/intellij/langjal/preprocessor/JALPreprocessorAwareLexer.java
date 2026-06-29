package tokyo.peya.javasm.intellij.langjal.preprocessor;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.langjal.compiler.JALLexer;

public class JALPreprocessorAwareLexer extends LexerBase {
    private final Lexer delegate = new ANTLRLexerAdaptor(JALLanguage.INSTANCE, new JALLexer(null));
    private CharSequence originalBuffer = "";

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState) {
        this.originalBuffer = buffer;
        this.delegate.start(maskPreprocessorDirectives(buffer), startOffset, endOffset, initialState);
    }

    @Override
    public int getState() {
        return this.delegate.getState();
    }

    @Override
    public @Nullable IElementType getTokenType() {
        return this.delegate.getTokenType();
    }

    @Override
    public int getTokenStart() {
        return this.delegate.getTokenStart();
    }

    @Override
    public int getTokenEnd() {
        return this.delegate.getTokenEnd();
    }

    @Override
    public void advance() {
        this.delegate.advance();
    }

    @Override
    public @NotNull CharSequence getBufferSequence() {
        return this.originalBuffer;
    }

    @Override
    public int getBufferEnd() {
        return this.delegate.getBufferEnd();
    }

    private static @NotNull CharSequence maskPreprocessorDirectives(@NotNull CharSequence buffer) {
        String text = buffer.toString();
        StringBuilder masked = new StringBuilder(text);

        for (JALPreprocessorDirectiveUtil.DefineDirective directive
                : JALPreprocessorDirectiveUtil.findDefineDirectives(text)) {
            for (int i = directive.range().getStartOffset(); i < directive.range().getEndOffset(); i++) {
                char c = masked.charAt(i);
                if (c != '\r' && c != '\n')
                    masked.setCharAt(i, ' ');
            }
        }

        return masked;
    }
}
