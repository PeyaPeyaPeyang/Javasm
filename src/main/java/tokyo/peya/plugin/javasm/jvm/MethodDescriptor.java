package tokyo.peya.plugin.javasm.jvm;

import lombok.Getter;
import tokyo.peya.plugin.javasm.langjal.psi.JALMethodDescriptor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MethodDescriptor
{
    private final TypeWrapper returnType;
    private final TypeWrapper[] parameterTypes;

    private final String descriptorString;

    private MethodDescriptor(TypeWrapper returnType, TypeWrapper[] parameterTypes, String descriptorString)
    {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.descriptorString = descriptorString;
    }

    public static MethodDescriptor of(JALMethodDescriptor jalMethodDescriptor)
    {
        String descriptor = jalMethodDescriptor.getText();
        if (descriptor == null || !descriptor.startsWith("("))
            throw new IllegalArgumentException("Invalid method descriptor: " + descriptor);

        return parse(descriptor);
    }

    public static MethodDescriptor parse(String descriptor)
    {
        if (!descriptor.startsWith("("))
            throw new IllegalArgumentException("Invalid method descriptor: " + descriptor);

        int pos = 1;
        List<TypeWrapper> parameters = new ArrayList<>();

        // パラメータの解析
        while (descriptor.charAt(pos) != ')')
        {
            ParsedType parsed = parseType(descriptor, pos);
            parameters.add(new TypeWrapper(parsed.type, parsed.dimensions));
            pos = parsed.nextIndex;
        }

        pos++; // skip ')'
        ParsedType returnParsed = parseType(descriptor, pos);  // 次の文字は戻り値の型
        TypeWrapper returnType = new TypeWrapper(returnParsed.type, returnParsed.dimensions);

        return new MethodDescriptor(returnType, parameters.toArray(new TypeWrapper[0]), descriptor);
    }

    private static ParsedType parseType(String descriptor, int start)
    {
        int pos = start;
        int dim = 0;

        // 配列ディメンション
        while (descriptor.charAt(pos) == '[')
        {
            dim++;
            pos++;
        }

        char typeChar = descriptor.charAt(pos);
        Type type;

        if (typeChar == 'L')
        {
            int semi = descriptor.indexOf(';', pos);
            if (semi == -1)
                throw new IllegalArgumentException("Invalid class type");
            String className = descriptor.substring(pos + 1, semi);
            type = ClassType.fromString(className);
            pos = semi; // 'L...;' の ';' まで飛ばす
        }
        else if ((type = PrimitiveTypes.fromDescriptor(typeChar)) == null)
            throw new IllegalArgumentException("Unknown type: " + typeChar);

        // assert type != null

        return new ParsedType(type, dim, pos + 1);
    }

    private record ParsedType(
            Type type,
            int dimensions,
            int nextIndex
    ) {}
}
