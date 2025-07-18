package tokyo.peya.javasm.intellij.stackviewer;

import com.google.common.collect.Multimap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.MethodNode;

import java.util.Collection;
import java.util.Collections;

public record StackFrameAnalysisResult(
        @NotNull
        Multimap<MethodWrapper, InstructionUIElement> instructions
)
{
    public Collection<MethodWrapper> getMethods()
    {
        return this.instructions.keySet();
    }

    @NotNull
    public Collection<InstructionUIElement> getInstructions(@NotNull String methodName,
                                                            @NotNull String methodDescriptor)
    {
        for (MethodWrapper wrapper : this.instructions.keySet())
        {
            MethodNode methodNode = wrapper.method();
            if (methodNode.name.equals(methodName) && methodNode.desc.equals(methodDescriptor))
                return this.instructions.get(wrapper);
        }

        return Collections.emptyList();
    }

    public Collection<InstructionUIElement> getInstructions(@NotNull MethodWrapper method)
    {
        if (this.instructions.containsKey(method))
            return this.instructions.get(method);

        throw new IllegalArgumentException("Method not found: " + method.method().name + method.method().desc);
    }

    @Nullable
    public InstructionUIElement getInstructionAt(@NotNull String methodName, @NotNull String methodDescriptor,
                                                 int instructionOffset)
    {
        Collection<InstructionUIElement> instructionElements = this.getInstructions(methodName, methodDescriptor);
        if (instructionElements.isEmpty())
            return null;
        for (InstructionUIElement element : instructionElements)
        {
            if (element.instructionOffset() == instructionOffset)
                return element;
        }

        return null;
    }
}
