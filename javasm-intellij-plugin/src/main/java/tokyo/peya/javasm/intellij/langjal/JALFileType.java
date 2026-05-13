package tokyo.peya.javasm.intellij.langjal;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.Assets;

import javax.swing.*;

public class JALFileType extends LanguageFileType {
    public static final JALFileType INSTANCE = new JALFileType();

    protected JALFileType() {
        super(JALLanguage.INSTANCE);
    }

    @Override
    public @NotNull String getName() {
        return "JVM Assembly Language File";
    }

    @Override
    public @Nls @NotNull String getDescription() {
        return "A file type for JVM Assembly Language (JAL) files.";
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return "jal";
    }

    @Override
    public @Nullable Icon getIcon() {
        return Assets.JAL;
    }
}
