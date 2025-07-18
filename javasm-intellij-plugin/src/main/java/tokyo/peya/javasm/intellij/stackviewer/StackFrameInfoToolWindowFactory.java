package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;

public class StackFrameInfoToolWindowFactory implements ToolWindowFactory
{
    public static final String TOOL_WINDOW_ID = "Frame and Stack Viewer";
    public static Key<Object> AUTO_CLOSED_KEY = Key.create("StackFrameInfoToolWindowAutoClosed");

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow)
    {
        toolWindow.setTitle("Frame and Stack Viewer");

        StackFrameInfoController controller = StackFrameInfoController.getInstance(project);
        JPanel panel = controller.getMainPanel();

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public static void closeAuto(@NotNull Project project)
    {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.getToolWindow(StackFrameInfoToolWindowFactory.TOOL_WINDOW_ID);
        if (!(toolWindow == null || toolWindow.isDisposed() || toolWindow.isVisible()))
        {
            // ツールウィンドウが自動的に閉じられたことを示すフラグを設定
            project.putUserData(AUTO_CLOSED_KEY, new Object());
            toolWindow.hide();
        }
    }

    public static void openAuto(@NotNull Project project)
    {
        if (project.getUserData(AUTO_CLOSED_KEY) == null)
            return;
        project.putUserData(AUTO_CLOSED_KEY, null);
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = toolWindowManager.getToolWindow(StackFrameInfoToolWindowFactory.TOOL_WINDOW_ID);
        if (!(toolWindow == null || toolWindow.isDisposed()))
            toolWindow.show();
    }
}
