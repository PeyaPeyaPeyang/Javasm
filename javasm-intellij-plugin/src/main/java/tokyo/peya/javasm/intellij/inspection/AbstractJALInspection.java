package tokyo.peya.javasm.intellij.inspection;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;

public abstract class AbstractJALInspection extends LocalInspectionTool {
    private final String id;

    public AbstractJALInspection(@NotNull String id) {
        this.id = id;
    }

    @Override
    public @NotNull PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                                   @NotNull LocalInspectionToolSession session) {
        return this.buildJALVisitor(holder, isOnTheFly, session);
    }

    @NotNull
    protected abstract JALPsiElementVisitor buildJALVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                                            @NotNull LocalInspectionToolSession session);

    @Override
    public @NonNls @NotNull String getID() {
        return this.id;
    }
    public boolean isAvailableForFile(@NotNull PsiFile file) {
        return file.getLanguage() instanceof JALLanguage;
    }
}
