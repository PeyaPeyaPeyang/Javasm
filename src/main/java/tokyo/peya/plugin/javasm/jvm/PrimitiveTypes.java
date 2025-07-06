package tokyo.peya.plugin.javasm.jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tokyo.peya.plugin.javasm.compiler.EOpcodes;

@Getter
@AllArgsConstructor
public enum PrimitiveTypes implements Type
{
    BYTE("byte", 'B', EOpcodes.T_BYTE),
    SHORT("short", 'S', EOpcodes.T_SHORT),
    INT("int", 'I', EOpcodes.T_INT),
    LONG("long", 'J', EOpcodes.T_LONG),
    FLOAT("float", 'F', EOpcodes.T_FLOAT),
    DOUBLE("double", 'D', EOpcodes.T_DOUBLE),
    BOOLEAN("boolean", 'Z', EOpcodes.T_BOOLEAN),
    CHAR("char", 'C', EOpcodes.T_CHAR),
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

