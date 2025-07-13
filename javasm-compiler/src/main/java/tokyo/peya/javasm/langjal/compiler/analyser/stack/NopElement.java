package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record NopElement(
        @NotNull InstructionInfo producer
) implements StackElement
{

    @Override
    public @NotNull StackElementType type()
    {
        return StackElementType.NOP;
    }
}
