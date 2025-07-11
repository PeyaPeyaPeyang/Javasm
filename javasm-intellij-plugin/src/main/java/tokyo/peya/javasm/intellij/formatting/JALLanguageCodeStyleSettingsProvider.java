package tokyo.peya.javasm.intellij.formatting;

import com.intellij.application.options.IndentOptionsEditor;
import com.intellij.application.options.SmartIndentOptionsEditor;
import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tokyo.peya.javasm.intellij.langjal.JALLanguage;

public class JALLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider
{
    @Override
    public @Nullable IndentOptionsEditor getIndentOptionsEditor()
    {
        return new SmartIndentOptionsEditor();
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType)
    {
        switch (settingsType)
        {
            case SPACING_SETTINGS:
                break;
            case BLANK_LINES_SETTINGS:
                break;
            case WRAPPING_AND_BRACES_SETTINGS:
                consumer.showStandardOptions(
                        "METHOD_PARAMETERS_WRAP",
                        "ALIGN_MULTILINE_PARAMETERS_IN_CALLS",
                        "METHOD_PARAMETERS_LPAREN_ON_NEXT_LINE", // ( を次行に置く
                        "METHOD_PARAMETERS_RPAREN_ON_NEXT_LINE"  // ) を次行に置く
                );
                break;
            default:
                // 他の設定タイプには何も表示しない
                break;
        }
    }

    @Override
    public @Nullable String getCodeSample(@NotNull SettingsType settingsType)
    {
        return """
                public class Main (
                   major_version=65,
                   minor_version=0) {
                  public static myString: Ljava/lang/String;
                
                  public static main([Ljava/lang/String;)V {
                  }
                
                  <init>()V {
                  myTryStart: {~myTryEnd, java/lang/Exception: myCatchA}
                    aaload
                    aastore
                    aconst_null
                    aload 1
                  myTryEnd:
                    return
                  myCatchA:
                    instanceof Ljava/lang/String;
                    invokeinterface java/lang/String->awdw$1_35(Ljava/lang/String;)Ljava/lang/String;
                    invokespecial java/lang/String-><init>()V
                    invokespecial java/lang/String-><init>(Ljava/lang/String;)V
                    invokespecial java/lang/String-><init>(Ljava/lang/String;Ljava/lang/String;)V
                
                    ldc "135135"
                    ldc_w "135135135135"
                    ldc2_w "1234567890123456789L"
                
                    lookupswitch {
                      0x00: test;
                      default: test
                    }
                
                    putfield java/lang/String->awdw$1_35:Ljava/lang/String;
                    putstatic java/lang/String->myString:Ljava/lang/String;
                
                    tableswitch 1 {
                       test
                    } default awd
                    wide aload 24
                  }
                
                  <clinit>()V {
                  }
                }
                """;
    }

    @Override
    public @NotNull Language getLanguage()
    {
        return JALLanguage.INSTANCE;
    }
}
