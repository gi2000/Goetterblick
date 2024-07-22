package modules.general.facades;

import data.enums.Module;
import data.general.Tuple;
import general.Initialisation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;

/**
 * The interface of a controller for the MVC pattern.
 */
public interface IController
{

    /**
     * The initialization method, which loads all the assets for the scene.
     */
    void initScreen(boolean isModuleSwitch);

    /**
     * Initializes all given control elements, which the user can interact with.
     */
    void initElements();

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
     * @param module The module to switch to.
     */
    void switchToModule(Module module);

    /**
     * Assigns all given control elements, which the user can interact with, their given tooltip text.
     * Tooltip texts should always be pulled from the translation files.
     *
     * @param elements The tuples list containing as val1 the JavaFX element and as val2 the tool tip text for the
     *                 JavaFX element.
     */
    void assignTooltipsToElements(List<Tuple<Control, String>> elements);

    /**
     * Assigns the given Labeled-Element the text retrieved from the translation files.
     *
     * @param elements The list of tuples: Val1 = Element in JavaFX, that needs to be labeled, Val2 = Translation
     */
    void assignTranslLabels(List<Tuple<Labeled, String>> elements);

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


    /**
     * Looks up the given element.
     *
     * @param id The id of the element to look for.
     * @return The element that was looked for in root.
     */
    default Node lookupID(String id)
    {
        return getRoot().lookup("#" + id);
    }

    /**
     * Looks up all the given buttons on the current page.
     *
     * @param selector The selector that is supposed to be looked up.
     * @return The set of all selected elements.
     */
    default Set<Node> lookupAll(String selector)
    {
        return getRoot().lookupAll(selector);
    }

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
     * The root node of the current scene.
     *
     * @return The JavaFX root node.
     */
    default Parent getRoot()
    {
        System.out.println(Initialisation.ROOT == null);
        return Initialisation.ROOT;
    }

    /**
     * The main window / stage.
     *
     * @return The JavaFX stage.
     */
    default Stage getStage()
    {
        return Initialisation.STAGE;
    }

    /**
     * The main scene, with its diameters and css' applied.
     *
     * @return The JavaFX main scene.
     */
    default Scene getScene()
    {
        return Initialisation.SCENE;
    }
}
