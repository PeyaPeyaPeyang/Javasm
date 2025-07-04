package tokyo.peya.plugin.javasm.langjal;

import com.intellij.lang.Language;

public class JALLanguage extends Language
{
    public static final JALLanguage INSTANCE = new JALLanguage();

    public static final String ID = "JAL";

    private JALLanguage()
    {
        super(ID);
    }
}
