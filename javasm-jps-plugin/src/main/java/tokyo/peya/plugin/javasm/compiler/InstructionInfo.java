package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;

public record InstructionInfo(
        @NotNull
        AbstractInsnNode insn,
        int bytecodeOffset,
        @Nullable
        LabelInfo assignedLabel,
        int instructionSize
)
{
    public InstructionInfo(int opcode, int bytecodeOffset, @Nullable LabelInfo assignedLabel, int instructionSize)
    {
        this(new InsnNode(opcode), bytecodeOffset, assignedLabel, instructionSize);
    }

    public int opcode()
    {
        return this.insn.getOpcode();
    }
}
