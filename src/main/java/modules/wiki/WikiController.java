package modules.wiki;

import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public class WikiController extends AbstractController
{
    @Override
    public void initElements()
    {
    }

    @Override
    public IModel createModel()
    {
        return new WikiModel();
    }

    @Override
    public IView createView()
    {
        return new WikiView(this, getModel());
    }
}
