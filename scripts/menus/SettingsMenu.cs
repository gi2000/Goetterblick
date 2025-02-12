using Godot;

namespace Goetterblick.scripts.menus;

public partial class SettingsMenu : Control
{
	private string _pathToStartMenu = "res://scenes/start_menu.tscn";

	private void OnBackButtonUp()
	{
		GetTree().ChangeSceneToFile(_pathToStartMenu);
	}
}
