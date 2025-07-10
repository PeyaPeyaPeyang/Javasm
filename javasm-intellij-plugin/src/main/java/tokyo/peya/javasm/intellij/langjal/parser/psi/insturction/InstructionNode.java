package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InstructionNode extends ANTLRPsiNode
{
    public InstructionNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public String getInstructionName()
    {
        InstructionNameNode instructionNameNode = PsiTreeUtil.findChildOfType(this, InstructionNameNode.class);
        if (instructionNameNode == null)
            return this.getText();

        return instructionNameNode.getInstructionName();
    }

    @Nullable
    public <T extends PsiElement> T getInstructionArgument(@NotNull Class<T> clazz)
    {
        return PsiTreeUtil.findChildOfType(this, clazz);
    }

    @Nullable
    public <T extends PsiElement> T getInstructionArgument(@NotNull Class<T> clazz, int index)
    {
        PsiElement[] elements = PsiTreeUtil.getChildrenOfType(this, clazz);
        if (elements == null || index < 0 || index >= elements.length)
            return null;
        return clazz.cast(elements[index]);
    }
}
