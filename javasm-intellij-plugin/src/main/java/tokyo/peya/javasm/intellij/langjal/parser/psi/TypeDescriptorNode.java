package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.lang.ASTNode;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.langjal.compiler.jvm.TypeDescriptor;

public class TypeDescriptorNode extends ANTLRPsiNode
{
    public TypeDescriptorNode(@NotNull ASTNode node)
    {
        super(node);
    }

    public IdentifierNode getIdentifierNode()
    {
        return this.findChildByClass(IdentifierNode.class);
    }

    @NotNull
    public TypeDescriptor getType()
    {
        return TypeDescriptor.parse(this.getText());
    }
}
