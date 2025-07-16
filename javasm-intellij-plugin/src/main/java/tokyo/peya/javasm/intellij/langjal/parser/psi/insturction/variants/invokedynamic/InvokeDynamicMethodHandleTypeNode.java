package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.invokedynamic;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.langjal.compiler.jvm.InvocationType;

public class InvokeDynamicMethodHandleTypeNode extends ANTLRPsiNode
{
    public InvokeDynamicMethodHandleTypeNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public InvocationType getMethodHandleType()
    {
        return InvocationType.fromName(getText());
    }
}
