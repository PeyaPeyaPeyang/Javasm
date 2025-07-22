package tokyo.peya.javasm.intellij.editor;

import com.intellij.codeInsight.highlighting.PairedBraceMatcherAdapter;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;

public class JALPairedBraceMatcherAdapter extends PairedBraceMatcherAdapter
{
    public JALPairedBraceMatcherAdapter()
    {
        super(new JALPairedBraceMatcher(), JALLanguage.INSTANCE);
    }
}
