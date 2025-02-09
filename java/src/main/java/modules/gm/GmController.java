package modules.gm;

import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public class GmController extends AbstractController
{
    @Override
    public void initElements()
    {
    }

    public IModel createModel()
    {
        return new GmModel();
    }

    public IView createView()
    {
        return new GmView(this, getModel());
    }
}
