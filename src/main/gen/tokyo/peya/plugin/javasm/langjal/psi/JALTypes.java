// This is a generated file. Not intended for manual editing.
package tokyo.peya.plugin.javasm.langjal.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import tokyo.peya.plugin.javasm.langjal.psi.impl.*;

public interface JALTypes {

  IElementType ACCESS_LEVEL = new JALElementType("ACCESS_LEVEL");
  IElementType ACC_ATTR_CLASS = new JALElementType("ACC_ATTR_CLASS");
  IElementType ACC_ATTR_FIELD = new JALElementType("ACC_ATTR_FIELD");
  IElementType ACC_ATTR_METHOD = new JALElementType("ACC_ATTR_METHOD");
  IElementType ACC_MOD_CLASS = new JALElementType("ACC_MOD_CLASS");
  IElementType ACC_MOD_FIELD = new JALElementType("ACC_MOD_FIELD");
  IElementType ACC_MOD_METHOD = new JALElementType("ACC_MOD_METHOD");
  IElementType CLASS_BODY = new JALElementType("CLASS_BODY");
  IElementType CLASS_DEFINITION = new JALElementType("CLASS_DEFINITION");
  IElementType CLASS_META = new JALElementType("CLASS_META");
  IElementType CLASS_META_ITEM = new JALElementType("CLASS_META_ITEM");
  IElementType CLASS_NAME = new JALElementType("CLASS_NAME");
  IElementType CLASS_PROP_INTERFACES = new JALElementType("CLASS_PROP_INTERFACES");
  IElementType CLASS_PROP_MAJOR = new JALElementType("CLASS_PROP_MAJOR");
  IElementType CLASS_PROP_MINOR = new JALElementType("CLASS_PROP_MINOR");
  IElementType CLASS_PROP_SUPER_CLASS = new JALElementType("CLASS_PROP_SUPER_CLASS");
  IElementType FIELD_DEFINITION = new JALElementType("FIELD_DEFINITION");
  IElementType FIELD_NAME = new JALElementType("FIELD_NAME");
  IElementType INSTRUCTION = new JALElementType("INSTRUCTION");
  IElementType JVM_INS_AALOAD = new JALElementType("JVM_INS_AALOAD");
  IElementType JVM_INS_AASTORE = new JALElementType("JVM_INS_AASTORE");
  IElementType JVM_INS_ACONST_NULL = new JALElementType("JVM_INS_ACONST_NULL");
  IElementType JVM_INS_ALOAD = new JALElementType("JVM_INS_ALOAD");
  IElementType JVM_INS_ALOAD_N = new JALElementType("JVM_INS_ALOAD_N");
  IElementType JVM_INS_ANEW_ARRAY = new JALElementType("JVM_INS_ANEW_ARRAY");
  IElementType JVM_INS_ARETURN = new JALElementType("JVM_INS_ARETURN");
  IElementType JVM_INS_ARG_FIELD_REF = new JALElementType("JVM_INS_ARG_FIELD_REF");
  IElementType JVM_INS_ARG_FIELD_REF_NAME = new JALElementType("JVM_INS_ARG_FIELD_REF_NAME");
  IElementType JVM_INS_ARG_FIELD_REF_TYPE = new JALElementType("JVM_INS_ARG_FIELD_REF_TYPE");
  IElementType JVM_INS_ARG_LOCAL_REF = new JALElementType("JVM_INS_ARG_LOCAL_REF");
  IElementType JVM_INS_ARG_LOOKUP_SWITCH = new JALElementType("JVM_INS_ARG_LOOKUP_SWITCH");
  IElementType JVM_INS_ARG_LOOKUP_SWITCH_CASE = new JALElementType("JVM_INS_ARG_LOOKUP_SWITCH_CASE");
  IElementType JVM_INS_ARG_LOOKUP_SWITCH_CASE_NAME = new JALElementType("JVM_INS_ARG_LOOKUP_SWITCH_CASE_NAME");
  IElementType JVM_INS_ARG_METHOD_REF = new JALElementType("JVM_INS_ARG_METHOD_REF");
  IElementType JVM_INS_ARG_METHOD_REF_OWNER_TYPE = new JALElementType("JVM_INS_ARG_METHOD_REF_OWNER_TYPE");
  IElementType JVM_INS_ARG_METHOD_SPECIAL_REF = new JALElementType("JVM_INS_ARG_METHOD_SPECIAL_REF");
  IElementType JVM_INS_ARG_SCALAR_TYPE = new JALElementType("JVM_INS_ARG_SCALAR_TYPE");
  IElementType JVM_INS_ARG_TABLE_SWITCH = new JALElementType("JVM_INS_ARG_TABLE_SWITCH");
  IElementType JVM_INS_ARRAYLENGTH = new JALElementType("JVM_INS_ARRAYLENGTH");
  IElementType JVM_INS_ASTORE = new JALElementType("JVM_INS_ASTORE");
  IElementType JVM_INS_ASTORE_N = new JALElementType("JVM_INS_ASTORE_N");
  IElementType JVM_INS_ATHROW = new JALElementType("JVM_INS_ATHROW");
  IElementType JVM_INS_BALOAD = new JALElementType("JVM_INS_BALOAD");
  IElementType JVM_INS_BASTORE = new JALElementType("JVM_INS_BASTORE");
  IElementType JVM_INS_BIPUSH = new JALElementType("JVM_INS_BIPUSH");
  IElementType JVM_INS_CALOAD = new JALElementType("JVM_INS_CALOAD");
  IElementType JVM_INS_CASTORE = new JALElementType("JVM_INS_CASTORE");
  IElementType JVM_INS_CHECKCAST = new JALElementType("JVM_INS_CHECKCAST");
  IElementType JVM_INS_DADD = new JALElementType("JVM_INS_DADD");
  IElementType JVM_INS_DALOAD = new JALElementType("JVM_INS_DALOAD");
  IElementType JVM_INS_DASTORE = new JALElementType("JVM_INS_DASTORE");
  IElementType JVM_INS_DCMP_OP = new JALElementType("JVM_INS_DCMP_OP");
  IElementType JVM_INS_DCONST_N = new JALElementType("JVM_INS_DCONST_N");
  IElementType JVM_INS_DDIV = new JALElementType("JVM_INS_DDIV");
  IElementType JVM_INS_DLOAD = new JALElementType("JVM_INS_DLOAD");
  IElementType JVM_INS_DLOAD_N = new JALElementType("JVM_INS_DLOAD_N");
  IElementType JVM_INS_DMUL = new JALElementType("JVM_INS_DMUL");
  IElementType JVM_INS_DNEG = new JALElementType("JVM_INS_DNEG");
  IElementType JVM_INS_DREM = new JALElementType("JVM_INS_DREM");
  IElementType JVM_INS_DRETURN = new JALElementType("JVM_INS_DRETURN");
  IElementType JVM_INS_DSTORE = new JALElementType("JVM_INS_DSTORE");
  IElementType JVM_INS_DSTORE_N = new JALElementType("JVM_INS_DSTORE_N");
  IElementType JVM_INS_DSUB = new JALElementType("JVM_INS_DSUB");
  IElementType JVM_INS_DUP = new JALElementType("JVM_INS_DUP");
  IElementType JVM_INS_DUP_2 = new JALElementType("JVM_INS_DUP_2");
  IElementType JVM_INS_DUP_2_X_1 = new JALElementType("JVM_INS_DUP_2_X_1");
  IElementType JVM_INS_DUP_2_X_2 = new JALElementType("JVM_INS_DUP_2_X_2");
  IElementType JVM_INS_DUP_X_1 = new JALElementType("JVM_INS_DUP_X_1");
  IElementType JVM_INS_DUP_X_2 = new JALElementType("JVM_INS_DUP_X_2");
  IElementType JVM_INS_D_2_F = new JALElementType("JVM_INS_D_2_F");
  IElementType JVM_INS_D_2_I = new JALElementType("JVM_INS_D_2_I");
  IElementType JVM_INS_D_2_L = new JALElementType("JVM_INS_D_2_L");
  IElementType JVM_INS_FADD = new JALElementType("JVM_INS_FADD");
  IElementType JVM_INS_FALOAD = new JALElementType("JVM_INS_FALOAD");
  IElementType JVM_INS_FASTORE = new JALElementType("JVM_INS_FASTORE");
  IElementType JVM_INS_FCMPG_OP = new JALElementType("JVM_INS_FCMPG_OP");
  IElementType JVM_INS_FCONST_N = new JALElementType("JVM_INS_FCONST_N");
  IElementType JVM_INS_FDIV = new JALElementType("JVM_INS_FDIV");
  IElementType JVM_INS_FLOAD = new JALElementType("JVM_INS_FLOAD");
  IElementType JVM_INS_FLOAD_N = new JALElementType("JVM_INS_FLOAD_N");
  IElementType JVM_INS_FMUL = new JALElementType("JVM_INS_FMUL");
  IElementType JVM_INS_FNEG = new JALElementType("JVM_INS_FNEG");
  IElementType JVM_INS_FREM = new JALElementType("JVM_INS_FREM");
  IElementType JVM_INS_FRETURN = new JALElementType("JVM_INS_FRETURN");
  IElementType JVM_INS_FSTORE = new JALElementType("JVM_INS_FSTORE");
  IElementType JVM_INS_FSTORE_N = new JALElementType("JVM_INS_FSTORE_N");
  IElementType JVM_INS_FSUB = new JALElementType("JVM_INS_FSUB");
  IElementType JVM_INS_F_2_D = new JALElementType("JVM_INS_F_2_D");
  IElementType JVM_INS_F_2_I = new JALElementType("JVM_INS_F_2_I");
  IElementType JVM_INS_F_2_L = new JALElementType("JVM_INS_F_2_L");
  IElementType JVM_INS_GETFIELD = new JALElementType("JVM_INS_GETFIELD");
  IElementType JVM_INS_GETSTATIC = new JALElementType("JVM_INS_GETSTATIC");
  IElementType JVM_INS_GOTO = new JALElementType("JVM_INS_GOTO");
  IElementType JVM_INS_GOTO_W = new JALElementType("JVM_INS_GOTO_W");
  IElementType JVM_INS_IADD = new JALElementType("JVM_INS_IADD");
  IElementType JVM_INS_IALOAD = new JALElementType("JVM_INS_IALOAD");
  IElementType JVM_INS_IAND = new JALElementType("JVM_INS_IAND");
  IElementType JVM_INS_IASTORE = new JALElementType("JVM_INS_IASTORE");
  IElementType JVM_INS_ICONST_N = new JALElementType("JVM_INS_ICONST_N");
  IElementType JVM_INS_IDIV = new JALElementType("JVM_INS_IDIV");
  IElementType JVM_INS_IF_ACMP_OP = new JALElementType("JVM_INS_IF_ACMP_OP");
  IElementType JVM_INS_IF_ICMP_OP = new JALElementType("JVM_INS_IF_ICMP_OP");
  IElementType JVM_INS_IF_NONNULL = new JALElementType("JVM_INS_IF_NONNULL");
  IElementType JVM_INS_IF_NULL = new JALElementType("JVM_INS_IF_NULL");
  IElementType JVM_INS_IF_OP = new JALElementType("JVM_INS_IF_OP");
  IElementType JVM_INS_IINC = new JALElementType("JVM_INS_IINC");
  IElementType JVM_INS_ILOAD = new JALElementType("JVM_INS_ILOAD");
  IElementType JVM_INS_ILOAD_N = new JALElementType("JVM_INS_ILOAD_N");
  IElementType JVM_INS_IMUL = new JALElementType("JVM_INS_IMUL");
  IElementType JVM_INS_INEG = new JALElementType("JVM_INS_INEG");
  IElementType JVM_INS_INSTANCEOF = new JALElementType("JVM_INS_INSTANCEOF");
  IElementType JVM_INS_INVOKEDYNAMIC = new JALElementType("JVM_INS_INVOKEDYNAMIC");
  IElementType JVM_INS_INVOKEINTERFACE = new JALElementType("JVM_INS_INVOKEINTERFACE");
  IElementType JVM_INS_INVOKESPECIAL = new JALElementType("JVM_INS_INVOKESPECIAL");
  IElementType JVM_INS_INVOKESTATIC = new JALElementType("JVM_INS_INVOKESTATIC");
  IElementType JVM_INS_INVOKEVIRTUAL = new JALElementType("JVM_INS_INVOKEVIRTUAL");
  IElementType JVM_INS_IOR = new JALElementType("JVM_INS_IOR");
  IElementType JVM_INS_IREM = new JALElementType("JVM_INS_IREM");
  IElementType JVM_INS_IRETURN = new JALElementType("JVM_INS_IRETURN");
  IElementType JVM_INS_ISHL = new JALElementType("JVM_INS_ISHL");
  IElementType JVM_INS_ISHR = new JALElementType("JVM_INS_ISHR");
  IElementType JVM_INS_ISTORE = new JALElementType("JVM_INS_ISTORE");
  IElementType JVM_INS_ISTORE_N = new JALElementType("JVM_INS_ISTORE_N");
  IElementType JVM_INS_ISUB = new JALElementType("JVM_INS_ISUB");
  IElementType JVM_INS_IUSHR = new JALElementType("JVM_INS_IUSHR");
  IElementType JVM_INS_IXOR = new JALElementType("JVM_INS_IXOR");
  IElementType JVM_INS_I_2_B = new JALElementType("JVM_INS_I_2_B");
  IElementType JVM_INS_I_2_C = new JALElementType("JVM_INS_I_2_C");
  IElementType JVM_INS_I_2_D = new JALElementType("JVM_INS_I_2_D");
  IElementType JVM_INS_I_2_F = new JALElementType("JVM_INS_I_2_F");
  IElementType JVM_INS_I_2_L = new JALElementType("JVM_INS_I_2_L");
  IElementType JVM_INS_I_2_S = new JALElementType("JVM_INS_I_2_S");
  IElementType JVM_INS_JSR = new JALElementType("JVM_INS_JSR");
  IElementType JVM_INS_JSR_W = new JALElementType("JVM_INS_JSR_W");
  IElementType JVM_INS_LADD = new JALElementType("JVM_INS_LADD");
  IElementType JVM_INS_LALOAD = new JALElementType("JVM_INS_LALOAD");
  IElementType JVM_INS_LAND = new JALElementType("JVM_INS_LAND");
  IElementType JVM_INS_LASTORE = new JALElementType("JVM_INS_LASTORE");
  IElementType JVM_INS_LCMP = new JALElementType("JVM_INS_LCMP");
  IElementType JVM_INS_LCONST_N = new JALElementType("JVM_INS_LCONST_N");
  IElementType JVM_INS_LDC = new JALElementType("JVM_INS_LDC");
  IElementType JVM_INS_LDC_2_W = new JALElementType("JVM_INS_LDC_2_W");
  IElementType JVM_INS_LDC_W = new JALElementType("JVM_INS_LDC_W");
  IElementType JVM_INS_LDIV = new JALElementType("JVM_INS_LDIV");
  IElementType JVM_INS_LLOAD = new JALElementType("JVM_INS_LLOAD");
  IElementType JVM_INS_LLOAD_N = new JALElementType("JVM_INS_LLOAD_N");
  IElementType JVM_INS_LMUL = new JALElementType("JVM_INS_LMUL");
  IElementType JVM_INS_LNEG = new JALElementType("JVM_INS_LNEG");
  IElementType JVM_INS_LOOKUPSWITCH = new JALElementType("JVM_INS_LOOKUPSWITCH");
  IElementType JVM_INS_LOR = new JALElementType("JVM_INS_LOR");
  IElementType JVM_INS_LREM = new JALElementType("JVM_INS_LREM");
  IElementType JVM_INS_LRETURN = new JALElementType("JVM_INS_LRETURN");
  IElementType JVM_INS_LSHL = new JALElementType("JVM_INS_LSHL");
  IElementType JVM_INS_LSHR = new JALElementType("JVM_INS_LSHR");
  IElementType JVM_INS_LSTORE = new JALElementType("JVM_INS_LSTORE");
  IElementType JVM_INS_LSTORE_N = new JALElementType("JVM_INS_LSTORE_N");
  IElementType JVM_INS_LSUB = new JALElementType("JVM_INS_LSUB");
  IElementType JVM_INS_LUSHR = new JALElementType("JVM_INS_LUSHR");
  IElementType JVM_INS_LXOR = new JALElementType("JVM_INS_LXOR");
  IElementType JVM_INS_L_2_D = new JALElementType("JVM_INS_L_2_D");
  IElementType JVM_INS_L_2_F = new JALElementType("JVM_INS_L_2_F");
  IElementType JVM_INS_L_2_I = new JALElementType("JVM_INS_L_2_I");
  IElementType JVM_INS_MONITORENTER = new JALElementType("JVM_INS_MONITORENTER");
  IElementType JVM_INS_MONITOREXIT = new JALElementType("JVM_INS_MONITOREXIT");
  IElementType JVM_INS_MULTIANEWARRAY = new JALElementType("JVM_INS_MULTIANEWARRAY");
  IElementType JVM_INS_NEW = new JALElementType("JVM_INS_NEW");
  IElementType JVM_INS_NEWARRAY = new JALElementType("JVM_INS_NEWARRAY");
  IElementType JVM_INS_NOP = new JALElementType("JVM_INS_NOP");
  IElementType JVM_INS_POP = new JALElementType("JVM_INS_POP");
  IElementType JVM_INS_POP_2 = new JALElementType("JVM_INS_POP_2");
  IElementType JVM_INS_PUTFIELD = new JALElementType("JVM_INS_PUTFIELD");
  IElementType JVM_INS_PUTSTATIC = new JALElementType("JVM_INS_PUTSTATIC");
  IElementType JVM_INS_RET = new JALElementType("JVM_INS_RET");
  IElementType JVM_INS_RETURN = new JALElementType("JVM_INS_RETURN");
  IElementType JVM_INS_SALOAD = new JALElementType("JVM_INS_SALOAD");
  IElementType JVM_INS_SASTORE = new JALElementType("JVM_INS_SASTORE");
  IElementType JVM_INS_SIPUSH = new JALElementType("JVM_INS_SIPUSH");
  IElementType JVM_INS_SWAP = new JALElementType("JVM_INS_SWAP");
  IElementType JVM_INS_TABLESWITCH = new JALElementType("JVM_INS_TABLESWITCH");
  IElementType JVM_INS_WIDE = new JALElementType("JVM_INS_WIDE");
  IElementType LABEL = new JALElementType("LABEL");
  IElementType LABEL_NAME = new JALElementType("LABEL_NAME");
  IElementType METHOD_BODY_ITEM = new JALElementType("METHOD_BODY_ITEM");
  IElementType METHOD_DEFINITION = new JALElementType("METHOD_DEFINITION");
  IElementType METHOD_DESCRIPTOR = new JALElementType("METHOD_DESCRIPTOR");
  IElementType METHOD_DESCRIPTOR_ARGS = new JALElementType("METHOD_DESCRIPTOR_ARGS");
  IElementType TYPE_DESCRIPTOR = new JALElementType("TYPE_DESCRIPTOR");
  IElementType TYPE_DESCRIPTOR_PRIMITIVE = new JALElementType("TYPE_DESCRIPTOR_PRIMITIVE");

