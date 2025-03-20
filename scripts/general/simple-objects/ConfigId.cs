namespace Goetterblick.scripts.general.simple_objects;

public class ConfigId
{
    public string Section { get; private set; }
    public string Key { get; private set; }

    public ConfigId(string section, string key)
    {
        Section = section;
        Key = key;
    }
}