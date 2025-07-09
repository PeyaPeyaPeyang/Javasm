package tokyo.peya.javasm.intellij.langjal.parser.psi;

import com.intellij.psi.PsiElement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JALPSITreeUtils
{
    @SuppressWarnings("unchecked")
    public static <T extends PsiElement> T findChildrenOfTypeRecursive(PsiElement element, Class<T> clazz)
    {
        if (element == null || clazz == null)
            return null;

        for (PsiElement child : element.getChildren())
        {
            if (clazz.isInstance(child))
                return (T) child;

            PsiElement found = findChildrenOfTypeRecursive(child, clazz);
            if (found != null)
                return (T) found;
        }
        return null;
    }
}
