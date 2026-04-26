package tokyo.peya.javasm.intellij.utils;

import tokyo.peya.langjal.compiler.jvm.ClassReferenceType;
import tokyo.peya.langjal.compiler.jvm.PrimitiveTypes;
import tokyo.peya.langjal.compiler.jvm.Type;
import tokyo.peya.langjal.compiler.jvm.TypeDescriptor;

public class TypeDescriptorUtil {
    public static String toPublicName(TypeDescriptor desc) {
        Type baseType = desc.getBaseType();
        if (baseType instanceof PrimitiveTypes primitive) {
            return primitive.getName();
        }

        assert baseType instanceof ClassReferenceType;  // sealed クラス。

        ClassReferenceType classType = (ClassReferenceType) baseType;
        return classType.getDottedName();
    }
}
