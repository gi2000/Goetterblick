package data.consts;

import utils.handler.AbstractHandler;

public interface ConstScreen
{
    // ###########################
    //     General Properties
    // Export later to a .cfg file
    // ###########################
    String FXML_MAIN_TITLE = "Götterblick";

    int SCREEN_HEIGHT = 720;
    int SCREEN_WIDTH  = 1080;

    int SCREEN_MIN_HEIGHT = 480;
    int SCREEN_MIN_WIDTH  = 720;

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

    static String join(String... paths)
    {
        return String.join("/", paths);
    }

    static String s()
    {
        return AbstractHandler.s();
    }
}
