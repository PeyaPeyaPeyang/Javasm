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

public class JALClassMetaItemImpl extends ASTWrapperPsiElement implements JALClassMetaItem {

  public JALClassMetaItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitClassMetaItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JALClassPropInterfaces getClassPropInterfaces() {
    return findChildByClass(JALClassPropInterfaces.class);
  }

  @Override
  @Nullable
  public JALClassPropMajor getClassPropMajor() {
    return findChildByClass(JALClassPropMajor.class);
  }

  @Override
  @Nullable
  public JALClassPropMinor getClassPropMinor() {
    return findChildByClass(JALClassPropMinor.class);
  }

  @Override
  @Nullable
  public JALClassPropSuperClass getClassPropSuperClass() {
    return findChildByClass(JALClassPropSuperClass.class);
  }

}
