// This is a generated file. Not intended for manual editing.
package tokyo.peya.plugin.javasm.langjal.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static tokyo.peya.plugin.javasm.langjal.psi.JALTypes.*;
import tokyo.peya.plugin.javasm.langjal.psi.*;

public class JALJvmInsIfNonnullImpl extends JALInstructionImpl implements JALJvmInsIfNonnull {

  public JALJvmInsIfNonnullImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull JALVisitor visitor) {
    visitor.visitJvmInsIfNonnull(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof JALVisitor) accept((JALVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public JALJvmInsArgUnsigned8Bytes getJvmInsArgUnsigned8Bytes() {
    return findChildByClass(JALJvmInsArgUnsigned8Bytes.class);
  }

  @Override
  @Nullable
  public JALLabelName getLabelName() {
    return findChildByClass(JALLabelName.class);
  }

}
