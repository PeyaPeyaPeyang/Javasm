package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALElementReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.utils.ClassResolutionUtil;

public class ClassReference extends JALElementReference
{
    public ClassReference(@NotNull IdentifierNode element)
    {
        super(element);
    }

    @Override
    public boolean isSubtree(PsiElement psiElement)
    {
        return psiElement instanceof ClassNameNode
                || psiElement instanceof FullQualifiedNameNode;
    }

    @Override
    public @Nullable PsiElement resolve()
    {
        Project project = this.myElement.getProject();

        String className = getQualifiedName(this.myElement);
        JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);

        return ClassResolutionUtil.resolve(facade, scope, className);
    }

    protected String getQualifiedName(IdentifierNode node)
    {
        if (node instanceof FullQualifiedNameNode)
            return ((FullQualifiedNameNode) node).getDotName();
        else
            return node.getText().replace('/', '.');
    }
}

