package general;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import javafx.stage.Stage;
import modules.general.facades.IController;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;
import utils.handler.ModuleHandler;

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
            System.err.println(
                    "The next warning is due to the fact that this Java Maven project is not setup as a java module. " +
                            "This could " +
                            "cause issues later, but is most likely harmless. See this StackOverflow thread:");
            System.err.println("https://stackoverflow.com/a/67854230");
            launch(args);
        } else
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
        } catch (AlreadyLockedException e)
        {
            alreadyRunning = true;
        }

        return alreadyRunning;
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        IController contr = ModuleHandler.getInstance("start");
        if (contr == null)
        {
            RuntimeException e = new RuntimeException("No start module found.");
            LOG.error("No start module found.", e);
            throw e;
        }

        contr.initScreen(stage);
    }
}
