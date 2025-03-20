using Godot;
using Goetterblick.scripts.general;

namespace Goetterblick.scripts.global;

public partial class Startup : Node
{
	
	// Called when the node enters the scene tree for the first time.
	public override void _Ready()
	{
		Utils.LoadAllConfigs(GetTree().Root);
	}
}