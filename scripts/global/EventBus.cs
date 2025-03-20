using Godot;
using Goetterblick.scripts.general.simple_objects;

namespace Goetterblick.scripts.global;

public partial class EventBus : Node
{
    public static EventBus Instance { get; private set; }
    
    public override void _Ready()
    {
        base._Ready();
        Instance = this;
    }

    [Signal]
    public delegate void OnSearchOptionItemSelectedEventHandler(SearchOptionType type);
    [Signal]
    public delegate void OnSortOptionItemSelectedEventHandler(SortOptionType type);
}