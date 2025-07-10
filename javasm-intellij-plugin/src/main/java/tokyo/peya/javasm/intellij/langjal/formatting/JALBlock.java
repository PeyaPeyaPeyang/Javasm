package tokyo.peya.javasm.intellij.langjal.formatting;

import com.intellij.formatting.Alignment;
import com.intellij.formatting.Block;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Indent;
import com.intellij.formatting.Spacing;
import com.intellij.formatting.SpacingBuilder;
import com.intellij.formatting.Wrap;
import com.intellij.formatting.WrapType;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.langjal.compiler.JALParser;

import java.util.ArrayList;
import java.util.List;

public class JALBlock extends AbstractBlock
{
    private final SpacingBuilder spacingBuilder;

    protected JALBlock(
            @NotNull ASTNode node,
            @Nullable Wrap wrap,
            @Nullable Alignment alignment,
            SpacingBuilder spacingBuilder
    )
    {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren()
    {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = this.myNode.getFirstChildNode();
        while (child != null)
        {
            if (child.getTextRange().getLength() == 0 || child.getElementType() == TokenType.WHITE_SPACE)
            {
                child = child.getTreeNext();
                continue;
            }

            blocks.add(new JALBlock(child, Wrap.createWrap(WrapType.NONE, false), null, this.spacingBuilder));
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent()
    {
        IElementType type = this.myNode.getElementType();
        if (!(type instanceof RuleIElementType rule))
            return Indent.getNoneIndent();

        int ruleIndex = rule.getRuleIndex();

        if (ruleIndex == JALParser.RULE_classMetaItem
                || ruleIndex == JALParser.RULE_fieldDefinition
                || ruleIndex == JALParser.RULE_methodDefinition
                || ruleIndex == JALParser.RULE_instruction)
            return Indent.getNormalIndent();

        return Indent.getNoneIndent();
    }

    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2)
    {
        return this.spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public @NotNull ChildAttributes getChildAttributes(int newChildIndex)
    {
        List<Block> subBlocks = getSubBlocks();

        if (newChildIndex > 0 && newChildIndex <= subBlocks.size())
        {
            Block prevBlock = subBlocks.get(newChildIndex - 1);
            int column = calcColumn(prevBlock);

            return new ChildAttributes(Indent.getSpaceIndent(column), null);
        }

        return new ChildAttributes(Indent.getNoneIndent(), null);
    }

    private int calcColumn(Block block)
    {
        ASTNode node = ((JALBlock) block).getNode();
        PsiElement psi = node.getPsi();
        if (psi == null)
            return 0;

        PsiFile file = psi.getContainingFile();
        Project project = psi.getProject();
        Document doc = PsiDocumentManager.getInstance(project).getDocument(file);
        if (doc == null)
            return 0;

        int offset = node.getTextRange().getStartOffset();
        int lineStart = doc.getLineStartOffset(doc.getLineNumber(offset));
        return offset - lineStart;
    }

    @Override
    public boolean isLeaf()
    {
        return this.myNode.getFirstChildNode() == null;
    }
}
