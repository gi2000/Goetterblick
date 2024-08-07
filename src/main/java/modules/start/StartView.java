package modules.start;

import data.enums.DSAVersion;
import data.enums.Module;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import modules.general.abstracts.AbstractView;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.LoggerHandler;

import java.lang.invoke.MethodHandles;
import java.util.Map;

public class StartView extends AbstractView
{
    private final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private StartModel      startModel;
    private StartController startController;

    private final Map<DSAVersion, Button> versionButtonMap;
    private final Map<Button, Module>     modulesButtonMap;

    // ###############
    // JavaFX Elements
    // ###############

    private final GridPane paneVersions;
    private final GridPane paneModules;

    // ###############

    public StartView(StartController controller, StartModel model, Map<DSAVersion, Button> versionButtonMap,
            Map<Button, Module> modulesButtonMap, GridPane paneVersions, GridPane paneModules)

    {
        super(controller, model);
        this.versionButtonMap = versionButtonMap;
        this.modulesButtonMap = modulesButtonMap;

        this.paneVersions = paneVersions;
        this.paneModules = paneModules;

        this.startModel = model;
        this.startController = controller;
    }

    @Override
    public boolean initialize()
    {
        startModel = (StartModel) getModel();
        startController = (StartController) getController();

        initVersionButtonEvents();
        initModuleButtonEvents();

        return true;
    }

    @Override
    public boolean deconstruct()
    {
        return true;
    }

    /**
     * Initializes the version buttons' events.
     */
    private void initVersionButtonEvents()
    {
        Utils.makeRadioButtonOnSelection(ConstStartModule.CSS_VERSIONS_SELECTED_VERSION, versionButtonMap.values());
    }

    /**
     * Initializes the module buttons' events.
     */
    private void initModuleButtonEvents()
    {
        for (Button b : versionButtonMap.values())
        {
            b.setOnAction((event) -> startController.switchToModule(modulesButtonMap.get(b)));
        }
    }
}
