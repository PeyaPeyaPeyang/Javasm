package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

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
                ctxt.localInstigation(),
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
