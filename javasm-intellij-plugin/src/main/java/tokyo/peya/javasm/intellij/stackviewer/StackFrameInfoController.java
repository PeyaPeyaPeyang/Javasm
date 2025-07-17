package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

@Service(Service.Level.PROJECT)
public final class StackFrameInfoController
{
    private final Project project;
    private final StackFramePanelFactory uiFactory;

    public StackFrameInfoController(Project project)
    {
        this.project = project;
        this.uiFactory = new StackFramePanelFactory(this::onAnalyseButtonPushed);
    }

    private void onAnalyseButtonPushed()
    {
        new Task.Backgroundable(null, "Analyzing stack frame", true)
        {
            @Override
            public void run(@NotNull ProgressIndicator indicator)
            {
                if (!StackFrameInfoController.this.computeStackAndFrame())
                    this.onCancel();
            }

            @Override
            public void onCancel()
            {
                ApplicationManager.getApplication()
                                  .invokeLater(StackFrameInfoController.this.uiFactory::onUpdateCancelled);
            }
        }.queue();
    }

    private boolean computeStackAndFrame()
    {
        Editor currentEditor = FileEditorManager.getInstance(this.project).getSelectedTextEditor();
        if (currentEditor == null)
        {
            ApplicationManager.getApplication().invokeLater(this.uiFactory::onUpdateCancelled);
            return false;
        }

        String editorContent = currentEditor.getDocument().getText();
        StackFramePanelFactory.ProgressbarUpdater updater = this.getUpdater();
        StackFrameInfoComputer computer = new StackFrameInfoComputer(editorContent, updater);
        boolean succeed = computer.compile();
        if (!succeed)
            return false;

        succeed = computer.analyseMethods();
        return succeed;
    }

    private StackFramePanelFactory.ProgressbarUpdater getUpdater()
    {
        return this.uiFactory.getUpdater();
    }

    public JPanel getMainPanel()
    {
        return this.uiFactory.getMainPanel();
    }

    public void updateStackFrameInfo(StackUIInstruction instruction, StackUIElement[] stackElements,
                                     StackUIElement[] localVariables)
    {
        this.uiFactory.updateStackFrameInfo(instruction, stackElements, localVariables);
    }

    public void onEditorChanged()
    {
        this.uiFactory.onEditorChanged();
    }

    public static StackFrameInfoController getInstance(Project project)
    {
        return project.getService(StackFrameInfoController.class);
    }
}
