package tokyo.peya.javasm.langjal.compiler.member;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;

public record InstructionInfo(
        @NotNull
        AbstractInstructionEvaluator<?> evaluator,
        @NotNull
        AbstractInsnNode insn,
        int bytecodeOffset,
        @Nullable
        LabelInfo assignedLabel,
        int instructionSize
)
{
    public InstructionInfo(AbstractInstructionEvaluator<?> evaluator, int opcode, int bytecodeOffset,
                           @Nullable LabelInfo assignedLabel, int instructionSize)
    {
        this(evaluator, new InsnNode(opcode), bytecodeOffset, assignedLabel, instructionSize);
    }

    public int opcode()
    {
        return this.insn.getOpcode();
    }
}
