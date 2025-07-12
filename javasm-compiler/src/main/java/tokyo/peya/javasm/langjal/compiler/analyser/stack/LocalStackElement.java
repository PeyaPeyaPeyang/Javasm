package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record LocalStackElement(
        @NotNull
        InstructionInfo producer,
        int index,
        @NotNull
        StackElement stackElement
) implements StackElement
{
    public LocalStackElement
    {
        if (index < 0)
            throw new IllegalArgumentException("Local variable index must be non-negative, but was: " + index);
        if (stackElement.type() == StackElementType.TOP)
            throw new IllegalArgumentException("LocalStackElement cannot have TOP type, but was: " + stackElement.type());
    }

    @Override
    public StackElementType type()
    {
        return this.stackElement.type();
    }
}
