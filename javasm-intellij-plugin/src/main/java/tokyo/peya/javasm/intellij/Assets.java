package tokyo.peya.javasm.intellij;

import com.intellij.idea.StartupUtil;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.StartupUiUtil;
import javax.swing.Icon;
import javax.swing.UIManager;

public class Assets
{
    public static final Icon JAL_ICON =
            (UIManager.getLookAndFeelDefaults() == null || UIManager.getLookAndFeelDefaults().getBoolean("ui.theme.isd.dark"))
                    ? IconLoader.getIcon("/assets/icons/jal_dark.png", Assets.class):
                    IconLoader.getIcon("/assets/icons/jal.png", Assets.class);
}
