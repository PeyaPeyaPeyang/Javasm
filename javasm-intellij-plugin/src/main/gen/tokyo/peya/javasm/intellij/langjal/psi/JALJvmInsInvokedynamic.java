// This is a generated file. Not intended for manual editing.
package tokyo.peya.javasm.intellij.langjal.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface JALJvmInsInvokedynamic extends JALInstruction {

  @NotNull
  List<JALInstruction> getInstructionList();

  @Nullable
  JALMethodDescriptor getMethodDescriptor();

  @Nullable
  PsiElement getId();

}
