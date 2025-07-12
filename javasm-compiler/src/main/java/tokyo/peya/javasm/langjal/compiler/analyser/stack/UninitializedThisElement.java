package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record UninitializedThisElement(
        @NotNull
        InstructionInfo producer // this を初期化している命令
) implements StackElement
{
    @Override
    public StackElementType type()
    {
        return StackElementType.UNINITIALIZED_THIS;
    }
}
