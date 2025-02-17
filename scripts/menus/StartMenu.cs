using Godot;

namespace Goetterblick.scripts.menus;

public partial class StartMenu : Control
{
    private string _pathToSettingsMenu = "res://scenes/main/settings_menu.tscn";
    private string _pathToWikiMenu = "res://scenes/main/module_wiki.tscn";

    private void OnQuitButtonUp()
    {
        GetTree().Quit();
    }

    private void OnSettingsButtonUp()
    {
        GetTree().ChangeSceneToFile(_pathToSettingsMenu);
    }

    private void OnWikiButtonUp()
    {
        GetTree().ChangeSceneToFile(_pathToWikiMenu);
    }
}