// This is a generated file. Not intended for manual editing.
package tokyo.peya.plugin.javasm.langjal.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static tokyo.peya.plugin.javasm.langjal.psi.JALTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tokyo.peya.plugin.javasm.langjal.psi.*;

public class JALMethodDefinitionImpl extends ASTWrapperPsiElement implements JALMethodDefinition {

  public JALMethodDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitMethodDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JALAccModMethod getAccModMethod() {
    return findNotNullChildByClass(JALAccModMethod.class);
  }

  @Override
  @NotNull
  public List<JALInstruction> getInstructionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JALInstruction.class);
  }

  @Override
  @NotNull
  public List<JALLabel> getLabelList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JALLabel.class);
  }

  @Override
  @NotNull
  public PsiElement getMethodDesc() {
    return findNotNullChildByType(METHOD_DESC);
  }

  @Override
  @Nullable
  public PsiElement getId() {
    return findChildByType(ID);
  }

}
