package utils.handler;

import data.translations.ui.general.TFiles;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Locale;

public class TranslationHandler
{

    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private static Locale        CURR_LOCALE;
    private static Configuration TRANSLATIONS;

    /**
     * Gets the translation with the given translation key.
     *
     * @param key The translation key.
     * @return The translation with the currently selected language.
     */
    public static String getTransl(String key)
    {
        return TRANSLATIONS.getString(key);
    }

    /**
     * Returns a subset of translations for the given prefix. For example, if the properties file contained 3 keys like this:<br>
     * <br>
     * <p>{@code a.test.prefix.a = translation 1}</p>
     * <p>{@code a.test.prefix.b = translation 2}</p>
     * <p>{@code other.test.prefix.c = translation 3}</p>
     * <br>
     * And this method was given the prefix "a.test.prefix" then a subset of this configuration, only containing the first 2 values,
     * will be returned.
     *
     * @param prefix The prefix / segment of a path, that a subset is supposed to me made of.
     * @return The subset configuration.
     */
    public static Configuration getSubsetOfTransls(String prefix)
    {
        return TRANSLATIONS.subset(prefix);
    }

    /**
     * Updates the translations by getting the currently set language from the config.
     */
    public static void updateTranslations()
    {
        TRANSLATIONS = loadLanguage(ConfigHandler.getMainConfig().getString(TFiles.DEFAULT_CFG_STARTUP_LANGUAGE.getVal1(),
                TFiles.DEFAULT_CFG_STARTUP_LANGUAGE.getVal2()));
        LOG.debug("System Locale: " + Locale.getDefault().getDisplayLanguage() + " (" + Locale.getDefault().toLanguageTag() +
                  ") - Current Translations: " + CURR_LOCALE.getDisplayLanguage() + " (" + CURR_LOCALE.toLanguageTag() + ")");
    }

    /**
     * Gets the translation config for the currently selected language.
     *
     * @return The translation config containing all translations.
     */
    public static Configuration getTranslationsMap()
    {
        return TRANSLATIONS;
    }

    // ###############
    // Private Methods
    // ###############

    /**
     * Loads the given language with f. ex. "en" and "GB" as the locale tag.
     *
     * @param langTag The language tag of the locale tag, f. ex. "en-US".
     */
    private static Configuration loadLanguage(String langTag)
    {
        setCurrLocale(Locale.forLanguageTag(langTag));

        // Load the translation file for the currently set locale
        Configuration cfg = getTranslationsFile();

        // If no translation file was found named after the language and variant, then just select the default language (German).
        if (cfg == null && TRANSLATIONS == null)
        {
            return loadDefaultLanguage();
        }

        return cfg;
    }

    /**
     * Loads the default translations (German).
     *
     * @return Returns the German translations.
     */
    private static Configuration loadDefaultLanguage()
    {
        setCurrLocale(new Locale("de", "DE"));
        return getTranslationsFile();
    }

    /**
     * Retrieves the currently set language (the default set language) translations.
     *
     * @return The properties file of the current default locale.
     */
    private static Configuration getTranslationsFile()
    {
        // Retrieve current language file.
        File f = Path.of(String.format(TFiles.FILE_FORMAT_STRING, CURR_LOCALE.toLanguageTag())).toFile();
        if (!f.exists())
        {
            LOG.error("Couldn't find file from path: " + f.toPath());
            return null;
        }

        Parameters params = new Parameters();
        Configuration out;

        // Creates a builder that can create a new configuration based on a file.
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>
                (PropertiesConfiguration.class).configure(
                params.fileBased().setEncoding(StandardCharsets.UTF_8.displayName()).setFile(f));

        try
        {
            // Read the language file - The config contains all properties read from the file
            out = builder.getConfiguration();
        }
        catch (ConfigurationException e)
        {
            LOG.error("Couldn't load file from path: " + f.toPath(), e);
            return null;
        }

        LOG.debug("Loaded language (" + CURR_LOCALE.toLanguageTag() + ") from file: " + f.toPath());
        return out;
    }

    /**
     * Gets the current locale for translations.
     *
     * @return The current locale.
     */
    public static Locale getCurrLocale()
    {
        return CURR_LOCALE;
    }

    /**
     * Sets the new locale for translation evaluation.
     *
     * @param currLocale The current, new locale.
     */
    public static void setCurrLocale(Locale currLocale)
    {
        TranslationHandler.CURR_LOCALE = currLocale;
    }
}
