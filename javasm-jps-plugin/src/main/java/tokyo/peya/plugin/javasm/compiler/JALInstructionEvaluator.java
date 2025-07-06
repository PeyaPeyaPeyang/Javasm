package tokyo.peya.plugin.javasm.compiler;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorANewArray;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorBiPush;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorCheckCast;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorGoto;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorGotoW;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorInstanceOf;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorJsr;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorJsrW;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorMonitorEnter;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorMonitorExit;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorMultiANewArray;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorNew;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorPop;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorPop2;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorRet;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorReturn;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorSiPush;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xand.InstructionEvaluatorIAnd;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xand.InstructionEvaluatorLAnd;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv.InstructionEvaluatorDDiv;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv.InstructionEvaluatorFDiv;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv.InstructionEvaluatorIDiv;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xdiv.InstructionEvaluatorLDiv;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul.InstructionEvaluatorDMul;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul.InstructionEvaluatorFMul;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul.InstructionEvaluatorIMul;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xmul.InstructionEvaluatorLMul;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg.InstructionEvaluatorDNeg;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg.InstructionEvaluatorFNeg;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg.InstructionEvaluatorINeg;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xneg.InstructionEvaluatorLNeg;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xor.InstructionEvaluatorIOr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xor.InstructionEvaluatorLOr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem.InstructionEvaluatorDRem;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem.InstructionEvaluatorFRem;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem.InstructionEvaluatorIRem;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xrem.InstructionEvaluatorLRem;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xshl.InstructionEvaluatorIShl;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xshl.InstructionEvaluatorLShl;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xshr.InstructionEvaluatorIShr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xshr.InstructionEvaluatorLShr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub.InstructionEvaluatorDSub;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub.InstructionEvaluatorFSub;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub.InstructionEvaluatorISub;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xsub.InstructionEvaluatorLSub;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xushr.InstructionEvaluatorIUShr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xushr.InstructionEvaluatorLUShr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xxor.InstructionEvaluatorIXOr;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xxor.InstructionEvaluatorLXOr;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorD2F;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorD2I;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorD2L;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorF2D;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorF2I;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorF2L;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorI2B;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorI2C;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorI2D;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorI2F;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorI2L;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorI2S;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorL2D;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorL2F;
import tokyo.peya.plugin.javasm.compiler.instructions.cast.InstructionEvaluatorL2I;
import tokyo.peya.plugin.javasm.compiler.instructions.dup.InstructionEvaluatorDup;
import tokyo.peya.plugin.javasm.compiler.instructions.dup.InstructionEvaluatorDup2;
import tokyo.peya.plugin.javasm.compiler.instructions.dup.InstructionEvaluatorDup2X1;
import tokyo.peya.plugin.javasm.compiler.instructions.dup.InstructionEvaluatorDup2X2;
import tokyo.peya.plugin.javasm.compiler.instructions.dup.InstructionEvaluatorDupX1;
import tokyo.peya.plugin.javasm.compiler.instructions.dup.InstructionEvaluatorDupX2;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd.InstructionEvaluatorDAdd;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd.InstructionEvaluatorFAdd;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd.InstructionEvaluatorIAdd;
import tokyo.peya.plugin.javasm.compiler.instructions.calc.xadd.InstructionEvaluatorLAdd;
import tokyo.peya.plugin.javasm.compiler.instructions.field.InstructionEvaluatorGetField;
import tokyo.peya.plugin.javasm.compiler.instructions.field.InstructionEvaluatorGetStatic;
import tokyo.peya.plugin.javasm.compiler.instructions.field.InstructionEvaluatorPutField;
import tokyo.peya.plugin.javasm.compiler.instructions.field.InstructionEvaluatorPutStatic;
import tokyo.peya.plugin.javasm.compiler.instructions.ifx.InstructionEvaluatorIfICmpOP;
import tokyo.peya.plugin.javasm.compiler.instructions.ifx.InstructionEvaluatorIfNonNull;
import tokyo.peya.plugin.javasm.compiler.instructions.ifx.InstructionEvaluatorIfNull;
import tokyo.peya.plugin.javasm.compiler.instructions.ifx.InstructionEvaluatorIfOP;
import tokyo.peya.plugin.javasm.compiler.instructions.invokex.InstructionEvaluatorInvokeSpecial;
import tokyo.peya.plugin.javasm.compiler.instructions.invokex.InstructionEvaluatorInvokeStatic;
import tokyo.peya.plugin.javasm.compiler.instructions.invokex.InstructionEvaluatorInvokeVirtual;
import tokyo.peya.plugin.javasm.compiler.instructions.ldc.InstructionEvaluatorLDC;
import tokyo.peya.plugin.javasm.compiler.instructions.ldc.InstructionEvaluatorLDCW;
import tokyo.peya.plugin.javasm.compiler.instructions.ldc.InstructionEvaluatorLDCW2;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorAAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorBAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorCAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorDAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorFAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorIAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorLAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xastore.InstructionEvaluatorSAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xcmp_op.InstructionEvaluatorDCmpOp;
import tokyo.peya.plugin.javasm.compiler.instructions.xcmp_op.InstructionEvaluatorFCmpOp;
import tokyo.peya.plugin.javasm.compiler.instructions.xconst.InstructionEvaluatorAConstNull;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorAALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorBALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorCALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorDALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorFALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorIALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorLALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xaload.InstructionEvaluatorSALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xconst.InstructionEvaluatorDConstN;
import tokyo.peya.plugin.javasm.compiler.instructions.xconst.InstructionEvaluatorFConstN;
import tokyo.peya.plugin.javasm.compiler.instructions.xconst.InstructionEvaluatorIConstN;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorALoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorALoadN;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorDLoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorDLoadN;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorFLoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorFLoadN;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorILoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorILoadN;
import tokyo.peya.plugin.javasm.compiler.instructions.xconst.InstructionEvaluatorLConstN;
import tokyo.peya.plugin.javasm.compiler.instructions.InstructionEvaluatorNop;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorLLoad;
import tokyo.peya.plugin.javasm.compiler.instructions.xload.InstructionEvaluatorLLoadN;
import tokyo.peya.plugin.javasm.compiler.instructions.xreturn.InstructionEvaluatorAReturn;
import tokyo.peya.plugin.javasm.compiler.instructions.xreturn.InstructionEvaluatorDReturn;
import tokyo.peya.plugin.javasm.compiler.instructions.xreturn.InstructionEvaluatorFReturn;
import tokyo.peya.plugin.javasm.compiler.instructions.xreturn.InstructionEvaluatorIReturn;
import tokyo.peya.plugin.javasm.compiler.instructions.xreturn.InstructionEvaluatorLReturn;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorAStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorAStoreN;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorDStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorDStoreN;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorFStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorFStoreN;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorIStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorIStoreN;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorLStore;
import tokyo.peya.plugin.javasm.compiler.instructions.xstore.InstructionEvaluatorLStoreN;
import tokyo.peya.plugin.javasm.langjal.compiler.JALParser;

