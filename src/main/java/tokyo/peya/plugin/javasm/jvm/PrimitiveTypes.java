package tokyo.peya.plugin.javasm.jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.objectweb.asm.Opcodes;

@Getter
@AllArgsConstructor
public enum PrimitiveTypes implements Type
{
    BYTE("byte", 'B', Opcodes.T_BYTE),
    SHORT("short", 'S', Opcodes.T_SHORT),
    INT("int", 'I', Opcodes.T_INT),
    LONG("long", 'J', Opcodes.T_LONG),
    FLOAT("float", 'F', Opcodes.T_FLOAT),
    DOUBLE("double", 'D', Opcodes.T_DOUBLE),
    BOOLEAN("boolean", 'Z', Opcodes.T_BOOLEAN),
    CHAR("char", 'C', Opcodes.T_CHAR),
    VOID("void", 'V', -1);

    private final String name;
    private final char descriptor;
    private final int asmType;

    @Override
    public boolean isPrimitive()
    {
        return true;
    }

    public static PrimitiveTypes fromDescriptor(char descriptorChar)
    {
        for (PrimitiveTypes type : PrimitiveTypes.values())
            if (type.getDescriptor() == descriptorChar)
                return type;
            
        return null; // 該当するプリミティブ型がない場合はnullを返す
    }
}

