package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.MethodDescriptor;
import tokyo.peya.javasm.intellij.jvm.TypeDescriptor;

public class MethodDescriptorNode extends ANTLRPsiNode
{
    public MethodDescriptorNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public @NotNull MethodDescriptor getMethodDescriptor()
    {
        return MethodDescriptor.parse(this.getText());
    }

    @NotNull
    public TypeDescriptor getReturnType()
    {
        return this.getMethodDescriptor().getReturnType();
    }

    @NotNull
    public TypeDescriptor[] getParameterTypes()
    {
        return this.getMethodDescriptor().getParameterTypes();
    }

    @NotNull
    public String getMethodName()
    {
        return this.getText();
    }
}
