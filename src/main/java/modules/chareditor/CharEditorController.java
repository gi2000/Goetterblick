package modules.chareditor;

import data.annotations.Module;
import data.translations.ui.modules.TCharEditorModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

import java.net.URL;
import java.util.List;

@Module(name = "chareditor")
public class CharEditorController extends AbstractController
{
    @Override
    public boolean initElements()
    {
        return false;
    }

    public IModel createModel()
    {
        return new CharEditorModel();
    }

    public IView createView()
    {
        return new CharEditorView(this, getModel(), getStage(), getRoot());
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TCharEditorModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TCharEditorModule.MODULEBUTTON_TOOLTIP);
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
