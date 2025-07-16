package tokyo.peya.javasm.intellij.editor.debugger;

import com.intellij.debugger.engine.DebugProcessImpl;
import com.intellij.debugger.engine.JavaBreakpointHandler;
import org.jetbrains.annotations.NotNull;

public class JALLineBreakpointHandler extends JavaBreakpointHandler
{
    public JALLineBreakpointHandler(@NotNull DebugProcessImpl process)
    {
        super(JALLineBreakpointType.class, process);
    }
}
