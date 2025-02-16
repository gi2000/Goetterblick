using Godot;
using System;
using Godot.Collections;
using Goetterblick.scripts.general;
using Goetterblick.scripts.menus;

public partial class Startup : Node
{
	
	// Called when the node enters the scene tree for the first time.
	public override void _Ready()
	{
		Utils.LoadAllConfigs(GetTree().Root);
	}
}
