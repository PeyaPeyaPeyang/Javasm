package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;

public class LabelNode extends IdentifierDefSubtree
{
    public LabelNode(@NotNull ASTNode node, @NotNull IElementType idElementType)
    {
        super(node, idElementType);
    }

    public LabelNameNode getLabelNameNode()
    {
        return PsiTreeUtil.findChildOfType(this, LabelNameNode.class);
    }
}
