package modules.gm;

import data.annotations.Module;
import data.translations.ui.modules.TGmModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

@Module(name = "gm")
public class GmController extends AbstractController
{
    public void resizeElements(double sizeFactor)
    {

    }

    public IModel createModel()
    {
        return new GmModel();
    }

    public IView createView()
    {
        return new GmView(this);
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TGmModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TGmModule.MODULEBUTTON_TOOLTIP);
    }
}
