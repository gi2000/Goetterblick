package modules.settings;

import data.annotations.Module;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

import java.net.URL;
import java.util.List;

@Module(name = "settings", isDisplayedInHome = false)
public class SettingsController extends AbstractController
{
    @Override
    public boolean initElements()
    {
        return false;
    }

    @Override
    public IModel createModel()
    {
        return new SettingsModel();
    }

    @Override
    public IView createView()
    {
        return new SettingsView(this, getModel(), getStage(), getRoot());
    }

    @Override
    public String getModuleName()
    {
        return "";
    }

    @Override
    public String getModuleTooltip()
    {
        return "";
    }

    @Override
    protected URL getFXMLPath()
    {
        return null;
    }

    @Override
    protected List<URL> getCSSPaths()
    {
        return List.of();
    }
}
