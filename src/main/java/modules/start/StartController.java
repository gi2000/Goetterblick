package modules.start;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IModule;
import modules.general.facades.IView;


public class StartController implements IController
{
    @FXML
    private Button   buttonModuleChar;
    @FXML
    private Button   buttonModuleGM;
    @FXML
    private Button   buttonModuleLegalNotice;
    @FXML
    private Button   buttonModuleMap;
    @FXML
    private Button   buttonModuleSettings;
    @FXML
    private Button   buttonModuleWiki;
    @FXML
    private GridPane paneModules;
    @FXML
    private GridPane paneVersions;
    @FXML
    private Button   radioDSA1;
    @FXML
    private Button   radioDSA2;
    @FXML
    private Button   radioDSA3;
    @FXML
    private Button   radioDSA4;
    @FXML
    private Button   radioDSA4d1;
    @FXML
    private Button   radioDSA5;
    @FXML
    private Button   radioDSK;
    @FXML
    private Button   radioMyranor;

    @FXML
    private AnchorPane root;

    @FXML
    @Override
    public void initialize()
    {
        Tooltip tip = new Tooltip("Wählt DSA 5 für alle Module aus.");
        tip.setShowDuration(Duration.INDEFINITE);
        tip.setShowDelay(Duration.seconds(0.5));

        radioDSA5.setTooltip(tip);
    }

    public boolean deconstruct()
    {
        return false;
    }

    public boolean switchToModule(IModule module)
    {
        return false;
    }

    public void resizeElements(double sizeFactor)
    {

    }

    public IModel getModel()
    {
        return null;
    }

    public IView getView()
    {
        return null;
    }
}
