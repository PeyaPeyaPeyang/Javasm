package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LabelInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

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
        return EvaluatedInstruction.of(lookupSwitchInsnNode, calcSize(ctxt));
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

    private static int calcSize(@NotNull JALParser.JvmInsLookupswitchContext ctxt)
    {
        JALParser.JvmInsArgLookupSwitchContext args = ctxt.jvmInsArgLookupSwitch();
        List<JALParser.JvmInsArgLookupSwitchCaseContext> cases = args.jvmInsArgLookupSwitchCase();
        int size = 1 + 4 + 4 * cases.size(); // opcode + default offset + number of pairs
        for (JALParser.JvmInsArgLookupSwitchCaseContext c : cases)
            if (c.jvmInsArgLookupSwitchCaseName().KWD_SWITCH_DEFAULT() == null)
                size += 2; // key + label
        return size;
    }
}
