package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import javax.swing.*;

public class InstructionDiagramToolWindowFactory implements ToolWindowFactory {
    public static final String TOOL_WINDOW_ID = "Instruction Diagram";
    public static final Key<Object> AUTO_CLOSED_KEY = Key.create("InstructionDiagramToolWindowAutoClosed");

    public static void closeAuto(@NotNull Project project) {
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_ID);
        if (!(toolWindow == null || toolWindow.isDisposed() || toolWindow.isVisible())) {
            project.putUserData(AUTO_CLOSED_KEY, new Object());
            toolWindow.hide();
        }
    }

    public static void openAuto(@NotNull Project project) {
        if (project.getUserData(AUTO_CLOSED_KEY) == null)
            return;
        project.putUserData(AUTO_CLOSED_KEY, null);
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_ID);
        if (!(toolWindow == null || toolWindow.isDisposed()))
            toolWindow.show();
    }

    public static boolean isVisible(@NotNull Project project) {
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow(TOOL_WINDOW_ID);
        return toolWindow != null && !toolWindow.isDisposed() && toolWindow.isVisible();
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        String title = JALMessages.message("jal.instructionDiagram.toolWindow.title");
        toolWindow.setTitle(title);
        toolWindow.setStripeTitle(title);

        InstructionDiagramController controller = InstructionDiagramController.getInstance(project);
        JPanel panel = controller.getMainPanel();

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
