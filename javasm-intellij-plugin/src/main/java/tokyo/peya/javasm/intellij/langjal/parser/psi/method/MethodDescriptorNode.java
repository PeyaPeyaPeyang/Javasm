package tokyo.peya.javasm.intellij.langjal.parser.psi.method;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiReference;
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.refs.MethodDescriptorClassReference;
import tokyo.peya.langjal.compiler.jvm.MethodDescriptor;
import tokyo.peya.langjal.compiler.jvm.TypeDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodDescriptorNode extends ANTLRPsiNode {
    private static final Pattern CLASS_DESCRIPTOR_PATTERN = Pattern.compile("(\\[*L[^;]+;)");

    public MethodDescriptorNode(@NotNull ASTNode node) {
        super(node);
    }

    public @NotNull MethodDescriptor getMethodDescriptor() {
        return MethodDescriptor.parse(this.getText());
    }

    @NotNull
    public TypeDescriptor getReturnType() {
        return this.getMethodDescriptor().getReturnType();
    }

    @NotNull
    public TypeDescriptor[] getParameterTypes() {
        return this.getMethodDescriptor().getParameterTypes();
    }

    @NotNull
    public String getMethodName() {
        return this.getText();
    }

    @Override
    public PsiReference @NotNull [] getReferences() {
        String text = this.getText();
        Matcher matcher = CLASS_DESCRIPTOR_PATTERN.matcher(text);
        List<PsiReference> references = new ArrayList<>();

        while (matcher.find()) {
            String descriptor = matcher.group(1);
            int arrayPrefixLength = 0;
            while (arrayPrefixLength < descriptor.length() && descriptor.charAt(arrayPrefixLength) == '[')
                arrayPrefixLength++;

            int startOffset = matcher.start(1) + arrayPrefixLength + 1; // skip '['* and leading 'L'
            int endOffset = matcher.end(1) - 1; // skip trailing ';'
            String className = descriptor.substring(arrayPrefixLength + 1, descriptor.length() - 1);

            references.add(new MethodDescriptorClassReference(this, TextRange.create(startOffset, endOffset), className));
        }

        return references.toArray(PsiReference.EMPTY_ARRAY);
    }
}
