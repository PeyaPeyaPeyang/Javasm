package tokyo.peya.javasm.langjal.compiler.analyser;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;

public record AnalysedInstruction(
        @NotNull
        InstructionInfo instruction,
        @NotNull
        FrameDifferenceInfo frameDifference
)
{
}
