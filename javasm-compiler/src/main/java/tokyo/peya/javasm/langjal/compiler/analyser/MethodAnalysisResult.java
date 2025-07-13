package tokyo.peya.javasm.langjal.compiler.analyser;

import org.jetbrains.annotations.NotNull;

public record MethodAnalysisResult(
        @NotNull
        FramePropagation[] propagations,
        int maxStack,
        int maxLocals
)
{
    public static final MethodAnalysisResult EMPTY = new MethodAnalysisResult(
            new FramePropagation[0],
            0,
            0
    );
}
