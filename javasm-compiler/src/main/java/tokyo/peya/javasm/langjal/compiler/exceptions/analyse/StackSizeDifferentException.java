package tokyo.peya.javasm.langjal.compiler.exceptions.analyse;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

@Getter
public class StackSizeDifferentException extends ClassAnalyseException
{
    @NotNull
    private final LabelInfo atLabel;
    @NotNull
    private final StackElement[] expected;
    @NotNull
    private final StackElement[] actual;

    public StackSizeDifferentException(@NotNull String message,
                                       @NotNull LabelInfo atLabel,
                                       @NotNull
                                       StackElement[] expected,
                                       @NotNull
                                       StackElement[] actual)
    {
        super(message);
        this.atLabel = atLabel;
        this.expected = expected;
        this.actual = actual;
    }
}