  IElementType BLOCK_COMMENT = new JALTokenType("block_comment");
  IElementType COLON = new JALTokenType(":");
  IElementType COMMA = new JALTokenType(",");
  IElementType DOT = new JALTokenType(".");
  IElementType EQ = new JALTokenType("=");
  IElementType FULL_QUALIFIED_CLASS_NAME = new JALTokenType("FULL_QUALIFIED_CLASS_NAME");
  IElementType ID = new JALTokenType("id");
  IElementType INSN_AALOAD = new JALTokenType("aaload");
  IElementType INSN_AASTORE = new JALTokenType("aastore");
  IElementType INSN_ACONST_NULL = new JALTokenType("aconst_null");
  IElementType INSN_ALOAD = new JALTokenType("aload");
  IElementType INSN_ALOAD_0 = new JALTokenType("aload_0");
  IElementType INSN_ALOAD_1 = new JALTokenType("aload_1");
  IElementType INSN_ALOAD_2 = new JALTokenType("aload_2");
  IElementType INSN_ALOAD_3 = new JALTokenType("aload_3");
  IElementType INSN_ALOAD_4 = new JALTokenType("aload_4");
  IElementType INSN_ANEWARRAY = new JALTokenType("anewarray");
  IElementType INSN_ARETURN = new JALTokenType("areturn");
  IElementType INSN_ARRAYLENGTH = new JALTokenType("arraylength");
  IElementType INSN_ASTORE = new JALTokenType("astore");
  IElementType INSN_ASTORE_0 = new JALTokenType("astore_0");
  IElementType INSN_ASTORE_1 = new JALTokenType("astore_1");
  IElementType INSN_ASTORE_2 = new JALTokenType("astore_2");
  IElementType INSN_ASTORE_3 = new JALTokenType("astore_3");
  IElementType INSN_ATHROW = new JALTokenType("athrow");
  IElementType INSN_BALOAD = new JALTokenType("baload");
  IElementType INSN_BASTORE = new JALTokenType("bastore");
  IElementType INSN_BIPUSH = new JALTokenType("bipush");
  IElementType INSN_CALOAD = new JALTokenType("caload");
  IElementType INSN_CASTORE = new JALTokenType("castore");
  IElementType INSN_CHECKCAST = new JALTokenType("checkcast");
  IElementType INSN_D2F = new JALTokenType("d2f");
  IElementType INSN_D2I = new JALTokenType("d2i");
  IElementType INSN_D2L = new JALTokenType("d2l");
  IElementType INSN_DADD = new JALTokenType("dadd");
  IElementType INSN_DALOAD = new JALTokenType("daload");
  IElementType INSN_DASTORE = new JALTokenType("dastore");
  IElementType INSN_DCMPG = new JALTokenType("dcmpg");
  IElementType INSN_DCMPL = new JALTokenType("dcmpl");
  IElementType INSN_DCONST_0 = new JALTokenType("dconst_0");
  IElementType INSN_DCONST_1 = new JALTokenType("dconst_1");
  IElementType INSN_DDIV = new JALTokenType("ddiv");
  IElementType INSN_DLOAD = new JALTokenType("dload");
  IElementType INSN_DLOAD_0 = new JALTokenType("dload_0");
  IElementType INSN_DLOAD_1 = new JALTokenType("dload_1");
  IElementType INSN_DLOAD_2 = new JALTokenType("dload_2");
  IElementType INSN_DLOAD_3 = new JALTokenType("dload_3");
  IElementType INSN_DMUL = new JALTokenType("dmul");
  IElementType INSN_DNEG = new JALTokenType("dneg");
  IElementType INSN_DREM = new JALTokenType("drem");
  IElementType INSN_DRETURN = new JALTokenType("dreturn");
  IElementType INSN_DSTORE = new JALTokenType("dstore");
  IElementType INSN_DSTORE_0 = new JALTokenType("dstore_0");
  IElementType INSN_DSTORE_1 = new JALTokenType("dstore_1");
  IElementType INSN_DSTORE_2 = new JALTokenType("dstore_2");
  IElementType INSN_DSTORE_3 = new JALTokenType("dstore_3");
  IElementType INSN_DSUB = new JALTokenType("dsub");
  IElementType INSN_DUP = new JALTokenType("dup");
  IElementType INSN_DUP2 = new JALTokenType("dup2");
  IElementType INSN_DUP2_X1 = new JALTokenType("dup2_x1");
  IElementType INSN_DUP2_X2 = new JALTokenType("dup2_x2");
  IElementType INSN_DUP_X1 = new JALTokenType("dup_x1");
  IElementType INSN_DUP_X2 = new JALTokenType("dup_x2");
  IElementType INSN_F2D = new JALTokenType("f2d");
  IElementType INSN_F2I = new JALTokenType("f2i");
  IElementType INSN_F2L = new JALTokenType("f2l");
  IElementType INSN_FADD = new JALTokenType("fadd");
  IElementType INSN_FALOAD = new JALTokenType("faload");
  IElementType INSN_FASTORE = new JALTokenType("fastore");
  IElementType INSN_FCMPG = new JALTokenType("fcmpg");
  IElementType INSN_FCMPL = new JALTokenType("fcmpl");
  IElementType INSN_FCONST_0 = new JALTokenType("fconst_0");
  IElementType INSN_FCONST_1 = new JALTokenType("fconst_1");
  IElementType INSN_FCONST_2 = new JALTokenType("fconst_2");
  IElementType INSN_FDIV = new JALTokenType("fdiv");
  IElementType INSN_FLOAD = new JALTokenType("fload");
  IElementType INSN_FLOAD_0 = new JALTokenType("fload_0");
  IElementType INSN_FLOAD_1 = new JALTokenType("fload_1");
  IElementType INSN_FLOAD_2 = new JALTokenType("fload_2");
  IElementType INSN_FLOAD_3 = new JALTokenType("fload_3");
  IElementType INSN_FMUL = new JALTokenType("fmul");
  IElementType INSN_FNEG = new JALTokenType("fneg");
  IElementType INSN_FREM = new JALTokenType("frem");
  IElementType INSN_FRETURN = new JALTokenType("freturn");
  IElementType INSN_FSTORE = new JALTokenType("fstore");
  IElementType INSN_FSTORE_0 = new JALTokenType("fstore_0");
  IElementType INSN_FSTORE_1 = new JALTokenType("fstore_1");
  IElementType INSN_FSTORE_2 = new JALTokenType("fstore_2");
  IElementType INSN_FSTORE_3 = new JALTokenType("fstore_3");
  IElementType INSN_FSUB = new JALTokenType("fsub");
  IElementType INSN_GETFIELD = new JALTokenType("getfield");
  IElementType INSN_GETSTATIC = new JALTokenType("getstatic");
  IElementType INSN_GOTO = new JALTokenType("goto");
  IElementType INSN_GOTO_W = new JALTokenType("goto_w");
  IElementType INSN_I2B = new JALTokenType("i2b");
  IElementType INSN_I2C = new JALTokenType("i2c");
  IElementType INSN_I2D = new JALTokenType("i2d");
  IElementType INSN_I2F = new JALTokenType("i2f");
  IElementType INSN_I2L = new JALTokenType("i2l");
  IElementType INSN_I2S = new JALTokenType("i2s");
  IElementType INSN_IADD = new JALTokenType("iadd");
  IElementType INSN_IALOAD = new JALTokenType("iaload");
  IElementType INSN_IAND = new JALTokenType("iand");
  IElementType INSN_IASTORE = new JALTokenType("iastore");
  IElementType INSN_ICONST_0 = new JALTokenType("iconst_0");
  IElementType INSN_ICONST_1 = new JALTokenType("iconst_1");
  IElementType INSN_ICONST_2 = new JALTokenType("iconst_2");
  IElementType INSN_ICONST_3 = new JALTokenType("iconst_3");
  IElementType INSN_ICONST_4 = new JALTokenType("iconst_4");
  IElementType INSN_ICONST_5 = new JALTokenType("iconst_5");
  IElementType INSN_ICONST_6 = new JALTokenType("iconst_6");
  IElementType INSN_ICONST_7 = new JALTokenType("iconst_7");
  IElementType INSN_ICONST_8 = new JALTokenType("iconst_8");
  IElementType INSN_ICONST_M1 = new JALTokenType("iconst_m1");
  IElementType INSN_IDIV = new JALTokenType("idiv");
  IElementType INSN_IFEQ = new JALTokenType("ifeq");
  IElementType INSN_IFGE = new JALTokenType("ifge");
  IElementType INSN_IFGT = new JALTokenType("ifgt");
  IElementType INSN_IFLE = new JALTokenType("ifle");
  IElementType INSN_IFLT = new JALTokenType("iflt");
  IElementType INSN_IFNE = new JALTokenType("ifne");
  IElementType INSN_IFNONNULL = new JALTokenType("ifnonnull");
  IElementType INSN_IFNULL = new JALTokenType("ifnull");
  IElementType INSN_IF_ACMPEQ = new JALTokenType("if_acmpeq");
  IElementType INSN_IF_ACMPNE = new JALTokenType("if_acmpne");
  IElementType INSN_IF_ICMPEQ = new JALTokenType("if_icmpeq");
  IElementType INSN_IF_ICMPGE = new JALTokenType("if_icmpge");
  IElementType INSN_IF_ICMPGT = new JALTokenType("if_icmpgt");
  IElementType INSN_IF_ICMPLE = new JALTokenType("if_icmple");
  IElementType INSN_IF_ICMPLT = new JALTokenType("if_icmplt");
  IElementType INSN_IF_ICMPNE = new JALTokenType("if_icmpne");
  IElementType INSN_IINC = new JALTokenType("iinc");
  IElementType INSN_ILOAD = new JALTokenType("iload");
  IElementType INSN_ILOAD_0 = new JALTokenType("iload_0");
  IElementType INSN_ILOAD_1 = new JALTokenType("iload_1");
  IElementType INSN_ILOAD_2 = new JALTokenType("iload_2");
  IElementType INSN_ILOAD_3 = new JALTokenType("iload_3");
  IElementType INSN_IMUL = new JALTokenType("imul");
  IElementType INSN_INEG = new JALTokenType("ineg");
  IElementType INSN_INSTANCEOF = new JALTokenType("instanceof");
  IElementType INSN_INVOKEDYNAMIC = new JALTokenType("invokedynamic");
  IElementType INSN_INVOKEINTERFACE = new JALTokenType("invokeinterface");
  IElementType INSN_INVOKESPECIAL = new JALTokenType("invokespecial");
  IElementType INSN_INVOKESTATIC = new JALTokenType("invokestatic");
  IElementType INSN_INVOKEVIRTUAL = new JALTokenType("invokevirtual");
  IElementType INSN_IOR = new JALTokenType("ior");
  IElementType INSN_IREM = new JALTokenType("irem");
  IElementType INSN_IRETURN = new JALTokenType("ireturn");
  IElementType INSN_ISHL = new JALTokenType("ishl");
  IElementType INSN_ISHR = new JALTokenType("ishr");
  IElementType INSN_ISTORE = new JALTokenType("istore");
  IElementType INSN_ISTORE_0 = new JALTokenType("istore_0");
  IElementType INSN_ISTORE_1 = new JALTokenType("istore_1");
  IElementType INSN_ISTORE_2 = new JALTokenType("istore_2");
  IElementType INSN_ISTORE_3 = new JALTokenType("istore_3");
  IElementType INSN_ISUB = new JALTokenType("isub");
  IElementType INSN_IUSHR = new JALTokenType("iushr");
  IElementType INSN_IXOR = new JALTokenType("ixor");
  IElementType INSN_JSR = new JALTokenType("jsr");
  IElementType INSN_JSR_W = new JALTokenType("jsr_w");
  IElementType INSN_L2D = new JALTokenType("l2d");
  IElementType INSN_L2F = new JALTokenType("l2f");
  IElementType INSN_L2I = new JALTokenType("l2i");
  IElementType INSN_LADD = new JALTokenType("ladd");
  IElementType INSN_LALOAD = new JALTokenType("laload");
  IElementType INSN_LAND = new JALTokenType("land");
  IElementType INSN_LASTORE = new JALTokenType("lastore");
  IElementType INSN_LCMP = new JALTokenType("lcmp");
  IElementType INSN_LCONST_0 = new JALTokenType("lconst_0");
  IElementType INSN_LCONST_1 = new JALTokenType("lconst_1");
  IElementType INSN_LDC = new JALTokenType("ldc");
  IElementType INSN_LDC2_W = new JALTokenType("ldc2_w");
  IElementType INSN_LDC_W = new JALTokenType("ldc_w");
  IElementType INSN_LDIV = new JALTokenType("ldiv");
  IElementType INSN_LLOAD = new JALTokenType("lload");
  IElementType INSN_LLOAD_0 = new JALTokenType("lload_0");
  IElementType INSN_LLOAD_1 = new JALTokenType("lload_1");
  IElementType INSN_LLOAD_2 = new JALTokenType("lload_2");
  IElementType INSN_LLOAD_3 = new JALTokenType("lload_3");
  IElementType INSN_LMUL = new JALTokenType("lmul");
  IElementType INSN_LNEG = new JALTokenType("lneg");
  IElementType INSN_LOOKUPSWITCH = new JALTokenType("lookupswitch");
  IElementType INSN_LOR = new JALTokenType("lor");
  IElementType INSN_LREM = new JALTokenType("lrem");
  IElementType INSN_LRETURN = new JALTokenType("lreturn");
  IElementType INSN_LSHL = new JALTokenType("lshl");
  IElementType INSN_LSHR = new JALTokenType("lshr");
  IElementType INSN_LSTORE = new JALTokenType("lstore");
  IElementType INSN_LSTORE_0 = new JALTokenType("lstore_0");
  IElementType INSN_LSTORE_1 = new JALTokenType("lstore_1");
  IElementType INSN_LSTORE_2 = new JALTokenType("lstore_2");
  IElementType INSN_LSTORE_3 = new JALTokenType("lstore_3");
  IElementType INSN_LSUB = new JALTokenType("lsub");
  IElementType INSN_LUSHR = new JALTokenType("lushr");
  IElementType INSN_LXOR = new JALTokenType("lxor");
  IElementType INSN_MONITORENTER = new JALTokenType("monitorenter");
  IElementType INSN_MONITOREXIT = new JALTokenType("monitorexit");
  IElementType INSN_MULTIANEWARRAY = new JALTokenType("multianewarray");
  IElementType INSN_NEW = new JALTokenType("new");
  IElementType INSN_NEWARRAY = new JALTokenType("newarray");
  IElementType INSN_NOP = new JALTokenType("nop");
  IElementType INSN_POP = new JALTokenType("pop");
  IElementType INSN_POP2 = new JALTokenType("pop2");
  IElementType INSN_PUTFIELD = new JALTokenType("putfield");
  IElementType INSN_PUTSTATIC = new JALTokenType("putstatic");
  IElementType INSN_RET = new JALTokenType("ret");
  IElementType INSN_RETURN = new JALTokenType("return");
  IElementType INSN_SALOAD = new JALTokenType("saload");
  IElementType INSN_SASTORE = new JALTokenType("sastore");
  IElementType INSN_SIPUSH = new JALTokenType("sipush");
  IElementType INSN_SWAP = new JALTokenType("swap");
  IElementType INSN_TABLESWITCH = new JALTokenType("tableswitch");
  IElementType INSN_WIDE = new JALTokenType("wide");
  IElementType KWD_ACC_ATTR_ABSTRACT = new JALTokenType("abstract");
  IElementType KWD_ACC_ATTR_ANNOTATION = new JALTokenType("annotation");
  IElementType KWD_ACC_ATTR_BRIDGE = new JALTokenType("bridge");
  IElementType KWD_ACC_ATTR_ENUM = new JALTokenType("enum");
  IElementType KWD_ACC_ATTR_FINAL = new JALTokenType("final");
  IElementType KWD_ACC_ATTR_NATIVE = new JALTokenType("native");
  IElementType KWD_ACC_ATTR_STATIC = new JALTokenType("static");
  IElementType KWD_ACC_ATTR_STRICTFP = new JALTokenType("strictfp");
  IElementType KWD_ACC_ATTR_SUPER = new JALTokenType("super");
  IElementType KWD_ACC_ATTR_SYNCHRONIZED = new JALTokenType("synchronized");
  IElementType KWD_ACC_ATTR_SYNTHETIC = new JALTokenType("synthetic");
  IElementType KWD_ACC_ATTR_TRANSIENT = new JALTokenType("transient");
  IElementType KWD_ACC_ATTR_VARARGS = new JALTokenType("varargs");
  IElementType KWD_ACC_ATTR_VOLATILE = new JALTokenType("volatile");
  IElementType KWD_ACC_PRIVATE = new JALTokenType("private");
  IElementType KWD_ACC_PROTECTED = new JALTokenType("protected");
  IElementType KWD_ACC_PUBLIC = new JALTokenType("public");
  IElementType KWD_CLASS = new JALTokenType("class");
  IElementType KWD_CLASS_PROP_INTERFACES = new JALTokenType("interfaces");
  IElementType KWD_CLASS_PROP_MAJOR = new JALTokenType("major_version");
  IElementType KWD_CLASS_PROP_MINOR = new JALTokenType("minor_version");
  IElementType KWD_CLASS_PROP_SUPER_CLASS = new JALTokenType("super_class");
  IElementType KWD_INTERFACE = new JALTokenType("interface");
  IElementType KWD_MNAME_CLINIT = new JALTokenType("<clinit>");
  IElementType KWD_MNAME_INIT = new JALTokenType("<init>");
  IElementType KWD_SWITCH_DEFAULT = new JALTokenType("default");
  IElementType LBK = new JALTokenType("[");
  IElementType LBR = new JALTokenType("{");
  IElementType LINE_COMMENT = new JALTokenType("line_comment");
  IElementType LP = new JALTokenType("(");
  IElementType NUMBER = new JALTokenType("number");
  IElementType RBK = new JALTokenType("]");
  IElementType RBR = new JALTokenType("}");
  IElementType REF = new JALTokenType("->");
  IElementType RP = new JALTokenType(")");
  IElementType SEMI = new JALTokenType(";");
  IElementType SLASH = new JALTokenType("/");
  IElementType STRING = new JALTokenType("string");
  IElementType TYPE_DESC_BOOLEAN = new JALTokenType("Z");
  IElementType TYPE_DESC_BYTE = new JALTokenType("B");
  IElementType TYPE_DESC_CHAR = new JALTokenType("C");
  IElementType TYPE_DESC_DOUBLE = new JALTokenType("D");
  IElementType TYPE_DESC_FLOAT = new JALTokenType("F");
  IElementType TYPE_DESC_INT = new JALTokenType("I");
  IElementType TYPE_DESC_LONG = new JALTokenType("J");
  IElementType TYPE_DESC_OBJECT = new JALTokenType("TYPE_DESC_OBJECT");
  IElementType TYPE_DESC_SHORT = new JALTokenType("S");
  IElementType TYPE_DESC_VOID = new JALTokenType("V");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACCESS_LEVEL) {
        return new JALAccessLevelImpl(node);
      }
      else if (type == ACC_ATTR_CLASS) {
        return new JALAccAttrClassImpl(node);
      }
      else if (type == ACC_ATTR_FIELD) {
        return new JALAccAttrFieldImpl(node);
      }
      else if (type == ACC_ATTR_METHOD) {
        return new JALAccAttrMethodImpl(node);
      }
      else if (type == ACC_MOD_CLASS) {
        return new JALAccModClassImpl(node);
      }
      else if (type == ACC_MOD_FIELD) {
        return new JALAccModFieldImpl(node);
      }
      else if (type == ACC_MOD_METHOD) {
        return new JALAccModMethodImpl(node);
      }
      else if (type == CLASS_BODY) {
        return new JALClassBodyImpl(node);
      }
      else if (type == CLASS_DEFINITION) {
        return new JALClassDefinitionImpl(node);
      }
      else if (type == CLASS_META) {
        return new JALClassMetaImpl(node);
      }
      else if (type == CLASS_META_ITEM) {
        return new JALClassMetaItemImpl(node);
      }
      else if (type == CLASS_NAME) {
        return new JALClassNameImpl(node);
      }
      else if (type == CLASS_PROP_INTERFACES) {
        return new JALClassPropInterfacesImpl(node);
      }
      else if (type == CLASS_PROP_MAJOR) {
        return new JALClassPropMajorImpl(node);
      }
      else if (type == CLASS_PROP_MINOR) {
        return new JALClassPropMinorImpl(node);
      }
      else if (type == CLASS_PROP_SUPER_CLASS) {
        return new JALClassPropSuperClassImpl(node);
      }
      else if (type == FIELD_DEFINITION) {
        return new JALFieldDefinitionImpl(node);
      }
      else if (type == FIELD_NAME) {
        return new JALFieldNameImpl(node);
      }
      else if (type == JVM_INS_AALOAD) {
        return new JALJvmInsAaloadImpl(node);
      }
      else if (type == JVM_INS_AASTORE) {
        return new JALJvmInsAastoreImpl(node);
      }
      else if (type == JVM_INS_ACONST_NULL) {
        return new JALJvmInsAconstNullImpl(node);
      }
      else if (type == JVM_INS_ALOAD) {
        return new JALJvmInsAloadImpl(node);
      }
      else if (type == JVM_INS_ALOAD_N) {
        return new JALJvmInsAloadNImpl(node);
      }
      else if (type == JVM_INS_ANEW_ARRAY) {
        return new JALJvmInsAnewArrayImpl(node);
      }
      else if (type == JVM_INS_ARETURN) {
        return new JALJvmInsAreturnImpl(node);
      }
      else if (type == JVM_INS_ARG_FIELD_REF) {
        return new JALJvmInsArgFieldRefImpl(node);
      }
      else if (type == JVM_INS_ARG_FIELD_REF_NAME) {
        return new JALJvmInsArgFieldRefNameImpl(node);
      }
      else if (type == JVM_INS_ARG_FIELD_REF_TYPE) {
        return new JALJvmInsArgFieldRefTypeImpl(node);
      }
      else if (type == JVM_INS_ARG_LOCAL_REF) {
        return new JALJvmInsArgLocalRefImpl(node);
      }
      else if (type == JVM_INS_ARG_LOOKUP_SWITCH) {
        return new JALJvmInsArgLookupSwitchImpl(node);
      }
      else if (type == JVM_INS_ARG_LOOKUP_SWITCH_CASE) {
        return new JALJvmInsArgLookupSwitchCaseImpl(node);
      }
      else if (type == JVM_INS_ARG_LOOKUP_SWITCH_CASE_NAME) {
        return new JALJvmInsArgLookupSwitchCaseNameImpl(node);
      }
      else if (type == JVM_INS_ARG_METHOD_REF) {
        return new JALJvmInsArgMethodRefImpl(node);
      }
      else if (type == JVM_INS_ARG_METHOD_REF_OWNER_TYPE) {
        return new JALJvmInsArgMethodRefOwnerTypeImpl(node);
      }
      else if (type == JVM_INS_ARG_METHOD_SPECIAL_REF) {
        return new JALJvmInsArgMethodSpecialRefImpl(node);
      }
      else if (type == JVM_INS_ARG_SCALAR_TYPE) {
        return new JALJvmInsArgScalarTypeImpl(node);
      }
      else if (type == JVM_INS_ARG_TABLE_SWITCH) {
        return new JALJvmInsArgTableSwitchImpl(node);
      }
      else if (type == JVM_INS_ARRAYLENGTH) {
        return new JALJvmInsArraylengthImpl(node);
      }
      else if (type == JVM_INS_ASTORE) {
        return new JALJvmInsAstoreImpl(node);
      }
      else if (type == JVM_INS_ASTORE_N) {
        return new JALJvmInsAstoreNImpl(node);
      }
      else if (type == JVM_INS_ATHROW) {
        return new JALJvmInsAthrowImpl(node);
      }
      else if (type == JVM_INS_BALOAD) {
        return new JALJvmInsBaloadImpl(node);
      }
      else if (type == JVM_INS_BASTORE) {
        return new JALJvmInsBastoreImpl(node);
      }
      else if (type == JVM_INS_BIPUSH) {
        return new JALJvmInsBipushImpl(node);
      }
      else if (type == JVM_INS_CALOAD) {
        return new JALJvmInsCaloadImpl(node);
      }
      else if (type == JVM_INS_CASTORE) {
        return new JALJvmInsCastoreImpl(node);
      }
      else if (type == JVM_INS_CHECKCAST) {
        return new JALJvmInsCheckcastImpl(node);
      }
      else if (type == JVM_INS_DADD) {
        return new JALJvmInsDaddImpl(node);
      }
      else if (type == JVM_INS_DALOAD) {
        return new JALJvmInsDaloadImpl(node);
      }
      else if (type == JVM_INS_DASTORE) {
        return new JALJvmInsDastoreImpl(node);
      }
      else if (type == JVM_INS_DCMP_OP) {
        return new JALJvmInsDcmpOPImpl(node);
      }
      else if (type == JVM_INS_DCONST_N) {
        return new JALJvmInsDconstNImpl(node);
      }
      else if (type == JVM_INS_DDIV) {
        return new JALJvmInsDdivImpl(node);
      }
      else if (type == JVM_INS_DLOAD) {
        return new JALJvmInsDloadImpl(node);
      }
      else if (type == JVM_INS_DLOAD_N) {
        return new JALJvmInsDloadNImpl(node);
      }
      else if (type == JVM_INS_DMUL) {
        return new JALJvmInsDmulImpl(node);
      }
      else if (type == JVM_INS_DNEG) {
        return new JALJvmInsDnegImpl(node);
      }
      else if (type == JVM_INS_DREM) {
        return new JALJvmInsDremImpl(node);
      }
      else if (type == JVM_INS_DRETURN) {
        return new JALJvmInsDreturnImpl(node);
      }
      else if (type == JVM_INS_DSTORE) {
        return new JALJvmInsDstoreImpl(node);
      }
      else if (type == JVM_INS_DSTORE_N) {
        return new JALJvmInsDstoreNImpl(node);
      }
      else if (type == JVM_INS_DSUB) {
        return new JALJvmInsDsubImpl(node);
      }
      else if (type == JVM_INS_DUP) {
        return new JALJvmInsDupImpl(node);
      }
      else if (type == JVM_INS_DUP_2) {
        return new JALJvmInsDup2Impl(node);
      }
      else if (type == JVM_INS_DUP_2_X_1) {
        return new JALJvmInsDup2X1Impl(node);
      }
      else if (type == JVM_INS_DUP_2_X_2) {
        return new JALJvmInsDup2X2Impl(node);
      }
      else if (type == JVM_INS_DUP_X_1) {
        return new JALJvmInsDupX1Impl(node);
      }
      else if (type == JVM_INS_DUP_X_2) {
        return new JALJvmInsDupX2Impl(node);
      }
      else if (type == JVM_INS_D_2_F) {
        return new JALJvmInsD2FImpl(node);
      }
      else if (type == JVM_INS_D_2_I) {
        return new JALJvmInsD2IImpl(node);
      }
      else if (type == JVM_INS_D_2_L) {
        return new JALJvmInsD2LImpl(node);
      }
      else if (type == JVM_INS_FADD) {
        return new JALJvmInsFaddImpl(node);
      }
      else if (type == JVM_INS_FALOAD) {
        return new JALJvmInsFaloadImpl(node);
      }
      else if (type == JVM_INS_FASTORE) {
        return new JALJvmInsFastoreImpl(node);
      }
      else if (type == JVM_INS_FCMPG_OP) {
        return new JALJvmInsFcmpgOPImpl(node);
      }
      else if (type == JVM_INS_FCONST_N) {
        return new JALJvmInsFconstNImpl(node);
      }
      else if (type == JVM_INS_FDIV) {
        return new JALJvmInsFdivImpl(node);
      }
      else if (type == JVM_INS_FLOAD) {
        return new JALJvmInsFloadImpl(node);
      }
      else if (type == JVM_INS_FLOAD_N) {
        return new JALJvmInsFloadNImpl(node);
      }
      else if (type == JVM_INS_FMUL) {
        return new JALJvmInsFmulImpl(node);
      }
      else if (type == JVM_INS_FNEG) {
        return new JALJvmInsFnegImpl(node);
      }
      else if (type == JVM_INS_FREM) {
        return new JALJvmInsFremImpl(node);
      }
      else if (type == JVM_INS_FRETURN) {
        return new JALJvmInsFreturnImpl(node);
      }
      else if (type == JVM_INS_FSTORE) {
        return new JALJvmInsFstoreImpl(node);
      }
      else if (type == JVM_INS_FSTORE_N) {
        return new JALJvmInsFstoreNImpl(node);
      }
      else if (type == JVM_INS_FSUB) {
        return new JALJvmInsFsubImpl(node);
      }
      else if (type == JVM_INS_F_2_D) {
        return new JALJvmInsF2DImpl(node);
      }
      else if (type == JVM_INS_F_2_I) {
        return new JALJvmInsF2IImpl(node);
      }
      else if (type == JVM_INS_F_2_L) {
        return new JALJvmInsF2LImpl(node);
      }
      else if (type == JVM_INS_GETFIELD) {
        return new JALJvmInsGetfieldImpl(node);
      }
      else if (type == JVM_INS_GETSTATIC) {
        return new JALJvmInsGetstaticImpl(node);
      }
      else if (type == JVM_INS_GOTO) {
        return new JALJvmInsGotoImpl(node);
      }
      else if (type == JVM_INS_GOTO_W) {
        return new JALJvmInsGotoWImpl(node);
      }
      else if (type == JVM_INS_IADD) {
        return new JALJvmInsIaddImpl(node);
      }
      else if (type == JVM_INS_IALOAD) {
        return new JALJvmInsIaloadImpl(node);
      }
      else if (type == JVM_INS_IAND) {
        return new JALJvmInsIandImpl(node);
      }
      else if (type == JVM_INS_IASTORE) {
        return new JALJvmInsIastoreImpl(node);
      }
      else if (type == JVM_INS_ICONST_N) {
        return new JALJvmInsIconstNImpl(node);
      }
      else if (type == JVM_INS_IDIV) {
        return new JALJvmInsIdivImpl(node);
      }
      else if (type == JVM_INS_IF_ACMP_OP) {
        return new JALJvmInsIfAcmpOPImpl(node);
      }
      else if (type == JVM_INS_IF_ICMP_OP) {
        return new JALJvmInsIfIcmpOPImpl(node);
      }
      else if (type == JVM_INS_IF_NONNULL) {
        return new JALJvmInsIfNonnullImpl(node);
      }
      else if (type == JVM_INS_IF_NULL) {
        return new JALJvmInsIfNullImpl(node);
      }
      else if (type == JVM_INS_IF_OP) {
        return new JALJvmInsIfOPImpl(node);
      }
      else if (type == JVM_INS_IINC) {
        return new JALJvmInsIincImpl(node);
      }
      else if (type == JVM_INS_ILOAD) {
        return new JALJvmInsIloadImpl(node);
      }
      else if (type == JVM_INS_ILOAD_N) {
        return new JALJvmInsIloadNImpl(node);
      }
      else if (type == JVM_INS_IMUL) {
        return new JALJvmInsImulImpl(node);
      }
      else if (type == JVM_INS_INEG) {
        return new JALJvmInsInegImpl(node);
      }
      else if (type == JVM_INS_INSTANCEOF) {
        return new JALJvmInsInstanceofImpl(node);
      }
      else if (type == JVM_INS_INVOKEDYNAMIC) {
        return new JALJvmInsInvokedynamicImpl(node);
      }
      else if (type == JVM_INS_INVOKEINTERFACE) {
        return new JALJvmInsInvokeinterfaceImpl(node);
      }
      else if (type == JVM_INS_INVOKESPECIAL) {
        return new JALJvmInsInvokespecialImpl(node);
      }
      else if (type == JVM_INS_INVOKESTATIC) {
        return new JALJvmInsInvokestaticImpl(node);
      }
      else if (type == JVM_INS_INVOKEVIRTUAL) {
        return new JALJvmInsInvokevirtualImpl(node);
      }
      else if (type == JVM_INS_IOR) {
        return new JALJvmInsIorImpl(node);
      }
      else if (type == JVM_INS_IREM) {
        return new JALJvmInsIremImpl(node);
      }
      else if (type == JVM_INS_IRETURN) {
        return new JALJvmInsIreturnImpl(node);
      }
      else if (type == JVM_INS_ISHL) {
        return new JALJvmInsIshlImpl(node);
      }
      else if (type == JVM_INS_ISHR) {
        return new JALJvmInsIshrImpl(node);
      }
      else if (type == JVM_INS_ISTORE) {
        return new JALJvmInsIstoreImpl(node);
      }
      else if (type == JVM_INS_ISTORE_N) {
        return new JALJvmInsIstoreNImpl(node);
      }
      else if (type == JVM_INS_ISUB) {
        return new JALJvmInsIsubImpl(node);
      }
      else if (type == JVM_INS_IUSHR) {
        return new JALJvmInsIushrImpl(node);
      }
      else if (type == JVM_INS_IXOR) {
        return new JALJvmInsIxorImpl(node);
      }
      else if (type == JVM_INS_I_2_B) {
        return new JALJvmInsI2BImpl(node);
      }
      else if (type == JVM_INS_I_2_C) {
        return new JALJvmInsI2CImpl(node);
      }
      else if (type == JVM_INS_I_2_D) {
        return new JALJvmInsI2DImpl(node);
      }
      else if (type == JVM_INS_I_2_F) {
        return new JALJvmInsI2FImpl(node);
      }
      else if (type == JVM_INS_I_2_L) {
        return new JALJvmInsI2LImpl(node);
      }
      else if (type == JVM_INS_I_2_S) {
        return new JALJvmInsI2SImpl(node);
      }
      else if (type == JVM_INS_JSR) {
        return new JALJvmInsJsrImpl(node);
      }
      else if (type == JVM_INS_JSR_W) {
        return new JALJvmInsJsrWImpl(node);
      }
      else if (type == JVM_INS_LADD) {
        return new JALJvmInsLaddImpl(node);
      }
      else if (type == JVM_INS_LALOAD) {
        return new JALJvmInsLaloadImpl(node);
      }
      else if (type == JVM_INS_LAND) {
        return new JALJvmInsLandImpl(node);
      }
      else if (type == JVM_INS_LASTORE) {
        return new JALJvmInsLastoreImpl(node);
      }
      else if (type == JVM_INS_LCMP) {
        return new JALJvmInsLcmpImpl(node);
      }
      else if (type == JVM_INS_LCONST_N) {
        return new JALJvmInsLconstNImpl(node);
      }
      else if (type == JVM_INS_LDC) {
        return new JALJvmInsLdcImpl(node);
      }
      else if (type == JVM_INS_LDC_2_W) {
        return new JALJvmInsLdc2WImpl(node);
      }
      else if (type == JVM_INS_LDC_W) {
        return new JALJvmInsLdcWImpl(node);
      }
      else if (type == JVM_INS_LDIV) {
        return new JALJvmInsLdivImpl(node);
      }
      else if (type == JVM_INS_LLOAD) {
        return new JALJvmInsLloadImpl(node);
      }
      else if (type == JVM_INS_LLOAD_N) {
        return new JALJvmInsLloadNImpl(node);
      }
      else if (type == JVM_INS_LMUL) {
        return new JALJvmInsLmulImpl(node);
      }
      else if (type == JVM_INS_LNEG) {
        return new JALJvmInsLnegImpl(node);
      }
      else if (type == JVM_INS_LOOKUPSWITCH) {
        return new JALJvmInsLookupswitchImpl(node);
      }
      else if (type == JVM_INS_LOR) {
        return new JALJvmInsLorImpl(node);
      }
      else if (type == JVM_INS_LREM) {
        return new JALJvmInsLremImpl(node);
      }
      else if (type == JVM_INS_LRETURN) {
        return new JALJvmInsLreturnImpl(node);
      }
      else if (type == JVM_INS_LSHL) {
        return new JALJvmInsLshlImpl(node);
      }
      else if (type == JVM_INS_LSHR) {
        return new JALJvmInsLshrImpl(node);
      }
      else if (type == JVM_INS_LSTORE) {
        return new JALJvmInsLstoreImpl(node);
      }
      else if (type == JVM_INS_LSTORE_N) {
        return new JALJvmInsLstoreNImpl(node);
      }
      else if (type == JVM_INS_LSUB) {
        return new JALJvmInsLsubImpl(node);
      }
      else if (type == JVM_INS_LUSHR) {
        return new JALJvmInsLushrImpl(node);
      }
      else if (type == JVM_INS_LXOR) {
        return new JALJvmInsLxorImpl(node);
      }
      else if (type == JVM_INS_L_2_D) {
        return new JALJvmInsL2DImpl(node);
      }
      else if (type == JVM_INS_L_2_F) {
        return new JALJvmInsL2FImpl(node);
      }
      else if (type == JVM_INS_L_2_I) {
        return new JALJvmInsL2IImpl(node);
      }
      else if (type == JVM_INS_MONITORENTER) {
        return new JALJvmInsMonitorenterImpl(node);
      }
      else if (type == JVM_INS_MONITOREXIT) {
        return new JALJvmInsMonitorexitImpl(node);
      }
      else if (type == JVM_INS_MULTIANEWARRAY) {
        return new JALJvmInsMultianewarrayImpl(node);
      }
      else if (type == JVM_INS_NEW) {
        return new JALJvmInsNewImpl(node);
      }
      else if (type == JVM_INS_NEWARRAY) {
        return new JALJvmInsNewarrayImpl(node);
      }
      else if (type == JVM_INS_NOP) {
        return new JALJvmInsNopImpl(node);
      }
      else if (type == JVM_INS_POP) {
        return new JALJvmInsPopImpl(node);
      }
      else if (type == JVM_INS_POP_2) {
        return new JALJvmInsPop2Impl(node);
      }
      else if (type == JVM_INS_PUTFIELD) {
        return new JALJvmInsPutfieldImpl(node);
      }
      else if (type == JVM_INS_PUTSTATIC) {
        return new JALJvmInsPutstaticImpl(node);
      }
      else if (type == JVM_INS_RET) {
        return new JALJvmInsRetImpl(node);
      }
      else if (type == JVM_INS_RETURN) {
        return new JALJvmInsReturnImpl(node);
      }
      else if (type == JVM_INS_SALOAD) {
        return new JALJvmInsSaloadImpl(node);
      }
      else if (type == JVM_INS_SASTORE) {
        return new JALJvmInsSastoreImpl(node);
      }
      else if (type == JVM_INS_SIPUSH) {
        return new JALJvmInsSipushImpl(node);
      }
      else if (type == JVM_INS_SWAP) {
        return new JALJvmInsSwapImpl(node);
      }
      else if (type == JVM_INS_TABLESWITCH) {
        return new JALJvmInsTableswitchImpl(node);
      }
      else if (type == JVM_INS_WIDE) {
        return new JALJvmInsWideImpl(node);
      }
      else if (type == LABEL) {
        return new JALLabelImpl(node);
      }
      else if (type == LABEL_NAME) {
        return new JALLabelNameImpl(node);
      }
      else if (type == METHOD_BODY_ITEM) {
        return new JALMethodBodyItemImpl(node);
      }
      else if (type == METHOD_DEFINITION) {
        return new JALMethodDefinitionImpl(node);
      }
      else if (type == METHOD_DESCRIPTOR) {
        return new JALMethodDescriptorImpl(node);
      }
      else if (type == METHOD_DESCRIPTOR_ARGS) {
        return new JALMethodDescriptorArgsImpl(node);
      }
      else if (type == TYPE_DESCRIPTOR) {
        return new JALTypeDescriptorImpl(node);
      }
      else if (type == TYPE_DESCRIPTOR_PRIMITIVE) {
        return new JALTypeDescriptorPrimitiveImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
