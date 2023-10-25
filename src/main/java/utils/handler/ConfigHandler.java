package utils.handler;

import general.Start;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class ConfigHandler extends AbstractHandler
{

    /**
     * Gets the path of the directory / folder of the currently running jar.
     *
     * @return The path to the folder, where the running jar is inside.
     */
    public static Path getCurrentWorkingDir()
    {
        // Take a different approach to retrieving the source folder, if the program is not run inside a jar.
        if (isNotRunningInJar())
        {
            return Path.of(System.getProperty("user.dir"));
        }

        try
        {
            return new File(Start.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getParentFile().toPath();
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if the program is currently running inside a jar or as code in a project.
     *
     * @return Whether the code is not running inside a jar.
     */
    public static boolean isNotRunningInJar()
    {
        // Retrieve this class from built code. If the file-path starts with "jar:file:" instead of "file:", it is run in jar.
        URL url = ConfigHandler.class.getResource("ConfigHandler.class");
        if (url == null)
        {
            throw new RuntimeException("Couldn't find \"ConfigHandler.class\".");
        }

        return !url.toString().startsWith("jar:");
    }
}
