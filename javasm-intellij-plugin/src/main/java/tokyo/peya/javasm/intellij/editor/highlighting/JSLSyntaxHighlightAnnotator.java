package tokyo.peya.javasm.intellij.editor.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.preprocessor.JALPreprocessorDirectiveUtil;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodClInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;
import tokyo.peya.langjal.compiler.JALLexer;
import tokyo.peya.langjal.compiler.jvm.DescriptorReader;

public class JSLSyntaxHighlightAnnotator implements Annotator {
    private static void highlightPreprocessorDirectives(@NotNull PsiFile file, @NotNull AnnotationHolder holder) {
        if (!(file instanceof JALFile))
            return;

        for (JALPreprocessorDirectiveUtil.DefineDirective directive
                : JALPreprocessorDirectiveUtil.findDefineDirectives(file.getText())) {
            highlight(file, directive.keywordRange(), holder, JALSyntaxHighlighter.PREPROCESSOR_DIRECTIVE);

            TextRange macroNameRange = directive.macroNameRange();
            if (macroNameRange != null)
                highlight(file, macroNameRange, holder, JALSyntaxHighlighter.MACRO_NAME);

            TextRange valueRange = directive.valueRange();
            if (valueRange != null) {
                highlight(file, valueRange, holder, JALSyntaxHighlighter.MACRO_VALUE);
                highlightMacroValueTokens(directive, holder);
            }
        }
    }

    private static void highlightMacroValueTokens(
            @NotNull JALPreprocessorDirectiveUtil.DefineDirective directive,
            @NotNull AnnotationHolder holder
    ) {
        TextRange valueRange = directive.valueRange();
        if (valueRange == null)
            return;

        JALLexer lexer = new JALLexer(CharStreams.fromString(maskLineContinuations(directive.value())));
        while (true) {
            Token token = lexer.nextToken();
            if (token.getType() == Token.EOF)
                return;

            TextAttributesKey key = JALSyntaxHighlighter.getTokenHighlightKey(token.getType());
            if (key == null)
                continue;

            highlight(TextRange.create(token.getStartIndex(), token.getStopIndex() + 1)
                               .shiftRight(valueRange.getStartOffset()),
                      holder,
                      key);
        }
    }

    private static @NotNull String maskLineContinuations(@NotNull String text) {
        StringBuilder masked = new StringBuilder(text);
        for (int i = 0; i < masked.length() - 1; i++) {
            if (masked.charAt(i) != '\\')
                continue;

            char next = masked.charAt(i + 1);
            if (next != '\r' && next != '\n')
                continue;

            masked.setCharAt(i, ' ');
            masked.setCharAt(i + 1, ' ');
            if (next == '\r' && i + 2 < masked.length() && masked.charAt(i + 2) == '\n')
                masked.setCharAt(i + 2, ' ');
        }

        return masked.toString();
    }

    private static void highlightIdentifiers(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // クラス名, メソッド名
        if (element instanceof ClassNameNode className)
            highlight(element, holder, JALSyntaxHighlighter.CLASS_NAME);
        else if (element instanceof MethodNameNode
                || element instanceof IdentifierMethodClInitNode
                || element instanceof IdentifierMethodInitNode)
            highlight(element, holder, JALSyntaxHighlighter.METHOD_NAME);

        // フィールドの参照名
        if (element instanceof IdentifierNode
                && element.getParent() instanceof FieldReferenceNameNode)
            highlight(element, holder, JALSyntaxHighlighter.FIELD_NAME);

        // 型記述子/オブジェクト
        if (element instanceof FullQualifiedNameNode)
            highlight(element, holder, JALSyntaxHighlighter.CLASS_NAME);

        // ラベル
        if (element instanceof LabelNameNode)
            highlight(element, holder, JALSyntaxHighlighter.LABEL);

        if (element instanceof MethodDescriptorNode method)
            highlightMethodDescriptor(method, holder);
    }

    private static void highlightMethodDescriptor(@NotNull MethodDescriptorNode node, @NotNull AnnotationHolder holder) {
        try {
            DescriptorReader reader = DescriptorReader.fromString(node.getText());
            while (reader.getPos() < node.getText().length()) {
                char c = reader.read();
                if (c == 'L') {
                    int start = reader.getPos() /* 'L'.length(): */ - 1;
                    // クラス型
                    while (c != ';')
                        c = reader.read();
                    int end = reader.getPos();

                    highlight(node, TextRange.create(start, end), holder, JALSyntaxHighlighter.CLASS_NAME);
                } else if (c == 'B' || c == 'C' || c == 'D' || c == 'F' || c == 'I' || c == 'J' || c == 'S' || c == 'Z' || c == 'V') {
                    // プリミティブ型
                    TextAttributesKey highlight = switch (c) {
                        case 'B' -> JALSyntaxHighlighter.DESC_BYTE;
                        case 'C' -> JALSyntaxHighlighter.DESC_CHAR;
                        case 'D' -> JALSyntaxHighlighter.DESC_DOUBLE;
                        case 'F' -> JALSyntaxHighlighter.DESC_FLOAT;
                        case 'I' -> JALSyntaxHighlighter.DESC_INT;
                        case 'J' -> JALSyntaxHighlighter.DESC_LONG;
                        case 'S' -> JALSyntaxHighlighter.DESC_SHORT;
                        case 'Z' -> JALSyntaxHighlighter.DESC_BOOLEAN;
                        case 'V' -> JALSyntaxHighlighter.DESC_VOID;
                        default -> throw new IllegalArgumentException("Unknown primitive type: " + c);
                    };

                    highlight(node, TextRange.create(reader.getPos() - 1, reader.getPos()), holder, highlight);
                }
            }
        } catch (Exception ignored) {
        }
    }

    private static void highlight(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                  @NotNull TextAttributesKey textAttributesKey) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(textAttributesKey)
                .create();
    }

    private static void highlight(@NotNull PsiElement element, TextRange range, @NotNull AnnotationHolder holder,
                                  @NotNull TextAttributesKey textAttributesKey) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.getTextRange().cutOut(range))
                .textAttributes(textAttributesKey)
                .create();
    }

    private static void highlight(@NotNull TextRange range, @NotNull AnnotationHolder holder,
                                  @NotNull TextAttributesKey textAttributesKey) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(range)
                .textAttributes(textAttributesKey)
                .create();
    }

    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiFile file)
            highlightPreprocessorDirectives(file, holder);

        highlightIdentifiers(element, holder);
    }
}
