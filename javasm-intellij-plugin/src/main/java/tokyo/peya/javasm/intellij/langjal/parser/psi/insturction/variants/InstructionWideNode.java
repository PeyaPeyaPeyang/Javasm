package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants;

import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;

public class InstructionWideNode extends ANTLRPsiLeafNode
{
    public InstructionWideNode(IElementType type)
    {
        super(type, "wide");
    }
}
