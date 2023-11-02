package data.consts;

import data.annotations.DefaultCfgValue;
import data.general.Tuple;
import utils.general.Utils;

public interface ConstScreen
{
    // ###################
    // Config Key Segments
    // ###################

    String SEGM_UI = "ui";
    String SEGM_SCREENS = "screens";
    String SEGM_SIZES = "sizes";
    String SEGM_WINDOW = "window";

    // ##################
    // General Properties
    // ##################

    @DefaultCfgValue
    Tuple<String, Integer> DEFAULT_SCREEN_HEIGHT = new Tuple<>(Utils.joinSegms(SEGM_UI, SEGM_SCREENS, SEGM_SIZES, "start-height"), 720);
    @DefaultCfgValue
    Tuple<String, Integer> DEFAULT_SCREEN_WIDTH  = new Tuple<>(Utils.joinSegms(SEGM_UI, SEGM_SCREENS, SEGM_SIZES, "start-width"), 1080);
    @DefaultCfgValue
    Tuple<String, Integer> DEFAULT_SCREEN_MIN_HEIGHT = new Tuple<>(Utils.joinSegms(SEGM_UI, SEGM_SCREENS, SEGM_SIZES, "min-height"), 480);
    @DefaultCfgValue
    Tuple<String, Integer> DEFAULT_SCREEN_MIN_WIDTH = new Tuple<>(Utils.joinSegms(SEGM_UI, SEGM_SCREENS, SEGM_SIZES, "min-width"), 720);
    @DefaultCfgValue
    Tuple<String, Boolean> DEFAULT_START_MAXIMIZED  = new Tuple<>(Utils.joinSegms(SEGM_UI, SEGM_SCREENS, SEGM_WINDOW, "start-maximized"), false);
    @DefaultCfgValue
    Tuple<String, Boolean> DEFAULT_TOGGLE_DARKMODE  = new Tuple<>(Utils.joinSegms(SEGM_UI, SEGM_SCREENS, SEGM_WINDOW, "darkmode"), true);

    // ############
    // Start-Module
    // ############
    String FXML_MAIN_TITLE = "Götterblick";
    String FXML_START_WINDOW = "main-window.fxml";
    String FXML_START_CSS    = "main-theme.css";

    String FXML_START_ICON_DIR = "icons";

    String FXML_START_ICON_64  = Utils.joinPath(FXML_START_ICON_DIR, "icon-square-64.png");
    String FXML_START_ICON_128 = Utils.joinPath(FXML_START_ICON_DIR, "icon-square-128.png");
    String FXML_START_ICON_256 = Utils.joinPath(FXML_START_ICON_DIR, "icon-square-256.png");
    String FXML_START_ICON_512 = Utils.joinPath(FXML_START_ICON_DIR, "icon-square-512.png");

    // #################
    // Charcreate-Module
    // #################

    // #################
    // GM-Screen-Module
    // #################

    // #################
    // Map-Module
    // #################

    // #################
    // Wiki-Module
    // #################
}
