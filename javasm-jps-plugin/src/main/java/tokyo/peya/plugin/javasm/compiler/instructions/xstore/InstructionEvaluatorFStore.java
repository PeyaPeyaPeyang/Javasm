package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFStore extends AbstractInstructionEvaluator<JALParser.JvmInsFstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator, JALParser.@NotNull JvmInsFstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.FSTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                "F",
                "fstore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsFstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFstore();
    }
}
