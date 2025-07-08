// This is a generated file. Not intended for manual editing.
package tokyo.peya.javasm.intellij.langjal.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static tokyo.peya.javasm.intellij.langjal.psi.JALTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import tokyo.peya.javasm.intellij.langjal.psi.*;

public class JALClassBodyImpl extends ASTWrapperPsiElement implements JALClassBody {

  public JALClassBodyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitClassBody(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<JALFieldDefinition> getFieldDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JALFieldDefinition.class);
  }

  @Override
  @NotNull
  public List<JALMethodDefinition> getMethodDefinitionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, JALMethodDefinition.class);
  }

}
