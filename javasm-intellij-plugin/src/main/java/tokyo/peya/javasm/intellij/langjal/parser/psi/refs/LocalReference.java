package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALElementReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.LocalDeclarationNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionLocalAccessNode;

public class LocalReference extends JALElementReference {
    public LocalReference(@NotNull IdentifierNode element) {
        super(element);
    }

    @Override
    public boolean isSubtree(PsiElement psiElement) {
        return psiElement instanceof LocalDeclarationNode;
    }

    @Override
    public @Nullable PsiElement resolve() {
        String localName = this.getElement().getText();
        MethodBodyNode methodBody = PsiTreeUtil.getParentOfType(this.getElement(), MethodBodyNode.class);
        if (methodBody == null)
            return null;

        LocalDeclarationNode bestMatch = null;
        int referenceOffset = this.getElement().getTextOffset();

        for (LocalDeclarationNode declaration : PsiTreeUtil.findChildrenOfType(methodBody, LocalDeclarationNode.class)) {
            PsiElement nameIdentifier = declaration.getNameIdentifier();
            if (nameIdentifier == null)
                continue;
            if (!localName.equals(nameIdentifier.getText()))
                continue;
            if (nameIdentifier.getTextOffset() > referenceOffset)
                continue;

            if (bestMatch == null || nameIdentifier.getTextOffset() > bestMatch.getTextOffset())
                bestMatch = declaration;
        }

        return bestMatch;
    }
}
