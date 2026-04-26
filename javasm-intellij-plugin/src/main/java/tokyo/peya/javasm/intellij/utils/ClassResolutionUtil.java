package tokyo.peya.javasm.intellij.utils;

import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import org.apache.commons.lang3.StringUtils;

public class ClassResolutionUtil {
    public static PsiClass resolve(JavaPsiFacade facade, GlobalSearchScope scope, String internalName) {
        String normalised = internalName.replace('/', '.');

        // Ljava/lang/String; -> java.lang.String
        if (normalised.startsWith("L") && normalised.endsWith(";")) {
            normalised = normalised.substring(1, normalised.length() - 1);
        }

        String[] inners = StringUtils.split(normalised, '$');
        if (inners.length == 0) {
            return null;
        }

        PsiClass psiClass = facade.findClass(inners[0], scope);

        // 内部クラスを解決
        for (int i = 1; i < inners.length; i++) {
            if (psiClass == null) {
                return null;
            }
            psiClass = psiClass.findInnerClassByName(inners[i], true);
        }

        return psiClass;
    }
}
