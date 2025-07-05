package tokyo.peya.plugin.javasm.langjal;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.langjal.filetype.JALFileType;

public class JALFile extends PsiFileBase
{
    public JALFile(@NotNull FileViewProvider viewProvider)
    {
        super(viewProvider, JALLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType()
    {
        return JALFileType.INSTANCE;
    }

    @Override
    public String toString()
    {
        return "Java Assembly Language File";
    }
}
