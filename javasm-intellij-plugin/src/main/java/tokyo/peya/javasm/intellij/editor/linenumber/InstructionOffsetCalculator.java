package tokyo.peya.javasm.intellij.editor.linenumber;

import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;

import java.util.HashMap;
import java.util.Map;

public class InstructionOffsetCalculator
{
    private static final Key<InstructionOffsetCalculator> KEY =
            Key.create("javasm.intellij.editor.linenumber.InstructionOffsetCalculator");

    private final Map<InstructionNode, Integer> instructionOffsets;

    private InstructionOffsetCalculator()
    {
        this.instructionOffsets = new HashMap<>();
    }

    private void buildOffsets(@NotNull MethodBodyNode methodBody)
    {
        long currentOffset = 0;
        InstructionSetNode[] instructionSets = methodBody.getInstructionSets();
        for (InstructionSetNode instructionSet : instructionSets)
        {
            InstructionNode[] instructions = instructionSet.getInstructions();
            for (InstructionNode instruction : instructions)
            {
                this.instructionOffsets.put(instruction, (int) currentOffset);
                currentOffset += instruction.getInstructionSize();
            }
        }
    }

    @Nullable
    public Integer getCumulativeOffset(@NotNull InstructionNode instruction)
    {
        return this.instructionOffsets.get(instruction);
    }

    public static InstructionOffsetCalculator get(@NotNull MethodBodyNode methodNode)
    {
        InstructionOffsetCalculator cached = methodNode.getUserData(KEY);
        if (cached != null)
            return cached;

        InstructionOffsetCalculator fresh;
        methodNode.putUserData(KEY, fresh = new InstructionOffsetCalculator());
        try
        {
            fresh.buildOffsets(methodNode);
        }
        catch (IllegalStateException ignored)
        {
            // これは，コードの記述が不完全だったり構文エラーのときにおきる。
        }
        return fresh;
    }

    public static void invalidate(@NotNull MethodBodyNode methodNode)
    {
        methodNode.putUserData(KEY, null);
    }
}
