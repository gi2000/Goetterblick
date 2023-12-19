package modules.start;

import data.annotations.Module;
import data.consts.modules.ConstStartModule;
import data.general.Tuple;
import data.translations.ui.modules.TStartModule;
import javafx.geometry.HPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import modules.general.abstracts.AbstractView;
import modules.general.facades.IController;
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

public class StartView extends AbstractView
{
    private final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private StartModel      startModel;
    private StartController startController;

    private final Map<String, Button> buttonMap;


    private String buttonIdDSA1    = "radioDSA1";
    private String buttonIdDSA2    = "radioDSA2";
    private String buttonIdDSA3    = "radioDSA3";
    private String buttonIdDSA4    = "radioDSA4";
    private String buttonIdDSA4d1  = "radioDSA4d1";
    private String buttonIdDSA5    = "radioDSA5";
    private String buttonIdDSK     = "radioDSK";
    private String buttonIdMyranor = "radioMyranor";

    public StartView()
    {
        super(ModuleHandler.getInstance("start"));
        this.buttonMap = new HashMap<>();
    }

    @Override
    public boolean initElements()
    {
        startModel = (StartModel) getModel();
        startController = (StartController) getController();

        buttonMap.put(buttonIdDSA1, (Button) lookupID(buttonIdDSA1));
        buttonMap.put(buttonIdDSA2, (Button) lookupID(buttonIdDSA2));
        buttonMap.put(buttonIdDSA3, (Button) lookupID(buttonIdDSA3));
        buttonMap.put(buttonIdDSA4, (Button) lookupID(buttonIdDSA4));
        buttonMap.put(buttonIdDSA4d1, (Button) lookupID(buttonIdDSA4d1));
        buttonMap.put(buttonIdDSA5, (Button) lookupID(buttonIdDSA5));
        buttonMap.put(buttonIdDSK, (Button) lookupID(buttonIdDSK));
        buttonMap.put(buttonIdMyranor, (Button) lookupID(buttonIdMyranor));

        assignTooltipsToElements(
                new Tuple<>(buttonMap.get(buttonIdDSA1), startModel.getTransl(TStartModule.VERSIONS_DSA1_TT)),
                new Tuple<>(buttonMap.get(buttonIdDSA2), startModel.getTransl(TStartModule.VERSIONS_DSA2_TT)),
                new Tuple<>(buttonMap.get(buttonIdDSA3), startModel.getTransl(TStartModule.VERSIONS_DSA3_TT)),
                new Tuple<>(buttonMap.get(buttonIdDSA4), startModel.getTransl(TStartModule.VERSIONS_DSA4_TT)),
                new Tuple<>(buttonMap.get(buttonIdDSA4d1), startModel.getTransl(TStartModule.VERSIONS_DSA4D1_TT)),
                new Tuple<>(buttonMap.get(buttonIdDSA5), startModel.getTransl(TStartModule.VERSIONS_DSA5_TT)),
                new Tuple<>(buttonMap.get(buttonIdDSK), startModel.getTransl(TStartModule.VERSIONS_DSK_TT)),
                new Tuple<>(buttonMap.get(buttonIdMyranor), startModel.getTransl(TStartModule.VERSIONS_MYR_TT))
        );

        assignTranslLabel(
                new Tuple<>(buttonMap.get(buttonIdDSA1), startModel.getTransl(TStartModule.VERSIONS_DSA1_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdDSA2), startModel.getTransl(TStartModule.VERSIONS_DSA2_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdDSA3), startModel.getTransl(TStartModule.VERSIONS_DSA3_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdDSA3), startModel.getTransl(TStartModule.VERSIONS_DSA4_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdDSA4d1), startModel.getTransl(TStartModule.VERSIONS_DSA4D1_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdDSA5), startModel.getTransl(TStartModule.VERSIONS_DSA5_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdDSK), startModel.getTransl(TStartModule.VERSIONS_DSK_LABEL)),
                new Tuple<>(buttonMap.get(buttonIdMyranor), startModel.getTransl(TStartModule.VERSIONS_MYR_LABEL))
        );

        setImagesForModules();
        return true;
    }

    @Override
    public boolean deconstruct()
    {
        return true;
    }

