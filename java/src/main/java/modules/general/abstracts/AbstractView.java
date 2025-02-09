package modules.general.abstracts;

import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public abstract class AbstractView implements IView
{
    private final IController controller;
    private final IModel      model;

    public AbstractView(IController controller, IModel model)
    {
        this.controller = controller;
        this.model = model;
    }

    @Override
    public IController getController()
    {
        return controller;
    }

    @Override
    public IModel getModel()
    {
        return model;
    }
}
