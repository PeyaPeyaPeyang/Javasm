package tokyo.peya.plugin.javasm.jvm;

import lombok.Getter;

@Getter
public class TypeWrapper
{
    private final Type baseType;
    private final int arrayDimensions;

    public TypeWrapper(Type baseType, int arrayDimensions)
    {
        this.baseType = baseType;
        this.arrayDimensions = arrayDimensions;

        if (arrayDimensions < 0)
            throw new IllegalArgumentException("Array dimensions cannot be negative: " + arrayDimensions);
    }

    public TypeWrapper(Type baseType)
    {
        this(baseType, 0);
    }

    public boolean isArray()
    {
        return this.arrayDimensions > 0;
    }
}
