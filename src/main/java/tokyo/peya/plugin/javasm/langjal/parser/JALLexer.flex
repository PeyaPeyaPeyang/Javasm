package tokyo.peya.plugin.javasm.langjal;

import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static tokyo.peya.plugin.javasm.langjal.psi.JALTypes.*;

%%

%{
  public JALLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class JALLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

INSN_ARG_UNSIG_8BYTES=0x[0-9a-fA-F]{1,8}|[0-9]+
TYPE_DESC=\[*([BCDFIJSZ]|L([^;\n\r]+);)
METHOD_DESC=\((\[*([BCDFIJSZV]|L([^;\n\r]+);))*\)(\[*[BCDFIJSZV]|L([^;\n\r]+);)?
SPACE=[ \t\n\x0B\f\r]+
ID=[\w$]+
STRING=('([^'\\]|\\.)*'|\"([^\"\\]|\\\"|\'|\\)*\")
NUMBER=[0-9]+
LINE_COMMENT="//".*
BLOCK_COMMENT="/"\*(.|\n)*\*"/"

%%
<YYINITIAL> {
  {WHITE_SPACE}                 { return WHITE_SPACE; }

  ";"                           { return SEMI; }
  ","                           { return COMMA; }
  ":"                           { return COLON; }
  "."                           { return DOT; }
  "="                           { return EQ; }
  "("                           { return LP; }
  ")"                           { return RP; }
  "{"                           { return LBR; }
  "}"                           { return RBR; }
  "["                           { return LBK; }
  "]"                           { return RBK; }
  "class"                       { return KWD_CLASS; }
  "interface"                   { return KWD_INTERFACE; }
  "public"                      { return KWD_ACC_PUBLIC; }
  "private"                     { return KWD_ACC_PRIVATE; }
  "protected"                   { return KWD_ACC_PROTECTED; }
  "static"                      { return KWD_ACC_ATTR_STATIC; }
  "final"                       { return KWD_ACC_ATTR_FINAL; }
  "abstract"                    { return KWD_ACC_ATTR_ABSTRACT; }
  "native"                      { return KWD_ACC_ATTR_NATIVE; }
  "synchronized"                { return KWD_ACC_ATTR_SYNCHRONIZED; }
  "transient"                   { return KWD_ACC_ATTR_TRANSIENT; }
  "volatile"                    { return KWD_ACC_ATTR_VOLATILE; }
  "strictfp"                    { return KWD_ACC_ATTR_STRICTFP; }
  "synthetic"                   { return KWD_ACC_ATTR_SYNTHETIC; }
  "<init>"                      { return KWD_MNAME_INIT; }
  "<clinit>"                    { return KWD_MNAME_CLINIT; }
  "default"                     { return KWD_SWITCH_DEFAULT; }
  "aaload"                      { return INSN_AALOAD; }
  "aastore"                     { return INSN_AASTORE; }
  "aconst_null"                 { return INSN_ACONST_NULL; }
  "aload"                       { return INSN_ALOAD; }
  "aload_0"                     { return INSN_ALOAD_0; }
  "aload_1"                     { return INSN_ALOAD_1; }
  "aload_2"                     { return INSN_ALOAD_2; }
  "aload_3"                     { return INSN_ALOAD_3; }
  "aload_4"                     { return INSN_ALOAD_4; }
  "anewarray"                   { return INSN_ANEWARRAY; }
  "areturn"                     { return INSN_ARETURN; }
  "arraylength"                 { return INSN_ARRAYLENGTH; }
  "astore"                      { return INSN_ASTORE; }
  "astore_0"                    { return INSN_ASTORE_0; }
  "astore_1"                    { return INSN_ASTORE_1; }
  "astore_2"                    { return INSN_ASTORE_2; }
  "astore_3"                    { return INSN_ASTORE_3; }
  "athrow"                      { return INSN_ATHROW; }
  "baload"                      { return INSN_BALOAD; }
  "bastore"                     { return INSN_BASTORE; }
  "bipush"                      { return INSN_BIPUSH; }
  "caload"                      { return INSN_CALOAD; }
  "castore"                     { return INSN_CASTORE; }
  "checkcast"                   { return INSN_CHECKCAST; }
  "d2f"                         { return INSN_D2F; }
  "d2i"                         { return INSN_D2I; }
  "d2l"                         { return INSN_D2L; }
  "dadd"                        { return INSN_DADD; }
  "daload"                      { return INSN_DALOAD; }
  "dastore"                     { return INSN_DASTORE; }
  "dcmpg"                       { return INSN_DCMPG; }
  "dcmpl"                       { return INSN_DCMPL; }
  "dconst_0"                    { return INSN_DCONST_0; }
  "dconst_1"                    { return INSN_DCONST_1; }
  "ddiv"                        { return INSN_DDIV; }
  "dload"                       { return INSN_DLOAD; }
  "dload_0"                     { return INSN_DLOAD_0; }
  "dload_1"                     { return INSN_DLOAD_1; }
  "dload_2"                     { return INSN_DLOAD_2; }
  "dload_3"                     { return INSN_DLOAD_3; }
  "dmul"                        { return INSN_DMUL; }
  "dneg"                        { return INSN_DNEG; }
  "drem"                        { return INSN_DREM; }
  "dreturn"                     { return INSN_DRETURN; }
  "dstore"                      { return INSN_DSTORE; }
  "dstore_0"                    { return INSN_DSTORE_0; }
  "dstore_1"                    { return INSN_DSTORE_1; }
  "dstore_2"                    { return INSN_DSTORE_2; }
  "dstore_3"                    { return INSN_DSTORE_3; }
  "dsub"                        { return INSN_DSUB; }
  "dup"                         { return INSN_DUP; }
  "dup_x1"                      { return INSN_DUP_X1; }
  "dup_x2"                      { return INSN_DUP_X2; }
  "dup2"                        { return INSN_DUP2; }
  "dup2_x1"                     { return INSN_DUP2_X1; }
  "dup2_x2"                     { return INSN_DUP2_X2; }
  "f2d"                         { return INSN_F2D; }
  "f2i"                         { return INSN_F2I; }
  "f2l"                         { return INSN_F2L; }
  "fadd"                        { return INSN_FADD; }
  "faload"                      { return INSN_FALOAD; }
  "fastore"                     { return INSN_FASTORE; }
  "fcmpg"                       { return INSN_FCMPG; }
  "fcmpl"                       { return INSN_FCMPL; }
  "fconst_0"                    { return INSN_FCONST_0; }
  "fconst_1"                    { return INSN_FCONST_1; }
  "fconst_2"                    { return INSN_FCONST_2; }
  "fdiv"                        { return INSN_FDIV; }
  "fload"                       { return INSN_FLOAD; }
  "fload_0"                     { return INSN_FLOAD_0; }
  "fload_1"                     { return INSN_FLOAD_1; }
  "fload_2"                     { return INSN_FLOAD_2; }
  "fload_3"                     { return INSN_FLOAD_3; }
  "fmul"                        { return INSN_FMUL; }
  "fneg"                        { return INSN_FNEG; }
  "frem"                        { return INSN_FREM; }
  "freturn"                     { return INSN_FRETURN; }
  "fstore"                      { return INSN_FSTORE; }
  "fstore_0"                    { return INSN_FSTORE_0; }
  "fstore_1"                    { return INSN_FSTORE_1; }
  "fstore_2"                    { return INSN_FSTORE_2; }
  "fstore_3"                    { return INSN_FSTORE_3; }
  "fsub"                        { return INSN_FSUB; }
  "getfield"                    { return INSN_GETFIELD; }
  "getstatic"                   { return INSN_GETSTATIC; }
  "goto"                        { return INSN_GOTO; }
  "goto_w"                      { return INSN_GOTO_W; }
  "i2b"                         { return INSN_I2B; }
  "i2c"                         { return INSN_I2C; }
  "i2d"                         { return INSN_I2D; }
  "i2f"                         { return INSN_I2F; }
  "i2l"                         { return INSN_I2L; }
  "i2s"                         { return INSN_I2S; }
  "iadd"                        { return INSN_IADD; }
  "iaload"                      { return INSN_IALOAD; }
  "iand"                        { return INSN_IAND; }
  "iastore"                     { return INSN_IASTORE; }
  "iconst_m1"                   { return INSN_ICONST_M1; }
  "iconst_0"                    { return INSN_ICONST_0; }
  "iconst_1"                    { return INSN_ICONST_1; }
  "iconst_2"                    { return INSN_ICONST_2; }
  "iconst_3"                    { return INSN_ICONST_3; }
  "iconst_4"                    { return INSN_ICONST_4; }
  "iconst_5"                    { return INSN_ICONST_5; }
  "iconst_6"                    { return INSN_ICONST_6; }
  "iconst_7"                    { return INSN_ICONST_7; }
  "iconst_8"                    { return INSN_ICONST_8; }
  "idiv"                        { return INSN_IDIV; }
  "if_acmpeq"                   { return INSN_IF_ACMPEQ; }
  "if_acmpne"                   { return INSN_IF_ACMPNE; }
  "if_icmpeq"                   { return INSN_IF_ICMPEQ; }
  "if_icmpne"                   { return INSN_IF_ICMPNE; }
  "if_icmplt"                   { return INSN_IF_ICMPLT; }
  "if_icmpge"                   { return INSN_IF_ICMPGE; }
  "if_icmpgt"                   { return INSN_IF_ICMPGT; }
  "if_icmple"                   { return INSN_IF_ICMPLE; }
  "ifeq"                        { return INSN_IFEQ; }
  "ifne"                        { return INSN_IFNE; }
  "iflt"                        { return INSN_IFLT; }
  "ifge"                        { return INSN_IFGE; }
  "ifgt"                        { return INSN_IFGT; }
  "ifle"                        { return INSN_IFLE; }
  "ifnonnull"                   { return INSN_IFNONNULL; }
  "ifnull"                      { return INSN_IFNULL; }
  "iinc"                        { return INSN_IINC; }
  "iload"                       { return INSN_ILOAD; }
  "iload_0"                     { return INSN_ILOAD_0; }
  "iload_1"                     { return INSN_ILOAD_1; }
  "iload_2"                     { return INSN_ILOAD_2; }
  "iload_3"                     { return INSN_ILOAD_3; }
  "imul"                        { return INSN_IMUL; }
  "ineg"                        { return INSN_INEG; }
  "instanceof"                  { return INSN_INSTANCEOF; }
  "invokedynamic"               { return INSN_INVOKEDYNAMIC; }
  "invokeinterface"             { return INSN_INVOKEINTERFACE; }
  "invokespecial"               { return INSN_INVOKESPECIAL; }
  "invokestatic"                { return INSN_INVOKESTATIC; }
  "invokevirtual"               { return INSN_INVOKEVIRTUAL; }
  "ior"                         { return INSN_IOR; }
  "irem"                        { return INSN_IREM; }
  "ireturn"                     { return INSN_IRETURN; }
  "ishl"                        { return INSN_ISHL; }
  "ishr"                        { return INSN_ISHR; }
  "istore"                      { return INSN_ISTORE; }
  "istore_0"                    { return INSN_ISTORE_0; }
  "istore_1"                    { return INSN_ISTORE_1; }
  "istore_2"                    { return INSN_ISTORE_2; }
  "istore_3"                    { return INSN_ISTORE_3; }
  "isub"                        { return INSN_ISUB; }
  "iushr"                       { return INSN_IUSHR; }
  "ixor"                        { return INSN_IXOR; }
  "jsr"                         { return INSN_JSR; }
  "jsr_w"                       { return INSN_JSR_W; }
  "l2d"                         { return INSN_L2D; }
  "l2f"                         { return INSN_L2F; }
  "l2i"                         { return INSN_L2I; }
  "ladd"                        { return INSN_LADD; }
  "laload"                      { return INSN_LALOAD; }
  "land"                        { return INSN_LAND; }
  "lastore"                     { return INSN_LASTORE; }
  "lcmp"                        { return INSN_LCMP; }
  "lconst_0"                    { return INSN_LCONST_0; }
  "lconst_1"                    { return INSN_LCONST_1; }
  "ldc"                         { return INSN_LDC; }
  "ldc_w"                       { return INSN_LDC_W; }
  "ldc2_w"                      { return INSN_LDC2_W; }
  "ldiv"                        { return INSN_LDIV; }
  "lload"                       { return INSN_LLOAD; }
  "lload_0"                     { return INSN_LLOAD_0; }
  "lload_1"                     { return INSN_LLOAD_1; }
  "lload_2"                     { return INSN_LLOAD_2; }
  "lload_3"                     { return INSN_LLOAD_3; }
  "lmul"                        { return INSN_LMUL; }
  "lneg"                        { return INSN_LNEG; }
  "lookupswitch"                { return INSN_LOOKUPSWITCH; }
  "lor"                         { return INSN_LOR; }
  "lrem"                        { return INSN_LREM; }
  "lreturn"                     { return INSN_LRETURN; }
  "lshl"                        { return INSN_LSHL; }
  "lshr"                        { return INSN_LSHR; }
  "lstore"                      { return INSN_LSTORE; }
  "lstore_0"                    { return INSN_LSTORE_0; }
  "lstore_1"                    { return INSN_LSTORE_1; }
  "lstore_2"                    { return INSN_LSTORE_2; }
  "lstore_3"                    { return INSN_LSTORE_3; }
  "lsub"                        { return INSN_LSUB; }
  "lushr"                       { return INSN_LUSHR; }
  "lxor"                        { return INSN_LXOR; }
  "monitorenter"                { return INSN_MONITORENTER; }
  "monitorexit"                 { return INSN_MONITOREXIT; }
  "multianewarray"              { return INSN_MULTIANEWARRAY; }
  "new"                         { return INSN_NEW; }
  "newarray"                    { return INSN_NEWARRAY; }
  "nop"                         { return INSN_NOP; }
  "pop"                         { return INSN_POP; }
  "pop2"                        { return INSN_POP2; }
  "putfield"                    { return INSN_PUTFIELD; }
  "putstatic"                   { return INSN_PUTSTATIC; }
  "ret"                         { return INSN_RET; }
  "return"                      { return INSN_RETURN; }
  "saload"                      { return INSN_SALOAD; }
  "sastore"                     { return INSN_SASTORE; }
  "sipush"                      { return INSN_SIPUSH; }
  "swap"                        { return INSN_SWAP; }
  "tableswitch"                 { return INSN_TABLESWITCH; }
  "wide"                        { return INSN_WIDE; }

  {INSN_ARG_UNSIG_8BYTES}       { return INSN_ARG_UNSIG_8BYTES; }
  {TYPE_DESC}                   { return TYPE_DESC; }
  {METHOD_DESC}                 { return METHOD_DESC; }
  {SPACE}                       { return SPACE; }
  {ID}                          { return ID; }
  {STRING}                      { return STRING; }
  {NUMBER}                      { return NUMBER; }
  {LINE_COMMENT}                { return LINE_COMMENT; }
  {BLOCK_COMMENT}               { return BLOCK_COMMENT; }

}

[^] { return BAD_CHARACTER; }
