package tokyo.peya.plugin.javasm.compiler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.jetbrains.jps.incremental.CompileContext;
import org.jetbrains.jps.incremental.messages.CompilerMessage;

import java.nio.file.Path;

@Getter
@RequiredArgsConstructor
public class JALCompileErrorStrategy extends DefaultErrorStrategy
{
    private final CompileContext context;
    private final Path sourcePath;

    private boolean error;

    @Override
    public void reset(Parser recognizer)
    {
        super.reset(recognizer);
        this.error = false;
    }

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException
    {
        return super.recoverInline(recognizer);
    }

    @Override
    public void recover(Parser recognizer, RecognitionException e) throws RecognitionException
    {
        super.recover(recognizer, e);
    }

    @Override
    public void sync(Parser recognizer) throws RecognitionException
    {
        super.sync(recognizer);
    }

    @Override
    public boolean inErrorRecoveryMode(Parser recognizer)
    {
        return super.inErrorRecoveryMode(recognizer);
    }

    @Override
    public void reportMatch(Parser recognizer)
    {
        super.reportMatch(recognizer);
    }

    @Override
    public void reportError(Parser recognizer, RecognitionException e)
    {
        Token offendingToken = e.getOffendingToken();
        if (offendingToken == null)
        {
            this.context.processMessage(new CompilerMessage(
                    "JALCompiler",
                    CompilerMessage.Kind.ERROR,
                    "Unexpected error in " + this.sourcePath + ": " + e.getMessage(),
                    this.sourcePath.toString()
            ));
            this.error = true;
            return;
        }

        long problemBeginOffset = offendingToken.getStartIndex();
        long problemEndOffset = offendingToken.getStopIndex();
        long problemLocationOffset = problemBeginOffset;
        long locationLine = offendingToken.getLine();
        long locationColumn = offendingToken.getCharPositionInLine() + 1;

        this.context.processMessage(new CompilerMessage(
                "JALCompiler",
                CompilerMessage.Kind.ERROR,
                "Unexpected token found in " + this.sourcePath + ": " + e.getMessage(),
                this.sourcePath.toString(),
                problemBeginOffset,
                problemEndOffset,
                problemLocationOffset,
                locationLine,
                locationColumn
        ));

        this.error = true;

        super.reportError(recognizer, e);
    }
}
