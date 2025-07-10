package tokyo.peya.javasm.intellij.langjal.highlighting;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.langjal.compiler.JALLexer;
import tokyo.peya.javasm.langjal.compiler.JALParser;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class JALSyntaxHighlighter extends SyntaxHighlighterBase
{
    public static final TextAttributesKey ID =
            createTextAttributesKey("JAL_ID", DefaultLanguageHighlighterColors.IDENTIFIER);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("JAL_NUMBER", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("JAL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey STRING =
            createTextAttributesKey("JAL_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("JAL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("JAL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BRACES =
            createTextAttributesKey("JAL_BRACES", DefaultLanguageHighlighterColors.BRACES);
    public static final TextAttributesKey SEMICOLON =
            createTextAttributesKey("JAL_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("JAL_COMMA", DefaultLanguageHighlighterColors.COMMA);
    public static final TextAttributesKey PARENTHESIS =
            createTextAttributesKey("JAL_PARENTHESIS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("JAL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey LABEL =
            createTextAttributesKey("JAL_LABEL", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey METHOD_NAME =
            createTextAttributesKey("JAL_METHOD_NAME", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey METHOD_CALL =
            createTextAttributesKey("JAL_METHOD_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL);
    public static final TextAttributesKey PARAMETER =
            createTextAttributesKey("JAL_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey CLASS_NAME =
            createTextAttributesKey("JAL_CLASS_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey FIELD_NAME =
            createTextAttributesKey("JAL_FIELD_NAME", DefaultLanguageHighlighterColors.INSTANCE_FIELD);
    public static final TextAttributesKey DESC_BOOLEAN =
            createTextAttributesKey("JAL_DESC_BOOLEAN", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_BYTE =
            createTextAttributesKey("JAL_DESC_BYTE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_CHAR =
            createTextAttributesKey("JAL_DESC_CHAR", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_SHORT =
            createTextAttributesKey("JAL_DESC_SHORT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_INT =
            createTextAttributesKey("JAL_DESC_INT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_LONG =
            createTextAttributesKey("JAL_DESC_LONG", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_FLOAT =
            createTextAttributesKey("JAL_DESC_FLOAT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_DOUBLE =
            createTextAttributesKey("JAL_DESC_DOUBLE", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey DESC_VOID =
            createTextAttributesKey("JAL_DESC_VOID", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_NOP =
            createTextAttributesKey("JAL_INSN_NOP", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_VARIABLE_ACCESS =
            createTextAttributesKey("JAL_INSN_VARIABLES_ACCESS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_INSTANCE_CREATION =
            createTextAttributesKey("JAL_INSN_INSTANCE_CREATION", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_FLOW_CONTROLS =
            createTextAttributesKey("JAL_INSN_FLOW_CONTROLS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_FLOW_JUMPS =
            createTextAttributesKey("JAL_INSN_FLOW_JUMP", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_VALUE_GENERATIONS =
            createTextAttributesKey("JAL_INSN_VALUE_GENERATIONS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_VALUE_CASTINGS =
            createTextAttributesKey("JAL_INSN_VALUE_CASTINGS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_VALUE_CALCULATIONS =
            createTextAttributesKey("JAL_INSN_VALUE_CALCULATIONS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_VALUE_LOGICAL_CALCULATIONS =
            createTextAttributesKey("JAL_INSN_VALUE_LOGICAL_CALCULATIONS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_VALUE_COMPARISONS =
            createTextAttributesKey("JAL_INSN_VALUE_COMPARISONS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_STACK_CONTROLS =
            createTextAttributesKey("JAL_INSN_STACK_CONTROLS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_FIELD_ACCESS =
            createTextAttributesKey("JAL_INSN_FIELD_ACCESS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_METHOD_INVOCATIONS =
            createTextAttributesKey("JAL_INSN_METHOD_INVOCATIONS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_ARRAY_ACCESS =
            createTextAttributesKey("JAL_INSN_ARRAY_ACCESS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_ARRAY_CREATIONS =
            createTextAttributesKey("JAL_INSN_ARRAY_CREATIONS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_SWITCH =
            createTextAttributesKey("JAL_INSN_SWITCH", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_MONITORING =
            createTextAttributesKey("JAL_INSN_MONITORING", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey INSN_WIDE =
            createTextAttributesKey("JAL_INSN_WIDE", DefaultLanguageHighlighterColors.KEYWORD);
    private static final TextAttributesKey[] EMPTY = new TextAttributesKey[0];

    static
    {
        initialise();
    }

    @Override
    public @NotNull Lexer getHighlightingLexer()
    {
        return new ANTLRLexerAdaptor(JALLanguage.INSTANCE, new JALLexer(null));
    }

    @Nullable
    private TextAttributesKey highlightsToken(TokenIElementType token)
    {
        return switch (token.getANTLRTokenType())
        {
            case JALLexer.ID -> ID;
            case JALLexer.NUMBER -> NUMBER;
            case JALLexer.STRING -> STRING;
            case JALLexer.LINE_COMMENT -> COMMENT;
            case JALLexer.BLOCK_COMMENT -> BLOCK_COMMENT;
            case JALLexer.LBR,
                 JALLexer.RBR -> BRACES;
            case JALLexer.SEMI -> SEMICOLON;
            case JALLexer.COMMA -> COMMA;
            case JALLexer.LP,
                 JALLexer.RP -> PARENTHESIS;
            case JALLexer.LBK,
                 JALLexer.RBK -> BRACKETS;
            case JALLexer.KWD_CLASS_PROP_MINOR,
                 JALLexer.KWD_CLASS_PROP_MAJOR,
                 JALLexer.KWD_CLASS_PROP_SUPER_CLASS,
                 JALLexer.KWD_CLASS_PROP_INTERFACES -> PARAMETER;
            case JALLexer.TYPE_DESC_OBJECT -> CLASS_NAME;

            case JALLexer.TYPE_DESC_BOOLEAN -> DESC_BOOLEAN;
            case JALLexer.TYPE_DESC_BYTE -> DESC_BYTE;
            case JALLexer.TYPE_DESC_CHAR -> DESC_CHAR;
            case JALLexer.TYPE_DESC_SHORT -> DESC_SHORT;
            case JALLexer.TYPE_DESC_INT -> DESC_INT;
            case JALLexer.TYPE_DESC_LONG -> DESC_LONG;
            case JALLexer.TYPE_DESC_FLOAT -> DESC_FLOAT;
            case JALLexer.TYPE_DESC_DOUBLE -> DESC_DOUBLE;
            case JALLexer.TYPE_DESC_VOID -> DESC_VOID;

            case JALLexer.KWD_ACC_PUBLIC,
                 JALLexer.KWD_ACC_PRIVATE,
                 JALLexer.KWD_ACC_PROTECTED,
                 JALLexer.KWD_ACC_ATTR_STATIC,
                 JALLexer.KWD_ACC_ATTR_FINAL,
                 JALLexer.KWD_ACC_ATTR_SUPER,
                 JALLexer.KWD_ACC_ATTR_SYNCHRONIZED,
                 JALLexer.KWD_ACC_ATTR_BRIDGE,
                 JALLexer.KWD_ACC_ATTR_VARARGS,
                 JALLexer.KWD_ACC_ATTR_NATIVE,
                 JALLexer.KWD_ACC_ATTR_ABSTRACT,
                 JALLexer.KWD_ACC_ATTR_STRICTFP,
                 JALLexer.KWD_ACC_ATTR_VOLATILE,
                 JALLexer.KWD_ACC_ATTR_TRANSIENT,
                 JALLexer.KWD_ACC_ATTR_SYNTHETIC,
                 JALLexer.KWD_ACC_ATTR_ANNOTATION,
                 JALLexer.KWD_ACC_ATTR_ENUM,
                 JALLexer.KWD_CLASS,
                 JALLexer.KWD_INTERFACE,
                 JALLexer.KWD_METHOD_HANDLE,
                 JALLexer.KWD_METHOD_TYPE,
                 JALLexer.KWD_SWITCH_DEFAULT -> KEYWORD;

            case JALLexer.INSN_NOP -> INSN_NOP;
            case JALLexer.INSN_ALOAD,
                 JALLexer.INSN_ALOAD_0,
                 JALLexer.INSN_ALOAD_1,
                 JALLexer.INSN_ALOAD_2,
                 JALLexer.INSN_ALOAD_3,
                 JALLexer.INSN_ALOAD_4,
                 JALLexer.INSN_ASTORE,
                 JALLexer.INSN_ASTORE_0,
                 JALLexer.INSN_ASTORE_1,
                 JALLexer.INSN_ASTORE_2,
                 JALLexer.INSN_ASTORE_3,
                 JALLexer.INSN_DLOAD,
                 JALLexer.INSN_DLOAD_0,
                 JALLexer.INSN_DLOAD_1,
                 JALLexer.INSN_DLOAD_2,
                 JALLexer.INSN_DLOAD_3,
                 JALLexer.INSN_DSTORE,
                 JALLexer.INSN_DSTORE_0,
                 JALLexer.INSN_DSTORE_1,
                 JALLexer.INSN_DSTORE_2,
                 JALLexer.INSN_DSTORE_3,
                 JALLexer.INSN_FLOAD,
                 JALLexer.INSN_FLOAD_0,
                 JALLexer.INSN_FLOAD_1,
                 JALLexer.INSN_FLOAD_2,
                 JALLexer.INSN_FLOAD_3,
                 JALLexer.INSN_FSTORE,
                 JALLexer.INSN_FSTORE_0,
                 JALLexer.INSN_FSTORE_1,
                 JALLexer.INSN_FSTORE_2,
                 JALLexer.INSN_FSTORE_3,
                 JALLexer.INSN_ILOAD,
                 JALLexer.INSN_ILOAD_0,
                 JALLexer.INSN_ILOAD_1,
                 JALLexer.INSN_ILOAD_2,
                 JALLexer.INSN_ILOAD_3,
                 JALLexer.INSN_ISTORE,
                 JALLexer.INSN_ISTORE_0,
                 JALLexer.INSN_ISTORE_1,
                 JALLexer.INSN_ISTORE_2,
                 JALLexer.INSN_ISTORE_3,
                 JALLexer.INSN_LLOAD,
                 JALLexer.INSN_LLOAD_0,
                 JALLexer.INSN_LLOAD_1,
                 JALLexer.INSN_LLOAD_2,
                 JALLexer.INSN_LLOAD_3,
                 JALLexer.INSN_LSTORE,
                 JALLexer.INSN_LSTORE_0,
                 JALLexer.INSN_LSTORE_1,
                 JALLexer.INSN_LSTORE_2,
                 JALLexer.INSN_LSTORE_3 -> INSN_VARIABLE_ACCESS;
            case JALLexer.INSN_NEW -> INSN_INSTANCE_CREATION;
            case JALLexer.INSN_ACONST_NULL,
                 JALLexer.INSN_BIPUSH,
                 JALLexer.INSN_DCONST_0,
                 JALLexer.INSN_DCONST_1,
                 JALLexer.INSN_FCONST_0,
                 JALLexer.INSN_FCONST_1,
                 JALLexer.INSN_FCONST_2,
                 JALLexer.INSN_ICONST_0,
                 JALLexer.INSN_ICONST_1,
                 JALLexer.INSN_ICONST_2,
                 JALLexer.INSN_ICONST_3,
                 JALLexer.INSN_ICONST_4,
                 JALLexer.INSN_ICONST_5,
                 JALLexer.INSN_LCONST_0,
                 JALLexer.INSN_LCONST_1,
                 JALLexer.INSN_LDC,
                 JALLexer.INSN_LDC_W,
                 JALLexer.INSN_LDC2_W,
                 JALLexer.INSN_SIPUSH -> INSN_VALUE_GENERATIONS;
            case JALLexer.INSN_ARETURN,
                 JALLexer.INSN_ATHROW,
                 JALLexer.INSN_DRETURN,
                 JALLexer.INSN_FRETURN,
                 JALLexer.INSN_IRETURN,
                 JALLexer.INSN_LRETURN,
                 JALLexer.INSN_RETURN -> INSN_FLOW_CONTROLS;
            case JALLexer.INSN_GOTO,
                 JALLexer.INSN_GOTO_W,
                 JALLexer.INSN_IF_ACMPEQ,
                 JALLexer.INSN_IF_ACMPNE,
                 JALLexer.INSN_IF_ICMPEQ,
                 JALLexer.INSN_IF_ICMPGE,
                 JALLexer.INSN_IF_ICMPGT,
                 JALLexer.INSN_IF_ICMPLE,
                 JALLexer.INSN_IF_ICMPLT,
                 JALLexer.INSN_IF_ICMPNE,
                 JALLexer.INSN_IFEQ,
                 JALLexer.INSN_IFGE,
                 JALLexer.INSN_IFGT,
                 JALLexer.INSN_IFLE,
                 JALLexer.INSN_IFLT,
                 JALLexer.INSN_IFNE,
                 JALLexer.INSN_IFNONNULL,
                 JALLexer.INSN_IFNULL,
                 JALLexer.INSN_JSR,
                 JALLexer.INSN_JSR_W,
                 JALLexer.INSN_RET -> INSN_FLOW_JUMPS;
            case JALLexer.INSN_D2F,
                 JALLexer.INSN_D2I,
                 JALLexer.INSN_D2L,
                 JALLexer.INSN_F2D,
                 JALLexer.INSN_F2I,
                 JALLexer.INSN_F2L,
                 JALLexer.INSN_I2B,
                 JALLexer.INSN_I2C,
                 JALLexer.INSN_I2D,
                 JALLexer.INSN_I2F,
                 JALLexer.INSN_I2L,
                 JALLexer.INSN_I2S,
                 JALLexer.INSN_L2D,
                 JALLexer.INSN_L2F,
                 JALLexer.INSN_L2I -> INSN_VALUE_CASTINGS;
            case JALLexer.INSN_DADD,
                 JALLexer.INSN_DDIV,
                 JALLexer.INSN_DMUL,
                 JALLexer.INSN_DREM,
                 JALLexer.INSN_DSUB,
                 JALLexer.INSN_FADD,
                 JALLexer.INSN_FDIV,
                 JALLexer.INSN_FMUL,
                 JALLexer.INSN_FREM,
                 JALLexer.INSN_FSUB,
                 JALLexer.INSN_IADD,
                 JALLexer.INSN_IDIV,
                 JALLexer.INSN_IINC,
                 JALLexer.INSN_IMUL,
                 JALLexer.INSN_IREM,
                 JALLexer.INSN_ISHL,
                 JALLexer.INSN_ISHR,
                 JALLexer.INSN_ISUB,
                 JALLexer.INSN_IUSHR,
                 JALLexer.INSN_LADD,
                 JALLexer.INSN_LDIV,
                 JALLexer.INSN_LMUL,
                 JALLexer.INSN_LREM,
                 JALLexer.INSN_LSHL,
                 JALLexer.INSN_LSHR,
                 JALLexer.INSN_LSUB,
                 JALLexer.INSN_LUSHR -> INSN_VALUE_CALCULATIONS;
            case JALLexer.INSN_IAND,
                 JALLexer.INSN_INEG,
                 JALLexer.INSN_IOR,
                 JALLexer.INSN_IXOR,
                 JALLexer.INSN_LAND,
                 JALLexer.INSN_LNEG,
                 JALLexer.INSN_LOR,
                 JALLexer.INSN_LXOR -> INSN_VALUE_LOGICAL_CALCULATIONS;
            case JALLexer.INSN_DCMPL,
                 JALLexer.INSN_CHECKCAST,
                 JALLexer.INSN_DCMPG,
                 JALLexer.INSN_FCMPL,
                 JALLexer.INSN_FCMPG,
                 JALLexer.INSN_INSTANCEOF,
                 JALLexer.INSN_LCMP -> INSN_VALUE_COMPARISONS;
            case JALLexer.INSN_DUP,
                 JALLexer.INSN_DUP2,
                 JALLexer.INSN_DUP2_X1,
                 JALLexer.INSN_DUP2_X2,
                 JALLexer.INSN_DUP_X1,
                 JALLexer.INSN_DUP_X2,
                 JALLexer.INSN_POP,
                 JALLexer.INSN_POP2,
                 JALLexer.INSN_SWAP -> INSN_STACK_CONTROLS;
            case JALLexer.INSN_GETFIELD,
                 JALLexer.INSN_GETSTATIC,
                 JALLexer.INSN_PUTFIELD,
                 JALLexer.INSN_PUTSTATIC -> INSN_FIELD_ACCESS;
            case JALLexer.INSN_INVOKEINTERFACE,
                 JALLexer.INSN_INVOKESPECIAL,
                 JALLexer.INSN_INVOKESTATIC,
                 JALLexer.INSN_INVOKEVIRTUAL,
                 JALLexer.INSN_INVOKEDYNAMIC -> INSN_METHOD_INVOCATIONS;
            case JALLexer.INSN_AALOAD,
                 JALLexer.INSN_AASTORE,
                 JALLexer.INSN_BALOAD,
                 JALLexer.INSN_BASTORE,
                 JALLexer.INSN_CALOAD,
                 JALLexer.INSN_CASTORE,
                 JALLexer.INSN_DALOAD,
                 JALLexer.INSN_DASTORE,
                 JALLexer.INSN_FALOAD,
                 JALLexer.INSN_FASTORE,
                 JALLexer.INSN_IALOAD,
                 JALLexer.INSN_IASTORE,
                 JALLexer.INSN_LALOAD,
                 JALLexer.INSN_LASTORE,
                 JALLexer.INSN_SALOAD,
                 JALLexer.INSN_SASTORE -> INSN_ARRAY_ACCESS;
            case JALLexer.INSN_ANEWARRAY,
                 JALLexer.INSN_ARRAYLENGTH,
                 JALLexer.INSN_MULTIANEWARRAY,
                 JALLexer.INSN_NEWARRAY -> INSN_ARRAY_CREATIONS;
            case JALLexer.INSN_LOOKUPSWITCH,
                 JALLexer.INSN_TABLESWITCH -> INSN_SWITCH;
            case JALLexer.INSN_MONITORENTER,
                 JALLexer.INSN_MONITOREXIT -> INSN_MONITORING;
            case JALLexer.INSN_WIDE -> INSN_WIDE;
            default -> null;
        };
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType elementType)
    {
        TextAttributesKey key = null;
        if (elementType instanceof TokenIElementType tokenType)
            key = highlightsToken(tokenType);

        if (key == null)
            return EMPTY;
        else
            return new TextAttributesKey[]{key};
    }

    @SuppressWarnings("deprecation")
    private static void initialise()
    {
        PSIElementTypeFactory.defineLanguageIElementTypes(
                JALLanguage.INSTANCE,
                JALParser.tokenNames,
                JALParser.ruleNames
        );
    }
}
