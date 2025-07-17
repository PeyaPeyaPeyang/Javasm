package tokyo.peya.javasm.intellij.stackviewer;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MethodNode;
import tokyo.peya.javasm.langjal.compiler.CompileSettings;
import tokyo.peya.javasm.langjal.compiler.JALClassCompiler;
import tokyo.peya.javasm.langjal.compiler.JALFileCompiler;
import tokyo.peya.javasm.langjal.compiler.analyser.MethodAnalysisResult;
import tokyo.peya.javasm.langjal.compiler.exceptions.CompileErrorException;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.ClassAnalyseException;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

import java.util.ArrayList;
import java.util.List;

public class StackFrameInfoComputer
{
    private final String content;
    private final StackFramePanelFactory.ProgressbarUpdater updater;
    private final List<MethodAnalysisResult> methodAnalysisResults;
    private JALClassCompiler compiler;

    public StackFrameInfoComputer(@NotNull String content,
                                  @NotNull StackFramePanelFactory.ProgressbarUpdater updater)
    {
        this.content = content;
        this.updater = updater;
        this.methodAnalysisResults = new ArrayList<>();
    }

    public boolean compile()
    {
        InlineCompileReporter reporter = new InlineCompileReporter(this.updater::updateStatus);
        try
        {
            this.compiler = JALFileCompiler.compileOnly(this.content, reporter, CompileSettings.NONE);
            return true;
        }
        catch (CompileErrorException e)
        {
            this.updater.updateStatus("CE: " + e.getDetailedMessage() +
                                              " at " + e.getLine() + ":" + e.getColumn()
            );
            return false;
        }
    }

    public boolean analyseMethods()
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
}
