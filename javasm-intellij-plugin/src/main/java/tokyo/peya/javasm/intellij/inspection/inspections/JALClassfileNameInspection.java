package tokyo.peya.javasm.intellij.inspection.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.execution.PSIExecutorUtil;
import tokyo.peya.javasm.intellij.inspection.AbstractJALInspection;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitor;
import tokyo.peya.javasm.intellij.inspection.quickfixes.JALClassfileNameQuickFix;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassNameNode;

public class JALClassfileNameInspection extends AbstractJALInspection
{
    public JALClassfileNameInspection()
    {
        super("JALClassfileName");
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

                ClassNameNode classNameNode = node.getClassNameNode();
                if (classNameNode == null)
                    return;

                // 一致しない場合
                holder.registerProblem(
                        classNameNode,
                        "Class file name does not match the class name: " + validationResult.relativePathFromSourceRoot(),
                        ProblemHighlightType.GENERIC_ERROR,
                        new JALClassfileNameQuickFix(validationResult.expectedPath())
                );
            }
        };
    }
}
