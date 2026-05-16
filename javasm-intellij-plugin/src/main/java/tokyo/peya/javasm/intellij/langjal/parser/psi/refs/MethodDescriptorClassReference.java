package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.utils.ClassResolutionUtil;

public class MethodDescriptorClassReference extends PsiReferenceBase<MethodDescriptorNode> {
    private final String className;

    public MethodDescriptorClassReference(
            @NotNull MethodDescriptorNode element,
            @NotNull TextRange rangeInElement,
            @NotNull String className) {
        super(element, rangeInElement);
        this.className = className;
    }

    @Override
    public @Nullable PsiElement resolve() {
        Project project = this.myElement.getProject();
        JavaPsiFacade facade = JavaPsiFacade.getInstance(project);
        GlobalSearchScope scope = GlobalSearchScope.allScope(project);
        return ClassResolutionUtil.resolve(facade, scope, this.className);
    }
}
