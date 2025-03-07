using System;
using System.Linq;
using Godot;
using Godot.Collections;
using Goetterblick.scripts.general.simple_objects;
using static System.Enum;

namespace Goetterblick.scripts.general.node;

public partial class SearchBar : LineEdit
{
    [Export] private Array<NodePath> _pathToSearchContainer;
    [Export] private NodePath _pathToSearchOptions;
    [Export] private Array<NodePath> _pathToCategories;

    private SearchOption _searchOption = SearchOption.NAME;

    private void OnSearchTextChanged(string newText)
    {
        string newTextLower = newText.ToLower();
        foreach (NodePath path in _pathToSearchContainer)
        {
            Node searchContainer = GetNode<Node>(path);
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
    }

    private void OnSearchOptionItemSelected(int selectedIndex)
    {
        OptionButton dropdownMenu = GetNode<OptionButton>(_pathToSearchOptions);
        string searchOptionString = dropdownMenu.GetItemText(selectedIndex).ToUpper();
        foreach (SearchOption searchOption in GetValues(typeof(SearchOption)))
        {
            if (!searchOptionString.EndsWith(searchOption.ToString())) continue;
            _searchOption = searchOption;
            break;
        }

        OnSearchTextChanged(GetText());
    }

    /// <summary>
    /// Gets the Index of the currently selected search option.
    /// </summary>
    /// <returns>Returns either the index or -1 if no category of this name was found.</returns>
    private int GetDisplayedCategoryIndex()
    {
        foreach (NodePath path in _pathToCategories)
        {
            Node categories = GetNode<Node>(path);
            for (int i = 0; i < categories.GetChildCount(); i++)
            {
                Node child = categories.GetChild(i);

                if (child is not Label textLabel) continue;
                string categoryText = textLabel.GetText().ToUpper();
                if (categoryText.EndsWith(_searchOption.ToString()))
                {
                    return i;
                }
            }
        }

        return -1;
    }
}