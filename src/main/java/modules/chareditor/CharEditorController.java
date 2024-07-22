package modules.chareditor;

import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public class CharEditorController extends AbstractController
{
    @Override
    public void initElements()
    {
    }

    public IModel createModel()
    {
        return new CharEditorModel();
    }

    public IView createView()
    {
        return new CharEditorView(this, getModel());
    }
}
