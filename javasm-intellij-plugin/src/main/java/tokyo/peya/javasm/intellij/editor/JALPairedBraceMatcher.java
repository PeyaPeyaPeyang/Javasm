package tokyo.peya.javasm.intellij.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.JALTokens;
import tokyo.peya.langjal.compiler.JALLexer;

public class JALPairedBraceMatcher implements PairedBraceMatcher
{
    @Override
    public BracePair @NotNull [] getPairs()
    {
        return  new BracePair[] {
                new BracePair(JALTokens.getToken(JALLexer.LBK), JALTokens.getToken(JALLexer.RBK), false),
                new BracePair(JALTokens.getToken(JALLexer.LBR), JALTokens.getToken(JALLexer.RBR), true),
                new BracePair(JALTokens.getToken(JALLexer.LP), JALTokens.getToken(JALLexer.RP), false),
        };
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType iElementType,
                                                   @Nullable IElementType iElementType1)
    {
        return true;
    }

    @Override
    public int getCodeConstructStart(@NotNull PsiFile psiFile, int braceOffset)
    {
        return braceOffset;
    }
}
