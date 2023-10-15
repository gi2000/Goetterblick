package modules.general;

import javafx.stage.Stage;

import java.net.URL;
import java.util.List;

/**
 * The interface for sub-areas such as the character creation, dungeon master, settings screen and so on.
 */
public interface IModule
{

    /**
     * Initializes this module's screen with everything necessary.
     *
     * @param stage The main stage / scene / window, where everything will be displayed in.
     * @return Whether the module was successfully setup.
     */
    boolean initScreen(Stage stage);

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


    /**
     * Get display name of this module.
     *
     * @return The display name.
     */
    String getName();

    /**
     * Returns the previously displayed module.
     *
     * @return The previous module.
     */
    IModule prevModule();

    /**
     * Gets the controller of this module.
     *
     * @return The controller of the MVC pattern for this module.
     */
    IController getController();

    /**
     * The relative path to the FXML file.
     *
     * @return The relative path to the FXML file.
     */
    URL getFXMLPath();

    /**
     * The relative path to the CSS files.
     *
     * @return A list of all relative paths to the CSS files.
     */
    List<String> getCSSPath();
}
