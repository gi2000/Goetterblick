package general;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import javafx.application.Application;
import modules.start.StartView;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;

import java.lang.invoke.MethodHandles;

public class Initialisation
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args)
    {
        // Application lock to only allow one instance running at the same time to prevent deadlocks in db usages
        String appId = "Götterblick";
        if (!isAppAlreadyRunning(appId))
        {
            Application.launch(StartView.class);
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
}
