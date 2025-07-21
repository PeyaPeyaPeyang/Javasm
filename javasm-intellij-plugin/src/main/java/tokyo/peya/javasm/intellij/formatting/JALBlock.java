package tokyo.peya.javasm.intellij.formatting;

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
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.langjal.compiler.JALParser;

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
        else if (ruleIndex == JALParser.RULE_label)
            return Indent.getLabelIndent();

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

    private int calcColumn(Block prevBlock)
    {
        ASTNode prevNode = ((JALBlock) prevBlock).getNode();
        IElementType prevType = prevNode.getElementType();

        // { のあとの調整
        IElementType thisType = this.myNode.getElementType();
        if (thisType instanceof RuleIElementType rule)
        {
            int ruleIndex = rule.getRuleIndex();
            if (ruleIndex == JALParser.RULE_classBody)
                return 1; // 固定カラム
        }

        // 命令ノードなら固定カラムにする（例えば 4 カラム）
        if (prevType instanceof RuleIElementType rule)
        {
            int ruleIndex = rule.getRuleIndex();
            if (ruleIndex == JALParser.RULE_instruction)
                return 2; // 固定カラム
        }
        if (prevType instanceof TokenIElementType token)
        {
            int tokenIndex = token.getANTLRTokenType();
            if (tokenIndex == JALParser.LINE_COMMENT
                    || tokenIndex == JALParser.BLOCK_COMMENT
                    || tokenIndex == JALParser.LBR)
                return 2; // 固定カラム
        }

        // 普通の方法
        PsiElement psi = prevNode.getPsi();
        if (psi == null)
            return 4;

        PsiFile file = psi.getContainingFile();
        Document doc = PsiDocumentManager.getInstance(psi.getProject()).getDocument(file);
        if (doc == null)
            return 4;

        int offset = prevNode.getTextRange().getStartOffset();
        int lineStart = doc.getLineStartOffset(doc.getLineNumber(offset));
        return offset - lineStart;
    }

    @Override
    public boolean isLeaf()
    {
        return this.myNode.getFirstChildNode() == null;
    }
}
