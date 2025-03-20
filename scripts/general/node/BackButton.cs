using Godot;

namespace Goetterblick.scripts.general.node;

public partial class BackButton : Button
{
	private void OnBackButtonUp()
	{
		if (Utils.PeekLastScene() == null)
		{
			Logger.Instance.Fatal("Can't go to last panel, as there is no last panel. This is never supposed to occur!");
			return;
		}
		
		GetTree().ChangeSceneToFile(Utils.PopLastScene());
	}
}
