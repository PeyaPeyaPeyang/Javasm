package tokyo.peya.javasm.intellij.dependency;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Key;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.ui.components.JBTextArea;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionJumpNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchCaseNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionTableSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.javasm.intellij.stackviewer.StackFrameAnalysisResult;
import tokyo.peya.javasm.intellij.stackviewer.StackFrameInfoComputer;
import tokyo.peya.javasm.intellij.stackviewer.StackFramePanelFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service(Service.Level.PROJECT)
public final class InstructionDependencyService {
    public static final Key<InstructionDependencyAnalysisResult> ANALYSIS_RESULT_KEY =
            Key.create(InstructionDependencyService.class.getName() + ".analysisResult");

    @SuppressWarnings("unused")
    private final Project project;

    public InstructionDependencyService(@NotNull Project project) {
        this.project = project;
    }

    public static @NotNull InstructionDependencyService getInstance(@NotNull Project project) {
        return project.getService(InstructionDependencyService.class);
    }

    public @Nullable InstructionDependencyAnalysisResult getCached(@NotNull JALFile file) {
        return file.getUserData(ANALYSIS_RESULT_KEY);
    }

    public void clearCached(@NotNull JALFile file) {
        file.putUserData(ANALYSIS_RESULT_KEY, null);
    }

    public @Nullable InstructionDependencyAnalysisResult reanalyze(@NotNull JALFile file) {
        AnalysisInput input = ApplicationManager.getApplication().runReadAction((Computable<AnalysisInput>) () -> {
            if (!file.isValid())
                return null;
            return new AnalysisInput(file.getText(), file);
        });
        if (input == null)
            return null;

        StackFrameInfoComputer computer = new StackFrameInfoComputer(
                input.content(),
                new StackFramePanelFactory.ProgressbarUpdater(new JProgressBar(), new JButton(), new JBTextArea())
        );
        InstructionDependencyAnalysisResult result;
        if (computer.computeStackFrames()) {
            StackFrameAnalysisResult frameResult = computer.getAnalysisResult();
            result = InstructionDependencyComputer.compute(frameResult);
        } else {
            result = InstructionDependencyAnalysisResult.empty();
        }
        InstructionDependencyAnalysisResult finalResult = result;
        return ApplicationManager.getApplication().runReadAction((Computable<InstructionDependencyAnalysisResult>) () -> {
            if (!input.file().isValid())
                return null;
            InstructionDependencyAnalysisResult psiBackedResult =
                    this.ensurePsiInstructionsPresent(input.file(), finalResult);
            InstructionDependencyAnalysisResult enrichedResult =
                    this.attachInstructionSetInfo(input.file(), psiBackedResult);
            input.file().putUserData(ANALYSIS_RESULT_KEY, enrichedResult);
            return enrichedResult;
        });
    }

