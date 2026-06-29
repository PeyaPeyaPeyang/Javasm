package tokyo.peya.javasm.intellij.editor.document;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.preprocessor.JALPreprocessorDirectiveUtil;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;
import tokyo.peya.javasm.intellij.utils.JALMessages;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JALInstructionDocumentProvider extends AbstractDocumentationProvider {
    private static final String DOCUMENTATION_PATH = "instructions";
    private static final String LOCALISED_DOCUMENTATION_PATH = "localization/%s/instructions";
    private static final Pattern INSTRUCTION_NAME_PATTERN =
            Pattern.compile("<!--\\s*Instructions:\\s*([a-z_0-9]+(,\\s*[a-z_0-9]+)*)\\s*-->");
    private static final Pattern INSTRUCTION_LINK_PATTERN =
            Pattern.compile("\\{link:([a-z_0-9]+)}");

    private static final Map<String, String> DOCUMENTS = new HashMap<>();

    public JALInstructionDocumentProvider() {
        if (DOCUMENTS.isEmpty())
            loadHTMLFiles();
    }

    public static List<String> loadHTMLFiles() {
        List<String> htmlContents = new ArrayList<>();
        try {
            ClassLoader classLoader = JALInstructionDocumentProvider.class.getClassLoader();

            String lang = JALMessages.getLocale().getLanguage();
            String localizedPath = String.format(LOCALISED_DOCUMENTATION_PATH, lang);

            // 1. ローカライズ優先
            loadFromPath(classLoader, localizedPath, true);

            // 2. デフォルト（未登録のみ）
            loadFromPath(classLoader, DOCUMENTATION_PATH, false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load HTML files from JAR", e);
        }

        return htmlContents;
    }

    private static void loadFromPath(ClassLoader classLoader, String path, boolean overwrite) throws IOException {
        Enumeration<URL> resources = classLoader.getResources(path);

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            if (!"jar".equals(url.getProtocol()))
                throw new RuntimeException("This method supports JAR protocol only!");

            String urlPath = url.getPath();
            String jarPath = urlPath.substring("file:".length(), urlPath.indexOf("!"));
            jarPath = URLDecoder.decode(jarPath, StandardCharsets.UTF_8);

            try (ZipFile jarFile = new ZipFile(jarPath)) {
                Enumeration<? extends ZipEntry> entries = jarFile.entries();

                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();

                    if (!entry.isDirectory()
                            && entry.getName().startsWith(path + "/")
                            && entry.getName().endsWith(".html")) {
                        String content = new String(
                                jarFile.getInputStream(entry).readAllBytes(),
                                StandardCharsets.UTF_8
                        );

                        content = replaceContents(content);
                        String[] instructionNames = extractInstructionNames(content);

                        for (String instructionName : instructionNames) {
                            if (overwrite || !DOCUMENTS.containsKey(instructionName)) {
                                DOCUMENTS.put(instructionName, content);
                            }
                        }
                    }
                }
            }
        }
    }

    @NotNull
    private static String[] extractInstructionNames(@NotNull String content) {
        Matcher matcher = INSTRUCTION_NAME_PATTERN.matcher(content);
        if (!matcher.find())
            return new String[0];
        String instructionNames = matcher.group(1);
        return instructionNames.split(",\\s*");
    }

    @NotNull
    private static String replaceContents(@NotNull String content) {
        Matcher matcher = INSTRUCTION_LINK_PATTERN.matcher(content);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            String instructionName = matcher.group(1);
            String link = JVMDocumentationLinkProvider.getDocumentationLink(instructionName);
            matcher.appendReplacement(sb, "<a href=\"" + link + "\">" + instructionName + "</a>");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Override
    public @Nullable @Nls String generateDoc(PsiElement element, @Nullable PsiElement originalElement) {
        String preprocessorDoc = generatePreprocessorDoc(originalElement != null ? originalElement : element);
        if (preprocessorDoc != null)
            return preprocessorDoc;

        if (!(originalElement instanceof InstructionNameNode instructionNameNode))
            return null;
        String instructionName = instructionNameNode.getInstructionName();
        String content = DOCUMENTS.get(instructionName);
        if (content == null)
            return null;

        return content.replace("{instruction}", instructionName);
    }

    private static @Nullable @Nls String generatePreprocessorDoc(@Nullable PsiElement element) {
        if (element == null || element.getContainingFile() == null)
            return null;

        JALPreprocessorDirectiveUtil.DefineDirective directive =
                JALPreprocessorDirectiveUtil.findDefineDirectiveAt(
                        element.getContainingFile().getText(),
                        element.getTextOffset()
                );
        if (directive == null)
            return null;

        String escapedMacroName = escapeHtml(directive.macroName().isEmpty() ? "<macro>" : directive.macroName());
        String escapedValue = escapeHtml(directive.value().strip());

        if ("ja".equals(JALMessages.getLocale().getLanguage())) {
            return """
                    <h2>#define</h2>
                    <p>プリプロセッサのマクロ定義です。以降の行で同じ識別子が現れると、コンパイル前に定義値へ展開されます。</p>
                    <pre>#define %s %s</pre>
                    <ul>
                      <li>行末の <code>\\</code> で定義を次の行へ継続できます。</li>
                      <li>文字列リテラル、行コメント、ブロックコメント内の識別子は展開されません。</li>
                      <li>再帰的な展開は循環を避けながら最大深さまで処理されます。</li>
                    </ul>
                    """.formatted(escapedMacroName, escapedValue);
        }

        return """
                <h2>#define</h2>
                <p>Defines a preprocessor macro. Later occurrences of the same identifier are expanded before compilation.</p>
                <pre>#define %s %s</pre>
                <ul>
                  <li>A trailing <code>\\</code> continues the definition on the next line.</li>
                  <li>Identifiers inside string literals, line comments, and block comments are not expanded.</li>
                  <li>Recursive expansion is bounded and avoids cycles.</li>
                </ul>
                """.formatted(escapedMacroName, escapedValue);
    }

    @NotNull
    private static String escapeHtml(@NotNull String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    @Override
    public @Nullable PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object,
                                                                     PsiElement element) {
        if (element instanceof InstructionNameNode instructionNameNode)
            return instructionNameNode;
        else if (object instanceof InstructionNode instruction)
            return instruction;

        return null;
    }

    @Override
    public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file,
                                                              @Nullable PsiElement contextElement, int targetOffset) {
        PsiElement element = file.findElementAt(targetOffset);
        if (JALPreprocessorDirectiveUtil.findDefineDirectiveAt(file.getText(), targetOffset) != null)
            return element;

        if (element instanceof InstructionNameNode instructionNameNode)
            return instructionNameNode;
        else if (element instanceof InstructionNode instructionNode)
            return instructionNode;

        return null;
    }
}
