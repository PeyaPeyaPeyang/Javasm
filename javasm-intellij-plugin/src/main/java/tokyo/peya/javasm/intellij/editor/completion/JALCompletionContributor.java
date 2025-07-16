package tokyo.peya.javasm.intellij.editor.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;

public class JALCompletionContributor extends CompletionContributor
{
    public JALCompletionContributor()
    {
        this.extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement()
                                .withLanguage(JALLanguage.INSTANCE)
                                .inside(InstructionSetNode.class)
                                .andNot(PlatformPatterns.psiElement().afterLeaf(
                                        PlatformPatterns.psiElement(InstructionNameNode.class))
                                ).andNot(PlatformPatterns.psiElement().afterLeaf(
                                        PlatformPatterns.psiElement(InstructionNode.class))
                                ),
                new JALInstructionNameCompletionProvider()
        );
    }
}
