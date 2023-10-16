package modules.general.abstracts;

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

public abstract class AbstractModule implements IModule
{

    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    protected String prevModule;

    private   Stage       stage;
    protected IController controller;

    private String screenTitle;


    public AbstractModule()
    {

    }

    @Override
    public boolean initScreen(Stage stage, String screenTitle)
    {
        // Initialize stage and title
        setStage(stage);
        setScreenTitle(screenTitle);

        // Load initial FXML and set it as the current root
        Parent root = loadFXML();
        if (root == null)
        {
            LOG.error("Couldn't load the FXML in module \"" + getName() + "\".");
            return false;
        }

        // Load up the controller (in which also the view and model are loaded)
        if (!initController(stage))
        {
            LOG.error("Couldn't load the controller in module \"" + getName() + "\".");
            return false;
        }

        // Update the root node and display the screen.
        getStage().getScene().setRoot(root);
        return displayScreen();
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
            LOG.error("Couldn't load FXML file for current module: \"" + getName() + "\"", new RuntimeException());
            return null;
        }

        try
        {
            // Insert the fxml contents into current stage
            parent = FXMLLoader.load(fxmlUrl);

            // If the scene is already set, then change the scene to this newly loaded fxml scene.
            if (getStage().getScene() != null)
            {
                getStage().getScene().rootProperty().setValue(parent);
            }
        }
        catch (IOException e)
        {
            LOG.error("Couldn't load FXML file for current module: \"" + getName() + "\"", e);
            return null;
        }

        LOG.debug("Loaded FXML file for current module: \"" + getName() + "\"");
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
        List<String> cssPaths = getCSSPaths();
        getStage().getScene().getStylesheets().clear();

        for (String cssPath : cssPaths)
        {
            URL cssURL = getClass().getClassLoader().getResource(cssPath);

            // If file was found, apply css
            if (cssURL != null)
            {
                // Turns the URL into a String representation
                String css = cssURL.toExternalForm();
                getStage().getScene().getStylesheets().add(css);
                LOG.debug("Loaded css file \"" + cssPath + "\" for current module \"" + getName() + "\"");
            }
            else
            {
                LOG.error("Couldn't load the CSS stylesheet \"" + cssPath + "\" for current module \"" + getName() + "\"");
                return false;
            }
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
     * Initializes this module's controller and with it the relevant view and model.
     *
     * @param stage The main stage / scene / window, where everything will be displayed in.
     * @return Whether the controller was successfully created.
     */
    protected abstract boolean initController(Stage stage);

    /**
     * The relative path to the FXML file.
     *
     * @return The relative path to the FXML file.
     */
    protected abstract URL getFXMLPath();

    /**
     * The relative path to the CSS files.
     *
     * @return A list of all relative paths to the CSS files.
     */
    protected abstract List<String> getCSSPaths();

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
