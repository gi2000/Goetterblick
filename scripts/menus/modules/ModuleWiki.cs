using Godot;
using Goetterblick.scripts.general;

namespace Goetterblick.scripts.menus;

public partial class ModuleWiki : Control
{
	private string _pathToStartMenu = "res://scenes/main/start_menu.tscn";
	private string _pathToSettingsMenu = "res://scenes/main/settings_menu.tscn";

	private void OnSettingsButtonUp()
	{
		Utils.AddLastScene(GetTree());
		GetTree().ChangeSceneToFile(_pathToSettingsMenu);
	}
}
