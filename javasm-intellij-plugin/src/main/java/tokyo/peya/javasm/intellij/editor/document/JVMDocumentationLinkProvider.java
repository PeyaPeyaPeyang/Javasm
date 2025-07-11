package tokyo.peya.javasm.intellij.editor.document;

import org.jetbrains.annotations.NotNull;

public class JVMDocumentationLinkProvider
{
    private static final String JVM_DOCUMENT_URL = "https://docs.oracle.com/javase/specs/jvms/se24/html/jvms-6.html";

    public static String getDocumentationLink(String instructionName)
    {
        if (instructionName == null || instructionName.isEmpty())
            return JVM_DOCUMENT_URL;

        return JVM_DOCUMENT_URL + "#jvms-6.5." + normalizeInstructionName(instructionName);
    }

    private static String normalizeInstructionName(@NotNull String instructionName)
    {
        return switch (instructionName)
        {
            case "aload_0", "aload_1", "aload_2", "aload_3" -> "aload_n";
            case "astore_0", "astore_1", "astore_2", "astore_3" -> "astore_n";
            case "dcmpg", "dcmpl" -> "dcmp_op";
            case "dconst_0", "dconst_1" -> "dconst_n";
            case "dload_0", "dload_1", "dload_2", "dload_3" -> "dload_n";
            case "dstore_0", "dstore_1", "dstore_2", "dstore_3" -> "dstore_n";
            case "fcmpl", "fcmpg" -> "fcmp_op";
            case "fconst_0", "fconst_1", "fconst_2" -> "fconst_n";
            case "fload_0", "fload_1", "fload_2", "fload_3" -> "fload_n";
            case "fstore_0", "fstore_1", "fstore_2", "fstore_3" -> "fstore_n";
            case "iconst_0", "iconst_1", "iconst_2", "iconst_3", "iconst_4", "iconst_5" -> "iconst_n";
            case "if_acmpeq", "if_acmpne" -> "if_acmp_cond";
            case "if_icmpeq", "if_icmpne", "if_icmplt", "if_icmpge", "if_icmpgt", "if_icmple" -> "if_icmp_cond";
            case "ifeq", "ifne", "iflt", "ifge", "ifgt", "ifle" -> "if_cond";
            case "iload_0", "iload_1", "iload_2", "iload_3" -> "iload_n";
            case "istore_0", "istore_1", "istore_2", "istore_3" -> "istore_n";
            case "lconst_0", "lconst_1" -> "lconst_n";
            case "lload_0", "lload_1", "lload_2", "lload_3" -> "lload_n";
            case "lstore_0", "lstore_1", "lstore_2", "lstore_3" -> "lstore_n";
            default -> instructionName;
        };
    }
}
