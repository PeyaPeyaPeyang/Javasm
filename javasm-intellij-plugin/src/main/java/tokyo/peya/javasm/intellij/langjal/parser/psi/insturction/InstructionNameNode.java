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

    public int getInstructionSize()
    {
        String name = this.getInstructionName();
        int opcode = EOpcodes.findOpcode(name);
        if (opcode == -1)
            return 0;

        return EOpcodes.getOpcodeSize(opcode);
    }

    @NotNull
    public String toString()
    {
        return "InstructionName(" + this.getText() + ")";
    }
}
