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
import tokyo.peya.javasm.intellij.utils.JALMessages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JALInstructionNameCompletionProvider extends CompletionProvider<CompletionParameters> {
    private static final Map<String, String> INSTRUCTIONS;
    private static final HashSet<String> INSTRUCTIONS_WITH_ARGUMENTS;

    private static Map.Entry<String, String> instruction(String instructionName) {
        return Map.entry(instructionName, JALMessages.message("jal.completion.instruction." + instructionName));
    }

    static {
        HashMap<String, String> instructions = new LinkedHashMap<>();
        // <editor-fold desc="Instruction Definitions">
        instructions.putAll(Map.ofEntries(
                instruction("aaload"),
                instruction("aastore"),
                instruction("aconst_null"),
                instruction("aload"),
                instruction("aload_0"),
                instruction("aload_1"),
                instruction("aload_2"),
                instruction("aload_3"),
                instruction("anewarray"),
                instruction("areturn"),
                instruction("arraylength"),
                instruction("athrow"),
                instruction("checkcast"),
                instruction("instanceof")
        ));
        instructions.putAll(Map.ofEntries(
                instruction("baload"),
                instruction("bastore"),
                instruction("bipush")
        ));
        instructions.putAll(Map.ofEntries(
                instruction("caload"),
                instruction("castore")
        ));
        instructions.putAll(Map.ofEntries(
                instruction("d2f"),
                instruction("d2i"),
                instruction("d2l"),
                instruction("dadd"),
                instruction("daload"),
                instruction("dastore"),
                instruction("dcmpg"),
                instruction("dcmpl"),
                instruction("dconst_0"),
                instruction("dconst_1"),
                instruction("ddiv"),
                instruction("dload"),
                instruction("dload_0"),
                instruction("dload_1"),
                instruction("dload_2"),
                instruction("dload_3"),
                instruction("dmul"),
                instruction("dneg"),
                instruction("drem"),
                instruction("dreturn"),
                instruction("dstore"),
                instruction("dstore_0"),
                instruction("dstore_1"),
                instruction("dstore_2"),
                instruction("dstore_3"),
                instruction("dsub")
        ));
        instructions.putAll(Map.ofEntries(
                instruction("dup"),
                instruction("dup_x1"),
                instruction("dup_x2"),
                instruction("dup2"),
                instruction("dup2_x1"),
                instruction("dup2_x2"),
                instruction("pop"),
                instruction("pop2"),
                instruction("swap")
        ));
        instructions.putAll(Map.ofEntries(
                instruction("f2d"),
                instruction("f2i"),
                instruction("f2l"),
                instruction("fadd"),
                instruction("faload"),
                instruction("fastore"),
                instruction("fcmpg"),
                instruction("fcmpl"),
                instruction("fconst_0"),
                instruction("fconst_1"),
                instruction("fconst_2"),
                instruction("fdiv"),
                instruction("fload"),
                instruction("fload_0"),
                instruction("fload_1"),
                instruction("fload_2"),
                instruction("fload_3"),
                instruction("fmul"),
                instruction("fneg"),
                instruction("frem"),
                instruction("freturn"),
                instruction("fstore"),
                instruction("fstore_0"),
                instruction("fstore_1"),
                instruction("fstore_2"),
                instruction("fstore_3"),
                instruction("fsub")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("i2b"),
                instruction("i2c"),
                instruction("i2d"),
                instruction("i2f"),
                instruction("i2l"),
                instruction("i2s"),
                instruction("iadd"),
                instruction("iaload"),
                instruction("iand"),
                instruction("iastore"),
                instruction("iconst_m1"),
                instruction("iconst_0"),
                instruction("iconst_1"),
                instruction("iconst_2"),
                instruction("iconst_3"),
                instruction("iconst_4"),
                instruction("iconst_5"),
                instruction("idiv"),
                instruction("iinc"),
                instruction("iload"),
                instruction("iload_0"),
                instruction("iload_1"),
                instruction("iload_2"),
                instruction("iload_3"),
                instruction("imul"),
                instruction("ineg"),
                instruction("ior"),
                instruction("irem"),
                instruction("ireturn"),
                instruction("ishl"),
                instruction("ishr"),
                instruction("istore"),
                instruction("istore_0"),
                instruction("istore_1"),
                instruction("istore_2"),
                instruction("istore_3"),
                instruction("isub"),
                instruction("iushr"),
                instruction("ixor")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("goto"),
                instruction("goto_w"),
                instruction("if_acmpeq"),
                instruction("if_acmpne"),
                instruction("if_icmpeq"),
                instruction("if_icmpne"),
                instruction("if_icmplt"),
                instruction("if_icmpge"),
                instruction("if_icmpgt"),
                instruction("if_icmple"),
                instruction("ifeq"),
                instruction("ifne"),
                instruction("iflt"),
                instruction("ifge"),
                instruction("ifgt"),
                instruction("ifle"),
                instruction("ifnonnull"),
                instruction("ifnull"),
                instruction("jsr"),
                instruction("jsr_w"),
                instruction("lookupswitch"),
                instruction("ret"),
                instruction("return"),
                instruction("tableswitch")
        ));
        instructions.putAll(Map.ofEntries(
                instruction("getfield"),
                instruction("getstatic"),
                instruction("putfield"),
                instruction("putstatic")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("invokedynamic"),
                instruction("invokeinterface"),
                instruction("invokespecial"),
                instruction("invokestatic"),
                instruction("invokevirtual")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("l2d"),
                instruction("l2f"),
                instruction("l2i"),
                instruction("ladd"),
                instruction("laload"),
                instruction("land"),
                instruction("lastore"),
                instruction("lcmp"),
                instruction("lconst_0"),
                instruction("lconst_1"),
                instruction("ldiv"),
                instruction("lload"),
                instruction("lload_0"),
                instruction("lload_1"),
                instruction("lload_2"),
                instruction("lload_3"),
                instruction("lmul"),
                instruction("lneg"),
                instruction("lor"),
                instruction("lrem"),
                instruction("lreturn"),
                instruction("lshl"),
                instruction("lshr"),
                instruction("lstore"),
                instruction("lstore_0"),
                instruction("lstore_1"),
                instruction("lstore_2"),
                instruction("lstore_3"),
                instruction("lsub"),
                instruction("lushr"),
                instruction("lxor")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("ldc"),
                instruction("ldc_w"),
                instruction("ldc2_w")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("monitorenter"),
                instruction("monitorexit")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("multianewarray"),
                instruction("newarray")
        ));

        instructions.putAll(Map.ofEntries(
                instruction("saload"),
                instruction("sastore"),
                instruction("sipush")
        ));

        instructions.putAll(
                Map.ofEntries(
                        instruction("new"),
                        instruction("nop"),
                        instruction("wide")
                )
        );
        // </editor-fold>
        // Value のほうで昇順にソートする。

        INSTRUCTIONS = instructions.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // 昇順
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // マージ関数は無視
                        LinkedHashMap::new // LinkedHashMap に詰める
                ));
    }

    static {
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

    private static InsertHandler<LookupElement> createInsertHandler(String instructionName) {

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

    @Override
    protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context,
                                  @NotNull CompletionResultSet result) {
        for (Map.Entry<String, String> entry : INSTRUCTIONS.entrySet()) {
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
}
