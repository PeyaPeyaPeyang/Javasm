package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record UninitializedThisElement(
        @NotNull InstructionInfo producer // かならず NOP
) implements StackElement
{
    @Override
    public @NotNull StackElementType type()
    {
        return StackElementType.UNINITIALIZED_THIS;
    }

    @Override
    public Object toASMStackElement()
    {
        return EOpcodes.UNINITIALIZED_THIS;
    }

    @Override
    public @NotNull String toString()
    {
        return "Uninitialized this";
    }
}
