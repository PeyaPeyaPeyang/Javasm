package tokyo.peya.javasm.intellij.stackviewer;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import javax.swing.JPanel;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

import java.util.Collection;

@Service(Service.Level.PROJECT)
public final class StackFrameInfoController
        implements FileEditorManagerListener, DocumentListener, EditorFactoryListener,
        CaretListener, Disposable
{
    public static final Key<StackFrameAnalysisResult> ANALYSIS_RESULT_KEY =
            Key.create(StackFrameInfoController.class.getName() + ".analysisResult");
    public static final Key<Object> EVENT_LISTENER_CONNECTED_KEY =
            Key.create(StackFrameInfoController.class.getName() + ".eventListenerConnected");
    public static final Key<Object> EDITOR_CHANGED_MARKER_KEY =
            Key.create(StackFrameInfoController.class.getName() + ".editorChangedMarker");
    private static final Object MAKER = new Object();

    private final Project project;
    private final StackFramePanelFactory uiFactory;

    public StackFrameInfoController(@NotNull Project project)
    {
        this.project = project;
        this.uiFactory = new StackFramePanelFactory(this::onAnalyseButtonPushed);

        // エディタの変更を監視するためにリスナーを登録する。
        this.project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, this);
    }

    private void onAnalyseButtonPushed()
    {
        new Task.Backgroundable(null, "Analyzing stack frame", true)
        {
            @Override
            public void run(@NotNull ProgressIndicator indicator)
            {
                // 現在のエディタを取得し， スタックフレームの解析を行う。
                Editor currentEditor = FileEditorManager.getInstance(StackFrameInfoController.this.project)
                                                        .getSelectedTextEditor();
                if (currentEditor == null)
                {
                    this.onCancel();
                    return;
                }

                StackFrameAnalysisResult result = StackFrameInfoController.this.computeStackAndFrame(currentEditor);
                if (result == null)
                    this.onCancel();
                else
                    StackFrameInfoController.this.onAnalyseFinished(result);
            }

            @Override
            public void onCancel()
            {
                ApplicationManager.getApplication()
                                  .invokeLater(StackFrameInfoController.this.uiFactory::onUpdateCancelled);
            }
        }.queue();
    }

    private void onAnalyseFinished(@NotNull StackFrameAnalysisResult result)
    {
        ApplicationManager.getApplication().invokeLater(this.uiFactory::onUpdateFinished);

        // 最初の結果 = 0 番地のスタックフレームを表示する。
        Collection<MethodWrapper> methods = result.getMethods();
        if (methods.isEmpty())
        {
            ApplicationManager.getApplication().invokeLater(this.uiFactory::onUpdateCancelled);
            return;
        }

        MethodWrapper firstMethod = methods.iterator().next();
        Collection<InstructionUIElement> instructions =
                result.getInstructions(firstMethod.method().name, firstMethod.method().desc);
        if (instructions.isEmpty())
        {
            ApplicationManager.getApplication().invokeLater(this.uiFactory::onUpdateCancelled);
            return;
        }

        InstructionUIElement firstInstruction = instructions.iterator().next();
        this.showStackFrameInfo(false, firstInstruction);
    }

    private void showStackFrameInfo(boolean isAfterChanged, @NotNull InstructionUIElement instruction)
    {
        StackUIInstruction stackInstruction = instruction.instruction();
        StackUIElement[] stackElements = instruction.stack().toArray(new StackUIElement[0]);
        StackUIElement[] localVariables = instruction.locals().toArray(new StackUIElement[0]);


        ApplicationManager.getApplication().invokeLater(() -> this.uiFactory.updateStackFrameInfo(
                isAfterChanged, stackInstruction, stackElements, localVariables
        ));
    }

    private StackFrameAnalysisResult computeStackAndFrame(@NotNull Editor currentEditor)
    {
        Document document = currentEditor.getDocument();
        PsiFile file = PsiDocumentManager.getInstance(this.project).getPsiFile(document);
        if (!(file instanceof JALFile))
            return null;

        currentEditor.putUserData(EDITOR_CHANGED_MARKER_KEY, null);  // ドキュメントが変更されたことを示すマーカーを設定する。
        String editorContent = document.getText();
        StackFramePanelFactory.ProgressbarUpdater updater = this.getUpdater();
        StackFrameInfoComputer computer = new StackFrameInfoComputer(editorContent, updater);

        if (!computer.computeStackFrames())
            return null;

        // 解析結果をエディタに保存する。
        StackFrameAnalysisResult result = computer.getAnalysisResult();
        currentEditor.putUserData(ANALYSIS_RESULT_KEY, result);

        return result;
    }

    private StackFramePanelFactory.ProgressbarUpdater getUpdater()
    {
        return this.uiFactory.getUpdater();
    }

    @Override
    public void caretPositionChanged(@NotNull CaretEvent event)
    {
        // キャレットの位置が変更されたときに， スタックフレームの情報を更新する。
        Editor editor = event.getEditor();
        StackFrameAnalysisResult result = editor.getUserData(ANALYSIS_RESULT_KEY);
        if (result == null)
            return;

        // 現在の行のスタックフレームを表示する。
        this.showCurrentLineStackFrame(editor);
    }

    private void showCurrentLineStackFrame(@NotNull Editor editor)
    {
        int currentLine = editor.getCaretModel().getLogicalPosition().line;
        StackFrameAnalysisResult result = editor.getUserData(ANALYSIS_RESULT_KEY);
        if (result == null)
            return;

        JALFile jalFile = JALFile.getJALFile(this.project, editor.getVirtualFile());
        if (jalFile == null)
            return;
        PsiElement element = jalFile.findInstructionRelatedElement(currentLine);
        if (!(element instanceof InstructionNode node))
            return;

        MethodDefinitionNode methodNode = PsiTreeUtil.getParentOfType(node, MethodDefinitionNode.class);
        if (methodNode == null)
            return;

        String methodName = methodNode.getMethodName();
        String methodDesc = methodNode.getMethodDescriptor().getDescriptorString();
        int offset = node.getStartInstructionOffset();

        // スタックフレームの情報を取得する。
        InstructionUIElement instruction = result.getInstructionAt(methodName, methodDesc, offset);
        if (instruction == null)
            return;

        boolean isAfterChanged = editor.getUserData(EDITOR_CHANGED_MARKER_KEY) != null;

        // スタックフレームの情報を表示する。
        this.showStackFrameInfo(isAfterChanged, instruction);
    }

    private void connectEventListener(@NotNull Editor editor)
    {
        if (editor.getUserData(EVENT_LISTENER_CONNECTED_KEY) != null)
            return;

        editor.getDocument().addDocumentListener(this, this);
        editor.getCaretModel().addCaretListener(this, this);
        editor.putUserData(EVENT_LISTENER_CONNECTED_KEY, MAKER);
    }

    @Override
    public void selectionChanged(@NotNull FileEditorManagerEvent event)
    {
        // エディタの選択が変更されたときに， スタックフレームの情報を更新する。
        this.uiFactory.onEditorSelectionChanged();

        FileEditor newEditor = event.getNewEditor();
        if (!(newEditor instanceof TextEditor texteditor))
        {
            StackFrameInfoToolWindowFactory.closeAuto(this.project);
            return;
        }
        Editor editor = texteditor.getEditor();
        this.connectEventListener(editor);

        PsiFile file = PsiDocumentManager.getInstance(this.project)
                                         .getPsiFile(editor.getDocument());
        if (!(file instanceof JALFile))
        {
            StackFrameInfoToolWindowFactory.closeAuto(this.project);
            return;
        }

        // 自動的に閉じられた場合に，自動的に開く
        StackFrameInfoToolWindowFactory.openAuto(this.project);
        StackFrameAnalysisResult result = editor.getUserData(ANALYSIS_RESULT_KEY);
        if (result == null)
            return;

        // 解析結果がある場合は， スタックフレームの情報を表示する。
        this.showCurrentLineStackFrame(editor);
    }

    @Override
    public void documentChanged(@NotNull DocumentEvent event)
    {
        // ドキュメントが変更されたときに， スタックフレームの再解析を要求する
        Editor editor = FileEditorManager.getInstance(this.project).getSelectedTextEditor();
        if (editor == null)
            return;

        editor.putUserData(EDITOR_CHANGED_MARKER_KEY, MAKER);
        StackFrameAnalysisResult result = editor.getUserData(ANALYSIS_RESULT_KEY);
        if (result == null)
            return;

        this.uiFactory.onEditorContentChanged();
    }

    public JPanel getMainPanel()
    {
        return this.uiFactory.getMainPanel();
    }

    @Override
    public void dispose()
    {
    }

    public static StackFrameInfoController getInstance(Project project)
    {
        return project.getService(StackFrameInfoController.class);
    }
}
