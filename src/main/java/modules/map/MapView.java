package modules.map;

import modules.general.abstracts.AbstractView;
import modules.general.facades.IController;
import modules.general.facades.IModel;

public class MapView extends AbstractView
{

    public MapView(IController controller, IModel model)
    {
        super(controller, model);
    }

    @Override
    public boolean initialize()
    {
        return false;
    }

    @Override
    public boolean deconstruct()
    {
        return false;
    }
}
