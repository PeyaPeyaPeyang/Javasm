package tokyo.peya.javasm.intellij.editor.gotoDeclaration;

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNameNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.LabelNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.identifier.IdentifierNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.InstructionSetNode;
import tokyo.peya.javasm.intellij.langjal.parser.psi.method.MethodBodyNode;

public class JALLabelGotoDeclarationHandler implements GotoDeclarationHandler
{
    @Override
    public PsiElement @Nullable [] getGotoDeclarationTargets(@Nullable PsiElement sourceElement,
                                                             int offset,
                                                             Editor editor)
    {
        if (sourceElement == null)
            return PsiElement.EMPTY_ARRAY;

        // ラベル名ノードであることを確認
        LabelNameNode labelName;
        if (sourceElement instanceof LabelNameNode labelNameNode)
            labelName = labelNameNode;
        else if (sourceElement instanceof IdentifierNode identifierNode
                && identifierNode.getParent() instanceof LabelNameNode labelNameParent)
            labelName = labelNameParent;
        else
            return PsiElement.EMPTY_ARRAY; // ラベル名ノードでない場合は何もしない


        // ラベルの参照箇所を取得する
        MethodBodyNode methodBody = PsiTreeUtil.getParentOfType(labelName, MethodBodyNode.class);
        if (methodBody == null)
            return PsiElement.EMPTY_ARRAY;

        boolean isLabelDeclaration = labelName.getParent() instanceof LabelNode;
        if (isLabelDeclaration)
        {
            // ラベルの宣言箇所へ移動
            return new PsiElement[]{labelName};
        }

        PsiElement labelDeclaration = getLabelDeclaration(methodBody, labelName);
        if (labelDeclaration == null)
            return PsiElement.EMPTY_ARRAY; // ラベルの宣言が見つからなかった場合
        else
            return new PsiElement[]{labelDeclaration}; // ラベルの宣言へ移動
    }

    @Nullable
    private static PsiElement getLabelDeclaration(@NotNull MethodBodyNode methodBody, @NotNull LabelNameNode labelName)
    {
        String labelNameText = labelName.getText();
        for (InstructionSetNode instructionSet : methodBody.getInstructionSets())
        {
            LabelNode label = instructionSet.getLabel();
            if (label == null)
                continue;

            String foundLabelName = label.getLabelName();
            if (labelNameText.equals(foundLabelName))
                return label;
        }

        return null; // ラベルの宣言が見つからなかった場合
    }
}
