package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
