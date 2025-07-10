package tokyo.peya.javasm.intellij.langjal.inspection;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

public class JALPsiElementVisitor extends PsiElementVisitor
{
    @Override
    public void visitElement(@NotNull PsiElement element)
    {
        switch (element)
        {
            case ClassDefinitionNode classNode -> visitClass(classNode);
            case MethodDefinitionNode methodNode -> visitMethod(methodNode);
            case InstructionNode instruction -> visitInstruction(instruction);
            default ->
            {
                // no-op
            }
        }
    }

    protected void visitClass(@NotNull ClassDefinitionNode node) {}

    protected void visitMethod(@NotNull MethodDefinitionNode node) {}

    protected void visitInstruction(@NotNull InstructionNode node) {}
}
