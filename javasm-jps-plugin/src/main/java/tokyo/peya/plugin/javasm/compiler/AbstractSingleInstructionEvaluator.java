package tokyo.peya.plugin.javasm.compiler;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractSingleInstructionEvaluator<T extends ParserRuleContext> extends AbstractInstructionEvaluator<T>
{
    private final int opcode;

    public AbstractSingleInstructionEvaluator(int opcode)
    {
        this.opcode = opcode;
    }

    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, @NotNull T ctxt)
    {
        return visitSingle(this.opcode);
    }
}
