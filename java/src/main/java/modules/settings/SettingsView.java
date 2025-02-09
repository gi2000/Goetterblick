package modules.settings;

import modules.general.abstracts.AbstractView;
import modules.general.facades.IController;
import modules.general.facades.IModel;

public class SettingsView extends AbstractView
{
    public SettingsView(IController controller, IModel model)
    {
        super(controller, model);
    }

    @Override
    public boolean initialize()
    {
        return true;
    }

    @Override
    public boolean deconstruct()
    {
        return true;
    }
}
