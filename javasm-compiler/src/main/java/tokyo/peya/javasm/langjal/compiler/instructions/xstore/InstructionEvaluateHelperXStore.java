package tokyo.peya.javasm.langjal.compiler.instructions.xstore;

import lombok.experimental.UtilityClass;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.tree.VarInsnNode;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.LabelInfo;
import tokyo.peya.javasm.langjal.compiler.LocalVariableInfo;
import tokyo.peya.javasm.langjal.compiler.jvm.TypeDescriptor;

import java.util.Objects;

@UtilityClass
public class InstructionEvaluateHelperXStore
{
    public static @NotNull EvaluatedInstruction evaluate(int opcode,
                                                         @NotNull JALMethodEvaluator evaluator,
                                                         @NotNull JALParser.JvmInsArgLocalRefContext localRef,
                                                         @NotNull JALParser.LocalInstigationContext instigation,
                                                         @NotNull String type,
                                                         @NotNull String callerInsn,
                                                         @Nullable TerminalNode wide)
    {
        LocalVariableInfo registeredLocal = evaluator.resolveSafe(localRef);
        if (registeredLocal == null)
            registeredLocal = registerNewLocal(evaluator, localRef, type, instigation);

        int idx = registeredLocal.index();
        boolean isWide = wide != null;
        if (idx >= 0xFF && !isWide)
            throw new IllegalArgumentException(String.format(
                    "Local variable index %d is too large for %s instruction. Use wide variant with.",
                    idx, callerInsn
            ));

        int size = isWide ? 4: 2;
        VarInsnNode insn = new VarInsnNode(opcode, idx);
        return EvaluatedInstruction.of(insn, size);
    }

    public static @NotNull EvaluatedInstruction evaluateN(int opcode, int idx,
                                                          @NotNull JALMethodEvaluator evaluator,
                                                          @NotNull String type,
                                                          @Nullable JALParser.LocalInstigationContext instigation)
    {
        LocalVariableInfo registeredLocal = evaluator.resolveLocalSafe(idx);
        if (registeredLocal == null)
            registeredLocal = registerNewLocal(evaluator, idx, type, instigation);

        // 0~3 が確定だから， wide は不要
        VarInsnNode insn = new VarInsnNode(opcode, registeredLocal.index());
        return EvaluatedInstruction.of(insn);
    }

    private static LocalVariableInfo registerNewLocal(@NotNull JALMethodEvaluator evaluator,
                                                      int idx,
                                                      @NotNull String typeName,
                                                      @Nullable JALParser.LocalInstigationContext instigation)
    {
        String localName = pickLocalName(evaluator, null, idx, instigation);
        LabelInfo endLabel = resolveEndLabel(evaluator, instigation);
        TypeDescriptor localType = TypeDescriptor.parse(typeName);

        return evaluator.registerLocal(idx, localType, localName, endLabel);
    }

    private static LocalVariableInfo registerNewLocal(@NotNull JALMethodEvaluator evaluator,
                                                      @NotNull JALParser.JvmInsArgLocalRefContext localRef,
                                                      @NotNull String type,
                                                      @Nullable JALParser.LocalInstigationContext instigation)
    {
        String localName = pickLocalName(evaluator, localRef, 0, instigation);
        LabelInfo endLabel = resolveEndLabel(evaluator, instigation);
        TypeDescriptor localType = TypeDescriptor.parse(type);

        return evaluator.registerLocal(localRef, localType, localName, endLabel);
    }

    private static LabelInfo resolveEndLabel(@NotNull JALMethodEvaluator evaluator,
                                             @Nullable JALParser.LocalInstigationContext instigation)
    {
        if (instigation == null)
            return null;

        JALParser.LabelNameContext labelNameContext = instigation.labelName();
        if (labelNameContext == null || labelNameContext.ID() == null)
            return null;

        return evaluator.resolveLabel(labelNameContext.ID().getText());
    }

    private static String pickLocalName(
            @NotNull JALMethodEvaluator evaluator,
            @Nullable JALParser.JvmInsArgLocalRefContext localRef,
            int idx,
            @Nullable JALParser.LocalInstigationContext instigation
    )
    {
        String instigationName = null;
        if (instigation != null)
        {
            TerminalNode localNameNode = instigation.ID();
            if (localNameNode != null)
                instigationName = localNameNode.getText();
        }

        String preferredName = Objects.requireNonNullElseGet(instigationName, () -> String.format("local_%d", idx));
        if (!(localRef == null || localRef.ID() == null))
        {
            String localName = localRef.ID().getText();
            if (localName.equals(preferredName))
                return localName;
            else
                evaluator.getContext().postWarning(String.format(
                        "Local variable name '%s' does not match the expected name '%s'.",
                        localName, preferredName
                ));
        }

        return preferredName;
    }
}
