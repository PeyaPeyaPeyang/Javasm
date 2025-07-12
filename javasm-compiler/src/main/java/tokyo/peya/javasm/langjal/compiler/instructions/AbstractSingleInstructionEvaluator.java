package tokyo.peya.javasm.langjal.compiler.instructions;

import org.antlr.v4.runtime.ParserRuleContext;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public abstract class AbstractSingleInstructionEvaluator<T extends ParserRuleContext>
        extends AbstractInstructionEvaluator<T>
{
    private final int opcode;

    public AbstractSingleInstructionEvaluator(int opcode)
    {
        this.opcode = opcode;
    }

    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler, @NotNull T ctxt)
    {
        return visitSingle(this.opcode);
    }
}
