package modules.general.facades;

import javafx.stage.Stage;

/**
 * The interface of a controller for the MVC pattern.
 */
public interface IController
{

    /**
     * The initialization method, which loads all the assets for the scene.
     */
    boolean initScreen(Stage stage);

    /**
     * Initializes all given control elements, which the user can interact with.
     *
     * @return Whether the initialization was successful.
     */
    boolean initElements();

    /**
     * Tears everything down from the current module.
     *
     * @param isModuleSwitch Whether the deconstruction is happening due to a module switch. If so, then also the
     *                       model has to be
     *                       deconstructed.
     * @return Whether the deconstruction was successful.
     */
    boolean deconstruct(boolean isModuleSwitch);

    /**
     * Switches the current module / controller to the new, given one.
     *
     * @param controller The module / controller to switch to.
     * @return Whether the switch was successful.
     */
    boolean switchToModule(IController controller);

    /**
     * Creates a new instance of the model for the current module.
     *
     * @return The model holding all the data.
     */
    IModel createModel();

    /**
     * Creates a new instance of the view for the current module.
     *
     * @return The view displaying all the model's data.
     */
    IView createView();

    // ###############
    // Getter & Setter
    // ###############

    /**
     * Sets the relevant model for this implementation of the MVC pattern.
     *
     * @param model The model, holding this screen's data.
     */
    void setModel(IModel model);

    /**
     * The relevant model for this implementation of the MVC pattern.
     *
     * @return The model, holding this screen's data.
     */
    IModel getModel();

    /**
     * The relevant view for this implementation of the MVC pattern.
     *
     * @param view The view for displaying the model's data.
     */
    void setView(IView view);

    /**
     * The relevant view for this implementation of the MVC pattern.
     *
     * @return The view, displaying this screen's data.
     */
    IView getView();

    /**
     * Retrieves the name of the associated module.
     *
     * @return The associated module name.
     */
    String getModuleName();

    /**
     * Retrieves the tooltip for the associated module button.
     *
     * @return The associated module button's tooltip.
     */
    String getModuleTooltip();
}
