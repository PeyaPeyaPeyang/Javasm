package tokyo.peya.javasm.intellij.stackviewer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public record InstructionUIElement(
        int instructionOffset,
        @NotNull
        StackUIInstruction instruction,
        @NotNull
        List<StackUIElement> stack,
        @NotNull
        List<StackUIElement> locals
) {}
