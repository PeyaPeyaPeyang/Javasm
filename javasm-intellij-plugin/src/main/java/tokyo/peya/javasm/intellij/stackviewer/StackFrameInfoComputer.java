package tokyo.peya.javasm.intellij.stackviewer;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MethodNode;
import tokyo.peya.javasm.langjal.compiler.CompileSettings;
import tokyo.peya.javasm.langjal.compiler.JALClassCompiler;
import tokyo.peya.javasm.langjal.compiler.JALFileCompiler;
import tokyo.peya.javasm.langjal.compiler.analyser.AnalysedInstruction;
import tokyo.peya.javasm.langjal.compiler.analyser.FramePropagation;
import tokyo.peya.javasm.langjal.compiler.analyser.MethodAnalysisResult;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.LocalStackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.ObjectElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.CompileErrorException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.ClassAnalyseException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

import java.util.ArrayList;
import java.util.List;

public class StackFrameInfoComputer
{
    private final String content;
    private final StackFramePanelFactory.ProgressbarUpdater updater;
    private final List<MethodAnalysisResult> methodAnalysisResults;
    @Getter
    private final Multimap<MethodWrapper, InstructionUIElement> instructions;
    private JALClassCompiler compiler;

    public StackFrameInfoComputer(@NotNull String content,
                                  @NotNull StackFramePanelFactory.ProgressbarUpdater updater)
    {
        this.content = content;
        this.updater = updater;
        this.methodAnalysisResults = new ArrayList<>();
        this.instructions = ArrayListMultimap.create();
    }

    public StackFrameAnalysisResult getAnalysisResult()
    {
        return new StackFrameAnalysisResult(this.instructions);
    }

    public boolean computeStackFrames()
    {
        boolean succeed = this.compile();
        if (!succeed)
            return false;
        this.updater.updateStatus("Compilation succeeded. Analyzing methods...");
        succeed = this.analyseMethods();
        if (!succeed)
            return false;
        this.updater.updateStatus("Method analysis succeeded. Creating UI caches...");
        this.createUICaches();
        this.updater.updateStatus("UI caches created successfully.");
        return true;
    }

    private boolean compile()
    {
        InlineCompileReporter reporter = new InlineCompileReporter(this.updater::updateStatus);
        try
        {
            this.compiler = JALFileCompiler.compileOnly(this.content, reporter, CompileSettings.NONE);
            return true;
        }
        catch (CompileErrorException e)
        {
            this.updater.updateStatus(
                    "CE: " + e.getDetailedMessage() + " at " + e.getLine() + ":" + e.getColumn()
            );
            return false;
        }
    }

    private boolean analyseMethods()
    {
        if (this.compiler == null)
            throw new IllegalStateException("ClassNode is not initialized. Call #compile() first.");

        for (JALMethodCompiler methodCompiler : this.compiler.getMethodCompilers())
        {
            MethodNode methodNode = methodCompiler.getMethod();
            this.updater.updateStatus("Analysing method: " + methodNode.name + methodNode.desc);
            try
            {
                MethodAnalysisResult analysisResult = methodCompiler.analyseMethod();
                this.methodAnalysisResults.add(analysisResult);
            }
            catch (ClassAnalyseException e)
            {
                this.updater.updateStatus("Analyse failed: " + e.getDetailedMessage());
                return false;
            }
        }

        return true;
    }

    private void createUICaches()
    {
        if (this.methodAnalysisResults.isEmpty())
            throw new IllegalStateException("Method analysis results are empty. Call #analyseMethods() first.");

        for (MethodAnalysisResult result : this.methodAnalysisResults)
            this.createMethodUICache(result);
    }

