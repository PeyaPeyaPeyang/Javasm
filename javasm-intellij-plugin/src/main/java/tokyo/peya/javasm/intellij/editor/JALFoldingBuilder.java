package tokyo.peya.javasm.intellij.editor;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitorRecursive;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchArgumentNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionTableSwitchArgumentNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionTableSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

import java.util.ArrayList;
import java.util.List;

public class JALFoldingBuilder extends FoldingBuilderEx
{
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document,
                                                          boolean quick)
    {
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        root.accept(new JALPsiElementVisitorRecursive() {
            @Override
            protected void visitClass(@NotNull ClassDefinitionNode node)
            {
                ClassBodyNode classBody = node.getClassBodyNode();
                if (classBody != null)
                    descriptors.add(new FoldingDescriptor(classBody.getNode(), classBody.getTextRange()));
            }

            @Override
            protected void visitMethod(@NotNull MethodDefinitionNode node)
            {
                MethodBodyNode methodBody = node.getMethodBody();
                if (methodBody != null)
                    descriptors.add(new FoldingDescriptor(methodBody.getNode(), methodBody.getTextRange()));
            }

            @Override
            protected void visitInstructionSet(@NotNull InstructionSetNode node)
            {
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }

            @Override
            protected void visitInstruction(@NotNull InstructionNode node)
            {
                FoldingDescriptor desc = JALFoldingBuilder.visitInstruction(node);
                if (desc != null)
                    descriptors.add(desc);
            }
        });

        return descriptors.toArray(FoldingDescriptor[]::new);
    }

    private static FoldingDescriptor visitInstruction(InstructionNode insn)
    {
        if (insn instanceof InstructionTableSwitchNode tableSwitch)
        {
            InstructionTableSwitchArgumentNode arg = tableSwitch.getTableSwitchArgument();
            if (arg != null)
                return new FoldingDescriptor(arg, arg.getTextRange());
        }
        else if (insn instanceof InstructionLookupSwitchNode lookupSwitch)
        {
            InstructionLookupSwitchArgumentNode arg = lookupSwitch.getTableSwitchArgument();
            if (arg != null)
                return new FoldingDescriptor(arg, arg.getTextRange());
        }

        return null;
    }


    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode astNode)
    {
        PsiElement element = astNode.getPsi();
        if (element instanceof InstructionSetNode instructionSet)
        {  // こいつも IdentifierDefSubtree だけど， それだとフォルド時に labelName となってしまうが， labelName: としたい
            LabelNode labelNode = instructionSet.getLabel();
            if(labelNode != null)
                return labelNode.getText();
        }
        else if (element instanceof IdentifierDefSubtree idDef)
        {
            IdentifierNode id = (IdentifierNode) idDef.getNameIdentifier();
            if (id != null)
                return id.getText();
        }

        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode)
    {
        return false;
    }
}
