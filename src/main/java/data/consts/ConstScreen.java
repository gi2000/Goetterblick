package data.consts;

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
    String FXML_START_WINDOW      = "modules/start/main-window.fxml";
    String FXML_START_CSS         = "modules/start/main-theme.css";
    String FXML_START_ICON_FOLDER = "modules/start/icons/";
    String FXML_START_ICON_64     = FXML_START_ICON_FOLDER + "icon-square-64.png";
    String FXML_START_ICON_128    = FXML_START_ICON_FOLDER + "icon-square-128.png";
    String FXML_START_ICON_256    = FXML_START_ICON_FOLDER + "icon-square-256.png";
    String FXML_START_ICON_512    = FXML_START_ICON_FOLDER + "icon-square-512.png";

    // ################
    // GM-Screen-Module
    // ################

    // ################
    // Map-Module
    // ################

    // ################
    // Wiki-Module
    // ################

    // ################
    // Map-Module
    // ################
}
