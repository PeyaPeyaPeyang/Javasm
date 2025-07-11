package tokyo.peya.javasm.intellij.editor;

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import com.intellij.util.PlatformIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALFile;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;
import tokyo.peya.javasm.intellij.langjal.parser.psi.clazz.ClassDefinitionNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodDefinitionNode;

public class JALStructureAwareNavbar extends StructureAwareNavBarModelExtension
{
    @Override
    protected @NotNull Language getLanguage()
    {
        return JALLanguage.INSTANCE;
    }

    @Override
    public @Nullable String getPresentableText(Object object)
    {
        return switch (object)
        {
            case JALFile file -> file.getName();
            case ClassDefinitionNode clazz -> clazz.getClassName();
            case MethodDefinitionNode method -> method.getMethodName();
            case InstructionSetNode instructionSet -> instructionSet.getName(); // InstructionSetNode の名前を返す
            case null, default -> null; // 他のオブジェクトにはアイコンを返さない
        };
    }

    @Override
    public @Nullable Icon getIcon(Object object)
    {
        return switch (object)
        {
            case JALFile file -> file.getIcon(0);
            case ClassDefinitionNode ignored -> PlatformIcons.CLASS_ICON;
            case MethodDefinitionNode ignored -> PlatformIcons.METHOD_ICON;
            case InstructionSetNode ignored -> PlatformIcons.XML_TAG_ICON;
            case null, default -> null; // 他のオブジェクトにはアイコンを返さない
        };
    }
}
