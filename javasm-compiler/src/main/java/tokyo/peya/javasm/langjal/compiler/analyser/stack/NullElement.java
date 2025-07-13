package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record NullElement(
        @NotNull
        InstructionInfo producer // この要素を生成した命令
) implements StackElement
{
    @Override
    public @NotNull StackElementType type()
    {
        return StackElementType.NULL;
    }

    @NotNull
    public static NullElement of(@NotNull InstructionInfo producer)
    {
        return new NullElement(producer);
    }

    @Override
    public @NotNull String toString()
    {
        return "Null type (by " + this.producer + ")";
    }
}
