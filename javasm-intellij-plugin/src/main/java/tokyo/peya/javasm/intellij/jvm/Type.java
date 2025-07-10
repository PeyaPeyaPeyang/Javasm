package tokyo.peya.javasm.intellij.jvm;

public interface Type
{
    boolean isPrimitive();

    String getDescriptor();
}
