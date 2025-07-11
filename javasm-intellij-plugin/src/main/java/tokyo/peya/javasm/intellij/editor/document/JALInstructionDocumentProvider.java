package tokyo.peya.javasm.intellij.editor.document;

import com.intellij.lang.documentation.AbstractDocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.InstructionNode;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JALInstructionDocumentProvider extends AbstractDocumentationProvider
{
    private static final String DOCUMENTATION_PATH = "instructions";
    private static final Pattern INSTRUCTION_NAME_PATTERN =
            Pattern.compile("<!--\\s*Instructions:\\s*([a-z_0-9]+(,\\s*[a-z_0-9]+)*)\\s*-->");
    private static final Pattern INSTRUCTION_LINK_PATTERN =
            Pattern.compile("\\{link:([a-z_0-9]+)}");

    private static final Map<String, String> DOCUMENTS = new HashMap<>();

    public JALInstructionDocumentProvider()
    {
        if (DOCUMENTS.isEmpty())
            loadHTMLFiles();
    }

    @Override
    public @Nullable @Nls String generateDoc(PsiElement element, @Nullable PsiElement originalElement)
    {
        if (!(originalElement instanceof InstructionNameNode instructionNameNode))
            return null;
        String instructionName = instructionNameNode.getInstructionName();
        String content = DOCUMENTS.get(instructionName);
        if (content == null)
            return null;

        return content.replace("{instruction}", instructionName);
    }

    @Override
    public @Nullable PsiElement getDocumentationElementForLookupItem(PsiManager psiManager, Object object,
                                                                     PsiElement element)
    {
        if (element instanceof InstructionNameNode instructionNameNode)
            return instructionNameNode;
        else if (object instanceof InstructionNode instruction)
            return instruction;

        return null;
    }

    @Override
    public @Nullable PsiElement getCustomDocumentationElement(@NotNull Editor editor, @NotNull PsiFile file,
                                                              @Nullable PsiElement contextElement, int targetOffset)
    {
        PsiElement element = file.findElementAt(targetOffset);
        if (element instanceof InstructionNameNode instructionNameNode)
            return instructionNameNode;
        else if (element instanceof InstructionNode instructionNode)
            return instructionNode;

        return null;
    }

    public static List<String> loadHTMLFiles()
    {
        List<String> htmlContents = new ArrayList<>();
        try
        {
            ClassLoader classLoader = JALInstructionDocumentProvider.class.getClassLoader();
            Enumeration<URL> resources = classLoader.getResources(DOCUMENTATION_PATH);

            while (resources.hasMoreElements())
            {
                URL url = resources.nextElement();

                if (!"jar".equals(url.getProtocol()))
                    throw new RuntimeException("This method supports JAR protocol only!");

                // jar:file:/path/to/plugin.jar!/myplugin/docs
                String urlPath = url.getPath();
                String jarPath = urlPath.substring("file:".length(), urlPath.indexOf("!"));

                jarPath = URLDecoder.decode(jarPath, StandardCharsets.UTF_8);

                try (ZipFile jarFile = new ZipFile(jarPath))
                {
                    Enumeration<? extends ZipEntry> entries = jarFile.entries();

                    boolean foundDocumentation = false;
                    while (entries.hasMoreElements())
                    {
                        ZipEntry entry = entries.nextElement();

                        if (!entry.isDirectory()
                                && entry.getName().startsWith(DOCUMENTATION_PATH + "/")
                                && entry.getName().endsWith(".html"))
                        {
                            foundDocumentation = true;
                            String content = new String(
                                    jarFile.getInputStream(entry).readAllBytes(),
                                    StandardCharsets.UTF_8
                            );
                            content = replaceContents(content);
                            String[] instructionNames = extractInstructionNames(content);
                            for (String instructionName : instructionNames)
                                DOCUMENTS.put(instructionName, content);
                        }
                        else if (foundDocumentation)
                            break; // ドキュメントの捜査完了
                    }
                }
            }

        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to load HTML files from JAR", e);
        }

        return htmlContents;
    }

    @NotNull
    private static String[] extractInstructionNames(@NotNull String content)
    {
        Matcher matcher = INSTRUCTION_NAME_PATTERN.matcher(content);
        if (!matcher.find())
            return new String[0];
        String instructionNames = matcher.group(1);
        return instructionNames.split(",\\s*");
    }

    @NotNull
    private static String replaceContents(@NotNull String content)
    {
        Matcher matcher = INSTRUCTION_LINK_PATTERN.matcher(content);
        StringBuilder sb = new StringBuilder();
        while (matcher.find())
        {
            String instructionName = matcher.group(1);
            String link = JVMDocumentationLinkProvider.getDocumentationLink(instructionName);
            matcher.appendReplacement(sb, "<a href=\"" + link + "\">" + instructionName + "</a>");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
