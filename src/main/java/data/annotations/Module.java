package data.annotations;

import java.lang.annotation.*;

/**
 * Marks the class as a module, which then makes it searchable for the main window.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Module
{
    String name();
}
