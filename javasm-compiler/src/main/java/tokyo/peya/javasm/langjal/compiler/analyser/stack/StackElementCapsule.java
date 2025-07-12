package tokyo.peya.javasm.langjal.compiler.analyser.stack;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

// DUP や SWAP などのために，スタック要素を一旦退避する特別な参照.
@Getter
@Setter
public final class StackElementCapsule implements StackElement
{
    private final InstructionInfo instruction;

    private StackElement element;

    public StackElementCapsule(@NotNull InstructionInfo instruction)
    {
        this.instruction = instruction;
    }

    @Override
    public InstructionInfo producer()
    {
        return this.instruction;
    }

    @Override
    public StackElementType type()
    {
        return this.element.type();
    }
}
