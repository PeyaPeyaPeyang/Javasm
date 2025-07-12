package tokyo.peya.javasm.langjal.compiler.jvm;

import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;

public interface Type
{
    boolean isPrimitive();

    String getDescriptor();

    int getCategory();

    StackElementType getStackElementType();
}
