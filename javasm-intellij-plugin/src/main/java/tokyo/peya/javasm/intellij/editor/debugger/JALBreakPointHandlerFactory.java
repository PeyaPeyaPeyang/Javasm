package tokyo.peya.javasm.intellij.editor.debugger;

import com.intellij.debugger.engine.DebugProcessImpl;
import com.intellij.debugger.engine.JavaBreakpointHandler;
import com.intellij.debugger.engine.JavaBreakpointHandlerFactory;

public class JALBreakPointHandlerFactory implements JavaBreakpointHandlerFactory
{
    @Override
    public JavaBreakpointHandler createHandler(DebugProcessImpl debugProcess)
    {
        return new JALLineBreakpointHandler(debugProcess);
    }
}
