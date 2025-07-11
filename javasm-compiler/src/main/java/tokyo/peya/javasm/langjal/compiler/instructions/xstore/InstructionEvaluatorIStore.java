package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIStore extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsIstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.ISTORE,
                evaluator,
                ctxt.jvmInsArgLocalRef(),
                ctxt.localInstigation(),
                "I",
                "istore",
                ctxt.INSN_WIDE()
        );
    }

    @Override
    protected JALParser.JvmInsIstoreContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIstore();
    }
}
