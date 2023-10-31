package data.consts;

import utils.general.Utils;

public interface ConstTranslation
{
    // #########################
    //   Path to translations
    // #########################

    String TRANSL_FILE_STRING_FORMAT = Utils.getCurrentWorkingDir().resolve("lang").resolve("%s.properties").toString();

    // ################
    // Translation Keys
    // ################

    // --- Overarching categories ---
    String UI   = "ui";
    String DATA = "data";


    // --- Sub paths ---
    String MODULES  = "modules";
    String START    = "start";
    String VERSIONS = "versions";

    // --- StartModule ---
    String MODULES_START_VERSIONS_DSA1   = j(UI, MODULES, START, VERSIONS, "dsa1");
    String MODULES_START_VERSIONS_DSA2   = j(UI, MODULES, START, VERSIONS, "dsa2");
    String MODULES_START_VERSIONS_DSA3   = j(UI, MODULES, START, VERSIONS, "dsa3");
    String MODULES_START_VERSIONS_DSA4   = j(UI, MODULES, START, VERSIONS, "dsa4");
    String MODULES_START_VERSIONS_DSA4d1 = j(UI, MODULES, START, VERSIONS, "dsa4d1");
    String MODULES_START_VERSIONS_DSA5   = j(UI, MODULES, START, VERSIONS, "dsa5");
    String MODULES_START_VERSIONS_DSK    = j(UI, MODULES, START, VERSIONS, "dsk");
    String MODULES_START_VERSIONS_MYR    = j(UI, MODULES, START, VERSIONS, "myranor");


    /**
     * Joins the strings together and creates a path with '.' as the separator.
     *
     * @param translElements The elements of the translation key.
     * @return The concatenated String of translation key path elements.
     */
    private static String j(String... translElements)
    {
        return String.join(".", translElements);
    }
}
