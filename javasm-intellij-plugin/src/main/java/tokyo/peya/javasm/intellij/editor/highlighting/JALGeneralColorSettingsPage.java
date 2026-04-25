package tokyo.peya.javasm.intellij.editor.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.NlsContexts;
import javax.swing.Icon;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.Assets;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import java.util.Map;

public class JALGeneralColorSettingsPage implements ColorSettingsPage
{
    private static final AttributesDescriptor[] DESCRIPTORS = {
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.comment.block"), JALSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.comment.line"), JALSyntaxHighlighter.COMMENT),

            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.literals.number"), JALSyntaxHighlighter.NUMBER),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.literals.string"), JALSyntaxHighlighter.STRING),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.literals.braces"), JALSyntaxHighlighter.BRACES),

            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.symbols.semicolon"), JALSyntaxHighlighter.SEMICOLON),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.symbols.comma"), JALSyntaxHighlighter.COMMA),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.symbols.parenthesis"), JALSyntaxHighlighter.PARENTHESIS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.symbols.brackets"), JALSyntaxHighlighter.BRACKETS),

            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.members.method.name"), JALSyntaxHighlighter.METHOD_NAME),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.members.method.call"), JALSyntaxHighlighter.METHOD_CALL),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.members.class.name"), JALSyntaxHighlighter.CLASS_NAME),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.members.parameter"), JALSyntaxHighlighter.PARAMETER),

            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.byte"), JALSyntaxHighlighter.DESC_BYTE),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.short"), JALSyntaxHighlighter.DESC_SHORT),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.char"), JALSyntaxHighlighter.DESC_CHAR),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.int"), JALSyntaxHighlighter.DESC_INT),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.long"), JALSyntaxHighlighter.DESC_LONG),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.float"), JALSyntaxHighlighter.DESC_FLOAT),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.double"), JALSyntaxHighlighter.DESC_DOUBLE),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.boolean"), JALSyntaxHighlighter.DESC_BOOLEAN),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.types.void"), JALSyntaxHighlighter.DESC_VOID),

            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.nop"), JALSyntaxHighlighter.INSN_NOP),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.variable.access"), JALSyntaxHighlighter.INSN_VARIABLE_ACCESS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.instance.creation"), JALSyntaxHighlighter.INSN_INSTANCE_CREATION),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.flow.controls"), JALSyntaxHighlighter.INSN_FLOW_CONTROLS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.flow.jumps"), JALSyntaxHighlighter.INSN_FLOW_JUMPS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.value.generations"), JALSyntaxHighlighter.INSN_VALUE_GENERATIONS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.value.castings"), JALSyntaxHighlighter.INSN_VALUE_CASTINGS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.value.calculations"), JALSyntaxHighlighter.INSN_VALUE_CALCULATIONS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.value.logical.calculations"), JALSyntaxHighlighter.INSN_VALUE_LOGICAL_CALCULATIONS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.value.comparisons"), JALSyntaxHighlighter.INSN_VALUE_COMPARISONS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.stack.controls"), JALSyntaxHighlighter.INSN_STACK_CONTROLS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.field.access"), JALSyntaxHighlighter.INSN_FIELD_ACCESS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.method.invocations"), JALSyntaxHighlighter.INSN_METHOD_INVOCATIONS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.switch"), JALSyntaxHighlighter.INSN_SWITCH),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.array.access"), JALSyntaxHighlighter.INSN_ARRAY_ACCESS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.array.creations"), JALSyntaxHighlighter.INSN_ARRAY_CREATIONS),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.monitoring"), JALSyntaxHighlighter.INSN_MONITORING),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.instructions.wide"), JALSyntaxHighlighter.INSN_WIDE),

            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.label"), JALSyntaxHighlighter.LABEL),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.identifier"), JALSyntaxHighlighter.ID),
            new AttributesDescriptor(JALMessages.message("jal.editor.highlight.settings.keyword"), JALSyntaxHighlighter.KEYWORD),
    };

    @Override
    public @Nullable Icon getIcon()
    {
        return Assets.JAL;
    }

    @Override
    public @NotNull SyntaxHighlighter getHighlighter()
    {
        return new JALSyntaxHighlighter();
    }

    @Override
    public @NonNls @NotNull String getDemoText()
    {
        return """
                public class TestClas (
                  major_version=65,
                  minor_version=0) {
                  public static myField: Ljava/lang/String;
                
                  public static main([Ljava/lang/String;)V {
                
                  }
                
                  <clinit>()V {
                  }
                
                  <init>()V {
                   label:
                   test:
                
                    // Nop
                    nop
                
                    // Variable access
                    aload_0
                    aload 255
                
                    // Instance creation
                    new Ljava/lang/String;
                
                    // Value generation
                    sipush 42
                    bipush 42
                    iconst_1
                    lconst_1
                    ldc "135135"
                    ldc_w "135135135135"
                    ldc2_w "1234567890123456789L"
                
                    // Flow controls
                    return
                    dreturn
                    athrow
                
                    // Flow jumps
                    goto test
                    if_acmpeq test
                    if_acmpne test
                    ifnonnull test
                    ret 0
                    jsr
                
                    // Value casting
                    d2f
                    f2l
                
                    // Value calculations
                    dadd
                    idiv
                
                    // Value comparison
                    dcmpg
                    dcmpl
                    lcmp
                
                
                    // Stack controlling
                    dup
                    dup2_x2
                    pop
                    swap
                
                    // Field accessing
                    getfield java/lang/Strin->awdw$1_35:Ljava/lang/String;
                    getstatic java/lang/System->out:Ljava/io/PrintStream;
                    putfield java/lang/String->awdw$1_35:Ljava/lang/String;
                    putstatic java/lang/String->myString:Ljava/lang/String;
                
                    // Method invocation
                
                    invokedynamic myMethod (Ljava/lang/String;)Ljava/lang/String; MethodHandle|getstatic|java/lang/String->awdw$1_35(Ljava/lang/String;)Ljava/lang/String; MethodType|(Ljava/lang/String;)V
                    invokeinterface java/lang/String->awdw$1_35(Ljava/lang/String;)Ljava/lang/String;
                    invokespecial java/lang/String-><init>(Ljava/lang/String;Ljava/lang/String;)V
                    invokeinterface java/lang/String->awdw$1_35(Ljava/lang/String;)Ljava/lang/String;
                
                    // Array access
                    aaload
                    iastore
                
                    // Array creations
                    newarray Ljava/lang/String;
                    anewarray Ljava/lang/String;
                    multianewarray Ljava/lang/String; 3
                
                
                    // Switch
                    lookupswitch {
                      0x00: test;
                      default: test
                    }   \s
                    tableswitch 1 {
                       test
                    } default awd
                
                    // synchronised(obj) {}
                    monitorenter
                    monitorexit
                
                
                    // Wide
                    wide aload 24
                  }
                
                }
                """;
    }

    @Override
    public @Nullable Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
    {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors()
    {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors()
    {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @Override
    public @NotNull @NlsContexts.ConfigurableName String getDisplayName()
    {
        return JALMessages.message("jal.editor.highlight.settings.title");
    }
}
