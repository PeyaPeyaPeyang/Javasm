package tokyo.peya.javasm.intellij.jvm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccessLevel
{
    PUBLIC("public"),
    PROTECTED("protected"),
    PRIVATE("private"),
    PACKAGE_PRIVATE("package-private");

    private final String name;

    @Override
    public String toString()
    {
        return this.name;
    }
}
