package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALElementReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNode;

public class FieldReference extends JALElementReference
{
    private final FieldReferenceNode ref;

    public FieldReference(@NotNull FieldReferenceNode element)
    {
        super(element.getFieldNameNode());
        this.ref = element;
    }

    @Override
    public boolean isSubtree(PsiElement psiElement)
    {
        return psiElement instanceof FieldReferenceNameNode;
    }

    @Override
    public @Nullable PsiElement resolve()
    {
        Project project = getElement().getProject();;
        String fieldName = this.ref.getFieldName();

        FullQualifiedNameNode fieldOwner = this.ref.getFieldOwner();
        if (fieldOwner == null) {
            return null;
        }
        String dotClassName = fieldOwner.getDotName();
        if (dotClassName == null) {
            return null;
        }

        // JavaPsiFacadeを使用して、完全修飾クラス名からPsiClassを取得
        PsiClass psiClass = JavaPsiFacade.getInstance(project).findClass(dotClassName, GlobalSearchScope.allScope(project));
        if (psiClass == null) {
            return null;
        }

        return psiClass.findFieldByName(fieldName, true);
    }
}

