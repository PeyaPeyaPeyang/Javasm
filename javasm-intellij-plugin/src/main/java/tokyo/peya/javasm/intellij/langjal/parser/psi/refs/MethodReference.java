package tokyo.peya.javasm.intellij.langjal.parser.psi.refs;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiType;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.JALElementReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.MethodReferenceNode;
import tokyo.peya.javasm.intellij.utils.ClassResolutionUtil;
import tokyo.peya.javasm.intellij.utils.TypeDescriptorUtil;
import tokyo.peya.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.langjal.compiler.jvm.TypeDescriptor;

public class MethodReference extends JALElementReference
{
    private final MethodReferenceNode ref;

    public MethodReference(@NotNull MethodReferenceNode element)
    {
        super(element.getMethodNameNode());
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
        Project project = getElement().getProject();
        String fieldName = this.ref.getMethodName();

        FullQualifiedNameNode methodOwner = this.ref.getMethodOwner();
        if (methodOwner == null) {
            return null;
        }
        String dotClassName = methodOwner.getDotName();
        if (dotClassName == null) {
            return null;
        }

        // JavaPsiFacadeを使用して、完全修飾クラス名からPsiClassを取得
        PsiClass psiClass = ClassResolutionUtil.resolve(JavaPsiFacade.getInstance(project), GlobalSearchScope.allScope(project), dotClassName);
        if (psiClass == null) {
            return null;
        }

        MethodDescriptor methodDescriptor = this.ref.getMethodDescriptor();
        if (methodDescriptor == null) {
            return null;
        }

        PsiMethod[] methods = psiClass.findMethodsByName(fieldName, true);
        for (PsiMethod method : methods)
        {
            if (this.isMatched(method, this.ref.getMethodDescriptor()))
            {
                return method;
            }
        }

        return null;
    }

    private boolean isMatched(PsiMethod method, MethodDescriptor expected) {
        TypeDescriptor[] expectedParams = expected.getParameterTypes();

        TypeDescriptor expectedReturn = expected.getReturnType();
        String expectedReturnName = TypeDescriptorUtil.toPublicName(expectedReturn);
        PsiType methodReturnType = method.getReturnType();
        if (methodReturnType == null || !expectedReturnName.equals(methodReturnType.getInternalCanonicalText())) {
            return false;
        }


        PsiParameterList parameterList = method.getParameterList();
        if (parameterList.getParametersCount() != expectedParams.length) {
            return false;
        }

        for (int i = 0; i < expectedParams.length; i++) {
            String expectedType = TypeDescriptorUtil.toPublicName(expectedParams[i]);
            String actualType = parameterList.getParameters()[i].getType().getInternalCanonicalText();
            if (!expectedType.equals(actualType)) {
                return false;
            }
        }

        return true;
    }
}

