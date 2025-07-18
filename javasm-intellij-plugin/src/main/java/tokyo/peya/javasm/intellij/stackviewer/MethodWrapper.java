package tokyo.peya.javasm.intellij.stackviewer;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MethodNode;

import java.util.Objects;

public record MethodWrapper(
        @NotNull
        MethodNode method
)
{
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof MethodWrapper(MethodNode method1)))
            return false;
        return this.method.name.equals(method1.name) && this.method.desc.equals(method1.desc);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.method.name, this.method.desc);
    }
}
