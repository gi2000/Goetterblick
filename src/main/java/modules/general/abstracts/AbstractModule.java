package modules.general.abstracts;

import data.consts.ConstCfg;
import data.consts.ConstScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import modules.general.facades.IController;
import modules.general.facades.IModule;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;

/**
 * The abstract class for sub-areas such as the character creation, dungeon master, settings screen and so on. Implement this class
 * instead of the {@code IModule} interface for easier integration.
 */
public abstract class AbstractModule implements IModule
{

    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    protected String prevModule;

    private   Stage       stage;
    protected IController controller;

    private String screenTitle;

    @Override
    public boolean initScreen(Stage stage, String screenTitle)
    {
        setStage(stage);
        setScreenTitle(screenTitle);

        return loadFiles() && initController() && displayScreen();
    }

    /**
     * Loads all relevant files for the window to be displayed.
     *
     * @return Whether everything was loaded successfully.
     */
    protected boolean loadFiles()
    {
        Parent root = loadFXML();
        if (root == null)
        {
            return false;
        }

        // Update the root node and display the screen.
        getStage().getScene().setRoot(root);
        return loadCSS();
    }

    /**
     * Loads the external fxml file.
     *
     * @return Whether the new window was successfully loaded.
     */
    protected Parent loadFXML()
    {
        // Retrieve the fxml file.
        Parent parent;
        URL fxmlUrl = getFXMLPath();
        if (fxmlUrl == null)
        {
            LOG.error("Couldn't load FXML file for current module: " + getName(), new RuntimeException());
            return null;
        }

        try
        {
            // Insert the fxml contents into current stage
            parent = FXMLLoader.load(fxmlUrl);
        }
        catch (IOException e)
        {
            LOG.error("Couldn't load FXML file for current module: " + getName(), e);
            return null;
        }

        LOG.debug("Loaded FXML file for current module: " + getName());
        return parent;
    }

    @Override
    public boolean displayScreen()
    {
        boolean out = loadCSS();
        if (out)
        {
            getStage().show();
        }

        return out;
    }

    /**
     * Loads the external css stylesheet.
     *
     * @return Whether the new stylesheet was successfully loaded.
     */
    protected boolean loadCSS()
    {
        // Apply css styles from external stylesheet
        List<URL> cssPaths = getCSSPaths();
        getStage().getScene().getStylesheets().clear();

        for (URL cssURL : cssPaths)
        {
            // Turns the URL into a String representation
            String css = cssURL.toExternalForm();
            getStage().getScene().getStylesheets().add(css);
            LOG.debug("Loaded css file \"" + cssURL.getFile() + "\" for current module \"" + getName() + "\".");
        }

        return true;
    }

    @Override
    public boolean deconstructScreen()
    {
        setPrevModule(getName());
        return true;
    }

    // ################
    // Abstract Methods
    // ################

    /**
     * Initializes this module's controller and with it the relevant view and model. This does not need to be implemented, if the
     * developer decides to include a controller in the FXML file. If the developer decides to not include on, this is the method to
     * call for FXML injection and controller initialisation.
     *
     * @return Whether the controller was successfully created.
     */
    protected abstract boolean initController();

    /**
     * The absolute path to the FXML file.
     *
     * @return The absolute path to the FXML file.
     */
    protected abstract URL getFXMLPath();

    /**
     * The absolute path to the CSS files.
     *
     * @return A list of all absolute paths to the CSS files.
     */
    protected abstract List<URL> getCSSPaths();

    /**
     * This hook-method directly and <b>only</b> loads the css via the following code:<br>
     * <code>return getClass().getResource(cssPath);</code><br>
     * This line of code needs to be implemented in each module, since the files can't be found otherwise when compiled to a jar.
     *
     * @param cssPath The path to the css file.
     * @return The URL to the given css path.
     */
    protected abstract URL loadCSS(String cssPath);

    // ###############
    // Getter & Setter
    // ###############

    /**
     * The title for the window screen, that is currently displayed. May change when contents in the window itself changes.
     *
     * @return The current screen title.
     */
    protected String getScreenTitle()
    {
        return screenTitle;
    }

    /**
     * Sets the new screen title and immediately updates the title.
     *
     * @param screenTitle The new title for this window. The name of the application will stay as a prefix, followed by a dash "-".
     */
    protected void setScreenTitle(String screenTitle)
    {
        this.screenTitle = screenTitle;
        getStage().setTitle(ConstScreen.FXML_MAIN_TITLE + " - " + getScreenTitle());
    }

    @Override
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    @Override
    public Stage getStage()
    {
        return stage;
    }

    public IController getController()
    {
        return controller;
    }

    public void setController(IController controller)
    {
        this.controller = controller;
    }

    @Override
    public String getPrevModule()
    {
        return prevModule;
    }

    public void setPrevModule(String prevModule)
    {
        this.prevModule = prevModule;
    }
}
