package modules.map;

import data.annotations.Module;
import data.translations.ui.modules.TMapModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

import java.net.URL;
import java.util.List;

@Module(name = "map")
public class MapController extends AbstractController
{

    @Override
    public boolean initElements()
    {
        return false;
    }

    public IModel createModel()
    {
        return new MapModel();
    }

    public IView createView()
    {
        return new MapView(this, getModel(), getStage(), getRoot());
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TMapModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TMapModule.MODULEBUTTON_TOOLTIP);
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
