using Godot;
using Goetterblick.scripts.general;

namespace Goetterblick.scripts.menus.general;

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
        Utils.AddLastScene(GetTree());
        GetTree().ChangeSceneToFile(_pathToSettingsMenu);
    }

    private void OnWikiButtonUp()
    {
        Utils.AddLastScene(GetTree());
        GetTree().ChangeSceneToFile(_pathToWikiMenu);
    }
}