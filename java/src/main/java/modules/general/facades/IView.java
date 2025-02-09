package modules.general.facades;

/**
 * The view of the MVC pattern, which <b>only</b> updates the visual side for the user. Paired with the corresponding
 * {@code
 * Model}, the view changes once values inside the model change. The view does not handle user interactions, as that
 * is the {@code
 * Controller}'s task.
 *
 * @apiNote It is recommended not to use this facade, if you are implementing a new View-class. Instead, extend from
 * the {@code
 * AbstractView}-class, since some methods are already implemented in the abstract View.
 */
public interface IView
{
    /**
     * Initializes / starts up the view. Here all initial data-displays are done, while in the constructor mainly
     * set-methods are
     * called.
     *
     * @return Whether the initialization was successful.
     */
    boolean initialize();

    /**
     * Deconstructs this view, if anything needs to be torn down for the next or previous module.
     *
     * @return Whether the deconstruction was successful.
     */
    boolean deconstruct();

    // ###################
    // Getters and Setters
    // ###################

    /**
     * Gets the controller of this view.
     *
     * @return The controller of the MVC pattern for this view.
     */
    IController getController();

    /**
     * Gets the model of this view.
     *
     * @return The model of the MVC pattern for this view.
     */
    IModel getModel();
}
