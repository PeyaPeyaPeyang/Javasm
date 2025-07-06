package tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.compiler.AbstractSingleInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

public class InstructionEvaluatorFSub extends AbstractSingleInstructionEvaluator<JALParser.JvmInsFsubContext>
{
    public InstructionEvaluatorFSub()
    {
        super(EOpcodes.FSUB);
    }

    @Override
    protected JALParser.@NotNull JvmInsFsubContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsFsub();
    }
}
