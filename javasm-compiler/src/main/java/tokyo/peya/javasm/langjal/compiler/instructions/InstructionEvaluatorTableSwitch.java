package tokyo.peya.javasm.langjal.compiler.instructions;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import tokyo.peya.javasm.langjal.compiler.JALParser;
import tokyo.peya.javasm.langjal.compiler.analyser.FrameDifferenceInfo;
import tokyo.peya.javasm.langjal.compiler.analyser.stack.StackElementType;
import tokyo.peya.javasm.langjal.compiler.member.EvaluatedInstruction;
import tokyo.peya.javasm.langjal.compiler.member.InstructionInfo;
import tokyo.peya.javasm.langjal.compiler.member.JALMethodCompiler;
import tokyo.peya.javasm.langjal.compiler.member.LabelInfo;
import tokyo.peya.javasm.langjal.compiler.utils.EvaluatorCommons;

import java.util.List;

public class InstructionEvaluatorTableSwitch extends AbstractInstructionEvaluator<JALParser.JvmInsTableswitchContext>
{
    @Override
    protected @NotNull EvaluatedInstruction evaluate(@NotNull JALMethodCompiler compiler,
                                                     JALParser.@NotNull JvmInsTableswitchContext ctxt)
    {
        JALParser.JvmInsArgTableSwitchContext args = ctxt.jvmInsArgTableSwitch();

        int low = EvaluatorCommons.asInteger(args.NUMBER());
        List<JALParser.LabelNameContext> branches = args.labelName();
        JALParser.LabelNameContext defaultBranch = args.labelName(branches.size() - 1);  // default は最後の要素
        int high = low + branches.size() - 1; // low から default までの範囲

        LabelNode defaultLabel = toLabel(compiler, defaultBranch);
        LabelNode[] labels = branches.stream()
                                     .map(labelName -> toLabel(compiler, labelName))
                                     .toArray(LabelNode[]::new);

        TableSwitchInsnNode tableSwitchInsn = new TableSwitchInsnNode(
                low,
                high,
                defaultLabel,
                labels
        );
        return EvaluatedInstruction.of(
                this,
                tableSwitchInsn,
                calcSize(ctxt, compiler.getInstructions().getBytecodeOffset())
        );
    }

    @Override
    public FrameDifferenceInfo getFrameDifferenceInfo(@NotNull InstructionInfo instruction)
    {
        return FrameDifferenceInfo.builder(instruction)
                                  .popPrimitive(StackElementType.INTEGER)
                                  .build();
    }

    private LabelNode toLabel(@NotNull JALMethodCompiler evaluator, @NotNull JALParser.LabelNameContext labelName)
    {
        LabelInfo labelInfo = evaluator.getLabels().resolve(labelName);
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
        int numCases = branches.size() - 1; // 最後の要素は default ブランチなので除外

        return 1               // opcode
                + padding         // padding to 4-byte boundary
                + 4               // default offset
                + 4               // low
                + 4               // high
                + 4 * numCases;   // jump offsets
    }
}
