package modules.general.abstracts.old;

import data.consts.ConstScreen;
import data.general.Tuple;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import modules.start.ConstStartModule;
import modules.start.StartView;
import org.apache.commons.configuration2.Configuration;
import org.kordamp.ikonli.javafx.FontIcon;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.LoggerHandler;
import utils.handler.TranslationHandler;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class DeprecatedAbstractView extends Application implements IView
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private IController controller;
    private IModel      model;

    protected Stage  stage;
    private   Parent root;
    private   String screenTitle;

    public DeprecatedAbstractView(IController controller)
    {
        if (controller == null)
        {
            throw new IllegalArgumentException("Controller was not initialized.");
        }

        this.controller = controller;
        this.model = controller.getModel();

        // Get the newly created model for the view and give the view itself to the controller.
        controller.setView(this);
    }

    @Override
    public void start(Stage stage)
    {
        // The name of the module to initially load.
        this.stage = stage;

        // Load initial language
        TranslationHandler.updateTranslations();

        // Load up the module and display it.
        initialize();
        LOG.debug("Initialized and started module: " + controller.getModuleName());
    }

    @Override
    public boolean initialize()
    {
        loadFiles(getRoot(), loadFXML());
        setScreenTitle(Utils.cap(getController().getModuleName()));
        return displayScreen();
    }

    /**
     * Loads in the fxml and css files.
     *
     * @param oldRoot The old root pane. May be null.
     * @param newRoot The new root pane, replacing the old one. Not null.
     */
    private void loadFiles(Parent oldRoot, Parent newRoot)
    {
        if (oldRoot == null || oldRoot.getScene() == null)
        {
            // If the current root isn't set yet, then the entire window doesn't exist yet -> Build it from grounds up.
            setRoot(newRoot);
            loadIcons();
            loadScene();
        } else
        {
            // If the scene is already set, then change the scene to this newly loaded fxml scene.
            setRoot(newRoot);
            getStage().getScene().setRoot(getRoot());
        }

        loadCSS();
    }

    /**
     * Loads the external fxml file.
     *
     * @return Whether the new window was successfully loaded.
     */
    protected Parent loadFXML()
    {
        // Retrieve the fxml file.
        Parent parent;
        URL fxmlUrl = getFXMLPath();
        if (fxmlUrl == null)
        {
            LOG.error("Couldn't load FXML file for current view: " + getClass().getSimpleName(),
                    new RuntimeException());
            return null;
        }

        try
        {
            // Insert the fxml contents into current stage
            parent = FXMLLoader.load(fxmlUrl);
        } catch (IOException e)
        {
            LOG.error("Couldn't load FXML file for current view: " + getClass().getSimpleName(), e);
            return null;
        }

        LOG.debug("Loaded FXML file for current view \"" + getClass().getSimpleName() + "\": " + fxmlUrl);
        return parent;
    }

    /**
     * Loads the icons for the initial window buildup.
     */
    private void loadIcons()
    {
        // Get all icon paths in descending order and loads them to the current icon cache
        getStage().getIcons()
                  .addAll(Utils.loadImagesFromResources(StartView.class, ConstStartModule.FXML_START_ICON_512,
                          ConstStartModule.FXML_START_ICON_256, ConstStartModule.FXML_START_ICON_128,
                          ConstStartModule.FXML_START_ICON_64));
    }

    /**
     * Adds a scene, if there isn't one present yet.
     */
    private void loadScene()
    {
        Configuration cfg = getModel().getMainCfg();

        // Retrieve all values from config and if there is none present, take the default value.
        int minWidth = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal1(),
                ConstScreen.DEFAULT_SCREEN_MIN_WIDTH.getVal2());
        int minHeight = cfg.getInt(ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal1(),
                ConstScreen.DEFAULT_SCREEN_MIN_HEIGHT.getVal2());
        int width = cfg.getInt(ConstScreen.DEFAULT_SCREEN_WIDTH.getVal1(), ConstScreen.DEFAULT_SCREEN_WIDTH.getVal2());
        int height =
                cfg.getInt(ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal1(), ConstScreen.DEFAULT_SCREEN_HEIGHT.getVal2());
        boolean startMaximized = cfg.getBoolean(ConstScreen.DEFAULT_START_MAXIMIZED.getVal1(),
                ConstScreen.DEFAULT_START_MAXIMIZED.getVal2());

        Stage stage = getStage();
        Scene scene = new Scene(getRoot(), width, height);

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setScene(scene);
        stage.setMaximized(startMaximized);

        LOG.debug("Starting up window with minimum sizes: min-width = " + minWidth + "p, min-height = " + minHeight +
                "p");
    }

    /**
     * Displays the css and stage, once everything is loaded.
     *
     * @return Whether the css loading and thus the display was successful;
     */
    public boolean displayScreen()
    {
        boolean out = loadCSS();
        if (out)
        {
            getStage().show();
            LOG.debug("Successfully displaying the view now: \"" + getClass().getSimpleName() + "\"");
        }

        return out;
    }

    /**
     * Loads the external css stylesheet.
     *
     * @return Whether the new stylesheet was successfully loaded.
     */
    protected boolean loadCSS()
    {
        // Apply css styles from external stylesheet
        List<URL> cssPaths = new ArrayList<>();
        cssPaths.add(getMainCSS());
        cssPaths.addAll(getCSSPaths());
        getStage().getScene().getStylesheets().clear();

        for (URL cssURL : cssPaths)
        {
            if (cssURL == null)
            {
                LOG.error("Couldn't find css of view: " + getClass().getName());
                continue;
            }
            // Turns the URL into a String representation
            String css = cssURL.toExternalForm();
            getStage().getScene().getStylesheets().add(css);
            LOG.debug("Loaded css file \"" + cssURL.getFile() + "\" for current view: \"" + getClass().getSimpleName() +
                    "\"");
        }

        return true;
    }

    @SafeVarargs
    public final void assignTooltipsToElements(Tuple<Control, String>... elements)
    {
        if (elements == null)
        {
            return;
        }

        assignTooltipsToElements(Utils.toList(elements));
    }

    @Override
    public final void assignTooltipsToElements(List<Tuple<Control, String>> elements)
    {
        if (elements == null)
        {
            return;
        }

        // Iterate over each entry and set the tool tip text to the respective JavaFX element.
        for (Tuple<Control, String> element : elements)
        {
            // If any element is null, skip it.
            if (element == null || element.getVal1() == null || element.getVal2() == null)
            {
                continue;
            }

            // Apply settings values to tooltip
            Tooltip tip = Utils.toToolTip(element.getVal2());
            Utils.run(() -> element.getVal1().setTooltip(tip));
        }
    }

    /**
     * Assigns the given Labeled-Element the text retrieved from the translation files.
     *
     * @param elements The list of tuples: Val1 = Element in JavaFX, that needs to be labeled, Val2 = Translation
     */
    protected final void assignTranslLabel(List<Tuple<Labeled, String>> elements)
    {
        if (elements == null)
        {
            return;
        }

        // Iterate over each entry and set the tool tip text to the respective JavaFX element.
        for (Tuple<Labeled, String> element : elements)
        {
            // If any element is null, skip it.
            if (element == null || element.getVal1() == null || element.getVal2() == null)
            {
                continue;
            }

            Utils.run(() -> element.getVal1().setText(element.getVal2()));
        }
    }

    /**
     * Looks up the given element.
     *
     * @param id The id of the element to look for.
     * @return The element that was looked for in root.
     */
    protected Node lookupID(String id)
    {
        return root.lookup("#" + id);
    }

    /**
     * Looks up all the given buttons on the current page.
     *
     * @param selector The selector that is supposed to be looked up.
     * @return The set of all selected elements.
     */
    protected Set<Node> lookupAll(String selector)
    {
        return root.lookupAll(selector);
    }

    // ################
    // Abstract Methods
    // ################

    /**
     * The absolute path to the FXML file.
     *
     * @return The absolute path to the FXML file.
     */
    protected abstract URL getFXMLPath();

    /**
     * The absolute path to the CSS files.
     *
     * @return A list of all absolute paths to the CSS files.
     */
    protected abstract List<URL> getCSSPaths();

    // ###############
    // Getter & Setter
    // ###############

    @Override
    public Parent getRoot()
    {
        return root;
    }

    public void setRoot(Parent root)
    {
        this.root = root;
    }

    @Override
    public IController getController()
    {
        return controller;
    }

    @Override
    public IModel getModel()
    {
        return model;
    }

    /**
     * The title for the window screen, that is currently displayed. May change when contents in the window itself
     * changes.
     *
     * @return The current screen title.
     */
    protected String getScreenTitle()
    {
        return screenTitle;
    }

    @Override
    public Stage getStage()
    {
        return stage;
    }

    /**
     * Sets the new screen title and immediately updates the title.
     *
     * @param screenTitle The new title for this window. The name of the application will stay as a prefix, followed
     *                    by a dash "-".
     */
    protected void setScreenTitle(String screenTitle)
    {
        this.screenTitle = screenTitle;
        getStage().setTitle(ConstStartModule.FXML_MAIN_TITLE + " - " + getScreenTitle());
    }

    @Override
    public FontIcon getModuleImage()
    {
        return new FontIcon("bi-slash-square");
    }

    protected URL getMainCSS()
    {
        return DeprecatedAbstractView.class.getResource(ConstScreen.FXML_CSS_MAIN);
    }
}
