package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFStore extends AbstractInstructionEvaluator<JALParser.JvmInsFstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsFstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.FSTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                ctxt.localInstigation(),
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
