package tokyo.peya.javasm.intellij.editor.debugger;

import com.intellij.debugger.ui.breakpoints.LineBreakpoint;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.breakpoints.XBreakpoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.java.debugger.breakpoints.properties.JavaLineBreakpointProperties;

public class JALLineBreakpoint extends LineBreakpoint<JavaLineBreakpointProperties>
{
    public JALLineBreakpoint(@NotNull Project project, @NotNull XBreakpoint xBreakpoint)
    {
        super(project, xBreakpoint);
    }

    @Override
    protected @NotNull JavaLineBreakpointProperties getProperties()
    {
        // XBreakpoint からとならないとだめ。（super#getProperties() は @NotNull であるから，ぬるぽする。
        XBreakpoint<JavaLineBreakpointProperties> xBreakpoint = super.getXBreakpoint();
        JavaLineBreakpointProperties properties = xBreakpoint.getProperties();
        if (properties == null)
            properties = new  JavaLineBreakpointProperties();
        return properties;
    }
}
