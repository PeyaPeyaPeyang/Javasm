package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record TopElement(
        @NotNull
        InstructionInfo producer
) implements StackElement
{

    @Override
    public StackElementType type()
    {
        return StackElementType.TOP;
    }
}
