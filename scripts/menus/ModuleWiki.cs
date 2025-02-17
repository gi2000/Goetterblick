using Godot;

namespace Goetterblick.scripts.menus;

public partial class ModuleWiki : Control
{
	private string _pathToStartMenu = "res://scenes/main/start_menu.tscn";
	private string _pathToSettingsMenu = "res://scenes/main/settings_menu.tscn";

	private void OnBackButtonUp()
	{
		GetTree().ChangeSceneToFile(_pathToStartMenu);
	}

	private void OnSettingsButtonUp()
	{
		GetTree().ChangeSceneToFile(_pathToSettingsMenu);
	}
}
