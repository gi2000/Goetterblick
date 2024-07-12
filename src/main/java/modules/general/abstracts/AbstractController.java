package modules.general.abstracts;

import data.consts.ConstScreen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import modules.start.ConstStartModule;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.ConfigHandler;
import utils.handler.LoggerHandler;
import utils.handler.TranslationHandler;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The abstract controller class of the MVC pattern. Extend this class to create the controller for the new module.
 */
public abstract class AbstractController implements IController
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private Stage stage;

    private IModel model;
    private IView  view;

    // ############################
    // Initializing / Loading Scene
    // ############################

    @Override
    public boolean initScreen(Stage stage)
    {
        // Initially set the stage, if this is the first time the app is launched.
        if (getStage() == null)
        {
            setStage(stage);
        }

        // Load initial language
        TranslationHandler.updateTranslations();

        // Load FXML files and (if existant) replace the old scene.
        loadFiles(getRoot(), loadFXML());
        setScreenTitle(Utils.cap(getModuleName()));

        setView(createView());
        setModel(createModel());

        return getModel().initialize(getModuleName()) && getView().initialize() && initElements() && displayScreen();
    }

    /**
     * Loads in the fxml and css files.
     *
     * @param oldRoot The old root pane. May be null.
     * @param newRoot The new root pane, replacing the old one. Not null.
     */
    protected void loadFiles(Parent oldRoot, Parent newRoot)
    {
        if (oldRoot == null || oldRoot.getScene() == null)
        {
            // If the current root isn't set yet, then the entire window doesn't exist yet -> Build it from grounds up.
            loadScene(newRoot);
            loadIcons();
        } else
        {
            // If the scene is already set, then change the scene to this newly loaded fxml scene.
            getStage().getScene().setRoot(newRoot);
        }

        loadCSS();
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
            LOG.error("Couldn't load FXML file for current view: {}", getClass().getSimpleName(),
                    new RuntimeException());
            return null;
        }

        try
        {
            // Insert the fxml contents into current stage
            parent = FXMLLoader.load(fxmlUrl);
        } catch (IOException e)
        {
            LOG.error("Couldn't load FXML file for current view: {}", getClass().getSimpleName(), e);
            return null;
        }

        LOG.debug("Loaded FXML file for current view \"{}\": {}", getClass().getSimpleName(), fxmlUrl);
        return parent;
    }

    /**
     * Loads the icons for the initial window buildup.
     */
    private void loadIcons()
    {
        // Get all icon paths in descending order and loads them to the current icon cache
        getStage().getIcons()
                  .addAll(Utils.loadImagesFromResources(AbstractController.class, ConstStartModule.FXML_START_ICON_512,
                          ConstStartModule.FXML_START_ICON_256, ConstStartModule.FXML_START_ICON_128,
                          ConstStartModule.FXML_START_ICON_64));
    }

    /**
     * Adds a scene, if there isn't one present yet.
     */
    private void loadScene(Parent newRoot)
    {
        Configuration cfg = ConfigHandler.getMainConfig();

        // Retrieve all values from config and if there is none present, take the default value.
        int minWidth = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal1(),
                ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal2());
        int minHeight = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal1(),
                ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal2());
        int width = cfg.getInt(ConstScreen.DEFAULT_SCREEN_WIDTH.getVal1(), ConstScreen.DEFAULT_SCREEN_WIDTH.getVal2());
        int height =
                cfg.getInt(ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal1(), ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal2());
        boolean startMaximized = cfg.getBoolean(ConstScreen.DEFAULT_START_MAXIMIZED.getVal1(),
                ConstScreen.DEFAULT_START_MAXIMIZED.getVal2());

        Stage stage = getStage();
        Scene scene = new Scene(newRoot, width, height);

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setScene(scene);
        stage.setMaximized(startMaximized);

        LOG.debug("Starting up window with minimum sizes: min-width = {}p, min-height = {}p", minWidth, minHeight);
    }

    /**
     * Displays the css and stage, once everything is loaded.
     *
     * @return Whether the css loading and thus the display was successful;
     */
    public boolean displayScreen()
    {
        boolean out = loadCSS();
        if (out)
        {
            getStage().show();
            LOG.debug("Successfully displaying the view now: \"{}\"", getClass().getSimpleName());
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
        List<URL> cssPaths = new ArrayList<>();
        cssPaths.add(getMainCSS());
        cssPaths.addAll(getCSSPaths());
        getStage().getScene().getStylesheets().clear();

        for (URL cssURL : cssPaths)
        {
            if (cssURL == null)
            {
                LOG.error("Couldn't find css of view: " + getClass().getName());
                continue;
            }
            // Turns the URL into a String representation
            String css = cssURL.toExternalForm();
            getStage().getScene().getStylesheets().add(css);
            LOG.debug("Loaded css file \"{}\" for current view: \"{}\"", cssURL.getFile(), getClass().getSimpleName());
        }

        return true;
    }

    // ###########################
    //

    @Override
    public boolean deconstruct(boolean isModuleSwitch)
    {
        // If this is a module switch, then deconstruct the model, too. Lazy loading allows for the second getModel()
        // .deconstruct()
        // to not get executed, if isModuleSwitch is set to false.
        return (!isModuleSwitch || getModel().deconstruct()) && getView().deconstruct();
    }

    @Override
    public boolean switchToModule(IController controller)
    {
        getModel().setPrevModule(getModuleName());
        return loadNewModule(controller);
    }

    private boolean loadNewModule(IController controller)
    {
        return controller.initScreen(getStage());
    }

    // ################
    // Abstract Methods
    // ################

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

    // #################
    // Getter and Setter
    // #################

    protected URL getMainCSS()
    {
        return AbstractController.class.getResource(ConstScreen.FXML_CSS_MAIN);
    }

    public IModel getModel()
    {
        return model;
    }

    public void setModel(IModel model)
    {
        this.model = model;
    }

    public IView getView()
    {
        return view;
    }

    public void setView(IView view)
    {
        this.view = view;
    }

    public Parent getRoot()
    {
        if (getStage() == null || getStage().getScene() == null)
        {
            return null;
        }

        return getStage().getScene().getRoot();
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * Sets the new screen title and immediately updates the title.
     *
     * @param screenTitle The new title for this window. The name of the application will stay as a prefix, followed
     *                    by a dash "-".
     */
    protected void setScreenTitle(String screenTitle)
    {
        getStage().setTitle(ConstStartModule.FXML_MAIN_TITLE + " - " + screenTitle);
    }
}
