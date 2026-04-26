package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.util.IncorrectOperationException;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;

public abstract class JALElementReference extends PsiReferenceBase<IdentifierNode> {
    public JALElementReference(@NotNull IdentifierNode element) {
        super(element, new TextRange(0, element.getTextLength()));
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        return this.getElement().setName(newElementName);
    }

    @Override
    public @Nullable PsiElement resolve() {
        ScopeNode scopeNode = (ScopeNode) this.getElement().getContext();
        if (scopeNode == null)
            return null;

        return scopeNode.resolve(this.getElement());
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        String refName = this.getElement().getName();

        PsiElement target = element;

        if (element instanceof IdentifierNode && this.isSubtree(element.getParent())) {
            target = element.getParent();
        }

        if (!(target instanceof PsiNameIdentifierOwner owner)) {
            return false;
        }

        PsiElement identifier = owner.getNameIdentifier();
        if (identifier == null) {
            return false;
        }

        String name = identifier.getText();
        return refName.equals(name);
    }

    public abstract boolean isSubtree(PsiElement psiElement);
}
