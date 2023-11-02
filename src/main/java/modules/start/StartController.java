package modules.start;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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

    private StartView  startView;
    private StartModel startModel;

    // #######
    // Methods
    // #######

    @FXML
    @Override
    public void initialize()
    {


    }

    public boolean deconstruct()
    {
        return startView.deconstruct() && startModel.deconstruct();
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
        return startModel;
    }

    public IView getView()
    {
        return startView;
    }
}
