package modules.chareditor;

import data.annotations.Module;
import data.translations.ui.modules.TCharEditorModule;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import utils.handler.TranslationHandler;

@Module(name = "chareditor")
public class CharEditorController extends AbstractController
{
    public void resizeElements(double sizeFactor)
    {

    }

    public IModel createModel()
    {
        return new CharEditorModel();
    }

    public IView createView()
    {
        return new CharEditorView(this);
    }

    public String getModuleName()
    {
        return TranslationHandler.getTransl(TCharEditorModule.MODULEBUTTON_LABEL);
    }

    public String getModuleTooltip()
    {
        return TranslationHandler.getTransl(TCharEditorModule.MODULEBUTTON_TOOLTIP);
    }
}
