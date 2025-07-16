package tokyo.peya.javasm.intellij.editor.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

public class JALLabelNameCompletionProvider extends CompletionProvider<CompletionParameters>
{
    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result)
    {
        PsiElement position = parameters.getPosition();
        MethodDefinitionNode methodDefinitionNode = PsiTreeUtil.getParentOfType(position, MethodDefinitionNode.class);
        if (methodDefinitionNode == null)
            return;
        InstructionSetNode[] instructionSets = methodDefinitionNode.getInstructionSets();
        for (InstructionSetNode instructionSet : instructionSets)
        {
            LabelNode labelNode = instructionSet.getLabel();
            if (labelNode == null)
                continue;

            String labelName = labelNode.getLabelName();
            LookupElementBuilder lookup =
                    LookupElementBuilder.create(labelName)
                                        .withTypeText("Label")
                                        .withIcon(labelNode.getIcon(0))
                                        .withInsertHandler(JALCompletionCommons.insertAndNewLine())
                                        .withTailText(" @" + instructionSet.getTextRange().getStartOffset(), true);
            result.addElement(lookup);
        }
    }
}
