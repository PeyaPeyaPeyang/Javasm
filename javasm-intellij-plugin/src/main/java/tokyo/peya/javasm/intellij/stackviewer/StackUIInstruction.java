package tokyo.peya.javasm.intellij.stackviewer;

import org.jetbrains.annotations.NotNull;

public record StackUIInstruction(
        @NotNull
        String instruction,
        int bytecodeOffset
)
{
}
