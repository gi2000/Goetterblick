package modules.settings;

import data.general.SettingEntry;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import modules.general.abstracts.AbstractModel;

public class SettingsModel extends AbstractModel
{
    private ListProperty<SettingEntry> settings = new SimpleListProperty<>();

    @Override
    public boolean initialize()
    {
        return false;
    }

    @Override
    public boolean deconstruct()
    {
        return false;
    }
}
