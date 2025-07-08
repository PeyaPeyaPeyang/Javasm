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

public class JALTypeDescriptorImpl extends ASTWrapperPsiElement implements JALTypeDescriptor {

  public JALTypeDescriptorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitTypeDescriptor(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JALTypeDescriptorPrimitive getTypeDescriptorPrimitive() {
    return findChildByClass(JALTypeDescriptorPrimitive.class);
  }

  @Override
  @Nullable
  public PsiElement getTypeDescObject() {
    return findChildByType(TYPE_DESC_OBJECT);
  }

}
