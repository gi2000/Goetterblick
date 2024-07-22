package modules.settings;

import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public class SettingsController extends AbstractController
{
    @Override
    public void initElements()
    {
    }

    @Override
    public IModel createModel()
    {
        return new SettingsModel();
    }

    @Override
    public IView createView()
    {
        return new SettingsView(this, getModel());
    }
}
