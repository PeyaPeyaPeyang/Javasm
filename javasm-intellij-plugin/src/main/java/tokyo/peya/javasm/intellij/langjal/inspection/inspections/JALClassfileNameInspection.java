package tokyo.peya.javasm.intellij.langjal.inspection.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.execution.PSIExecutorUtil;
import tokyo.peya.javasm.intellij.langjal.inspection.AbstractJALInspection;
import tokyo.peya.javasm.intellij.langjal.inspection.JALPsiElementVisitor;
import tokyo.peya.javasm.intellij.langjal.inspection.quickfixes.JALClassfileNameQuickFix;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

public class JALClassfileNameInspection extends AbstractJALInspection
{
    public JALClassfileNameInspection()
    {
        super("JALClassfileNameInspection");
    }

    @Override
    protected @NotNull JALPsiElementVisitor buildJALVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                                            @NotNull LocalInspectionToolSession session)
    {
        return new JALPsiElementVisitor()
        {
            @Override
            protected void visitClass(@NotNull ClassDefinitionNode node)
            {
                PSIExecutorUtil.ClassNameValidationResult validationResult = PSIExecutorUtil.validateClassName(node);
                if (validationResult == null || validationResult.isValid())
                    return;

                // 一致しない場合
                holder.registerProblem(
                        node.getClassNameNode(),
                        "Class file name does not match the class name: " + validationResult.relativePathFromSourceRoot(),
                        new JALClassfileNameQuickFix(validationResult.expectedPath())
                );
            }
        };
    }
}
