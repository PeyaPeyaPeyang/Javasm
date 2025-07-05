// This is a generated file. Not intended for manual editing.
package tokyo.peya.plugin.javasm.langjal.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static tokyo.peya.plugin.javasm.langjal.psi.JALTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class JALParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(INSTRUCTION, JVM_INS_AALOAD, JVM_INS_AASTORE, JVM_INS_ACONST_NULL,
      JVM_INS_ALOAD, JVM_INS_ALOAD_N, JVM_INS_ANEW_ARRAY, JVM_INS_ARETURN,
      JVM_INS_ARG_FIELD_REF, JVM_INS_ARG_FIELD_REF_NAME, JVM_INS_ARG_FIELD_REF_TYPE, JVM_INS_ARG_LOCAL_REF,
      JVM_INS_ARG_LOOKUP_SWITCH, JVM_INS_ARG_LOOKUP_SWITCH_CASE, JVM_INS_ARG_LOOKUP_SWITCH_CASE_NAME, JVM_INS_ARG_METHOD_REF,
      JVM_INS_ARG_METHOD_REF_OWNER_TYPE, JVM_INS_ARG_METHOD_SPECIAL_REF, JVM_INS_ARG_SCALAR_TYPE, JVM_INS_ARG_TABLE_SWITCH,
      JVM_INS_ARG_UNSIGNED_8_BYTES, JVM_INS_ARRAYLENGTH, JVM_INS_ASTORE, JVM_INS_ASTORE_N,
      JVM_INS_ATHROW, JVM_INS_BALOAD, JVM_INS_BASTORE, JVM_INS_BIPUSH,
      JVM_INS_CALOAD, JVM_INS_CASTORE, JVM_INS_CHECKCAST, JVM_INS_DADD,
      JVM_INS_DALOAD, JVM_INS_DASTORE, JVM_INS_DCMP_OP, JVM_INS_DCONST_N,
      JVM_INS_DDIV, JVM_INS_DLOAD, JVM_INS_DLOAD_N, JVM_INS_DMUL,
      JVM_INS_DNEG, JVM_INS_DREM, JVM_INS_DRETURN, JVM_INS_DSTORE,
      JVM_INS_DSTORE_N, JVM_INS_DSUB, JVM_INS_DUP, JVM_INS_DUP_2,
      JVM_INS_DUP_2_X_1, JVM_INS_DUP_2_X_2, JVM_INS_DUP_X_1, JVM_INS_DUP_X_2,
      JVM_INS_D_2_F, JVM_INS_D_2_I, JVM_INS_D_2_L, JVM_INS_FADD,
      JVM_INS_FALOAD, JVM_INS_FASTORE, JVM_INS_FCMPG_OP, JVM_INS_FCONST_N,
      JVM_INS_FDIV, JVM_INS_FLOAD, JVM_INS_FLOAD_N, JVM_INS_FMUL,
      JVM_INS_FNEG, JVM_INS_FREM, JVM_INS_FRETURN, JVM_INS_FSTORE,
      JVM_INS_FSTORE_N, JVM_INS_FSUB, JVM_INS_F_2_D, JVM_INS_F_2_I,
      JVM_INS_F_2_L, JVM_INS_GETFIELD, JVM_INS_GETSTATIC, JVM_INS_GOTO,
      JVM_INS_GOTO_W, JVM_INS_IADD, JVM_INS_IALOAD, JVM_INS_IAND,
      JVM_INS_IASTORE, JVM_INS_ICONST_N, JVM_INS_IDIV, JVM_INS_IF_ACMP_OP,
      JVM_INS_IF_ICMP_OP, JVM_INS_IF_NONNULL, JVM_INS_IF_NULL, JVM_INS_IF_OP,
      JVM_INS_IINC, JVM_INS_ILOAD, JVM_INS_ILOAD_N, JVM_INS_IMUL,
      JVM_INS_INEG, JVM_INS_INSTANCEOF, JVM_INS_INVOKEDYNAMIC, JVM_INS_INVOKEINTERFACE,
      JVM_INS_INVOKESPECIAL, JVM_INS_INVOKESTATIC, JVM_INS_INVOKEVIRTUAL, JVM_INS_IOR,
      JVM_INS_IREM, JVM_INS_IRETURN, JVM_INS_ISHL, JVM_INS_ISHR,
      JVM_INS_ISTORE, JVM_INS_ISTORE_N, JVM_INS_ISUB, JVM_INS_IUSHR,
      JVM_INS_IXOR, JVM_INS_I_2_B, JVM_INS_I_2_C, JVM_INS_I_2_D,
      JVM_INS_I_2_F, JVM_INS_I_2_L, JVM_INS_I_2_S, JVM_INS_JSR,
      JVM_INS_JSR_W, JVM_INS_LADD, JVM_INS_LALOAD, JVM_INS_LAND,
      JVM_INS_LASTORE, JVM_INS_LCMP, JVM_INS_LCONST_N, JVM_INS_LDC,
      JVM_INS_LDC_2_W, JVM_INS_LDC_W, JVM_INS_LDIV, JVM_INS_LLOAD,
      JVM_INS_LLOAD_N, JVM_INS_LMUL, JVM_INS_LNEG, JVM_INS_LOOKUPSWITCH,
      JVM_INS_LOR, JVM_INS_LREM, JVM_INS_LRETURN, JVM_INS_LSHL,
      JVM_INS_LSHR, JVM_INS_LSTORE, JVM_INS_LSTORE_N, JVM_INS_LSUB,
      JVM_INS_LUSHR, JVM_INS_LXOR, JVM_INS_L_2_D, JVM_INS_L_2_F,
      JVM_INS_L_2_I, JVM_INS_MONITORENTER, JVM_INS_MONITOREXIT, JVM_INS_MULTIANEWARRAY,
      JVM_INS_NEW, JVM_INS_NEWARRAY, JVM_INS_NOP, JVM_INS_POP,
      JVM_INS_POP_2, JVM_INS_PUTFIELD, JVM_INS_PUTSTATIC, JVM_INS_RET,
      JVM_INS_RETURN, JVM_INS_SALOAD, JVM_INS_SASTORE, JVM_INS_SIPUSH,
      JVM_INS_SWAP, JVM_INS_TABLESWITCH, JVM_INS_WIDE),
  };

  /* ********************************************************** */
  // KWD_ACC_ATTR_STATIC | KWD_ACC_ATTR_FINAL | KWD_ACC_ATTR_ABSTRACT | KWD_ACC_ATTR_SYNTHETIC
  //                  | KWD_ACC_ATTR_ANNOTATION | KWD_ACC_ATTR_ENUM
  public static boolean accAttrClass(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accAttrClass")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACC_ATTR_CLASS, "<accessAttribute>");
    r = consumeToken(b, KWD_ACC_ATTR_STATIC);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_FINAL);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_ABSTRACT);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_SYNTHETIC);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_ANNOTATION);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_ENUM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KWD_ACC_ATTR_STATIC | KWD_ACC_ATTR_FINAL | KWD_ACC_ATTR_TRANSIENT | KWD_ACC_ATTR_VOLATILE | KWD_ACC_ATTR_STRICTFP | KWD_ACC_ATTR_SYNTHETIC
  public static boolean accAttrField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accAttrField")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACC_ATTR_FIELD, "<accessAttribute>");
    r = consumeToken(b, KWD_ACC_ATTR_STATIC);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_FINAL);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_TRANSIENT);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_VOLATILE);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_STRICTFP);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_SYNTHETIC);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KWD_ACC_ATTR_STATIC | KWD_ACC_ATTR_FINAL | KWD_ACC_ATTR_ABSTRACT | KWD_ACC_ATTR_NATIVE
  //                   | KWD_ACC_ATTR_SYNCHRONIZED | KWD_ACC_ATTR_TRANSIENT | KWD_ACC_ATTR_VOLATILE | KWD_ACC_ATTR_STRICTFP
  //                   | KWD_ACC_ATTR_SYNTHETIC
  public static boolean accAttrMethod(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accAttrMethod")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACC_ATTR_METHOD, "<accessAttribute>");
    r = consumeToken(b, KWD_ACC_ATTR_STATIC);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_FINAL);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_ABSTRACT);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_NATIVE);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_SYNCHRONIZED);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_TRANSIENT);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_VOLATILE);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_STRICTFP);
    if (!r) r = consumeToken(b, KWD_ACC_ATTR_SYNTHETIC);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // accessLevel? accAttrClass*
  public static boolean accModClass(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModClass")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACC_MOD_CLASS, "<accessModifier>");
    r = accModClass_0(b, l + 1);
    r = r && accModClass_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // accessLevel?
  private static boolean accModClass_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModClass_0")) return false;
    accessLevel(b, l + 1);
    return true;
  }

  // accAttrClass*
  private static boolean accModClass_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModClass_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!accAttrClass(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "accModClass_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // accessLevel? accAttrField*
  public static boolean accModField(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModField")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACC_MOD_FIELD, "<accessModifier>");
    r = accModField_0(b, l + 1);
    r = r && accModField_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // accessLevel?
  private static boolean accModField_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModField_0")) return false;
    accessLevel(b, l + 1);
    return true;
  }

  // accAttrField*
  private static boolean accModField_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModField_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!accAttrField(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "accModField_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // accessLevel? accAttrMethod*
  public static boolean accModMethod(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModMethod")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACC_MOD_METHOD, "<accessModifier>");
    r = accModMethod_0(b, l + 1);
    r = r && accModMethod_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // accessLevel?
  private static boolean accModMethod_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModMethod_0")) return false;
    accessLevel(b, l + 1);
    return true;
  }

  // accAttrMethod*
  private static boolean accModMethod_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accModMethod_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!accAttrMethod(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "accModMethod_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KWD_ACC_PUBLIC | KWD_ACC_PRIVATE | KWD_ACC_PROTECTED
  public static boolean accessLevel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "accessLevel")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACCESS_LEVEL, "<access level>");
    r = consumeToken(b, KWD_ACC_PUBLIC);
    if (!r) r = consumeToken(b, KWD_ACC_PRIVATE);
    if (!r) r = consumeToken(b, KWD_ACC_PROTECTED);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // classBodyItem*
  public static boolean classBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBody")) return false;
    Marker m = enter_section_(b, l, _NONE_, CLASS_BODY, "<class body>");
    while (true) {
      int c = current_position_(b);
      if (!classBodyItem(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classBody", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // fieldDefinition | methodDefinition
  static boolean classBodyItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classBodyItem")) return false;
    boolean r;
    r = fieldDefinition(b, l + 1);
    if (!r) r = methodDefinition(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // accModClass (KWD_CLASS| KWD_INTERFACE) className (LP classMeta? RP)? LBR classBody RBR
  public static boolean classDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_DEFINITION, "<class definition>");
    r = accModClass(b, l + 1);
    r = r && classDefinition_1(b, l + 1);
    p = r; // pin = 2
    r = r && report_error_(b, className(b, l + 1));
    r = p && report_error_(b, classDefinition_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, LBR)) && r;
    r = p && report_error_(b, classBody(b, l + 1)) && r;
    r = p && consumeToken(b, RBR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // KWD_CLASS| KWD_INTERFACE
  private static boolean classDefinition_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition_1")) return false;
    boolean r;
    r = consumeToken(b, KWD_CLASS);
    if (!r) r = consumeToken(b, KWD_INTERFACE);
    return r;
  }

  // (LP classMeta? RP)?
  private static boolean classDefinition_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition_3")) return false;
    classDefinition_3_0(b, l + 1);
    return true;
  }

  // LP classMeta? RP
  private static boolean classDefinition_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LP);
    r = r && classDefinition_3_0_1(b, l + 1);
    r = r && consumeToken(b, RP);
    exit_section_(b, m, null, r);
    return r;
  }

  // classMeta?
  private static boolean classDefinition_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classDefinition_3_0_1")) return false;
    classMeta(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // classMetaItem (COMMA classMetaItem)*
  public static boolean classMeta(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classMeta")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_META, "<class meta>");
    r = classMetaItem(b, l + 1);
    r = r && classMeta_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA classMetaItem)*
  private static boolean classMeta_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classMeta_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!classMeta_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classMeta_1", c)) break;
    }
    return true;
  }

  // COMMA classMetaItem
  private static boolean classMeta_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classMeta_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && classMetaItem(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // classPropMajor | classPropMinor | classPropSuperClass | classPropInterfaces
  public static boolean classMetaItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classMetaItem")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_META_ITEM, "<class meta item>");
    r = classPropMajor(b, l + 1);
    if (!r) r = classPropMinor(b, l + 1);
    if (!r) r = classPropSuperClass(b, l + 1);
    if (!r) r = classPropInterfaces(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id | FULL_QUALIFIED_CLASS_NAME
  public static boolean className(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "className")) return false;
    if (!nextTokenIs(b, "<class name>", FULL_QUALIFIED_CLASS_NAME, ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CLASS_NAME, "<class name>");
    r = consumeToken(b, ID);
    if (!r) r = consumeToken(b, FULL_QUALIFIED_CLASS_NAME);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KWD_CLASS_PROP_INTERFACES EQ className (COMMA className)*
  public static boolean classPropInterfaces(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classPropInterfaces")) return false;
    if (!nextTokenIs(b, KWD_CLASS_PROP_INTERFACES)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_PROP_INTERFACES, null);
    r = consumeTokens(b, 1, KWD_CLASS_PROP_INTERFACES, EQ);
    p = r; // pin = 1
    r = r && report_error_(b, className(b, l + 1));
    r = p && classPropInterfaces_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (COMMA className)*
  private static boolean classPropInterfaces_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classPropInterfaces_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!classPropInterfaces_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "classPropInterfaces_3", c)) break;
    }
    return true;
  }

  // COMMA className
  private static boolean classPropInterfaces_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classPropInterfaces_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && className(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KWD_CLASS_PROP_MAJOR EQ INSN_ARG_UNSIG_8BYTES
  public static boolean classPropMajor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classPropMajor")) return false;
    if (!nextTokenIs(b, KWD_CLASS_PROP_MAJOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_PROP_MAJOR, null);
    r = consumeTokens(b, 1, KWD_CLASS_PROP_MAJOR, EQ, INSN_ARG_UNSIG_8BYTES);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // KWD_CLASS_PROP_MINOR EQ INSN_ARG_UNSIG_8BYTES
  public static boolean classPropMinor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classPropMinor")) return false;
    if (!nextTokenIs(b, KWD_CLASS_PROP_MINOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_PROP_MINOR, null);
    r = consumeTokens(b, 1, KWD_CLASS_PROP_MINOR, EQ, INSN_ARG_UNSIG_8BYTES);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // KWD_CLASS_PROP_SUPER_CLASS EQ className
  public static boolean classPropSuperClass(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "classPropSuperClass")) return false;
    if (!nextTokenIs(b, KWD_CLASS_PROP_SUPER_CLASS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CLASS_PROP_SUPER_CLASS, null);
    r = consumeTokens(b, 1, KWD_CLASS_PROP_SUPER_CLASS, EQ);
    p = r; // pin = 1
    r = r && className(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // accModField fieldName COLON typeDescriptor
  public static boolean fieldDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDefinition")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FIELD_DEFINITION, "<field definition>");
    r = accModField(b, l + 1);
    r = r && fieldName(b, l + 1);
    r = r && consumeToken(b, COLON);
    p = r; // pin = 3
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // id
  public static boolean fieldName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldName")) return false;
    if (!nextTokenIs(b, ID)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ID);
    exit_section_(b, m, FIELD_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // jvmInsAaload | jvmInsAastore  | jvmInsAconstNull | jvmInsAload | jvmInsAloadN  | jvmInsAnewArray
  //                 | jvmInsAreturn | jvmInsArraylength | jvmInsAstore | jvmInsAstoreN | jvmInsAthrow | jvmInsBaload
  //                 | jvmInsBastore | jvmInsBipush | jvmInsCaload | jvmInsCastore | jvmInsCheckcast | jvmInsD2F
  //                 | jvmInsD2I | jvmInsD2L | jvmInsDadd | jvmInsDaload | jvmInsDastore | jvmInsDcmpOP | jvmInsDconstN
  //                 | jvmInsDdiv | jvmInsDload | jvmInsDloadN | jvmInsDmul | jvmInsDneg | jvmInsDrem  | jvmInsDreturn
  //                 | jvmInsDstore | jvmInsDstoreN | jvmInsDsub | jvmInsDup | jvmInsDupX1 | jvmInsDupX2 | jvmInsDup2
  //                 | jvmInsDup2X1 | jvmInsDup2X2 | jvmInsF2D | jvmInsF2I | jvmInsF2L | jvmInsFadd | jvmInsFaload
  //                 | jvmInsFastore | jvmInsFcmpgOP | jvmInsFconstN | jvmInsFdiv | jvmInsFload | jvmInsFloadN | jvmInsFmul
  //                 | jvmInsFneg | jvmInsFrem | jvmInsFreturn | jvmInsFstore | jvmInsFstoreN | jvmInsFsub | jvmInsGetfield
  //                 | jvmInsGetstatic | jvmInsGoto | jvmInsGotoW | jvmInsI2B | jvmInsI2C | jvmInsI2D | jvmInsI2F
  //                 | jvmInsI2L | jvmInsI2S | jvmInsIadd | jvmInsIaload | jvmInsIand | jvmInsIastore | jvmInsIconstN
  //                 | jvmInsIdiv | jvmInsIfAcmpOP | jvmInsIfIcmpOP | jvmInsIfOP | jvmInsIfNonnull | jvmInsIfNull
  //                 | jvmInsIinc | jvmInsIload | jvmInsIloadN | jvmInsImul | jvmInsIneg | jvmInsInstanceof
  //                 | jvmInsInvokedynamic | jvmInsInvokeinterface  | jvmInsInvokespecial | jvmInsInvokestatic
  //                 | jvmInsInvokevirtual | jvmInsIor | jvmInsIrem | jvmInsIreturn | jvmInsIshl | jvmInsIshr | jvmInsIstore
  //                 | jvmInsIstoreN | jvmInsIsub | jvmInsIushr | jvmInsIxor | jvmInsJsr | jvmInsJsrW | jvmInsL2D
  //                 | jvmInsL2F | jvmInsL2I | jvmInsLadd | jvmInsLaload | jvmInsLand | jvmInsLastore | jvmInsLcmp
  //                 | jvmInsLconstN | jvmInsLdc | jvmInsLdcW | jvmInsLdc2W | jvmInsLdiv | jvmInsLload | jvmInsLloadN
  //                 | jvmInsLmul | jvmInsLneg | jvmInsLookupswitch | jvmInsLor | jvmInsLrem | jvmInsLreturn | jvmInsLshl
  //                 | jvmInsLshr | jvmInsLstore | jvmInsLstoreN | jvmInsLsub | jvmInsLushr | jvmInsLxor | jvmInsMonitorenter
  //                 | jvmInsMonitorexit | jvmInsMultianewarray | jvmInsNew | jvmInsNewarray | jvmInsNop | jvmInsPop
  //                 | jvmInsPop2 | jvmInsPutfield | jvmInsPutstatic | jvmInsRet | jvmInsReturn | jvmInsSaload
  //                 | jvmInsSastore | jvmInsSipush | jvmInsSwap | jvmInsTableswitch | jvmInsWide
  public static boolean instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "instruction")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, INSTRUCTION, "<instruction>");
    r = jvmInsAaload(b, l + 1);
    if (!r) r = jvmInsAastore(b, l + 1);
    if (!r) r = jvmInsAconstNull(b, l + 1);
    if (!r) r = jvmInsAload(b, l + 1);
    if (!r) r = jvmInsAloadN(b, l + 1);
    if (!r) r = jvmInsAnewArray(b, l + 1);
    if (!r) r = jvmInsAreturn(b, l + 1);
    if (!r) r = jvmInsArraylength(b, l + 1);
    if (!r) r = jvmInsAstore(b, l + 1);
    if (!r) r = jvmInsAstoreN(b, l + 1);
    if (!r) r = jvmInsAthrow(b, l + 1);
    if (!r) r = jvmInsBaload(b, l + 1);
    if (!r) r = jvmInsBastore(b, l + 1);
    if (!r) r = jvmInsBipush(b, l + 1);
    if (!r) r = jvmInsCaload(b, l + 1);
    if (!r) r = jvmInsCastore(b, l + 1);
    if (!r) r = jvmInsCheckcast(b, l + 1);
    if (!r) r = jvmInsD2F(b, l + 1);
    if (!r) r = jvmInsD2I(b, l + 1);
    if (!r) r = jvmInsD2L(b, l + 1);
    if (!r) r = jvmInsDadd(b, l + 1);
    if (!r) r = jvmInsDaload(b, l + 1);
    if (!r) r = jvmInsDastore(b, l + 1);
    if (!r) r = jvmInsDcmpOP(b, l + 1);
    if (!r) r = jvmInsDconstN(b, l + 1);
    if (!r) r = jvmInsDdiv(b, l + 1);
    if (!r) r = jvmInsDload(b, l + 1);
    if (!r) r = jvmInsDloadN(b, l + 1);
    if (!r) r = jvmInsDmul(b, l + 1);
    if (!r) r = jvmInsDneg(b, l + 1);
    if (!r) r = jvmInsDrem(b, l + 1);
    if (!r) r = jvmInsDreturn(b, l + 1);
    if (!r) r = jvmInsDstore(b, l + 1);
    if (!r) r = jvmInsDstoreN(b, l + 1);
    if (!r) r = jvmInsDsub(b, l + 1);
    if (!r) r = jvmInsDup(b, l + 1);
    if (!r) r = jvmInsDupX1(b, l + 1);
    if (!r) r = jvmInsDupX2(b, l + 1);
    if (!r) r = jvmInsDup2(b, l + 1);
    if (!r) r = jvmInsDup2X1(b, l + 1);
    if (!r) r = jvmInsDup2X2(b, l + 1);
    if (!r) r = jvmInsF2D(b, l + 1);
    if (!r) r = jvmInsF2I(b, l + 1);
    if (!r) r = jvmInsF2L(b, l + 1);
    if (!r) r = jvmInsFadd(b, l + 1);
    if (!r) r = jvmInsFaload(b, l + 1);
    if (!r) r = jvmInsFastore(b, l + 1);
    if (!r) r = jvmInsFcmpgOP(b, l + 1);
    if (!r) r = jvmInsFconstN(b, l + 1);
    if (!r) r = jvmInsFdiv(b, l + 1);
    if (!r) r = jvmInsFload(b, l + 1);
    if (!r) r = jvmInsFloadN(b, l + 1);
    if (!r) r = jvmInsFmul(b, l + 1);
    if (!r) r = jvmInsFneg(b, l + 1);
    if (!r) r = jvmInsFrem(b, l + 1);
    if (!r) r = jvmInsFreturn(b, l + 1);
    if (!r) r = jvmInsFstore(b, l + 1);
    if (!r) r = jvmInsFstoreN(b, l + 1);
    if (!r) r = jvmInsFsub(b, l + 1);
    if (!r) r = jvmInsGetfield(b, l + 1);
    if (!r) r = jvmInsGetstatic(b, l + 1);
    if (!r) r = jvmInsGoto(b, l + 1);
    if (!r) r = jvmInsGotoW(b, l + 1);
    if (!r) r = jvmInsI2B(b, l + 1);
    if (!r) r = jvmInsI2C(b, l + 1);
    if (!r) r = jvmInsI2D(b, l + 1);
    if (!r) r = jvmInsI2F(b, l + 1);
    if (!r) r = jvmInsI2L(b, l + 1);
    if (!r) r = jvmInsI2S(b, l + 1);
    if (!r) r = jvmInsIadd(b, l + 1);
    if (!r) r = jvmInsIaload(b, l + 1);
    if (!r) r = jvmInsIand(b, l + 1);
    if (!r) r = jvmInsIastore(b, l + 1);
    if (!r) r = jvmInsIconstN(b, l + 1);
    if (!r) r = jvmInsIdiv(b, l + 1);
    if (!r) r = jvmInsIfAcmpOP(b, l + 1);
    if (!r) r = jvmInsIfIcmpOP(b, l + 1);
    if (!r) r = jvmInsIfOP(b, l + 1);
    if (!r) r = jvmInsIfNonnull(b, l + 1);
    if (!r) r = jvmInsIfNull(b, l + 1);
    if (!r) r = jvmInsIinc(b, l + 1);
    if (!r) r = jvmInsIload(b, l + 1);
    if (!r) r = jvmInsIloadN(b, l + 1);
    if (!r) r = jvmInsImul(b, l + 1);
    if (!r) r = jvmInsIneg(b, l + 1);
    if (!r) r = jvmInsInstanceof(b, l + 1);
    if (!r) r = jvmInsInvokedynamic(b, l + 1);
    if (!r) r = jvmInsInvokeinterface(b, l + 1);
    if (!r) r = jvmInsInvokespecial(b, l + 1);
    if (!r) r = jvmInsInvokestatic(b, l + 1);
    if (!r) r = jvmInsInvokevirtual(b, l + 1);
    if (!r) r = jvmInsIor(b, l + 1);
    if (!r) r = jvmInsIrem(b, l + 1);
    if (!r) r = jvmInsIreturn(b, l + 1);
    if (!r) r = jvmInsIshl(b, l + 1);
    if (!r) r = jvmInsIshr(b, l + 1);
    if (!r) r = jvmInsIstore(b, l + 1);
    if (!r) r = jvmInsIstoreN(b, l + 1);
    if (!r) r = jvmInsIsub(b, l + 1);
    if (!r) r = jvmInsIushr(b, l + 1);
    if (!r) r = jvmInsIxor(b, l + 1);
    if (!r) r = jvmInsJsr(b, l + 1);
    if (!r) r = jvmInsJsrW(b, l + 1);
    if (!r) r = jvmInsL2D(b, l + 1);
    if (!r) r = jvmInsL2F(b, l + 1);
    if (!r) r = jvmInsL2I(b, l + 1);
    if (!r) r = jvmInsLadd(b, l + 1);
    if (!r) r = jvmInsLaload(b, l + 1);
    if (!r) r = jvmInsLand(b, l + 1);
    if (!r) r = jvmInsLastore(b, l + 1);
    if (!r) r = jvmInsLcmp(b, l + 1);
    if (!r) r = jvmInsLconstN(b, l + 1);
    if (!r) r = jvmInsLdc(b, l + 1);
    if (!r) r = jvmInsLdcW(b, l + 1);
    if (!r) r = jvmInsLdc2W(b, l + 1);
    if (!r) r = jvmInsLdiv(b, l + 1);
    if (!r) r = jvmInsLload(b, l + 1);
    if (!r) r = jvmInsLloadN(b, l + 1);
    if (!r) r = jvmInsLmul(b, l + 1);
    if (!r) r = jvmInsLneg(b, l + 1);
    if (!r) r = jvmInsLookupswitch(b, l + 1);
    if (!r) r = jvmInsLor(b, l + 1);
    if (!r) r = jvmInsLrem(b, l + 1);
    if (!r) r = jvmInsLreturn(b, l + 1);
    if (!r) r = jvmInsLshl(b, l + 1);
    if (!r) r = jvmInsLshr(b, l + 1);
    if (!r) r = jvmInsLstore(b, l + 1);
    if (!r) r = jvmInsLstoreN(b, l + 1);
    if (!r) r = jvmInsLsub(b, l + 1);
    if (!r) r = jvmInsLushr(b, l + 1);
    if (!r) r = jvmInsLxor(b, l + 1);
    if (!r) r = jvmInsMonitorenter(b, l + 1);
    if (!r) r = jvmInsMonitorexit(b, l + 1);
    if (!r) r = jvmInsMultianewarray(b, l + 1);
    if (!r) r = jvmInsNew(b, l + 1);
    if (!r) r = jvmInsNewarray(b, l + 1);
    if (!r) r = jvmInsNop(b, l + 1);
    if (!r) r = jvmInsPop(b, l + 1);
    if (!r) r = jvmInsPop2(b, l + 1);
    if (!r) r = jvmInsPutfield(b, l + 1);
    if (!r) r = jvmInsPutstatic(b, l + 1);
    if (!r) r = jvmInsRet(b, l + 1);
    if (!r) r = jvmInsReturn(b, l + 1);
    if (!r) r = jvmInsSaload(b, l + 1);
    if (!r) r = jvmInsSastore(b, l + 1);
    if (!r) r = jvmInsSipush(b, l + 1);
    if (!r) r = jvmInsSwap(b, l + 1);
    if (!r) r = jvmInsTableswitch(b, l + 1);
    if (!r) r = jvmInsWide(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_AALOAD
  public static boolean jvmInsAaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_AALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_AALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_AALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_AASTORE jvmInsArgLocalRef
  public static boolean jvmInsAastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_AASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_AASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_AASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ACONST_NULL
  public static boolean jvmInsAconstNull(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAconstNull")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ACONST_NULL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ACONST_NULL, "<JVMInstruction>");
    r = consumeToken(b, INSN_ACONST_NULL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ALOAD jvmInsArgLocalRef
  public static boolean jvmInsAload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ALOAD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_ALOAD);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ALOAD_0 | INSN_ALOAD_1 | INSN_ALOAD_2 | INSN_ALOAD_3 | INSN_ALOAD_4
  public static boolean jvmInsAloadN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAloadN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ALOAD_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_ALOAD_0);
    if (!r) r = consumeToken(b, INSN_ALOAD_1);
    if (!r) r = consumeToken(b, INSN_ALOAD_2);
    if (!r) r = consumeToken(b, INSN_ALOAD_3);
    if (!r) r = consumeToken(b, INSN_ALOAD_4);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ANEWARRAY typeDescriptor
  public static boolean jvmInsAnewArray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAnewArray")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ANEWARRAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ANEW_ARRAY, "<JVMInstruction>");
    r = consumeToken(b, INSN_ANEWARRAY);
    p = r; // pin = 1
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ARETURN
  public static boolean jvmInsAreturn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAreturn")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ARETURN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARETURN, "<JVMInstruction>");
    r = consumeToken(b, INSN_ARETURN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // jvmInsArgFieldRefType REF jvmInsArgFieldRefName COLON jvmInsArgFieldRefType
  public static boolean jvmInsArgFieldRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgFieldRef")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _COLLAPSE_, JVM_INS_ARG_FIELD_REF, "<JVMInstruction>");
    r = jvmInsArgFieldRefType(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, REF));
    r = p && report_error_(b, jvmInsArgFieldRefName(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, COLON)) && r;
    r = p && jvmInsArgFieldRefType(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // id
  public static boolean jvmInsArgFieldRefName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgFieldRefName")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", ID)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_FIELD_REF_NAME, "<JVMInstruction>");
    r = consumeToken(b, ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // typeDescriptor
  public static boolean jvmInsArgFieldRefType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgFieldRefType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_FIELD_REF_TYPE, "<JVMInstruction>");
    r = typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // jvmInsArgUnsigned8Bytes | id
  public static boolean jvmInsArgLocalRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgLocalRef")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", ID, INSN_ARG_UNSIG_8BYTES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, JVM_INS_ARG_LOCAL_REF, "<JVMInstruction>");
    r = jvmInsArgUnsigned8Bytes(b, l + 1);
    if (!r) r = consumeToken(b, ID);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBR jvmInsArgLookupSwitchCase (SEMI jvmInsArgLookupSwitchCase)* RBR
  public static boolean jvmInsArgLookupSwitch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgLookupSwitch")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", LBR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_LOOKUP_SWITCH, "<JVMInstruction>");
    r = consumeToken(b, LBR);
    p = r; // pin = 1
    r = r && report_error_(b, jvmInsArgLookupSwitchCase(b, l + 1));
    r = p && report_error_(b, jvmInsArgLookupSwitch_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (SEMI jvmInsArgLookupSwitchCase)*
  private static boolean jvmInsArgLookupSwitch_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgLookupSwitch_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!jvmInsArgLookupSwitch_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jvmInsArgLookupSwitch_2", c)) break;
    }
    return true;
  }

  // SEMI jvmInsArgLookupSwitchCase
  private static boolean jvmInsArgLookupSwitch_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgLookupSwitch_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMI);
    r = r && jvmInsArgLookupSwitchCase(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // jvmInsArgLookupSwitchCaseName COLON labelName
  public static boolean jvmInsArgLookupSwitchCase(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgLookupSwitchCase")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ARG_UNSIG_8BYTES, KWD_SWITCH_DEFAULT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_LOOKUP_SWITCH_CASE, "<JVMInstruction>");
    r = jvmInsArgLookupSwitchCaseName(b, l + 1);
    r = r && consumeToken(b, COLON);
    p = r; // pin = 2
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // jvmInsArgUnsigned8Bytes | KWD_SWITCH_DEFAULT
  public static boolean jvmInsArgLookupSwitchCaseName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgLookupSwitchCaseName")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ARG_UNSIG_8BYTES, KWD_SWITCH_DEFAULT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, JVM_INS_ARG_LOOKUP_SWITCH_CASE_NAME, "<JVMInstruction>");
    r = jvmInsArgUnsigned8Bytes(b, l + 1);
    if (!r) r = consumeToken(b, KWD_SWITCH_DEFAULT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (jvmInsArgMethodRefOwnerType REF)? methodName methodDescriptor
  public static boolean jvmInsArgMethodRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodRef")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_METHOD_REF, "<JVMInstruction>");
    r = jvmInsArgMethodRef_0(b, l + 1);
    r = r && methodName(b, l + 1);
    r = r && methodDescriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (jvmInsArgMethodRefOwnerType REF)?
  private static boolean jvmInsArgMethodRef_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodRef_0")) return false;
    jvmInsArgMethodRef_0_0(b, l + 1);
    return true;
  }

  // jvmInsArgMethodRefOwnerType REF
  private static boolean jvmInsArgMethodRef_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodRef_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = jvmInsArgMethodRefOwnerType(b, l + 1);
    r = r && consumeToken(b, REF);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // typeDescriptor
  public static boolean jvmInsArgMethodRefOwnerType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodRefOwnerType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_METHOD_REF_OWNER_TYPE, "<JVMInstruction>");
    r = typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // (jvmInsArgMethodRefOwnerType REF)? (KWD_MNAME_INIT | KWD_MNAME_CLINIT) methodDescriptor
  public static boolean jvmInsArgMethodSpecialRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodSpecialRef")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _COLLAPSE_, JVM_INS_ARG_METHOD_SPECIAL_REF, "<JVMInstruction>");
    r = jvmInsArgMethodSpecialRef_0(b, l + 1);
    r = r && jvmInsArgMethodSpecialRef_1(b, l + 1);
    p = r; // pin = 2
    r = r && methodDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (jvmInsArgMethodRefOwnerType REF)?
  private static boolean jvmInsArgMethodSpecialRef_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodSpecialRef_0")) return false;
    jvmInsArgMethodSpecialRef_0_0(b, l + 1);
    return true;
  }

  // jvmInsArgMethodRefOwnerType REF
  private static boolean jvmInsArgMethodSpecialRef_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodSpecialRef_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = jvmInsArgMethodRefOwnerType(b, l + 1);
    r = r && consumeToken(b, REF);
    exit_section_(b, m, null, r);
    return r;
  }

  // KWD_MNAME_INIT | KWD_MNAME_CLINIT
  private static boolean jvmInsArgMethodSpecialRef_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgMethodSpecialRef_1")) return false;
    boolean r;
    r = consumeToken(b, KWD_MNAME_INIT);
    if (!r) r = consumeToken(b, KWD_MNAME_CLINIT);
    return r;
  }

  /* ********************************************************** */
  // string | number | jvmInsArgUnsigned8Bytes
  public static boolean jvmInsArgScalarType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgScalarType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, JVM_INS_ARG_SCALAR_TYPE, "<JVMInstruction>");
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = jvmInsArgUnsigned8Bytes(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // jvmInsArgUnsigned8Bytes LBR labelName* RBR  KWD_SWITCH_DEFAULT labelName
  public static boolean jvmInsArgTableSwitch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgTableSwitch")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ARG_UNSIG_8BYTES)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_TABLE_SWITCH, "<JVMInstruction>");
    r = jvmInsArgUnsigned8Bytes(b, l + 1);
    r = r && consumeToken(b, LBR);
    p = r; // pin = 2
    r = r && report_error_(b, jvmInsArgTableSwitch_2(b, l + 1));
    r = p && report_error_(b, consumeTokens(b, -1, RBR, KWD_SWITCH_DEFAULT)) && r;
    r = p && labelName(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // labelName*
  private static boolean jvmInsArgTableSwitch_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgTableSwitch_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!labelName(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jvmInsArgTableSwitch_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // INSN_ARG_UNSIG_8BYTES
  public static boolean jvmInsArgUnsigned8Bytes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArgUnsigned8Bytes")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ARG_UNSIG_8BYTES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARG_UNSIGNED_8_BYTES, "<JVMInstruction>");
    r = consumeToken(b, INSN_ARG_UNSIG_8BYTES);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ARRAYLENGTH
  public static boolean jvmInsArraylength(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsArraylength")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ARRAYLENGTH)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ARRAYLENGTH, "<JVMInstruction>");
    r = consumeToken(b, INSN_ARRAYLENGTH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ASTORE
  public static boolean jvmInsAstore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAstore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ASTORE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_ASTORE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ASTORE_0 | INSN_ASTORE_1 | INSN_ASTORE_2 | INSN_ASTORE_3
  public static boolean jvmInsAstoreN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAstoreN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ASTORE_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_ASTORE_0);
    if (!r) r = consumeToken(b, INSN_ASTORE_1);
    if (!r) r = consumeToken(b, INSN_ASTORE_2);
    if (!r) r = consumeToken(b, INSN_ASTORE_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ATHROW
  public static boolean jvmInsAthrow(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsAthrow")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ATHROW)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ATHROW, "<JVMInstruction>");
    r = consumeToken(b, INSN_ATHROW);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_BALOAD
  public static boolean jvmInsBaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsBaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_BALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_BALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_BALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_BASTORE jvmInsArgLocalRef
  public static boolean jvmInsBastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsBastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_BASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_BASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_BASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_BIPUSH jvmInsArgUnsigned8Bytes
  public static boolean jvmInsBipush(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsBipush")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_BIPUSH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_BIPUSH, "<JVMInstruction>");
    r = consumeToken(b, INSN_BIPUSH);
    p = r; // pin = 1
    r = r && jvmInsArgUnsigned8Bytes(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_CALOAD
  public static boolean jvmInsCaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsCaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_CALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_CALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_CALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_CASTORE jvmInsArgLocalRef
  public static boolean jvmInsCastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsCastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_CASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_CASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_CASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_CHECKCAST typeDescriptor
  public static boolean jvmInsCheckcast(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsCheckcast")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_CHECKCAST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_CHECKCAST, "<JVMInstruction>");
    r = consumeToken(b, INSN_CHECKCAST);
    p = r; // pin = 1
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_D2F
  public static boolean jvmInsD2F(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsD2F")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_D2F)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_D_2_F, "<JVMInstruction>");
    r = consumeToken(b, INSN_D2F);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_D2I
  public static boolean jvmInsD2I(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsD2I")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_D2I)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_D_2_I, "<JVMInstruction>");
    r = consumeToken(b, INSN_D2I);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_D2L
  public static boolean jvmInsD2L(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsD2L")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_D2L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_D_2_L, "<JVMInstruction>");
    r = consumeToken(b, INSN_D2L);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DADD
  public static boolean jvmInsDadd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDadd")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DADD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DADD, "<JVMInstruction>");
    r = consumeToken(b, INSN_DADD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DALOAD
  public static boolean jvmInsDaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_DALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DASTORE jvmInsArgUnsigned8Bytes
  public static boolean jvmInsDastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_DASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgUnsigned8Bytes(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_DCMPG | INSN_DCMPL
  public static boolean jvmInsDcmpOP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDcmpOP")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DCMPG, INSN_DCMPL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DCMP_OP, "<JVMInstruction>");
    r = consumeToken(b, INSN_DCMPG);
    if (!r) r = consumeToken(b, INSN_DCMPL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DCONST_0 | INSN_DCONST_1
  public static boolean jvmInsDconstN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDconstN")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DCONST_0, INSN_DCONST_1)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DCONST_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_DCONST_0);
    if (!r) r = consumeToken(b, INSN_DCONST_1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DDIV
  public static boolean jvmInsDdiv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDdiv")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DDIV)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DDIV, "<JVMInstruction>");
    r = consumeToken(b, INSN_DDIV);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DLOAD jvmInsArgLocalRef
  public static boolean jvmInsDload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DLOAD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DLOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_DLOAD);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_DLOAD_0 | INSN_DLOAD_1 | INSN_DLOAD_2 | INSN_DLOAD_3
  public static boolean jvmInsDloadN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDloadN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DLOAD_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_DLOAD_0);
    if (!r) r = consumeToken(b, INSN_DLOAD_1);
    if (!r) r = consumeToken(b, INSN_DLOAD_2);
    if (!r) r = consumeToken(b, INSN_DLOAD_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DMUL
  public static boolean jvmInsDmul(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDmul")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DMUL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DMUL, "<JVMInstruction>");
    r = consumeToken(b, INSN_DMUL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DNEG
  public static boolean jvmInsDneg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDneg")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DNEG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DNEG, "<JVMInstruction>");
    r = consumeToken(b, INSN_DNEG);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DREM
  public static boolean jvmInsDrem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDrem")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DREM)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DREM, "<JVMInstruction>");
    r = consumeToken(b, INSN_DREM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DRETURN
  public static boolean jvmInsDreturn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDreturn")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DRETURN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DRETURN, "<JVMInstruction>");
    r = consumeToken(b, INSN_DRETURN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DSTORE jvmInsArgLocalRef
  public static boolean jvmInsDstore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDstore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DSTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DSTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_DSTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_DSTORE_0 | INSN_DSTORE_1 | INSN_DSTORE_2 | INSN_DSTORE_3
  public static boolean jvmInsDstoreN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDstoreN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DSTORE_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_DSTORE_0);
    if (!r) r = consumeToken(b, INSN_DSTORE_1);
    if (!r) r = consumeToken(b, INSN_DSTORE_2);
    if (!r) r = consumeToken(b, INSN_DSTORE_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DSUB
  public static boolean jvmInsDsub(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDsub")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DSUB)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DSUB, "<JVMInstruction>");
    r = consumeToken(b, INSN_DSUB);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DUP
  public static boolean jvmInsDup(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDup")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DUP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DUP, "<JVMInstruction>");
    r = consumeToken(b, INSN_DUP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DUP2
  public static boolean jvmInsDup2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDup2")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DUP2)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DUP_2, "<JVMInstruction>");
    r = consumeToken(b, INSN_DUP2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DUP2_X1
  public static boolean jvmInsDup2X1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDup2X1")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DUP2_X1)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DUP_2_X_1, "<JVMInstruction>");
    r = consumeToken(b, INSN_DUP2_X1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DUP2_X2
  public static boolean jvmInsDup2X2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDup2X2")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DUP2_X2)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DUP_2_X_2, "<JVMInstruction>");
    r = consumeToken(b, INSN_DUP2_X2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DUP_X1
  public static boolean jvmInsDupX1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDupX1")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DUP_X1)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DUP_X_1, "<JVMInstruction>");
    r = consumeToken(b, INSN_DUP_X1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_DUP_X2
  public static boolean jvmInsDupX2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsDupX2")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_DUP_X2)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_DUP_X_2, "<JVMInstruction>");
    r = consumeToken(b, INSN_DUP_X2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_F2D
  public static boolean jvmInsF2D(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsF2D")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_F2D)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_F_2_D, "<JVMInstruction>");
    r = consumeToken(b, INSN_F2D);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_F2I
  public static boolean jvmInsF2I(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsF2I")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_F2I)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_F_2_I, "<JVMInstruction>");
    r = consumeToken(b, INSN_F2I);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_F2L
  public static boolean jvmInsF2L(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsF2L")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_F2L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_F_2_L, "<JVMInstruction>");
    r = consumeToken(b, INSN_F2L);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FADD
  public static boolean jvmInsFadd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFadd")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FADD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FADD, "<JVMInstruction>");
    r = consumeToken(b, INSN_FADD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FALOAD
  public static boolean jvmInsFaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_FALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FASTORE jvmInsArgLocalRef
  public static boolean jvmInsFastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_FASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_FCMPG | INSN_FCMPL
  public static boolean jvmInsFcmpgOP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFcmpgOP")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FCMPG, INSN_FCMPL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FCMPG_OP, "<JVMInstruction>");
    r = consumeToken(b, INSN_FCMPG);
    if (!r) r = consumeToken(b, INSN_FCMPL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FCONST_0 | INSN_FCONST_1 | INSN_FCONST_2
  public static boolean jvmInsFconstN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFconstN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FCONST_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_FCONST_0);
    if (!r) r = consumeToken(b, INSN_FCONST_1);
    if (!r) r = consumeToken(b, INSN_FCONST_2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FDIV
  public static boolean jvmInsFdiv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFdiv")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FDIV)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FDIV, "<JVMInstruction>");
    r = consumeToken(b, INSN_FDIV);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FLOAD jvmInsArgLocalRef
  public static boolean jvmInsFload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FLOAD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FLOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_FLOAD);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_FLOAD_0 | INSN_FLOAD_1 | INSN_FLOAD_2 | INSN_FLOAD_3
  public static boolean jvmInsFloadN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFloadN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FLOAD_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_FLOAD_0);
    if (!r) r = consumeToken(b, INSN_FLOAD_1);
    if (!r) r = consumeToken(b, INSN_FLOAD_2);
    if (!r) r = consumeToken(b, INSN_FLOAD_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FMUL
  public static boolean jvmInsFmul(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFmul")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FMUL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FMUL, "<JVMInstruction>");
    r = consumeToken(b, INSN_FMUL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FNEG
  public static boolean jvmInsFneg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFneg")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FNEG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FNEG, "<JVMInstruction>");
    r = consumeToken(b, INSN_FNEG);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FREM
  public static boolean jvmInsFrem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFrem")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FREM)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FREM, "<JVMInstruction>");
    r = consumeToken(b, INSN_FREM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FRETURN
  public static boolean jvmInsFreturn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFreturn")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FRETURN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FRETURN, "<JVMInstruction>");
    r = consumeToken(b, INSN_FRETURN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FSTORE
  public static boolean jvmInsFstore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFstore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FSTORE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FSTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_FSTORE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FSTORE_0 | INSN_FSTORE_1 | INSN_FSTORE_2 | INSN_FSTORE_3
  public static boolean jvmInsFstoreN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFstoreN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FSTORE_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_FSTORE_0);
    if (!r) r = consumeToken(b, INSN_FSTORE_1);
    if (!r) r = consumeToken(b, INSN_FSTORE_2);
    if (!r) r = consumeToken(b, INSN_FSTORE_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_FSUB
  public static boolean jvmInsFsub(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsFsub")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_FSUB)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_FSUB, "<JVMInstruction>");
    r = consumeToken(b, INSN_FSUB);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_GETFIELD jvmInsArgFieldRef
  public static boolean jvmInsGetfield(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsGetfield")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_GETFIELD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_GETFIELD, "<JVMInstruction>");
    r = consumeToken(b, INSN_GETFIELD);
    p = r; // pin = 1
    r = r && jvmInsArgFieldRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_GETSTATIC jvmInsArgFieldRef
  public static boolean jvmInsGetstatic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsGetstatic")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_GETSTATIC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_GETSTATIC, "<JVMInstruction>");
    r = consumeToken(b, INSN_GETSTATIC);
    p = r; // pin = 1
    r = r && jvmInsArgFieldRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_GOTO labelName
  public static boolean jvmInsGoto(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsGoto")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_GOTO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_GOTO, "<JVMInstruction>");
    r = consumeToken(b, INSN_GOTO);
    p = r; // pin = 1
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_GOTO_W labelName
  public static boolean jvmInsGotoW(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsGotoW")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_GOTO_W)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_GOTO_W, "<JVMInstruction>");
    r = consumeToken(b, INSN_GOTO_W);
    p = r; // pin = 1
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_I2B
  public static boolean jvmInsI2B(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsI2B")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_I2B)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_I_2_B, "<JVMInstruction>");
    r = consumeToken(b, INSN_I2B);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_I2C
  public static boolean jvmInsI2C(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsI2C")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_I2C)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_I_2_C, "<JVMInstruction>");
    r = consumeToken(b, INSN_I2C);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_I2D
  public static boolean jvmInsI2D(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsI2D")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_I2D)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_I_2_D, "<JVMInstruction>");
    r = consumeToken(b, INSN_I2D);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_I2F
  public static boolean jvmInsI2F(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsI2F")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_I2F)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_I_2_F, "<JVMInstruction>");
    r = consumeToken(b, INSN_I2F);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_I2L
  public static boolean jvmInsI2L(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsI2L")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_I2L)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_I_2_L, "<JVMInstruction>");
    r = consumeToken(b, INSN_I2L);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_I2S
  public static boolean jvmInsI2S(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsI2S")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_I2S)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_I_2_S, "<JVMInstruction>");
    r = consumeToken(b, INSN_I2S);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IADD
  public static boolean jvmInsIadd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIadd")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IADD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IADD, "<JVMInstruction>");
    r = consumeToken(b, INSN_IADD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IALOAD
  public static boolean jvmInsIaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_IALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IAND
  public static boolean jvmInsIand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIand")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IAND)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IAND, "<JVMInstruction>");
    r = consumeToken(b, INSN_IAND);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IASTORE jvmInsArgUnsigned8Bytes
  public static boolean jvmInsIastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_IASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgUnsigned8Bytes(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ICONST_M1 | INSN_ICONST_0 | INSN_ICONST_1 | INSN_ICONST_2 | INSN_ICONST_3 | INSN_ICONST_4
  //                   | INSN_ICONST_5 | INSN_ICONST_6 | INSN_ICONST_7 | INSN_ICONST_8
  public static boolean jvmInsIconstN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIconstN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ICONST_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_ICONST_M1);
    if (!r) r = consumeToken(b, INSN_ICONST_0);
    if (!r) r = consumeToken(b, INSN_ICONST_1);
    if (!r) r = consumeToken(b, INSN_ICONST_2);
    if (!r) r = consumeToken(b, INSN_ICONST_3);
    if (!r) r = consumeToken(b, INSN_ICONST_4);
    if (!r) r = consumeToken(b, INSN_ICONST_5);
    if (!r) r = consumeToken(b, INSN_ICONST_6);
    if (!r) r = consumeToken(b, INSN_ICONST_7);
    if (!r) r = consumeToken(b, INSN_ICONST_8);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IDIV
  public static boolean jvmInsIdiv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIdiv")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IDIV)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IDIV, "<JVMInstruction>");
    r = consumeToken(b, INSN_IDIV);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IF_ACMPEQ labelName
  //                    | INSN_IF_ACMPNE labelName
  public static boolean jvmInsIfAcmpOP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfAcmpOP")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IF_ACMPEQ, INSN_IF_ACMPNE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IF_ACMP_OP, "<JVMInstruction>");
    r = jvmInsIfAcmpOP_0(b, l + 1);
    if (!r) r = jvmInsIfAcmpOP_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // INSN_IF_ACMPEQ labelName
  private static boolean jvmInsIfAcmpOP_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfAcmpOP_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ACMPEQ);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IF_ACMPNE labelName
  private static boolean jvmInsIfAcmpOP_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfAcmpOP_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ACMPNE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INSN_IF_ICMPEQ labelName
  //                    | INSN_IF_ICMPNE labelName
  //                    | INSN_IF_ICMPLT labelName
  //                    | INSN_IF_ICMPGE labelName
  //                    | INSN_IF_ICMPGT labelName
  //                    | INSN_IF_ICMPLE labelName
  public static boolean jvmInsIfIcmpOP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IF_ICMP_OP, "<JVMInstruction>");
    r = jvmInsIfIcmpOP_0(b, l + 1);
    if (!r) r = jvmInsIfIcmpOP_1(b, l + 1);
    if (!r) r = jvmInsIfIcmpOP_2(b, l + 1);
    if (!r) r = jvmInsIfIcmpOP_3(b, l + 1);
    if (!r) r = jvmInsIfIcmpOP_4(b, l + 1);
    if (!r) r = jvmInsIfIcmpOP_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // INSN_IF_ICMPEQ labelName
  private static boolean jvmInsIfIcmpOP_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ICMPEQ);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IF_ICMPNE labelName
  private static boolean jvmInsIfIcmpOP_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ICMPNE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IF_ICMPLT labelName
  private static boolean jvmInsIfIcmpOP_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ICMPLT);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IF_ICMPGE labelName
  private static boolean jvmInsIfIcmpOP_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ICMPGE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IF_ICMPGT labelName
  private static boolean jvmInsIfIcmpOP_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ICMPGT);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IF_ICMPLE labelName
  private static boolean jvmInsIfIcmpOP_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfIcmpOP_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IF_ICMPLE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INSN_IFNONNULL labelName
  public static boolean jvmInsIfNonnull(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfNonnull")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IFNONNULL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IF_NONNULL, "<JVMInstruction>");
    r = consumeToken(b, INSN_IFNONNULL);
    p = r; // pin = 1
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_IFNULL labelName
  public static boolean jvmInsIfNull(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfNull")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IFNULL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IF_NULL, "<JVMInstruction>");
    r = consumeToken(b, INSN_IFNULL);
    p = r; // pin = 1
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_IFEQ labelName
  //                | INSN_IFNE labelName
  //                | INSN_IFLT labelName
  //                | INSN_IFGE labelName
  //                | INSN_IFGT labelName
  //                | INSN_IFLE labelName
  public static boolean jvmInsIfOP(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IF_OP, "<JVMInstruction>");
    r = jvmInsIfOP_0(b, l + 1);
    if (!r) r = jvmInsIfOP_1(b, l + 1);
    if (!r) r = jvmInsIfOP_2(b, l + 1);
    if (!r) r = jvmInsIfOP_3(b, l + 1);
    if (!r) r = jvmInsIfOP_4(b, l + 1);
    if (!r) r = jvmInsIfOP_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // INSN_IFEQ labelName
  private static boolean jvmInsIfOP_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IFEQ);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IFNE labelName
  private static boolean jvmInsIfOP_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IFNE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IFLT labelName
  private static boolean jvmInsIfOP_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IFLT);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IFGE labelName
  private static boolean jvmInsIfOP_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IFGE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IFGT labelName
  private static boolean jvmInsIfOP_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IFGT);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // INSN_IFLE labelName
  private static boolean jvmInsIfOP_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIfOP_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INSN_IFLE);
    r = r && labelName(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INSN_IINC jvmInsArgLocalRef COMMA jvmInsArgUnsigned8Bytes
  public static boolean jvmInsIinc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIinc")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IINC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IINC, "<JVMInstruction>");
    r = consumeToken(b, INSN_IINC);
    p = r; // pin = 1
    r = r && report_error_(b, jvmInsArgLocalRef(b, l + 1));
    r = p && report_error_(b, consumeToken(b, COMMA)) && r;
    r = p && jvmInsArgUnsigned8Bytes(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ILOAD jvmInsArgLocalRef
  public static boolean jvmInsIload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ILOAD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ILOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_ILOAD);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ILOAD_0 | INSN_ILOAD_1 | INSN_ILOAD_2 | INSN_ILOAD_3
  public static boolean jvmInsIloadN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIloadN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ILOAD_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_ILOAD_0);
    if (!r) r = consumeToken(b, INSN_ILOAD_1);
    if (!r) r = consumeToken(b, INSN_ILOAD_2);
    if (!r) r = consumeToken(b, INSN_ILOAD_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IMUL
  public static boolean jvmInsImul(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsImul")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IMUL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IMUL, "<JVMInstruction>");
    r = consumeToken(b, INSN_IMUL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_INEG
  public static boolean jvmInsIneg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIneg")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INEG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INEG, "<JVMInstruction>");
    r = consumeToken(b, INSN_INEG);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_INSTANCEOF typeDescriptor
  public static boolean jvmInsInstanceof(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInstanceof")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INSTANCEOF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INSTANCEOF, "<JVMInstruction>");
    r = consumeToken(b, INSN_INSTANCEOF);
    p = r; // pin = 1
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_INVOKEDYNAMIC jvmInsArgMethodRef jvmInsArgMethodRef jvmInsArgScalarType*
  public static boolean jvmInsInvokedynamic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInvokedynamic")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INVOKEDYNAMIC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INVOKEDYNAMIC, "<JVMInstruction>");
    r = consumeToken(b, INSN_INVOKEDYNAMIC);
    p = r; // pin = 1
    r = r && report_error_(b, jvmInsArgMethodRef(b, l + 1));
    r = p && report_error_(b, jvmInsArgMethodRef(b, l + 1)) && r;
    r = p && jvmInsInvokedynamic_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // jvmInsArgScalarType*
  private static boolean jvmInsInvokedynamic_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInvokedynamic_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!jvmInsArgScalarType(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "jvmInsInvokedynamic_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // INSN_INVOKEINTERFACE jvmInsArgMethodRef jvmInsArgUnsigned8Bytes
  public static boolean jvmInsInvokeinterface(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInvokeinterface")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INVOKEINTERFACE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INVOKEINTERFACE, "<JVMInstruction>");
    r = consumeToken(b, INSN_INVOKEINTERFACE);
    p = r; // pin = 1
    r = r && report_error_(b, jvmInsArgMethodRef(b, l + 1));
    r = p && jvmInsArgUnsigned8Bytes(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_INVOKESPECIAL jvmInsArgMethodSpecialRef
  public static boolean jvmInsInvokespecial(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInvokespecial")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INVOKESPECIAL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INVOKESPECIAL, "<JVMInstruction>");
    r = consumeToken(b, INSN_INVOKESPECIAL);
    p = r; // pin = 1
    r = r && jvmInsArgMethodSpecialRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_INVOKESTATIC jvmInsArgMethodRef
  public static boolean jvmInsInvokestatic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInvokestatic")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INVOKESTATIC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INVOKESTATIC, "<JVMInstruction>");
    r = consumeToken(b, INSN_INVOKESTATIC);
    p = r; // pin = 1
    r = r && jvmInsArgMethodRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_INVOKEVIRTUAL jvmInsArgMethodRef
  public static boolean jvmInsInvokevirtual(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsInvokevirtual")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_INVOKEVIRTUAL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_INVOKEVIRTUAL, "<JVMInstruction>");
    r = consumeToken(b, INSN_INVOKEVIRTUAL);
    p = r; // pin = 1
    r = r && jvmInsArgMethodRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_IOR
  public static boolean jvmInsIor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIor")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IOR, "<JVMInstruction>");
    r = consumeToken(b, INSN_IOR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IREM
  public static boolean jvmInsIrem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIrem")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IREM)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IREM, "<JVMInstruction>");
    r = consumeToken(b, INSN_IREM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IRETURN
  public static boolean jvmInsIreturn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIreturn")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IRETURN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IRETURN, "<JVMInstruction>");
    r = consumeToken(b, INSN_IRETURN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ISHL
  public static boolean jvmInsIshl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIshl")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ISHL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ISHL, "<JVMInstruction>");
    r = consumeToken(b, INSN_ISHL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ISHR
  public static boolean jvmInsIshr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIshr")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ISHR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ISHR, "<JVMInstruction>");
    r = consumeToken(b, INSN_ISHR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ISTORE jvmInsArgLocalRef
  public static boolean jvmInsIstore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIstore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ISTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ISTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_ISTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_ISTORE_0 | INSN_ISTORE_1 | INSN_ISTORE_2 | INSN_ISTORE_3
  public static boolean jvmInsIstoreN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIstoreN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ISTORE_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_ISTORE_0);
    if (!r) r = consumeToken(b, INSN_ISTORE_1);
    if (!r) r = consumeToken(b, INSN_ISTORE_2);
    if (!r) r = consumeToken(b, INSN_ISTORE_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_ISUB
  public static boolean jvmInsIsub(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIsub")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_ISUB)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_ISUB, "<JVMInstruction>");
    r = consumeToken(b, INSN_ISUB);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IUSHR
  public static boolean jvmInsIushr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIushr")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IUSHR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IUSHR, "<JVMInstruction>");
    r = consumeToken(b, INSN_IUSHR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_IXOR
  public static boolean jvmInsIxor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsIxor")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_IXOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_IXOR, "<JVMInstruction>");
    r = consumeToken(b, INSN_IXOR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_JSR labelName
  public static boolean jvmInsJsr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsJsr")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_JSR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_JSR, "<JVMInstruction>");
    r = consumeToken(b, INSN_JSR);
    p = r; // pin = 1
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_JSR_W labelName
  public static boolean jvmInsJsrW(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsJsrW")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_JSR_W)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_JSR_W, "<JVMInstruction>");
    r = consumeToken(b, INSN_JSR_W);
    p = r; // pin = 1
    r = r && labelName(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_L2D
  public static boolean jvmInsL2D(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsL2D")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_L2D)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_L_2_D, "<JVMInstruction>");
    r = consumeToken(b, INSN_L2D);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_L2F
  public static boolean jvmInsL2F(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsL2F")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_L2F)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_L_2_F, "<JVMInstruction>");
    r = consumeToken(b, INSN_L2F);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_L2I
  public static boolean jvmInsL2I(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsL2I")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_L2I)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_L_2_I, "<JVMInstruction>");
    r = consumeToken(b, INSN_L2I);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LADD
  public static boolean jvmInsLadd(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLadd")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LADD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LADD, "<JVMInstruction>");
    r = consumeToken(b, INSN_LADD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LALOAD
  public static boolean jvmInsLaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_LALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LAND
  public static boolean jvmInsLand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLand")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LAND)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LAND, "<JVMInstruction>");
    r = consumeToken(b, INSN_LAND);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LASTORE jvmInsArgLocalRef
  public static boolean jvmInsLastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_LASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LCMP
  public static boolean jvmInsLcmp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLcmp")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LCMP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LCMP, "<JVMInstruction>");
    r = consumeToken(b, INSN_LCMP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LCONST_0 | INSN_LCONST_1
  public static boolean jvmInsLconstN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLconstN")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LCONST_0, INSN_LCONST_1)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LCONST_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_LCONST_0);
    if (!r) r = consumeToken(b, INSN_LCONST_1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LDC jvmInsArgScalarType
  public static boolean jvmInsLdc(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLdc")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LDC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LDC, "<JVMInstruction>");
    r = consumeToken(b, INSN_LDC);
    p = r; // pin = 1
    r = r && jvmInsArgScalarType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LDC2_W jvmInsArgScalarType
  public static boolean jvmInsLdc2W(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLdc2W")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LDC2_W)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LDC_2_W, "<JVMInstruction>");
    r = consumeToken(b, INSN_LDC2_W);
    p = r; // pin = 1
    r = r && jvmInsArgScalarType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LDC_W jvmInsArgScalarType
  public static boolean jvmInsLdcW(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLdcW")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LDC_W)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LDC_W, "<JVMInstruction>");
    r = consumeToken(b, INSN_LDC_W);
    p = r; // pin = 1
    r = r && jvmInsArgScalarType(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LDIV
  public static boolean jvmInsLdiv(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLdiv")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LDIV)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LDIV, "<JVMInstruction>");
    r = consumeToken(b, INSN_LDIV);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LLOAD jvmInsArgLocalRef
  public static boolean jvmInsLload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LLOAD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LLOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_LLOAD);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LLOAD_0 | INSN_LLOAD_1 | INSN_LLOAD_2 | INSN_LLOAD_3
  public static boolean jvmInsLloadN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLloadN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LLOAD_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_LLOAD_0);
    if (!r) r = consumeToken(b, INSN_LLOAD_1);
    if (!r) r = consumeToken(b, INSN_LLOAD_2);
    if (!r) r = consumeToken(b, INSN_LLOAD_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LMUL
  public static boolean jvmInsLmul(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLmul")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LMUL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LMUL, "<JVMInstruction>");
    r = consumeToken(b, INSN_LMUL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LNEG
  public static boolean jvmInsLneg(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLneg")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LNEG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LNEG, "<JVMInstruction>");
    r = consumeToken(b, INSN_LNEG);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LOOKUPSWITCH jvmInsArgLookupSwitch
  public static boolean jvmInsLookupswitch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLookupswitch")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LOOKUPSWITCH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LOOKUPSWITCH, "<JVMInstruction>");
    r = consumeToken(b, INSN_LOOKUPSWITCH);
    p = r; // pin = 1
    r = r && jvmInsArgLookupSwitch(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LOR
  public static boolean jvmInsLor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLor")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LOR, "<JVMInstruction>");
    r = consumeToken(b, INSN_LOR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LREM
  public static boolean jvmInsLrem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLrem")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LREM)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LREM, "<JVMInstruction>");
    r = consumeToken(b, INSN_LREM);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LRETURN
  public static boolean jvmInsLreturn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLreturn")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LRETURN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LRETURN, "<JVMInstruction>");
    r = consumeToken(b, INSN_LRETURN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LSHL
  public static boolean jvmInsLshl(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLshl")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LSHL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LSHL, "<JVMInstruction>");
    r = consumeToken(b, INSN_LSHL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LSHR
  public static boolean jvmInsLshr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLshr")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LSHR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LSHR, "<JVMInstruction>");
    r = consumeToken(b, INSN_LSHR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LSTORE jvmInsArgLocalRef
  public static boolean jvmInsLstore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLstore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LSTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LSTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_LSTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_LSTORE_0 | INSN_LSTORE_1 | INSN_LSTORE_2 | INSN_LSTORE_3
  public static boolean jvmInsLstoreN(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLstoreN")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LSTORE_N, "<JVMInstruction>");
    r = consumeToken(b, INSN_LSTORE_0);
    if (!r) r = consumeToken(b, INSN_LSTORE_1);
    if (!r) r = consumeToken(b, INSN_LSTORE_2);
    if (!r) r = consumeToken(b, INSN_LSTORE_3);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LSUB
  public static boolean jvmInsLsub(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLsub")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LSUB)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LSUB, "<JVMInstruction>");
    r = consumeToken(b, INSN_LSUB);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LUSHR
  public static boolean jvmInsLushr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLushr")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LUSHR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LUSHR, "<JVMInstruction>");
    r = consumeToken(b, INSN_LUSHR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_LXOR
  public static boolean jvmInsLxor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsLxor")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_LXOR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_LXOR, "<JVMInstruction>");
    r = consumeToken(b, INSN_LXOR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_MONITORENTER
  public static boolean jvmInsMonitorenter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsMonitorenter")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_MONITORENTER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_MONITORENTER, "<JVMInstruction>");
    r = consumeToken(b, INSN_MONITORENTER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_MONITOREXIT
  public static boolean jvmInsMonitorexit(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsMonitorexit")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_MONITOREXIT)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_MONITOREXIT, "<JVMInstruction>");
    r = consumeToken(b, INSN_MONITOREXIT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_MULTIANEWARRAY typeDescriptor jvmInsArgUnsigned8Bytes
  public static boolean jvmInsMultianewarray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsMultianewarray")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_MULTIANEWARRAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_MULTIANEWARRAY, "<JVMInstruction>");
    r = consumeToken(b, INSN_MULTIANEWARRAY);
    p = r; // pin = 1
    r = r && report_error_(b, typeDescriptor(b, l + 1));
    r = p && jvmInsArgUnsigned8Bytes(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_NEW typeDescriptor
  public static boolean jvmInsNew(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsNew")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_NEW)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_NEW, "<JVMInstruction>");
    r = consumeToken(b, INSN_NEW);
    p = r; // pin = 1
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_NEWARRAY typeDescriptor
  public static boolean jvmInsNewarray(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsNewarray")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_NEWARRAY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_NEWARRAY, "<JVMInstruction>");
    r = consumeToken(b, INSN_NEWARRAY);
    p = r; // pin = 1
    r = r && typeDescriptor(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_NOP
  public static boolean jvmInsNop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsNop")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_NOP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_NOP, "<JVMInstruction>");
    r = consumeToken(b, INSN_NOP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_POP
  public static boolean jvmInsPop(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsPop")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_POP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_POP, "<JVMInstruction>");
    r = consumeToken(b, INSN_POP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_POP2
  public static boolean jvmInsPop2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsPop2")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_POP2)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_POP_2, "<JVMInstruction>");
    r = consumeToken(b, INSN_POP2);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_PUTFIELD jvmInsArgFieldRef
  public static boolean jvmInsPutfield(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsPutfield")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_PUTFIELD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_PUTFIELD, "<JVMInstruction>");
    r = consumeToken(b, INSN_PUTFIELD);
    p = r; // pin = 1
    r = r && jvmInsArgFieldRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_PUTSTATIC jvmInsArgFieldRef
  public static boolean jvmInsPutstatic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsPutstatic")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_PUTSTATIC)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_PUTSTATIC, "<JVMInstruction>");
    r = consumeToken(b, INSN_PUTSTATIC);
    p = r; // pin = 1
    r = r && jvmInsArgFieldRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_RET jvmInsArgLocalRef
  public static boolean jvmInsRet(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsRet")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_RET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_RET, "<JVMInstruction>");
    r = consumeToken(b, INSN_RET);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_RETURN
  public static boolean jvmInsReturn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsReturn")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_RETURN, "<JVMInstruction>");
    r = consumeToken(b, INSN_RETURN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_SALOAD
  public static boolean jvmInsSaload(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsSaload")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_SALOAD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_SALOAD, "<JVMInstruction>");
    r = consumeToken(b, INSN_SALOAD);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_SASTORE jvmInsArgLocalRef
  public static boolean jvmInsSastore(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsSastore")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_SASTORE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_SASTORE, "<JVMInstruction>");
    r = consumeToken(b, INSN_SASTORE);
    p = r; // pin = 1
    r = r && jvmInsArgLocalRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_SIPUSH jvmInsArgUnsigned8Bytes
  public static boolean jvmInsSipush(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsSipush")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_SIPUSH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_SIPUSH, "<JVMInstruction>");
    r = consumeToken(b, INSN_SIPUSH);
    p = r; // pin = 1
    r = r && jvmInsArgUnsigned8Bytes(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_SWAP
  public static boolean jvmInsSwap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsSwap")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_SWAP)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_SWAP, "<JVMInstruction>");
    r = consumeToken(b, INSN_SWAP);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // INSN_TABLESWITCH jvmInsArgTableSwitch
  public static boolean jvmInsTableswitch(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsTableswitch")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_TABLESWITCH)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_TABLESWITCH, "<JVMInstruction>");
    r = consumeToken(b, INSN_TABLESWITCH);
    p = r; // pin = 1
    r = r && jvmInsArgTableSwitch(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // INSN_WIDE (
  //                  jvmInsIload | jvmInsFload | jvmInsAload | jvmInsLload | jvmInsDload | jvmInsIstore | jvmInsFstore
  //                  | jvmInsAstore | jvmInsDstore | jvmInsRet
  //                ) jvmInsArgUnsigned8Bytes
  public static boolean jvmInsWide(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsWide")) return false;
    if (!nextTokenIs(b, "<JVMInstruction>", INSN_WIDE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JVM_INS_WIDE, "<JVMInstruction>");
    r = consumeToken(b, INSN_WIDE);
    p = r; // pin = 1
    r = r && report_error_(b, jvmInsWide_1(b, l + 1));
    r = p && jvmInsArgUnsigned8Bytes(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // jvmInsIload | jvmInsFload | jvmInsAload | jvmInsLload | jvmInsDload | jvmInsIstore | jvmInsFstore
  //                  | jvmInsAstore | jvmInsDstore | jvmInsRet
  private static boolean jvmInsWide_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "jvmInsWide_1")) return false;
    boolean r;
    r = jvmInsIload(b, l + 1);
    if (!r) r = jvmInsFload(b, l + 1);
    if (!r) r = jvmInsAload(b, l + 1);
    if (!r) r = jvmInsLload(b, l + 1);
    if (!r) r = jvmInsDload(b, l + 1);
    if (!r) r = jvmInsIstore(b, l + 1);
    if (!r) r = jvmInsFstore(b, l + 1);
    if (!r) r = jvmInsAstore(b, l + 1);
    if (!r) r = jvmInsDstore(b, l + 1);
    if (!r) r = jvmInsRet(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // labelName COLON
  public static boolean label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "label")) return false;
    if (!nextTokenIs(b, "<label>", ID, INSN_ARG_UNSIG_8BYTES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL, "<label>");
    r = labelName(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // id | jvmInsArgUnsigned8Bytes
  public static boolean labelName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "labelName")) return false;
    if (!nextTokenIs(b, "<label name>", ID, INSN_ARG_UNSIG_8BYTES)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LABEL_NAME, "<label name>");
    r = consumeToken(b, ID);
    if (!r) r = jvmInsArgUnsigned8Bytes(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBR methodBodyItem RBR
  static boolean methodBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodBody")) return false;
    if (!nextTokenIs(b, LBR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, LBR);
    p = r; // pin = 1
    r = r && report_error_(b, methodBodyItem(b, l + 1));
    r = p && consumeToken(b, RBR) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // (instruction | label)*
  public static boolean methodBodyItem(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodBodyItem")) return false;
    Marker m = enter_section_(b, l, _NONE_, METHOD_BODY_ITEM, "<method body item>");
    while (true) {
      int c = current_position_(b);
      if (!methodBodyItem_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "methodBodyItem", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  // instruction | label
  private static boolean methodBodyItem_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodBodyItem_0")) return false;
    boolean r;
    r = instruction(b, l + 1);
    if (!r) r = label(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // accModMethod methodName methodDescriptor methodBody
  public static boolean methodDefinition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDefinition")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DEFINITION, "<method definition>");
    r = accModMethod(b, l + 1);
    r = r && methodName(b, l + 1);
    r = r && methodDescriptor(b, l + 1);
    p = r; // pin = 3
    r = r && methodBody(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // LP methodDescriptorArgs RP typeDescriptor
  public static boolean methodDescriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDescriptor")) return false;
    if (!nextTokenIs(b, LP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DESCRIPTOR, null);
    r = consumeToken(b, LP);
    p = r; // pin = 1
    r = r && report_error_(b, methodDescriptorArgs(b, l + 1));
    r = p && report_error_(b, consumeToken(b, RP)) && r;
    r = p && typeDescriptor(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // typeDescriptor*
  public static boolean methodDescriptorArgs(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodDescriptorArgs")) return false;
    Marker m = enter_section_(b, l, _NONE_, METHOD_DESCRIPTOR_ARGS, "<method descriptor args>");
    while (true) {
      int c = current_position_(b);
      if (!typeDescriptor(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "methodDescriptorArgs", c)) break;
    }
    exit_section_(b, l, m, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // id | KWD_MNAME_INIT | KWD_MNAME_CLINIT
  static boolean methodName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "methodName")) return false;
    boolean r;
    r = consumeToken(b, ID);
    if (!r) r = consumeToken(b, KWD_MNAME_INIT);
    if (!r) r = consumeToken(b, KWD_MNAME_CLINIT);
    return r;
  }

  /* ********************************************************** */
  // classDefinition?
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    classDefinition(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBK? (typeDescriptorPrimitive | TYPE_DESC_OBJECT)
  public static boolean typeDescriptor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDescriptor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_DESCRIPTOR, "<type descriptor>");
    r = typeDescriptor_0(b, l + 1);
    r = r && typeDescriptor_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LBK?
  private static boolean typeDescriptor_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDescriptor_0")) return false;
    consumeToken(b, LBK);
    return true;
  }

  // typeDescriptorPrimitive | TYPE_DESC_OBJECT
  private static boolean typeDescriptor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDescriptor_1")) return false;
    boolean r;
    r = typeDescriptorPrimitive(b, l + 1);
    if (!r) r = consumeToken(b, TYPE_DESC_OBJECT);
    return r;
  }

  /* ********************************************************** */
  // TYPE_DESC_BYTE | TYPE_DESC_CHAR | TYPE_DESC_DOUBLE | TYPE_DESC_FLOAT | TYPE_DESC_INT
  //                    | TYPE_DESC_LONG | TYPE_DESC_SHORT | TYPE_DESC_VOID | TYPE_DESC_BOOLEAN
  public static boolean typeDescriptorPrimitive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDescriptorPrimitive")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_DESCRIPTOR_PRIMITIVE, "<type descriptor primitive>");
    r = consumeToken(b, TYPE_DESC_BYTE);
    if (!r) r = consumeToken(b, TYPE_DESC_CHAR);
    if (!r) r = consumeToken(b, TYPE_DESC_DOUBLE);
    if (!r) r = consumeToken(b, TYPE_DESC_FLOAT);
    if (!r) r = consumeToken(b, TYPE_DESC_INT);
    if (!r) r = consumeToken(b, TYPE_DESC_LONG);
    if (!r) r = consumeToken(b, TYPE_DESC_SHORT);
    if (!r) r = consumeToken(b, TYPE_DESC_VOID);
    if (!r) r = consumeToken(b, TYPE_DESC_BOOLEAN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
