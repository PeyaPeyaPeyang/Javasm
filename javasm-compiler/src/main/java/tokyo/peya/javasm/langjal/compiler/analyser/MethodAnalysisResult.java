package tokyo.peya.javasm.langjal.compiler.analyser;

import org.jetbrains.annotations.NotNull;

public record MethodAnalysisResult(
        @NotNull
        FrameDifferenceInfo[] frameDifferences,
        int maxStack,
        int maxLocals
)
{
}
