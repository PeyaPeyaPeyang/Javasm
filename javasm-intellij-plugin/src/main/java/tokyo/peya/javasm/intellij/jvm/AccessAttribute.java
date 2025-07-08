package tokyo.peya.javasm.intellij.jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;

@Getter
@AllArgsConstructor
public enum AccessAttribute
{
    ABSTRACT("abstract", EOpcodes.ACC_ABSTRACT),
    FINAL("final", EOpcodes.ACC_FINAL),
    NATIVE("native", EOpcodes.ACC_NATIVE),
    STATIC("static", EOpcodes.ACC_STATIC),
    SYNCHRONIZED("synchronized", EOpcodes.ACC_SYNCHRONIZED),
    TRANSIENT("transient", EOpcodes.ACC_TRANSIENT),
    VOLATILE("volatile", EOpcodes.ACC_VOLATILE),
    BRIDGE("strictfp", EOpcodes.ACC_STRICT),
    SYNTHETIC("synthetic", EOpcodes.ACC_SYNTHETIC),
    STRICT("bridge", EOpcodes.ACC_BRIDGE);

    private final String name;
    private final int asmFlag;
}
