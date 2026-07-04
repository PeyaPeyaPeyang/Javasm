package tokyo.peya.javasm.intellij.langjal.preprocessor;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class JALPreprocessorDirectiveUtil {
    private JALPreprocessorDirectiveUtil() {
    }

    public record DefineDirective(@NotNull TextRange range,
                                  @NotNull TextRange keywordRange,
                                  @Nullable TextRange macroNameRange,
                                  @Nullable TextRange valueRange,
                                  @NotNull String macroName,
                                  @NotNull String value) {
        public boolean contains(int offset) {
            return range.containsOffset(offset);
        }
    }

    public static @NotNull List<DefineDirective> findDefineDirectives(@NotNull String text) {
        List<DefineDirective> directives = new ArrayList<>();

        int lineStart = 0;
        while (lineStart < text.length()) {
            int lineEnd = findLineEnd(text, lineStart);
            int nextLineStart = nextLineStart(text, lineEnd);
            int directiveStart = skipHorizontalSpaces(text, lineStart, lineEnd);

            if (directiveStart < lineEnd && text.charAt(directiveStart) == '#') {
                int directiveEnd = lineEnd;
                int continuationLineStart = nextLineStart;
                while (hasLineContinuation(text, lineStart, directiveEnd)
                        && continuationLineStart < text.length()) {
                    directiveEnd = findLineEnd(text, continuationLineStart);
                    continuationLineStart = nextLineStart(text, directiveEnd);
                }

                DefineDirective directive = parseDefineDirective(text, directiveStart, directiveEnd);
                if (directive != null)
                    directives.add(directive);

                lineStart = continuationLineStart;
            } else {
                lineStart = nextLineStart;
            }
        }

        return directives;
    }

    public static @Nullable DefineDirective findDefineDirectiveAt(@NotNull String text, int offset) {
        for (DefineDirective directive : findDefineDirectives(text)) {
            if (directive.contains(offset))
                return directive;
        }

        return null;
    }

    public static @Nullable String resolveMacroValueAt(@NotNull String text,
                                                       int offset,
                                                       @NotNull String macroName) {
        DefineDirective directive = findActiveDefineDirectiveAt(text, offset, macroName);
        if (directive == null)
            return null;

        return normalizeMacroValue(directive.value());
    }

    public static @Nullable DefineDirective findActiveDefineDirectiveAt(@NotNull String text,
                                                                        int offset,
                                                                        @NotNull String macroName) {
        DefineDirective resolved = null;
        for (DefineDirective directive : findDefineDirectives(text)) {
            if (directive.range().getStartOffset() >= offset)
                break;
            if (macroName.equals(directive.macroName()))
                resolved = directive;
        }

        return resolved;
    }

    public static @Nullable String resolveMacroValue(@NotNull PsiElement element) {
        if (element.getContainingFile() == null)
            return null;

        return resolveMacroValueAt(
                element.getContainingFile().getText(),
                element.getTextRange().getStartOffset(),
                element.getText()
        );
    }

    public static @NotNull String normalizeMacroValue(@NotNull String value) {
        return value.replace("\\\r\n", " ")
                    .replace("\\\n", " ")
                    .replace("\\\r", " ")
                    .trim();
    }

    public static @NotNull List<String> tokenizeMacroValue(@NotNull String value) {
        String normalized = normalizeMacroValue(value);
        if (normalized.isEmpty())
            return List.of();

        List<String> tokens = new ArrayList<>();
        int pos = 0;
        while (pos < normalized.length()) {
            while (pos < normalized.length() && Character.isWhitespace(normalized.charAt(pos)))
                pos++;
            if (pos >= normalized.length())
                break;

            int start = pos;
            char quote = 0;
            while (pos < normalized.length()) {
                char c = normalized.charAt(pos);
                if (quote != 0) {
                    if (c == '\\' && pos + 1 < normalized.length()) {
                        pos += 2;
                        continue;
                    }
                    pos++;
                    if (c == quote)
                        quote = 0;
                    continue;
                }

                if (c == '"' || c == '\'') {
                    quote = c;
                    pos++;
                    continue;
                }
                if (Character.isWhitespace(c))
                    break;
                pos++;
            }
            tokens.add(normalized.substring(start, pos));
        }

        return tokens;
    }

    private static @Nullable DefineDirective parseDefineDirective(@NotNull String text, int directiveStart, int directiveEnd) {
        int pos = directiveStart + 1;
        pos = skipHorizontalSpaces(text, pos, directiveEnd);

        int keywordStart = pos;
        int keywordEnd = readIdentifierEnd(text, keywordStart, directiveEnd);
        if (keywordStart == keywordEnd || !"define".contentEquals(text.subSequence(keywordStart, keywordEnd)))
            return null;

        pos = skipHorizontalSpaces(text, keywordEnd, directiveEnd);

        int macroNameStart = pos;
        int macroNameEnd = readIdentifierEnd(text, macroNameStart, directiveEnd);
        TextRange macroNameRange = null;
        String macroName = "";
        if (macroNameEnd > macroNameStart) {
            macroNameRange = TextRange.create(macroNameStart, macroNameEnd);
            macroName = text.substring(macroNameStart, macroNameEnd);
        }

        pos = skipHorizontalSpaces(text, macroNameEnd, directiveEnd);
        TextRange valueRange = pos < directiveEnd ? TextRange.create(pos, directiveEnd) : null;
        String value = valueRange == null ? "" : text.substring(valueRange.getStartOffset(), valueRange.getEndOffset());

        return new DefineDirective(
                TextRange.create(directiveStart, directiveEnd),
                TextRange.create(directiveStart, keywordEnd),
                macroNameRange,
                valueRange,
                macroName,
                value
        );
    }

    private static int findLineEnd(@NotNull String text, int start) {
        int pos = start;
        while (pos < text.length()) {
            char c = text.charAt(pos);
            if (c == '\r' || c == '\n')
                break;
            pos++;
        }
        return pos;
    }

    private static int nextLineStart(@NotNull String text, int lineEnd) {
        if (lineEnd >= text.length())
            return lineEnd;
        if (text.charAt(lineEnd) == '\r'
                && lineEnd + 1 < text.length()
                && text.charAt(lineEnd + 1) == '\n')
            return lineEnd + 2;
        return lineEnd + 1;
    }

    private static int skipHorizontalSpaces(@NotNull String text, int start, int end) {
        int pos = start;
        while (pos < end) {
            char c = text.charAt(pos);
            if (c != ' ' && c != '\t')
                break;
            pos++;
        }
        return pos;
    }

    private static int readIdentifierEnd(@NotNull String text, int start, int end) {
        if (start >= end || !isIdentifierStart(text.charAt(start)))
            return start;

        int pos = start + 1;
        while (pos < end && isIdentifierPart(text.charAt(pos)))
            pos++;
        return pos;
    }

    private static boolean hasLineContinuation(@NotNull String text, int lineStart, int lineEnd) {
        int pos = lineEnd - 1;
        while (pos >= lineStart) {
            char c = text.charAt(pos);
            if (c != ' ' && c != '\t')
                break;
            pos--;
        }

        return pos >= lineStart && text.charAt(pos) == '\\';
    }

    private static boolean isIdentifierStart(char c) {
        return c == '_' || c == '$' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean isIdentifierPart(char c) {
        return isIdentifierStart(c) || (c >= '0' && c <= '9');
    }
}
