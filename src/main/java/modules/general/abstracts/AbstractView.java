package modules.general.abstracts;

import data.general.Tuple;
import javafx.scene.Parent;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import org.kordamp.ikonli.javafx.FontIcon;
import utils.general.Utils;

import java.util.List;

public abstract class AbstractView implements IView
{
    private final IController controller;
    private final IModel      model;
    private final Stage       stage;
    private final Parent      root;

    public AbstractView(IController controller, IModel model, Stage stage, Parent root)
    {
        this.controller = controller;
        this.model = model;
        this.stage = stage;
        this.root = root;
    }

    @Override
    public void assignTooltipsToElements(List<Tuple<Control, String>> elements)
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
    public void assignTranslLabels(List<Tuple<Labeled, String>> elements)
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

    @Override
    public Parent getRoot()
    {
        return root;
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

    @Override
    public Stage getStage()
    {
        return stage;
    }

    @Override
    public FontIcon getModuleImage()
    {
        return new FontIcon("bi-slash-square");
    }
}
