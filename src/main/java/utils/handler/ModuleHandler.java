package utils.handler;

import data.annotations.Module;
import modules.general.abstracts.AbstractModule;
import modules.general.facades.IModule;
import org.reflections.Reflections;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A utility class, that helps in creation, modification or other things with modules.
 */
public class ModuleHandler extends AbstractHandler
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Factory Pattern: Creates an instance of the given module name. The name corresponds with the @Module-Annotation name.
     *
     * @param moduleName The modules name / id, as written in their name-value in the @Module-Annotation.
     * @return An instance of the given module.
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

        return getInstanceOfClass(moduleClass);
    }

    /**
     * Retrieves all modules, that are supposed to be displayed in the home window.
     *
     * @return A list of all modules with isDisplayedInHome set to true.
     */
    @Nonnull
    public static List<IModule> getAllModules()
    {
        // Find all classes with the annotation "modules".
        Reflections reflections = new Reflections("modules");
        Class<Module> annotation = Module.class;
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(annotation);

        // Of these filter all those out, that don't match the given name.
        return types.stream()
                .filter(clazz -> clazz.getAnnotation(annotation).isDisplayedInHome())
                .filter(AbstractModule.class::isAssignableFrom)
                .map(ModuleHandler::getInstanceOfClass)
                .collect(Collectors.toList());
    }

    /**
     * Takes the given class and creates an instance of it.
     *
     * @param moduleClass The class of which an instance should be created.
     * @return The created instance of an IModule.
     */
    private static IModule getInstanceOfClass(Class<?> moduleClass)
    {
        // Find the one constructor, that is empty and with which an instance of this new module can be created.
        Constructor<?> emptyConstructor = null;
        Constructor<?>[] constructors = moduleClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors)
        {
            // Get constructor with no parameters
            if (constructor.getGenericParameterTypes().length == 0)
            {
                emptyConstructor = constructor;
                break;
            }
        }

        // If no constructor was found, just stop and don't start guessing arguments.
        if (emptyConstructor == null)
        {
            LOG.warn("Couldn't find an empty constructor of the module class \"" + moduleClass.getAnnotation(Module.class).name() +
                     "\".");
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
