package modules.chareditor;

import modules.general.abstracts.AbstractView;
import modules.general.facades.IController;
import modules.general.facades.IModel;

public class CharEditorView extends AbstractView
{

    public CharEditorView(IController controller, IModel model)
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
