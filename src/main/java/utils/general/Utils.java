package utils.general;

import data.general.Tuple;
import general.Start;
import javafx.scene.image.Image;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;
import utils.handler.TranslationHandler;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All-purpose utility-methods, that can be used anywhere.
 */
public abstract class Utils
{
    /**
     * Returns the current date and time as a string object, already formated.
     *
     * @return The date and time currently.
     */
    public static String getCurrentDateAndTime()
    {
        SimpleDateFormat f = new SimpleDateFormat();
        f.applyPattern("EEEE', 'dd.MM.yyyy '-' HH:mm:ss '('S'ms)'");
        return f.format(new Date(System.currentTimeMillis()));
    }

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
        URL url = Utils.class.getResource("Utils.class");
        if (url == null)
        {
            throw new RuntimeException("Couldn't find \"Utils.class\".");
        }

        return !url.toString().startsWith("jar:");
    }

    /**
     * Joins the given segments together as a path separated by dots.
     *
     * @param segments The path segments of the full path.
     * @return The joined segments as one path separated by dots.
     */
    public static String joinSegms(String... segments)
    {
        return String.join(".", segments);
    }

    /**
     * Joins all strings together in a path with the system dependent symbol.
     *
     * @param paths The paths to connect as one.
     * @return The fully connected path string.
     */
    public static String joinPath(String... paths)
    {
        return String.join(s(), paths);
    }

    /**
     * Joins all strings together in a path with the system dependent symbol. Use this method, if the files are inside a jar.
     *
     * @param paths The paths to connect as one, when accessing a file inside a jar.
     * @return The fully connected path string from inside a jar.
     */
    public static String joinJarPath(String... paths)
    {
        return String.join("/", paths);
    }

    /**
     * Retrieves the given tuple from the given config.
     *
     * @param cfg   The config or translation file holding the key-value pair.
     * @param tuple The key-value pair with the value being the default fallback value.
     * @param <T>   The class, which is supposed to be returned.
     * @return The value from the config f. ex. a String, Double, Integer etc.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getVal(Configuration cfg, Tuple<String, T> tuple)
    {
        Object obj = cfg.get(tuple.getVal2().getClass(), tuple.getVal1());
        T out = tuple.getVal2();

        if (obj != null && obj.getClass() == tuple.getVal2().getClass())
        {
            out = (T) obj;
        }

        return out;
    }

    /**
     * Capitalizes the first letter of the String with the currently selected translation locale.
     *
     * @param s The String of which the first character is to be capitalized.
     * @return The same String but with the first character capitalized.
     */
    public static String cap(String s)
    {
        return s.substring(0, 1).toUpperCase(TranslationHandler.getCurrLocale()) + s.substring(1);
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
