package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import tokyo.peya.javasm.langjal.compiler.AbstractInstructionEvaluator;
import tokyo.peya.javasm.langjal.compiler.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.EvaluatorCommons;
import tokyo.peya.javasm.langjal.compiler.JALMethodEvaluator;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.LabelInfo;

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
        return EvaluatedInstruction.of(
                tableSwitchInsn,
                calcSize(ctxt, evaluator.getInstructions().getBytecodeOffset())
        );
    }

    private LabelNode toLabel(@NotNull JALMethodEvaluator evaluator, @NotNull JALParser.LabelNameContext labelName)
    {
        LabelInfo labelInfo = evaluator.getLabels().resolve(labelName.getText());
        return labelInfo.node();
    }

    @Override
    protected JALParser.JvmInsTableswitchContext map(JALParser.@NotNull InstructionContext instruction)
    {
        return instruction.jvmInsTableswitch();
    }

    private static int calcSize(@NotNull JALParser.JvmInsTableswitchContext ctxt, long startOffset)
    {
        JALParser.JvmInsArgTableSwitchContext args = ctxt.jvmInsArgTableSwitch();
        List<JALParser.LabelNameContext> branches = args.labelName();

        int padding = (int) ((4 - (startOffset + 1) % 4) % 4);
        int numCases = branches.size();

        return 1               // opcode
                + padding         // padding to 4-byte boundary
                + 4               // default offset
                + 4               // low
                + 4               // high
                + 4 * numCases;   // jump offsets
    }
}
