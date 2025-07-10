package tokyo.peya.javasm.intellij.langjal.inspection;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

public class JALPsiElementVisitorRecursive extends PsiRecursiveElementWalkingVisitor
{
    private boolean shouldStop;

    @Override
    public void visitElement(@NotNull PsiElement element)
    {
        if (this.shouldStop)
            return; // 再帰を止める

        switch (element)
        {
            case ClassDefinitionNode classDefinitionNode -> this.visitClass(classDefinitionNode);
            case MethodDefinitionNode methodDefinitionNode -> this.visitMethod(methodDefinitionNode);
            case InstructionNode instructionNode -> this.visitInstruction(instructionNode);
            default ->
            {
                // no-op
            }
        }

        if (this.shouldStop)
            return; // 再帰を止める
        super.visitElement(element); // 再帰は最後に（条件付きで止めたければ上で return 済み）
    }

    protected void visitClass(@NotNull ClassDefinitionNode node)
    {
    }

    protected void visitMethod(@NotNull MethodDefinitionNode node)
    {
    }

    protected void visitInstruction(@NotNull InstructionNode node)
    {
    }

    public void stop()
    {
        this.shouldStop = true;
        stopWalking();
    }
}
