package tokyo.peya.javasm.langjal.compiler.jvm;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;

public class ClassReferenceType implements Type
{
    @NotNull
    private final String packageName;
    @NotNull
    private final String className;

    private ClassReferenceType(@NotNull String packageName, @NotNull String className)
    {
        this.packageName = packageName;
        this.className = className;
    }

    @Override
    public boolean isPrimitive()
    {
        return false;
    }

    @Override
    public int getCategory()
    {
        return 1; // 定数プールにあるので cat 1
    }

    @Override
    public StackElementType getStackElementType()
    {
        return StackElementType.OBJECT; // オブジェクト型なので OBJECT
    }

    @Override
    public String getDescriptor()
    {
        return "L" + this.packageName + "/" + this.className + ";";
    }

    public static ClassReferenceType parse(@NotNull String typeName)
    {
        if (typeName.startsWith("L"))
            typeName = typeName.substring(1);
        if (typeName.endsWith(";"))
            typeName = typeName.substring(0, typeName.length() - 1);

        String[] parts = typeName.split("/");
        if (parts.length == 1)
            return new ClassReferenceType("", parts[0]);
        else
        {

            String[] packageParts = new String[parts.length - 1];
            System.arraycopy(parts, 0, packageParts, 0, parts.length - 1);
            String packageName = String.join("/", packageParts);
            String className = parts[parts.length - 1];
            return new ClassReferenceType(packageName, className);
        }
    }
}
