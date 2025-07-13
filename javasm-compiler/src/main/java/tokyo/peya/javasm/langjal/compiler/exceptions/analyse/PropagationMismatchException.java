package tokyo.peya.javasm.langjal.compiler.exceptions.analyse;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.analyser.FramePropagation;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public class PropagationMismatchException extends ClassAnalyseException
{
    private final FramePropagation propagation;
    private final LabelInfo receiver;

    public PropagationMismatchException(@NotNull FramePropagation propagation, @NotNull LabelInfo receiver)
    {
        super("CANNOT ANALYSE FRAME PROPAGATION: " + propagation + " -> " + receiver);
        this.propagation = propagation;
        this.receiver = receiver;
    }

    public PropagationMismatchException(@NotNull FramePropagation propagation, @NotNull LabelInfo receiver,
                                        @NotNull String message)
    {
        super(message);
        this.propagation = propagation;
        this.receiver = receiver;
    }
}
