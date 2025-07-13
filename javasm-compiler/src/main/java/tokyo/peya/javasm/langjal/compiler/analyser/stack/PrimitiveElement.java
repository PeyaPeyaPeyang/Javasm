package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record PrimitiveElement(
        @NotNull
        InstructionInfo producer,
        @NotNull
        StackElementType type
) implements StackElement
{
    public PrimitiveElement
    {
        if (!(type == StackElementType.INTEGER ||
                type == StackElementType.FLOAT ||
                type == StackElementType.LONG ||
                type == StackElementType.DOUBLE))
            throw new IllegalArgumentException("PrimitiveElement must be INTEGER, FLOAT, LONG, or DOUBLE, but was: " + type);
    }

    public static PrimitiveElement of(
            @NotNull InstructionInfo producer,
            @NotNull StackElementType type
    )
    {
        return new PrimitiveElement(producer, type);
    }

    @Override
    public @NotNull String toString()
    {
        return "Primitive type of " + this.type + " (by " + this.producer + ")";
    }
}
