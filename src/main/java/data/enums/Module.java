package data.enums;

import data.translations.ui.modules.TAbstractModule;
import modules.chareditor.CharEditorController;
import modules.general.abstracts.AbstractController;
import modules.gm.GmController;
import modules.map.MapController;
import modules.settings.SettingsController;
import modules.start.ConstStartModule;
import modules.start.StartController;
import modules.wiki.WikiController;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum Module
{
    START(StartController.class, null, "start.fxml", toList("start.css"), false),
    SETTINGS(SettingsController.class, "bi-gear", "settings.fxml", toList("settings.css"), true),
    WIKI(WikiController.class, "bi-book", "wiki.fxml", toList("wiki.css"), true),
    CHAREDITOR(CharEditorController.class, "bi-person", "chareditor.fxml", toList("chareditor.css"), true),
    MAP(MapController.class, "bi-compass", "map.fxml", toList("map.css"), true),
    GM(GmController.class, "bi-map", "gm.fxml", toList("gm.css"), true);

    // ##################################
    // Variables, Methods and Constructor
    // ##################################

    public final Class<? extends AbstractController> controller;
    public final String                              fxmlPath;
    public final List<String>                        cssUrls;
    public final FontIcon                            moduleIcon;
    public final boolean                             isDisplayedInHome;

    /**
     * An enum to differentiate between Controllers and instantly get the locations of fxmls and css's.
     *
     * @param controller        The associated controller class, with this module.
     * @param moduleImg         The string of the font icon for the module.
     * @param fxmlPath          The path to the fxml, relative from the classes' ressource folder.
     * @param cssURLs           The path to the css's, relative from the classes' ressource folder.
     * @param isDisplayedInHome If true, then a module button will be added to the start screen for this module, if not already
     *                          present.
     */
    Module(Class<? extends AbstractController> controller, String moduleImg, String fxmlPath, List<String> cssURLs,
            boolean isDisplayedInHome)
    {
        this.controller = controller;
        this.isDisplayedInHome = isDisplayedInHome;
        this.fxmlPath = fxmlPath;
        this.cssUrls = cssURLs;
        this.moduleIcon = new FontIcon(moduleImg == null ? ConstStartModule.BUTTON_MODULE_DEFAULT_ICON : moduleImg);
    }

    /**
     * A utility method to add the given elements as a list.
     *
     * @param urls The URLs as strings.
     * @return The list containing all URLs.
     */
    private static List<String> toList(String... urls)
    {
        return Arrays.asList(urls);
    }

    /**
     * Returns the translation key for the given module tooltip in the home screen.
     *
     * @return The translation key of the module button's tooltip.
     */
    public String getTooltipTranslKey()
    {

        return buildDefaultPath() + this.name().toLowerCase() + "." + TAbstractModule.SEGM_GENERAL + "." +
               TAbstractModule.SEGM_TOOLTIP;
    }

    /**
     * Returns the translation key for the given module label in the home screen.
     *
     * @return The translation key of the module button's label.
     */
    public String getModuleButtonLabelKey()
    {
        return buildDefaultPath() + this.name().toLowerCase() + "." + TAbstractModule.SEGM_GENERAL + "." +
               TAbstractModule.SEGM_LABEL;
    }

    /**
     * The default build path for module related keys.
     *
     * @return The initial, default build path for module related keys.
     */
    public static String buildDefaultPath()
    {
        return TAbstractModule.SEGM_UI + "." + TAbstractModule.SEGM_MODULES + ".";
    }

    /**
     * Retrieves the module of the given controller.
     *
     * @param controllerClass The controller's class of the module.
     * @return The associated module.
     */
    public static Module getModuleFromController(Class<? extends AbstractController> controllerClass)
    {
        return Stream.of(values()).filter(module -> module.controller.equals(controllerClass)).findFirst().orElse(null);
    }
}
