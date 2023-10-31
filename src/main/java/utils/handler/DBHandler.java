package utils.handler;

import utils.general.Utils;

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
        return Utils.getCurrentWorkingDir().resolve("db").resolve("main.db");
    }
}
