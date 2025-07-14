package tokyo.peya.javasm.langjal.compiler.instructions.xreturn;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.MethodNode;
import tokyo.peya.javasm.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;

public class InstructionEvaluateHelperXReturn
{
    public static void checkReturnType(@NotNull MethodNode method, @NotNull TypeDescriptor returningType)
    {
        MethodDescriptor methodDescriptor = MethodDescriptor.parse(method.desc);
        TypeDescriptor expectedReturnType = methodDescriptor.getReturnType();

        if (returningType.equals(TypeDescriptor.OBJECT))
        {
            if (expectedReturnType.getBaseType().getDescriptor().startsWith("L"))
                return;

        }

        if (!expectedReturnType.equals(returningType))
            throw new IllegalStateException("Method " + method.name + " has return type " + expectedReturnType
                                                    + ", but the return type of the instruction is " + returningType);
    }
}
