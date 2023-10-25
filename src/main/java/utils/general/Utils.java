package utils.general;

import javafx.scene.image.Image;
import org.slf4j.Logger;
import utils.handler.LoggerHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * All-purpose utility-methods, that can be used anywhere.
 */
public class Utils
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
                        log.error("Couldn't find image: \"" + imgPath + "\"");
                    }
                    else
                    {
                        log.debug("Added image: \"" + out + "\"");
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
        return new ArrayList<>(Arrays.asList(elements));
    }
}
