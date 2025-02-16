using Godot;

namespace Goetterblick.scripts.menus;

public partial class StartMenu : Control
{
	private string _pathToSettingsMenu = "res://scenes/main/settings_menu.tscn";

	private void OnQuitButtonUp()
	{
		GetTree().Quit();
	}

	private void OnSettingsButtonUp()
	{
		GetTree().ChangeSceneToFile(_pathToSettingsMenu);
	}
}
