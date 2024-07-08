package utils.handler;

import org.slf4j.Logger;
import utils.general.Utils;

import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends AbstractHandler
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Creates or opens the database at the given path location.
     *
     * @param path The path directly referencing the db file. Use {@code getMainDBDirPath()} to retrieve the current working
     *             directory and the database directory.
     * @return The opened connection to the given database.
     */
    public static Connection createOrOpen(Path path)
    {
        return dbExists(path) ? openDB(path) : createDB(path);
    }

    /**
     * Checks whether the database at the given location exists.
     *
     * @param path The path directly referencing the db file. Use {@code getMainDBDirPath()} to retrieve the current working
     *             directory and the database directory.
     * @return True, if the database already exists.
     */
    public static boolean dbExists(Path path)
    {
        if (path == null)
        {
            return false;
        }

        return path.toFile().exists();
    }

    /**
     * Creates the given database, if it doesn't already exist.
     *
     * @param path The path directly referencing the db file. Use {@code getMainDBDirPath()} to retrieve the current working
     *             directory and the database directory.
     * @return The opened connection to the given database.
     */
    public static Connection createDB(Path path)
    {
        if (path == null || path.toFile().exists())
        {
            return null;
        }

        Connection out = null;
        LOG.info("Creating new db at location: " + path);

        // Create the base folder structure of the main db folders.
        Path homeDir = getMainDBDirPath();
        if (!homeDir.toFile().exists())
        {
            // Check if the folders were created
            if (!homeDir.toFile().mkdirs())
            {
                LOG.error("Couldn't create folders: " + homeDir);
            }
        }

        // Now open the connection to the db, if the folders exist. This creates a new db, if it isn't already present.
        if (homeDir.toFile().exists())
        {
            out = openDB(path);
        }

        return out;
    }

    /**
     * Opens the given database, if it exists.
     *
     * @param path The path directly referencing the db file. Use {@code getMainDBDirPath()} to retrieve the current working
     *             directory and the database directory.
     * @return The opened connection to the given database.
     */
    public static Connection openDB(Path path)
    {
        // Catch early errors like path being null or the file not existing.
        if (path == null || !path.toFile().exists())
        {
            return null;
        }

        Connection conn = null;
        try
        {
            // Establish "connection" to file via Java's Database connection framework.
            conn = DriverManager.getConnection("jdbc:sqlite:" + path);
            LOG.debug("Opening db at location: " + path);
        }
        catch (SQLException e)
        {
            LOG.trace(e.getMessage());
        }

        return conn;
    }

    /**
     * The main path where all db files or folders are stored in. It's the current working directory, followed by the folder "db".
     *
     * @return The main db directory path.
     */
    public static Path getMainDBDirPath()
    {
        return Utils.getCurrentWorkingDir().resolve("db");
    }

    /**
     * This returns the main or meta db, which holds all information regarding f. ex. caching, last opened sites etc.
     *
     * @return The meta database.
     */
    public static Path getPathToMetaDB()
    {
        return getMainDBDirPath().resolve("meta.db");
    }
}
