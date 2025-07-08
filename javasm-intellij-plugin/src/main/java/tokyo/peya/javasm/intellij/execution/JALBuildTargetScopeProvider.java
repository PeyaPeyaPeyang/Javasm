package tokyo.peya.javasm.intellij.execution;

import com.intellij.compiler.impl.BuildTargetScopeProvider;
import com.intellij.openapi.compiler.CompileScope;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.api.CmdlineProtoUtil;
import org.jetbrains.jps.api.CmdlineRemoteProto.Message.ControllerMessage.ParametersMessage.TargetTypeBuildScope;
import tokyo.peya.javasm.intellij.builder.JALBuildTargetType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JALBuildTargetScopeProvider extends BuildTargetScopeProvider
{
    @Override
    public @NotNull List<TargetTypeBuildScope> getBuildTargetScopes(
            @NotNull CompileScope baseScope,
            @NotNull Project project,
            boolean forceBuild)
    {
        List<TargetTypeBuildScope> scopes = new ArrayList<>();
        scopes.add(
                CmdlineProtoUtil.createTargetsScope(
                        JALBuildTargetType.TYPE_ID,
                        Arrays.stream(ModuleManager.getInstance(project).getModules())
                              .map(Module::getName)
                              .toList(),
                        forceBuild
                )
        );

        return scopes;
    }
}
