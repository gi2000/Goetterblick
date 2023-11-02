package utils.handler;

import data.consts.ConstTranslation;
import data.consts.ConstCfg;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.util.Locale;

public class TranslationHandler
{

    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private static Locale        CURR_LOCALE;
    private static Configuration TRANSLATIONS;

    public static String getTransl(String key)
    {
        return TRANSLATIONS.getString(key);
    }

    public static Configuration getSubsetOfTransls(String key)
    {
        return TRANSLATIONS.subset(key);
    }

    /**
     * Updates the translations by getting the currently set language from the config.
     */
    public static void updateTranslations()
    {
        TRANSLATIONS = loadLanguage(ConfigHandler.getMainConfig().getString(ConstTranslation.DEFAULT_CFG_STARTUP_LANGUAGE.getVal1(),
                                                                            ConstTranslation.DEFAULT_CFG_STARTUP_LANGUAGE.getVal2()));
        LOG.debug("System Locale: " + Locale.getDefault().getDisplayLanguage() + " (" + Locale.getDefault().toLanguageTag() +
                  ") - Current Translations: " + CURR_LOCALE.getDisplayLanguage() + " (" + CURR_LOCALE.toLanguageTag() + ")");
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
        File f = Path.of(String.format(ConstTranslation.TRANSL_FILE_STRING_FORMAT, CURR_LOCALE.toLanguageTag())).toFile();
        if (!f.exists())
        {
            LOG.error("Couldn't find file from path: " + f.toPath());
            return null;
        }

        Parameters params = new Parameters();
        Configuration out;

        // Creates a builder that can create a new configuration based on a file.
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>
                (PropertiesConfiguration.class).configure(params.fileBased().setFile(f));

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
     * Sets the new locale for translation evaluation.
     *
     * @param currLocale The current, new locale.
     */
    public static void setCurrLocale(Locale currLocale)
    {
        TranslationHandler.CURR_LOCALE = currLocale;
    }
}
