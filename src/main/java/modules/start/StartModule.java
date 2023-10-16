package modules.start;

import data.annotations.Module;
import data.consts.ConstScreen;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modules.general.abstracts.AbstractModule;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.LoggerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;

@Module(name = "start")
@SuppressWarnings(value = "unused")
public class StartModule extends AbstractModule
{

    private              Parent root;
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public boolean initScreen(Stage stage, String screenTitle)
    {
        // Initialize stage and title
        setStage(stage);
        setScreenTitle(screenTitle);
        Parent root = loadFXML();

        // If the current root isn't set yet, then the entire window doesn't exist yet -> Build it from grounds up.
        if (getRoot() == null)
        {
            setRoot(root);
            loadIcons();
            loadScene();
        }

        // Load up the controller (in which also the view and model are loaded) and then the window itself.
        if (!initController(getStage()))
        {
            LOG.error("Couldn't load the controller for module \"" + getName() + "\".");
            return false;
        }

        // Load initial FXML and set it as the current root
        getStage().getScene().setRoot(getRoot());
        return displayScreen();
    }

    @Override
    protected boolean initController(Stage stage)
    {
        return true;
    }

    @Override
    public boolean deconstructScreen()
    {
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
        // Load CSS and display it, if successfully loaded.
        boolean out = loadCSS();
        if (out)
        {
            getStage().show();
        }
        return out;
    }

    /**
     * Adds a scene, if there isn't one present yet.
     */
    private void loadScene()
    {
        int minWidth = ConstScreen.SCREEN_MIN_WIDTH;
        int minHeight = ConstScreen.SCREEN_MIN_HEIGHT;

        Stage stage = getStage();
        Scene scene = new Scene(getRoot(), ConstScreen.SCREEN_WIDTH, ConstScreen.SCREEN_HEIGHT);

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setScene(scene);

        LOG.debug("Starting up window with minimum sizes: min-width = " + minWidth + "p, min-height = " + minHeight + "p");
    }

    /**
     * Loads the icons for the initial window buildup.
     */
    private void loadIcons()
    {
        // Get all icon paths in descending order.
        List<String> icons = Utils.toList(
                ConstScreen.FXML_START_ICON_512,
                ConstScreen.FXML_START_ICON_256,
                ConstScreen.FXML_START_ICON_128,
                ConstScreen.FXML_START_ICON_64
        );

        // Iterate over all icon paths to load them into the available icon cache
        for (String iconPath : icons)
        {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(iconPath))
            {
                if (stream != null)
                {
                    getStage().getIcons().add(new Image(stream));
                    LOG.debug("Added icon: \"" + iconPath + "\"");
                }
            }
            catch (IOException e)
            {
                LOG.error("Couldn't load an icon: \"" + iconPath + "\"");
                e.printStackTrace();
            }
        }
    }

    public Parent getRoot()
    {
        return root;
    }

    public void setRoot(Parent root)
    {
        this.root = root;
    }

    public URL getFXMLPath()
    {
        return getClass().getClassLoader().getResource(ConstScreen.FXML_START_WINDOW);
    }

    public List<String> getCSSPaths()
    {
        return Utils.toList(ConstScreen.FXML_START_CSS);
    }

    public URL getModuleImage()
    {
        return null;
    }

    public String getName()
    {
        return "Start";
    }
}
