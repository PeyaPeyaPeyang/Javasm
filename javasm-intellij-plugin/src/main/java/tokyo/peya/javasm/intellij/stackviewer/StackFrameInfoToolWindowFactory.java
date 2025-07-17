package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

public class StackFrameInfoToolWindowFactory implements ToolWindowFactory
{

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow)
    {
        toolWindow.setTitle("Frame and Stack Viewer");

        StackFrameInfoController controller = StackFrameInfoController.getInstance(project);
        JPanel panel = controller.getMainPanel();
        controller.onEditorChanged();

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
