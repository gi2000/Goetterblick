using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Threading;
using global::Goetterblick.scripts.global;
using Godot;
using Godot.Collections;
using Goetterblick.scripts.general.simple_objects;
using static System.Enum;

namespace Goetterblick.scripts.general.node;

public partial class SearchBar : LineEdit
{
    [Export] private Array<NodePath> _pathToElementsContainer;
    [Export] private Array<NodePath> _pathToCategories;

    private SearchOptionType _searchOptionType;
    private SortOptionType _sortOptionType;

    public override void _Ready()
    {
        base._Ready();
        SortElements();

        EventBus.Instance.OnSearchOptionItemSelected += OnSearchOptionItemSelected;
        EventBus.Instance.OnSortOptionItemSelected += OnSortOptionItemSelected;
    }

    private void OnSortOptionItemSelected(SortOptionType type)
    {
        _sortOptionType = type;
        SortElements();
    }

    private void OnSearchOptionItemSelected(SearchOptionType type)
    {
        _searchOptionType = type;
        OnSearchTextChanged(GetText());
    }

    private void OnSearchTextChanged(string newText)
    {
        FilterElements(newText);
        SortElements();
    }

    private void FilterElements(string newText)
    {
        string newTextLower = newText.ToLower();
        foreach (NodePath path in _pathToElementsContainer)
        {
            Node searchContainer = GetNode<Node>(path);
            if (searchContainer == null || searchContainer.GetChildCount() <= 0) return;

            foreach (Node child in searchContainer.GetChildren())
            {
                if (child.GetNode("Margin/Box").GetChild(GetDisplayedCategoryIndex(_searchOptionType)) is Label textLabel &&
                    child is Control control)
                {
                    control.SetVisible(TranslationServer.Translate(textLabel.GetText()).ToString().ToLower()
                        .Contains(newTextLower, StringComparison.CurrentCultureIgnoreCase));
                }
            }
        }
    }

    private void SortElements()
    {
        foreach (NodePath path in _pathToElementsContainer)
        {
            Node searchContainer = GetNode<Node>(path);
            if (searchContainer == null || searchContainer.GetChildCount() <= 0) return;

            List<Node> sorted = searchContainer.GetChildren().OrderBy(child => GetTextToSortBy(_sortOptionType, child)).ToList();
            for (int i = 0; i < sorted.Count && i < searchContainer.GetChildCount(); i++)
            {
                searchContainer.MoveChild(sorted[i], i);
            }
        }
    }

    private string GetTextToSortBy(SortOptionType sortBy, Node child)
    {
        switch (sortBy)
        {
            case SortOptionType.ALPHABETICAL:
                if (child.GetNode("Margin/Box").GetChild(GetDisplayedCategoryIndex(SearchOptionType.NAME)) is Label textLabel)
                {
                    return TranslationServer.Translate(textLabel.Text).ToString();
                }

                return null;
            case SortOptionType.CREATIONDATE:
                return null;
            default:
                throw new ArgumentOutOfRangeException(nameof(sortBy), sortBy, null);
        }
    }

    /// <summary>
    /// Gets the Index of the currently selected search option.
    /// </summary>
    /// <returns>Returns either the index or -1 if no category of this name was found.</returns>
    private int GetDisplayedCategoryIndex(SearchOptionType searchOptionType)
    {
        foreach (NodePath path in _pathToCategories)
        {
            Node categories = GetNode<Node>(path);
            for (int i = 0; i < categories.GetChildCount(); i++)
            {
                Node child = categories.GetChild(i);

                if (child is not Label textLabel) continue;
                string categoryText = textLabel.GetText().ToUpper();
                if (categoryText.EndsWith(searchOptionType.ToString()))
                {
                    return i;
                }
            }
        }

        return -1;
    }
}