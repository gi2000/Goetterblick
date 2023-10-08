package modules.general;

import javafx.fxml.FXML;

/**
 * The interface of a controller for the MVC pattern.
 */
public interface IController
{
    /**
     * Prepares and initializes this controller. Here the model and view get prepared, too.
     *
     * @return Whether the initialization was successful.
     */
    @FXML
    boolean initialize();

    /**
     * Tears everything down from the current module.
     *
     * @return Whether the deconstruction was successful.
     */
    boolean deconstruct();

    /**
     * Switches the current module to the given one.
     *
     * @param module The module to switch to.
     * @return Whether the switch was successful.
     */
    boolean switchToModule(IModule module);

    /**
     * The relevant model for this implementation of the MVC pattern.
     *
     * @return The model, holding this screen's data.
     */
    IModel getModel();

    /**
     * The relevant view for this implementation of the MVC pattern.
     *
     * @return The view, displaying this screen's data.
     */
    IView getView();
}