    private @NotNull InstructionDependencyAnalysisResult ensurePsiInstructionsPresent(
            @NotNull JALFile file,
            @NotNull InstructionDependencyAnalysisResult result
    ) {
        // PSI 上の命令に対応するエントリがない場合は，NEUTRAL なエントリを追加する
        Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries =
                new EnumMap<>(InstructionDependencyKind.class);
        for (InstructionDependencyKind kind : InstructionDependencyKind.values())
            entries.put(kind, new ArrayList<>(result.getEntries(kind)));

        // 命令をキーにエントリを検索できるようにするためのマップ
        Map<InstructionKey, InstructionDependencyEntry> entryByInstruction = new HashMap<>();
        for (InstructionDependencyEntry entry : result.getAllEntries()) {
            entryByInstruction.put(new InstructionKey(
                    entry.methodName(),
                    entry.methodDescriptor(),
                    entry.instructionOffset()
            ), entry);
        }

        Set<InstructionKey> psiInstructionKeys = new HashSet<>();
        Map<InstructionDependencyEntry, InstructionDependencyEntry> psiBackedEntryByOriginal = new HashMap<>();
        List<InstructionDependencyEdge> edges = new ArrayList<>(result.edges());
        for (MethodDefinitionNode methodNode : PsiTreeUtil.findChildrenOfType(file, MethodDefinitionNode.class)) {
            String methodName = methodNode.getMethodName();
            String methodDescriptor = methodNode.getMethodDescriptor().getDescriptorString();
            List<InstructionWithEntry> methodEntries = new ArrayList<>();

            // PSI 上の命令を順番に見ていき，対応するエントリがない場合は NEUTRAL なエントリを追加する
            for (InstructionNode instruction : PsiTreeUtil.findChildrenOfType(methodNode, InstructionNode.class)) {
                Integer offset = this.tryGetInstructionOffset(instruction);
                if (offset == null)
                    continue;

                InstructionKey key = new InstructionKey(methodName, methodDescriptor, offset);
                psiInstructionKeys.add(key);
                InstructionDependencyEntry entry = entryByInstruction.get(key);
                if (entry == null) {
                    entry = new InstructionDependencyEntry(
                            methodName,
                            methodDescriptor,
                            offset,
                            this.displayInstructionName(instruction),
                            0,
                            0,
                            InstructionDependencyKind.NEUTRAL
                    );
                    entries.get(InstructionDependencyKind.NEUTRAL).add(entry);
                    entryByInstruction.put(key, entry);
                } else {
                    InstructionDependencyEntry psiBackedEntry = this.withPsiInstructionName(entry, instruction);
                    if (!psiBackedEntry.equals(entry)) {
                        this.replaceEntry(entries, entry, psiBackedEntry);
                        entryByInstruction.put(key, psiBackedEntry);
                        psiBackedEntryByOriginal.put(entry, psiBackedEntry);
                        entry = psiBackedEntry;
                    }
                }
                methodEntries.add(new InstructionWithEntry(instruction, entry));
            }

            methodEntries.sort(Comparator.comparingInt(it -> it.entry().instructionOffset()));
            for (int i = 1; i < methodEntries.size(); i++) {
                InstructionWithEntry previous = methodEntries.get(i - 1);
                InstructionWithEntry current = methodEntries.get(i);
                // 命令が fall-through しない場合は，灰色の CONTROL エッジを張る
                if (!this.fallsThrough(previous.instruction()))
                    continue;
                edges.add(new InstructionDependencyEdge(
                        previous.entry(),
                        current.entry(),
                        InstructionDependencyEdgeKind.CONTROL
                ));
            }
        }

        edges.replaceAll(edge -> new InstructionDependencyEdge(
                psiBackedEntryByOriginal.getOrDefault(edge.from(), edge.from()),
                psiBackedEntryByOriginal.getOrDefault(edge.to(), edge.to()),
                edge.kind()
        ));
        this.keepPsiBackedEntriesOnly(entries, psiInstructionKeys);
        edges.removeIf(edge -> !psiInstructionKeys.contains(InstructionKey.from(edge.from()))
                || !psiInstructionKeys.contains(InstructionKey.from(edge.to())));

        return new InstructionDependencyAnalysisResult(
                entries,
                edges,
                result.instructionSets(),
                result.instructionSetJumps()
        );
    }

    private void keepPsiBackedEntriesOnly(@NotNull Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries,
                                          @NotNull Set<InstructionKey> psiInstructionKeys) {
        for (List<InstructionDependencyEntry> kindEntries : entries.values())
            kindEntries.removeIf(entry -> !psiInstructionKeys.contains(InstructionKey.from(entry)));
    }

    private @NotNull InstructionDependencyEntry withPsiInstructionName(@NotNull InstructionDependencyEntry entry,
                                                                       @NotNull InstructionNode instruction) {
        String instructionName = this.displayInstructionName(instruction);
        if (instructionName.equals(entry.instructionName()))
            return entry;

        return new InstructionDependencyEntry(
                entry.methodName(),
                entry.methodDescriptor(),
                entry.instructionOffset(),
                instructionName,
                entry.producedCount(),
                entry.consumedCount(),
                entry.kind()
        );
    }

    private void replaceEntry(@NotNull Map<InstructionDependencyKind, List<InstructionDependencyEntry>> entries,
                              @NotNull InstructionDependencyEntry oldEntry,
                              @NotNull InstructionDependencyEntry newEntry) {
        List<InstructionDependencyEntry> kindEntries = entries.get(oldEntry.kind());
        if (kindEntries == null)
            return;
        int index = kindEntries.indexOf(oldEntry);
        if (index >= 0)
            kindEntries.set(index, newEntry);
    }

    private @NotNull String displayInstructionName(@NotNull InstructionNode instruction) {
        String instructionName = instruction.getInstructionName();
        if (instructionName == null)
            return "<instruction>";

        if (this.shouldShowOperands(instruction)) {
            String text = instruction.getText().trim();
            if (!text.isEmpty())
                return text.lines().findFirst().orElse(instructionName).trim();
        }

        return instructionName;
    }

    private boolean shouldShowOperands(@NotNull InstructionNode instruction) {
        return instruction instanceof InstructionJumpNode;
    }

    private boolean fallsThrough(@NotNull InstructionNode instruction) {
        String name = instruction.getInstructionName();
        if (name == null)
            return true;
        return switch (name) {
            case "goto", "goto_w",
                 "ret",
                 "ireturn", "lreturn", "freturn", "dreturn", "areturn", "return",
                 "athrow",
                 "tableswitch", "lookupswitch" -> false;
            default -> true;
        };
    }

