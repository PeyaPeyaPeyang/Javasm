package tokyo.peya.javasm.intellij.instructiondiagram;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.*;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.dependency.InstructionDependencyAnalysisResult;
import tokyo.peya.javasm.intellij.dependency.InstructionDependencyEntry;
import tokyo.peya.javasm.intellij.dependency.InstructionDependencyService;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import javax.swing.*;

@Service(Service.Level.PROJECT)
public final class InstructionDiagramController implements FileEditorManagerListener, DocumentListener,
        EditorFactoryListener, CaretListener, Disposable {
    public static final Key<Object> EVENT_LISTENER_CONNECTED_KEY =
            Key.create(InstructionDiagramController.class.getName() + ".eventListenerConnected");

    private static final Object MARKER = new Object();
    private final Project project;
    private final InstructionDiagramPanelFactory uiFactory;
    private final InstructionDependencyService dependencyService;
    private final InstructionDiagramSettingsService settingsService;
    private final Timer autoRefreshTimer;
    private boolean analysisRunning;
    private boolean refreshRequested;

    public InstructionDiagramController(@NotNull Project project) {
        this.project = project;
        this.dependencyService = InstructionDependencyService.getInstance(project);
        this.settingsService = ApplicationManager.getApplication().getService(InstructionDiagramSettingsService.class);
        InstructionDiagramSettings settings = this.settingsService.getSettings();
        this.uiFactory = new InstructionDiagramPanelFactory(
                this::refreshDependencies,
                this::navigateTo,
                settings
        );
        this.autoRefreshTimer = new Timer(settings.autoRefreshDelayMs(), ignored -> this.refreshDependencies());
        this.autoRefreshTimer.setRepeats(false);
        this.project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, this);
    }

    public static InstructionDiagramController getInstance(@NotNull Project project) {
        return project.getService(InstructionDiagramController.class);
    }

    public JPanel getMainPanel() {
        return this.uiFactory.getMainPanel();
    }

    public void refreshDependencies() {
        if (this.analysisRunning) {
            this.refreshRequested = true;
            return;
        }
        this.analysisRunning = true;
        this.uiFactory.onAnalysisStarted();
        new Task.Backgroundable(this.project, JALMessages.message("jal.instructionDiagram.analysis.progress"), true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                Editor editor = FileEditorManager.getInstance(InstructionDiagramController.this.project)
                        .getSelectedTextEditor();
                if (editor == null) {
                    InstructionDiagramController.this.uiFactory.onAnalysisFailed(
                            JALMessages.message("jal.instructionDiagram.status.noActiveEditor")
                    );
                    InstructionDiagramController.this.onAnalysisCompleted();
                    return;
                }

                InstructionDependencyAnalysisResult result =
                        InstructionDiagramController.this.computeDependencies(editor);
                if (result == null) {
                    InstructionDiagramController.this.uiFactory.onAnalysisFailed(
                            JALMessages.message("jal.instructionDiagram.status.analysisFailed")
                    );
                    InstructionDiagramController.this.onAnalysisCompleted();
                    return;
                }

                InstructionDiagramController.this.uiFactory.onAnalysisFinished(
                        result,
                        InstructionDiagramController.this.settingsService.getSettings().resetViewOnRefresh()
                );
                InstructionDiagramController.this.onAnalysisCompleted();
            }
        }.queue();
    }

    public void reloadSettings() {
        InstructionDiagramSettings settings = this.settingsService.getSettings();
        this.autoRefreshTimer.setInitialDelay(settings.autoRefreshDelayMs());
        this.autoRefreshTimer.setDelay(settings.autoRefreshDelayMs());
        if (!settings.autoRefreshEnabled())
            this.autoRefreshTimer.stop();
        this.uiFactory.applySettings(settings);
    }

    private void onAnalysisCompleted() {
        this.analysisRunning = false;
        if (!this.refreshRequested)
            return;
        this.refreshRequested = false;
        if (InstructionDiagramToolWindowFactory.isVisible(this.project))
            this.scheduleAutoRefresh();
    }

    private void scheduleAutoRefresh() {
        ApplicationManager.getApplication().invokeLater(() -> {
            if (this.project.isDisposed())
                return;
            if (!this.settingsService.getSettings().autoRefreshEnabled()) {
                this.autoRefreshTimer.stop();
                return;
            }
            if (!InstructionDiagramToolWindowFactory.isVisible(this.project))
                return;
            this.autoRefreshTimer.restart();
        });
    }

    private InstructionDependencyAnalysisResult computeDependencies(@NotNull Editor editor) {
        JALFile file = this.getJalFile(editor);
        if (file == null)
            return null;
        return this.dependencyService.reanalyze(file);
    }

    private @Nullable JALFile getJalFile(@NotNull Editor editor) {
        return ApplicationManager.getApplication().runReadAction((Computable<JALFile>) () -> {
            PsiFile file = PsiDocumentManager.getInstance(this.project).getPsiFile(editor.getDocument());
            return file instanceof JALFile jalFile ? jalFile : null;
        });
    }

    private void navigateTo(@NotNull InstructionDependencyEntry entry) {
        ApplicationManager.getApplication().runReadAction(() -> {
            Editor editor = FileEditorManager.getInstance(this.project).getSelectedTextEditor();
            if (editor == null)
                return;

            PsiFile psiFile = PsiDocumentManager.getInstance(this.project).getPsiFile(editor.getDocument());
            if (!(psiFile instanceof JALFile jalFile))
                return;

            for (InstructionNode instructionNode : PsiTreeUtil.findChildrenOfType(jalFile, InstructionNode.class)) {
                MethodDefinitionNode methodNode = PsiTreeUtil.getParentOfType(instructionNode, MethodDefinitionNode.class);
                if (methodNode == null)
                    continue;
                if (!entry.methodName().equals(methodNode.getMethodName()))
                    continue;
                if (!entry.methodDescriptor().equals(methodNode.getMethodDescriptor().getDescriptorString()))
                    continue;

                int offset;
                try {
                    offset = instructionNode.getStartInstructionOffset();
                } catch (IllegalStateException e) {
                    continue;
                }

                if (offset != entry.instructionOffset())
                    continue;

                int textOffset = instructionNode.getTextOffset();
                editor.getCaretModel().moveToOffset(textOffset);
                editor.getScrollingModel().scrollToCaret(com.intellij.openapi.editor.ScrollType.CENTER);
                return;
            }
        });
    }

    private void connectEventListener(@NotNull Editor editor) {
        if (editor.getUserData(EVENT_LISTENER_CONNECTED_KEY) != null)
            return;
        try {
            editor.getDocument().addDocumentListener(this, this);
            editor.getCaretModel().addCaretListener(this, this);
        } catch (Exception e) {
            return;
        }
        editor.putUserData(EVENT_LISTENER_CONNECTED_KEY, MARKER);
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        this.uiFactory.onEditorSelectionChanged();

        FileEditor newEditor = event.getNewEditor();
        if (!(newEditor instanceof TextEditor textEditor)) {
            InstructionDiagramToolWindowFactory.closeAuto(this.project);
            return;
        }

        Editor editor = textEditor.getEditor();
        this.connectEventListener(editor);
        PsiFile file = PsiDocumentManager.getInstance(this.project).getPsiFile(editor.getDocument());
        if (!(file instanceof JALFile)) {
            InstructionDiagramToolWindowFactory.closeAuto(this.project);
            return;
        }

        InstructionDiagramToolWindowFactory.openAuto(this.project);
        InstructionDependencyAnalysisResult result = this.dependencyService.getCached((JALFile) file);
        if (result != null) {
            this.uiFactory.onAnalysisFinished(result, this.settingsService.getSettings().resetViewOnRefresh());
            return;
        }
        this.refreshDependencies();
    }

    @Override
    public void documentChanged(@NotNull DocumentEvent event) {
        Editor editor = FileEditorManager.getInstance(this.project).getSelectedTextEditor();
        if (editor == null)
            return;
        JALFile file = this.getJalFile(editor);
        if (file != null)
            this.dependencyService.clearCached(file);
        this.uiFactory.onEditorContentChanged();
        if (InstructionDiagramToolWindowFactory.isVisible(this.project))
            this.scheduleAutoRefresh();
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event) {
    }

    @Override
    public void dispose() {
        this.autoRefreshTimer.stop();
    }
}
