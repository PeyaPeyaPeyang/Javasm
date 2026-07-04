package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.preprocessor.JALPreprocessorDirectiveUtil;
import tokyo.peya.javasm.intellij.langjal.parser.psi.refs.MacroReference;
import tokyo.peya.langjal.compiler.jvm.EOpcodes;

public class InstructionNameNode extends ANTLRPsiLeafNode {
    public InstructionNameNode(IElementType type, CharSequence text) {
        super(type, text);
    }

    @NotNull
    public String getInstructionName() {
        String text = this.getText();
        if (EOpcodes.findOpcode(text) >= 0)
            return text;

        String macroInstructionName = this.resolveMacroInstructionName();
        return macroInstructionName == null ? text : macroInstructionName;
    }

    public int getOpcode() {
        String name = this.getInstructionName();
        return EOpcodes.findOpcode(name);
    }

    public int getInstructionSize() {
        String macroValue = JALPreprocessorDirectiveUtil.resolveMacroValue(this);
        if (macroValue == null)
            return EOpcodes.getOpcodeSize(this.getOpcode());

        int size = 0;
        for (String token : JALPreprocessorDirectiveUtil.tokenizeMacroValue(macroValue)) {
            int opcode = EOpcodes.findOpcode(token);
            if (opcode >= 0)
                size += EOpcodes.getOpcodeSize(opcode);
        }

        return size == 0 ? EOpcodes.getOpcodeSize(this.getOpcode()) : size;
    }

    private @Nullable String resolveMacroInstructionName() {
        String macroValue = JALPreprocessorDirectiveUtil.resolveMacroValue(this);
        if (macroValue == null)
            return null;

        return JALPreprocessorDirectiveUtil.tokenizeMacroValue(macroValue)
                                           .stream()
                                           .filter(token -> EOpcodes.findOpcode(token) >= 0)
                                           .findFirst()
                                           .orElse(macroValue);
    }

    @Override
    public PsiReference getReference() {
        return MacroReference.createIfMacro(this);
    }

    @NotNull
    public String toString() {
        return "InstructionName(" + this.getInstructionName() + ")";
    }
}
