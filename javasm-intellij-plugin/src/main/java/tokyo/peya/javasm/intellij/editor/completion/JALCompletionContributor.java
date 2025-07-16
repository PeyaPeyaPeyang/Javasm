package tokyo.peya.javasm.intellij.editor.completion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;

public class JALCompletionContributor extends CompletionContributor
{
    private static final String[] LABEL_INSTRUCTION_NAMES = {
            "goto",
            "goto_w",
            "if_acmpeq",
            "if_acmpne",
            "if_icmpeq",
            "if_icmpne",
            "if_icmplt",
            "if_icmpge",
            "if_icmpgt",
            "if_icmple",
            "ifeq",
            "ifne",
            "iflt",
            "ifge",
            "ifgt",
            "ifle",
            "ifnull",
            "ifnonnull",
            "jsr",
            "jsr_w",
            "ret"
    };

    public JALCompletionContributor()
    {
        this.extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement()
                                .withLanguage(JALLanguage.INSTANCE)
                                .inside(InstructionSetNode.class)
                                .with(JALCompletionCommons.emptyLine())
                                .andNot(PlatformPatterns.psiElement().afterLeaf(
                                        PlatformPatterns.psiElement(InstructionNameNode.class))
                                ),
                new JALInstructionNameCompletionProvider()
        );
        this.extend(
                CompletionType.BASIC,
                PlatformPatterns.psiElement()
                                .withLanguage(JALLanguage.INSTANCE)
                                .afterLeaf(PlatformPatterns.psiElement(InstructionNameNode.class)
                                                           .withText(PlatformPatterns.string()
                                                                                     .oneOf(LABEL_INSTRUCTION_NAMES))
                                ),
                new JALLabelNameCompletionProvider()
        );
    }
}
