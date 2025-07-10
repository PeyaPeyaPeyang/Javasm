package tokyo.peya.javasm.intellij.langjal.formatting;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class JALCodeStyleSettings extends CustomCodeStyleSettings
{
    protected JALCodeStyleSettings(@NotNull CodeStyleSettings container)
    {
        super("JALCodeStyleSettings", container);
    }
}
