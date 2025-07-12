package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.langjal.compiler.jvm.EOpcodes;

public class InstructionNameNode extends ANTLRPsiLeafNode
{
    public InstructionNameNode(IElementType type, CharSequence text)
    {
        super(type, text);
    }

    @NotNull
    public String getInstructionName()
    {
        return this.getText();
    }

    public int getOpcode()
    {
        String name = this.getInstructionName();
        return EOpcodes.findOpcode(name);
    }

    public int getInstructionSize()
    {
        return EOpcodes.getOpcodeSize(this.getOpcode());
    }

    @NotNull
    public String toString()
    {
        return "InstructionName(" + this.getText() + ")";
    }
}
