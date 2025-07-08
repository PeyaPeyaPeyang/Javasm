package tokyo.peya.javasm.intellij.langjal.psi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.jvm.AccessLevel;

import java.util.List;

public class JALMethodAccessor
{
    @NotNull
    public static AccessAttributeSet getAttributes(@NotNull JALAccModMethod method)
    {
        List<JALAccAttrMethod> attributes = method.getAccAttrMethodList();
        List<String> attributeNames = attributes.stream()
                                                .map(JALAccAttrMethod::getText)
                                                .toList();

        return new AccessAttributeSet(attributeNames.toArray(new String[0]));
    }

    @NotNull
    public static AccessAttributeSet getAttributes(@NotNull JALMethodDefinition method)
    {
        JALAccModMethod modMethod = method.getAccModMethod();
        return getAttributes(modMethod);
    }

    @NotNull
    public static AccessLevel getAccessLevel(@NotNull JALMethodDefinition method)
    {
        JALAccModMethod modMethod = method.getAccModMethod();

        return getAccessLevel(modMethod);
    }

    @NotNull
    public static AccessLevel getAccessLevel(@NotNull JALAccModMethod accMethod)
    {
        JALAccessLevel accessLevel = accMethod.getAccessLevel();
        if (accessLevel != null)
            return AccessLevel.valueOf(accessLevel.getText().toUpperCase());
        return AccessLevel.PACKAGE_PRIVATE; // Default access level if not specified
    }

    @Nullable
    public static String getMethodName(@NotNull JALMethodDefinition method)
    {
        if (method.getId() != null)
            return method.getId().getText();
        return null; // メソッド名が存在しない場合はnullを返す
    }
}
