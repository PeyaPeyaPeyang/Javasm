package tokyo.peya.javasm.intellij.execution;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;
import tokyo.peya.javasm.langjal.compiler.jvm.AccessAttribute;
import tokyo.peya.javasm.langjal.compiler.jvm.AccessAttributeSet;
import tokyo.peya.javasm.langjal.compiler.jvm.AccessLevel;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;

@UtilityClass
public class PSIExecutorUtil
{
    public static boolean hasMainMethod(@NotNull JALFile jalFile)
    {
        ClassDefinitionNode classDefinition = PsiTreeUtil.findChildOfType(jalFile, ClassDefinitionNode.class);
        if (classDefinition == null)
            return false; // クラス定義が見つからない場合はメインメソッドがない

        return hasMainMethod(classDefinition);
    }

    public static boolean hasMainMethod(@NotNull ClassDefinitionNode classDefinition)
    {
        for (MethodDefinitionNode method : classDefinition.getMethods())
        {
            if (isMainMethod(method))
                return true; // メインメソッドが見つかった
        }

        return false; // メインメソッドが見つからなかった
    }

    public static boolean isAccessibleClass(@NotNull ClassDefinitionNode clazz)
    {
        AccessLevel accessLevel = clazz.getAccessLevel();
        AccessAttributeSet attributes = clazz.getAccessAttributes();

        return accessLevel == AccessLevel.PUBLIC && attributes.isNormalClass();
    }

    public static boolean isMainMethod(@NotNull MethodDefinitionNode method)
    {
        AccessLevel accessLevel = method.getAccessLevel();
        AccessAttributeSet attributes = method.getAccessAttributes();
        String name = method.getMethodName();
        if (!name.equals("main"))
            return false; // メインメソッドの名前は"main"でなければならない

        // メインメソッドの条件をチェック -> public static main(Ljava/lang/String;)V
        if (accessLevel != AccessLevel.PUBLIC)
            return false; // メインメソッドはpublicでなければならない
        else if (!attributes.has(AccessAttribute.STATIC))
            return false; // メインメソッドはstaticでなければならない

        try
        {
            return method.getMethodDescriptor().equals("([Ljava/lang/String;)V");
        }
        catch (IllegalArgumentException e)
        {
            return false; // メソッドのシグネチャが無効な場合はメインメソッドではない
        }
    }

    public static boolean isInSourceRoot(@NotNull PsiElement element)
    {
        Module module = ModuleUtilCore.findModuleForPsiElement(element);
        if (module == null)
            return false; // モジュールが見つからない場合はソースルートではない

        VirtualFile file = element.getContainingFile().getVirtualFile();
        if (file == null)
            return false; // ファイルが見つからない場合はソースルートではない

        VirtualFile[] sourceRoots = ModuleRootManager.getInstance(module).getSourceRoots(false);
        for (VirtualFile sourceRoot : sourceRoots)
            if (file.getPath().startsWith(sourceRoot.getPath()))
                return true; // ファイルがソースルートのパスで始まる場合はソースルート内にある

        return false;
    }

    @Nullable
    public static ClassNameValidationResult validateClassName(@NotNull ClassDefinitionNode clazz)
    {
        JALFile file = (JALFile) clazz.getContainingFile();
        Project project = file.getProject();
        ProjectFileIndex fileIndex = ProjectFileIndex.getInstance(project);

        VirtualFile sourceRoot = fileIndex.getSourceRootForFile(file.getVirtualFile());
        if (sourceRoot == null)
            return null;

        String className = clazz.getClassName();
        String packageName = clazz.getPackageName();

        // ここで，実際のソースルート内のパスと，パッケージが一致するかを見る。
        // 一致しなかったらエラーを出す。
        Path sourceRootPath = sourceRoot.toNioPath();
        Path expectedPath;
        try
        {
            expectedPath = sourceRootPath.resolve(packageName).resolve(className + ".jal");
        }
        catch (InvalidPathException e)
        {
            return new ClassNameValidationResult(
                    true,
                    sourceRootPath.resolve(className + ".jal"),
                    sourceRootPath.relativize(file.getVirtualFile().toNioPath())
            );
        }

        Path relativePath = sourceRootPath.relativize(file.getVirtualFile().toNioPath());
        VirtualFile expectedFile = sourceRoot.getFileSystem().findFileByPath(expectedPath.toString());
        return new ClassNameValidationResult(
                expectedFile != null && expectedFile.isValid(),
                expectedPath,
                relativePath
        );
    }

    public record ClassNameValidationResult(
            boolean isValid,
            @NotNull
            Path expectedPath,
            @NotNull
            Path relativePathFromSourceRoot
    ) {}
}
