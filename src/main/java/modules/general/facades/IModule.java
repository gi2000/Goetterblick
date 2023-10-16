package modules.general.facades;

import javafx.stage.Stage;

import java.net.URL;

/**
 * The interface for sub-areas such as the character creation, dungeon master, settings screen and so on.
 */
public interface IModule
{

    /**
     * Initializes this module's screen with everything necessary.
     *
     * @param stage       The main stage / scene / window, where everything will be displayed in.
     * @param screenTitle The main windows title, displayed at the top of the window frame.
     * @return The root node of the FXML.
     */
    boolean initScreen(Stage stage, String screenTitle);

    /**
     * Tear down everything not needed for the next screen. Should clear the stage if necessary.
     *
     * @return Whether the screen was successfully cleared.
     */
    boolean deconstructScreen();

    /**
     * Displays this module's screen, after it has been successfully initialized.
     *
     * @return Whether the screen was successfully displayed.
     */
    boolean displayScreen();

    // ###############
    // Getter & Setter
    // ###############

    void setStage(Stage stage);

    /**
     * Returns the main stage of this module.
     *
     * @return The stage in which all elements are displayed at.
     */
    Stage getStage();

    /**
     * Returns the previously displayed module.
     *
     * @return The previous modules name, that can be fed directly into the ModuleHandler again.
     */
    String getPrevModule();

    /**
     * Gets the controller of this module.
     *
     * @return The controller of the MVC pattern for this module.
     */
    IController getController();

    /**
     * Retrieves the URL for the given module image.
     *
     * @return The url to the module image for the display-area in the start window..
     */
    URL getModuleImage();

    /**
     * Get display name of this module.
     *
     * @return The display name.
     */
    String getName();
}
