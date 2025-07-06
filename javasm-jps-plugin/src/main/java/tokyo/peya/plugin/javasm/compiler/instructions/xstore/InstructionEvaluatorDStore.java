package tokyo.peya.plugin.javasm.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorDStore extends AbstractInstructionEvaluator<JALParser.JvmInsDstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsDstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.DSTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                "D",
                "dstore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsDstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsDstore();
    }
}
