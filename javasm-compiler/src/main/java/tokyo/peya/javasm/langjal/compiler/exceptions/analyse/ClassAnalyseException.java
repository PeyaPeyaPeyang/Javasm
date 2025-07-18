package tokyo.peya.javasm.langjal.compiler.exceptions.analyse;

import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.exceptions.CompileErrorException;

public class ClassAnalyseException extends CompileErrorException
{
    public ClassAnalyseException(@NotNull String message)
    {
        super(message, 0, 0, 0);
    }
}
