package utils.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All-purpose utility-methods, that can be used anywhere.
 */
public class Utils
{
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
