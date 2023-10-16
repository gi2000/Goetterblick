package utils.handler;

import data.annotations.Module;
import modules.general.abstracts.AbstractModule;
import modules.general.facades.IModule;
import org.reflections.Reflections;
import org.slf4j.Logger;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * A utility class, that helps in creation, modification or other things with modules.
 */
public class ModuleHandler
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Factory Pattern: Creates an instance of the given module name. The name corresponds with the @Module-Annotation name.
     *
     * @param moduleName
     * @return
     */
    public static IModule getInstance(String moduleName)
    {
        // Find all classes with the annotation "modules".
        Reflections reflections = new Reflections("modules");
        Class<Module> annotation = Module.class;
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(annotation);

        // Of these filter all those out, that don't match the given name.
        Class<?> moduleClass = types.stream()
                .filter(clazz -> clazz.getAnnotation(annotation).name().equalsIgnoreCase(moduleName))
                .filter(AbstractModule.class::isAssignableFrom)
                .findAny().orElse(null);

        // If none match, return nothing.
        if (moduleClass == null)
        {
            LOG.warn("Couldn't find any class with the given name \"" + moduleName + "\".");
            return null;
        }

        // Find the one constructor, that is empty and with which an instance of this new module can be created.
        Constructor<?> emptyConstructor = null;
        Constructor<?>[] constructors = moduleClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors)
        {
            if (constructor.getGenericParameterTypes().length == 0)
            {
                emptyConstructor = constructor;
                break;
            }
        }

        // If no constructor was found, just stop and don't start guessing arguments.
        if (emptyConstructor == null)
        {
            LOG.warn("Couldn't find an empty constructor of the module class with the name \"" + moduleName + "\".");
            return null;
        }

        try
        {
            // Try to create a new instance with the empty constructor
            return (IModule) emptyConstructor.newInstance();
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e)
        {
            LOG.error("Couldn't create an instance of the module class \"" + moduleClass.getSimpleName() + "\".", e);
            return null;
        }
    }
}
