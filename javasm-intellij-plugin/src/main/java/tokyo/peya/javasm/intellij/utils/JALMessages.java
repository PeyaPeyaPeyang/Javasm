package tokyo.peya.javasm.intellij.utils;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.PropertyKey;

public class JALMessages extends DynamicBundle
{
    private static final JALMessages INSTANCE = new JALMessages();

    private JALMessages() {
        super("messages.JALMessages");
    }

    public static String message(@PropertyKey(resourceBundle = "messages.JALMessages") String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }
}
