package tokyo.peya.javasm.intellij.langjal.snippet;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFileType;

public class JALSnippetFile extends PsiFileBase {
    public JALSnippetFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, JALSnippetLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return JALFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "JAL Snippet";
    }
}
