package tokyo.peya.plugin.javasm.langjal.psi;

import com.intellij.psi.tree.IElementType;
import tokyo.peya.plugin.javasm.langjal.JALLanguage;

public class JALTokenType extends IElementType
{
    public JALTokenType(String debugName)
    {
        super(debugName, JALLanguage.INSTANCE);
    }

    @Override
    public String toString()
    {
        return "JalTokenType." + super.toString();
    }
}
