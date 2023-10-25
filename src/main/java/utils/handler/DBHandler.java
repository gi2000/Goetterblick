package utils.handler;

import java.nio.file.Path;
import java.sql.Connection;

public class DBHandler extends AbstractHandler
{
    public static Connection openDB(String file)
    {
        return null;
    }

    public static Path getDBWorkingDir()
    {
        return ConfigHandler.getCurrentWorkingDir().resolve("db" + s() + "main.db");
    }
}
