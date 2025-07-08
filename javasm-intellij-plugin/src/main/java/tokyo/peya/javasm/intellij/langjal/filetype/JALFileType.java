package tokyo.peya.javasm.intellij.langjal.filetype;

import com.intellij.openapi.fileTypes.LanguageFileType;
import javax.swing.Icon;
import lombok.Getter;
import tokyo.peya.javasm.intellij.Assets;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;

@Getter
public class JALFileType extends LanguageFileType
{
    public static final JALFileType INSTANCE = new JALFileType();

    private final String name = "Java Assembly Language File";
    private final String description = "A file type for Java Assembly Language (JAL) files.";
    private final String defaultExtension = "assets";
    private final Icon icon = Assets.JAL_ICON;

    protected JALFileType()
    {
        super(JALLanguage.INSTANCE);
    }
}
