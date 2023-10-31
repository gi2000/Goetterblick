package data.consts;

import data.general.Tuple;
import utils.general.Utils;

import java.io.File;

public interface ConstCfg
{

    // This one value cannot be changed, as it is the "root" settings file, from which every other path or meta value will be read.
    File CFG_FILE = new File(Utils.getCurrentWorkingDir().toString() + Utils.s() + "cfg" + Utils.s() + "settings.properties");

    // ########################
    // Settings Key-Value-Pairs
    // ########################

    // Default database directory
    Tuple<String, String> PATH_DB_DIR   = new Tuple<>("paths.db.dir", Utils.getCurrentWorkingDir().resolve("db").toString());
    // Default language directory
    Tuple<String, String> PATH_LANG_DIR = new Tuple<>("paths.lang.dir", Utils.getCurrentWorkingDir().resolve("lang").toString());

}
