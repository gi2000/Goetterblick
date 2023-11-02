package general;

import data.consts.ConstTranslation;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.stage.Stage;
import modules.general.facades.IModule;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;
import utils.handler.ModuleHandler;
import utils.handler.TranslationHandler;

import java.lang.invoke.MethodHandles;

public class Initialisation extends Application
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args)
    {
        // Application lock to only allow one instance running at the same time to prevent deadlocks in db usages
        String appId = "Götterblick";
        if (!isAppAlreadyRunning(appId))
        {
            launch(args);
        }
        else
        {
            LOG.error("An instance of \"" + appId + "\" is already running. Shutting down this new instance.");
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage)
    {
        // Load initial language
        TranslationHandler.updateTranslations();

        // Get the starting module and load it.
        IModule startModule = ModuleHandler.getInstance("start");
        if (startModule == null)
        {
            throw new RuntimeException("Couldn't load an initial starting module.");
        }

        // Load up the module and display it.
        if (startModule.initScreen(stage, "Home"))
        {
            startModule.displayScreen();
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
}
