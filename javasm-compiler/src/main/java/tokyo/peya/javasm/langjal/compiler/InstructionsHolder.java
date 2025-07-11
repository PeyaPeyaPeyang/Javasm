package tokyo.peya.javasm.langjal.compiler;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

public class InstructionsHolder
{
    private final LabelsHolder labels;
    private final List<InstructionInfo> instructions;

    @Getter
    private int bytecodeOffset;

    public InstructionsHolder(@NotNull LabelsHolder labels)
    {
        this.labels = labels;

        this.instructions = new ArrayList<>();
    }

    public int getSize()
    {
        return this.instructions.size();
    }

    public InstructionInfo addInstruction(@NotNull InsnNode insn)
    {
        InstructionInfo instruction = new InstructionInfo(
                insn,
                this.bytecodeOffset,
                this.labels.getCurrentLabel(),
                EOpcodes.getOpcodeSize(insn.getOpcode())
        );
        this.instructions.add(instruction);
        this.bytecodeOffset += instruction.instructionSize();
        return instruction;
    }

    public InstructionInfo addInstruction(@NotNull EvaluatedInstruction evaluatedInstruction)
    {
        InstructionInfo instruction = new InstructionInfo(
                evaluatedInstruction.insn(),
                this.bytecodeOffset,
                this.labels.getCurrentLabel(),
                evaluatedInstruction.getInstructionSize()
        );
        this.instructions.add(instruction);
        this.bytecodeOffset += instruction.instructionSize();
        return instruction;
    }

    public void finaliseInstructions(@NotNull MethodNode method)
    {
        for (InstructionInfo instruction : this.instructions)
        {
            if (instruction.assignedLabel() != null)  // 命令にラベルが割り当てられている場合
                method.instructions.add(instruction.assignedLabel().node());

            method.instructions.add(instruction.insn());
        }
    }

    public boolean isEmpty()
    {
        return this.instructions.isEmpty();
    }

    @Nullable
    public InstructionInfo getInstruction(int index)
    {
        if (index < 0 || index >= this.instructions.size())
            return null; // インデックスが範囲外の場合はnullを返す
        return this.instructions.get(index);
    }

    @NotNull
    public InstructionInfo getLastInstruction()
    {
        if (this.instructions.isEmpty())
            throw new IllegalStateException("No instructions available");
        return this.instructions.get(this.instructions.size() - 1);
    }
}
