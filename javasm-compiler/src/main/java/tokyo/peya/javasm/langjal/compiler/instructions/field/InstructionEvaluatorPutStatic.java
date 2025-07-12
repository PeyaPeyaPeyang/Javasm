package tokyo.peya.javasm.langjal.compiler.instructions.field;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorPutStatic extends AbstractInstructionEvaluator<JALParser.JvmInsPutstaticContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
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
