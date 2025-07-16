package tokyo.peya.javasm.intellij.editor.completion;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class JALInstructionNameCompletionProvider extends CompletionProvider<CompletionParameters>
{
    private static final Map<String, String> INSTRUCTIONS;
    private static final HashSet<String> INSTRUCTIONS_WITH_ARGUMENTS;

    static
    {
        HashMap<String, String> instructions = new HashMap<>();
        // <editor-fold desc="Instruction Definitions">
        instructions.putAll(Map.ofEntries(
                Map.entry("aaload", "Reference: Array Load"),
                Map.entry("aastore", "Reference: Array Store"),
                Map.entry("aconst_null", "Reference: Constant Null"),
                Map.entry("aload", "Reference: Load local variable"),
                Map.entry("aload_0", "Reference: Load local 0"),
                Map.entry("aload_1", "Reference: Load local 1"),
                Map.entry("aload_2", "Reference: Load local 2"),
                Map.entry("aload_3", "Reference: Load local 3"),
                Map.entry("anewarray", "Reference: Create new array"),
                Map.entry("areturn", "Reference: Return reference"),
                Map.entry("arraylength", "Reference: Get array length"),
                Map.entry("athrow", "Reference: Throw exception"),
                Map.entry("checkcast", "Reference: Check cast"),
                Map.entry("instanceof", "Reference: Check if instance of class")
        ));
        instructions.putAll(Map.ofEntries(
                Map.entry("baload", "Byte: Array Load"),
                Map.entry("bastore", "Byte: Array Store"),
                Map.entry("bipush", "Byte: Push byte constant")
        ));
        instructions.putAll(Map.ofEntries(
                Map.entry("caload", "Char: Array Load"),
                Map.entry("castore", "Char: Array Store")
        ));
        instructions.putAll(Map.ofEntries(
                Map.entry("d2f", "Double: Convert to float"),
                Map.entry("d2i", "Double: Convert to int"),
                Map.entry("d2l", "Double: Convert to long"),
                Map.entry("dadd", "Double: Add"),
                Map.entry("daload", "Double: Array Load"),
                Map.entry("dastore", "Double: Array Store"),
                Map.entry("dcmpg", "Double: Compare greater"),
                Map.entry("dcmpl", "Double: Compare less"),
                Map.entry("dconst_0", "Double: Constant 0.0"),
                Map.entry("dconst_1", "Double: Constant 1.0"),
                Map.entry("ddiv", "Double: Divide"),
                Map.entry("dload", "Double: Load local variable"),
                Map.entry("dload_0", "Double: Load local 0"),
                Map.entry("dload_1", "Double: Load local 1"),
                Map.entry("dload_2", "Double: Load local 2"),
                Map.entry("dload_3", "Double: Load local 3"),
                Map.entry("dmul", "Double: Multiply"),
                Map.entry("dneg", "Double: Negate"),
                Map.entry("drem", "Double: Remainder"),
                Map.entry("dreturn", "Double: Return"),
                Map.entry("dstore", "Double: Store local variable"),
                Map.entry("dstore_0", "Double: Store local 0"),
                Map.entry("dstore_1", "Double: Store local 1"),
                Map.entry("dstore_2", "Double: Store local 2"),
                Map.entry("dstore_3", "Double: Store local 3"),
                Map.entry("dsub", "Double: Subtract")
        ));
        instructions.putAll(Map.ofEntries(
                Map.entry("dup", "Stack: Duplicate top value"),
                Map.entry("dup_x1", "Stack: Duplicate top value and insert below second"),
                Map.entry("dup_x2", "Stack: Duplicate top value and insert below third"),
                Map.entry("dup2", "Stack: Duplicate top two values"),
                Map.entry("dup2_x1", "Stack: Duplicate top two values and insert below third"),
                Map.entry("dup2_x2", "Stack: Duplicate top two values and insert below fourth"),
                Map.entry("pop", "Stack: Pop top value"),
                Map.entry("pop2", "Stack: Pop top two values"),
                Map.entry("swap", "Stack: Swap top two values")
        ));
        instructions.putAll(Map.ofEntries(
                Map.entry("f2d", "Float: Convert to double"),
                Map.entry("f2i", "Float: Convert to int"),
                Map.entry("f2l", "Float: Convert to long"),
                Map.entry("fadd", "Float: Add"),
                Map.entry("faload", "Float: Array Load"),
                Map.entry("fastore", "Float: Array Store"),
                Map.entry("fcmpg", "Float: Compare greater"),
                Map.entry("fcmpl", "Float: Compare less"),
                Map.entry("fconst_0", "Float: Constant 0.0"),
                Map.entry("fconst_1", "Float: Constant 1.0"),
                Map.entry("fconst_2", "Float: Constant 2.0"),
                Map.entry("fdiv", "Float: Divide"),
                Map.entry("fload", "Float: Load local variable"),
                Map.entry("fload_0", "Float: Load local 0"),
                Map.entry("fload_1", "Float: Load local 1"),
                Map.entry("fload_2", "Float: Load local 2"),
                Map.entry("fload_3", "Float: Load local 3"),
                Map.entry("fmul", "Float: Multiply"),
                Map.entry("fneg", "Float: Negate"),
                Map.entry("frem", "Float: Remainder"),
                Map.entry("freturn", "Float: Return"),
                Map.entry("fstore", "Float: Store local variable"),
                Map.entry("fstore_0", "Float: Store local 0"),
                Map.entry("fstore_1", "Float: Store local 1"),
                Map.entry("fstore_2", "Float: Store local 2"),
                Map.entry("fstore_3", "Float: Store local 3"),
                Map.entry("fsub", "Float: Subtract")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("i2b", "Integer: Convert to byte"),
                Map.entry("i2c", "Integer: Convert to char"),
                Map.entry("i2d", "Integer: Convert to double"),
                Map.entry("i2f", "Integer: Convert to float"),
                Map.entry("i2l", "Integer: Convert to long"),
                Map.entry("i2s", "Integer: Convert to short"),
                Map.entry("iadd", "Integer: Add"),
                Map.entry("iaload", "Integer: Array Load"),
                Map.entry("iand", "Integer: Bitwise AND"),
                Map.entry("iastore", "Integer: Array Store"),
                Map.entry("iconst_m1", "Integer: Constant -1"),
                Map.entry("iconst_0", "Integer: Constant 0"),
                Map.entry("iconst_1", "Integer: Constant 1"),
                Map.entry("iconst_2", "Integer: Constant 2"),
                Map.entry("iconst_3", "Integer: Constant 3"),
                Map.entry("iconst_4", "Integer: Constant 4"),
                Map.entry("iconst_5", "Integer: Constant 5"),
                Map.entry("idiv", "Integer: Divide"),
                Map.entry("iinc", "Integer: Increment local variable"),
                Map.entry("iload", "Integer: Load local variable"),
                Map.entry("iload_0", "Integer: Load local 0"),
                Map.entry("iload_1", "Integer: Load local 1"),
                Map.entry("iload_2", "Integer: Load local 2"),
                Map.entry("iload_3", "Integer: Load local 3"),
                Map.entry("imul", "Integer: Multiply"),
                Map.entry("ineg", "Integer: Negate"),
                Map.entry("ior", "Integer: Bitwise OR"),
                Map.entry("irem", "Integer: Remainder"),
                Map.entry("ireturn", "Integer: Return integer"),
                Map.entry("ishl", "Integer: Shift left"),
                Map.entry("ishr", "Integer: Shift right"),
                Map.entry("istore", "Integer: Store local variable"),
                Map.entry("istore_0", "Integer: Store local 0"),
                Map.entry("istore_1", "Integer: Store local 1"),
                Map.entry("istore_2", "Integer: Store local 2"),
                Map.entry("istore_3", "Integer: Store local 3"),
                Map.entry("isub", "Integer: Subtract"),
                Map.entry("iushr", "Integer: Unsigned shift right"),
                Map.entry("ixor", "Integer: Bitwise XOR")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("goto", "Control Flow: Unconditional jump"),
                Map.entry("goto_w", "Control Flow: Unconditional jump (wide)"),
                Map.entry("if_acmpeq", "Control Flow: If reference equals"),
                Map.entry("if_acmpne", "Control Flow: If reference not equals"),
                Map.entry("if_icmpeq", "Control Flow: If integer equals"),
                Map.entry("if_icmpne", "Control Flow: If integer not equals"),
                Map.entry("if_icmplt", "Control Flow: If integer less than"),
                Map.entry("if_icmpge", "Control Flow: If integer greater than or equal"),
                Map.entry("if_icmpgt", "Control Flow: If integer greater than"),
                Map.entry("if_icmple", "Control Flow: If integer less than or equal"),
                Map.entry("ifeq", "Control Flow: If equal to zero"),
                Map.entry("ifne", "Control Flow: If not equal to zero"),
                Map.entry("iflt", "Control Flow: If less than zero"),
                Map.entry("ifge", "Control Flow: If greater than or equal to zero"),
                Map.entry("ifgt", "Control Flow: If greater than zero"),
                Map.entry("ifle", "Control Flow: If less than or equal to zero"),
                Map.entry("ifnonnull", "Control Flow: If not null"),
                Map.entry("ifnull", "Control Flow: If null"),
                Map.entry("jsr", "Control Flow: Jump to subroutine"),
                Map.entry("jsr_w", "Control Flow: Jump to subroutine (wide)"),
                Map.entry("lookupswitch", "Control Flow: Lookup switch"),
                Map.entry("ret", "Control Flow: Return from subroutine"),
                Map.entry("return", "Control Flow: Return from method"),
                Map.entry("tableswitch", "Control Flow: Table switch")
        ));
        instructions.putAll(Map.ofEntries(
                Map.entry("getfield", "Field: Get field from object"),
                Map.entry("getstatic", "Field: Get static field"),
                Map.entry("putfield", "Field: Set field in object"),
                Map.entry("putstatic", "Field: Set static field")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("invokedynamic", "Method: Invoke dynamic method"),
                Map.entry("invokeinterface", "Method: Invoke interface method"),
                Map.entry("invokespecial", "Method: Invoke special method"),
                Map.entry("invokestatic", "Method: Invoke static method"),
                Map.entry("invokevirtual", "Method: Invoke virtual method")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("l2d", "Long: Convert to double"),
                Map.entry("l2f", "Long: Convert to float"),
                Map.entry("l2i", "Long: Convert to int"),
                Map.entry("ladd", "Long: Add"),
                Map.entry("laload", "Long: Array Load"),
                Map.entry("land", "Long: Bitwise AND"),
                Map.entry("lastore", "Long: Array Store"),
                Map.entry("lcmp", "Long: Compare"),
                Map.entry("lconst_0", "Long: Constant 0"),
                Map.entry("lconst_1", "Long: Constant 1"),
                Map.entry("ldiv", "Long: Divide"),
                Map.entry("lload", "Long: Load local variable"),
                Map.entry("lload_0", "Long: Load local 0"),
                Map.entry("lload_1", "Long: Load local 1"),
                Map.entry("lload_2", "Long: Load local 2"),
                Map.entry("lload_3", "Long: Load local 3"),
                Map.entry("lmul", "Long: Multiply"),
                Map.entry("lneg", "Long: Negate"),
                Map.entry("lor", "Long: Bitwise OR"),
                Map.entry("lrem", "Long: Remainder"),
                Map.entry("lreturn", "Long: Return long"),
                Map.entry("lshl", "Long: Shift left"),
                Map.entry("lshr", "Long: Shift right"),
                Map.entry("lstore", "Long: Store local variable"),
                Map.entry("lstore_0", "Long: Store local 0"),
                Map.entry("lstore_1", "Long: Store local 1"),
                Map.entry("lstore_2", "Long: Store local 2"),
                Map.entry("lstore_3", "Long: Store local 3"),
                Map.entry("lsub", "Long: Subtract"),
                Map.entry("lushr", "Long: Unsigned shift right"),
                Map.entry("lxor", "Long: Bitwise XOR")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("ldc", "Constant: Load constant"),
                Map.entry("ldc_w", "Constant: Load constant (wide)"),
                Map.entry("ldc2_w", "Constant: Load double/long constant (wide)")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("monitorenter", "Synchronization: Enter monitor"),
                Map.entry("monitorexit", "Synchronization: Exit monitor")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("multianewarray", "Array: Create multi-dimensional array"),
                Map.entry("newarray", "Array: Create new array")
        ));

        instructions.putAll(Map.ofEntries(
                Map.entry("saload", "Short: Array Load"),
                Map.entry("sastore", "Short: Array Store"),
                Map.entry("sipush", "Short: Push short constant")
        ));

        instructions.putAll(
                Map.ofEntries(
                        Map.entry("new", "Misc: Create new instance"),
                        Map.entry("nop", "Misc: Do nothing"),
                        Map.entry("wide", "Misc: Use 2-byte index for local vars")
                )
        );
        // </editor-fold>
        INSTRUCTIONS = instructions;
    }

    static
    {
        INSTRUCTIONS_WITH_ARGUMENTS = new HashSet<>();

        // <editor-fold desc="Instructions with Arguments">
        INSTRUCTIONS_WITH_ARGUMENTS.add("aload");
        INSTRUCTIONS_WITH_ARGUMENTS.add("anewarray");
        INSTRUCTIONS_WITH_ARGUMENTS.add("astore");
        INSTRUCTIONS_WITH_ARGUMENTS.add("bipush");
        INSTRUCTIONS_WITH_ARGUMENTS.add("checkcast");
        INSTRUCTIONS_WITH_ARGUMENTS.add("dload");
        INSTRUCTIONS_WITH_ARGUMENTS.add("dstore");
        INSTRUCTIONS_WITH_ARGUMENTS.add("fload");
        INSTRUCTIONS_WITH_ARGUMENTS.add("fstore");
        INSTRUCTIONS_WITH_ARGUMENTS.add("getfield");
        INSTRUCTIONS_WITH_ARGUMENTS.add("getstatic");
        INSTRUCTIONS_WITH_ARGUMENTS.add("goto");
        INSTRUCTIONS_WITH_ARGUMENTS.add("goto_w");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_acmpeq");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_acmpne");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_icmpeq");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_icmpne");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_icmplt");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_icmpge");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_icmpgt");
        INSTRUCTIONS_WITH_ARGUMENTS.add("if_icmple");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifeq");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifne");
        INSTRUCTIONS_WITH_ARGUMENTS.add("iflt");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifge");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifgt");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifle");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifnonnull");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ifnull");
        INSTRUCTIONS_WITH_ARGUMENTS.add("iinc");
        INSTRUCTIONS_WITH_ARGUMENTS.add("iload");
        INSTRUCTIONS_WITH_ARGUMENTS.add("instanceof");
        INSTRUCTIONS_WITH_ARGUMENTS.add("invokedynamic");
        INSTRUCTIONS_WITH_ARGUMENTS.add("invokeinterface");
        INSTRUCTIONS_WITH_ARGUMENTS.add("invokespecial");
        INSTRUCTIONS_WITH_ARGUMENTS.add("invokestatic");
        INSTRUCTIONS_WITH_ARGUMENTS.add("invokevirtual");
        INSTRUCTIONS_WITH_ARGUMENTS.add("istore");
        INSTRUCTIONS_WITH_ARGUMENTS.add("jsr");
        INSTRUCTIONS_WITH_ARGUMENTS.add("jsr_w");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ldc");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ldc_w");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ldc2_w");
        INSTRUCTIONS_WITH_ARGUMENTS.add("lload");
        INSTRUCTIONS_WITH_ARGUMENTS.add("lookupswitch");
        INSTRUCTIONS_WITH_ARGUMENTS.add("lstore");
        INSTRUCTIONS_WITH_ARGUMENTS.add("multianewarray");
        INSTRUCTIONS_WITH_ARGUMENTS.add("new");
        INSTRUCTIONS_WITH_ARGUMENTS.add("newarray");
        INSTRUCTIONS_WITH_ARGUMENTS.add("putfield");
        INSTRUCTIONS_WITH_ARGUMENTS.add("putstatic");
        INSTRUCTIONS_WITH_ARGUMENTS.add("ret");
        INSTRUCTIONS_WITH_ARGUMENTS.add("saload");
        INSTRUCTIONS_WITH_ARGUMENTS.add("sastore");
        INSTRUCTIONS_WITH_ARGUMENTS.add("sipush");
        INSTRUCTIONS_WITH_ARGUMENTS.add("tableswitch");
        INSTRUCTIONS_WITH_ARGUMENTS.add("wide");
        // </editor-fold>
    }


    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result)
    {
        for (Map.Entry<String, String> entry : INSTRUCTIONS.entrySet())
        {
            String instructionName = entry.getKey();
            String description = entry.getValue();
            LookupElementBuilder lookupElement = LookupElementBuilder.create(instructionName)
                                                                     .withTypeText(description)
                                                                     .withInsertHandler(createInsertHandler(
                                                                             instructionName))
                                                                     .withCaseSensitivity(true);
            result.addElement(lookupElement);

        }
    }

    private static InsertHandler<LookupElement> createInsertHandler(String instructionName)
    {

        if (INSTRUCTIONS_WITH_ARGUMENTS.contains(instructionName))
            return (ctxt, item) -> {
                Editor editor = ctxt.getEditor();
                Document doc = editor.getDocument();
                int offset = editor.getCaretModel().getOffset();
                // 現在のカーソル位置にスペースを挿入して移動
                doc.insertString(offset, " ");
                editor.getCaretModel().moveToOffset(offset + 1);
            };
        else
            return JALCompletionCommons.insertAndNewLine();
    }
}
