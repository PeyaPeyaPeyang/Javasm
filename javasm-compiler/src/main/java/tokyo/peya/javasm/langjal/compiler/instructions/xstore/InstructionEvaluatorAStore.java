package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorAStore extends AbstractInstructionEvaluator<JALParser.JvmInsAstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsAstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.ASTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                "A",
                "astore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsAstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAstore();
    }
}
