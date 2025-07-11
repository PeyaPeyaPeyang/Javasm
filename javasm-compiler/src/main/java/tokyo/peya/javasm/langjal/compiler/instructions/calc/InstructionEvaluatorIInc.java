package tokyo.peya.javasm.langjal.compiler.instructions.calc;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.IincInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.LocalVariableInfo;

public class InstructionEvaluatorIInc extends AbstractInstructionEvaluator<JALParser.JvmInsIincContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsIincContext ctxt)
    {
        JALParser.JvmInsArgLocalRefContext ref = ctxt.jvmInsArgLocalRef();
        LocalVariableInfo local = evaluator.getLocals().resolve(ref, "iinc");

        int idx = local.index();
        boolean isWide = ctxt.INSN_WIDE() != null;
        int increment = EvaluatorCommons.asInteger(ctxt.NUMBER());

        if (!isWide)
        {
            if (idx >= 0xFF)
                throw new IllegalArgumentException(String.format(
                        "Local variable index %d is too large for iinc instruction. Use wide variant with.",
                        idx
                ));
            else if (increment < Byte.MIN_VALUE || increment > Byte.MAX_VALUE)
                throw new IllegalArgumentException(String.format(
                        "Increment value %d is out of range for iinc instruction. Use wide variant with.",
                        increment
                ));
        }

        int size = isWide ? 6: 3;
        IincInsnNode insn = new IincInsnNode(idx, increment);
        return EvaluatedInstruction.of(insn, size);
    }

    @Override
    protected JALParser.JvmInsIincContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsIinc();
    }
}
