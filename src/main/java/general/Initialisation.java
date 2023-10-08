package general;

import data.consts.ConstScreen;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Initialisation extends Application
{
    public static void main(String[] args)
    {
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
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage)
    {
        // Root node, which holds everything in the scene as one of their children.
        Parent root;
        try
        {
            String fxmlName = ConstScreen.FXML_MAIN_WINDOW;

            // Path to fxml
            URL fxmlURL = getClass().getClassLoader().getResource(fxmlName);
            if (fxmlURL == null)
            {
                // If not found, exit the program.
                stage.close();
                throw new RuntimeException("Couldn't load \"" + fxmlName + "\".");
            }

            root = FXMLLoader.load(fxmlURL);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        // Create a new scene with the given window sizes
        Scene scene = new Scene(root, ConstScreen.SCREEN_WIDTH, ConstScreen.SCREEN_HEIGHT);

        stage.setTitle(ConstScreen.FXML_MAIN_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}
