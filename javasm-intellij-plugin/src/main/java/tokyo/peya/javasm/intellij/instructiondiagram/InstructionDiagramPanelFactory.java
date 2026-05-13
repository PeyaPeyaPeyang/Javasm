package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.JBUI;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.dependency.InstructionDependencyAnalysisResult;
import tokyo.peya.javasm.intellij.dependency.InstructionDependencyEntry;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import javax.swing.*;
import java.awt.*;

public class InstructionDiagramPanelFactory {
    @Getter
    private final JPanel mainPanel;
    private final JBLabel statusLabel;
    private final JButton refreshButton;
    private final InstructionDiagramPanel diagramPanel;

    public InstructionDiagramPanelFactory(@NotNull Runnable onRefresh,
                                          @NotNull java.util.function.Consumer<InstructionDependencyEntry> onNavigate,
                                          @NotNull InstructionDiagramSettings settings) {
        this.statusLabel = new JBLabel(JALMessages.message("jal.instructionDiagram.status.selectFile"));
        this.refreshButton = new JButton(JALMessages.message("jal.instructionDiagram.refresh"));
        this.refreshButton.addActionListener(e -> onRefresh.run());
        this.diagramPanel = new InstructionDiagramPanel(onNavigate, settings);
        this.mainPanel = createMainPanel();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBorder(JBUI.Borders.empty(10));

        JPanel toolbar = new JPanel(new BorderLayout(8, 0));
        toolbar.add(this.statusLabel, BorderLayout.CENTER);
        toolbar.add(this.refreshButton, BorderLayout.EAST);
        panel.add(toolbar, BorderLayout.NORTH);
        panel.add(this.diagramPanel, BorderLayout.CENTER);
        return panel;
    }

    public void onEditorSelectionChanged() {
        ApplicationManager.getApplication().invokeLater(() ->
                this.statusLabel.setText(JALMessages.message("jal.instructionDiagram.status.editorChanged")));
    }

    public void onEditorContentChanged() {
        ApplicationManager.getApplication().invokeLater(() ->
                this.statusLabel.setText(JALMessages.message("jal.instructionDiagram.status.contentChanged")));
    }

    public void onAnalysisStarted() {
        ApplicationManager.getApplication().invokeLater(() -> {
            this.refreshButton.setEnabled(false);
            this.statusLabel.setText(JALMessages.message("jal.instructionDiagram.status.analyzing"));
        });
    }

    public void onAnalysisFailed(@NotNull String reason) {
        ApplicationManager.getApplication().invokeLater(() -> {
            this.refreshButton.setEnabled(true);
            this.statusLabel.setText(reason);
            this.diagramPanel.clear();
        });
    }

    public void onAnalysisFinished(@NotNull InstructionDependencyAnalysisResult result, boolean resetView) {
        ApplicationManager.getApplication().invokeLater(() -> {
            this.refreshButton.setEnabled(true);
            if (resetView)
                this.diagramPanel.resetView();
            this.diagramPanel.setAnalysisResult(result);
            this.statusLabel.setText(JALMessages.message("jal.instructionDiagram.status.updated"));
        });
    }

    public void applySettings(@NotNull InstructionDiagramSettings settings) {
        ApplicationManager.getApplication().invokeLater(() -> this.diagramPanel.applySettings(settings));
    }
}
