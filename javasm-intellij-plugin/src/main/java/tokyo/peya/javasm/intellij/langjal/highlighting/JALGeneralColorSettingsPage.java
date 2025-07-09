package tokyo.peya.javasm.intellij.langjal.highlighting;

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

import java.util.Map;

public class JALGeneralColorSettingsPage implements ColorSettingsPage
{
    private static final AttributesDescriptor[] DESCRIPTORS = {
            new AttributesDescriptor("Comment//Block comment", JALSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Comment//Line comment", JALSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Literals//Number", JALSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Literals//String", JALSyntaxHighlighter.STRING),
            new AttributesDescriptor("Literals//Braces", JALSyntaxHighlighter.BRACES),
            new AttributesDescriptor("Symbols//Semicolon", JALSyntaxHighlighter.SEMICOLON),
            new AttributesDescriptor("Symbols//Comma", JALSyntaxHighlighter.COMMA),
            new AttributesDescriptor("Symbols//Parenthesis", JALSyntaxHighlighter.PARENTHESIS),
            new AttributesDescriptor("Symbols//Brackets", JALSyntaxHighlighter.BRACKETS),

            new AttributesDescriptor("Label", JALSyntaxHighlighter.LABEL),
            new AttributesDescriptor("Members//Method name", JALSyntaxHighlighter.METHOD_NAME),
            new AttributesDescriptor("Members//Method call", JALSyntaxHighlighter.METHOD_CALL),
            new AttributesDescriptor("Members//Class name", JALSyntaxHighlighter.CLASS_NAME),
            new AttributesDescriptor("Members//Parameter", JALSyntaxHighlighter.PARAMETER),

            new AttributesDescriptor("Types//Byte(B)", JALSyntaxHighlighter.DESC_BYTE),
            new AttributesDescriptor("Types//Short(S)", JALSyntaxHighlighter.DESC_SHORT),
            new AttributesDescriptor("Types//Char(C)", JALSyntaxHighlighter.DESC_CHAR),
            new AttributesDescriptor("Types//Int(I)", JALSyntaxHighlighter.DESC_INT),
            new AttributesDescriptor("Types//Long(J)", JALSyntaxHighlighter.DESC_LONG),
            new AttributesDescriptor("Types//Float(F)", JALSyntaxHighlighter.DESC_FLOAT),
            new AttributesDescriptor("Types//Double(D)", JALSyntaxHighlighter.DESC_DOUBLE),
            new AttributesDescriptor("Types//Boolean(Z)", JALSyntaxHighlighter.DESC_BOOLEAN),
            new AttributesDescriptor("Types//Void(V)", JALSyntaxHighlighter.DESC_VOID),

            new AttributesDescriptor("Identifier", JALSyntaxHighlighter.ID),
            new AttributesDescriptor("Keywords", JALSyntaxHighlighter.KEYWORD),

    };


    @Override
    public @Nullable Icon getIcon()
    {
        return Assets.JAL_ICON;
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
                 \s
                   label:
                   test:
                
                    // Nop
                    nop
                
                    // Variable controls
                    aaload
                    aload_0
                    aload 255
                
                    // New instances
                    new Ljava/lang/String;
                    newarray Ljava/lang/String;
                    anewarray Ljava/lang/String;
                    multianewarray Ljava/lang/String; 3
                
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
                
                    // Value calculation\s
                    dadd
                    idiv
                
                    // Value comparison
                    dcmpg
                    dcmpl
                    ddiv
                    drem
                
                    // Stack controlling
                    dup
                    dup2_x2
                    nop
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
                 \s
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
        return "Java Assembly Language (JAL) - General";
    }
}
