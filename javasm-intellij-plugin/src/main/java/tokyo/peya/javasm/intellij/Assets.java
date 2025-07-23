package tokyo.peya.javasm.intellij;

import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;
import javax.swing.UIManager;

public interface Assets
{
    Icon JAL = IconLoader.getIcon("/assets/icons/jal.svg", Assets.class);
    Icon STACK_VIEWER = IconLoader.getIcon("/assets/icons/stackViewer.svg", Assets.class);
}
