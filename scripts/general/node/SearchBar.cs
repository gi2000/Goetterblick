using System;
using Godot;

namespace Goetterblick.scripts.general.node;

public partial class SearchBar : LineEdit
{
    [Export] private NodePath _pathToSearchContainer;

    private void OnSearchTextChanged(string newText)
    {
        string newTextLower = newText.ToLower();
        Node searchContainer = GetNode<Node>(_pathToSearchContainer);
        if (searchContainer == null || searchContainer.GetChildCount() <= 0) return;
        foreach (Node child in searchContainer.GetChildren())
        {
            // Achtung! Durchsucht im Moment nur Name (erstes Feld). - Für Erweiterungen in der Zukunft, benutze statt GetChild(0) lieber GetNode("LabelName"). LabelName kann durch eine Suchoption ersetzt werden.
            if (child.GetNode("Margin/Box").GetChild(0) is Label textLabel && child is Control control)
            {
                control.SetVisible(textLabel.GetText().ToLower().Contains(newTextLower));
            }
        }
    }
}