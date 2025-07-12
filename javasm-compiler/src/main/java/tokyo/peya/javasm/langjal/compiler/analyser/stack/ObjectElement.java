package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record ObjectElement(
        @NotNull
        InstructionInfo producer,
        @NotNull
        TypeDescriptor content
) implements StackElement
{
    public ObjectElement
    {
        if (content.getBaseType().isPrimitive())
            throw new IllegalArgumentException(
                    "ObjectElement content must not be a primitive type: " + content
            );
    }

    public ObjectElement(@NotNull
                         InstructionInfo producer)
    {
        this(producer, TypeDescriptor.parse("java/lang/Object"));
    }

    @Override
    public StackElementType type()
    {
        return StackElementType.OBJECT;
    }
}
