package modules.start;

import data.annotations.Module;
import data.enums.DSAVersion;
import data.general.Tuple;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.layout.*;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import org.kordamp.ikonli.javafx.FontIcon;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.LoggerHandler;
import utils.handler.ModuleHandler;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Module(name = "start", isDisplayedInHome = false)
public class StartController extends AbstractController
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private final Map<DSAVersion, Button>  versionButtonMap = new HashMap<>();
    private final Map<Button, DSAVersion>  buttonVersionMap = new HashMap<>();
    private final Map<Button, IController> modulesButtonMap = new HashMap<>();

    // ###############
    // JavaFX Elements
    // ###############

    @FXML
    private AnchorPane root;
    @FXML
    private Button     buttonModuleLegalNotice;
    @FXML
    private Button     buttonModuleSettings;
    @FXML
    private GridPane   paneModules;
    @FXML
    private GridPane   paneVersions;

    // ###############

    private StartModel startModel;
    private StartView  startView;

    @Override
    public boolean initElements()
    {
        startModel = (StartModel) getModel();
        startView = (StartView) getView();

        return initAllVersionButtons() && initAllModuleButtons() && initStaticButtons();
    }

    public IModel createModel()
    {
        return new StartModel();
    }

    public IView createView()
    {
        return new StartView(this, getStartModel(), getStage(), getRoot(), versionButtonMap, buttonVersionMap,
                modulesButtonMap, paneVersions, paneModules);
    }

    // ###################
    // Button Interactions
    // ###################

    /**
     * Initializes the other buttons, that have an effect on the scene. Like f. ex. the settings or legal button.
     */
    public boolean initStaticButtons()
    {
        buttonModuleSettings.setOnAction((event) ->
        {
            deconstruct(true);
            IController contr = ModuleHandler.getInstance("settings");
            LOG.info("Settings == null ? {}", contr == null);
            if (contr == null)
            {
                return;
            }

            switchToModule(contr);
        });

        return true;
    }

    /**
     * Initializes and creates all version buttons, that are referenced in the enum DSAVersion. Once setup, all
     * buttons get enabled,
     * that have isActive set to true.
     */
    public boolean initAllVersionButtons()
    {
        // Retrieve all versions and prepare their tooltips + labels
        DSAVersion[] allVersions = DSAVersion.values();
        List<Tuple<Control, String>> tooltips = new ArrayList<>(allVersions.length);
        List<Tuple<Labeled, String>> labels = new ArrayList<>(allVersions.length);

        // Iterate over all versions to add them to the screen
        for (DSAVersion version : allVersions)
        {
            // Create a new version button and apply the translation's corresponding name to it.
            Tuple<Button, AnchorPane> tuple = getNewVersionButton(version);
            tooltips.add(new Tuple<>(tuple.getVal1(), startModel.getTransl(version.getTranslToolTipKey())));
            labels.add(new Tuple<>(tuple.getVal1(), startModel.getTransl(version.getTranslLabelKey())));

            // Create the constraints to let the button be centered and stretched if the window resizes
            RowConstraints constrRow = getRowConstraints(50, 100);
            ColumnConstraints constrColumn = getColumnConstraints(50, 200);

            // Retrieve necessary variables for adding the version button to the gridpane
            int versionsPerRow = startModel.getVersionsPerRow();
            // Only allows a given amount of versions per row. Default is 4.
            int amtVersionsInCurrentRow = paneVersions.getChildren().size() % versionsPerRow;

            // If the current column index is 0, then add a new row.
            if (amtVersionsInCurrentRow == 0)
            {
                paneVersions.addRow(paneVersions.getRowCount());
                paneVersions.getRowConstraints().add(constrRow);
            }
            // If the current column amount is smaller than the current amount of versions existing, then a new
            // column has to be
            // added to the gridpane.
            if (paneVersions.getChildren().size() < versionsPerRow)
            {
                paneVersions.addColumn(paneVersions.getColumnCount());
                paneVersions.getColumnConstraints().add(constrColumn);
            }

            // Finally add the button to the (new) column and row.
            paneVersions.add(tuple.getVal2(), amtVersionsInCurrentRow, paneVersions.getRowCount() - 1);

            versionButtonMap.put(version, tuple.getVal1());
            buttonVersionMap.put(tuple.getVal1(), version);
        }

        // Assign tooltips and labels
        getView().assignTooltipsToElements(tooltips);
        getView().assignTranslLabels(labels);
        updateStartingVersionButton();

        return true;
    }

    /**
     * Initializes all modules with corresponding module buttons. If buttons with the name "buttonModuleXYZ" are
     * present in the fxml
     * ("XYZ" being the name of the module annotated) then no new button is created.
     */
    private boolean initAllModuleButtons()
    {
        // Retrieve all module buttons
        Set<Node> allButtons = lookupAll(".button");
        List<IController> allModules =
                startModel.getAllModules().stream().sorted(Comparator.comparing(IController::getModuleName)).toList();

        List<Tuple<Control, String>> tooltips = new ArrayList<>();
        List<Tuple<Labeled, String>> labels = new ArrayList<>();

        // Iterate over all controllers to then find the related module buttons, if they exist.
        for (IController contr : allModules)
        {
            IView view = contr.getView();
            String nameModule = getModuleAnnotationName(contr);

            // Find a button in the root fxml, that could match the related button
            Button relatedButton = allButtons.stream()
                                             .map(node -> (Button) node)
                                             .filter(button -> button.getId()
                                                                     .toLowerCase()
                                                                     .contains("buttonModule".toLowerCase()) &&
                                                     button.getId().toLowerCase().contains(nameModule.toLowerCase()))
                                             .findFirst()
                                             .orElse(null);

            // If the related button is not found, create it as a new module button.
            if (relatedButton == null)
            {
                GridPane grid = (GridPane) lookupID("paneModules");

                // Apply css constraints
                Tuple<Button, AnchorPane> newModuleButton = getNewModuleButton();
                RowConstraints constrRow = getRowConstraints(100, 200);
                ColumnConstraints constr = getColumnConstraints(55, 100);
                grid.getRowConstraints().setAll(constrRow);
                grid.addColumn(grid.getColumnCount(), newModuleButton.getVal2());
                grid.getColumnConstraints().add(constr);

                relatedButton = newModuleButton.getVal1();
                LOG.info("Found new module: {}.", contr.getModuleName());
            }

            tooltips.add(new Tuple<>(relatedButton, contr.getModuleTooltip()));
            labels.add(new Tuple<>(relatedButton, contr.getModuleName()));
            modulesButtonMap.put(relatedButton, contr);

            // Then just apply the control
            Button finalRelatedButton = relatedButton;
            Utils.run(() ->
            {
                // Retrieve the icon and mark it as a module icon for the css
                FontIcon icon = view.getModuleImage();
                Utils.addCssClass(icon, ConstStartModule.CSS_MODULES_FONT_ICON);
                finalRelatedButton.setGraphic(icon);
            });
        }

        getView().assignTooltipsToElements(tooltips);
        getView().assignTranslLabels(labels);
        return true;
    }

    /**
     * Loads the version button, that is set in the config.
     */
    private void updateStartingVersionButton()
    {
        DSAVersion selectedVersion = getModel().getMainCfg()
                                               .get(DSAVersion.class, ConstStartModule.DSA_STARTING_VERSION.getVal1(),
                                                       ConstStartModule.DSA_STARTING_VERSION.getVal2());

        startModel.setSelectedVersion(selectedVersion);
        Utils.addCssClass(versionButtonMap.get(selectedVersion), ConstStartModule.CSS_VERSIONS_SELECTED_VERSION);
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


    // #################
    // Getter and Setter
    // #################

    /**
     * The default row constraint, that is used in the module buttons and version buttons.
     *
     * @param minHeight  The minimum height required for the button to be displayed.
     * @param prefHeight The preferred height required for the button to be displayed nicely.
     * @return The newly created RowConstraints.
     */
    private RowConstraints getRowConstraints(double minHeight, double prefHeight)
    {
        return new RowConstraints(minHeight, prefHeight, Control.USE_COMPUTED_SIZE, Priority.SOMETIMES, VPos.CENTER,
                true);
    }

    /**
     * The default column constraint, that is used in the module buttons and version buttons.
     *
     * @param minWidth  The minimum width required for the button to be displayed.
     * @param prefWidth The preferred width required for the button to be displayed nicely.
     * @return The newly created ColumnConstraints.
     */
    private ColumnConstraints getColumnConstraints(double minWidth, double prefWidth)
    {
        return new ColumnConstraints(minWidth, prefWidth, Double.MAX_VALUE, Priority.SOMETIMES, HPos.LEFT, true);
    }

    /**
     * Creates a new module button, stored inside an anchorpane, with default style and function settings.
     *
     * @return The newly created module button coupled with the anchorpane it is stored in.
     */
    private Tuple<Button, AnchorPane> getNewModuleButton()
    {
        Button moduleButton = new Button();
        AnchorPane ap = new AnchorPane(moduleButton);

        moduleButton.setId("buttonModule" + Utils.cap(getClass().getDeclaredAnnotation(Module.class).name()));
        setDefaultButtonSettings(moduleButton);

        return new Tuple<>(moduleButton, ap);
    }

    /**
     * Creates a new version button, stored inside an anchorpane, with default style and function settings.
     *
     * @param version The corresponding version for the versionbutton.
     * @return The newly created version button coupled with the anchorpane it is stored in.
     */
    private Tuple<Button, AnchorPane> getNewVersionButton(DSAVersion version)
    {
        Button bVersion = new Button();
        AnchorPane ap = new AnchorPane(bVersion);

        bVersion.setId("radioVersion" + version.name());
        bVersion.setDisable(!version.isActive());
        setDefaultButtonSettings(bVersion);

        return new Tuple<>(bVersion, ap);
    }

    /**
     * Sets some default settings for the button, as f. ex. the text being wrapped, the cursor switching to a hand
     * icon etc.
     *
     * @param b The button to change these settings for.
     */
    private void setDefaultButtonSettings(Button b)
    {
        b.setCursor(Cursor.HAND);
        b.setWrapText(true);
        b.setMnemonicParsing(false);
        b.setContentDisplay(ContentDisplay.TOP);
        b.setGraphicTextGap(15);

        AnchorPane.setTopAnchor(b, 0.0);
        AnchorPane.setLeftAnchor(b, 0.0);
        AnchorPane.setBottomAnchor(b, 0.0);
        AnchorPane.setRightAnchor(b, 0.0);
    }

    /**
     * Gets the annoted module name of the given controller.
     *
     * @param controller The controller from which the annoted name is supposed to pulled.
     * @return The name as a string.
     */
    private String getModuleAnnotationName(IController controller)
    {
        return controller.getClass().getDeclaredAnnotation(Module.class).name();
    }

    @Override
    public String getModuleName()
    {
        return "Start";
    }

    public String getModuleTooltip()
    {
        return null;
    }

    public StartModel getStartModel()
    {
        return (StartModel) getModel();
    }

    public StartView getStartView()
    {
        return (StartView) getView();
    }

    @Override
    public URL getFXMLPath()
    {
        return StartController.class.getResource(ConstStartModule.FXML_START_WINDOW);
    }

    @Override
    public List<URL> getCSSPaths()
    {
        return Utils.toList(ConstStartModule.FXML_START_CSS)
                    .stream()
                    .map(StartController.class::getResource)
                    .collect(Collectors.toList());
    }
}
