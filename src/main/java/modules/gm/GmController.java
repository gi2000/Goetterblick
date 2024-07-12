package modules.gm;

import data.annotations.Module;
import data.translations.ui.modules.TGmModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

import java.net.URL;
import java.util.List;

@Module(name = "gm")
public class GmController extends AbstractController
{
    @Override
    public boolean initElements()
    {
        return false;
    }

    public IModel createModel()
    {
        return new GmModel();
    }

    public IView createView()
    {
        return new GmView(this, getModel(), getStage(), getRoot());
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TGmModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TGmModule.MODULEBUTTON_TOOLTIP);
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
