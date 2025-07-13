package tokyo.peya.javasm.langjal.compiler.analyser;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.LocalStackElement;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElement;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;

public record InstructionSetFrame(
        @NotNull
        LabelInfo label,
        @NotNull
        StackElement[] stack,
        @NotNull
        LocalStackElement[] locals
)
{
}
