package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.TypeDescriptor;
import tokyo.peya.javasm.intellij.langjal.parser.psi.TypeDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;

public class FieldReferenceNode extends ANTLRPsiNode
{
    public FieldReferenceNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public FullQualifiedNameNode getFieldOwner()
    {
        FullQualifiedNameNode fullQualifiedNameNode = PsiTreeUtil.findChildOfType(this, FullQualifiedNameNode.class);
        if (fullQualifiedNameNode == null)
            throw new IllegalStateException("Field owner not found in instruction field reference node");

        return fullQualifiedNameNode;
    }

    @NotNull
    public String getFieldName()
    {
        String fieldName = this.getText();
        if (fieldName == null || fieldName.isEmpty())
            throw new IllegalStateException("Field name is not found in instruction field reference node");

        return fieldName;
    }

    @NotNull
    public TypeDescriptor getType()
    {
        TypeDescriptorNode typeDescriptorNode = PsiTreeUtil.findChildOfType(this, TypeDescriptorNode.class);
        if (typeDescriptorNode == null)
            throw new IllegalStateException("TypeDescriptorNode is not found in InstructionFieldReferenceNode");

        return typeDescriptorNode.getType();
    }
}
