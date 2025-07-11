package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

import java.util.ArrayList;
import java.util.List;

public class InstructionSetNode extends IdentifierDefSubtree implements ScopeNode
{
    public InstructionSetNode(@NotNull ASTNode node, @NotNull IElementType idElementType)
    {
        super(node, idElementType);
    }

    @Nullable
    public LabelNode getLabel()
    {
        return PsiTreeUtil.findChildOfType(this, LabelNode.class);
    }

    @Nullable
    public TryCatchDirectiveNode getTryCatchDirective()
    {
        return PsiTreeUtil.findChildOfType(this, TryCatchDirectiveNode.class);
    }

    @Override
    public String getName()
    {
        LabelNode labelNode = this.getLabel();
        if (labelNode != null)
            return labelNode.getName();
        return null;
    }

    @NotNull
    public InstructionNode[] getInstructions()
    {
        List<InstructionNode> instructionNodes = new ArrayList<>();
        for (PsiElement child : this.getChildren())
        {
            if (child instanceof ANTLRPsiNode instructionNode &&
                    instructionNode.getFirstChild() instanceof InstructionNode insn)
                instructionNodes.add(insn);
        }

        return instructionNodes.toArray(new InstructionNode[0]);
    }

    @Override
    public @Nullable PsiElement resolve(PsiNamedElement element)
    {
        return SymtabUtils.resolve(
                this,
                JALLanguage.INSTANCE,
                element,
                "/classDefinition/classBody/classBodyItem/methodDefinition/methodBody/instructionSet/label/labelName"
        );
    }
}
