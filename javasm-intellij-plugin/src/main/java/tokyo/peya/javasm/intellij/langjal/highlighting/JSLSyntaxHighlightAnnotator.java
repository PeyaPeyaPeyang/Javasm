package tokyo.peya.javasm.intellij.langjal.highlighting;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.execution.PSIExecutorUtil;
import tokyo.peya.javasm.intellij.jvm.DescriptorReader;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.FullQualifiedNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodClInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierMethodInitNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.FieldReferenceNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDescriptorNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodNameNode;

public class JSLSyntaxHighlightAnnotator implements Annotator
{
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder)
    {
        highlightIdentifiers(element, holder);
    }

    private static void highlightIdentifiers(@NotNull PsiElement element, @NotNull AnnotationHolder holder)
    {
        // クラス名, メソッド名
        if (element instanceof ClassNameNode className)
            highlightClassName(className, holder);
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

    private static void highlightClassName(@NotNull ClassNameNode node, @NotNull AnnotationHolder holder)
    {
        // クラス名のハイライト
        highlight(node, holder, JALSyntaxHighlighter.CLASS_NAME);

        // クラスが ClassDefinition の子である場合，正しい名前か（ファイル名と合っているか検証する）
        PsiElement parent = node.getParent();
        if (!(parent instanceof ClassDefinitionNode classDef))
            return;

        PSIExecutorUtil.ClassNameValidationResult validationResult =
                PSIExecutorUtil.validateClassName(classDef);
        if (validationResult == null || validationResult.isValid())
            return;

        // クラス名がファイル名と一致しない場合はエラーを表示
        holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Class name does not match file name/path. " +
                        "Expected: " + validationResult.relativePathFromSourceRoot()
        ).range(node.getTextRange()).create();
    }

    private static void highlightMethodDescriptor(@NotNull MethodDescriptorNode node, @NotNull AnnotationHolder holder)
    {
        try
        {
            DescriptorReader reader = DescriptorReader.fromString(node.getText());
            reader.expect('(');
            while (reader.peek() != ')')
            {
                char c = reader.read();
                if (c == 'L')
                {
                    int start = reader.getPos() /* 'L'.length(): */ - 1;
                    // クラス型の引数
                    while (c != ';')
                        c = reader.read();
                    int end = reader.getPos();

                    highlight(node, TextRange.create(start, end), holder, JALSyntaxHighlighter.CLASS_NAME);
                }
                else if (c == 'B' || c == 'C' || c == 'D' || c == 'F' || c == 'I' || c == 'J' || c == 'S' || c == 'Z')
                {
                    // プリミティブ型の引数
                    TextAttributesKey highlight = switch (c)
                    {
                        case 'B' -> JALSyntaxHighlighter.DESC_BYTE;
                        case 'C' -> JALSyntaxHighlighter.DESC_CHAR;
                        case 'D' -> JALSyntaxHighlighter.DESC_DOUBLE;
                        case 'F' -> JALSyntaxHighlighter.DESC_FLOAT;
                        case 'I' -> JALSyntaxHighlighter.DESC_INT;
                        case 'J' -> JALSyntaxHighlighter.DESC_LONG;
                        case 'S' -> JALSyntaxHighlighter.DESC_SHORT;
                        case 'Z' -> JALSyntaxHighlighter.DESC_BOOLEAN;
                        default -> throw new IllegalArgumentException("Unknown primitive type: " + c);
                    };

                    highlight(node, TextRange.create(reader.getPos() - 1, reader.getPos()), holder, highlight);
                }
            }
        }
        catch (Exception ignored)
        {
        }
    }

    private static void highlight(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                  @NotNull TextAttributesKey textAttributesKey)
    {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
              .range(element)
              .textAttributes(textAttributesKey)
              .create();
    }

    private static void highlight(@NotNull PsiElement element, TextRange range, @NotNull AnnotationHolder holder,
                                  @NotNull TextAttributesKey textAttributesKey)
    {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
              .range(element.getTextRange().cutOut(range))
              .textAttributes(textAttributesKey)
              .create();
    }
}
