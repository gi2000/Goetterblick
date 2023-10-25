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
    /**
     * The modules name. Can be used in the {@code ModuleHandler} for creating instances of that module.
     *
     * @return The name / id of this module.
     */
    String name();

    /**
     * Whether this module should be displayed in the row below the DSA versions. F. ex. settings are not included in this, even
     * though they are technically a module. But the character creation module is included, since it is the first module on the left.
     *
     * @return Whether this module should be displayed in the row below the DSA versions.
     */
    boolean isDisplayedInHome() default true;
}
