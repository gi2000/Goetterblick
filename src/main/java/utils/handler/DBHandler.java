package utils.handler;

import org.slf4j.Logger;
import utils.general.Utils;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends AbstractHandler
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    public static Connection createOrOpen(String file)
    {
        return dbExists(file) ? openDB(file) : createDB(file);
    }

    public static boolean dbExists(String file)
    {
        if (file == null)
        {
            return false;
        }

        return new File(file).exists();
    }

    public static Connection createDB(String file)
    {
        LOG.info("Creating new db at location: " + file);
        return null;
    }


    public static Connection openDB(String file)
    {
        Connection conn = null;
        try
        {
            String url = "jdbc:sqlite:" + file;
            conn = DriverManager.getConnection(url);

            LOG.debug("Opening db at location: " + file);
        }
        catch (SQLException e)
        {
            LOG.trace(e.getMessage());
        }

        return conn;
    }

    public static Path getMainDbPath()
    {
        return Utils.getCurrentWorkingDir().resolve("db").resolve("main.db");
    }
}
