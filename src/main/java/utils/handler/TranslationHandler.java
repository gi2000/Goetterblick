package utils.handler;

import data.consts.ConstTranslation;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;

public class TranslationHandler
{

    private static final Logger     LOG          = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());
    private static       Properties TRANSLATIONS = getTranslationsFile();

    public static String getTransl(String key)
    {
        return TRANSLATIONS.getProperty(key);
    }

    /**
     * Updates the translations by getting the currently set language from the config.
     */
    public static void updateTranslations()
    {
        TRANSLATIONS = getTranslationsFile();
    }

    // ###############
    // Private Methods
    // ###############

    /**
     * Loads the given language with f. ex. "en" and "GB" as the locale tag.
     *
     * @param lang    The first part of the locale tag, f. ex. "en".
     * @param variant The second part of the locale tag, f. ex. "GB".
     */
    private static void loadLanguage(String lang, String variant)
    {
        Locale locale = new Locale(lang, variant);
        Locale.setDefault(locale);

        // Load the translation file for the currently set locale
        Properties props = getTranslationsFile();
        // If no translation file was found named after the language and variant, then just select the default language (German).
        if (props == null && TRANSLATIONS == null)
        {
            TRANSLATIONS = loadDefaultLanguage();
        }
    }

    /**
     * Loads the default translations (German).
     *
     * @return Returns the German translations.
     */
    private static Properties loadDefaultLanguage()
    {
        Locale.setDefault(new Locale("de", "DE"));
        return getTranslationsFile();
    }

    /**
     * Retrieves the currently set language (the default set language) translations.
     *
     * @return The properties file of the current default locale.
     */
    private static Properties getTranslationsFile()
    {
        // Retrieve current language file.
        File f = Path.of(String.format(ConstTranslation.TRANSL_FILE_STRING_FORMAT, Locale.getDefault().toLanguageTag())).toFile();
        if (!f.exists())
        {
            LOG.error("Couldn't find file from path: " + f.toPath());
            return null;
        }

        // Load the contents of the file into a properties object.
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(f))
        {
            props.load(in);
        }
        catch (IOException e)
        {
            LOG.error("Couldn't load file from path: " + f.toPath(), e);
            return null;
        }

        LOG.debug("Loaded language \"" + Locale.getDefault().toLanguageTag() + "\" from file: " + f.toPath());
        return props;
    }
}
