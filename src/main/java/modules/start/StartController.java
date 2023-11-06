package modules.start;

import data.annotations.Module;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;

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

    // #################
    // Getter and Setter
    // #################

    @Override
    public String getModuleName()
    {
        return "Start";
    }
}
