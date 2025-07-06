package tokyo.peya.plugin.javasm.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import tokyo.peya.plugin.javasm.compiler.AbstractInstructionEvaluator;
import tokyo.peya.plugin.javasm.compiler.EvaluatedInstruction;
import tokyo.peya.plugin.javasm.compiler.EvaluatorCommons;
import tokyo.peya.plugin.javasm.compiler.JALMethodEvaluator;
import tokyo.peya.plugin.javasm.compiler.LabelInfo;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.List;

public class InstructionEvaluatorTableSwitch extends AbstractInstructionEvaluator<JALParser.JvmInsTableswitchContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodEvaluator evaluator,
                                                     JALParser.@NotNull JvmInsTableswitchContext ctxt)
    {
        JALParser.JvmInsArgTableSwitchContext args = ctxt.jvmInsArgTableSwitch();

        int low = EvaluatorCommons.asInteger(args.NUMBER());
        List<JALParser.LabelNameContext> branches = args.labelName();
        JALParser.LabelNameContext defaultBranch = args.labelName(branches.size() - 1);  // default は最後の要素
        int high = low + branches.size() - 2; // default を除いて，インデックス化する

        LabelNode defaultLabel = toLabel(evaluator, defaultBranch);
        LabelNode[] labels = branches.stream()
                                     .map(labelName -> toLabel(evaluator, labelName))
                                     .toArray(LabelNode[]::new);

        TableSwitchInsnNode tableSwitchInsn = new TableSwitchInsnNode(
                low,
                high,
                defaultLabel,
                labels
        );
        return EvaluatedInstruction.of(tableSwitchInsn, calcSize(ctxt));
    }

    private LabelNode toLabel(@NotNull JALMethodEvaluator evaluator, @NotNull JALParser.LabelNameContext labelName)
    {
        LabelInfo labelInfo = evaluator.resolveLabel(labelName.getText());
        return labelInfo.node();
    }

    @Override
    protected JALParser.JvmInsTableswitchContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsTableswitch();
    }

    private static int calcSize(@NotNull JALParser.JvmInsTableswitchContext ctxt)
    {
        JALParser.JvmInsArgTableSwitchContext args = ctxt.jvmInsArgTableSwitch();
        List<JALParser.LabelNameContext> branches = args.labelName();
        return 1 + 4 + 4 + 4 * (branches.size() - 1); // opcode + default offset + low + high + number of labels
    }
}
