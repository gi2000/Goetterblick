using System;
using System.Collections.Generic;
using Godot;

namespace Goetterblick.scripts.general;

public class Utils
{
    public const LogLevel LogLevel = general.LogLevel.Debug;
    
    public const string MainSettingsConfig = "user://settings.cfg";

    public const string PathToDarkTheme = "res://resources/theme/dark_theme.tres";
    public const string PathToLightTheme = "res://resources/theme/light_theme.tres";

    public static readonly ConfigFile Config = new();

    // ###############
    // Config Keywords
    // ###############

    public static readonly ConfigId DarkThemeId = new("general", "IsDarkTheme");

    private static readonly Stack<string> LastScenes = new();

    public static void SaveSetting(ConfigId id, Variant value)
    {
        // Create new ConfigFile object.
        LoadOrCreateConfig(Config);

        Config.SetValue(id.Section, id.Key, value);
        Config.Save(MainSettingsConfig);
    }

    /// <summary>
    /// Returns the value saved to the default config file. If no value was found, then the defaultValue will be a fallback.
    /// </summary>
    /// <param name="id">The ConfigId, that acts as a (section + key)-Key conglamerate. Godot likes to store these in .cfg files like that.</param>
    /// <param name="defaultValue">The fallback value, if the value is not found.</param>
    /// <returns>A variant, dependent on the option / setting. Casting to that data type (f. ex. bool or string) must still be done outside this method.</returns>
    public static Variant GetSettingFromFile(ConfigId id, Variant defaultValue)
    {
        LoadOrCreateConfig(Config);
        return Config.GetValue(id.Section, id.Key, defaultValue);
    }

    /// <summary>
    /// Get already loaded setting from the ConfigValues Dictionary.
    /// </summary>
    /// <param name="id">The ConfigId, that acts as a (section + key)-Key conglamerate. Godot likes to store these in .cfg files like that.</param>
    /// <param name="defaultValue">The fallback value, if the value is not found.</param>
    /// <returns>A variant, dependent on the option / setting. Casting to that data type (f. ex. bool or string) must still be done outside this method.</returns>
    public static Variant GetSetting(ConfigId id, Variant defaultValue)
    {
        return Config.GetValue(id.Section, id.Key, defaultValue);
    }

    private static void LoadOrCreateConfig(ConfigFile config)
    {
        // Load data from a file.
        Error err = config.Load(MainSettingsConfig);

        // If the file didn't load, ignore it.
        if (err != Error.Ok)
        {
            config.Save(MainSettingsConfig);
        }
    }

    public static void LoadAllConfigs(Window root)
    {
        bool isDarkTheme = GetSettingFromFile(DarkThemeId, Variant.From(true)).AsBool();
        Theme t = ResourceLoader.Load<Theme>(isDarkTheme ? PathToDarkTheme : PathToLightTheme);
        root.SetTheme(t);
        root.Show();

        Config.SetValue(DarkThemeId.Section, DarkThemeId.Key, isDarkTheme);
    }
    
    public static void AddLastScene(SceneTree tree)
    {
        LastScenes.Push(tree.GetCurrentScene().GetSceneFilePath());
    }

    public static string PopLastScene()
    {
        return LastScenes.Pop();
    }

    public static string PeekLastScene()
    {
        return LastScenes.Count > 0 ? LastScenes.Peek() : null;
    }
}