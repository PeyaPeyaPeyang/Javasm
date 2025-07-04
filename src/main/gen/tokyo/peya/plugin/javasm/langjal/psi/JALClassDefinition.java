// This is a generated file. Not intended for manual editing.
package tokyo.peya.plugin.javasm.langjal.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JALClassDefinition extends PsiElement {

  @NotNull
  JALAccModClass getAccModClass();

  @Nullable
  JALClassBody getClassBody();

  @Nullable
  JALClassMeta getClassMeta();

  @Nullable
  PsiElement getId();

}
