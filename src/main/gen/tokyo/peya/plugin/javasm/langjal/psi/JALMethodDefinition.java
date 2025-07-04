// This is a generated file. Not intended for manual editing.
package tokyo.peya.plugin.javasm.langjal.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JALMethodDefinition extends PsiElement {

  @NotNull
  JALAccModMethod getAccModMethod();

  @NotNull
  List<JALInstruction> getInstructionList();

  @NotNull
  List<JALLabel> getLabelList();

  @NotNull
  PsiElement getMethodDesc();

  @Nullable
  PsiElement getId();

}
