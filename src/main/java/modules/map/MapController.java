package modules.map;

import data.annotations.Module;
import data.translations.ui.modules.TMapModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

@Module(name = "map")
public class MapController extends AbstractController
{
    public void resizeElements(double sizeFactor)
    {

    }

    public IModel createModel()
    {
        return new MapModel();
    }

    public IView createView()
    {
        return new MapView(this);
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TMapModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TMapModule.MODULEBUTTON_TOOLTIP);
    }
}
