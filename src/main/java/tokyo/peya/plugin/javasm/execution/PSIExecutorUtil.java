package tokyo.peya.plugin.javasm.execution;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.jvm.AccessAttribute;
import tokyo.peya.plugin.javasm.jvm.AccessAttributeSet;
import tokyo.peya.plugin.javasm.jvm.AccessLevel;
import tokyo.peya.plugin.javasm.langjal.JALFile;
import tokyo.peya.plugin.javasm.langjal.psi.JALClassBody;
import tokyo.peya.plugin.javasm.langjal.psi.JALClassDefinition;
import tokyo.peya.plugin.javasm.langjal.psi.JALMethodAccessor;
import tokyo.peya.plugin.javasm.langjal.psi.JALMethodDefinition;

@UtilityClass
public class PSIExecutorUtil
{
    public static JALClassDefinition findClassForFile(@NotNull JALFile file)
    {
        return PsiTreeUtil.findChildOfType(file, JALClassDefinition.class);
    }

    public static boolean hasMainMethod(@NotNull JALClassDefinition clazz)
    {
        JALClassBody classBody = clazz.getClassBody();
        if (classBody == null)
            return false; // クラスボディが存在しない場合はメインメソッドはない

        for (JALMethodDefinition method : classBody.getMethodDefinitionList())
            if (isMainMethod(method))
                return true;
        return false;
    }

    public static boolean isMainMethod(@NotNull JALMethodDefinition method)
    {
        AccessLevel accessLevel = JALMethodAccessor.getAccessLevel(method);
        AccessAttributeSet attributes = JALMethodAccessor.getAttributes(method);
        String name = JALMethodAccessor.getMethodName(method);
        if (name == null || !name.equals("main"))
            return false; // メインメソッドの名前は"main"でなければならない

        // メインメソッドの条件をチェック -> public static main(Ljava/lang/String;)V

        if (accessLevel != AccessLevel.PUBLIC)
            return false; // メインメソッドはpublicでなければならない
        else if (!attributes.has(AccessAttribute.STATIC))
            return false; // メインメソッドはstaticでなければならない

        return method.getMethodDescriptor().textMatches("([Ljava/lang/String;)V");
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
