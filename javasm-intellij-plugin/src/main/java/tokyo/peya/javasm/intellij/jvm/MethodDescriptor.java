package tokyo.peya.javasm.intellij.jvm;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MethodDescriptor
{
    private final TypeDescriptor returnType;
    private final TypeDescriptor[] parameterTypes;

    private final String descriptorString;

    private MethodDescriptor(TypeDescriptor returnType, TypeDescriptor[] parameterTypes, String descriptorString)
    {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.descriptorString = descriptorString;
    }
/*
    public static MethodDescriptor of(JALMethodDescriptor jalMethodDescriptor)
    {
        String descriptor = jalMethodDescriptor.getText();
        if (descriptor == null || !descriptor.startsWith("("))
            throw new IllegalArgumentException("Invalid method descriptor: " + descriptor);

        return parse(descriptor);
    }*/

    public static MethodDescriptor parse(String descriptor) {
        DescriptorReader reader = DescriptorReader.fromString(descriptor);

        reader.expect('(');
        List<TypeDescriptor> parameters = new ArrayList<>();

        while (reader.peek() != ')')
            parameters.add(TypeDescriptor.parse(reader));

        reader.expect(')');

        TypeDescriptor returnType = TypeDescriptor.parse(reader);

        return new MethodDescriptor(returnType, parameters.toArray(new TypeDescriptor[0]), descriptor);
    }
}
