package data.consts.modules;

import utils.general.Utils;

public interface ConstStartModule
{
    String FXML_MAIN_TITLE   = "Götterblick";
    String FXML_START_WINDOW = "main-window.fxml";
    String FXML_START_CSS    = "main-theme.css";

    String FXML_START_ICON_DIR = "icons";
    String FXML_START_ICON_64  = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-64.png");
    String FXML_START_ICON_128 = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-128.png");
    String FXML_START_ICON_256 = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-256.png");
    String FXML_START_ICON_512 = Utils.joinJarPath(FXML_START_ICON_DIR, "icon-512.png");
}
