package modules.general.abstracts;

import javafx.fxml.FXML;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

/**
 * The abstract controller class of the MVC pattern. Extend this class to create the controller for the new module.
 */
public abstract class AbstractController implements IController
{
    private IModel model;
    private IView  view;

    /**
     * The abstract controller class of the MVC pattern. This constructor creates a model and later gets set the view.
     */
    public AbstractController()
    {
        setView(createView());
        setModel(createModel());
    }

    @FXML
    public void initialize()
    {
        getModel().initialize(getModuleName());
    }

    @Override
    public boolean deconstruct()
    {
        getModel().setPrevModule(getModuleName());
        return getModel().deconstruct() && getView().deconstruct();
    }

    @Override
    public boolean switchToModule(IController controller)
    {
        return deconstruct();
    }

    // #################
    // Getter and Setter
    // #################

    public IModel getModel()
    {
        return model;
    }

    public void setModel(IModel model)
    {
        this.model = model;
    }

    public IView getView()
    {
        return view;
    }

    public void setView(IView view)
    {
        this.view = view;
    }
}
