package tokyo.peya.plugin.javasm.execution;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.plugin.javasm.jvm.AccessAttribute;
import tokyo.peya.plugin.javasm.jvm.AccessAttributeSet;
import tokyo.peya.plugin.javasm.jvm.AccessLevel;
import tokyo.peya.plugin.javasm.jvm.MethodDescriptor;
import tokyo.peya.plugin.javasm.langjal.psi.JALClassBody;
import tokyo.peya.plugin.javasm.langjal.psi.JALClassDefinition;
import tokyo.peya.plugin.javasm.langjal.psi.JALMethodAccessor;
import tokyo.peya.plugin.javasm.langjal.psi.JALMethodDefinition;

import java.util.List;

public class PSIExecutorUtil
{
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

        return method.getMethodDescriptor().textMatches("(Ljava/lang/String;)V");
    }
}
