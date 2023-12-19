package data.translations.ui.general;

import data.annotations.DefaultCfgValue;
import data.general.Tuple;
import utils.general.Utils;

import java.util.Locale;

public interface TFiles
{
    // ####################
    // Config path segments
    // ####################
    String SEGM_TRANSL        = "transl";
    String SEGM_STARTUP       = "startup";
    String DIR_NAME           = "lang";
    String FILE_FORMAT_STRING = Utils.getCurrentWorkingDir().resolve(DIR_NAME).resolve("%s.properties").toString();

    // #############
    // Config values
    // #############
    @DefaultCfgValue
    Tuple<String, String> DEFAULT_CFG_STARTUP_LANGUAGE = new Tuple<>(Utils.joinSegms(SEGM_TRANSL, DIR_NAME, SEGM_STARTUP),
            new Locale("de", "DE").toLanguageTag());
}
