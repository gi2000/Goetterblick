using System;
using global::Goetterblick.scripts.global;
using Godot;
using Goetterblick.scripts.general.simple_objects;

namespace Goetterblick.scripts.general.node;

public partial class SortOptionButton : OptionButton
{
	private void OnItemSelected(int index)
	{
		string sortOption = GetItemText(index).ToUpper();
		foreach (SortOptionType sortOptionType in Enum.GetValues<SortOptionType>())
		{
			if (!sortOption.EndsWith(sortOptionType.ToString())) continue;
			EventBus.Instance.EmitSignal("OnSearchOptionItemSelected", Variant.From(sortOptionType));
			break;
		}
	}
}