    private void setImagesForModules()
    {
        // Retrieve all module buttons
        Set<Node> allButtons = lookupAll(".button");
        List<IController> allModules = ModuleHandler.getAllModules()
                .stream()
                .sorted(Comparator.comparing(IController::getModuleName))
                .collect(Collectors.toList());

        // Iterate over all controllers to then find the related module buttons, if they exist.
        for (IController contr : allModules)
        {
            IView view = contr.getView();
            String nameModule = contr.getClass().getDeclaredAnnotation(Module.class).name();
            // Find a button in the root fxml, that could match the related button
            Button relatedButton = allButtons.stream()
                    .map(node -> (Button) node)
                    .filter(button -> button.getId().toLowerCase().contains("buttonModule".toLowerCase()) &&
                                      button.getId().toLowerCase().contains(nameModule.toLowerCase()))
                    .findFirst()
                    .orElse(null);

            // If the related button is not found, create it as a new module button.
            if (relatedButton == null)
            {
                GridPane grid = (GridPane) lookupID("paneModules");
                // Apply css constraints
                Tuple<Button, AnchorPane> newModuleButton = initNewModuleButton(view);
                ColumnConstraints constr = new ColumnConstraints(
                        50,
                        200,
                        Control.USE_COMPUTED_SIZE,
                        Priority.SOMETIMES,
                        HPos.CENTER, true
                );
                grid.addColumn(grid.getColumnCount(), newModuleButton.getVal2());
                grid.getColumnConstraints().add(constr);

                relatedButton = newModuleButton.getVal1();
                LOG.info("Found new module: " + contr.getModuleName() + ".");
            }

            // Then just apply the control
            Button finalRelatedButton = relatedButton;
            Utils.run(() ->
            {
                // Retrieve the icon and mark it as a module icon for the css
                FontIcon icon = view.getModuleImage();
                Utils.addCssClass(icon, "module-icons");

                // Then apply the module specific tooltip and button size.
                finalRelatedButton.setTooltip(Utils.toToolTip(contr.getModuleTooltip()));
                finalRelatedButton.setGraphic(icon);
                finalRelatedButton.setMinHeight(icon.getIconSize() + finalRelatedButton.getFont().getSize() + 25);
            });
        }
    }

    // ###############
    // Getter & Setter
    // ###############

    /**
     * Returns any button from the version selector buttons.
     *
     * @param fallBack The fallback, if no selection button was found.
     * @return Either a select-version-button or the fallback. If the fallback is null, then null will be returned.
     * @apiNote This method is needed to enable the scaling of images inside the module buttons. Due to an unknown bug which
     * constantly increases the size of these image, the width-property has to be taken from the version selection buttons (like
     * "DSA1" or "DSA2"), since they are the same width but aren't effected by the image-resizing. The increasing size bug only
     * occurs when the width-property is bound to the parent module-button: When one scales the window smaller, the
     * returned, observed width value is still positive, even though it should be negative since the window got smaller.
     */
    private Button getAnyVersionButton(Button fallBack)
    {
        return buttonMap.entrySet()
                .stream()
                .findAny()
                .orElse(new AbstractMap.SimpleEntry<>("", fallBack))
                .getValue();
    }

    private Tuple<Button, AnchorPane> initNewModuleButton(IView view)
    {
        Button moduleButton = new Button(view.getController().getModuleName());
        moduleButton.setCursor(Cursor.HAND);
        moduleButton.setId("buttonModule" + Utils.cap(view.getController().getClass().getDeclaredAnnotation(Module.class).name()));
        moduleButton.setWrapText(true);
        moduleButton.setMnemonicParsing(false);
        moduleButton.setGraphicTextGap(15);
        moduleButton.setContentDisplay(ContentDisplay.TOP);

        AnchorPane ap = new AnchorPane(moduleButton);
        ap.setMinWidth(50);
        ap.setMinHeight(150);

        AnchorPane.setTopAnchor(moduleButton, 0.0);
        AnchorPane.setLeftAnchor(moduleButton, 0.0);
        AnchorPane.setBottomAnchor(moduleButton, 0.0);
        AnchorPane.setRightAnchor(moduleButton, 0.0);

        return new Tuple<>(moduleButton, ap);
    }

    private Button getSelectedButton()
    {
        return null;
    }

    @Override
    public URL getFXMLPath()
    {
        return StartView.class.getResource(ConstStartModule.FXML_START_WINDOW);
    }

    @Override
    public List<URL> getCSSPaths()
    {
        return Utils.toList(ConstStartModule.FXML_START_CSS)
                .stream()
                .map(StartView.class::getResource)
                .collect(Collectors.toList());
    }
}
