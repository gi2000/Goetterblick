using global::Goetterblick.scripts.global;
using Godot;
using Goetterblick.scripts.general.simple_objects;
using static System.Enum;

namespace Goetterblick.scripts.general.node;

public partial class SearchOptionButton : OptionButton
{
	private void OnItemSelected(int index)
	{
		string searchOptionString = GetItemText(index).ToUpper();
		foreach (SearchOptionType searchOption in GetValues<SearchOptionType>())
		{
			if (!searchOptionString.EndsWith(searchOption.ToString())) continue;
			EventBus.Instance.EmitSignal("OnSearchOptionItemSelected", Variant.From(searchOption));
			break;
		}
	}
}
