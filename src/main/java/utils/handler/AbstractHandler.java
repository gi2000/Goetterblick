package utils.handler;

import java.io.File;

public abstract class AbstractHandler
{
    /**
     * Returns the operating system dependent file separator.
     *
     * @return The OS specific file separator.
     */
    public static String s()
    {
        return File.separator;
    }
}
