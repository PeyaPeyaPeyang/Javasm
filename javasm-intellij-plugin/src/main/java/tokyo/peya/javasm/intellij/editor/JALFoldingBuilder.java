package tokyo.peya.javasm.intellij.editor;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.antlr.intellij.adaptor.psi.IdentifierDefSubtree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.inspection.JALPsiElementVisitorRecursive;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchArgumentNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionTableSwitchArgumentNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionTableSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.langjal.compiler.JALLexer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JALFoldingBuilder extends FoldingBuilderEx {
    private static final Pattern DESC_PATTERN = Pattern.compile("<editor-fold\\s+desc(ription)?\\s*=\\s*\"([^\"]*)\"");

    private static String extractDesc(String text) {
        Matcher m = DESC_PATTERN.matcher(text);
        return m.find() ? m.group(1) : "...";
    }

    private static FoldingDescriptor visitInstruction(InstructionNode insn) {
        if (insn instanceof InstructionTableSwitchNode tableSwitch) {
            InstructionTableSwitchArgumentNode arg = tableSwitch.getTableSwitchArgument();
            if (arg != null)
                return new FoldingDescriptor(arg, arg.getTextRange());
        } else if (insn instanceof InstructionLookupSwitchNode lookupSwitch) {
            InstructionLookupSwitchArgumentNode arg = lookupSwitch.getTableSwitchArgument();
            if (arg != null)
                return new FoldingDescriptor(arg, arg.getTextRange());
        }

        return null;
    }

    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(@NotNull PsiElement root,
                                                          @NotNull Document document,
                                                          boolean quick) {
        List<FoldingDescriptor> descriptors = new ArrayList<>();
        List<PsiComment> comments = new ArrayList<>();

        root.accept(new JALPsiElementVisitorRecursive() {
            @Override
            protected void visitClass(@NotNull ClassDefinitionNode node) {
                ClassBodyNode classBody = node.getClassBodyNode();
                if (classBody != null)
                    descriptors.add(new FoldingDescriptor(classBody.getNode(), classBody.getTextRange()));
            }

            @Override
            protected void visitMethod(@NotNull MethodDefinitionNode node) {
                MethodBodyNode methodBody = node.getMethodBody();
                if (methodBody != null)
                    descriptors.add(new FoldingDescriptor(methodBody.getNode(), methodBody.getTextRange()));
            }

            @Override
            protected void visitInstructionSet(@NotNull InstructionSetNode node) {
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }

            @Override
            protected void visitInstruction(@NotNull InstructionNode node) {
                FoldingDescriptor desc = JALFoldingBuilder.visitInstruction(node);
                if (desc != null)
                    descriptors.add(desc);
            }

            @Override
            public void visitComment(@NotNull PsiComment comment) {
                if (!(comment.getTokenType() instanceof TokenIElementType antlrElement))
                    return;

                if (antlrElement.getANTLRTokenType() == JALLexer.BLOCK_COMMENT) {
                    descriptors.add(new FoldingDescriptor(comment, comment.getTextRange()));
                }

                // editor-fold 用に収集
                comments.add(comment);
            }
        });

        // --- ここから editor-fold 処理 ---
        Deque<PsiComment> stack = new ArrayDeque<>();

        for (PsiComment comment : comments) {
            String text = comment.getText();

            if (text.contains("<editor-fold")) {
                stack.push(comment);
            } else if (text.contains("</editor-fold>")) {
                if (stack.isEmpty()) {
                    continue;
                }
                PsiComment start = stack.pop();

                TextRange range = new TextRange(
                        start.getTextRange().getStartOffset(),
                        comment.getTextRange().getEndOffset()
                );

                ASTNode node = start.getNode();

                // desc 抽出して node に紐付け
                FoldingDescriptor descriptor = new FoldingDescriptor(node, range);
                descriptor.setPlaceholderText(extractDesc(start.getText()));
                descriptors.add(descriptor);
            }
        }

        return descriptors.toArray(FoldingDescriptor[]::new);
    }

    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode astNode) {
        PsiElement element = astNode.getPsi();
        if (element instanceof InstructionSetNode instructionSet) {  // こいつも IdentifierDefSubtree だけど， それだとフォルド時に labelName となってしまうが， labelName: としたい
            LabelNode labelNode = instructionSet.getLabel();
            if (labelNode != null)
                return labelNode.getText();
        } else if (element instanceof IdentifierDefSubtree idDef) {
            IdentifierNode id = (IdentifierNode) idDef.getNameIdentifier();
            if (id != null)
                return id.getText();
        }

        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return false;
    }
}
