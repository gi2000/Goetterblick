using System;
using Godot;
using Godot.Collections;
using Goetterblick.scripts.general;

namespace Goetterblick.scripts.menus;

public partial class SettingsMenu : Control
{
    private const string PathToStartMenu = "res://scenes/main/start_menu.tscn";
    private const string PathToThemeToggleButton = "PanelContainer/VBoxContainer/OptionsMargin/Options/ToggleTheme";

    public override void _Ready()
    {
        base._Ready();
        // Load all initial config values and update all options
        GetNode<BaseButton>(PathToThemeToggleButton).ButtonPressed = Utils.Config.GetValue(Utils.DarkThemeId.Section, Utils.DarkThemeId.Key).AsBool();
    }

    private void OnBackButtonUp()
    {
        GetTree().ChangeSceneToFile(PathToStartMenu);
    }

    private void OnToggleTheme(bool toggledOn)
    {
        GetTree().Root.SetTheme(toggledOn ? ResourceLoader.Load<Theme>(Utils.PathToDarkTheme) : ResourceLoader.Load<Theme>(Utils.PathToLightTheme));
        Utils.SaveSetting(Utils.DarkThemeId, toggledOn);
    }
}