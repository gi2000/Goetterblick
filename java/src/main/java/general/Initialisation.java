package general;

import data.consts.ConstScreen;
import data.enums.Module;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modules.general.abstracts.AbstractController;
import modules.start.ConstStartModule;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.ConfigHandler;
import utils.handler.LoggerHandler;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;

public class Initialisation extends Application
{
    public static final Module STARTING_MODULE = Module.START;

    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());
    public static Stage  STAGE;
    public static Scene  SCENE;
    public static Parent ROOT;

    public static void main(String[] args)
    {
        // Application lock to only allow one instance running at the same time to prevent deadlocks in db usages
        String appId = "Götterblick";
        if (!isAppAlreadyRunning(appId))
        {
            System.err.println(
                    "The next warning is due to the fact that this Java Maven project is not setup as a java module. " +
                    "This could " +
                    "cause issues later, but is most likely harmless. See this StackOverflow thread:");
            System.err.println("https://stackoverflow.com/a/67854230");
            launch(args);
        }
        else
        {
            LOG.error("An instance of \"" + appId + "\" is already running. Shutting down this new instance.");
            System.exit(1);
        }
    }

    /**
     * Checks for a lock placed onto this application.
     *
     * @return Whether the application is already running currently.
     */
    private static boolean isAppAlreadyRunning(String appId)
    {
        boolean alreadyRunning;
        try
        {
            JUnique.acquireLock(appId);
            alreadyRunning = false;
        }
        catch (AlreadyLockedException e)
        {
            alreadyRunning = true;
        }

        return alreadyRunning;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        Initialisation.STAGE = stage;

        // Try and get URL of FXML
        Class<? extends AbstractController> controllerClass = STARTING_MODULE.controller;
        URL fxmlURL = controllerClass.getResource(Module.START.fxmlPath);
        List<URL> cssURLs = Module.START.cssUrls.stream().map(controllerClass::getResource).toList();
        URL standardCSS = AbstractController.class.getResource(ConstScreen.FXML_CSS_MAIN);
        if (fxmlURL == null || standardCSS == null)
        {
            RuntimeException e = new RuntimeException("Unable to load starting window.");
            LOG.error("Couldn't load startup window - Missing file: {}", Module.START.fxmlPath, e);
            throw e;
        }

        // Load via FXMLLoader
        ROOT = FXMLLoader.load(fxmlURL);
        loadScene();
        loadIcons();
        reloadDefaultCss();

        SCENE.getStylesheets().add(standardCSS.toExternalForm());
        for (URL cssURL : cssURLs)
        {
            SCENE.getStylesheets().add(cssURL.toExternalForm());
        }
        stage.setScene(SCENE);
        stage.show();
    }

    /**
     * Loads the icons for the initial window buildup.
     */
    private void loadIcons()
    {
        // Get all icon paths in descending order and loads them to the current icon cache
        STAGE.getIcons()
                .addAll(Utils.loadImagesFromResources(AbstractController.class, ConstStartModule.FXML_START_ICON_512,
                        ConstStartModule.FXML_START_ICON_256, ConstStartModule.FXML_START_ICON_128,
                        ConstStartModule.FXML_START_ICON_64));
    }

    /**
     * Adds a scene, if there isn't one present yet.
     */
    private void loadScene()
    {
        Configuration cfg = ConfigHandler.getMainConfig();

        // Retrieve all values from config and if there is none present, take the default value.
        int minWidth = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal1(), ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal2());
        int minHeight = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal1(), ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal2());
        int width = cfg.getInt(ConstScreen.DEFAULT_SCREEN_WIDTH.getVal1(), ConstScreen.DEFAULT_SCREEN_WIDTH.getVal2());
        int height = cfg.getInt(ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal1(), ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal2());
        boolean startMaximized = cfg.getBoolean(ConstScreen.DEFAULT_START_MAXIMIZED.getVal1(),
                ConstScreen.DEFAULT_START_MAXIMIZED.getVal2());

        SCENE = new Scene(ROOT, width, height);

        STAGE.setMinWidth(minWidth);
        STAGE.setMinHeight(minHeight);
        STAGE.setScene(SCENE);
        STAGE.setMaximized(startMaximized);

        LOG.debug("Starting up window with minimum sizes: min-width = {}p, min-height = {}p", minWidth, minHeight);
    }

    public static void loadRoot(Module module) throws Exception
    {
        URL fxmlPath = module.controller.getResource(module.fxmlPath);
        List<URL> cssPaths = module.cssUrls.stream().map(module.controller::getResource).toList();

        if (fxmlPath == null)
        {
            RuntimeException e = new RuntimeException("Unable to load " + module.name() + " module.");
            LOG.error("Couldn't load {} window - Missing file: {}", module.name(), module.fxmlPath, e);
            throw e;
        }

        ROOT = FXMLLoader.load(fxmlPath);
        SCENE.setRoot(ROOT);
        reloadDefaultCss();
        SCENE.getStylesheets().addAll(cssPaths.stream().map(URL::toExternalForm).toList());
    }

    /**
     * Loads the external css stylesheet. Warning, removes any previous css stylesheets!
     */
    protected static void reloadDefaultCss()
    {
        List<URL> cssURLs = Utils.toList(ConstScreen.FXML_CSS_MAIN).stream().map(AbstractController.class::getResource).toList();

        // Apply css styles from external stylesheet
        SCENE.getStylesheets().clear();
        for (URL cssURL : cssURLs)
        {
            if (cssURL == null)
            {
                LOG.error("Couldn't find css of view: " + Initialisation.class.getName());
                continue;
            }

            // Turns the URL into a String representation
            String css = cssURL.toExternalForm();
            SCENE.getStylesheets().add(css);
            LOG.debug("Loaded css file \"{}\" for current view: \"{}\"", cssURL.getFile(), Initialisation.class.getSimpleName());
        }

    }
}