    private @NotNull InstructionDependencyAnalysisResult attachInstructionSetInfo(
            @NotNull JALFile file,
            @NotNull InstructionDependencyAnalysisResult result
    ) {
        Map<InstructionKey, InstructionDependencyEntry> entryByInstruction = new HashMap<>();
        for (InstructionDependencyEntry entry : result.getAllEntries()) {
            entryByInstruction.put(new InstructionKey(
                    entry.methodName(),
                    entry.methodDescriptor(),
                    entry.instructionOffset()
            ), entry);
        }

        List<InstructionSetDependencyGroup> groups = new ArrayList<>();
        List<InstructionSetJumpEdge> jumps = new ArrayList<>();

        for (MethodDefinitionNode methodNode : PsiTreeUtil.findChildrenOfType(file, MethodDefinitionNode.class)) {
            String methodName = methodNode.getMethodName();
            String methodDescriptor = methodNode.getMethodDescriptor().getDescriptorString();
            Map<String, InstructionSetDependencyGroup> groupByLabel = new HashMap<>();

            InstructionSetNode[] instructionSets = methodNode.getInstructionSets();
            for (int index = 0; index < instructionSets.length; index++) {
                InstructionSetNode instructionSet = instructionSets[index];
                String label = instructionSet.getName();
                if (label == null || label.isBlank())
                    continue;

                List<Integer> instructionOffsets = new ArrayList<>();
                for (InstructionNode instruction : instructionSet.getInstructions()) {
                    Integer offset = this.tryGetInstructionOffset(instruction);
                    if (offset != null)
                        instructionOffsets.add(offset);
                }

                InstructionSetDependencyGroup group = new InstructionSetDependencyGroup(
                        methodName,
                        methodDescriptor,
                        label,
                        index,
                        instructionOffsets
                );
                groups.add(group);
                groupByLabel.put(label, group);
            }

            for (InstructionSetNode instructionSet : instructionSets) {
                for (InstructionNode instruction : instructionSet.getInstructions()) {
                    Integer offset = this.tryGetInstructionOffset(instruction);
                    if (offset == null)
                        continue;
                    InstructionDependencyEntry from = entryByInstruction.get(new InstructionKey(
                            methodName,
                            methodDescriptor,
                            offset
                    ));
                    if (from == null)
                        continue;

                    for (LabelNameNode label : this.getJumpLabels(instruction)) {
                        InstructionSetDependencyGroup target = groupByLabel.get(label.getName());
                        if (target != null)
                            jumps.add(new InstructionSetJumpEdge(from, target));
                    }
                }
            }
        }

        return result.withInstructionSets(groups, jumps);
    }

    private Integer tryGetInstructionOffset(@NotNull InstructionNode instruction) {
        try {
            return instruction.getStartInstructionOffset();
        } catch (IllegalStateException e) {
            return null;
        }
    }

    private @NotNull List<LabelNameNode> getJumpLabels(@NotNull InstructionNode instruction) {
        List<LabelNameNode> labels = new ArrayList<>();
        switch (instruction) {
            case InstructionJumpNode jumpNode -> {
                LabelNameNode label = jumpNode.getJumpLabel();
                if (label != null)
                    labels.add(label);
            }
            case InstructionTableSwitchNode tableSwitchNode -> {
                LabelNameNode defaultLabel = tableSwitchNode.getDefaultBranchLabelName();
                if (defaultLabel != null)
                    labels.add(defaultLabel);
                LabelNameNode[] branchLabels = tableSwitchNode.getBranchLabels();
                if (branchLabels != null)
                    labels.addAll(List.of(branchLabels));
                this.addSwitchLabelsFallback(tableSwitchNode, labels);
            }
            case InstructionLookupSwitchNode lookupSwitchNode -> {
                LabelNameNode defaultLabel = lookupSwitchNode.getDefaultBranchLabelName();
                if (defaultLabel != null)
                    labels.add(defaultLabel);
                InstructionLookupSwitchCaseNode[] branches = lookupSwitchNode.getCaseBranches();
                if (branches != null) {
                    for (InstructionLookupSwitchCaseNode branch : branches) {
                        LabelNameNode label = branch.getBranchLabel();
                        if (label != null)
                            labels.add(label);
                    }
                }
                this.addSwitchLabelsFallback(lookupSwitchNode, labels);
            }
            default -> {
            }
        }
        return labels;
    }

    private void addSwitchLabelsFallback(@NotNull InstructionNode instruction,
                                         @NotNull List<LabelNameNode> labels) {
        for (LabelNameNode label : PsiTreeUtil.findChildrenOfType(instruction, LabelNameNode.class)) {
            if (!labels.contains(label))
                labels.add(label);
        }
    }

    private record InstructionKey(@NotNull String methodName, @NotNull String methodDescriptor, int instructionOffset) {
        private static @NotNull InstructionKey from(@NotNull InstructionDependencyEntry entry) {
            return new InstructionKey(entry.methodName(), entry.methodDescriptor(), entry.instructionOffset());
        }
    }

    private record AnalysisInput(@NotNull String content, @NotNull JALFile file) {
    }

    private record InstructionWithEntry(@NotNull InstructionNode instruction,
                                        @NotNull InstructionDependencyEntry entry) {
    }
}
