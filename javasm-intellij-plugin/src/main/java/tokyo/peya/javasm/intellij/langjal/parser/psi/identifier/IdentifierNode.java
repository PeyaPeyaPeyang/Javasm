package tokyo.peya.javasm.intellij.langjal.parser.psi.identifier;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.antlr.intellij.adaptor.psi.Trees;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.JALParserDefinition;
import tokyo.peya.javasm.intellij.langjal.parser.psi.refs.LabelNameReference;
import tokyo.peya.javasm.intellij.langjal.parser.psi.refs.LocalReference;
import tokyo.peya.langjal.compiler.JALParser;

public class IdentifierNode extends ANTLRPsiLeafNode implements PsiNamedElement
{
    public IdentifierNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    @NotNull
    @Override
    public String getName()
    {
        return this.getText();
    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException
    {
        PsiElement newID = Trees.createLeafFromText(
                this.getProject(),
                this.getLanguage(),
                this.getContext(),
                s,
                JALParserDefinition.ID
        );

        if (newID != null)
            return this.replace(newID);

        return this;
    }

    @Override
    public String toString()
    {
        return "Identifier(" + this.getText() + ")";
    }

    @Override
    public PsiReference getReference()
    {
        PsiElement parent = this.getParent();
        IElementType type = parent.getNode().getElementType();
        if (!(type instanceof RuleIElementType rule))
            return null;

        return switch (rule.getRuleIndex())
        {
            case JALParser.RULE_label, JALParser.RULE_labelName -> new LabelNameReference(this);
            case JALParser.RULE_jvmInsArgLocalRef -> new LocalReference(this);
            default -> null;
        };
    }
}
