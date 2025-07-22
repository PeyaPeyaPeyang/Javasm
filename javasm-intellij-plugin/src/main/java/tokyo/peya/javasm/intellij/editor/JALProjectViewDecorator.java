package tokyo.peya.javasm.intellij.editor;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.projectView.ProjectViewNode;
import com.intellij.ide.projectView.ProjectViewNodeDecorator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;

public class JALProjectViewDecorator implements ProjectViewNodeDecorator
{
    @Override
    public void decorate(@NotNull ProjectViewNode<?> node, @NotNull PresentationData data)
    {
        Project project = node.getProject();
        VirtualFile file = node.getVirtualFile();
        if (project == null || file == null || file.isDirectory())
            return;

        PsiFile  psiFile = PsiManager.getInstance(project).findFile(file);
        if (!(psiFile instanceof JALFile jal))
            return;

        ClassDefinitionNode clazz= jal.getClassDefinition();
        if (clazz == null)
            return;

        String name = clazz.getClassName();
        if (file.getName().equals(name + ".jal"))  // ファイル名が クラス名.jal なときにのみ, .jal をリダクション
            data.setPresentableText(name);
    }
}
