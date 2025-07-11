package tokyo.peya.javasm.langjal.compiler.jvm;

import lombok.Getter;

@Getter
public class TypeDescriptor
{
    private final Type baseType;
    private final int arrayDimensions;

    public TypeDescriptor(Type baseType, int arrayDimensions)
    {
        this.baseType = baseType;
        this.arrayDimensions = arrayDimensions;

        if (arrayDimensions < 0)
            throw new IllegalArgumentException("Array dimensions cannot be negative: " + arrayDimensions);
    }

    public TypeDescriptor(Type baseType)
    {
        this(baseType, 0);
    }

    public boolean isArray()
    {
        return this.arrayDimensions > 0;
    }

    @Override
    public String toString()
    {
        return "[".repeat(Math.max(0, this.arrayDimensions)) + this.baseType.getDescriptor();
    }

    public static TypeDescriptor parse(String descriptor)
    {
        return parse(DescriptorReader.fromString(descriptor));
    }

    static TypeDescriptor parse(DescriptorReader reader)
    {
        int dim = 0;

        // 配列の次元をカウントする
        while (reader.peek() == '[')
        {
            dim++;
            reader.read(); // skip '['
        }

        char c = reader.read(); // プリミティブ or L(オブジェクト型の識別子)

        Type type;
        if (c == 'L')
        {
            StringBuilder className = new StringBuilder();
            while (reader.peek() != ';')
            {
                className.append(reader.read());
                if (!reader.hasMore())
                    throw new IllegalArgumentException("Unterminated object type");
            }
            reader.read(); // skip ';'
            type = ClassReferenceType.fromString(className.toString());
        }
        else
        {
            // プリミティブ型の処理
            type = PrimitiveTypes.fromDescriptor(c);
            if (type == null)
                throw new IllegalArgumentException("Unknown type: " + c);
        }

        return new TypeDescriptor(type, dim);
    }
}
