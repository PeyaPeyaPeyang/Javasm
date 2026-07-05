package tokyo.peya.javasm.intellij.inspection.inspections;

import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.inspection.AbstractJALInspection;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitor;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.langjal.compiler.jvm.EOpcodes;
import tokyo.peya.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.langjal.compiler.jvm.TypeDescriptor;

public class JALMethodReturnTypeInspection extends AbstractJALInspection {
    public JALMethodReturnTypeInspection() {
        super("JALMethodReturnType");
    }

    private static void checkInstruction(@NotNull ProblemsHolder holder, @NotNull InstructionNode node) {
        int opcode = node.getOpcode();
        TypeDescriptor actualReturnType = switch (opcode) {
            case EOpcodes.IRETURN -> TypeDescriptor.INTEGER;
            case EOpcodes.LRETURN -> TypeDescriptor.LONG;
            case EOpcodes.FRETURN -> TypeDescriptor.FLOAT;
            case EOpcodes.DRETURN -> TypeDescriptor.DOUBLE;
            case EOpcodes.ARETURN -> TypeDescriptor.OBJECT;
            case EOpcodes.RETURN -> TypeDescriptor.VOID;
            default -> null;
        };
        if (actualReturnType == null)
            return; // return 命令じゃないとき

        MethodDefinitionNode method = PsiTreeUtil.getParentOfType(node, MethodDefinitionNode.class);
        if (method == null)
            return;
        MethodDescriptor methodDesc = method.getMethodDescriptor();
        TypeDescriptor expectedReturnType = methodDesc.getReturnType();

        if (!isCompatibleReturnType(expectedReturnType, actualReturnType))
            registerTypeMismatchProblem(holder, node, expectedReturnType, actualReturnType);
    }

    private static boolean isCompatibleReturnType(
            @NotNull TypeDescriptor expectedType,
            @NotNull TypeDescriptor actualType
    ) {
        // boolean/byte/char/short/int は，すべて ireturn
        if (actualType.equals(TypeDescriptor.INTEGER))
            return isIntReturnType(expectedType);

        // 参照型は areturn
        if (actualType.equals(TypeDescriptor.OBJECT))
            return !expectedType.getBaseType().isPrimitive();

        return expectedType.equals(actualType);
    }

    private static boolean isIntReturnType(@NotNull TypeDescriptor type) {
        return switch (type.getBaseType().getDescriptor()) {
            case "Z", "B", "C", "S", "I" -> true;
            default -> false;
        };
    }

    private static void registerTypeMismatchProblem(
            @NotNull ProblemsHolder holder,
            @NotNull InstructionNode node,
            @NotNull TypeDescriptor expectedType,
            @NotNull TypeDescriptor actualType
    ) {
        String message = String.format(
                "Expected return type '%s', but found '%s'.",
                expectedType,
                actualType
        );
        holder.registerProblem(
                node,
                message,
                ProblemHighlightType.GENERIC_ERROR_OR_WARNING
        );
    }

    @Override
    protected @NotNull JALPsiElementVisitor buildJALVisitor(@NotNull ProblemsHolder holder, boolean isOnTheFly,
                                                            @NotNull LocalInspectionToolSession session) {
        return new JALPsiElementVisitor() {
            @Override
            protected void visitInstruction(@NotNull InstructionNode node) {
                checkInstruction(holder, node);
            }
        };
    }
}
