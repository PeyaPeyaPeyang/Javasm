package tokyo.peya.javasm.intellij.editor.debugger;

import com.intellij.debugger.PositionManager;
import com.intellij.debugger.PositionManagerFactory;
import com.intellij.debugger.engine.DebugProcess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JALPositionManagerFactory extends PositionManagerFactory
{
    @Override
    public @Nullable PositionManager createPositionManager(@NotNull DebugProcess debugProcess)
    {
        return new JALPositionManager(debugProcess);
    }
}
