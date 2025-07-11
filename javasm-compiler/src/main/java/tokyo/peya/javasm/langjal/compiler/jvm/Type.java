package tokyo.peya.javasm.langjal.compiler.jvm;

public interface Type
{
    boolean isPrimitive();

    String getDescriptor();

    int getCategory();
}
