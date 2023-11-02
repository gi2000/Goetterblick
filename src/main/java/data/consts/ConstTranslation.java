package data.consts;

import data.annotations.DefaultCfgValue;
import data.general.Tuple;
import utils.general.Utils;

import java.util.Locale;

public interface ConstTranslation
{
    // ####################
    // Config path segments
    // ####################
    String UI   = "ui";
    String DATA            = "data";
    String SEGM_MODULES    = "modules";
    String SEGM_START    = "start";
    String SEGM_VERSIONS = "versions";
    String SEGM_TRANSL    = "transl";
    String SEGM_STARTUP = "startup";

    // #########################
    //   Path to translations
    // #########################
    String TRANSL_DIR_NAME = "lang";

    String TRANSL_FILE_STRING_FORMAT = Utils.getCurrentWorkingDir().resolve(TRANSL_DIR_NAME).resolve("%s.properties").toString();

    // #############
    // Config values
    // #############

    @DefaultCfgValue
    Tuple<String, String> DEFAULT_CFG_STARTUP_LANGUAGE = new Tuple<>(Utils.joinSegms(SEGM_TRANSL, ConstTranslation.TRANSL_DIR_NAME, SEGM_STARTUP),
                                                                     new Locale("de", "DE").toLanguageTag());


    // ################
    // Translation Keys
    // ################

    // --- Overarching categories ---

    // --- StartModule ---
    String MODULES_START_VERSIONS_DSA1   = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa1");
    String MODULES_START_VERSIONS_DSA2   = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa2");
    String MODULES_START_VERSIONS_DSA3   = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa3");
    String MODULES_START_VERSIONS_DSA4   = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa4");
    String MODULES_START_VERSIONS_DSA4d1 = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa4d1");
    String MODULES_START_VERSIONS_DSA5   = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa5");
    String MODULES_START_VERSIONS_DSK    = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsk");
    String MODULES_START_VERSIONS_MYR    = Utils.joinSegms(UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "myranor");
}