import java.util.List;

public class JALInstructionEvaluator
{
    private static final List<AbstractInstructionEvaluator<?>> EVALUATORS = List.of(
            // ---- カテゴリ 1 ----
            new InstructionEvaluatorNop(),
            new InstructionEvaluatorAConstNull(),

            new InstructionEvaluatorIConstN(),
            new InstructionEvaluatorLConstN(),
            new InstructionEvaluatorFConstN(),
            new InstructionEvaluatorDConstN(),

            new InstructionEvaluatorILoadN(),
            new InstructionEvaluatorLLoadN(),
            new InstructionEvaluatorFLoadN(),
            new InstructionEvaluatorDLoadN(),
            new InstructionEvaluatorALoadN(),

            new InstructionEvaluatorIALoad(),
            new InstructionEvaluatorLALoad(),
            new InstructionEvaluatorFALoad(),
            new InstructionEvaluatorDALoad(),
            new InstructionEvaluatorAALoad(),
            new InstructionEvaluatorBALoad(),
            new InstructionEvaluatorCALoad(),
            new InstructionEvaluatorSALoad(),

            new InstructionEvaluatorIStoreN(),
            new InstructionEvaluatorLStoreN(),
            new InstructionEvaluatorFStoreN(),
            new InstructionEvaluatorDStoreN(),
            new InstructionEvaluatorAStoreN(),

            new InstructionEvaluatorIAStore(),
            new InstructionEvaluatorLAStore(),
            new InstructionEvaluatorFAStore(),
            new InstructionEvaluatorDAStore(),
            new InstructionEvaluatorAAStore(),
            new InstructionEvaluatorBAStore(),
            new InstructionEvaluatorCAStore(),
            new InstructionEvaluatorSAStore(),

            new InstructionEvaluatorPop(),
            new InstructionEvaluatorPop2(),

            new InstructionEvaluatorDup(),
            new InstructionEvaluatorDupX1(),
            new InstructionEvaluatorDupX2(),
            new InstructionEvaluatorDup2(),
            new InstructionEvaluatorDup2X1(),
            new InstructionEvaluatorDup2X2(),

            new InstructionEvaluatorIAdd(),
            new InstructionEvaluatorLAdd(),
            new InstructionEvaluatorFAdd(),
            new InstructionEvaluatorDAdd(),

            new InstructionEvaluatorISub(),
            new InstructionEvaluatorLSub(),
            new InstructionEvaluatorFSub(),
            new InstructionEvaluatorDSub(),

            new InstructionEvaluatorIMul(),
            new InstructionEvaluatorLMul(),
            new InstructionEvaluatorFMul(),
            new InstructionEvaluatorDMul(),

            new InstructionEvaluatorIDiv(),
            new InstructionEvaluatorLDiv(),
            new InstructionEvaluatorFDiv(),
            new InstructionEvaluatorDDiv(),

            new InstructionEvaluatorIRem(),
            new InstructionEvaluatorLRem(),
            new InstructionEvaluatorFRem(),
            new InstructionEvaluatorDRem(),

            new InstructionEvaluatorINeg(),
            new InstructionEvaluatorLNeg(),
            new InstructionEvaluatorFNeg(),
            new InstructionEvaluatorDNeg(),

            new InstructionEvaluatorIShl(),
            new InstructionEvaluatorLShl(),

            new InstructionEvaluatorIShr(),
            new InstructionEvaluatorLShr(),

            new InstructionEvaluatorIUShr(),
            new InstructionEvaluatorLUShr(),

            new InstructionEvaluatorIAnd(),
            new InstructionEvaluatorLAnd(),

            new InstructionEvaluatorIOr(),
            new InstructionEvaluatorLOr(),

            new InstructionEvaluatorIXOr(),
            new InstructionEvaluatorLXOr(),

            new InstructionEvaluatorI2F(),
            new InstructionEvaluatorI2L(),
            new InstructionEvaluatorI2D(),

            new InstructionEvaluatorL2I(),
            new InstructionEvaluatorL2F(),
            new InstructionEvaluatorL2D(),

            new InstructionEvaluatorF2I(),
            new InstructionEvaluatorF2L(),
            new InstructionEvaluatorF2D(),

            new InstructionEvaluatorD2I(),
            new InstructionEvaluatorD2L(),
            new InstructionEvaluatorD2F(),

            new InstructionEvaluatorI2B(),
            new InstructionEvaluatorI2C(),
            new InstructionEvaluatorI2S(),

            new InstructionEvaluatorFCmpOp(),
            new InstructionEvaluatorDCmpOp(),

            new InstructionEvaluatorIReturn(),
            new InstructionEvaluatorLReturn(),
            new InstructionEvaluatorFReturn(),
            new InstructionEvaluatorDReturn(),
            new InstructionEvaluatorAReturn(),
            new InstructionEvaluatorReturn(),

            new InstructionEvaluatorMonitorEnter(),
            new InstructionEvaluatorMonitorExit(),

            // ---- カテゴリ 2 ----

            new InstructionEvaluatorBiPush(),

            new InstructionEvaluatorLDC(),

            new InstructionEvaluatorILoad(),
            new InstructionEvaluatorLLoad(),
            new InstructionEvaluatorFLoad(),
            new InstructionEvaluatorDLoad(),
            new InstructionEvaluatorALoad(),

            new InstructionEvaluatorIStore(),
            new InstructionEvaluatorLStore(),
            new InstructionEvaluatorFStore(),
            new InstructionEvaluatorDStore(),
            new InstructionEvaluatorAStore(),

            new InstructionEvaluatorRet(),

            // ---- カテゴリ 3 ----

            new InstructionEvaluatorSiPush(),

            new InstructionEvaluatorLDCW(),
            new InstructionEvaluatorLDCW2(),

            new InstructionEvaluatorGetStatic(),
            new InstructionEvaluatorPutStatic(),
            new InstructionEvaluatorGetField(),
            new InstructionEvaluatorPutField(),

            new InstructionEvaluatorInvokeVirtual(),
            new InstructionEvaluatorInvokeSpecial(),
            new InstructionEvaluatorInvokeStatic(),

            new InstructionEvaluatorNew(),
            new InstructionEvaluatorANewArray(),

            new InstructionEvaluatorCheckCast(),
            new InstructionEvaluatorInstanceOf(),

            new InstructionEvaluatorIfOP(),
            new InstructionEvaluatorIfICmpOP(),
            new InstructionEvaluatorIfNull(),
            new InstructionEvaluatorIfNonNull(),

            new InstructionEvaluatorGoto(),
            new InstructionEvaluatorJsr(),

            // ---- カテゴリ 4 ----

            new InstructionEvaluatorMultiANewArray(),

            // ---- カテゴリ 5 ----

            new InstructionEvaluatorGotoW(),
            new InstructionEvaluatorJsrW()
    );

    @Nullable
    static InstructionInfo evaluateInstruction(@NotNull JALMethodEvaluator methodEvaluator,
                                               @NotNull JALParser.InstructionContext instruction,
                                               @Nullable LabelInfo lastLabel)
    {
        try
        {
            for (AbstractInstructionEvaluator<?> evaluator : EVALUATORS)
                if (evaluator.isApplicable(instruction))
                {
                    EvaluatedInstruction evaluated = evaluator.evaluate(methodEvaluator, instruction);
                    return new InstructionInfo(
                            evaluated.insn(),
                            methodEvaluator.getBytecodeOffset(),
                            lastLabel,
                            evaluated.getInstructionSize()
                    );
                }

            throw new UnsupportedOperationException("Unsupported instruction: " + instruction.getText());
        }
        catch (Exception e)
        {
            long line = instruction.getStart().getLine();
            long offset = instruction.getStart().getCharPositionInLine();
            long problemLengthInLine = instruction.getStop().getCharPositionInLine() - offset + 1;

            methodEvaluator.getContext().postError(
                    "An error occurred while evaluating instruction: " + instruction.getText(),
                    e,
                    line,
                    offset,
                    problemLengthInLine
            );
            return null;
        }
    }
}
