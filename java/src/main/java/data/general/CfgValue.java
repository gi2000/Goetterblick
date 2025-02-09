package data.general;

/**
 * Creates a new value pair. The first value being the one read from a config, the second being the standard / default value.
 *
 * @param <T> The class of the values
 */
public class CfgValue<T>
{
    private T readVal;
    private T defaultVal;

    /**
     * Creates a new value pair. The first value being the one read from a config, the second being the standard / default value.
     *
     * @param readValue    The value read from a config.
     * @param defaultValue The default / fallback value.
     */
    public CfgValue(T readValue, T defaultValue)
    {
        this.readVal = readValue;
        this.defaultVal = defaultValue;
    }

    /**
     * Returns the read value.
     *
     * @return The value read from a config.
     */
    public T getReadValue()
    {
        // If the read value is not set, return the normal / default value.
        if (readVal == null)
        {
            return getDefaultValue();
        }

        return readVal;
    }

    /**
     * Sets the read value.
     *
     * @param readVal The new value read from a config.
     */
    public void setReadValue(T readVal)
    {
        this.readVal = readVal;
    }

    /**
     * Returns the default value.
     *
     * @return The default value.
     */
    public T getDefaultValue()
    {
        return defaultVal;
    }

    /**
     * Sets the default value.
     *
     * @param defaultVal The new default value.
     */
    public void setDefaultValue(T defaultVal)
    {
        this.defaultVal = defaultVal;
    }
}
