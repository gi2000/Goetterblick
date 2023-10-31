package data.consts;

import data.general.Tuple;
import utils.handler.AbstractHandler;

public interface ConstScreen
{
    // ##################
    // General Properties
    // ##################
    String FXML_MAIN_TITLE = "Götterblick";

    // ################################
    // TODO Export later to a .cfg file
    // ################################
    Tuple<String, Integer> DEFAULT_SCREEN_HEIGHT = new Tuple<>("ui.screens.height", 720);
    Tuple<String, Integer> DEFAULT_SCREEN_WIDTH  = new Tuple<>("ui.screens.width", 1080);

    Tuple<String, Integer> DEFAULT_SCREEN_MIN_HEIGHT = new Tuple<>("ui.screens.min-height", 480);
    Tuple<String, Integer> DEFAULT_SCREEN_MIN_WIDTH  = new Tuple<>("ui.screens.min-width", 720);

    Tuple<String, Boolean> DEFAULT_TOGGLE_FULLSCREEN = new Tuple<>("ui.screens.start-maximized", false);

    // ############
    // Start-Module
    // ############
    String FXML_START_WINDOW = "main-window.fxml";
    String FXML_START_CSS    = "main-theme.css";

    String FXML_START_ICON_DIR = "icons";

    String FXML_START_ICON_64  = join(FXML_START_ICON_DIR, "icon-square-64.png");
    String FXML_START_ICON_128 = join(FXML_START_ICON_DIR, "icon-square-128.png");
    String FXML_START_ICON_256 = join(FXML_START_ICON_DIR, "icon-square-256.png");
    String FXML_START_ICON_512 = join(FXML_START_ICON_DIR, "icon-square-512.png");

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

    // Helping Methods

    /**
     * Joins all strings together in a path with the system dependent symbol.
     *
     * @param paths The paths to connect as one.
     * @return The fully connected path string.
     */
    static String join(String... paths)
    {
        return String.join(s(), paths);
    }

    /**
     * Returns the operating system dependent file separator.
     *
     * @return The OS specific file separator.
     */
    static String s()
    {
        return AbstractHandler.s();
    }
}
