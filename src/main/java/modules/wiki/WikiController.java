package modules.wiki;

import data.annotations.Module;
import data.translations.ui.modules.TWikiModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

@Module(name = "wiki")
public class WikiController extends AbstractController
{
    public void resizeElements(double sizeFactor)
    {

    }

    public IModel createModel()
    {
        return new WikiModel();
    }

    public IView createView()
    {
        return new WikiView(this);
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TWikiModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TWikiModule.MODULEBUTTON_TOOLTIP);
    }
}