    private void createMethodUICache(@NotNull MethodAnalysisResult methodAnalysis)
    {
        MethodWrapper methodWrapper = new MethodWrapper(methodAnalysis.node());
        FramePropagation[] propagations = methodAnalysis.propagations();

        boolean isFirst = true;
        StackElement[] lastStack = null;
        LocalStackElement[] lastLocals = null;
        List<Integer> analysedOffsets = new ArrayList<>();
        for (FramePropagation propagation : propagations)
        {
            if (isFirst)
            {
                isFirst = false;
                lastStack = propagation.stack();
                lastLocals = propagation.locals();
                continue;
            }

            AnalysedInstruction[] analysedInstructions = propagation.analysed();
            for (AnalysedInstruction analysedInstruction : analysedInstructions)
            {
                int instructionOffset = analysedInstruction.instruction().bytecodeOffset();
                if (analysedOffsets.contains(instructionOffset))
                    continue;  // 既に処理済みのオフセットはスキップ
                analysedOffsets.add(instructionOffset);

                StackElement[] stackSnapshot = analysedInstruction.stackSnapshot();
                LocalStackElement[] localSnapshot = analysedInstruction.localSnapshot();

                StackUIInstruction instructionUI = createStackUIInstruction(analysedInstruction);
                List<StackUIElement> stackUIElements = createStackStackUIElements(stackSnapshot, lastStack);
                List<StackUIElement> localsUIElements = createLocalStackUIElements(localSnapshot, lastLocals);

                InstructionUIElement instructionUIElement = new InstructionUIElement(
                        instructionOffset,
                        instructionUI,
                        stackUIElements,
                        localsUIElements
                );
                this.instructions.put(methodWrapper, instructionUIElement);

                // 最後のスタックとローカル変数を更新
                lastStack = stackSnapshot;
                lastLocals = localSnapshot;
            }
        }
    }

    private static List<StackUIElement> createStackStackUIElements(
            @NotNull StackElement[] stack,
            @NotNull StackElement[] lastStack
    )
    {
        List<StackUIElement> elements = new ArrayList<>();
        int lastStackSize = lastStack.length;
        int currentIndex = 0;
        int minIndex = Math.min(stack.length, lastStackSize);
        for (int i = 0; i < minIndex; i++)
        {
            StackElement currentElement = stack[i];
            StackElement lastElement = lastStack[i];

            StackUIElement.DisplayType type;
            if (currentElement.type() == lastElement.type())
                type = StackUIElement.DisplayType.UNCHANGING;
            else if (lastElement.type() == StackElementType.TOP)
                type = StackUIElement.DisplayType.PUSH;
            else if (currentElement.type() == StackElementType.TOP)
                type = StackUIElement.DisplayType.POP;
            else
                type = StackUIElement.DisplayType.PUSH;  // CHANGE が妥当だが，ここでは PUSH とする。

            StackUIElement uiElement = createUIElement(type, currentElement);
            elements.add(uiElement);
            currentIndex++;
        }
        // 現在のスタックの残りの要素を PUSH として追加
        for (int i = currentIndex; i < stack.length; i++)
        {
            StackElement currentElement = stack[i];
            StackUIElement uiElement = createUIElement(StackUIElement.DisplayType.PUSH, currentElement);
            elements.add(uiElement);
        }

        return elements;
    }

    private static List<StackUIElement> createLocalStackUIElements(
            @NotNull LocalStackElement[] locals,
            @NotNull LocalStackElement[] lastLocals
    )
    {
        List<StackUIElement> elements = new ArrayList<>();
        for (LocalStackElement element : locals)
        {
            StackUIElement.DisplayType type;
            int localIndex = element.index();
            if (localIndex >= lastLocals.length)
                type = StackUIElement.DisplayType.PUSH;
            else
            {
                LocalStackElement lastLocal = lastLocals[localIndex];
                if (lastLocal.type() == element.type())
                    type = StackUIElement.DisplayType.UNCHANGING;
                else if (lastLocal.type() == StackElementType.TOP)
                    type = StackUIElement.DisplayType.PUSH;
                else if (element.type() == StackElementType.TOP)
                    type = StackUIElement.DisplayType.POP;
                else
                    type = StackUIElement.DisplayType.PUSH;  // CHANGE が妥当だが，ここでは PUSH とする。
            }

            StackUIElement uiElement = createUIElement(type, element.stackElement());
            elements.add(uiElement);
        }
        return elements;
    }

    private static StackUIElement createUIElement(@NotNull StackUIElement.DisplayType type, @NotNull StackElement elm)
    {
        if (elm instanceof ObjectElement objectElement)
        {
            String objectName = objectElement.content().toString();
            return StackUIColorPalette.toUIObjectElement(
                    type,
                    objectName
            );  // Object は特別なので static#toUIObjectElement を使う
        }
        else
        {
            StackUIColorPalette colorPalette = StackUIColorPalette.fromStackElementType(elm.type());
            return colorPalette.toUIElement(type);
        }
    }

    private static StackUIInstruction createStackUIInstruction(@NotNull AnalysedInstruction analysedInstruction)
    {
        int opcode = analysedInstruction.instruction().opcode();
        String instructionName = EOpcodes.getName(opcode);
        int bytecodeOffset = analysedInstruction.instruction().bytecodeOffset();
        return new StackUIInstruction(instructionName, bytecodeOffset);
    }
}
