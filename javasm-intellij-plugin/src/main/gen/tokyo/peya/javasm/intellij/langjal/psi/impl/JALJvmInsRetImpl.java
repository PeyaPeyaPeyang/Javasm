// This is a generated file. Not intended for manual editing.
package tokyo.peya.javasm.intellij.langjal.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static tokyo.peya.javasm.intellij.langjal.psi.JALTypes.*;
import tokyo.peya.javasm.intellij.langjal.psi.*;

public class JALJvmInsRetImpl extends JALInstructionImpl implements JALJvmInsRet {

  public JALJvmInsRetImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitJvmInsRet(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JALJvmInsArgLocalRef getJvmInsArgLocalRef() {
    return findChildByClass(JALJvmInsArgLocalRef.class);
  }

}
