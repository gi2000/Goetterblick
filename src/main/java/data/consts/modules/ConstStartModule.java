package data.consts.modules;

import data.annotations.DefaultCfgValue;
import data.enums.DSAVersion;
import data.general.Tuple;
import utils.general.Utils;

public interface ConstStartModule
{
    String FXML_MAIN_TITLE   = "Götterblick";
    String FXML_START_WINDOW = "start.fxml";
    String FXML_START_CSS    = "start.css";

    String FXML_START_ICON_DIR = "icons";
    String FXML_START_ICON_64  = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-64.png");
    String FXML_START_ICON_128 = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-128.png");
    String FXML_START_ICON_256 = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-256.png");
    String FXML_START_ICON_512 = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-512.png");


    // #############
    // CSS Constants
    // #############

    String CSS_VERSIONS_SELECTED_VERSION = "selected-version";
    String CSS_MODULES_FONT_ICON         = "module-icons";

    // ###################
    // Config Key Segments
    // ###################

    String SEGM_START    = "start";
    String SEGM_VERSIONS = "versions";

    // ##################
    // General Properties
    // ##################

    @DefaultCfgValue
    Tuple<String, Integer>    FXML_START_AMT_VERSIONS_PER_ROW = new Tuple<>(
            Utils.joinSegms(SEGM_START, SEGM_VERSIONS, "amount-per-row"),
            4);
    @DefaultCfgValue
    Tuple<String, DSAVersion> DSA_STARTING_VERSION            = new Tuple<>(
            Utils.joinSegms(SEGM_START, SEGM_VERSIONS, "selected-starting-version"), DSAVersion.DSA4d1);
}
