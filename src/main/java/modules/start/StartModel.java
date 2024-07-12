package modules.start;

import data.enums.DSAVersion;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import modules.general.abstracts.AbstractModel;
import modules.general.facades.IController;
import utils.handler.ModuleHandler;

import java.util.List;


/**
 * The model holding all information on database data and other things for the view.
 */
public class StartModel extends AbstractModel
{

    private final ObjectProperty<DSAVersion> selectedVersion;
    private final ObjectProperty<String>     selectedModule;

    /**
     * The empty constructor for the start model holding all information on database data and other things for the view.
     */
    public StartModel()
    {
        this.selectedVersion = new SimpleObjectProperty<>(null);
        this.selectedModule = new SimpleObjectProperty<>(null);
    }

    // ######################
    // Business Logic Methods
    // ######################

    @Override
    public boolean initialize(String moduleName)
    {
        return true;
    }

    @Override
    public boolean deconstruct()
    {
        return false;
    }

    // ########################
    // Simple Setter and Getter
    // ########################

    /**
     * Retrieves all module's IControllers.
     *
     * @return A list of all module's IController
     */
    public List<IController> getAllModules()
    {
        return ModuleHandler.getAllModules();
    }

    /**
     * Returns the amount of version buttons per row in the start screen.
     *
     * @return The amount of versions per row taken from the config.
     */
    public int getVersionsPerRow()
    {
        return getMainCfg()
                .getInt(ConstStartModule.FXML_START_AMT_VERSIONS_PER_ROW.getVal1(),
                        ConstStartModule.FXML_START_AMT_VERSIONS_PER_ROW.getVal2());
    }

    /**
     * Retrieves the observable, selected version.
     *
     * @return The selected version property.
     */
    public DSAVersion getSelectedVersion()
    {
        return selectedVersion.get();
    }

    /**
     * Retrieves the selected version property for adding listeners upon value changes.
     *
     * @return The selected version property.
     */
    public ObjectProperty<DSAVersion> selectedVersionProperty()
    {
        return selectedVersion;
    }

    /**
     * Updates the selected version button.
     *
     * @param selectedVersion The newly selected version.
     */
    public void setSelectedVersion(DSAVersion selectedVersion)
    {
        this.selectedVersion.set(selectedVersion);
    }

    /**
     * The currently selected module's annotation name as a string.
     *
     * @return The selected module.
     */
    public String getSelectedModule()
    {
        return selectedModule.get();
    }

    /**
     * The selected module property for adding event listeners.
     *
     * @return The selected module property.
     */
    public ObjectProperty<String> selectedModuleProperty()
    {
        return selectedModule;
    }

    /**
     * Sets the currently selected module's annotation name.
     *
     * @param selectedModule The selected module.
     */
    public void setSelectedModule(String selectedModule)
    {
        this.selectedModule.set(selectedModule);
    }
}
