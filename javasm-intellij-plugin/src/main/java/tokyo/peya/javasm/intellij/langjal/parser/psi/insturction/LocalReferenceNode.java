package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.intellij.adaptor.psi.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.JALParserDefinition;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;

public class LocalReferenceNode extends ANTLRPsiNode implements PsiNamedElement, PsiNameIdentifierOwner
{
    public LocalReferenceNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier()
    {
        PsiElement node = this.findChildByType(JALParserDefinition.ID);
        if (node == null)
            node = this.findChildByType(JALParserDefinition.NUMBER);

        return node;
    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException
    {
        if (s.isEmpty())
            throw new IncorrectOperationException("Name cannot be empty");

        boolean isNumber = EvaluatorCommons.isNumber(s);
        PsiElement newID = Trees.createLeafFromText(
                this.getProject(),
                this.getLanguage(),
                this.getContext(),
                s,
                isNumber ? JALParserDefinition.NUMBER: JALParserDefinition.ID
        );

        PsiElement oldID = this.getNameIdentifier();
        if (oldID == null)
            throw new IncorrectOperationException("InstructionLocalReferenceNode must have an ID or NUMBER child node");

        if (newID != null)
        {
            this.getNode().replaceChild(oldID.getNode(), newID.getNode());
            return newID;
        }

        return this;
    }
}
