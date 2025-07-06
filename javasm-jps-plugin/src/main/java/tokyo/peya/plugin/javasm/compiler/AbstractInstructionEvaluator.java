package tokyo.peya.plugin.javasm.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public abstract class AbstractInstructionEvaluator<T extends ParserRuleContext>
{
    @NotNull
    protected abstract EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContxt, @NotNull T ctxt);

    @Nullable
    protected abstract T map(@NotNull JALParser.InstructionContext instruction);


    public EvaluatedInstruction evaluate(@NotNull EvaluatingContext evalContext, @NotNull JALParser.InstructionContext instruction)
    {
        if (!isApplicable(instruction))
            throw new IllegalArgumentException("Instruction is not applicable: " + instruction.getText());

        T mappedContext = map(instruction);
        if (mappedContext == null)
            throw new IllegalArgumentException("Mapped context is null for instruction: " + instruction.getText());

        return evaluate(evalContext, mappedContext);
    }

    public boolean isApplicable(@NotNull JALParser.InstructionContext instruction)
    {
        return map(instruction) != null;
    }

    public static EvaluatedInstruction visitSingle(int opCode)
    {
        EvaluatedInstruction inst = new EvaluatedInstruction(opCode);
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
