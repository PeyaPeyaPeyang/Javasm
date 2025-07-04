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

public class JALFieldDefinitionImpl extends ASTWrapperPsiElement implements JALFieldDefinition {

  public JALFieldDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitFieldDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JALAccModField getAccModField() {
    return findNotNullChildByClass(JALAccModField.class);
  }

  @Override
  @Nullable
  public PsiElement getTypeDesc() {
    return findChildByType(TYPE_DESC);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

}
