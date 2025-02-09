package utils.handler;

import utils.general.Utils;

public abstract class AbstractHandler
{
    /**
     * Returns the operating system dependent file separator.
     *
     * @return The OS specific file separator.
     */
    public static String s()
    {
        return Utils.s();
    }

    /**
     * Returns the operating system dependent line separator.
     *
     * @return The os specific line separator.
     */
    public static String n()
    {
        return Utils.n();
    }
}
