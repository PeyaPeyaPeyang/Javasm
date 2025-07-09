package tokyo.peya.javasm.intellij.jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tokyo.peya.javasm.langjal.compiler.EOpcodes;

@Getter
@AllArgsConstructor
public enum AccessAttribute
{
    FINAL("final", EOpcodes.ACC_FINAL),
    STATIC("static", EOpcodes.ACC_STATIC),
    SYNCHRONIZED("synchronized", EOpcodes.ACC_SYNCHRONIZED),
    BRIDGE("bridge", EOpcodes.ACC_BRIDGE),
    NATIVE("native", EOpcodes.ACC_NATIVE),
    ABSTRACT("abstract", EOpcodes.ACC_ABSTRACT),
    STRICTFP("strictfp", EOpcodes.ACC_STRICT),
    SYNTHETIC("synthetic", EOpcodes.ACC_SYNTHETIC),
    MANDATED("mandated", EOpcodes.ACC_MANDATED),
    DEPRECATED("deprecated", EOpcodes.ACC_DEPRECATED),
    TRANSIENT("transient", EOpcodes.ACC_TRANSIENT),
    VOLATILE("volatile", EOpcodes.ACC_VOLATILE);

    private final String name;
    private final int asmFlag;

    public static AccessAttribute fromString(String name)
    {
        return switch (name.trim().toLowerCase()) {
            case "final" -> FINAL;
            case "static" -> STATIC;
            case "synchronized" -> SYNCHRONIZED;
            case "bridge" -> BRIDGE;
            case "native" -> NATIVE;
            case "abstract" -> ABSTRACT;
            case "strictfp", "strict" -> STRICTFP;
            case "synthetic" -> SYNTHETIC;
            case "mandated" -> MANDATED;
            case "deprecated" -> DEPRECATED;
            case "transient" -> TRANSIENT;
            case "volatile" -> VOLATILE;
            default -> throw new IllegalArgumentException("Unknown access attribute: " + name);
        };
    }
}
