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
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.MethodReferenceNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.refs.*;
import tokyo.peya.langjal.compiler.JALParser;

public class IdentifierNode extends ANTLRPsiLeafNode implements PsiNamedElement {
    public IdentifierNode(IElementType type, CharSequence text) {
        super(type, text);
    }

    @NotNull
    @Override
    public String getName() {
        return this.getText();
    }

    @Override
    public PsiElement setName(@NotNull String s) throws IncorrectOperationException {
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
    public String toString() {
        return "Identifier(" + this.getText() + ")";
    }

    @Override
    public PsiReference getReference() {
        PsiElement parent = this.getParent();
        if (parent == null || parent.getNode() == null)
            return null;

        IElementType type = parent.getNode().getElementType();
        if (!(type instanceof RuleIElementType rule))
            return null;

        return switch (rule.getRuleIndex()) {
            case JALParser.RULE_label,
                 JALParser.RULE_labelName -> new LabelNameReference(this);

            case JALParser.RULE_jvmInsArgFieldRefType, JALParser.RULE_jvmInsArgMethodRefOwnerType ->
                    new ClassReference(this);

            case JALParser.RULE_jvmInsArgFieldRefName -> {
                PsiElement g = parent.getParent();
                if (g instanceof FieldReferenceNode field)
                    yield new FieldReference(field);
                yield null;
            }
            case JALParser.RULE_methodName -> {
                PsiElement g = parent.getParent();
                if (g instanceof MethodReferenceNode method)
                    yield new MethodReference(method);
                yield null;
            }
            case JALParser.RULE_typeDescriptor -> new ClassTypeDescriptorReference(this);

            case JALParser.RULE_jvmInsArgLocalRef -> new LocalReference(this);

            default -> null;
        };
    }
}
