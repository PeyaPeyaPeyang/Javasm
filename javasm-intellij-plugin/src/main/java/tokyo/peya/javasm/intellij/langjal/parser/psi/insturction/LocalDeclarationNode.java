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

public class LocalDeclarationNode extends ANTLRPsiNode implements PsiNamedElement, PsiNameIdentifierOwner {
    public LocalDeclarationNode(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable PsiElement getNameIdentifier() {
        return this.findChildByType(JALParserDefinition.ID);
    }

    @Override
    public String getName() {
        PsiElement identifier = this.getNameIdentifier();
        return identifier == null ? "" : identifier.getText();
    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException {
        if (s.isEmpty())
            throw new IncorrectOperationException("Name cannot be empty");

        PsiElement oldID = this.getNameIdentifier();
        if (oldID == null)
            throw new IncorrectOperationException("LocalDeclarationNode must have an ID child node");

        PsiElement newID = Trees.createLeafFromText(
                this.getProject(),
                this.getLanguage(),
                this.getContext(),
                s,
                JALParserDefinition.ID
        );

        if (newID != null) {
            this.getNode().replaceChild(oldID.getNode(), newID.getNode());
            return newID;
        }

        return this;
    }
}
