package tokyo.peya.plugin.javasm.langjal.psi;

import com.intellij.psi.tree.IElementType;
import tokyo.peya.plugin.javasm.langjal.JALLanguage;

public class JALElementType extends IElementType
{
    public JALElementType(String debugName)
    {
        super(debugName, JALLanguage.INSTANCE);
    }

    @Override
    public String toString()
    {
        return "JalElementType." + super.toString();
    }
}
