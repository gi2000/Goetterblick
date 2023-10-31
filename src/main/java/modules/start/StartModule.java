package modules.start;

import data.annotations.Module;
import data.consts.ConstScreen;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modules.general.abstracts.AbstractModule;
import modules.general.facades.IController;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.ConfigHandler;
import utils.handler.LoggerHandler;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Module(name = "start", isDisplayedInHome = false)
@SuppressWarnings(value = "unused")
public class StartModule extends AbstractModule
{

    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private Parent      root;
    private IController controller;

    @Override
    public boolean initScreen(Stage stage, String screenTitle)
    {
        setStage(stage);
        setScreenTitle(screenTitle);

        // Load content from external files and load up controller
        loadFiles(getRoot(), loadFXML());
        return initController();
    }

    @Override
    protected boolean initController()
    {
        return true;
    }

    @Override
    public boolean deconstructScreen()
    {
        getController().deconstruct();
        return false;
    }

    @Override
    public String getPrevModule()
    {
        return null;
    }

    @Override
    public boolean displayScreen()
    {
        getStage().show();
        return true;
    }

    /**
     * Adds a scene, if there isn't one present yet.
     */
    private void loadScene()
    {
        Configuration cfg = ConfigHandler.getMainConfig();

        int minWidth = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal1(), ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal2());
        int minHeight = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal1(), ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal2());
        int width = cfg.getInt(ConstScreen.DEFAULT_SCREEN_WIDTH.getVal1(), ConstScreen.DEFAULT_SCREEN_WIDTH.getVal2());
        int height = cfg.getInt(ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal1(), ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal2());
        boolean startMaximized = cfg.getBoolean(ConstScreen.DEFAULT_TOGGLE_FULLSCREEN.getVal1(),
                ConstScreen.DEFAULT_TOGGLE_FULLSCREEN.getVal2());

        Stage stage = getStage();
        Scene scene = new Scene(getRoot(), width, height);

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setScene(scene);
        stage.setMaximized(startMaximized);

        LOG.debug("Starting up window with minimum sizes: min-width = " + minWidth + "p, min-height = " + minHeight + "p");
    }

    /**
     * Loads the icons for the initial window buildup.
     */
    private void loadIcons()
    {
        // Get all icon paths in descending order and loads them to the current icon cache
        getStage().getIcons().addAll(Utils.loadImagesFromResources(getClass(),
                ConstScreen.FXML_START_ICON_512,
                ConstScreen.FXML_START_ICON_256,
                ConstScreen.FXML_START_ICON_128,
                ConstScreen.FXML_START_ICON_64));
    }

    /**
     * Loads in the fxml and css files.
     *
     * @param oldRoot The old root pane. May be null.
     * @param newRoot The new root pane, replacing the old one. Not null.
     */
    private void loadFiles(Parent oldRoot, Parent newRoot)
    {
        if (oldRoot == null || oldRoot.getScene() == null)
        {
            // If the current root isn't set yet, then the entire window doesn't exist yet -> Build it from grounds up.
            setRoot(newRoot);
            loadIcons();
            loadScene();
        }
        else
        {
            // If the scene is already set, then change the scene to this newly loaded fxml scene.
            setRoot(newRoot);
            getStage().getScene().setRoot(getRoot());
        }

        loadCSS();
    }

    @Override
    protected URL loadCSS(String cssPath)
    {
        return getClass().getResource(cssPath);
    }

    // ###############
    // Getter & Setter
    // ###############

    public Parent getRoot()
    {
        return root;
    }

    public void setRoot(Parent root)
    {
        this.root = root;
    }

    @Override
    public URL getFXMLPath()
    {
        return getClass().getResource(ConstScreen.FXML_START_WINDOW);
    }

    @Override
    public List<URL> getCSSPaths()
    {
        return Utils.toList(ConstScreen.FXML_START_CSS)
                .stream()
                .map(StartModule.class::getResource)
                .collect(Collectors.toList());
    }

    @Override
    public URL getModuleImage()
    {
        return null;
    }

    @Override
    public String getName()
    {
        return "Start";
    }
}
