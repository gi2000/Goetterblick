package utils.handler;

import data.consts.ConstCfg;
import data.consts.ConstScreen;
import data.general.Tuple;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationUtils;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.slf4j.Logger;
import utils.general.Utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class ConfigHandler extends AbstractHandler
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private static       Configuration          MAIN_CFG_MAP;
    private static final List<Tuple<String, ?>> ALL_DEFAULT_CFG_VALUES = Utils.toList
            (
                    ConstCfg.PATH_DB_DIR,
                    ConstCfg.PATH_LANG_DIR,
                    ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT,
                    ConstScreen.DEFAULT_SCREEN_MIN_WIDTH,
                    ConstScreen.DEFAULT_SCREEN_HEIGHT,
                    ConstScreen.DEFAULT_SCREEN_WIDTH,
                    ConstScreen.DEFAULT_TOGGLE_FULLSCREEN
            );

    /**
     * Returns the configurations loaded when the app was started.
     *
     * @return The configuration holding all infos about meta-data like screen size or the database directory path.
     */
    public static Configuration getMainConfig()
    {
        if (MAIN_CFG_MAP == null)
        {
            MAIN_CFG_MAP = getOrCreateMainConfig();
        }

        return MAIN_CFG_MAP;
    }

    // ###############
    // Private Methods
    // ###############

    private static Configuration getOrCreateMainConfig()
    {
        File cfgFile = ConstCfg.CFG_FILE;

        // If file doesn't exist, first create directories and then the file itself.
        if (!cfgFile.exists())
        {
            return createNewMainCfg();
        }
        else
        {
            return loadConfig(ConstCfg.CFG_FILE.toPath());
        }
    }

    /**
     * Creates a default configuration - while creating a file at the given position for it - and returns it.
     *
     * @return The default Configuration.
     */
    private static Configuration createNewMainCfg()
    {
        Configuration cfg = new PropertyListConfiguration();

        for (Tuple<String, ?> tuple : ALL_DEFAULT_CFG_VALUES)
        {
            cfg.addProperty(tuple.getVal1(), tuple.getVal2());
        }

        saveConfig(cfg, ConstCfg.CFG_FILE.toPath());
        return cfg;
    }

    /**
     * Loads the config at the given path and returns it as a configuration object.
     *
     * @param path The path to the config.
     * @return A configuration object holding all data from that file.
     */
    private static Configuration loadConfig(Path path)
    {
        Parameters params = new Parameters();
        Configuration out;

        // Creates a builder that can create a new configuration based on a file.
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>
                (PropertiesConfiguration.class).configure(params.fileBased().setFile(path.toFile()));

        try
        {
            // Read the config - The config contains all properties read from the file
            out = builder.getConfiguration();
        }
        catch (ConfigurationException e)
        {
            LOG.error("Couldn't load the config file at path: " + path, e);
            return null;
        }

        LOG.debug("Loaded config from path: " + path);
        return out;
    }

    /**
     * Saves the given config at the given location and creates the necessary directories and the file itself.
     *
     * @param cfg  The configuration to save in a file format.
     * @param path The path to the configuration file.
     */
    private static void saveConfig(Configuration cfg, Path path)
    {
        // If a config file already exists but the creation process also failed, return unsuccessfully.
        File f = path.toFile();
        if (!f.exists() && !createConfigFileAndDirs(path))
        {
            return;
        }

        // Open a writer, that will print all configuration values to the given cfg file.
        try (PrintWriter out = new PrintWriter(f, StandardCharsets.UTF_8))
        {
            ConfigurationUtils.dump(cfg, out);
        }
        catch (IOException e)
        {
            LOG.error("IOException thrown when dumping the configuration into the new file at given path: " +
                      path, e);
            return;
        }

        LOG.debug("Created new file at given file path: " + path);
    }

    /**
     * Creates an empty file at the given path plus the needed parent directories, if they aren't present.
     *
     * @param path The file to create.
     * @return Whether the creation was successful or not.
     */
    private static boolean createConfigFileAndDirs(Path path)
    {
        File f = path.toFile();
        try
        {
            // Try to create file and directories to file.
            if (!f.getParentFile().mkdirs() && !f.createNewFile())
            {
                LOG.error("Couldn't create new file and / or directories to given file path: " + path);
                return false;
            }
        }
        catch (IOException e)
        {
            LOG.error("IOException thrown when creating the new file and / or directories to given file path: " + path, e);
            return false;
        }

        LOG.debug("Created every folder and config file itself from path: " + path);
        return true;
    }
}
