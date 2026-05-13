package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import javax.swing.*;
import java.util.Objects;

public final class InstructionDiagramConfigurable implements Configurable {
    private final InstructionDiagramSettingsService settingsService;
    private InstructionDiagramSettingsPanel panel;
    private InstructionDiagramSettings settings;

    public InstructionDiagramConfigurable() {
        this.settingsService = ApplicationManager.getApplication().getService(InstructionDiagramSettingsService.class);
    }

    @Override
    public @Nls String getDisplayName() {
        return JALMessages.message("jal.instructionDiagram.settings.displayName");
    }

    @Override
    public @Nullable JComponent createComponent() {
        this.settings = this.settingsService.getSettings();
        this.panel = new InstructionDiagramSettingsPanel(this.settings, updated -> this.settings = updated);
        return this.panel.getComponent();
    }

    @Override
    public boolean isModified() {
        return this.panel != null && !Objects.equals(this.settingsService.getSettings(), this.settings);
    }

    @Override
    public void apply() {
        if (this.panel == null)
            return;
        this.settingsService.setSettings(this.settings);
        for (Project project : ProjectManager.getInstance().getOpenProjects()) {
            InstructionDiagramController controller = project.getService(InstructionDiagramController.class);
            if (controller != null)
                controller.reloadSettings();
        }
    }

    @Override
    public void reset() {
        if (this.panel == null)
            return;
        this.settings = this.settingsService.getSettings();
        this.panel.setSettings(this.settings);
    }

    @Override
    public void disposeUIResources() {
        this.panel = null;
    }
}
