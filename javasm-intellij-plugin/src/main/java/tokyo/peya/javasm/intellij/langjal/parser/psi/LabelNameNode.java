package tokyo.peya.javasm.intellij.langjal.parser.psi;

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

public class LabelNameNode extends ANTLRPsiNode implements PsiNamedElement, PsiNameIdentifierOwner
{
    public LabelNameNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException
    {
        PsiElement newID = Trees.createLeafFromText(
                this.getProject(),
                this.getLanguage(),
                this.getContext(),
                s,
                JALParserDefinition.ID
        );

        PsiElement oldID = this.findChildByType(JALParserDefinition.ID);
        assert oldID != null: "LabelNameNode must have an ID child node";

        if (newID != null)
        {
            this.getNode().replaceChild(oldID.getNode(), newID.getNode());
            return newID;
        }
        return this;
    }

    public @NotNull String getName()
    {
        PsiElement id = this.findChildByType(JALParserDefinition.ID);
        if (id == null)
            return "<unknown>"; // ID child node must exist

        String name = id.getText();
        if (name == null)
            return "<unknown>"; // ID text must not be null
        return name;
    }

    @Override
    public @Nullable PsiElement getNameIdentifier()
    {
        return this.findChildByType(JALParserDefinition.ID);
    }
}
