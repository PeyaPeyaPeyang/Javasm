package tokyo.peya.javasm.intellij.langjal.inspection.quickfixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;

import java.nio.file.Path;

public class JALClassfileNameQuickFix implements LocalQuickFix
{
    @SafeFieldForPreview
    private final Path correctPath;

    public JALClassfileNameQuickFix(@NotNull Path correctPath)
    {
        this.correctPath = correctPath;
    }

    @Override
    public @IntentionFamilyName @NotNull String getFamilyName()
    {
        return "Rename class file to match class name";
    }

    @Override
    public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor)
    {
        if (!(descriptor.getPsiElement().getContainingFile() instanceof JALFile jalFile))
            return;

        try
        {
            VirtualFile currentVFile = jalFile.getVirtualFile();

            Path targetPath = this.correctPath;
            Path targetParentPath = targetPath.getParent();

            // VirtualFileの親ディレクトリを取得or作成
            VirtualFile targetParentVFile = VfsUtil.createDirectoryIfMissing(targetParentPath.toString());

            if (targetParentVFile == null)
            {
                System.err.println("Failed to create target directory: " + targetParentPath);
                return;
            }

            // ファイル移動＆リネーム
            currentVFile.move(this, targetParentVFile);
            currentVFile.rename(this, targetPath.getFileName().toString());

        }
        catch (Exception e)
        {
            System.err.println("Failed to move and rename class file: " + e.getMessage());
        }
    }
}
