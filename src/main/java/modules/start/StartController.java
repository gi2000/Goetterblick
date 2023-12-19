package modules.start;

import data.annotations.Module;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

@Module(name = "start", isDisplayedInHome = false)
public class StartController extends AbstractController
{

    // #######
    // Methods
    // #######

    public void resizeElements(double sizeFactor)
    {

    }

    public IModel createModel()
    {
        return new StartModel();
    }

    public IView createView()
    {
        return null;
    }

    // #################
    // Getter and Setter
    // #################

    @Override
    public String getModuleName()
    {
        return "Start";
    }

    public String getModuleTooltip()
    {
        return null;
    }

    public StartModel getStartModel()
    {
        return (StartModel) getModel();
    }

    public StartView getStartView()
    {
        return (StartView) getView();
    }
}
