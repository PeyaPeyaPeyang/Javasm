package tokyo.peya.javasm.langjal.compiler.analyser;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;

public record InstructionSetAnalysisResult(
        @NotNull
        AnalysedInstruction[] analyzedInstructions,
        @NotNull
        FramePropagation[] framePropagations,
        @NotNull
        StackElement[] stack,
        @NotNull
        StackElement[] locals,

        int maxStackSize,
        int maxLocalSize
)
{
}
