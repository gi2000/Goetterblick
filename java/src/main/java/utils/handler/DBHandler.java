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
    private static final String DB_FOLDER = "db";

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
        return path != null && path.toFile().exists();
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
        if (path == null)
        {
            return null;
        }

        Connection out;
        Path dbPath = Path.of("db").resolve(path);

        // Create the base folder structure of the main db folders.
        Path homeDir = Utils.getCurrentWorkingDir().resolve(dbPath);
        if (!homeDir.toFile().exists())
        {
            // Check if the folders were created
            if (!homeDir.getParent().toFile().mkdirs())
            {
                LOG.error("Couldn't create folders: " + homeDir);
            }
        }

        // Now open the connection to the db. This creates a new db, if it isn't already present.
        LOG.info("Creating new db at location: " + path);
        out = openDB(dbPath, true);
        return out;
    }

    /**
     * Opens the given database, if it exists.
     *
     * @param path The path directly referencing the db file. Use {@code getMainDBDirPath()} to retrieve the current working
     *             directory and the database directory.
     * @return The opened connection to the given database.
     */
    public static Connection openDB(Path path) {
        return openDB(path, false);
    }

    /**
     * Opens the given database, if it exists.
     *
     * @param path The path directly referencing the db file. Use {@code getMainDBDirPath()} to retrieve the current working
     *             directory and the database directory.
     * @return The opened connection to the given database.
     */
    private static Connection openDB(Path path, boolean isInitialCreation)
    {
        // Catch early errors like path being null or the file not existing.
        if (path == null || (!isInitialCreation && !Utils.getCurrentWorkingDir().resolve(path).toFile().exists()))
        {
            return null;
        }

        Connection con = null;
        try
        {
            // Trying to instantiate the driver manually to avoid a weird bug.
            Class.forName("org.sqlite.JDBC");

            // Establish "connection" to file via Java's Database connection framework.
            con = DriverManager.getConnection("jdbc:sqlite:" + path);
            LOG.debug("Opening db at location: " + path);
        } catch (SQLException | ClassNotFoundException e)
        {
            LOG.error("Couldn't create database.", e);
        }

        return con;
    }

    /**
     * This returns the main or meta db, which holds all information regarding f. ex. caching, last opened sites etc.
     *
     * @return The meta database.
     */
    public static Path getPathToMetaDB()
    {
        return Utils.getCurrentWorkingDir().resolve(DB_FOLDER).resolve("meta.db");
    }
}
