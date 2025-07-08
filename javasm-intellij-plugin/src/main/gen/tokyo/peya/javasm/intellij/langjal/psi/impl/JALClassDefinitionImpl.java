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

public class JALClassDefinitionImpl extends ASTWrapperPsiElement implements JALClassDefinition {

  public JALClassDefinitionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitClassDefinition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public JALAccModClass getAccModClass() {
    return findNotNullChildByClass(JALAccModClass.class);
  }

  @Override
  @Nullable
  public JALClassBody getClassBody() {
    return findChildByClass(JALClassBody.class);
  }

  @Override
  @Nullable
  public JALClassMeta getClassMeta() {
    return findChildByClass(JALClassMeta.class);
  }

  @Override
  @Nullable
  public JALClassName getClassName() {
    return findChildByClass(JALClassName.class);
  }

}
