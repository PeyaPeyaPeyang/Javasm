package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;

public class LabelNode extends IdentifierDefSubtree
{
    public LabelNode(@NotNull ASTNode node, @NotNull IElementType idElementType)
    {
        super(node, idElementType);
    }

    @NotNull
    public LabelNameNode getLabelNameNode()
    {
        LabelNameNode labelNameNode = PsiTreeUtil.getChildOfType(this, LabelNameNode.class);
        if (labelNameNode == null)
            throw new IllegalStateException("LabelNameNode is not found in " + this);
        return labelNameNode;
    }

    @Override
    public String getName()
    {
        return this.getLabelNameNode().getText();
    }

    @NotNull
    public String getLabelName()
    {
        return this.getLabelNameNode().getText();
    }
}
