package tokyo.peya.plugin.javasm.compiler.instructions.field;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorPutStatic extends AbstractInstructionEvaluator<JALParser.JvmInsPutstaticContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsPutstaticContext ctxt)
    {
        return InstructionEvaluateHelperField.evaluate(ctxt.jvmInsArgFieldRef(), EOpcodes.PUTSTATIC);
    }

    @Override
    protected JALParser.JvmInsPutstaticContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsPutstatic();
    }
}
