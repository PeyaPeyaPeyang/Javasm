package tokyo.peya.javasm.intellij.jvm;

import org.jetbrains.annotations.NotNull;

public class ClassType implements Type
{
    @NotNull
    private final String packageName;
    @NotNull
    private final String className;

    private ClassType(@NotNull String packageName, @NotNull String className)
    {
        this.packageName = packageName;
        this.className = className;
    }

    @Override
    public boolean isPrimitive()
    {
        return false;
    }

    public static ClassType fromString(@NotNull String typeName)
    {
        if (typeName.startsWith("L"))
            typeName = typeName.substring(1);
        if (typeName.endsWith(";"))
            typeName = typeName.substring(0, typeName.length() - 1);

        String[] parts = typeName.split("/");
        if (parts.length == 1)
            return new ClassType("", parts[0]);
        else
        {

            String[] packageParts = new String[parts.length - 1];
            System.arraycopy(parts, 0, packageParts, 0, parts.length - 1);
            String packageName = String.join("/", packageParts);
            String className = parts[parts.length - 1];
            return new ClassType(packageName, className);
        }
    }
}
