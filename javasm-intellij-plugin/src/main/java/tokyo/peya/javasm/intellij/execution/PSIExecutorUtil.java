package tokyo.peya.javasm.intellij.execution;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.jvm.AccessAttribute;
import tokyo.peya.javasm.intellij.jvm.AccessAttributeSet;
import tokyo.peya.javasm.intellij.jvm.AccessLevel;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

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

        return method.getMethodDescriptor().equals("([Ljava/lang/String;)V");
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
}
