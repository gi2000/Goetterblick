package general;

import data.consts.ConstScreen;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initialisation extends Application
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args)
    {
        // Application lock to only allow one instance running at the same time to prevent deadlocks in db usages
        String appId = "Götterblick";
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

        if (!alreadyRunning)
        {
            launch(args);
        }
        else
        {
            LOG.debug("An instance of \"" + appId + "\" is already running. Shutting down this new instance.");
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage)
    {
        // Root node, which holds everything in the scene as one of their children.
        Parent root = loadRoot(stage);

        // Create a new scene with the given window sizes
        Scene scene = new Scene(root, ConstScreen.SCREEN_WIDTH, ConstScreen.SCREEN_HEIGHT);

        loadCSS(scene);
        loadIcon(stage);
        prepareStage(stage, scene);
    }

    /**
     * Loads the external fxml file.
     *
     * @param stage The stage, which the fxml is loaded onto.
     * @return The updated parent node.
     */
    private Parent loadRoot(Stage stage)
    {
        String fxmlPath = ConstScreen.FXML_MAIN_WINDOW;
        Parent root;
        try
        {

            // Path to fxml
            URL fxmlURL = getClass().getClassLoader().getResource(fxmlPath);
            if (fxmlURL == null)
            {
                // If not found, exit the program.
                stage.close();
                throw new RuntimeException("Couldn't load \"" + fxmlPath + "\".");
            }

            root = FXMLLoader.load(fxmlURL);
            LOG.debug("Loaded FXML file: \"" + fxmlPath + "\"");
        }
        catch (IOException e)
        {
            LOG.error("Couldn't load the FXML file: \"" + fxmlPath + "\"");
            throw new RuntimeException(e);
        }

        return root;
    }

    /**
     * Applies css styles from external stylesheet.
     *
     * @param scene The scene to which the css should be applied to.
     */
    private void loadCSS(Scene scene)
    {
        // Apply css styles from external stylesheet
        String cssPath = ConstScreen.FXML_MAIN_CSS;
        URL cssURL = getClass().getClassLoader().getResource(cssPath);

        // If file was found, apply css
        if (cssURL != null)
        {
            // Turns the URL into a String representation
            String css = cssURL.toExternalForm();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            LOG.debug("Loaded css file: \"" + cssPath + "\"");
        }
        else
        {
            LOG.error("Couldn't load the CSS stylesheet: \"" + cssPath + "\"");
        }
    }

    /**
     * Loads the icon.
     *
     * @param stage The main stage to which the icon should be applied to.
     */
    private void loadIcon(Stage stage)
    {
        // Get all icon paths in descending order.
        List<String> icons = toList(
                ConstScreen.FXML_MAIN_ICON_NAME_512,
                ConstScreen.FXML_MAIN_ICON_NAME_256,
                ConstScreen.FXML_MAIN_ICON_NAME_128,
                ConstScreen.FXML_MAIN_ICON_NAME_64,
                ConstScreen.FXML_MAIN_ICON_NAME_32
        );

        // Iterate over all icon paths to load them into the available icon cache
        for (String iconPath : icons)
        {
            try (InputStream stream = getClass().getClassLoader().getResourceAsStream(iconPath))
            {
                if (stream != null)
                {
                    stage.getIcons().add(new Image(stream));
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

    /**
     * Prepare the main window.
     *
     * @param stage The stage that is to be altered.
     * @param scene The scene that is to be altered.
     */
    private void prepareStage(Stage stage, Scene scene)
    {
        int minWidth = 1080;
        int minHeight = 720;

        stage.setTitle(ConstScreen.FXML_MAIN_TITLE);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setScene(scene);
        stage.show();

        LOG.debug("Starting up window with minimum sizes: min-width = " + minWidth + "p, min-height = " + minHeight + "p");
    }

    /**
     * Returns a list of the given Strings.
     *
     * @return <b>List[String]</b> - A list consisting of all the
     */
    @SafeVarargs
    public static <T> List<T> toList(T... elements)
    {
        return new ArrayList<T>(Arrays.asList(elements));
    }
}
