package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorLStore extends AbstractInstructionEvaluator<JALParser.JvmInsLstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.LSTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                "L",
                "lstore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsLstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLstore();
    }
}
