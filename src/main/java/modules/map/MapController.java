package modules.map;

import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public class MapController extends AbstractController
{

    @Override
    public void initElements()
    {
    }

    public IModel createModel()
    {
        return new MapModel();
    }

    public IView createView()
    {
        return new MapView(this, getModel());
    }
}
