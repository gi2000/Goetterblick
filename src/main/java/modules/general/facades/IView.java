package modules.general.facades;

import data.general.Tuple;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

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
     * Initializes all given control elements, which the user can interact with.
     *
     * @return Whether the initialization was successful.
     */
    boolean initElements();

    /**
     * Assigns all given control elements, which the user can interact with, their given tooltip text.
     * Tooltip texts should always be pulled from the translation files.
     *
     * @param elements The tuples containing as val1 the JavaFX element and as val2 the tool tip text for the JavaFX element.
     */
    @SuppressWarnings("unchecked")
    void assignTooltipsToElements(Tuple<Control, String>... elements);

    /**
     * Assigns all given control elements, which the user can interact with, their given tooltip text.
     * Tooltip texts should always be pulled from the translation files.
     *
     * @param elements The tuples list containing as val1 the JavaFX element and as val2 the tool tip text for the JavaFX element.
     */
    void assignTooltipsToElements(List<Tuple<Control, String>> elements);

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
     * Sets the root node for the stage.
     *
     * @param root The root node containing all nested elements as children.
     */
    void setRoot(Parent root);

    /**
     * Gets the root node for the stage.
     *
     * @return The root node containing all nested elements as children.
     */
    Parent getRoot();

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

    /**
     * Returns the main stage of this view.
     *
     * @return The stage in which all elements are displayed.
     */
    Stage getStage();

    /**
     * Retrieves the FontIcon for the given module image.
     *
     * @return The FontIcon to the module image for the display-area in the start window.
     */
    FontIcon getModuleImage();


}
