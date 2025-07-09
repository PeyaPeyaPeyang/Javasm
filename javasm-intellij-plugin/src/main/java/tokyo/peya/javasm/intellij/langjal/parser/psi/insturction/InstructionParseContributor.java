package tokyo.peya.javasm.intellij.langjal.parser.psi.insturction;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.RuleIElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionFieldAccessNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionIntIncrementNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionLookupSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.xswitch.InstructionTableSwitchNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.invokedynamic.InstructionInvokeDynamicNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionMultiANewArrayNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionScalarNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionWideNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionJumpNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionLocalAccessNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionNoArgumentNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionNumericArgumentNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.insturction.variants.InstructionTypeArgumentNode;
import tokyo.peya.javasm.langjal.compiler.JALLexer;
import tokyo.peya.javasm.langjal.compiler.JALParser;

public class InstructionParseContributor
{
    @NotNull
    public static PsiElement createInstructionElement(ASTNode node, RuleIElementType type)
    {
        if (!isInstructionRule(type.getRuleIndex()))
            throw new IllegalArgumentException("Not an instruction rule: " + type.getRuleIndex());
        return switch (type.getRuleIndex())
        {
            case JALParser.RULE_jvmInsAaload,
                 JALParser.RULE_jvmInsAastore,
                 JALParser.RULE_jvmInsAconstNull,
                 JALParser.RULE_jvmInsAloadN,
                 JALParser.RULE_jvmInsAreturn,
                 JALParser.RULE_jvmInsArraylength,
                 JALParser.RULE_jvmInsAthrow,
                 JALParser.RULE_jvmInsBaload,
                 JALParser.RULE_jvmInsBastore,
                 JALParser.RULE_jvmInsCaload,
                 JALParser.RULE_jvmInsCastore,
                 JALParser.RULE_jvmInsD2F,
                 JALParser.RULE_jvmInsD2I,
                 JALParser.RULE_jvmInsD2L,
                 JALParser.RULE_jvmInsDadd,
                 JALParser.RULE_jvmInsDaload,
                 JALParser.RULE_jvmInsDastore,
                 JALParser.RULE_jvmInsDcmpOP,
                 JALParser.RULE_jvmInsDconstN,
                 JALParser.RULE_jvmInsDdiv,
                 JALParser.RULE_jvmInsDloadN,
                 JALParser.RULE_jvmInsDmul,
                 JALParser.RULE_jvmInsDneg,
                 JALParser.RULE_jvmInsDrem,
                 JALParser.RULE_jvmInsDreturn,
                 JALParser.RULE_jvmInsDstoreN,
                 JALParser.RULE_jvmInsDsub,
                 JALParser.RULE_jvmInsDup,
                 JALParser.RULE_jvmInsDupX1,
                 JALParser.RULE_jvmInsDupX2,
                 JALParser.RULE_jvmInsDup2,
                 JALParser.RULE_jvmInsDup2X1,
                 JALParser.RULE_jvmInsDup2X2,
                 JALParser.RULE_jvmInsF2D,
                 JALParser.RULE_jvmInsF2I,
                 JALParser.RULE_jvmInsF2L,
                 JALParser.RULE_jvmInsFadd,
                 JALParser.RULE_jvmInsFaload,
                 JALParser.RULE_jvmInsFastore,
                 JALParser.RULE_jvmInsFcmpOP,
                 JALParser.RULE_jvmInsFconstN,
                 JALParser.RULE_jvmInsFdiv,
                 JALParser.RULE_jvmInsFloadN,
                 JALParser.RULE_jvmInsFmul,
                 JALParser.RULE_jvmInsFneg,
                 JALParser.RULE_jvmInsFrem,
                 JALParser.RULE_jvmInsFreturn,
                 JALParser.RULE_jvmInsFstoreN,
                 JALParser.RULE_jvmInsFsub,
                 JALParser.RULE_jvmInsI2B,
                 JALParser.RULE_jvmInsI2C,
                 JALParser.RULE_jvmInsI2D,
                 JALParser.RULE_jvmInsI2F,
                 JALParser.RULE_jvmInsI2L,
                 JALParser.RULE_jvmInsI2S,
                 JALParser.RULE_jvmInsIadd,
                 JALParser.RULE_jvmInsIaload,
                 JALParser.RULE_jvmInsIand,
                 JALParser.RULE_jvmInsIastore,
                 JALParser.RULE_jvmInsIconstN,
                 JALParser.RULE_jvmInsIdiv,
                 JALParser.RULE_jvmInsIloadN,
                 JALParser.RULE_jvmInsImul,
                 JALParser.RULE_jvmInsIneg,
                 JALParser.RULE_jvmInsIor,
                 JALParser.RULE_jvmInsIrem,
                 JALParser.RULE_jvmInsIreturn,
                 JALParser.RULE_jvmInsIshl,
                 JALParser.RULE_jvmInsIshr,
                 JALParser.RULE_jvmInsIstoreN,
                 JALParser.RULE_jvmInsIsub,
                 JALParser.RULE_jvmInsIxor,
                 JALParser.RULE_jvmInsL2D,
                 JALParser.RULE_jvmInsL2F,
                 JALParser.RULE_jvmInsL2I,
                 JALParser.RULE_jvmInsLadd,
                 JALParser.RULE_jvmInsLaload,
                 JALParser.RULE_jvmInsLand,
                 JALParser.RULE_jvmInsLastore,
                 JALParser.RULE_jvmInsLcmp,
                 JALParser.RULE_jvmInsLconstN,
                 JALParser.RULE_jvmInsLdiv,
                 JALParser.RULE_jvmInsLloadN,
                 JALParser.RULE_jvmInsLmul,
                 JALParser.RULE_jvmInsLneg,
                 JALParser.RULE_jvmInsLor,
                 JALParser.RULE_jvmInsLrem,
                 JALParser.RULE_jvmInsLreturn,
                 JALParser.RULE_jvmInsLshl,
                 JALParser.RULE_jvmInsLshr,
                 JALParser.RULE_jvmInsLstoreN,
                 JALParser.RULE_jvmInsLsub,
                 JALParser.RULE_jvmInsLushr,
                 JALParser.RULE_jvmInsLxor,
                 JALParser.RULE_jvmInsMonitorenter,
                 JALParser.RULE_jvmInsMonitorexit,
                 JALParser.RULE_jvmInsNop,
                 JALParser.RULE_jvmInsPop,
                 JALParser.RULE_jvmInsPop2,
                 JALParser.RULE_jvmInsReturn,
                 JALParser.RULE_jvmInsSaload,
                 JALParser.RULE_jvmInsSastore,
                 JALParser.RULE_jvmInsSwap -> new InstructionNoArgumentNode(node);
            case JALParser.RULE_jvmInsBipush,
                 JALParser.RULE_jvmInsSipush -> new InstructionNumericArgumentNode(node);
            case JALParser.RULE_jvmInsAload,
                 JALParser.RULE_jvmInsAstore,
                 JALParser.RULE_jvmInsDload,
                 JALParser.RULE_jvmInsDstore,
                 JALParser.RULE_jvmInsFload,
                 JALParser.RULE_jvmInsFstore,
                 JALParser.RULE_jvmInsIload,
                 JALParser.RULE_jvmInsIstore,
                 JALParser.RULE_jvmInsLload,
                 JALParser.RULE_jvmInsLstore,
                 JALParser.RULE_jvmInsRet -> new InstructionLocalAccessNode(node);
            case JALParser.RULE_jvmInsAnewArray,
                 JALParser.RULE_jvmInsCheckcast,
                 JALParser.RULE_jvmInsInstanceof,
                 JALParser.RULE_jvmInsNew,
                 JALParser.RULE_jvmInsNewarray -> new InstructionTypeArgumentNode(node);
            case JALParser.RULE_jvmInsGoto,
                 JALParser.RULE_jvmInsGotoW,
                 JALParser.RULE_jvmInsIfAcmpOP,
                 JALParser.RULE_jvmInsIfIcmpOP,
                 JALParser.RULE_jvmInsIfOP,
                 JALParser.RULE_jvmInsIfNonnull,
                 JALParser.RULE_jvmInsIfNull,
                 JALParser.RULE_jvmInsJsr,
                 JALParser.RULE_jvmInsJsrW -> new InstructionJumpNode(node);
            case JALParser.RULE_jvmInsGetfield,
                    JALParser.RULE_jvmInsGetstatic,
                    JALParser.RULE_jvmInsPutfield,
                    JALParser.RULE_jvmInsPutstatic -> new InstructionFieldAccessNode(node);
            case JALParser.RULE_jvmInsIinc -> new InstructionIntIncrementNode(node);
            case JALParser.RULE_jvmInsLdc,
                 JALParser.RULE_jvmInsLdcW,
                 JALParser.RULE_jvmInsLdc2W -> new InstructionScalarNode(node);
            case JALParser.RULE_jvmInsMultianewarray -> new InstructionMultiANewArrayNode(node);
            case JALParser.RULE_jvmInsInvokedynamic -> new InstructionInvokeDynamicNode(node);
            case JALParser.RULE_jvmInsTableswitch -> new InstructionTableSwitchNode(node);
            case JALParser.RULE_jvmInsLookupswitch -> new InstructionLookupSwitchNode(node);
            default -> new InstructionNode(node);
        };
    }

    public static boolean isInstructionRule(int ruleType)
    {
        // WIDE ではない理由は，WIDE は通常の命令ではなく，ある命令の前に置くいわば修飾子のようなものであるから。
        return JALParser.RULE_jvmInsAaload <= ruleType && ruleType <= JALParser.RULE_jvmInsTableswitch;
    }

    @NotNull
    public static LeafElement createInstructionNameLeaf(@NotNull IElementType type, @NotNull CharSequence text)
    {
        if (!(type instanceof TokenIElementType token))
            throw new IllegalArgumentException("Not a token type: " + type);

        return switch(token.getANTLRTokenType()) {
            case JALLexer.INSN_WIDE -> new InstructionWideNode(type);
            default -> new InstructionNameNode(type, text);
        };
    }


    public static boolean isInstructionNameToken(int tokenType)
    {
        return JALLexer.INSN_AALOAD <= tokenType && tokenType <= JALLexer.INSN_WIDE;
    }
}
