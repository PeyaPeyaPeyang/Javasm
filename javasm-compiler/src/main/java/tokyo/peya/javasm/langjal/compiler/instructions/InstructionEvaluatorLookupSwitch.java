package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.LabelInfo;

import java.util.LinkedList;
import java.util.List;

public class InstructionEvaluatorLookupSwitch extends AbstractInstructionEvaluator<JALParser.JvmInsLookupswitchContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsLookupswitchContext ctxt)
    {
        JALParser.JvmInsArgLookupSwitchContext args = ctxt.jvmInsArgLookupSwitch();
        List<JALParser.JvmInsArgLookupSwitchCaseContext> cases = args.jvmInsArgLookupSwitchCase();

        List<Integer> keys = new LinkedList<>();
        List<LabelNode> labels = new LinkedList<>();
        LabelNode defaultLabel = null;

        for (JALParser.JvmInsArgLookupSwitchCaseContext c : cases)
        {
            JALParser.JvmInsArgLookupSwitchCaseNameContext caseName = c.jvmInsArgLookupSwitchCaseName();
            JALParser.LabelNameContext labelName = c.labelName();
            if (caseName.KWD_SWITCH_DEFAULT() != null)
                defaultLabel = toLabel(evaluator, labelName);
            else if (caseName.NUMBER() != null)
            {
                int key = EvaluatorCommons.asInteger(caseName.NUMBER());
                keys.add(key);
                labels.add(toLabel(evaluator, labelName));
            }
        }

        if (defaultLabel == null)
            throw new IllegalArgumentException("lookupswitch must have a default case");

        LookupSwitchInsnNode lookupSwitchInsnNode = new LookupSwitchInsnNode(
                defaultLabel,
                keys.stream().mapToInt(Integer::intValue).toArray(),
                labels.toArray(new LabelNode[0])
        );
        return EvaluatedInstruction.of(lookupSwitchInsnNode, calcSize(ctxt, evaluator.getBytecodeOffset()));
    }

    private LabelNode toLabel(@NotNull JALMethodEvaluator evaluator, @NotNull JALParser.LabelNameContext labelName)
    {
        LabelInfo labelInfo = evaluator.resolveLabel(labelName.getText());
        return labelInfo.node();
    }

    @Override
    protected JALParser.JvmInsLookupswitchContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsLookupswitch();
    }

    private static int calcSize(@NotNull JALParser.JvmInsLookupswitchContext ctxt, long startOffset)
    {
        JALParser.JvmInsArgLookupSwitchContext args = ctxt.jvmInsArgLookupSwitch();
        List<JALParser.JvmInsArgLookupSwitchCaseContext> cases = args.jvmInsArgLookupSwitchCase();

        int nPairs = 0;
        for (JALParser.JvmInsArgLookupSwitchCaseContext c : cases)
            if (c.jvmInsArgLookupSwitchCaseName().KWD_SWITCH_DEFAULT() == null)
                nPairs++;

        int padding = (int) ((4 - (startOffset + 1) % 4) % 4);

        return 1              // opcode
                + padding        // align to 4-byte boundary
                + 4              // default offset
                + 4              // nPairs
                + 8 * nPairs;    // key + offset per pair
    }
}
