package modules.general.facades;

import data.general.Tuple;
import javafx.scene.control.Control;

/**
 * The view of the MVC pattern, which <b>only</b> updates the visual side for the user. Paired with the corresponding {@code
 * Model}, the view changes once values inside the model change. The view does not handle user interactions, as that is the {@code
 * Controller}'s task.
 *
 * @apiNote It is recommended not to use this facade, if you are implementing a new View-class. Instead, extend from the {@code
 * AbstractView}-class, since some methods are already implemented in the abstract View.
 */
public interface IView
{
    /**
     * Initializes / starts up the view. Here all initial data-displays are done, while in the constructor mainly set-methods are
     * called.
     *
     * @return Whether the initialization was successful.
     */
    boolean initialize();

    /**
     * Initializes all given control elements, which the user can interact with, and assigns them with the given tooltip text.
     * Tooltip texts should always be pulled from the translation files.
     *
     * @param elements The tuples containing as val1 the JavaFX element and as val2 the tool tip text for the JavaFX element.
     */
    @SuppressWarnings("unchecked")
    void initToolTips(Tuple<Control, String>... elements);

    /**
     * Deconstructs this view, if anything needs to be torn down for the next or previous module.
     *
     * @return Whether the deconstruction was successful.
     */
    boolean deconstruct();
}
