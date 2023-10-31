package utils.general;

import general.Start;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import utils.handler.ConfigHandler;
import utils.handler.LoggerHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All-purpose utility-methods, that can be used anywhere.
 */
public abstract class Utils
{
    /**
     * Loads the given Images with the given class as the root in the resource directory.
     *
     * @param c     The root class, from which to search the directories.
     * @param paths The images to look for.
     * @return A list of all images.
     */
    public static List<Image> loadImagesFromResources(Class<?> c, String... paths)
    {
        Logger log = LoggerHandler.getLogger(c);
        return Stream.of(paths)
                .map(imgPath ->
                {
                    URL out = c.getResource(imgPath);

                    // If file was not found, log the error.
                    if (out == null)
                    {
                        log.error("Couldn't find image: " + imgPath);
                    }
                    else
                    {
                        log.debug("Added image: " + out);
                    }

                    return out;
                })
                // Filter out not-findable image
                .filter(Objects::nonNull)
                // Iterate over all image paths to load them
                .map(imgURL ->
                {
                    Image img = null;
                    try
                    {
                        img = new Image(imgURL.openStream());
                    }
                    catch (IOException e)
                    {
                        log.error("Couldn't load image.", e);
                    }

                    return img;
                }).collect(Collectors.toList());
    }

    /**
     * Returns a list of the given elements.
     *
     * @return A list consisting of all the elements, given via arguments.
     */
    @SafeVarargs
    public static <T> List<T> toList(T... elements)
    {
        return new ArrayList<>(new ArrayList<>(Arrays.asList(elements)));
    }

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

    /**
     * Returns the operating system dependent file separator.
     *
     * @return The OS specific file separator.
     */
    public static String s()
    {
        return File.separator;
    }

    /**
     * Returns the operating system dependent line separator.
     *
     * @return The os specific line separator.
     */
    public static String n()
    {
        return System.lineSeparator();
    }
}
