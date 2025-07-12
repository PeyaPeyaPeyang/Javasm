package tokyo.peya.javasm.langjal.compiler.instructions;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.InsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public abstract class AbstractInstructionEvaluator<T extends ParserRuleContext>
{
    @NotNull
    protected abstract EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler, @NotNull T ctxt);

    @Nullable
    protected abstract T map(@NotNull JALParser.InstructionContext instruction);

    public EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                         @NotNull JALParser.InstructionContext instruction)
    {
        if (!isApplicable(instruction))
            throw new IllegalArgumentException("Instruction is not applicable: " + instruction.getText());

        T mappedContext = map(instruction);
        if (mappedContext == null)
            throw new IllegalArgumentException("Mapped context is null for instruction: " + instruction.getText());

        return evaluate(compiler, mappedContext);
    }

    public boolean isApplicable(@NotNull JALParser.InstructionContext instruction)
    {
        return map(instruction) != null;
    }

    public static EvaluatedInstruction visitSingle(int opCode)
    {
        EvaluatedInstruction inst = EvaluatedInstruction.of(new InsnNode(opCode));
        if (inst.getInstructionSize() != 1)
            throw new IllegalArgumentException("Instruction size mismatch: expected 1, but got " +
                                                       inst.getInstructionSize() + " for opcode " + opCode);
        return inst;
    }

    protected static boolean has(@Nullable Object context)
    {
        return context != null;
    }
}
