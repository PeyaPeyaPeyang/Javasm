package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.instructions.AbstractSingleInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;

public class InstructionEvaluatorAReturn extends AbstractSingleInstructionEvaluator<JALParser.JvmInsAreturnContext>
{
    public InstructionEvaluatorAReturn()
    {
        super(EOpcodes.ARETURN);
    }

    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsAreturnContext ctxt)
    {
        InstructionEvaluateHelperXReturn.checkReturnType(compiler.getMethod(), ctxt, TypeDescriptor.OBJECT);
        return super.evaluate(compiler, ctxt);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popObjectRef()
                                  .build();
    }

    @Override
    protected JALParser.JvmInsAreturnContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsAreturn();
    }
}
