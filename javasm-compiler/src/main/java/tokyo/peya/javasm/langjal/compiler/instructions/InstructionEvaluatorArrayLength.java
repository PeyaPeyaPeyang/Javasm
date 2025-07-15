package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.ObjectElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementCapsule;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.exceptions.analyse.StackElementMismatchedException;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public class InstructionEvaluatorArrayLength
        extends AbstractSingleInstructionEvaluator<JALParser.JvmInsArraylengthContext>
{
    public InstructionEvaluatorArrayLength()
    {
        super(EOpcodes.ARRAYLENGTH);
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        ObjectElement expectedElement = new ObjectElement(instruction, TypeDescriptor.parse("[Ljava/lang/Object;"));

        StackElementCapsule arrayRef = new StackElementCapsule(
                instruction, (elm) -> {
            StackElementType elementType = elm.type();
            if (elementType != StackElementType.OBJECT)
                throw new StackElementMismatchedException(instruction, expectedElement, elm);
            ObjectElement objectElement = (ObjectElement) elm;
            TypeDescriptor content = objectElement.content();
            if (!content.isArray())
                throw new StackElementMismatchedException(instruction, expectedElement, elm);

            return elm;
        }
        );
        return FrameDifferenceInfo.builder(instruction)
                                  .popToCapsule(arrayRef)
                                  .pushPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    @Override
    protected JALParser.JvmInsArraylengthContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsArraylength();
    }
}
