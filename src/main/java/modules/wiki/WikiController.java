package modules.wiki;

import data.annotations.Module;
import data.translations.ui.modules.TWikiModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

import java.net.URL;
import java.util.List;

@Module(name = "wiki")
public class WikiController extends AbstractController
{
    @Override
    public boolean initElements()
    {
        return false;
    }

    @Override
    public IModel createModel()
    {
        return new WikiModel();
    }

    @Override
    public IView createView()
    {
        return new WikiView(this, getModel(), getStage(), getRoot());
    }

    @Override
    public String getModuleName()
    {
        return TranslationHandler.getTransl(TWikiModule.MODULEBUTTON_LABEL);
    }

    @Override
    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TWikiModule.MODULEBUTTON_TOOLTIP);
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
