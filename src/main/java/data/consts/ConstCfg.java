package data.consts;

import data.annotations.DefaultCfgValue;
import data.general.Tuple;
import data.translations.ui.general.TFiles;
import utils.general.Utils;

import java.io.File;
import java.nio.file.Path;

public interface ConstCfg
{

    // This one value cannot be changed, as it is the "root" settings file, from which every other path or meta value will be read.
    File CFG_FILE =
            Path.of(Utils.getCurrentWorkingDir().toString()).resolve("cfg").resolve("settings.properties").toFile();

    // ###################
    // Config Key Segments
    // ###################

    // --- Meta ---
    String SEGM_PATH = "path";
    String SEGM_DIR = "dir";

    // ########################
    // Settings Key-Value-Pairs
    // ########################

    // --- Default directories ---
    @DefaultCfgValue
    Tuple<String, String> DEFAULT_PATH_DB_DIR = new Tuple<>(Utils.joinSegms(SEGM_PATH, ConstDB.DB_DIR_NAME, SEGM_DIR),
            Utils.getCurrentWorkingDir().resolve(ConstDB.DB_DIR_NAME).toString());
    @DefaultCfgValue
    Tuple<String, String> DEFAULT_PATH_LANG_DIR = new Tuple<>(Utils.joinSegms(SEGM_PATH, TFiles.DIR_NAME, SEGM_DIR),
            Utils.getCurrentWorkingDir().resolve(TFiles.DIR_NAME).toString());


}
