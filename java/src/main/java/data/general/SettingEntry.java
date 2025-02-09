package data.general;

public record SettingEntry(String name, String descr, Object value, Object defaultValue)
{
    /**
     * Gets the current value. If it fails, it returns the fallback value.
     *
     * @return Returns either the currently selected value, if it is present, or the default fallback otherwise.
     */
    public Object getValueOrDefault()
    {
        return value == null ? defaultValue : value;
    }
}
