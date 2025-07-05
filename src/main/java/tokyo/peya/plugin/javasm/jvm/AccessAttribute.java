package tokyo.peya.plugin.javasm.jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.objectweb.asm.Opcodes;

@Getter
@AllArgsConstructor
public enum AccessAttribute
{
    ABSTRACT("abstract", Opcodes.ACC_ABSTRACT),
    FINAL("final", Opcodes.ACC_FINAL),
    NATIVE("native", Opcodes.ACC_NATIVE),
    STATIC("static", Opcodes.ACC_STATIC),
    SYNCHRONIZED("synchronized", Opcodes.ACC_SYNCHRONIZED),
    TRANSIENT("transient", Opcodes.ACC_TRANSIENT),
    VOLATILE("volatile", Opcodes.ACC_VOLATILE),
    BRIDGE("strictfp", Opcodes.ACC_STRICT),
    SYNTHETIC("synthetic", Opcodes.ACC_SYNTHETIC),
    STRICT("bridge", Opcodes.ACC_BRIDGE);

    private final String name;
    private final int asmFlag;
}
