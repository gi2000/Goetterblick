package modules.general.facades;

import javafx.fxml.FXML;

/**
 * The interface of a controller for the MVC pattern.
 */
public interface IController
{
    /**
     * Prepares and initializes this controller. Here the model and view get prepared, too.
     */
    @FXML
    void initialize();

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
     * The hook method to implement all resize changes, once the window resizes.
     *
     * @param sizeFactor The factor of how much the window was set to smaller or bigger. F. ex. -2.0 means 2x smaller and 3.0 means
     *                   3x bigger.
     */
    void resizeElements(double sizeFactor);

    // ###############
    // Getter & Setter
    // ###############

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