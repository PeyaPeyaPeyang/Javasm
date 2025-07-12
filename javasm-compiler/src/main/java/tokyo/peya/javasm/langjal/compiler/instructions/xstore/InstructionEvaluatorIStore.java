package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorIStore extends AbstractInstructionEvaluator<JALParser.JvmInsIstoreContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsIstoreContext ctxt)
    {
        return InstructionEvaluateHelperXStore.evaluate(
                EOpcodes.ISTORE,
                compiler,
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
