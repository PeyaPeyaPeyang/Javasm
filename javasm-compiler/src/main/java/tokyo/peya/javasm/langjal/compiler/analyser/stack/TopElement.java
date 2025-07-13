package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record TopElement(
        InstructionInfo producer
) implements StackElement
{

    @Override
    public @NotNull StackElementType type()
    {
        return StackElementType.TOP;
    }

    @Override
    public Object toASMStackElement()
    {
        return EOpcodes.TOP;
    }

    @Override
    public @NotNull String toString()
    {
        return "Top type (by " + this.producer + ")";
    }
}
