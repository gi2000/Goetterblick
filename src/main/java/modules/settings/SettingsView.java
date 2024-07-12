package modules.settings;

import javafx.scene.Parent;
import javafx.stage.Stage;
import modules.general.abstracts.AbstractView;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import org.kordamp.ikonli.javafx.FontIcon;

public class SettingsView extends AbstractView
{
    public SettingsView(IController controller, IModel model, Stage stage, Parent root)
    {
        super(controller, model, stage, root);
    }

    @Override
    public boolean initialize()
    {
        return false;
    }

    @Override
    public boolean deconstruct()
    {
        return false;
    }

    @Override
    public FontIcon getModuleImage()
    {
        return new FontIcon("bi-book");
    }
}
