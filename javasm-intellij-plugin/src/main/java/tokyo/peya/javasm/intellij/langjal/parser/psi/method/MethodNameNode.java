package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.antlr.intellij.adaptor.psi.Trees;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.JALParserDefinition;

public class MethodNameNode extends ANTLRPsiNode implements PsiNameIdentifierOwner
{
    public MethodNameNode(@NotNull ASTNode node)
    {
        super(node);
    }

    @NotNull
    public String getMethodName()
    {
        return this.getText();
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
        assert oldID != null : "LabelNameNode must have an ID child node";

        if (newID != null)
        {
            this.getNode().replaceChild(oldID.getNode(), newID.getNode());
            return newID;
        }
        return this;
    }

    @Override
    public @Nullable PsiElement getNameIdentifier()
    {
        return this.findChildByType(JALParserDefinition.ID);
    }
}
