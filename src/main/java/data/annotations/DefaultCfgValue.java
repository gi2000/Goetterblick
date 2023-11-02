package data.annotations;

import java.lang.annotation.*;

/**
 * Marks the class variable as a default config value and exports it to the main config.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DefaultCfgValue
{

}
