package tokyo.peya.javasm.langjal.compiler.member;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

public class LabelsHolder
{
    private final JALMethodCompiler methodEvaluator;
    private final List<LabelInfo> labels;

    @Getter
    private final LabelInfo globalStart;
    @Getter
    private final LabelInfo globalEnd;


    @Getter
    @Setter
    private LabelInfo currentLabel; // 現在解析中の最後のラベル

    public LabelsHolder(@NotNull JALMethodCompiler methodEvaluator)
    {
        this.methodEvaluator = methodEvaluator;

        this.labels = new ArrayList<>();

        this.globalStart = new LabelInfo("MBEGIN", new Label(), 0);
        this.globalEnd = new LabelInfo("MEND", new Label(), Integer.MAX_VALUE);
    }

    @NotNull
    public LabelInfo resolve(@NotNull String labelName)
    {
        LabelInfo resolvedLabel = this.resolveSafe(labelName);
        if (resolvedLabel == null)
            throw new IllegalArgumentException("Label '" + labelName + "' is not defined in this method.");

        return resolvedLabel;  // すでに登録されているラベルを返す
    }

    @Nullable
    public LabelInfo resolveSafe(@NotNull String labelName)
    {
        for (LabelInfo existingLabel : this.labels)
        {
            if (existingLabel.name().equals(labelName))
                return existingLabel;  // すでに登録されているラベルを返す
        }

        return null;  // ラベルが見つからない場合は null を返す
    }

    @NotNull
    public LabelInfo register(@NotNull String labelName, int instructionIndex)
    {
        LabelInfo existingLabel = this.resolveSafe(labelName);
        if (existingLabel != null)
            throw new IllegalArgumentException(
                    "Label '" + labelName + "' is already defined at instruction index "
                            + existingLabel.instructionIndex()
            );

        // 新しいラベルを登録
        Label newLabel = new Label();
        LabelInfo labelInfo = new LabelInfo(labelName, newLabel, instructionIndex);
        this.labels.add(labelInfo);

        // メソッドへの登録はあと
        return labelInfo;
    }

    @NotNull
    public LabelInfo register(@NotNull String labelName)
    {
        return this.register(labelName, this.currentInstructionIndex());
    }

    private int currentInstructionIndex()
    {
        return this.methodEvaluator.getInstructions().getSize();
    }

    public boolean isInScope(@NotNull LabelInfo scopeStart, @NotNull LabelInfo scopeEnd)
    {
        int startIndex = scopeStart.instructionIndex();
        int endIndex = scopeEnd.instructionIndex();
        int currentIndex = this.currentInstructionIndex();

        return currentIndex >= startIndex && currentIndex <= endIndex;
    }

    public void finalise(@NotNull MethodNode method)
    {
        LabelNode globalEndNode = this.globalEnd.node();
        method.instructions.add(globalEndNode);
        // ↑ END なので，いっちゃんさいご
    }

    public void initialise(@NotNull MethodNode method)
    {
        LabelNode globalStartNode = this.globalStart.node();
        method.instructions.add(globalStartNode);
    }
}
