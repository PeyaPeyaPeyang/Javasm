package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.editor.linenumber.InstructionOffsetCalculator;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;

public class InstructionNode extends ANTLRPsiNode
{
    public InstructionNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Nullable
    public String getInstructionName()
    {
        InstructionNameNode instructionNameNode = PsiTreeUtil.findChildOfType(this, InstructionNameNode.class);
        if (instructionNameNode == null)
            return null;

        return instructionNameNode.getInstructionName();
    }

    public int getOpcode()
    {
        InstructionNameNode instructionNameNode = PsiTreeUtil.findChildOfType(this, InstructionNameNode.class);
        if (instructionNameNode == null)
            return -1;

        return instructionNameNode.getOpcode();
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

    public int getInstructionSize()
    {
        InstructionNameNode instructionNameNode = PsiTreeUtil.findChildOfType(this, InstructionNameNode.class);
        if (instructionNameNode == null)
            throw new IllegalStateException();

        return instructionNameNode.getInstructionSize();
    }

    public final int getStartInstructionOffset() throws IllegalStateException
    {
        MethodBodyNode methodBody = PsiTreeUtil.getParentOfType(this, MethodBodyNode.class);
        if (methodBody == null)
            throw new IllegalStateException();

        InstructionOffsetCalculator calculator = InstructionOffsetCalculator.get(methodBody);
        Integer offset = calculator.getCumulativeOffset(this);
        if (offset == null)
            throw new IllegalStateException();

        return offset;
    }

    public final int getCumulativeOffset()
    {
        int startOffset = this.getStartInstructionOffset();
        int instructionSize = this.getInstructionSize();
        if (instructionSize <= 0)
            return startOffset;

        return startOffset + instructionSize; // inclusive end offset
    }
}
