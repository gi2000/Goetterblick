package utils.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The LoggerHandler produces LOG-instances for easier access. Still, it is recommended for easier bug finding to include a code
 * section at relevant classes like the following code snippet:<br>
 * <br>
 * {@code private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());}
 */
public abstract class LoggerHandler
{

    private LoggerHandler()
    {
        // Do nothing
    }

    /**
     * Returns a logger with the given class as the executor.
     *
     * @param clazz The class of which the logger should report from.
     * @return A log-instance to log events with.
     * @apiNote Create an instance like so:
     * {@code private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());}
     */
    public static Logger getLogger(Class<?> clazz)
    {
        return LoggerFactory.getLogger(clazz);
    }
}
