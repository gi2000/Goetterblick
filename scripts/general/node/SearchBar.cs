using Godot;
using Godot.Collections;
using Goetterblick.scripts.general.simple_objects;
using static System.Enum;

namespace Goetterblick.scripts.general.node;

public partial class SearchBar : LineEdit
{
	[Export] private NodePath _pathToSearchContainer;
	[Export] private NodePath _pathToSearchOptions;
	[Export] private Array<NodePath> _pathToCategories;

	private SearchOption _searchOption = SearchOption.NAME;
	
	private void OnSearchTextChanged(string newText)
	{
		string newTextLower = newText.ToLower();
		Node searchContainer = GetNode<Node>(_pathToSearchContainer);
		if (searchContainer == null || searchContainer.GetChildCount() <= 0) return;


		foreach (Node child in searchContainer.GetChildren())
		{
			if (child.GetNode("Margin/Box").GetChild(GetDisplayedCategoryIndex()) is Label textLabel &&
				child is Control control)
			{
				control.SetVisible(textLabel.GetText().ToLower().Contains(newTextLower));
			}
		}
	}

	private void OnSearchOptionItemSelected(int selectedIndex)
	{
		OptionButton dropdownMenu = GetNode<OptionButton>(_pathToSearchOptions);
		string searchOptionString = dropdownMenu.GetItemText(selectedIndex).ToUpper();
		if (TryParse(searchOptionString, out SearchOption temp))
		{
			_searchOption = temp;
		}
	}

	private int GetDisplayedCategoryIndex()
	{
		Node categories = GetNode<Node>(_pathToCategories);
		foreach (Node child in categories.GetChildren())
		{
		}
	}
}
