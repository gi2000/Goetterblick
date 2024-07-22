package modules.settings;

import general.Initialisation;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import modules.general.abstracts.AbstractController;
import modules.general.facades.IModel;
import modules.general.facades.IView;

public class SettingsController extends AbstractController
{
    @FXML
    private Button buttonBack;

    @FXML
    private AnchorPane root;

    @Override
    public void initElements()
    {
        initButtonActions();
    }

    @Override
    public IModel createModel()
    {
        return new SettingsModel();
    }

    @Override
    public IView createView()
    {
        return new SettingsView(this, getModel());
    }

    private void initButtonActions()
    {
        buttonBack.setOnAction(event -> switchToModule(Initialisation.STARTING_MODULE));
    }
}
