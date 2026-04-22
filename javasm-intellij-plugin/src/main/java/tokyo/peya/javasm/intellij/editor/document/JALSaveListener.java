package tokyo.peya.javasm.intellij.editor.document;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFileType;
import tokyo.peya.javasm.intellij.stackviewer.StackFrameInfoController;

public class JALSaveListener implements FileDocumentManagerListener
{
    @Override
    public void fileContentLoaded(@NotNull VirtualFile file, @NotNull Document document)
    {
        this.triggerReanalyse(file, document);
    }

    @Override
    public void afterDocumentSaved(@NotNull Document document)
    {
        FileDocumentManager fdm = FileDocumentManager.getInstance();
        VirtualFile file = fdm.getFile(document);
        if (file == null) {
            return;
        }

        this.triggerReanalyse(file, document);
    }

    private void triggerReanalyse(@NotNull VirtualFile file, @NotNull Document document) {
        // LangJAL かどうかを確認
        if (file.getFileType() != JALFileType.INSTANCE) {
            return;
        }

        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        for (Project project : projects) {
            FileEditorManager fem = FileEditorManager.getInstance(project);

            FileEditor[] editors = fem.getEditors(file);
            if (editors.length == 0) {
                continue;
            }

            // 今アクティブなエディタかどうか
            FileEditor selected = fem.getSelectedEditor(file);
            boolean isActive = selected != null;

            if (isActive) {
                // アクティブなエディタがある場合は、再解析をトリガー
                StackFrameInfoController.getInstance(project).analyseStackFrame();
            }
        }
    }
}
