package tokyo.peya.javasm.intellij.langjal.parser;

import com.intellij.lexer.FlexAdapter;

public class JALLexerAdapter extends FlexAdapter
{
    public JALLexerAdapter()
    {
        super(new JALLexer(null));
    }
}
